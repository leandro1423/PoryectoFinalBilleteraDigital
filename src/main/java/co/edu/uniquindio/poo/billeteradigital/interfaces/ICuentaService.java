package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.List;

public interface ICuentaService {
    void agregarCuenta(Usuario usuario, Cuenta cuenta);
    void actualizarCuenta(Usuario usuario, Cuenta cuenta);
    Cuenta buscarCuentaPorId(Usuario usuario, String idCuenta);
    List<Cuenta> listarCuentasPorUsuario(Usuario usuario);
    void eliminarCuenta(Usuario usuario, Cuenta cuenta);
}
