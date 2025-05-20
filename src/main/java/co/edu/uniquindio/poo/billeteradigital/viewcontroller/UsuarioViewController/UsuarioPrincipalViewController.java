package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioPrincipalViewController {

    @FXML
    private Button btnGestionarCuentas;

    @FXML
    private Button btnConfiguracion;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Button btnDepositar;

    @FXML
    private Button btnRetirar;

    @FXML
    private Button btnVerTransacciones;

    private final ModelFactoryController modelFactory = ModelFactoryController.getInstance();
    private Usuario usuarioActual;


    @FXML
    public void initialize() {
        // Obtener el usuario actual desde el Singleton o como tú lo definas
        usuarioActual = modelFactory.getUsuarioActual(); // Suponiendo que tienes esto
        if (usuarioActual != null) {
            lblNombreUsuario.setText(" " + usuarioActual.getNombre());
            lblSaldo.setText("$" + String.format("%.2f", usuarioActual.getSaldo()));
        }

    }
    private void actualizarSaldo() {
        if (usuarioActual != null) {
            lblSaldo.setText("$" + String.format("%.2f", usuarioActual.getSaldo()));
        }
    }

    @FXML
    private void handleGestionarCuentas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/GestionCuentas-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Centro De Gestion de Cuentas");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de gestion de centro de cuentas.", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void handleDeposito(ActionEvent event) {
        // Lógica para abrir ventana de depósito o realizar depósito
        System.out.println("Depositar...");
    }

    @FXML
    private void handleRetirar(ActionEvent event) {
        // Lógica para abrir ventana de retiro
        System.out.println("Retirar...");
    }

    @FXML
    private void handleVerTransacciones(ActionEvent event) {
        // Lógica para mostrar historial
        System.out.println("Ver transacciones...");
    }
    @FXML
    private void handleConfiguracionUsuario(ActionEvent event) {
        // Lógica para abrir ventana de configuracion
        System.out.println("Configuracion...");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            Stage stageActual = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stageActual.close();

            // Cargar la vista de inicio de sesión
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/UsuarioVistaInicio.fxml"));
            Parent root = loader.load();

            // Crear nueva escena y ventana
            Scene scene = new Scene(root);
            Stage stageLogin = new Stage();
            stageLogin.setTitle("Inicio de Sesión");
            stageLogin.setScene(scene);
            stageLogin.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de inicio de sesión.", Alert.AlertType.ERROR);
        }
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}
