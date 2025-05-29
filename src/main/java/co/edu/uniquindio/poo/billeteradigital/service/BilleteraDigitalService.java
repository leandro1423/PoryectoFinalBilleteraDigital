package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.interfaces.*;
import co.edu.uniquindio.poo.billeteradigital.model.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BilleteraDigitalService implements IBilleteraDigitalService {

    private final IUsuarioService usuarioService;
    private final ICuentaService cuentaService;
    private final ITransaccionService transaccionService;
    private final ICategoriaService categoriaService;
    private final IPresupuestoService presupuestoService;
    private final IEstadisticaService estadisticaService;

    public BilleteraDigitalService(IUsuarioService usuarioService, ICuentaService cuentaService,
                                   ITransaccionService transaccionService, ICategoriaService categoriaService,
                                   IPresupuestoService presupuestoService, IEstadisticaService estadisticaService) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
        this.transaccionService = transaccionService;
        this.categoriaService = categoriaService;
        this.presupuestoService = presupuestoService;
        this.estadisticaService = estadisticaService;
    }

    // Usuario
    @Override
    public void loguearUsuario(Usuario usuario) {
        usuarioService.loguearUsuario(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }

    @Override
    public Usuario BuscarUsuarioPorId(String idUsuario) {
        return usuarioService.BuscarUsuarioPorId(idUsuario);
    }

    @Override
    public Usuario ModificarUsuario( String idUsuario,String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        return usuarioService.ModificarUsuario(idUsuario, nuevoNombre, nuevoCorreo, nuevoTelefono);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(String idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @Override
    public IUsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    @Override
    public ICuentaService getCuentaService() {
        return null;
    }

    @Override
    public IPresupuestoService getPresupuestoService() {
        return null;
    }

    @Override
    public IEstadisticaService getEstadisticaService() {
        return null;
    }

    // Cuenta
    @Override
    public void agregarCuenta(Usuario usuario, Cuenta cuenta) {
        cuentaService.agregarCuenta(usuario, cuenta);
    }

    @Override
    public void actualizarCuenta(Usuario usuario, Cuenta cuenta) {
        cuentaService.actualizarCuenta(usuario, cuenta);
    }

    @Override
    public Cuenta buscarCuentaPorId(Usuario usuario, String idCuenta) {
        return cuentaService.buscarCuentaPorId(usuario, idCuenta);
    }

    @Override
    public List<Cuenta> listarCuentasPorUsuario(Usuario usuario) {
        return cuentaService.listarCuentasPorUsuario(usuario);
    }

    @Override
    public void eliminarCuenta(Usuario usuario, Cuenta cuenta) {
        cuentaService.eliminarCuenta(usuario, cuenta);
    }

    // Transaccion
    @Override
    public void crearTransaccion(Transaccion transaccion) {
        transaccionService.crearTransaccion(transaccion);
    }

    @Override
    public void eliminarTransaccion(Transaccion transaccion) {
        transaccionService.eliminarTransaccion(transaccion);
    }

    @Override
    public Transaccion buscarTransaccionPorId(Usuario usuario, String id) {
        return transaccionService.buscarTransaccionPorId(usuario, id);
    }

    @Override
    public List<Transaccion> listarTransacciones() {
        return transaccionService.listarTransacciones();
    }

    @Override
    public List<Transaccion> listarTransaccionesPorUsuario(Usuario usuario) {
        return transaccionService.listarTransaccionesPorUsuario(usuario);
    }

    @Override
    public List<Transaccion> listarTransaccionesPorCategoria(Usuario usuario, String categoria) {
        return transaccionService.listarTransaccionesPorCategoria(usuario, categoria);
    }

    @Override
    public List<Transaccion> listarTransaccionesPorFecha(Usuario usuario, LocalDate desde, LocalDate hasta) {
        return transaccionService.listarTransaccionesPorFecha(usuario, desde, hasta);
    }



    @Override
    public List<Transaccion> listarTransaccionesPorTipo(Usuario usuario, TipoTransaccion tipo) {
        return transaccionService.listarTransaccionesPorTipo(usuario, tipo);
    }

    @Override
    public boolean realizarDeposito(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        return transaccionService.realizarDeposito(usuario, cuenta, monto, descripcion);
    }

    @Override
    public boolean realizarRetiro(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        return transaccionService.realizarRetiro(usuario, cuenta, monto, descripcion);
    }

    @Override
    public boolean realizarTransferencia(Usuario usuarioOrigen, Cuenta cuentaOrigen, Usuario usuarioDestino, Cuenta cuentaDestino, double monto, String descripcion) {
        return transaccionService.realizarTransferencia(usuarioOrigen, cuentaOrigen, usuarioDestino, cuentaDestino, monto, descripcion);
    }

    @Override
    public ITransaccionService getTransaccionService() {
        return this.transaccionService;
    }

    // Categoria
    @Override
    public void crearCategoria(Usuario usuario, Categoria categoria) {
        categoriaService.crearCategoria(usuario, categoria);
    }

    @Override
    public Categoria buscarCategoriaPorId(Usuario usuario, String id) {
        return categoriaService.buscarCategoriaPorId(usuario, id);
    }

    @Override
    public List<Categoria> listarCategorias(Usuario usuario) {
        return categoriaService.listarCategorias(usuario);
    }

    @Override
    public void eliminarCategoria(Usuario usuario, String id) {
        categoriaService.eliminarCategoria(usuario, id);
    }

    @Override
    public void actualizarCategoria(Usuario usuario, Categoria categoria) {
        categoriaService.actualizarCategoria(usuario, categoria);
    }

    // Presupuesto
    @Override
    public void crearPresupuesto(Usuario usuario, Presupuesto presupuesto) {
        presupuestoService.crearPresupuesto(usuario, presupuesto);
    }

    @Override
    public void modificarPresupuesto(Usuario usuario, Presupuesto presupuesto) {
        presupuestoService.modificarPresupuesto(usuario, presupuesto);
    }

    @Override
    public void eliminarPresupuesto(Usuario usuario, String idPresupuesto) {
        presupuestoService.eliminarPresupuesto(usuario, idPresupuesto);
    }

    @Override
    public List<Presupuesto> listarPresupuestos(Usuario usuario) {
        return presupuestoService.listarPresupuestos(usuario);
    }

    @Override
    public Presupuesto buscarPorId(Usuario usuario, String id) {
        return presupuestoService.buscarPorId(usuario, id);
    }

    // Estadísticas
    @Override
    public List<Usuario> usuariosConMasTransacciones() {
        return estadisticaService.usuariosConMasTransacciones();
    }

    @Override
    public double saldoPromedioUsuarios() {
        return estadisticaService.saldoPromedioUsuarios();
    }

    @Override
    public Map<String, Integer> gastosMasComunes() {
        return estadisticaService.gastosMasComunes();
    }
}
