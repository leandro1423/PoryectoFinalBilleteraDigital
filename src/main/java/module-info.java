module co.edu.uniquindio.poo.billeteradigital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Exporta paquetes para que otros módulos puedan acceder
    exports co.edu.uniquindio.poo.billeteradigital;
    exports co.edu.uniquindio.poo.billeteradigital.controller;
    exports co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;
    exports co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController;
    exports co.edu.uniquindio.poo.billeteradigital.viewcontroller.AdministradorController;
    // Abre paquetes para reflexión
    opens co.edu.uniquindio.poo.billeteradigital to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteradigital.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteradigital.viewcontroller.formularioViewController to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteradigital.viewcontroller.AdministradorController to javafx.fxml;
}

