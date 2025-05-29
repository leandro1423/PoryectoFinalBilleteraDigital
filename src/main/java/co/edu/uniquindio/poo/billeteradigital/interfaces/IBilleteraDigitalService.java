package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IBilleteraDigitalService {

    //Usuario
    void loguearUsuario(Usuario usuario);
    void registrarUsuario(Usuario usuario);
    Usuario BuscarUsuarioPorId(String idUsuario);
    Usuario ModificarUsuario(String idUsuario, String nombre, String correo, String telefono);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(String usuario);
    List<Usuario> listarUsuarios();




    //Cuenta
    void agregarCuenta(Usuario usuario, Cuenta cuenta);
    void actualizarCuenta(Usuario usuario, Cuenta cuenta);
    Cuenta buscarCuentaPorId(Usuario usuario, String idCuenta);
    List<Cuenta> listarCuentasPorUsuario(Usuario usuario);
    void eliminarCuenta(Usuario usuario, Cuenta cuenta);


    //Transaccion
    void crearTransaccion(Transaccion transaccion);
    void eliminarTransaccion(Transaccion transaccion);
    Transaccion buscarTransaccionPorId(Usuario usuario, String id);
    List<Transaccion> listarTransacciones();
    List<Transaccion> listarTransaccionesPorUsuario(Usuario usuario);
    List<Transaccion> listarTransaccionesPorCategoria(Usuario usuario, String categoria);
    List<Transaccion> listarTransaccionesPorFecha(Usuario usuario, LocalDate desde, LocalDate hasta);
    List<Transaccion> listarTransaccionesPorTipo(Usuario usuario, TipoTransaccion tipo);
    boolean realizarDeposito(Usuario usuario, Cuenta cuenta, double monto, String descripcion);
    boolean realizarRetiro(Usuario usuario, Cuenta cuenta, double monto, String descripcion);
    boolean realizarTransferencia(Usuario usuarioOrigen, Cuenta cuentaOrigen, Usuario usuarioDestino, Cuenta cuentaDestino, double monto, String descripcion);


    ITransaccionService getTransaccionService();

    //Categoria
    void crearCategoria(Usuario usuario, Categoria categoria);
    Categoria buscarCategoriaPorId(Usuario usuario, String id);
    List<Categoria> listarCategorias(Usuario usuario);
    void eliminarCategoria(Usuario usuario, String id);
    void actualizarCategoria(Usuario usuario, Categoria categoria);


    //Presupuesto
    void crearPresupuesto(Usuario usuario, Presupuesto presupuesto);
    void modificarPresupuesto(Usuario usuario, Presupuesto presupuesto);
    void eliminarPresupuesto(Usuario usuario, String idPresupuesto);
    List<Presupuesto> listarPresupuestos(Usuario usuario);
    Presupuesto buscarPorId(Usuario usuario, String id);


    //Estadisticas
    List<Usuario> usuariosConMasTransacciones();
    double saldoPromedioUsuarios();
    Map<String, Integer> gastosMasComunes();

    IUsuarioService getUsuarioService();
    ICuentaService getCuentaService();
    IPresupuestoService getPresupuestoService();
    IEstadisticaService getEstadisticaService();


}
