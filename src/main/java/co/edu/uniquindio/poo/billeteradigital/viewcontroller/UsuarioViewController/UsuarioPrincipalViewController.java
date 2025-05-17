package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class UsuarioPrincipalViewController {

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Button btnDepositar;

    @FXML
    private Button btnRetirar;

    @FXML
    private Button btnTransferir;

    @FXML
    private Button btnVerTransacciones;

    private final ModelFactoryController modelFactory = ModelFactoryController.getInstance();
    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        // Obtener el usuario actual desde el Singleton o como tú lo definas
        usuarioActual = modelFactory.getUsuarioActual(); // Suponiendo que tienes esto
        if (usuarioActual != null) {
            lblNombreUsuario.setText("Usuario: " + usuarioActual.getNombre());
            lblSaldo.setText("$" + String.format("%.2f", usuarioActual.getSaldo()));
        }

    }
    private void actualizarSaldo() {
        if (usuarioActual != null) {
            lblSaldo.setText("$" + String.format("%.2f", usuarioActual.getSaldo()));
        }
    }


    @FXML
    private void handleDepositar(ActionEvent event) {
        // Lógica para abrir ventana de depósito o realizar depósito
        System.out.println("Depositar...");
    }

    @FXML
    private void handleRetirar(ActionEvent event) {
        // Lógica para abrir ventana de retiro
        System.out.println("Retirar...");
    }

    @FXML
    private void handleTransferencia(ActionEvent event) {
        // Lógica para abrir ventana de transferencia
        System.out.println("Transferir...");
    }

    @FXML
    private void handleVerTransacciones(ActionEvent event) {
        // Lógica para mostrar historial
        System.out.println("Ver transacciones...");
    }
}
