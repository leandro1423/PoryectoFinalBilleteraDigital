package co.edu.uniquindio.poo.billeteradigital.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class VentanaInicioViewController {

    @FXML
    private Button btnUsuario;

    @FXML
    private Button btnAdministrador;

    @FXML
    private void handleIngresarUsuario(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/UsuarioView/UsuarioVistaInicio.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Billetera Digital - Usuario");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) btnUsuario.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleIngresarAdministrador(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/AdministradorVista.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Billetera Digital - Administrador");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) btnAdministrador.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
