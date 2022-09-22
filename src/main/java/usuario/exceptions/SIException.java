package usuario.exceptions;

/**
 * Exceção para quando for tentado seguir a si mesmo.
 */
public class SIException extends Exception{

    public SIException(String userName){
        super("O Perfil "+userName+" não pode seguir ele mesmo!!");
    }
}
