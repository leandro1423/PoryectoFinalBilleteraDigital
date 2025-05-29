package co.edu.uniquindio.poo.billeteradigital.model;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoBanco;
import co.edu.uniquindio.poo.billeteradigital.enums.TipoCuenta;

import java.util.UUID;

public class Cuenta {

    private String idCuenta;
    private TipoBanco banco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Usuario usuario;
    private double saldo;

    public Cuenta() {

    }

    public Cuenta(TipoBanco banco,
                  String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuario, double saldo) {
        this.idCuenta = UUID.randomUUID().toString(); // Se genera el ID interno
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

    @Override
    public String toString() {
        return banco + " - " + tipoCuenta + " - " + numeroCuenta + " (Saldo: $" + saldo + ")";
    }
}
