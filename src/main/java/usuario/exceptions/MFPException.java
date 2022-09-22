package usuario.exceptions;

/**
 * Exceção para quando a mensagem o tweet passar de 140 caracteres.
 */
public class MFPException extends Exception{

    public MFPException(){
        super("A mensagem deve conter até 140 caracteres!!");
    }
}
