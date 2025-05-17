package co.edu.uniquindio.poo.billeteradigital.model;

import co.edu.uniquindio.poo.billeteradigital.interfaces.IBilleteraDigitalService;
import java.util.List;

public class Administrador {

    private final IBilleteraDigitalService billeteraService;

    public Administrador(IBilleteraDigitalService billeteraService) {
        this.billeteraService = billeteraService;
    }

    // --- Gestión de usuarios ---
    public void registrarUsuario(Usuario usuario) {
        billeteraService.registrarUsuario(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        billeteraService.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(String idUsuario) {
        billeteraService.eliminarUsuario(idUsuario);
    }


    public List<Usuario> verUsuarios() {
        return billeteraService.listarUsuarios();
    }



    // --- Gestión de cuentas ---
    public void agregarCuenta(Usuario usuario, Cuenta cuenta) {
        billeteraService.agregarCuenta(usuario, cuenta);
    }

    public void actualizarCuenta(Usuario usuario, Cuenta cuenta) {
        billeteraService.actualizarCuenta(usuario, cuenta);
    }

    public void eliminarCuenta(Usuario usuario, Cuenta cuenta) {
        billeteraService.eliminarCuenta(usuario, cuenta);
    }



    // --- Gestión de transacciones ---
    public void crearTransaccion(Transaccion transaccion) {
        billeteraService.crearTransaccion(transaccion);
    }

    public List<Transaccion> listarTransacciones() {
        return billeteraService.listarTransacciones();
    }
}
