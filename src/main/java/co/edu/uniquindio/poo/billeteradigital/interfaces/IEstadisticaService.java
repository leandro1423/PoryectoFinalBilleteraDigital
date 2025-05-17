package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import java.util.List;
import java.util.Map;

public interface IEstadisticaService {

    List<Usuario> usuariosConMasTransacciones();
    double saldoPromedioUsuarios();
    Map<String, Integer> gastosMasComunes();
}
