package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.interfaces.IBilleteraDigitalService;
import co.edu.uniquindio.poo.billeteradigital.interfaces.IUsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.service.UsuarioService;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ConfiguracionUsuarioController {
    @FXML
    private Button eliminarBtn;



    @FXML
    private TextField nombreField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField telefonoField;

    @FXML
    private Button actualizarBtn;

    // Variables para manejar el usuario actual y el servicio
    private Usuario usuarioActual;
    private UsuarioService usuarioService;
    private Stage stagePrincipal;

    @FXML
    public void initialize() {
        actualizarBtn.setOnAction(e -> actualizarUsuario());
        eliminarBtn.setOnAction(e -> eliminarUsuario());
    }

    public void setUsuarioYServicio(Usuario usuario, IUsuarioService usuarioService) {
        this.usuarioActual = usuario;
        this.usuarioService = (UsuarioService) usuarioService;

        nombreField.setText(usuario.getNombre());
        correoField.setText(usuario.getCorreo());
        telefonoField.setText(usuario.getTelefono());
    }

    // Método para actualizar el usuario actual
    private void actualizarUsuario() {
        if (usuarioActual != null && usuarioService != null) {
            String nuevoNombre = nombreField.getText();
            String nuevoCorreo = correoField.getText();
            String nuevoTelefono = telefonoField.getText();

            Usuario usuarioModificado = usuarioService.ModificarUsuario(
                    usuarioActual.getIdUsuario(),
                    nuevoNombre,
                    nuevoCorreo,
                    nuevoTelefono
            );

            if (usuarioModificado != null) {
                mostrarMensaje("Éxito", null, "Datos del usuario actualizados correctamente", Alert.AlertType.INFORMATION);

                // Actualiza también el objeto en el controlador
                this.usuarioActual = usuarioModificado;
            } else {
                System.out.println("⚠️ Error al actualizar usuario.");
            }
        }
    }

    @FXML
    private void eliminarUsuario() {
        if (usuarioActual != null && usuarioService != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("¿Estás seguro que deseas eliminar tu cuenta?");
            alerta.setContentText("Esta acción no se puede deshacer.");

            ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.YES);
            ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.NO);

            alerta.getButtonTypes().setAll(botonSi, botonNo);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == botonSi) {
                boolean eliminado = usuarioService.eliminarUsuario(usuarioActual.getIdUsuario());

                if (eliminado) {
                    mostrarMensaje("Usuario eliminado", null, "Tu cuenta fue eliminada exitosamente.", Alert.AlertType.INFORMATION);

                    // Cerrar ventana configuración
                    Stage stageActual = (Stage) eliminarBtn.getScene().getWindow();
                    stageActual.close();

                    // Cerrar ventana principal
                    if(stagePrincipal != null) {
                        stagePrincipal.close();
                    }

                    // Abrir ventana de inicio de sesión
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/UsuarioVistaInicio.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Inicio de Sesión");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        mostrarMensaje("Error", null, "No se pudo abrir la ventana de inicio.", Alert.AlertType.ERROR);
                    }

                } else {
                    mostrarMensaje("Error", null, "No se pudo eliminar el usuario.", Alert.AlertType.ERROR);
                }
            }
        }
    }


    private void mostrarMensaje(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    public Usuario getUsuarioActualizado() {
        return usuarioActual;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

}
