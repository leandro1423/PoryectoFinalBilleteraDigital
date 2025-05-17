package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.interfaces.ITransaccionService;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionService implements ITransaccionService {

    private final List<Transaccion> listaTransacciones = new ArrayList<>();

    @Override
    public void crearTransaccion(Transaccion transaccion) {
        listaTransacciones.add(transaccion);
        Usuario usuario = obtenerUsuarioDeTransaccion(transaccion);
        if (usuario != null) {
            usuario.getTransacciones().add(transaccion);
        }
    }

    @Override
    public void eliminarTransaccion(Transaccion transaccion) {
        listaTransacciones.remove(transaccion);
        Usuario usuario = obtenerUsuarioDeTransaccion(transaccion);
        if (usuario != null) {
            usuario.getTransacciones().remove(transaccion);
        }
    }

    @Override
    public Transaccion buscarTransaccionPorId(Usuario usuario, String id) {
        if (usuario == null || usuario.getTransacciones() == null) {
            return null;
        }
        for (Transaccion transaccion : usuario.getTransacciones()) {
            if (transaccion.getIdTransaccion().equals(id)) {
                return transaccion;
            }
        }
        return null;
    }

    @Override
    public List<Transaccion> listarTransacciones() {
        return List.of(); // O implementa global si deseas
    }

    @Override
    public List<Transaccion> listarTransaccionesPorUsuario(Usuario usuario) {
        return usuario.getTransacciones();
    }

    @Override
    public List<Transaccion> listarTransaccionesPorCategoria(Usuario usuario, String categoria) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : usuario.getTransacciones()) {
            if (t.getCategoria() != null &&
                    t.getCategoria().getNombreCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    @Override
    public List<Transaccion> listarTransaccionesPorFecha(Usuario usuario, LocalDate desde, LocalDate hasta) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : usuario.getTransacciones()) {
            LocalDate fecha = t.getFechaTransaccion();
            if ((fecha.isEqual(desde) || fecha.isAfter(desde)) &&
                    (fecha.isEqual(hasta) || fecha.isBefore(hasta))) {
                resultado.add(t);
            }
        }
        return resultado;
    }


    @Override
    public List<Transaccion> listarTransaccionesPorTipo(Usuario usuario, TipoTransaccion tipo) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : usuario.getTransacciones()) {
            if (t.getTipoTransaccion() == tipo) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    @Override
    public boolean realizarDeposito(Usuario usuario, double monto, String descripcion) {
        if (monto <= 0) return false;

        usuario.setSaldo(usuario.getSaldo() + monto);

        Transaccion transaccion = new Transaccion(
                null,
                LocalDate.now(),
                TipoTransaccion.DEPOSITO,
                monto,
                descripcion,
                null,
                null,
                null
        );

        crearTransaccion(transaccion);
        return true;
    }

    @Override
    public boolean realizarRetiro(Usuario usuario, double monto, String descripcion) {
        if (monto <= 0 || monto > usuario.getSaldo()) return false;

        usuario.setSaldo(usuario.getSaldo() - monto);

        Transaccion transaccion = new Transaccion(
                null,
                LocalDate.now(),
                TipoTransaccion.RETIRO,
                monto,
                descripcion,
                null,
                null,
                null
        );

        crearTransaccion(transaccion);
        return true;
    }

    @Override
    public boolean realizarTransferencia(Usuario usuarioOrigen, Usuario usuarioDestino, double monto, String descripcion) {
        if (monto <= 0 || monto > usuarioOrigen.getSaldo()) return false;

        usuarioOrigen.setSaldo(usuarioOrigen.getSaldo() - monto);
        usuarioDestino.setSaldo(usuarioDestino.getSaldo() + monto);

        Transaccion transaccionSalida = new Transaccion(
                null,
                LocalDate.now(),
                TipoTransaccion.TRANSFERENCIA_ENVIADA,
                monto,
                descripcion,
                null,
                null,
                null
        );

        Transaccion transaccionEntrada = new Transaccion(
                null,
                LocalDate.now(),
                TipoTransaccion.TRANSFERENCIA_RECIBIDA,
                monto,
                descripcion,
                null,
                null,
                null
        );

        crearTransaccion(transaccionSalida);
        crearTransaccion(transaccionEntrada);

        return true;
    }

    private Usuario obtenerUsuarioDeTransaccion(Transaccion transaccion) {
        if (transaccion.getCuentaOrigen() != null) {
            return transaccion.getCuentaOrigen().getUsuario();
        }
        if (transaccion.getCuentaDestino() != null) {
            return transaccion.getCuentaDestino().getUsuario();
        }
        return null;
    }
}
