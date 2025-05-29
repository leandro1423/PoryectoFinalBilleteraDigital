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

public class RetirarController {

    @FXML private ComboBox<Cuenta> comboCuenta; // Combo para seleccionar la cuenta de donde retirar
    @FXML private TextField txtMontoRetiro;
    @FXML private Button btnRetirar;
    @FXML private TextField txtDescripcion; // Campo para la descripción


    private Usuario usuarioActual;
    private IUsuarioService usuarioService;
    private ITransaccionService transaccionService;

    @FXML
    public void initialize() {
        // Puedes agregar lógica si es necesario al iniciar
    }

    public void setUsuarioYServicios(Usuario usuarioActual, IUsuarioService usuarioService, ITransaccionService transaccionService) {
        this.usuarioActual = usuarioActual;
        this.usuarioService = usuarioService;
        this.transaccionService = transaccionService;

        cargarCuentas();
    }

    private void cargarCuentas() {
        ObservableList<Cuenta> cuentas = FXCollections.observableArrayList(usuarioActual.getCuentas());
        comboCuenta.setItems(cuentas);
        if (!cuentas.isEmpty()) {
            comboCuenta.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void retirar() {
        Cuenta cuentaSeleccionada = comboCuenta.getSelectionModel().getSelectedItem();
        if (cuentaSeleccionada == null) {
            mostrarMensaje("Error", null, "Debe seleccionar una cuenta", Alert.AlertType.WARNING);
            return;
        }

        String montoStr = txtMontoRetiro.getText().trim();
        String descripcion = txtDescripcion.getText().trim(); // Capturar descripción

        if (montoStr.isEmpty()) {
            mostrarMensaje("Error", null, "Ingrese el monto a retirar", Alert.AlertType.WARNING);
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                mostrarMensaje("Error", null, "El monto debe ser mayor a cero", Alert.AlertType.WARNING);
                return;
            }

            if (cuentaSeleccionada.getSaldo() < monto) {
                mostrarMensaje("Error", null, "Saldo insuficiente", Alert.AlertType.ERROR);
                return;
            }

            boolean exito = transaccionService.realizarRetiro(usuarioActual, cuentaSeleccionada, monto, descripcion);

            if (exito) {
                mostrarMensaje("Éxito", null, "Retiro realizado con éxito", Alert.AlertType.INFORMATION);
                usuarioActual = usuarioService.BuscarUsuarioPorId(usuarioActual.getIdUsuario());
                cargarCuentas();
                txtMontoRetiro.clear();
                txtDescripcion.clear();  // Limpiar el campo descripción
            } else {
                mostrarMensaje("Error", null, "No se pudo realizar el retiro", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", null, "Ingrese un número válido", Alert.AlertType.ERROR);
        }
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
