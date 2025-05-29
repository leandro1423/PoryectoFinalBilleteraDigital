package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface ITransaccionService {

    void crearTransaccion(Transaccion transaccion);
    void eliminarTransaccion(Transaccion transaccion);
    Transaccion buscarTransaccionPorId(Usuario usuario, String id);
    Usuario obtenerUsuarioPorId(String idUsuario);


    List<Transaccion> listarTransacciones();
    List<Transaccion> listarTransaccionesPorUsuario(Usuario usuario);


    List<Transaccion> listarTransaccionesPorCategoria(Usuario usuario, String categoria);
    List<Transaccion> listarTransaccionesPorFecha(Usuario usuario, LocalDate desde, LocalDate hasta);
    List<Transaccion> listarTransaccionesPorTipo(Usuario usuario, TipoTransaccion tipo);
    List<Transaccion> buscarTransaccionesPorIdCuenta(String idCuenta);


    boolean realizarDeposito(Usuario usuario, Cuenta cuenta, double monto, String descripcion);
    boolean realizarRetiro(Usuario usuario, Cuenta cuenta, double monto, String descripcion);
    boolean realizarTransferencia(Usuario usuarioOrigen, Cuenta cuentaOrigen, Usuario usuarioDestino, Cuenta cuentaDestino, double monto, String descripcion);
}
