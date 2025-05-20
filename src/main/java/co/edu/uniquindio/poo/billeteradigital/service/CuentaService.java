package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.interfaces.ICuentaService;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.List;

public class CuentaService implements ICuentaService {

    @Override
    public void agregarCuenta(Usuario usuario, Cuenta cuenta) {
        usuario.getCuentas().add(cuenta);
    }

    @Override
    public void actualizarCuenta(Usuario usuario, Cuenta cuenta) {
        List<Cuenta> cuentas = usuario.getCuentas();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCuenta().equals(cuenta.getIdCuenta())) {
                cuentas.set(i, cuenta);
                break;
            }
        }
    }

    @Override
    public Cuenta buscarCuentaPorId(Usuario usuario, String idCuenta) {
        for (Cuenta cuenta : usuario.getCuentas()) {
            if (cuenta.getIdCuenta().equals(idCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public List<Cuenta> listarCuentasPorUsuario(Usuario usuario) {
        return usuario.getCuentas();
    }

    @Override
    public void eliminarCuenta(Usuario usuario, Cuenta cuenta) {
        List<Cuenta> cuentas = usuario.getCuentas();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCuenta().equals(cuenta.getIdCuenta())) {
                cuentas.remove(i);
                break;
            }
        }
    }
}