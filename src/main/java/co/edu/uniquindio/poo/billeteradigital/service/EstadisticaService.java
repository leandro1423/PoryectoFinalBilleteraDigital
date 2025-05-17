package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.interfaces.IEstadisticaService;
import co.edu.uniquindio.poo.billeteradigital.model.Transaccion;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.*;

public class EstadisticaService implements IEstadisticaService {

    private final List<Usuario> usuarios;

    public EstadisticaService() {
        this.usuarios = new ArrayList<>();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios.clear();
        this.usuarios.addAll(usuarios);
    }

    @Override
    public List<Usuario> usuariosConMasTransacciones() {
        List<Usuario> listaOrdenada = new ArrayList<>(usuarios);

        for (int i = 0; i < listaOrdenada.size() - 1; i++) {
            for (int j = 0; j < listaOrdenada.size() - 1 - i; j++) {
                if (listaOrdenada.get(j).getTransacciones().size() < listaOrdenada.get(j + 1).getTransacciones().size()) {
                    Usuario temp = listaOrdenada.get(j);
                    listaOrdenada.set(j, listaOrdenada.get(j + 1));
                    listaOrdenada.set(j + 1, temp);
                }
            }
        }

        return listaOrdenada;
    }

    @Override
    public double saldoPromedioUsuarios() {
        if (usuarios.isEmpty()) return 0;

        double suma = 0;
        for (Usuario usuario : usuarios) {
            suma += usuario.getSaldo();
        }

        return suma / usuarios.size();
    }

    @Override
    public Map<String, Integer> gastosMasComunes() {
        List<Map.Entry<String, Integer>> listaEntradas = getEntries();

        for (int i = 0; i < listaEntradas.size() - 1; i++) {
            for (int j = 0; j < listaEntradas.size() - 1 - i; j++) {
                if (listaEntradas.get(j).getValue() < listaEntradas.get(j + 1).getValue()) {
                    Map.Entry<String, Integer> temp = listaEntradas.get(j);
                    listaEntradas.set(j, listaEntradas.get(j + 1));
                    listaEntradas.set(j + 1, temp);
                }
            }
        }

        Map<String, Integer> resultadoOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : listaEntradas) {
            resultadoOrdenado.put(entry.getKey(), entry.getValue());
        }

        return resultadoOrdenado;
    }

    private List<Map.Entry<String, Integer>> getEntries() {
        Map<String, Integer> categoriaConteo = new HashMap<>();

        for (Usuario usuario : usuarios) {
            for (Transaccion transaccion : usuario.getTransacciones()) {
                if (transaccion.getCategoria() != null && transaccion.getCategoria().getNombreCategoria() != null) {
                    String nombre = transaccion.getCategoria().getNombreCategoria();
                    categoriaConteo.put(nombre, categoriaConteo.getOrDefault(nombre, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> listaEntradas = new ArrayList<>(categoriaConteo.entrySet());
        listaEntradas.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return listaEntradas;
    }
}
