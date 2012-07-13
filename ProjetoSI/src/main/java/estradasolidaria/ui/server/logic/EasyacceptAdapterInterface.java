package estradasolidaria.ui.server.logic;

import java.util.List;

import javax.mail.MessagingException;

public interface EasyacceptAdapterInterface {

	/**
	 * Cria usuario para o sistema.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws MessageException 
	 */
	public abstract void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws MessageException;

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
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * @throws MessageException 
	 * 
	 * @see Usuario, SistemaCaronas
	 */
	public abstract String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException, MessageException;

	/**
	 * Abre sessao para usuario identificado por login e senha.
	 * 
	 * @param login
	 * @param senha
	 * @return id sessao aberta
	 * @throws UsuarioInexistenteException 
	 */
	public abstract String abrirSessao(String login, String senha) throws UsuarioInexistenteException;

	/**
	 * Retorna atributo de um usuario.
	 * 
	 * @param login
	 * @param atributo
	 * @return atributo
	 * @throws UsuarioInexistenteException 
	 */
	public abstract Object getAtributoUsuario(String login, String atributo) throws UsuarioInexistenteException;

	/**
	 * Retorna lista de ids de carona localizadas.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return lista de ids de caronas como string
	 */
	public abstract String localizarCarona(String idSessao, String origem,
			String destino);

	/**
	 * Retorna atributo de carona
	 * 
	 * @param idCarona
	 * @param nomeAtributo
	 * @return atributo
	 */
	public abstract Object getAtributoCarona(String idCarona,
			String nomeAtributo);

	/**
	 * Retorna trajeto da carona.
	 * 
	 * @param idCarona
	 * @return trajeto
	 * @throws TrajetoInexistenteException
	 */
	public abstract String getTrajeto(String idCarona)
			throws TrajetoInexistenteException;

	/**
	 * Retorna carona.
	 * 
	 * @param idCarona
	 * @return carona
	 */
	public abstract String getCarona(String idCarona);

	/**
	 * Encerra sessao aberta.
	 * 
	 * @param login
	 */
	public abstract void encerrarSessao(String login);

	/**
	 * Torna null todos os objetos do sistema.
	 */
	public abstract void zerarSistema();

	/**
	 * Persiste e encerra o sistema.
	 */
	public abstract void encerrarSistema();

	/**
	 * Reinicia o sistema com os dados persistidos no encerramento.
	 */
	public abstract void reiniciarSistema();

	/**
	 * Retorna atributo da solicitacao a partir do nome do atributo.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return atributo
	 */
	public abstract Object getAtributoSolicitacao(String idSolicitacao,
			String atributo);

	/**
	 * Sugere ponto de encontro para uma carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return id da sugestao feita
	 * @throws MessageException 
	 */
	public abstract String sugerirPontoEncontro(String idSessao,
			String idCarona, String pontos) throws MessageException;

	/**
	 * Responde a uma sugestao feita por outro usuario.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 *            : ponto de encontro escolhido ate o momento para a carona
	 * @throws CaronaInexistenteException 
	 * @throws MessageException 
	 */
	public abstract void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws CaronaInexistenteException, MessageException;

	/**
	 * Solicita vaga e sugere um ponto de encontro para a carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return id de solicitacao
	 * @throws CaronaInvalidaException
	 * @throws CaronaInexistenteException 
	 * @throws CadastroEmCaronaPreferencialException 
	 * @throws MessageException 
	 */
	public abstract String solicitarVagaPontoEncontro(String idSessao,
			String idCarona, String ponto) throws CaronaInvalidaException, CaronaInexistenteException, CadastroEmCaronaPreferencialException, MessageException;

	/**
	 * Aceita uma solicitacão.
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * @throws IllegalArgumentException 
	 * @throws MessageException 
	 */
	public abstract void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws CaronaInexistenteException, IllegalArgumentException, EstadoSolicitacaoException, MessageException;

	/**
	 * Aceita solicitacao
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws IllegalArgumentException 
	 * @throws EstadoSolicitacaoException 
	 * @throws MessageException 
	 */
	public abstract void aceitarSolicitacao(String idSessao,
			String idSolicitacao) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException, MessageException;

	/**
	 * Adiciona solicitacao a lista de solicitacoes associadas a uma carona,
	 * indicada por idCarona. A solicitacao eh feita pelo usuario indicado por
	 * idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return solicitacao feita
	 * @throws CaronaInvalidaException
	 * @throws CadastroEmCaronaPreferencialException 
	 * @throws IllegalArgumentException 
	 * @throws MessageException 
	 */
	public abstract String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInvalidaException, IllegalArgumentException, CadastroEmCaronaPreferencialException, MessageException;

	/**
	 * Usuario dono da carona, indicado por idSessao, rejeita solicitacao,
	 * indicada por idSolicitacao.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * @throws MessageException 
	 */
	public abstract void rejeitarSolicitacao(String idSessao,
			String idSolicitacao) throws CaronaInexistenteException, EstadoSolicitacaoException, MessageException;

	/**
	 * Remove a solicitacao, indicada por idSolicitacao, da lista de
	 * solicitacoes da carona indicada por idCarona, feita pelo usuario indicado
	 * por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacao
	 * @throws CaronaInvalidaException
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * @throws MessageException 
	 */
	public abstract void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInvalidaException, CaronaInexistenteException, EstadoSolicitacaoException, MessageException;

	/**
	 * Pega usuario associado a sessao indicada por idSessao e pega o perfil
	 * dele.
	 * 
	 * @param idSessao
	 * @param login
	 * @return idPerfil
	 */
	public abstract String visualizarPerfil(String idSessao, String login);

	/**
	 * Pesquisa usuario com login e retorna um atributo do perfil dele.
	 * 
	 * @param login
	 * @param nomeAtributo
	 * @return atributo
	 */
	public abstract Object getAtributoPerfil(String login, String atributo);

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
	 * @throws UsuarioInexistenteException 
	 */
	public abstract void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException, UsuarioInexistenteException;

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
	 * @throws UsuarioInexistenteException 
	 */
	public abstract void reviewCarona(String idSessao, String idCarona,
			String review) throws CaronaInexistenteException, UsuarioInexistenteException;

	/**
	 * Cadastra no usuario identificado por idSessao, a carona com os atributos
	 * origem, destino, cidade, data, hora e vagas.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @return id carona cadastrada
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * @throws MessageException 
	 */
	public abstract String cadastrarCaronaMunicipal(String idSessao,
			String origem, String destino, String cidade, String data,
			String hora, String vagas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException, MessageException;

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
	public abstract List<String> localizarCaronaMunicipal(String idSessao,
			String cidade, String origem, String destino);

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem a especificacao requerida pelo o cliente, no caso cidade
	 * da carona.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @return lista de ids de caronas
	 */
	public abstract List<String> localizarCaronaMunicipal(String idSessao,
			String cidade);

	/**
	 * Retorna carona do usuario identificado por idSessao. O indexCarona vai
	 * trabalhar com a ordemParaCaronas, jah que o indice vai está na mesma
	 * ordem relativa de inserção das caronas no sistema.
	 * 
	 * @param idSessao
	 * @param indexCarona
	 * @return carona
	 */
	public abstract String getCaronaUsuario(String idSessao, int indexCarona);

	/**
	 * Retorna todas as caronas cadastradas pelo usuario identificado por
	 * idSessao.
	 * 
	 * @param idSessao
	 * @return lista de ids de caronas como string
	 */
	public abstract String getTodasCaronasUsuario(String idSessao);

	/**
	 * Retorna lista de ids de solicitacoes confirmadas para a carona (idCarona)
	 * oferecida pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes confirmadas como string
	 */
	public abstract String getSolicitacoesConfirmadas(String idSessao,
			String idCarona);

	/**
	 * Retorna lista de solicitacoes pendentes de serem respondidas pelo usuario
	 * dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes
	 * @throws CaronaInvalidaException
	 */
	public abstract String getSolicitacoesPendentes(String idSessao,
			String idCarona) throws CaronaInvalidaException;

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
	public abstract String getPontosSugeridos(String idSessao, String idCarona)
			throws CaronaInvalidaException;

	/**
	 * Retorna ponto de encontro para a carona identificada por idCarona.
	 * idSessao eh o identificador do usuario dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return string com ponto de encontro
	 */
	public abstract String getPontosEncontro(String idSessao, String idCarona);

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
	 * @throws CaronaInvalidaException 
	 */
	public abstract String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) throws CaronaInvalidaException;

	/**
	 * Resume as mensagens sobre as caronas sobre as quais o usuario demonstra
	 * interesse.
	 * 
	 * @param idSessao
	 * @return mensagens
	 */
	public abstract String verificarMensagensPerfil(String idSessao);

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
	public abstract boolean enviarEmail(String idSessao, String destino,
			String message) throws MessagingException;

	/**
	 * Retorna atributo de carona relampago.
	 * 
	 * @param atributo
	 * @return atributo de carona
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 */
	public Object getAtributoCaronaRelampago(String idCarona, String atributo) throws CaronaInvalidaException, CaronaInexistenteException;
	
	/**
	 * Retorna minimo de caroneiros para a carona
	 * identificada por idCarona.
	 * 
	 * @param idCarona
	 * @return minimo de caroneiros
	 * @throws CaronaInexistenteException 
	 * @throws CaronaInvalidaException 
	 */
	public Integer getMinimoCaroneiros(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException;
	
	/**
	 * Retorna atributo de 
	 * 
	 * @return
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 */
	public Object getAtributoExpired(String idExpired, String atributo) throws CaronaInvalidaException, CaronaInexistenteException;
	
	/**
	 * Retorna carona relampago identificada por idCarona
	 * 
	 * @param idCarona
	 * @return carona
	 * @throws CaronaInexistenteException 
	 * @throws CaronaInvalidaException 
	 */
	public String getCaronaRelampago(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException;

	/**
	 * Configures uma nova carona expirada (para testes)
	 * 
	 * @param idCarona
	 * @return 
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoCaronaException 
	 * @throws MessagingException 
	 */
	public Integer setCaronaRelampagoExpired(String idCarona) throws CaronaInvalidaException, CaronaInexistenteException, MessagingException, EstadoCaronaException;
	
	
	
	/**
	 * Define uma carona como preferencial.
	 * 
	 * @param idCarona
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 */
	public void definirCaronaPreferencial(String idCarona) throws CaronaInvalidaException, CaronaInexistenteException; 
	
	/**
	 * Retorna se uma carona identificada por idCarona eh preferencial.
	 * 
	 * @param idCarona
	 * @return true se carona eh preferencial
	 * @throws CaronaInexistenteException 
	 * @throws CaronaInvalidaException 
	 */
	public boolean isCaronaPreferencial(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException; 
	
	/**
	 * Retorna lista de usuarios preferenciais.
	 * 
	 * @param idCarona
	 * @return lista de usuarios
	 * @throws CaronaInexistenteException 
	 * @throws CaronaInvalidaException 
	 */
	public String getUsuariosPreferenciaisCarona(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException;
	
	/**
	 * Cadastra carona relampago no sistema.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param minimoCaroneiros
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 */
	public Integer cadastrarCaronaRelampago(String idSessao, String origem,
			String destino, String dataIda, String dataVolta,
			String hora, String minimoCaroneiros) throws MessagingException, CaronaInvalidaException, EstadoCaronaException;
	
	/**
	 * Retorna lista de usuarios ranqueados segundo avaliacoes feitas por outros
	 * usuario e registradas no sistema.
	 * 
	 * @param ordem
	 * @return lista de usuarios
	 */
	public String getRankingUsuarios(String ordem);
}