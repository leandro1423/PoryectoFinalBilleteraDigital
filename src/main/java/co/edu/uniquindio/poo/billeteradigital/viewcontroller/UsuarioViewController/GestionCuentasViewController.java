package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController.FormularioCuentaViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionCuentasViewController {

    @FXML
    private VBox contenedorFormulario;

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private Button btnActualizarCuenta;

    private Usuario usuarioActual;

    @FXML
    private void initialize() {
        if (contenedorFormulario != null) {
            contenedorFormulario.setVisible(false);
            contenedorFormulario.setManaged(false);
        }

    }

    @FXML
    private void handleCrearCuenta() {
        try {
            // Cargar el archivo FXML del formulario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormulariosView/FormularioCuenta-view.fxml"));
            Parent root = loader.load();

            // Obtener el controlador y pasar el usuario actual
            FormularioCuentaViewController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            // Crear una nueva escena y ventana
            Scene scene = new Scene(root);
            Stage modalStage = new Stage();
            modalStage.setTitle("Formulario de Cuenta");
            modalStage.setScene(scene);
            modalStage.setResizable(false); // Opcional

            // Hacer la ventana modal
            modalStage.initModality(Modality.APPLICATION_MODAL);

            // Establecer la ventana padre (opcional, mejora el enfoque y comportamiento)
            modalStage.initOwner(btnCrearCuenta.getScene().getWindow());

            // Mostrar y bloquear hasta que se cierre
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error al abrir el formulario de cuentas en una ventana modal.");
        }
    }


    @FXML
    private void handleActualizarCuenta() {
        // Aquí puedes implementar la lógica para actualizar cuentas
        System.out.println("Actualizar cuentas - funcionalidad pendiente");
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}
