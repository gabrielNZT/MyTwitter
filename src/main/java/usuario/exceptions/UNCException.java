package usuario.exceptions;

/**
 * Exceção para quando não for encontrado um perfil.
 */
public class UNCException extends Exception {

    public UNCException(String userName){
        super("O Usuario "+userName+" não foi encontrado");
    }
}
