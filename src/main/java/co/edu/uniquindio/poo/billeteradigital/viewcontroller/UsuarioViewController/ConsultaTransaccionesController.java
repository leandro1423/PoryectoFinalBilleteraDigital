package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.interfaces.ITransaccionService;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsultaTransaccionesController {

    @FXML private ComboBox<String> comboTipoTransaccion;

    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, String> colFecha;
    @FXML private TableColumn<Transaccion, TipoTransaccion> colTipo;
    @FXML private TableColumn<Transaccion, Double> colMonto;
    @FXML private TableColumn<Transaccion, String> colDescripcion;

    @FXML private Label lblTotalEnviado;
    @FXML private Label lblTotalRecibido;
    @FXML private Label lblBalance;

    private Usuario usuarioActual;
    private ITransaccionService transaccionService;

    private ObservableList<Transaccion> listaTransacciones;

    public void setUsuarioYServicios(Usuario usuario, ITransaccionService transaccionService) {
        this.transaccionService = transaccionService;

        // 👉 Recargar usuario actualizado desde el servicio
        this.usuarioActual = transaccionService.obtenerUsuarioPorId(usuario.getIdUsuario());

        inicializarDatos();
    }

    private void inicializarDatos() {
        // Configurar columnas
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        cargarTransacciones();

        // Cargar filtro
        comboTipoTransaccion.getItems().clear();
        comboTipoTransaccion.getItems().add("Todos");
        for (TipoTransaccion tipo : TipoTransaccion.values()) {
            comboTipoTransaccion.getItems().add(tipo.name());
        }
        comboTipoTransaccion.setValue("Todos");

        comboTipoTransaccion.setOnAction(e -> aplicarFiltro());

        // Mostrar totales iniciales
        actualizarTotales(listaTransacciones);
    }

    private void cargarTransacciones() {
        if (usuarioActual == null || usuarioActual.getCuentas() == null || usuarioActual.getCuentas().isEmpty()) {
            listaTransacciones = FXCollections.observableArrayList();
            tablaTransacciones.setItems(listaTransacciones);
            return;
        }

        Set<Transaccion> transaccionesUnicas = new HashSet<>();
        for (var cuenta : usuarioActual.getCuentas()) {
            List<Transaccion> transaccionesCuenta = transaccionService.buscarTransaccionesPorIdCuenta(cuenta.getIdCuenta());
            if (transaccionesCuenta != null) {
                transaccionesUnicas.addAll(transaccionesCuenta);
            }
        }

        listaTransacciones = FXCollections.observableArrayList(transaccionesUnicas);
        tablaTransacciones.setItems(listaTransacciones);
    }

    private void aplicarFiltro() {
        String filtro = comboTipoTransaccion.getValue();

        if (filtro.equals("Todos")) {
            tablaTransacciones.setItems(listaTransacciones);
            actualizarTotales(listaTransacciones);
        } else {
            ObservableList<Transaccion> filtradas = listaTransacciones.filtered(
                    t -> t.getTipoTransaccion().name().equalsIgnoreCase(filtro)
            );
            tablaTransacciones.setItems(filtradas);
            actualizarTotales(filtradas);
        }
    }

    private void actualizarTotales(ObservableList<Transaccion> transacciones) {
        double totalEnviado = 0.0;
        double totalRecibido = 0.0;

        for (Transaccion t : transacciones) {
            if (t.getTipoTransaccion() == TipoTransaccion.TRANSFERENCIA_ENVIADA) {
                totalEnviado += t.getMonto();
            } else if (t.getTipoTransaccion() == TipoTransaccion.TRANSFERENCIA_RECIBIDA) {
                totalRecibido += t.getMonto();
            }
        }

        lblTotalEnviado.setText(String.format("%.2f", totalEnviado));
        lblTotalRecibido.setText(String.format("%.2f", totalRecibido));
        lblBalance.setText(String.format("%.2f", totalRecibido - totalEnviado));
    }
}
