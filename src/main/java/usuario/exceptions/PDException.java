package usuario.exceptions;

/**
 * Exceção para quando for tentado acessar informações de um perfil desativado.
 */
public class PDException extends Exception{

    public PDException(String userName){
        super("o perfil "+userName+" está desativado!!");
    }
}
