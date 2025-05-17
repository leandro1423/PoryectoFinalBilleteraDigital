package co.edu.uniquindio.poo.billeteradigital.model;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoBanco;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoCuenta;

public class Cuenta {

    private String idCuenta;
    private TipoBanco banco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Usuario usuario;
    private double saldo;  // <-- agregado

    public Cuenta() {

    }

    public Cuenta(String idCuenta, TipoBanco banco,
                  String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuario, double saldo) {
        this.idCuenta = idCuenta;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.usuario = usuario;
        this.saldo = saldo;
    }


    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public TipoBanco getBanco() {
        return banco;
    }

    public void setBanco(TipoBanco banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
