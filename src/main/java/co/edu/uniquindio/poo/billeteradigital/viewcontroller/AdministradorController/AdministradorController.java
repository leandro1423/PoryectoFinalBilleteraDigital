package co.edu.uniquindio.poo.billeteradigital.viewcontroller.AdministradorController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorController {

    @FXML private Button btnCrearUsuario;
    @FXML private Button btnActualizarUsuario;
    @FXML private Button btnEliminarUsuario;
    @FXML private Button btnListarUsuarios;

    @FXML private Button btnAgregarCuenta;
    @FXML private Button btnActualizarCuenta;
    @FXML private Button btnEliminarCuenta;

    @FXML private Button btnCrearTransaccion;
    @FXML private Button btnListarTransacciones;

    @FXML private Button btnGastosMasComunes;
    @FXML private Button btnUsuariosConMasTransacciones;
    @FXML private Button btnSaldoPromedioUsuarios;

    @FXML
    public void onCrearUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormulariosView/RegistrarUsuarioView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registrar Usuario");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de registro.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onActualizarUsuario() {
        System.out.println("Actualizar Usuario presionado");
    }

    @FXML
    public void onEliminarUsuario() {
        System.out.println("Eliminar Usuario presionado");
    }

    @FXML
    public void onListarUsuarios() {
        System.out.println("Listar Usuarios presionado");
    }

    @FXML
    public void onAgregarCuenta() {
        System.out.println("Agregar Cuenta presionado");
    }

    @FXML
    public void onActualizarCuenta() {
        System.out.println("Actualizar Cuenta presionado");
    }

    @FXML
    public void onEliminarCuenta() {
        System.out.println("Eliminar Cuenta presionado");
    }

    @FXML
    public void onCrearTransaccion() {
        System.out.println("Crear Transacción presionado");
    }

    @FXML
    public void onListarTransacciones() {
        System.out.println("Listar Transacciones presionado");
    }

    @FXML
    public void onGastosMasComunes() {
        System.out.println("Gastos más comunes presionado");
    }

    @FXML
    public void onUsuariosConMasTransacciones() {
        System.out.println("Usuarios con más transacciones presionado");
    }

    @FXML
    public void onSaldoPromedioUsuarios() {
        System.out.println("Saldo promedio de usuarios presionado");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
