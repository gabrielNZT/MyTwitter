package usuario;

import usuario.RepositorioUsuario;
import usuario.exceptions.*;
import usuario.Tweet;
import usuario.entities.Perfil;
import usuario.interfaces.ITwitter;
import java.util.ArrayList;

 /**
 * Esta classe implementa a interface "ITwitter".
 */
public class MyTwitter implements ITwitter {

    /**
    * Atributos da classe "MyTwitter".
    */
    RepositorioUsuario repositorioUsuario;
    ArrayList<Perfil> seguidores;
    ArrayList<Perfil> seguidos;
    ArrayList<Tweet> tweets;

    /**
    * Contrutor da classe "MyTwitter".
    */
    public MyTwitter(){
        repositorioUsuario = new RepositorioUsuario();
        seguidores = new ArrayList<>();
        seguidos = new ArrayList<>();
        tweets = new ArrayList<>();
     }

    /**
     * Esse método cadastra o perfil passado como argumento por meio do método "cadastrar" da classe "RepositorioUsuario"
     * Restrição: se já exister um perfil com o mesmo nome de usuario será levantada uma (PEException).
     */
    @Override
    public void criarPerfil(Perfil usuario) throws PEExcpetion {
        try{
            repositorioUsuario.cadastrar(usuario);

        } catch (UJCException e) {
            throw new PEExcpetion(usuario.getUsuario());
        }
     }

    /**
     * Esse método desativa o perfil que tem o nome de usuario passado no argumento, para isso ele irá buscar
     * o perfil que tem o determinado nome de usario por meio do método "buscar" da classe "RepositorioUsuario" e depois
     * disso mudar o atributo "ativo" da classe "Perfil" tornando-o "false".
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException)
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException).
     */
    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            perfil.setAtivo(false);
        }
     }

    /**
     * Esse método adiciona um Tweet na timeline tanto do perfil que tuitou quanto dos seus seguidores.
     * Primeiro é buscado o perfil com o nome de usuario passado no argumento, depois é feito alguns tratamentos de
     * exceções e por fim é adicionado o Tweet nas timelines necessárias.
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso a mensagem passada no argumenta tenha mais de 140 caracteres será levantada uma (MFPException)
     */
    @Override
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(mensagem.length() > 140){
            throw new MFPException();
        }else{

            seguidores = perfil.getSeguidores();
            perfil.addTweet(new Tweet(usuario, mensagem));

            for(Perfil user : seguidores) {
                user.addTweet(new Tweet(usuario, mensagem));
            }
        }
     }

    /**
     * Esse método retorna a timeline do usuario passado no argumento.
     * Primeiro é buscado o perfil com o nome de usuario passado no argumento, depois é feito alguns tratamentos de
     * exceções e por fim é retornado a timeline do usuario.
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException).
     */
    @Override
    public ArrayList<Tweet> timeline(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            return perfil.getTimeline();
        }
     }

    /**
     * Esse método retorna uma ArrayList "tweets" que representa todos os tweets feitos pelo usuario passado no argumento.
     * Primeiro é buscado o perfil com o nome de usuario passado no argumento, depois é feito alguns tratamentos de
     * exceções, após isso é recebido na ArrayList tweets a timeline do usuario para então retornar essa ArrayList com
     * todos os tweets que não foram feitos por esse usuario removidos.
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException).
     */
    @Override
    public ArrayList<Tweet> tweets(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            tweets = perfil.getTimeline();
            tweets.removeIf(tweet -> !tweet.getUsuario().equals(perfil.getUsuario()));
            return tweets;
        }
     }

    /**
     * Esse método adiciona um seguido e um seguidor, respectivamente, nos perfis dos usuarios passados no argumento.
     * seguido (ganha um seguidor), seguidor (ganha um seguido).
     * Primeiro é buscado os perfil com os nome dos usuarios (seguidor, seguido) passados no argumento, depois é
     * feito alguns tratamentos de exceções e por fim é adicionado o perfil do seguidor na Arraylist de seguidores
     * do seguido e o perfil do seguido é adicionado na ArrayList de seguidos do seguidor.
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException).
     * Restrição 3: caso a String seguidor seja igual a String seguido será levantada uma (SIException).
     */
    @Override
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
        Perfil Useguidor = repositorioUsuario.buscar(seguidor);
        Perfil Useguido = repositorioUsuario.buscar(seguido);

        if(Useguidor == null){
            throw new PIException(seguido);
        }
        else if(Useguido == null){
            throw new PIException(seguido);
        }
        else if(!Useguido.isAtivo()){
            throw new PDException(seguido);
        }
        else if(!Useguidor.isAtivo()){
            throw new PDException(seguidor);
        }
        else if(seguidor.equals(seguido)){
            throw new SIException(seguidor);
        }else{
            Useguido.addSeguidor(Useguidor);
            Useguidor.addSeguido(Useguido);
        }
     }

    /**
     * Esse método retorna o número de seguidores do usuario passado no argumento.
     * Primeiro é buscado o perfil passado no argumento, depois é feito o tratamento de exceções e por fim é retornado
     * o tamanho da ArrayList seguidores que irá representar a quantidade de seguidores.
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException).
     * */
    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            return perfil.getSeguidores().size();
        }
     }

    /**
     * Esse método retorna a ArrayList seguidores do usuario passado no argumento.
     * Primeiro é buscado o perfil com o nome de usuario passado no argumento, depois é feito alguns tratamentos de
     * exceções e por fim é retornado a ArrayList seguidores do perfil
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException)
     */
    @Override
    public ArrayList<Perfil> seguidores(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            return perfil.getSeguidores();
        }
     }

    /**
     * Esse método retorna a ArrayList seguidos do usuario passado no argumento.
     * Primeiro é buscado o perfil com o nome de usuario passado no argumento, depois é feito alguns tratamentos de
     * exceções e por fim é retornado a ArrayList seguidos do perfil
     * Restrição 1: caso não exista um perfil com o nome de usuario passado no argumento será levantada uma (PIException).
     * Restrição 2: caso o perfil retornado pelo método "buscar" tenha atributo "ativo = false" será levantada.
     * uma (PDException)
     */
    @Override
    public ArrayList<Perfil> seguidos(String usuario) throws PIException, PDException {
        Perfil perfil = repositorioUsuario.buscar(usuario);
        if(perfil == null){
            throw new PIException(usuario);
        }
        else if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }else{
            return perfil.getSeguidos();
        }
    }
}
