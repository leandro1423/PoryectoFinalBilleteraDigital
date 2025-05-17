package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.model.Presupuesto;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.List;

public interface IPresupuestoService {

    void crearPresupuesto(Usuario usuario, Presupuesto presupuesto);
    void modificarPresupuesto(Usuario usuario, Presupuesto presupuesto);
    void eliminarPresupuesto(Usuario usuario, String idPresupuesto);
    List<Presupuesto> listarPresupuestos(Usuario usuario);
    Presupuesto buscarPorId(Usuario usuario, String id);
}

