package co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoBanco;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class FormularioCuentaViewController {


    @FXML private TextField txtSaldoInicial;
    @FXML private ChoiceBox<TipoBanco> choiceBanco;
    @FXML private ChoiceBox<TipoCuenta> choiceTipo;
    @FXML private Button btnGuardar;


    private Usuario usuarioActual;

    @FXML
    private void initialize() {
        cargarTiposCuenta();
        cargarTiposBanco();
    }

    private void cargarTiposBanco() {
        choiceBanco.getItems().addAll(TipoBanco.values());
    }

    private void cargarTiposCuenta() {
        choiceTipo.getItems().addAll(TipoCuenta.values());
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void handleGuardarCuenta() {
        String saldoStr = txtSaldoInicial.getText();
        TipoBanco tipoBancoSeleccionado = choiceBanco.getValue();
        TipoCuenta tipoCuentaSeleccionado = choiceTipo.getValue();

        // Validación de campos vacíos
        if (saldoStr.isEmpty() || tipoBancoSeleccionado == null || tipoCuentaSeleccionado == null) {
            System.out.println("❗ Por favor completa todos los campos: saldo, banco y tipo de cuenta.");
            return;
        }

        try {
            double saldo = Double.parseDouble(saldoStr);

            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(UUID.randomUUID().toString());
            cuenta.setSaldo(saldo);
            cuenta.setBanco(tipoBancoSeleccionado);
            cuenta.setTipoCuenta(tipoCuentaSeleccionado);

            // Asociar la cuenta al usuario actual
            usuarioActual.getCuentas().add(cuenta);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Cuenta guardada");
            alerta.setHeaderText(null);
            alerta.setContentText("✅ La cuenta se ha guardado exitosamente.");
            alerta.showAndWait();

        } catch (NumberFormatException e) {
            System.out.println("❌ El saldo debe ser un número válido.");
        }
    }


}
