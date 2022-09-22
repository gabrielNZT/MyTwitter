package usuario;

public class Tweet {

    /**
    * Atributos da classe "Tweet"
    */
    private String usuario;
    private String mensagem;

    /**
     * Construtor da classe Tweet
     */
    public Tweet(String usuario, String mensagem){
        this.usuario = usuario;
        this.mensagem = mensagem;
    }

    /**
    * Altera o atributo "mensagem"
    */
    public void setMenssagem(String menssagem) {
        this.mensagem = mensagem;
    }

    /**
    * Retorna o atributo "mensagem"
    */
    public String getMensagem() {
        return mensagem;
    }

    /**
    * Altera o atributo "usuario
    */
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    /**
    * Retorna o atributo "usuario"
    */
    public String getUsuario() {
        return usuario;
    }
}
