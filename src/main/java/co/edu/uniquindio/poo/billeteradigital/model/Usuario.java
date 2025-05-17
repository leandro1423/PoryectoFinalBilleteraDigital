package co.edu.uniquindio.poo.billeteradigital.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private double saldo;

    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Presupuesto> Presupuestos = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();

    public Usuario() {
        // Constructor vacío para permitir crear objetos sin argumentos
    }


    public Usuario(String idUsuario, String nombre, String correo, String telefono,
                   String direccion, double saldo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.saldo = saldo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Presupuesto> getPresupuestos() {
        return Presupuestos;
    }

    public void setPresupuestos(List<Presupuesto> presupuestos) {
        Presupuestos = presupuestos;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
