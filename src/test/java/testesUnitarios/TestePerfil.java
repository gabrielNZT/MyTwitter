package testesUnitarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import usuario.PessoaFisica;
import usuario.PessoaJuridica;
import usuario.Tweet;
import usuario.entities.Perfil;

import java.util.ArrayList;

public class TestePerfil {
    ArrayList<Tweet> timeline;
    ArrayList <Perfil> perfils;
    PessoaFisica pf;
    PessoaFisica perfil1;
    PessoaFisica perfil2;
    PessoaJuridica perfil3;
    Tweet tweet;
    Tweet tweet1;

    @BeforeEach
    public void init(){
        perfils = new ArrayList<>();
        timeline = new ArrayList<>();
        pf = new PessoaFisica("@gabriel");
        perfil1 = new PessoaFisica("@zequinha");
        perfil2 = new PessoaFisica("@mariazinha");
        perfil3 = new PessoaJuridica("@Carros_novos");

        tweet = new Tweet("@gabriel","hj o dia foi pesado");
        tweet1 = new Tweet("@gabriel","trabalhei muito!!");

    }

    @Test
    public void testeTimeLine(){
        Assertions.assertTrue(pf.isAtivo());

        pf.addTweet(tweet);
        pf.addTweet(tweet1);
        timeline = pf.getTimeline();
        Assertions.assertEquals("trabalhei muito!!",timeline.get(1).getMensagem());
        Assertions.assertEquals("@gabriel", timeline.get(1).getUsuario());
        Assertions.assertEquals("hj o dia foi pesado",timeline.get(0).getMensagem());
        Assertions.assertEquals("@gabriel", timeline.get(0).getUsuario());
    }

    @Test
    public void testeSeguires(){
        pf.addSeguidor(perfil1);
        pf.addSeguidor(perfil2);
        pf.addSeguidor(perfil3);

        perfils = pf.getSeguidores();
        Assertions.assertEquals("@mariazinha", perfils.get(1).getUsuario());
        Assertions.assertEquals("@Carros_novos", perfils.get(2).getUsuario());
    }
}
