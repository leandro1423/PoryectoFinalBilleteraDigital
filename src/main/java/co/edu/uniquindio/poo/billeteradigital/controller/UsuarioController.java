package co.edu.uniquindio.poo.billeteradigital.controller;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.factory.ModelFactoryController;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Presupuesto;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import co.edu.uniquindio.poo.billeteradigital.service.CuentaService;
import co.edu.uniquindio.poo.billeteradigital.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;

public class UsuarioController {

    private final ModelFactoryController modelFactory;
    private final UsuarioService usuarioService = new UsuarioService();
    private final CuentaService cuentaService = new CuentaService();

    public UsuarioController() {
        this.modelFactory = ModelFactoryController.getInstance();
    }

    // Gestión de Usuario
    public void registrarUsuario(Usuario usuario) {
        modelFactory.getBilleteraService().registrarUsuario(usuario);
    }
    public Usuario BuscarUsuarioPorId(String idUsuario) {
        return modelFactory.getBilleteraService().BuscarUsuarioPorId(idUsuario);
    }

    public Usuario modificarUsuario(String idUsuario, String nombre, String correo, String telefono) {
        return modelFactory.getBilleteraService().ModificarUsuario(idUsuario, nombre, correo, telefono);
    }

    public boolean loguearUsuario(Usuario usuario) {
        Usuario encontrado = modelFactory.getBilleteraService().BuscarUsuarioPorId(usuario.getIdUsuario());
        return encontrado != null && encontrado.getNombre().equalsIgnoreCase(usuario.getNombre());
    }


    // Gestion de Transacciones
    public boolean realizarDeposito(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        return modelFactory.getBilleteraService().realizarDeposito(usuario, cuenta, monto, descripcion);
    }

    public boolean realizarRetiro(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        return modelFactory.getBilleteraService().realizarRetiro(usuario, cuenta, monto, descripcion);
    }

    public boolean realizarTransferencia(Usuario usuarioOrigen, Cuenta cuentaOrigen, Usuario usuarioDestino, Cuenta cuentaDestino, double monto, String descripcion) {
        return modelFactory.getBilleteraService().realizarTransferencia(usuarioOrigen, cuentaOrigen, usuarioDestino, cuentaDestino, monto, descripcion);
    }


    // Gestión de Presupuestos
    public void crearPresupuesto(Usuario usuario, Presupuesto presupuesto) {
        modelFactory.getBilleteraService().crearPresupuesto(usuario, presupuesto);
    }

    public void modificarPresupuesto(Usuario usuario, Presupuesto presupuesto) {
        modelFactory.getBilleteraService().modificarPresupuesto(usuario, presupuesto);
    }

    public void listarPresupuestos(Usuario usuario) {
        modelFactory.getBilleteraService().listarPresupuestos(usuario);
    }

    public void eliminarPresupuesto(Usuario usuario, String idPresupuesto) {
        modelFactory.getBilleteraService().eliminarPresupuesto(usuario, idPresupuesto);
    }


    // Gestion de Transacciones
    public List<Transaccion> consultarTransaccionesPorTipo(Usuario usuario, TipoTransaccion tipo) {
        return modelFactory.getBilleteraService().listarTransaccionesPorTipo(usuario, tipo);
    }

    public List<Transaccion> consultarTransaccionesPorCategoria(Usuario usuario, String categoria) {
        return modelFactory.getBilleteraService().listarTransaccionesPorCategoria(usuario, categoria);
    }

    public List<Transaccion> listarTransaccionesPorUsuario(Usuario usuario) {
        return modelFactory.getBilleteraService().listarTransaccionesPorUsuario(usuario);
    }

    public List<Transaccion> consultarTransaccionesPorFecha(Usuario usuario, LocalDate desde, LocalDate hasta) {
        return modelFactory.getBilleteraService().listarTransaccionesPorFecha(usuario, desde, hasta);
    }

    public List<Transaccion> consultarTransacciones(Usuario usuario) {
        return modelFactory.getBilleteraService().listarTransaccionesPorUsuario(usuario);
    }


    // Gestion de Cuentas
    public void agregarCuenta(Usuario usuario, Cuenta cuenta) {
        modelFactory.getBilleteraService().agregarCuenta(usuario, cuenta);
    }

    public void actualizarCuenta(Usuario usuario, Cuenta cuenta) {
        modelFactory.getBilleteraService().actualizarCuenta(usuario, cuenta);
    }

    public void eliminarCuenta(Usuario usuario, Cuenta cuenta) {
        modelFactory.getBilleteraService().eliminarCuenta(usuario, cuenta);
    }

    public List<Cuenta> listarCuentas(Usuario usuario) {
        return modelFactory.getBilleteraService().listarCuentasPorUsuario(usuario);
    }

    public Cuenta buscarCuentaPorId(Usuario usuario, String idCuenta) {
        return modelFactory.getBilleteraService().buscarCuentaPorId(usuario, idCuenta);
    }

    //Gestionar Saldo
    public double consultarSaldo(Usuario usuario) {
        return usuario.getSaldo();
    }

    public void registrarUsuarioConCuenta(Usuario usuario, Cuenta cuenta) {
        cuentaService.agregarCuenta(usuario, cuenta);
        usuarioService.registrarUsuario(usuario);
    }

}
