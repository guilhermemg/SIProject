package estradasolidaria.ui.client;

import java.util.List;

import javax.mail.MessagingException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("estradaSolidariaService")
public interface EstradaSolidariaService extends RemoteService {
	
	/**
	 * Cria usuario para o sistema.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception 
	 */
	public abstract void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws GWTException;

	/**
	 * Cadastra carona na listaDeCaronasOferecidas do usuario cadastrante e no
	 * mapa de caronas do sistema.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return id da carona cadastrada
	 * @throws GWTException 
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * 
	 * @see Usuario, SistemaCaronas
	 */
	public abstract String cadastrarCarona(Integer idSessao, String origem,
			String destino, String data, String hora, String vagas) throws GWTException;

	/**
	 * Abre sessao para usuario identificado por login e senha.
	 * 
	 * @param login
	 * @param senha
	 * @return id sessao aberta
	 * @throws Exception 
	 */
	public abstract Integer abrirSessao(String login, String senha) throws GWTException;

	/**
	 * Retorna lista de ids de carona localizadas.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return lista de ids de caronas como string
	 * @throws GWTException 
	 */
	public abstract List<GWTCarona> localizarCarona(Integer idSessao, String origem,
			String destino) throws GWTException;

	/**
	 * Retorna trajeto da carona.
	 * 
	 * @param idCarona
	 * @return trajeto
	 * @throws TrajetoInexistenteException
	 */
	public abstract String getTrajeto(Integer idCarona)
			throws GWTException;

	/**
	 * Retorna carona.
	 * 
	 * @param idCarona
	 * @return carona
	 */
	public abstract List<String> getCarona(Integer idCarona) throws GWTException;

	/**
	 * Encerra sessao aberta.
	 * 
	 * @param login
	 */
	public abstract void encerrarSessao(String login) throws GWTException;

	/**
	 * Torna null todos os objetos do sistema.
	 */
	public abstract void zerarSistema() throws GWTException;

	/**
	 * Persiste e encerra o sistema.
	 */
	public abstract void encerrarSistema() throws GWTException;

	/**
	 * Reinicia o sistema com os dados persistidos no encerramento.
	 */
	public abstract void reiniciarSistema() throws GWTException;

	/**
	 * Sugere ponto de encontro para uma carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return id da sugestao feita
	 */
	public abstract String sugerirPontoEncontro(Integer idSessao,
			Integer idCarona, String pontos)  throws GWTException;

	/**
	 * Responde a uma sugestao feita por outro usuario.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 *            : ponto de encontro escolhido ate o momento para a carona
	 */
	public abstract void responderSugestaoPontoEncontro(Integer idSessao,
			Integer idCarona, String idSugestao, String pontos) throws GWTException;

	/**
	 * Solicita vaga e sugere um ponto de encontro para a carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return id de solicitacao
	 * @throws CaronaInvalidaException
	 * @throws CaronaInexistenteException 
	 */
	public abstract String solicitarVagaPontoEncontro(Integer idSessao,
			Integer idCarona, String ponto) throws GWTException;

	/**
	 * Aceita uma solicitacão.
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 */
	public abstract void aceitarSolicitacaoPontoEncontro(Integer idSessao,
			Integer idSolicitacao) throws GWTException;

	/**
	 * Aceita solicitacao
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 * @throws GWTException 
	 */
	public abstract void aceitarSolicitacao(Integer idSessao,
			Integer idSolicitacao) throws GWTException;

	/**
	 * Adiciona solicitacao a lista de solicitacoes associadas a uma carona,
	 * indicada por idCarona. A solicitacao eh feita pelo usuario indicado por
	 * idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return solicitacao feita
	 * @throws CaronaInvalidaException
	 */
	public abstract String solicitarVaga(Integer idSessao, Integer idCarona)
			throws GWTException;

	/**
	 * Usuario dono da carona, indicado por idSessao, rejeita solicitacao,
	 * indicada por idSolicitacao.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws GWTException 
	 */
	public abstract void rejeitarSolicitacao(Integer idSessao,
			Integer idSolicitacao) throws GWTException;

	/**
	 * Remove a solicitacao, indicada por idSolicitacao, da lista de
	 * solicitacoes da carona indicada por idCarona, feita pelo usuario indicado
	 * por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacao
	 * @throws CaronaInvalidaException
	 */
	public abstract void desistirRequisicao(Integer idSessao, Integer idCarona,
			Integer idSolicitacao) throws GWTException;

	/**
	 * Pega usuario associado a sessao indicada por idSessao e pega o perfil
	 * dele.
	 * 
	 * @param idSessao
	 * @param login
	 * @return idPerfil
	 */
	public abstract String visualizarPerfil(Integer idSessao, String login)  throws GWTException;

	/**
	 * Seta o valor do review de vaga em carona: diz se o solicitante
	 * (caroneiro) faltou ou nao.
	 * 
	 * valores validos: . faltou . nao faltou
	 * 
	 * @param idSessao
	 *            : id da sessao do dono da carona
	 * @param idCarona
	 *            : id da carona oferecida
	 * @param loginCaroneiro
	 *            : login do caroneiro
	 * @param review
	 *            : faltou ou nao faltou
	 * @throws CaronaInvalidaException
	 */
	public abstract void reviewVagaEmCarona(Integer idSessao, Integer idCarona,
			String loginCaroneiro, String review) throws GWTException;

	/**
	 * Seta o valor do review da carona, diz se a carona foi boa ou nao. O
	 * review eh feito pelo usuario da carona (caroneiro). Pesquisa entre as
	 * caronas pegas por ele, idCarona e seta o review.
	 * 
	 * valores validos: . carona segura e tranquila . carona nao funcionou
	 * 
	 * obs.: A quantidade de caronas seguras e tranquilas e que não funcionaram
	 * devem aparecer no perfil do motorista
	 * 
	 * @param idSessao
	 *            : id do caroneiro
	 * @param idCarona
	 *            : id da carona pega por ele (caroneiro)
	 * @param review
	 *            : review do caroneiro presente
	 */
	public abstract void reviewCarona(Integer idSessao, Integer idCarona,
			String review) throws GWTException;

	/**
	 * Cadastra no usuario identificado por idSessao, a carona com os atributos
	 * origem, destino, cidade, data, hora e vagas.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @return id carona cadastrada
	 */
	public abstract String cadastrarCaronaMunicipal(Integer idSessao,
			String origem, String destino, String cidade, String data,
			String hora, String vagas) throws GWTException;

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem as especificacoes requeridas pelo o cliente.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @param origem
	 * @param destino
	 * @return lista de ids de caronas localizadas
	 */
	public abstract List<String> localizarCaronaMunicipal(Integer idSessao,
			String cidade, String origem, String destino) throws GWTException;

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem a especificacao requerida pelo o cliente, no caso cidade
	 * da carona.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @return lista de ids de caronas
	 */
	public abstract List<String> localizarCaronaMunicipal(Integer idSessao,
			String cidade) throws GWTException;

	/**
	 * Retorna carona do usuario identificado por idSessao. O indexCarona vai
	 * trabalhar com a ordemParaCaronas, jah que o indice vai está na mesma
	 * ordem relativa de inserção das caronas no sistema.
	 * 
	 * @param idSessao
	 * @param indexCarona
	 * @return carona
	 */
	public abstract String getCaronaUsuario(Integer idSessao, int indexCarona) throws GWTException;

	List<GWTCarona> getTodasCaronasUsuario(Integer idSessao) throws GWTException;

	/**
	 * Retorna lista de ids de solicitacoes confirmadas para a carona (idCarona)
	 * oferecida pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes confirmadas como string
	 */
	public abstract List<List<String>> getSolicitacoesFeitasConfirmadas(Integer idSessao) throws GWTException;

	/**
	 * Retorna lista de solicitacoes pendentes de serem respondidas pelo usuario
	 * dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes
	 * @throws CaronaInvalidaException
	 */
	public abstract List<List<String>> getSolicitacoesFeitasPendentes(Integer idSessao) throws GWTException;

	/**
	 * Retorna string com o ponto sugerido para idCarona. O usuario identificado
	 * por idSessao deve ser o dono da carona.
	 * 
	 * @param idSessao
	 *            : id da sessao do dono da carona
	 * @param idCarona
	 *            : id da carona referida
	 * @return string de lista de pontos sugeridos
	 * @throws CaronaInvalidaException
	 */
	public abstract String getPontosSugeridos(Integer idSessao, Integer idCarona)
			throws GWTException;

	/**
	 * Retorna ponto de encontro para a carona identificada por idCarona.
	 * idSessao eh o identificador do usuario dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return string com ponto de encontro
	 */
	public abstract String getPontosEncontro(Integer idSessao, Integer idCarona) throws GWTException;

	/**
	 * Cadastra interesse em determinada carona, mas nao envia solicitacao de
	 * participacao. As caronas selecionadas como atendentes ao interesse do
	 * usuario registrador vai aparecer nas mensagens do perfil dele.
	 * 
	 * obs.: Para os horários o sistema poderá deixar o usuário livre, ou seja,
	 * ele poderá não colocar horaInicio (pegará todos a partir das 0h do dia
	 * especificado. ou horaFim(pegará todos até às 11:59 do dia especificado)
	 * que a consulta será realizada.
	 * 
	 * obs.: data="" retornará todas as caronas que tem marcadas da data atual
	 * em diante.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param horaInicio
	 * @param horaFim
	 * @return idInteresse
	 */
	public abstract String cadastrarInteresse(Integer idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) throws GWTException;

	/**
	 * Resume as mensagens sobre as caronas sobre as quais o usuario demonstra
	 * interesse.
	 * 
	 * @param idSessao
	 * @return mensagens
	 */
	public abstract String verificarMensagensPerfil(Integer idSessao) throws GWTException;

	/**
	 * Envia email criado automaticamente pelo sistema, a partir de um conjunto
	 * predefinido de mensagens.
	 * 
	 * @param idSessao
	 * @param destino
	 * @param message
	 * @return boolean indicando se email foi enviado com sucesso
	 * @throws MessagingException
	 */
	public abstract boolean enviarEmail(Integer idSessao, String destino,
			String message) throws GWTException;

	List<GWTCarona> getTodasCaronasPegas(Integer idSessao) throws GWTException;

	public abstract void editarSenha(Integer idSessaoAberta, String novaSenha) throws GWTException;

	public abstract List<List<String>> getCaroneiros(Integer idSessao, String idCarona) throws GWTException;
	
	public abstract void editarLogin(Integer idSessaoAberta, String novoLogin) throws GWTException;

	public abstract void editarNome(Integer idSessaoAberta, String novoNome) throws GWTException;

	public abstract void editarEmail(Integer idSessaoAberta, String novoEmail) throws GWTException;

	public abstract void editarEndereco(Integer idSessaoAberta, String novoEndereco) throws GWTException;
	
	public abstract String[] getUsuario(Integer idSessao) throws GWTException;

	public abstract List<String[]> getSolicitacoes(Integer idSessao, Integer idCarona) throws GWTException;

	public abstract List<GWTInteresse> getInteresses(Integer idSessao) throws GWTException;
	
	public abstract List<String> pesquisaUsuariosNoSistema(String nome) throws GWTException;

	public abstract List<String> getUsuarioNoSistema(Integer idUsuario) throws GWTException;

	void deletarInteresse(Integer idSessao, Integer idInteresse) throws GWTException;
}
