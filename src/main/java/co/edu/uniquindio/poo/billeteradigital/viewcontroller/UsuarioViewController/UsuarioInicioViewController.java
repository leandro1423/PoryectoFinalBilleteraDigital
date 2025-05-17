package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.controller.UsuarioController;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import static co.edu.uniquindio.poo.billeteradigital.utils.MensajesInformacionConstantes.*;

import co.edu.uniquindio.poo.billeteradigital.utils.ValidacionUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioInicioViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnRegistrarse;

    private UsuarioController usuarioController;

    @FXML
    public void initialize() {
        usuarioController = new UsuarioController();
        btnRegistrarse.setOnAction(event -> abrirVentanaRegistro());
    }

    @FXML
    private void ingresar() {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();

        // Validar campos
        if (!ValidacionUtils.camposSonValidos(nombre, cedula)) {
            mostrarMensaje("Error", null, CAMPOS_OBLIGATORIOS, Alert.AlertType.ERROR);
            return;
        }

        // Crear usuario temporal para la validación
        Usuario usuarioTmp = new Usuario();
        usuarioTmp.setIdUsuario(cedula);
        usuarioTmp.setNombre(nombre);

        boolean logueado = usuarioController.loguearUsuario(usuarioTmp);

        if (logueado) {
            mostrarMensaje("Bienvenido", null, "Bienvenido " + nombre, Alert.AlertType.INFORMATION);
            abrirVentanaPrincipal(usuarioTmp);
        } else {
            mostrarMensaje("Error", null, USUARIO_NO_ENCONTRADO, Alert.AlertType.ERROR);
        }
    }

    private void abrirVentanaPrincipal(Usuario usuario) {
        try {
            // Asegúrate que la ruta es relativa al paquete de la clase actual
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/PrincipalUsuarioView.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Billetera Digital - Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana principal.", Alert.AlertType.ERROR);
        }
    }

    private void abrirVentanaRegistro() {
        try {
            // Asegúrate que la ruta es relativa al paquete de la clase actual
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/VentanaPrincipalUsuario.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Registrar Usuario");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana sea modal
            stage.showAndWait(); // Espera a que la ventana se cierre antes de continuar

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de registro.", Alert.AlertType.ERROR);
        }
    }



    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
