module co.edu.uniquindio.poo.billeteradigital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Exporta paquetes para que otros módulos puedan acceder
    exports co.edu.uniquindio.poo.billeteradigital;
    exports co.edu.uniquindio.poo.billeteradigital.controller;

    // Abre paquetes para reflexión (FXML necesita esto)
    opens co.edu.uniquindio.poo.billeteradigital to javafx.fxml;
    opens co.edu.uniquindio.poo.billeteradigital.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteradigital.viewcontroller;
    opens co.edu.uniquindio.poo.billeteradigital.viewcontroller to javafx.fxml;



    // Exportar el paquete que contiene los controladores
    exports co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController;

    // Abrir el paquete para reflexión
    opens co.edu.uniquindio.poo.billeteradigital.viewcontroller.UsuarioViewController to javafx.fxml;
}

