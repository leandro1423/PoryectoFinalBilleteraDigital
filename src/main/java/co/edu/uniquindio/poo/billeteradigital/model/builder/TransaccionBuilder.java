package co.edu.uniquindio.poo.billeteradigital.model.builder;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Categoria;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;

import java.time.LocalDate;

public class TransaccionBuilder {

    private final Transaccion transaccion;

    public TransaccionBuilder() {
        transaccion = new Transaccion();
    }

    public TransaccionBuilder idTransaccion(String idTransaccion) {
        transaccion.setIdTransaccion(idTransaccion);
        return this;
    }

    public TransaccionBuilder fechaTransaccion(LocalDate fechaTransaccion) {
        transaccion.setFechaTransaccion(fechaTransaccion);
        return this;
    }

    public TransaccionBuilder tipoTransaccion(TipoTransaccion tipoTransaccion) {
        transaccion.setTipoTransaccion(tipoTransaccion);
        return this;
    }

    public TransaccionBuilder monto(double monto) {
        transaccion.setMonto(monto);
        return this;
    }

    public TransaccionBuilder descripcion(String descripcion) {
        transaccion.setDescripcion(descripcion);
        return this;
    }

    public TransaccionBuilder cuentaOrigen(Cuenta cuentaOrigen) {
        transaccion.setCuentaOrigen(cuentaOrigen);
        return this;
    }

    public TransaccionBuilder cuentaDestino(Cuenta cuentaDestino) {
        transaccion.setCuentaDestino(cuentaDestino);
        return this;
    }

    public TransaccionBuilder categoria(Categoria categoria) {
        transaccion.setCategoria(categoria);
        return this;
    }

    public Transaccion build() {
        return transaccion;
    }
}
