package usuario;

import usuario.entities.Perfil;

public class PessoaJuridica extends Perfil {

    /**
    * Atributos da classe "PessoaJuridica"
    */
    private long cnpj;

    /**
    * Construtor da classe "PessoaJuridica"
    */
    public PessoaJuridica(String usuario) {
        super(usuario);

    }

    /**
    * Altera o atributo "cnpj"
    */
    public void setCnpj(long cnpj){
        this.cnpj = cnpj;
    }

    /**
    * retorna o atributo "cnpj"
    */
    public long getCnpj() {
        return cnpj;
    }
}
