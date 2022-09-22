package usuario.exceptions;

/**
 * Exceção para quando não for encontrado um perfil.
 */
public class PIException extends Exception{

    public PIException(String userName){
        super("não existe um perfil com nome: "+userName+"!!");
    }
}
