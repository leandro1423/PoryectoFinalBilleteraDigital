package co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController;

import co.edu.uniquindio.poo.billeteradigital.interfaces.ITransaccionService;
import co.edu.uniquindio.poo.billeteradigital.interfaces.IUsuarioService;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferenciaController {

    @FXML private ComboBox<Cuenta> comboCuentaOrigen;
    @FXML private TextField txtDestinatario;
    @FXML private ComboBox<Cuenta> comboCuentaDestino;
    @FXML private TextField txtMonto;
    @FXML private TextField txtDescripcion;
    @FXML private Button btnEnviar;

    private Usuario usuarioActual;
    private IUsuarioService usuarioService;
    private ITransaccionService transaccionService;

    @FXML
    public void initialize() {
        // Deshabilitar comboCuentaDestino inicialmente
        comboCuentaDestino.setDisable(true);

        // Cuando se cambie el destinatario (perder foco o al presionar enter), buscamos sus cuentas
        txtDestinatario.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // perdió foco
                cargarCuentasDestino();
            }
        });
    }

    private void cargarCuentasOrigen() {
        // Cargar cuentas del usuario actual en el comboCuentaOrigen
        ObservableList<Cuenta> cuentasOrigen = FXCollections.observableArrayList(usuarioActual.getCuentas());
        comboCuentaOrigen.setItems(cuentasOrigen);
        if (!cuentasOrigen.isEmpty()) {
            comboCuentaOrigen.getSelectionModel().selectFirst();
        }
    }

    private void cargarCuentasDestino() {
        String idDestinatario = txtDestinatario.getText().trim();
        if (idDestinatario.isEmpty()) {
            comboCuentaDestino.getItems().clear();
            comboCuentaDestino.setDisable(true);
            return;
        }

        Usuario usuarioDestino = usuarioService.BuscarUsuarioPorId(idDestinatario);
        if (usuarioDestino == null) {
            mostrarMensaje("Error", null, "Usuario destinatario no encontrado", Alert.AlertType.ERROR);
            comboCuentaDestino.getItems().clear();
            comboCuentaDestino.setDisable(true);
            return;
        }

        ObservableList<Cuenta> cuentasDestino = FXCollections.observableArrayList(usuarioDestino.getCuentas());
        comboCuentaDestino.setItems(cuentasDestino);
        comboCuentaDestino.setDisable(false);

        if (!cuentasDestino.isEmpty()) {
            comboCuentaDestino.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void enviarTransferencia() {
        String nombreDestinatario = txtDestinatario.getText().trim();
        String montoTexto = txtMonto.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (nombreDestinatario.isEmpty() || montoTexto.isEmpty()) {
            mostrarMensaje("Campos obligatorios", "Faltan datos", "Debe llenar todos los campos obligatorios", Alert.AlertType.WARNING);
            return;
        }

        if (comboCuentaOrigen.getSelectionModel().isEmpty()) {
            mostrarMensaje("Cuenta origen", null, "Debe seleccionar la cuenta de origen", Alert.AlertType.WARNING);
            return;
        }

        if (comboCuentaDestino.getSelectionModel().isEmpty()) {
            mostrarMensaje("Cuenta destino", null, "Debe seleccionar la cuenta de destino", Alert.AlertType.WARNING);
            return;
        }

        try {
            double monto = Double.parseDouble(montoTexto);

            Usuario usuarioDestino = usuarioService.BuscarUsuarioPorId(nombreDestinatario);

            if (usuarioDestino == null) {
                mostrarMensaje("Error", null, "Usuario destinatario no encontrado", Alert.AlertType.ERROR);
                return;
            }

            Cuenta cuentaOrigen = comboCuentaOrigen.getSelectionModel().getSelectedItem();
            Cuenta cuentaDestino = comboCuentaDestino.getSelectionModel().getSelectedItem();

            boolean exito = transaccionService.realizarTransferencia(usuarioActual, cuentaOrigen, usuarioDestino, cuentaDestino, monto, descripcion);

            if (exito) {
                mostrarMensaje("Éxito", null, "Transferencia realizada con éxito", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error", null, "No se pudo realizar la transferencia. Verifique el saldo y datos.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Monto inválido", null, "Ingrese un número válido", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void setUsuarioYServicios(Usuario usuarioActual, IUsuarioService usuarioService, ITransaccionService transaccionService) {
        this.usuarioActual = usuarioActual;
        this.usuarioService = usuarioService;
        this.transaccionService = transaccionService;

        cargarCuentasOrigen();
    }
}
