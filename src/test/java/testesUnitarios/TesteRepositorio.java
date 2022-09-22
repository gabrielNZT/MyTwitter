package testesUnitarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.MyTwitter;
import usuario.entities.Perfil;
import usuario.PessoaFisica;
import usuario.RepositorioUsuario;
import usuario.exceptions.*;

import java.util.ArrayList;

public class TesteRepositorio {

    ArrayList<Perfil> T;
    RepositorioUsuario run;
    MyTwitter myTwitter;
    PessoaFisica user1;
    PessoaFisica user2;
    PessoaFisica user3;
    PessoaFisica user4;

    @BeforeEach
    public void init(){
        run = new RepositorioUsuario();
        myTwitter = new MyTwitter();
        T = new ArrayList<>();
        user1 = new PessoaFisica("gabriel_nzt");
        user1.setCpf(123456);
        user2 = new PessoaFisica("@zequinha_123");
        user3 = new PessoaFisica("gabriel_nzt");
        user4 = new PessoaFisica("@chico");
    }

    /* Teste do exception */
    @Test
    public void TestCadastrar(){
        try {
            run.cadastrar(user1);
        } catch (UJCException e) {
            System.out.println(e.getMessage());
        }
        try {
            run.cadastrar(user2);
        } catch (UJCException e) {
            System.out.println(e.getMessage());
        }
        try {
            run.cadastrar(user3); // usuario já existente
        } catch (UJCException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void TesteCadastrar1() throws UJCException {

        run.cadastrar(user1);
        run.cadastrar(user2);

        T = run.getUsuarios();

        Assertions.assertEquals("@zequinha_123",T.get(1).getUsuario());
    }

    /* Teste do exception */
    @Test
    public void TesteAtulizar(){
    try{
        run.atualizar(user1);
    }catch (UNCException e){
        System.out.println(e.getMessage());
    }
    }

    @Test
    public void Atualizar() throws UJCException, PDException, SIException, PIException, PEExcpetion, UNCException {
        run.cadastrar(user1);
        ArrayList<Perfil> usuarios = new ArrayList<>();
               usuarios = run.getUsuarios();


        run.atualizar(user3);

        Assertions.assertEquals(user3, usuarios.get(0));
    }

    @Test
    public void buscarTeste() throws UJCException, UNCException {
        run.cadastrar(user1);
        run.cadastrar(user2);
        run.cadastrar(user4);


        Perfil perfil = run.buscar(user3.getUsuario());
        run.atualizar(user3);


        Assertions.assertEquals(user1, perfil);
    }
}
