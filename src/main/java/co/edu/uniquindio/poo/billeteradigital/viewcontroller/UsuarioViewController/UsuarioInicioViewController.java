package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.controller.UsuarioController;
import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import static co.edu.uniquindio.poo.billeteradigital.utils.MensajesInformacionConstantes.*;

import co.edu.uniquindio.poo.billeteradigital.model.builder.UsuarioBuilder;
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

    @FXML
    private Button btnIngresar;



    private final UsuarioController usuarioController = new UsuarioController(); // ✅


    private Usuario usuarioAdminstrador;

    @FXML
    public void initialize() {
        this.crearUsuarioAdminstrador();
        btnRegistrarse.setOnAction(event -> abrirVentanaRegistro());
        btnIngresar.setOnAction(event -> ingresar());

    }

    @FXML
    private void ingresar() {
        String nombre = txtNombre.getText().trim();
        String cedula = txtCedula.getText().trim();

        Usuario usuarioLogueado = usuarioController.BuscarUsuarioPorId(cedula);

        if (usuarioLogueado != null && usuarioLogueado.getNombre().equalsIgnoreCase(nombre)) {
            ModelFactoryController.getInstance().setUsuarioActual(usuarioLogueado);

            mostrarMensaje("Bienvenido", null, "Bienvenido " + usuarioLogueado.getNombre(), Alert.AlertType.INFORMATION);

            // Cerrar ventana login
            Stage stageActual = (Stage) btnIngresar.getScene().getWindow();
            stageActual.close();

            // Abrir la ventana según tipo
            if (usuarioLogueado.esAdministrador()) {
                abrirVentanaAdministrador();
            } else {
                abrirVentanaPrincipal(usuarioLogueado);
            }

        } else {
            mostrarMensaje("Error", null, USUARIO_NO_ENCONTRADO, Alert.AlertType.ERROR);
            limpiarCampos();
        }
    }


    private void crearUsuarioAdminstrador() {
        usuarioAdminstrador = new UsuarioBuilder()
            .conNombre("Administrador")
            .conIdUsuario("123")
            .conTipo("ADM").build();

        usuarioController.registrarUsuario(usuarioAdminstrador);
    }

    private void abrirVentanaRegistro() {
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

    private void abrirVentanaPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/UsuarioPrincipal-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Billetera Digital - Usuario");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana principal.", Alert.AlertType.ERROR);
        }
    }

    private void abrirVentanaAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdministradorView/AdministradorPrincipal-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Billetera Digital - Administrador");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana del administrador.", Alert.AlertType.ERROR);
        }
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
    }
}
