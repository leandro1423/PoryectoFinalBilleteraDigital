package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteradigital.interfaces.ITransaccionService;
import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransaccionService implements ITransaccionService {

    private final List<Transaccion> listaTransacciones = new ArrayList<>();
    private final List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    public void crearTransaccion(Transaccion transaccion) {
        listaTransacciones.add(transaccion);
        Usuario usuario = obtenerUsuarioDeTransaccion(transaccion);
        if (usuario != null && usuario.getTransacciones() != null) {
            usuario.getTransacciones().add(transaccion);
        }
    }

    @Override
    public void eliminarTransaccion(Transaccion transaccion) {
        listaTransacciones.remove(transaccion);
        Usuario usuario = obtenerUsuarioDeTransaccion(transaccion);
        if (usuario != null && usuario.getTransacciones() != null) {
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
        return new ArrayList<>(listaTransacciones);
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
    public boolean realizarDeposito(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        if (monto <= 0 || usuario == null || cuenta == null || !usuario.getCuentas().contains(cuenta)) {
            return false;
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);

        Transaccion transaccion = new Transaccion(
                generarIdUnico(),
                LocalDate.now(),
                TipoTransaccion.DEPOSITO,
                monto,
                descripcion,
                cuenta,
                null,
                null
        );

        crearTransaccion(transaccion);
        return true;
    }

    @Override
    public boolean realizarRetiro(Usuario usuario, Cuenta cuenta, double monto, String descripcion) {
        if (monto <= 0 || usuario == null || cuenta == null || !usuario.getCuentas().contains(cuenta) || monto > cuenta.getSaldo()) {
            return false;
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);

        Transaccion transaccion = new Transaccion(
                generarIdUnico(),
                LocalDate.now(),
                TipoTransaccion.RETIRO,
                monto,
                descripcion,
                cuenta,
                null,
                null
        );

        crearTransaccion(transaccion);
        return true;
    }

    @Override
    public boolean realizarTransferencia(Usuario usuarioOrigen, Cuenta cuentaOrigen, Usuario usuarioDestino, Cuenta cuentaDestino, double monto, String descripcion) {
        if (monto <= 0
                || usuarioOrigen == null
                || cuentaOrigen == null
                || usuarioDestino == null
                || cuentaDestino == null
                || !usuarioOrigen.getCuentas().contains(cuentaOrigen)
                || !usuarioDestino.getCuentas().contains(cuentaDestino)
                || monto > cuentaOrigen.getSaldo()) {
            return false;
        }

        // Restar monto en cuenta origen
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        // Sumar monto en cuenta destino
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        Transaccion transaccionSalida = new Transaccion(
                generarIdUnico(),
                LocalDate.now(),
                TipoTransaccion.TRANSFERENCIA_ENVIADA,
                monto,
                descripcion,
                cuentaOrigen,
                cuentaDestino,
                null
        );

        Transaccion transaccionEntrada = new Transaccion(
                generarIdUnico(),
                LocalDate.now(),
                TipoTransaccion.TRANSFERENCIA_RECIBIDA,
                monto,
                descripcion,
                cuentaOrigen,
                cuentaDestino,
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

    private String generarIdUnico() {
        return UUID.randomUUID().toString();
    }


    @Override
    public List<Transaccion> buscarTransaccionesPorIdCuenta(String idCuenta) {
        if (idCuenta == null) {
            return new ArrayList<>();
        }

        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : listaTransacciones) {
            boolean coincideCuentaOrigen = transaccion.getCuentaOrigen() != null &&
                    idCuenta.equals(transaccion.getCuentaOrigen().getIdCuenta());
            boolean coincideCuentaDestino = transaccion.getCuentaDestino() != null &&
                    idCuenta.equals(transaccion.getCuentaDestino().getIdCuenta());

            if (coincideCuentaOrigen || coincideCuentaDestino) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    public Usuario obtenerUsuarioPorId(String idUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null; // Si no se encuentra
    }





}
