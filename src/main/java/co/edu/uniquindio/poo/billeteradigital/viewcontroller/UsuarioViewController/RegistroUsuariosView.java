package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.controller.UsuarioController;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoBanco;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoCuenta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.UUID;

public class RegistroUsuariosView {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtSaldo;

    @FXML private ChoiceBox<TipoBanco> choiceBanco;
    @FXML private ChoiceBox<TipoCuenta> choiceTipo;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    private final UsuarioController usuarioController = new UsuarioController();

    @FXML
    private void initialize() {
        cargarTiposCuenta();
        cargarTiposBanco();

        btnGuardar.setOnAction(event -> guardarUsuario());
        btnCancelar.setOnAction(event -> cerrarVentana());
    }

    private void cargarTiposBanco() {
        choiceBanco.getItems().addAll(TipoBanco.values());
    }

    private void cargarTiposCuenta() {
        choiceTipo.getItems().addAll(TipoCuenta.values());
    }

    private void guardarUsuario() {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String saldoStr = txtSaldo.getText();
        TipoBanco tipoBancoSeleccionado = choiceBanco.getValue();
        TipoCuenta tipoCuentaSeleccionado = choiceTipo.getValue();

        if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty() ||
                telefono.isEmpty() || direccion.isEmpty() || saldoStr.isEmpty() || tipoBancoSeleccionado == null|| tipoCuentaSeleccionado == null) {
            mostrarMensaje("Error", null, "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(saldoStr);
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", null, "El saldo debe ser un número válido", Alert.AlertType.ERROR);
            return;
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(cedula);
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setSaldo(saldo);

        // Crear cuenta inicial
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(UUID.randomUUID().toString()); // Generar ID aleatorio
        cuenta.setSaldo(saldo);
        cuenta.setBanco(tipoBancoSeleccionado);
        cuenta.setTipoCuenta(tipoCuentaSeleccionado);

        usuario.getCuentas().add(cuenta); // Asignar cuenta al usuario

        usuarioController.registrarUsuario(usuario);

        mostrarMensaje("Éxito", null, "Usuario y cuenta registrados correctamente", Alert.AlertType.INFORMATION);
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensaje(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
