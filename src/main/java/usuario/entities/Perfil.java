package usuario.entities;

import usuario.Tweet;
import java.util.ArrayList;

public abstract class Perfil {

    /**
    * Atributos da classe "Perfil"
    */
    private String usuario;
    private ArrayList<Perfil> seguidos;
    private ArrayList<Perfil> seguidores;
    private ArrayList<Tweet> timeline;
    private boolean ativo;


    /**
    * Construtor da classe "Perfil"
    */
    public Perfil(String usuario){
       this.usuario = usuario;
       seguidos = new ArrayList<>();
       seguidores = new ArrayList<>();
       timeline = new ArrayList<>();
       ativo = true;
    }

    /**
    * Adiciona o objeto "usuario" na ArrayList "seguidos" de tipo "Perfil"
    */
    public void addSeguido(Perfil usuario){ seguidos.add(usuario); }

    /**
    * Adiciona o objeto "usuario" na ArrayList "seguidores" de tipo "Perfil"
    */
    public void addSeguidor(Perfil usuario){ seguidores.add(usuario); }

    /**
     * Adiciona o objeto "tweet" na ArrayList "timeline"
     */
    public void addTweet(Tweet tweet){ timeline.add(tweet); }

    /**
    * Altera o atributo "usuario" dessa classe
    */
    public void setUsuario(String usuario){ this.usuario = usuario; }

    /**
    * Retorna o atributo usuario dessa clase
    */
    public String getUsuario() { return usuario; }

    /**
    * Retorna a ArrayList "seguidos" do tipo "Perfil"
    */
    public ArrayList<Perfil> getSeguidos() { return seguidos; }

    /**
    * Retorna a ArrayList "seguidores" do tipo "Perfil"
    */
    public ArrayList<Perfil> getSeguidores(){ return seguidores; }

    /**
    * Retorna a ArrayList "timeline" do tipo "Tweet"
    */
    public ArrayList<Tweet> getTimeline() { return timeline; }

    /**
    * Altera o atributo "ativo" dessa classe
    */
    public void setAtivo(Boolean ativo){ this.ativo = ativo; }

    /**
    * Retorna o atributo "ativo" dessa clase
    */
    public boolean isAtivo() { return ativo; }
}
