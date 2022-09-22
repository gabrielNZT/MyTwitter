package usuario.interfaces;

import usuario.exceptions.UJCException;
import usuario.exceptions.UNCException;
import usuario.entities.Perfil;

public interface IRepositorioUsuario {

    /**
    * A documentação para os métodos desta interface encontram-se na classe "RepositorioUsuario"
    */

    void cadastrar(Perfil usuario) throws UJCException;
    Perfil buscar(String usuario);
    void atualizar(Perfil usuario) throws UNCException;
}
