package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.model.Categoria;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.List;

public interface ICategoriaService {
    void crearCategoria(Usuario usuario, Categoria categoria);
    Categoria buscarCategoriaPorId(Usuario usuario, String id);
    List<Categoria> listarCategorias(Usuario usuario);
    void eliminarCategoria(Usuario usuario, String id);
    void actualizarCategoria(Usuario usuario, Categoria categoria);
}
