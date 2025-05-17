package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.interfaces.ICategoriaService;
import co.edu.uniquindio.poo.billeteradigital.model.Categoria;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import java.util.List;

public class CategoriaService implements ICategoriaService {

    @Override
    public void crearCategoria(Usuario usuario, Categoria categoria) {
        usuario.getCategorias().add(categoria);
    }

    @Override
    public Categoria buscarCategoriaPorId(Usuario usuario, String id) {
        for (Categoria categoria : usuario.getCategorias()) {
            if (categoria.getIdCategoria().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public List<Categoria> listarCategorias(Usuario usuario) {
        return usuario.getCategorias();
    }

    @Override
    public void eliminarCategoria(Usuario usuario, String id) {
        List<Categoria> categorias = usuario.getCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria().equals(id)) {
                categorias.remove(i);
                break;
            }
        }
    }

    @Override
    public void actualizarCategoria(Usuario usuario, Categoria categoriaActualizada) {
        List<Categoria> categorias = usuario.getCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria().equals(categoriaActualizada.getIdCategoria())) {
                categorias.set(i, categoriaActualizada);
                break;
            }
        }
    }
}
