package usuario.exceptions;

/**
 * Exceção para quando for tentado criar um usurio de perfil que já está em uso.
 */
public class PEExcpetion extends Exception {

    public PEExcpetion(String userName){
        super("Já existe um perfil com nome: "+userName+"!!");
    }
}
