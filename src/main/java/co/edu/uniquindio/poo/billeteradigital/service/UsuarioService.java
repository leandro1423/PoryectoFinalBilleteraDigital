package co.edu.uniquindio.poo.billeteradigital.service;

import co.edu.uniquindio.poo.billeteradigital.interfaces.IUsuarioService;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService implements IUsuarioService {

    private final List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    public void loguearUsuario(Usuario usuario) {
        if (usuario.getIdUsuario().equals("idUsuario")) {
            System.out.println("Bienvenido " + usuario.getNombre());
        } else {
            System.out.println("Correo Incorrecto");
        }
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public Usuario BuscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario ModificarUsuario(String idUsuario, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                if (nuevoNombre != null && !nuevoNombre.isBlank()) {
                    usuario.setNombre(nuevoNombre);
                }
                if (nuevoCorreo != null && !nuevoCorreo.isBlank()) {
                    usuario.setCorreo(nuevoCorreo);
                }
                if (nuevoTelefono != null && !nuevoTelefono.isBlank()) {
                    usuario.setTelefono(nuevoTelefono);
                }
                return usuario; // Retorna el usuario modificado
            }
        }
        return null; // Si no se encontró el usuario
    }


    @Override
    public void actualizarUsuario(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getIdUsuario().equals(usuario.getIdUsuario())) {
                listaUsuarios.set(i, usuario);
                break;
            }
        }
    }

    @Override
    public void eliminarUsuario(String idUsuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getIdUsuario().equals(idUsuario)) {
                listaUsuarios.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return listaUsuarios;
    }
}
