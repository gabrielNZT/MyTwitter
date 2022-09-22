package usuario;

import usuario.entities.Perfil;

public class PessoaFisica extends Perfil {

    /**
    * Atributos da classe "PessoaFisica
    */
    private long cpf;

    /**
    * Construtor da classe "PessoaFisica"
    */
    public PessoaFisica(String usuario) {
        super(usuario);
    }

    /**
    * Altera o atributo "cpf"
    */
    public void setCpf(long cpf){
        this.cpf = cpf;
    }

    /**
    * retorna o atributo "cpf"
    */
    public long getCpf() {
        return cpf;
    }
}
