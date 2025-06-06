package co.edu.uniquindio.poo.billeteradigital.interfaces;

import co.edu.uniquindio.poo.billeteradigital.model.Cuenta;
import co.edu.uniquindio.poo.billeteradigital.model.Usuario;
import java.util.List;

public interface IUsuarioService {

    Usuario loguearUsuario(Usuario usuario);
    void registrarUsuario(Usuario usuario);
    Usuario BuscarUsuarioPorId(String idUsuario);
    Usuario ModificarUsuario(String idUsuario,String nuevoNombre,String nuevoCorreo,String nuevoTelefono);
    void actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(String usuario);
    List<Usuario> listarUsuarios();
    void actualizarSaldo(Usuario usuario, Cuenta cuenta, double nuevoSaldo);
}
