package usuario.exceptions;

/**
 * Exceção para quando for tentado criar um usurio de perfil que já está em uso.
 */
public class UJCException extends Exception {

    public UJCException(String userName) {
        super("O usuário "+ userName +" já está em uso");
    }
}
