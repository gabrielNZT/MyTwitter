package InterfaceGráfica;

import usuario.*;
import usuario.entities.Perfil;
import usuario.exceptions.*;

import javax.swing.*;
import java.util.ArrayList;

public class MyTwitterRun extends JFrame{
    private JButton criarPerfilButton;
    private JPanel menu;
    private JButton sairButton;
    private JButton seguidosButton;
    private JButton cancelarPerfilButton;
    private JButton timeLineButton;
    private JButton tweetsButton;
    private JButton tweetarButton;
    private JButton seguidoresButton;
    private JButton numeroDeSeguidoresButton;
    private JButton seguirButton;

    MyTwitter myTwitter = new MyTwitter();
    ArrayList<Tweet> tweets = new ArrayList<>();
    ArrayList<Perfil> perfils = new ArrayList<>();


    public MyTwitterRun(String title) {

        super(title);

        setContentPane(menu);

        criarPerfilButton.addActionListener(e -> {

            int option = Integer.parseInt(JOptionPane.showInputDialog("[1] Pessoa física\n[2] Pessoa Jurídica\n\n\n"));
            switch (option){
                case 1:
                    String usuario = JOptionPane.showInputDialog("Informe o usuario:\n\n\n");
                    long cpf = Long.parseLong(JOptionPane.showInputDialog("Informe o CPF\n\n\n"));
                    try{
                        PessoaFisica pessoaFisica = new PessoaFisica(usuario);
                        myTwitter.criarPerfil(pessoaFisica);
                        pessoaFisica.setCpf(cpf);
                        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!!!");
                    }catch (PEExcpetion err){
                        JOptionPane.showMessageDialog(null,err.getMessage());
                    }
                  break;
                case 2:
                    usuario = JOptionPane.showInputDialog("Informe o usuario:\n\n\n");
                    long cnpj = Long.parseLong(JOptionPane.showInputDialog("Informe o CNPJ\n\n\n"));
                    try{
                        PessoaJuridica pessoaJuridica = new PessoaJuridica(usuario);
                        myTwitter.criarPerfil(pessoaJuridica);
                        pessoaJuridica.setCnpj(cnpj);
                        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!!!");
                    }catch (PEExcpetion err){
                        JOptionPane.showMessageDialog(null,err.getMessage());
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "COMANDO INVÁLIDO!!");
                    break;
            }
        });

        cancelarPerfilButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o usuario:\n\n\n");
            try{
                myTwitter.cancelarPerfil(usuario);
                JOptionPane.showMessageDialog(null, "Conta desativada com sucesso!!!");
            }catch (PIException | PDException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        });

        tweetarButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");
            String mensagem = JOptionPane.showInputDialog("Digete a mensagem:\n\n\n");

            try{
                myTwitter.tweetar(usuario, mensagem);
                JOptionPane.showMessageDialog(null, "Tweet enviado com sucesso!!!");
            }catch (PIException | MFPException err){
                JOptionPane.showMessageDialog(null,err.getMessage());
            }
        });

        timeLineButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");
            try {
                tweets =  myTwitter.timeline(usuario);
                StringBuilder stringBuilder = new StringBuilder();
                for (Tweet tweet : tweets) {
                    stringBuilder.append("@").append(tweet.getUsuario()).append("\n").append(tweet.getMensagem()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, stringBuilder.toString());

                }catch (PIException | PDException err){
                JOptionPane.showMessageDialog(null,err.getMessage());
            }

        });

        tweetsButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");

            try{
                tweets = myTwitter.tweets(usuario);
                StringBuilder stringBuilder = new StringBuilder();
                for(Tweet tweet: tweets){
                    stringBuilder.append("@").append(tweet.getUsuario()).append("\n").append(tweet.getMensagem()).append("\n\n");
                    JOptionPane.showMessageDialog(null, stringBuilder.toString());
                }
            }catch (PIException | PDException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        });

        seguirButton.addActionListener(e -> {
          String seguido =  JOptionPane.showInputDialog("Nome do usuario que será seguido:\n\n\n");
          String seguidor = JOptionPane.showInputDialog("nome do usuario seguidor:\n\n\n");
          try{
              myTwitter.seguir(seguidor, seguido);
              JOptionPane.showMessageDialog(null, "você seguiu @"+seguido+" com sucessor!!\nAgora você poderá ver os novos Tweets dele(a) na sua TimeLine :)");
          }catch (PIException | PDException | SIException err){
              JOptionPane.showMessageDialog(null, err.getMessage());
          }
        });

        numeroDeSeguidoresButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");
            try{
                int num_seguidores = myTwitter.numeroSeguidores(usuario);
                if(num_seguidores == 1){
                    JOptionPane.showMessageDialog(null, "@"+usuario+" "+num_seguidores+ " seguidor");
                }else{
                    JOptionPane.showMessageDialog(null, "@"+usuario+" "+num_seguidores+ " seguidores");
                }
            }catch (PIException | PDException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        });
        seguidoresButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");
            try{
                perfils = myTwitter.seguidores(usuario);

                if(perfils.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Você não tem seguidores 😭");
                }else{
                    StringBuilder stringBuilder = new StringBuilder();
                    for(Perfil perfil: perfils){
                        stringBuilder.append(perfil.getUsuario()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, stringBuilder.toString());
                }
            }catch (PIException | PDException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        });
        seguidosButton.addActionListener(e -> {
            String usuario = JOptionPane.showInputDialog("Informe o nome do usuario:\n\n\n");
            try{
                perfils = myTwitter.seguidos(usuario);
                if(perfils.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Você não segue ninguem 😭");
                }else{
                    StringBuilder stringBuilder = new StringBuilder();
                    for(Perfil perfil: perfils){
                        stringBuilder.append(perfil.getUsuario()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, stringBuilder.toString());
                }
            }catch(PIException | PDException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        });
        sairButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        MyTwitterRun frame = new MyTwitterRun("MyTwitter");
        //frame.setContentPane(new MyTwitterRun("MyTwitter").menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

}
