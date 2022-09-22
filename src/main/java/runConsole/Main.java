package runConsole;


import usuario.MyTwitter;
import usuario.PessoaFisica;
import usuario.PessoaJuridica;
import usuario.Tweet;
import usuario.entities.Perfil;
import usuario.exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean loop = true;
    static int option;
    static int aux = 0;

    public static void main(String[] args) {

        final Scanner input = new Scanner(System.in);

        ArrayList<Perfil> perfils;
        ArrayList<Tweet> tweets;
        MyTwitter myTwitter = new MyTwitter();
        String usuario;
        String mensagem;


       while(loop){

           if(aux == 0){
               aux++;
               System.out.println("[1] Criar perfil\n[2] sair");
               option = input.nextInt();
               switch (option){
                   case 1:
                       System.out.println("[1] Criar uma conta de Pessoa Física\n[2] Criar uma conta de Pessoa Juridica");
                       option = input.nextInt();
                       criarConta(myTwitter);
                       break;
                   case 2:
                       loop = false;
                       break;
                   default:
                       System.out.println("Comando invalido!!");
                       break;
               }
           }else{
               System.out.println("[1] criar perfil\n[2] Cancelar perfil\n[3] Tweetar\n[4] TimeLine\n[5] Tweets\n[6] Seguir\n[7] Numero de seguidores\n[8] Seguidores\n[9] Seguidos\n[0] Sair");
               option = input.nextInt();
               switch (option){
                   case 1:
                       System.out.println("[1] Criar uma conta de Pessoa Física\n[2] Criar uma conta de Pessoa Juridica");
                       option = input.nextInt();
                       criarConta(myTwitter);
                       break;
                   case 2:
                       System.out.println("Informe o nome de usuario que se deseja cancelar:");
                       usuario = input.next();
                       try{
                           myTwitter.cancelarPerfil(usuario);
                           System.out.println("O perfil foi cancelado com sucesso!");
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 3:
                       System.out.println("Informe o usuario que ira tweetar:");
                       usuario = input.next();
                       System.out.print("Digite a mensagem do Tweet:\n");
                       String aux = input.nextLine();
                       mensagem = input.nextLine();
                       try{
                           myTwitter.tweetar(usuario,mensagem);
                           System.out.println("Tweet feito com sucesso!");
                       } catch (PIException | MFPException e) {
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 4:
                       System.out.println("Informe o usuario que deseja saber a timeline:");
                       usuario = input.next();
                       try{
                           tweets = myTwitter.timeline(usuario);
                           for(Tweet tweet: tweets){
                               System.out.println(tweet.getUsuario()+"\n"+tweet.getMensagem());
                           }
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 5:
                       System.out.println("Informe o usuario que deseja saber os Tweets:");
                       usuario = input.next();
                       try{
                          tweets = myTwitter.tweets(usuario);
                           for(Tweet tweet: tweets){
                               System.out.println(tweet.getUsuario()+"\n"+tweet.getMensagem());
                           }
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 6:
                       System.out.println("Informe o usuario do perfil seguido:");
                       usuario = input.next();
                       System.out.println("Informe o usuario do perfil seguidor:");
                       String usuario2 = input.next();
                       try{
                           myTwitter.seguir(usuario2, usuario);
                           System.out.println("o usuario "+usuario2+" seguiu o usuario "+usuario+" com sucesso!!");
                       }catch (PIException | PDException | SIException e){
                           System.out.println(e.getMessage());
                       }

                       break;
                   case 7:
                       System.out.println("Informe o nome de usuario que deseja saber o numero de seguidores:");
                       usuario = input.next();
                       try{
                           System.out.println(myTwitter.numeroSeguidores(usuario));
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 8:
                       System.out.println("Informe o nome de usuario que deseja saber o nome dos seguidores:");
                       usuario = input.next();
                       try{
                          perfils =  myTwitter.seguidores(usuario);
                           for(Perfil perfil: perfils){
                               System.out.println(perfil.getUsuario());
                           }
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case 9:
                       System.out.println("Informe o nome de usuario que deseja saber o nome dos seguidos:");
                       usuario = input.next();
                       try{
                           perfils = myTwitter.seguidos(usuario);
                           for(Perfil perfil: perfils){
                               System.out.println(perfil.getUsuario());
                           }
                       }catch (PIException | PDException e){
                           System.out.println(e.getMessage());
                       }

                       break;

                   case 0:
                       loop = false;
                       break;
                   default:
                       System.out.println("Comando invalido!!");
                       break;
               }
           }


       }
    }

    private static void criarConta(MyTwitter myTwitter) {

    final Scanner input = new Scanner(System.in);

        String usuario;
        long cpf;
        long cnpj;
        switch (option){
            case 1:
                System.out.println("Informe o nome de usuario:");
                usuario = input.next();
                System.out.println("Informe o CPF do usuario:");
                cpf = input.nextLong();
                try{
                    myTwitter.criarPerfil(new PessoaFisica(usuario));
                }catch (PEExcpetion e){
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.println("Informe o nome de usuario:");
                usuario = input.next();
                System.out.println("Informe o CNPJ do usuario:");
                cnpj = input.nextLong();
                try{
                    myTwitter.criarPerfil(new PessoaJuridica(usuario));
                }catch (PEExcpetion e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Comando invalido!!");
                break;
        }
    }
}
