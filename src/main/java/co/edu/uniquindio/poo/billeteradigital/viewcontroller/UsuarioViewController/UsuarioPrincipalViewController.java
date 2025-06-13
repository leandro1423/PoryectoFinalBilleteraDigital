package co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.interfaces.IBilleteraDigitalService;
import co.edu.uniquindio.poo.billeteradigital.interfaces.ITransaccionService;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController.RetirarController;
import co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController.TransferenciaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioPrincipalViewController {

    // ====== FXML Elements ======
    @FXML private Button btnGestionarCuentas;
    @FXML private Button btnGestionarTransacciones;
    @FXML private Button btnCerrarSesion;
    @FXML private Button btnConfiguracion;
    @FXML private Button btnDepositar;
    @FXML private Button btnRetirar;
    @FXML private Button btnRealizarTransacciones;
    @FXML private Label lblSaldo;
    @FXML private Label lblNombreUsuario;

    // ====== Lógica Interna ======
    private final ModelFactoryController modelFactory = ModelFactoryController.getInstance();
    private Usuario usuarioActual;
    private Cuenta cuentaActual;




    // ====== Inicialización ======
    @FXML
    public void initialize() {
        usuarioActual = modelFactory.getUsuarioActual();
        if (usuarioActual != null) {
            lblNombreUsuario.setText(" " + usuarioActual.getNombre());
            actualizarSaldo();
        }
    }

    public void actualizarSaldo() {
        double saldoTotal = usuarioActual.obtenerSaldoTotal();
        lblSaldo.setText(String.format("$ %.2f", saldoTotal));
    }


    @FXML
    private void handleGestionarTransacciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/Transacciones-view.fxml"));
            Parent root = loader.load();

            IBilleteraDigitalService servicio = modelFactory.getBilleteraService();
            // Obtener el controlador y pasarle los datos necesarios
            ConsultaTransaccionesController controller = loader.getController();
            controller.setUsuarioYServicios(usuarioActual, servicio.getTransaccionService());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Centro De Gestión de Cuentas");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de gestión de Transacciones.", Alert.AlertType.ERROR);
        }
    }

    // ====== Botón: Gestionar Cuentas ======
    @FXML
    private void handleGestionarCuentas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/GestionCuentas-view.fxml"));
            Parent root = loader.load();

            // Obtener controlador y pasar usuarioActual
            GestionCuentasViewController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Centro De Gestión de Cuentas");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de gestión de cuentas.", Alert.AlertType.ERROR);
        }
    }


    // ====== Botón: Depositar ======
    @FXML
    private void handleDeposito(ActionEvent event) {
        mostrarMensaje("Error", null, "Funcion en Proceso.", Alert.AlertType.ERROR);

    }

    // ====== Botón: Retirar ======
    @FXML
    private void handleRetirar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormulariosView/RetirarSaldo-view.fxml"));
            Parent root = loader.load();

            RetirarController controller = loader.getController();
            IBilleteraDigitalService servicio = modelFactory.getBilleteraService();
            controller.setUsuarioYServicios(
                    usuarioActual,
                    servicio.getUsuarioService(),
                    servicio.getTransaccionService()
            );

            Stage stage = new Stage();
            stage.setTitle("Realizar Retiro");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // 🚨 Aquí se actualiza el objeto completo desde el servicio
            usuarioActual = servicio.getUsuarioService().BuscarUsuarioPorId(usuarioActual.getIdUsuario());
            actualizarSaldo();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de Retiro.", Alert.AlertType.ERROR);
        }
    }

    // ====== Botón: Ver Transacciones (Transferencias) ======
    @FXML
    private void handleRealizarTransacciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FormulariosView/Transferencia-view.fxml"));
            Parent root = loader.load();

            TransferenciaController controller = loader.getController();
            IBilleteraDigitalService servicio = modelFactory.getBilleteraService();
            controller.setUsuarioYServicios(
                    usuarioActual,
                    servicio.getUsuarioService(),
                    servicio.getTransaccionService()
            );

            Stage stage = new Stage();
            stage.setTitle("Realizar Transferencia");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // 🚨 Aquí se actualiza el objeto completo desde el servicio
            usuarioActual = servicio.getUsuarioService().BuscarUsuarioPorId(usuarioActual.getIdUsuario());
            actualizarSaldo();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de transferencia.", Alert.AlertType.ERROR);
        }
    }

    // ====== Botón: Configuración Usuario ======
    @FXML
    private void handleConfiguracionUsuario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/ConfiguracionUsuario-view.fxml"));
            Parent root = loader.load();

            IBilleteraDigitalService servicio = modelFactory.getBilleteraService();
            ConfiguracionUsuarioController controller = loader.getController();

            controller.setUsuarioYServicio(usuarioActual, servicio.getUsuarioService());

            Stage stagePrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();
            controller.setStagePrincipal(stagePrincipal);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Configuración del Usuario");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            Usuario usuarioActualizado = controller.getUsuarioActualizado();
            if (usuarioActualizado != null) {
                this.usuarioActual = usuarioActualizado;
                lblNombreUsuario.setText(" " + usuarioActual.getNombre());
                actualizarSaldo();
            }

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", null, "No se pudo abrir la ventana de Configuración.", Alert.AlertType.ERROR);
        }
    }

    // ====== Botón: Cerrar sesión ======
    @FXML
    private void cerrarVentana(ActionEvent event) {
        try {
            Stage stageActual = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stageActual.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UsuarioView/UsuarioVistaInicio.fxml"));
            Parent root = loader.load();

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

    // ====== Setters ======
    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    // ====== Utilidad: Mostrar Mensaje ======
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


}
