package testesUnitarios;

import usuario.MyTwitter;
import org.junit.jupiter.api.*;
import usuario.PessoaFisica;
import usuario.PessoaJuridica;
import usuario.Tweet;
import usuario.entities.Perfil;
import usuario.exceptions.*;

import java.util.ArrayList;

public class TesteMyTwitter {


    ArrayList<Perfil> seguidores;
    ArrayList<Perfil> seguidos;
    ArrayList<Tweet> timeline;
    MyTwitter myTwitter;
    PessoaFisica p1;
    PessoaFisica p2;
    PessoaJuridica p3;
    String mensagem = "6546564897897965465465465465465465465465465465465465465465464645646546546546546546546546546546546546546546546546546546465465465465415645646456465465456465";

    @BeforeEach
    public void init(){
        timeline = new ArrayList<>();
        myTwitter = new MyTwitter();
        seguidores = new ArrayList<>();
        seguidos = new ArrayList<>();
        p1 = new PessoaFisica("@gabriel_nzt");
        p2 = new PessoaFisica("@zequinha123");
        p3 = new PessoaJuridica("@carrosTunados");
    }

    @Test
    @DisplayName("Teste do metodo criar perfil (sem excpetion)")
    public void Test1(){
        try{
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
        }catch (PEExcpetion e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do metodo criar perfil (com excpetion (PEExcpetion))")
    public void test2(){
        try{
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p1); // tendando criar conta já existente
        }catch (PEExcpetion e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do numero de seguidores (sem exception)")
    public void test3() throws PDException, PIException {
        try{
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        }catch (PEExcpetion e){
            System.out.println(e.getMessage());
        }

        try{
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
        }catch(PIException | PDException | SIException e){
            System.out.println(e.getMessage());
        }

        try{
            myTwitter.numeroSeguidores(p1.getUsuario());
        }catch (PIException | PDException e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(2, myTwitter.numeroSeguidores(p1.getUsuario()));
    }

    @Test
    @DisplayName("Teste do numero de seguidores (com exception (SIException))")
    public void test4() {
        try{
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        }catch (PEExcpetion e){
            System.out.println(e.getMessage());
        }

        try{
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p1.getUsuario(), p1.getUsuario()); // tentando seguir a si mesmo
        }catch(PIException | PDException | SIException e){
            System.out.println(e.getMessage());
        }

        try{
            myTwitter.numeroSeguidores(p1.getUsuario());
        }catch (PIException | PDException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste tweetar (sem exception)")
    public void Test5() throws PDException, PIException {
        try {
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        } catch (PEExcpetion e) {
            System.out.println(e.getMessage());
        }

        try {
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
        } catch (PIException | PDException | SIException e) {
            System.out.println(e.getMessage());
        }
        try{
            myTwitter.tweetar(p1.getUsuario(),"hello mundo!");
            myTwitter.tweetar(p1.getUsuario(), "oiii dnv");
        }catch (PIException | MFPException e){
            System.out.println(e.getMessage());
        }

        timeline = myTwitter.timeline(p2.getUsuario());
        Assertions.assertEquals("@gabriel_nzt", timeline.get(0).getUsuario());
        Assertions.assertEquals("hello mundo!",timeline.get(0).getMensagem());
        Assertions.assertEquals("oiii dnv", timeline.get(1).getMensagem());
    }

    @Test
    @DisplayName("Teste Tweetar (com exception (MFPException))")
    public void test6(){
        try {
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        } catch (PEExcpetion e) {
            System.out.println(e.getMessage());
        }

        try {
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
        } catch (PIException | PDException | SIException e) {
            System.out.println(e.getMessage());
        }
        try{
            myTwitter.tweetar(p1.getUsuario(),this.mensagem); // mensagem contem mmais de 140 caracteres
        }catch (PIException | MFPException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste lista de seguidos (sem exception)")
    public void teste7(){
        try {
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        } catch (PEExcpetion e) {
            System.out.println(e.getMessage());
        }

        try {
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p2.getUsuario(), p3.getUsuario());
        } catch (PIException | PDException | SIException e) {
            System.out.println(e.getMessage());
        }
        seguidores = p1.getSeguidores();
        seguidos = p2.getSeguidos();

        for(Perfil perfil: seguidos){
            System.out.println(perfil.getUsuario());
        }
    }

    @Test
    @DisplayName("Teste lista de seguidores (sem exception)")
    public void test8(){
        try {
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        } catch (PEExcpetion e) {
            System.out.println(e.getMessage());
        }

        try {
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p2.getUsuario(), p3.getUsuario());
        } catch (PIException | PDException | SIException e) {
            System.out.println(e.getMessage());
        }
        seguidores = p1.getSeguidores();
        seguidos = p2.getSeguidos();

        for(Perfil perfil: seguidores){
            System.out.println(perfil.getUsuario());
        }
    }

    @Test
    @DisplayName("Teste da lista se seguidores (com excpetion (PDException))")
    public void test9() throws PDException, PIException {
        try {
            myTwitter.criarPerfil(p1);
            myTwitter.criarPerfil(p2);
            myTwitter.criarPerfil(p3);
        } catch (PEExcpetion e) {
            System.out.println(e.getMessage());
        }

        try {
            myTwitter.seguir(p2.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p3.getUsuario(), p1.getUsuario());
            myTwitter.seguir(p2.getUsuario(), p3.getUsuario());
        } catch (PIException | PDException | SIException e) {
            System.out.println(e.getMessage());
        }
        myTwitter.cancelarPerfil(p1.getUsuario());

        try{
            seguidores = myTwitter.seguidores(p1.getUsuario()); // tentando acessar informações de um usuario desativado
        }catch (PDException e){
            System.out.println(e.getMessage());
        }
    }
}
