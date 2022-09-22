package usuario;

import usuario.entities.Perfil;
import usuario.exceptions.UJCException;
import usuario.exceptions.UNCException;
import usuario.interfaces.IRepositorioUsuario;

import java.util.ArrayList;

/**
 * Esta classe implementa a interface "IRepositorioUsuario"
 */

public class RepositorioUsuario implements IRepositorioUsuario {

    /**
     *Atributo da classe "RepositorioUsuario"
     */
    private ArrayList<Perfil> usuarios;

    public RepositorioUsuario() {
        usuarios = new ArrayList<>();
    }


    /**
    * Método para retornar a "ArrayList<Perfil> usuario"
    */
    public ArrayList<Perfil> getUsuarios() { return usuarios; }


    /**
    * Esse méodo tem como parâmetro um "usuario" do tipo "Perfil" que será adicionado na ArrayList "usuarios", caso esse usuario
    * já esteja em uso será levantada uma "UJCException"
    */
    @Override
    public void cadastrar(Perfil usuario) throws UJCException {
        if(usuarios.stream().anyMatch(user -> user.getUsuario().equals(usuario.getUsuario()))){
            throw new UJCException(usuario.getUsuario());
        }else{
            usuarios.add(usuario);
        }
    }

    /**
    * Esse método é responsável por procurar pelo parâmetro String usuario na ArrayList "usuarios", caso seja
    * encontrado deve-se retornar o Perfil detentor dessa String usuario e caso não seja encontrado
    * deve-se retornar null
    */
    @Override
    public Perfil buscar(String usuario) {
        for(Perfil perfil : usuarios){
          if(perfil.getUsuario().equals(usuario)){
              return perfil;
          }
        }
        return null;
    }

    /**
    * Esse método atualiza um perfil com base nas novas informações de perfil passadas como parâmetro, ou seja,
    * ele vai identificar o perfil que será atualizado por meio do método buscar e irá substituir na ArrayList "usuarios"
    * o perfil anterior pelo perfil passado como argumento, caso não seja encontrado
    * o perfil a ser atualizado, será levantada uma UNCException
    */
    @Override
    public void atualizar(Perfil usuario) throws UNCException {
        int index;

       if(buscar(usuario.getUsuario()) == null) {
           throw new UNCException(usuario.getUsuario());
       }else{
           index = usuarios.indexOf(buscar(usuario.getUsuario()));
           usuarios.set(index, usuario);
       }

    }
}
