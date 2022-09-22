package usuario.interfaces;

import usuario.exceptions.*;
import usuario.Tweet;
import usuario.entities.Perfil;

import java.util.ArrayList;

public interface ITwitter {

    /**
    * A documentação para os métodos desta interface encontram-se na classe "MyTwitter"
     */

    void criarPerfil(Perfil usuario) throws PEExcpetion;
    void cancelarPerfil(String usuario) throws PIException, PDException;
    void tweetar(String usuario, String mensagem) throws PIException, MFPException;
    ArrayList<Tweet> timeline(String usuario) throws PIException, PDException;
    ArrayList<Tweet> tweets(String usuario) throws PIException, PDException;
    void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;
    int numeroSeguidores(String usuario) throws PIException, PDException;
    ArrayList<Perfil> seguidores(String usuario) throws PIException, PDException;
    ArrayList<Perfil> seguidos(String usuario) throws PIException, PDException;
}
