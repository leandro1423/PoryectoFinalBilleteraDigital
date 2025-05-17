package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.interfaces.IPresupuestoService;
import co.edu.uniquindio.poo.billeteradigital.model.Presupuesto;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PresupuestoService implements IPresupuestoService {

    @Override
    public void crearPresupuesto(Usuario usuario, Presupuesto presupuesto) {
        usuario.getPresupuestos().add(presupuesto);
    }

    @Override
    public void modificarPresupuesto(Usuario usuario, Presupuesto presupuestoModificado) {
        List<Presupuesto> lista = usuario.getPresupuestos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPresupuesto().equals(presupuestoModificado.getIdPresupuesto())) {
                lista.set(i, presupuestoModificado);
                break;
            }
        }
    }

    @Override
    public void eliminarPresupuesto(Usuario usuario, String idPresupuesto) {
        List<Presupuesto> lista = usuario.getPresupuestos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPresupuesto().equals(idPresupuesto)) {
                lista.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Presupuesto> listarPresupuestos(Usuario usuario) {
        return usuario.getPresupuestos();
    }

    @Override
    public Presupuesto buscarPorId(Usuario usuario, String id) {
        for (Presupuesto p : usuario.getPresupuestos()) {
            if (p.getIdPresupuesto().equals(id)) {
                return p;
            }
        }
        return null;
    }

}
