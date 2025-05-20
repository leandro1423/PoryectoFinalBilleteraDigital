package co.edu.uniquindio.poo.billeteradigital.model.builder;

import co.edu.uniquindio.poo.billeteradigital.model.*;

import java.util.List;

public class UsuarioBuilder {

    private final Usuario usuario;

    public UsuarioBuilder() {
        usuario = new Usuario();
    }

    public UsuarioBuilder conIdUsuario(String idUsuario) {
        usuario.setIdUsuario(idUsuario);
        return this;
    }

    public UsuarioBuilder conNombre(String nombre) {
        usuario.setNombre(nombre);
        return this;
    }

    public UsuarioBuilder conCorreo(String correo) {
        usuario.setCorreo(correo);
        return this;
    }

    public UsuarioBuilder conTelefono(String telefono) {
        usuario.setTelefono(telefono);
        return this;
    }

    public UsuarioBuilder conDireccion(String direccion) {
        usuario.setDireccion(direccion);
        return this;
    }

    public UsuarioBuilder conSaldo(double saldo) {
        usuario.setSaldo(saldo);
        return this;
    }

    public UsuarioBuilder conTipo(String tipo) {
        usuario.setTipo(tipo);
        return this;
    }

    public UsuarioBuilder conCuentas(List<Cuenta> cuentas) {
        usuario.setCuentas(cuentas);
        return this;
    }

    public UsuarioBuilder conTransacciones(List<Transaccion> transacciones) {
        usuario.setTransacciones(transacciones);
        return this;
    }

    public UsuarioBuilder conPresupuestos(List<Presupuesto> presupuestos) {
        usuario.setPresupuestos(presupuestos);
        return this;
    }

    public UsuarioBuilder conCategorias(List<Categoria> categorias) {
        usuario.setCategorias(categorias);
        return this;
    }

    public Usuario build() {
        return usuario;
    }
}

