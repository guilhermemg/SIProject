package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;

import estradasolidaria.ui.server.adder.Adder;
import estradasolidaria.ui.server.data.GerenciadorDeDados;
import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;


/**
 * Classe principal do sistema, representa o controller no padrao arquitetural
 * MVC.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class EstradaSolidariaController implements Serializable {
	private static final long serialVersionUID = -9005050380302326978L;

	private Integer ordemParaCaronas = 0;

	private Map<Integer, Sessao> mapIdSessao = new TreeMap<Integer, Sessao>(); // contem
	private Iterator<Sessao> iteratorIdSessao = this.mapIdSessao.values()
			.iterator();

	private Map<Integer, Usuario> mapIdUsuario = new TreeMap<Integer, Usuario>();
	private Iterator<Usuario> iteratorIdUsuario = this.mapIdUsuario.values()
			.iterator();

	private GerenciadorDeDados gerenciadorDeDados = GerenciadorDeDados
			.getInstance(this.mapIdUsuario);

	private static volatile EstradaSolidariaController uniqueInstance;

	private EstradaSolidariaController() {
	};

	/**
	 * Retorna unica instancia do controlador
	 * 
	 * @return instancia
	 */
	public static EstradaSolidariaController getInstance() {
		if (uniqueInstance == null) {
			synchronized (EstradaSolidariaController.class) {
				if (uniqueInstance == null)
					uniqueInstance = new EstradaSolidariaController();
			}
		}
		return uniqueInstance;
	}

	/**
	 * Cria usuario para o sistema.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(login))
				throw new IllegalArgumentException(
						"Já existe um usuário com este login");

			if (u.getEmail().equals(email))
				throw new IllegalArgumentException(
						"Já existe um usuário com este email");
		}

		Usuario user = new Usuario(login, senha, nome, endereco, email);
		this.mapIdUsuario.put(user.getIdUsuario(), user);
	}

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
	 * @return carona cadastrada
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * 
	 * @see Usuario
	 */
	public Carona cadastrarCarona(Integer idSessao, String origem,
			String destino, String data, String hora, Integer vagas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");

		if (!this.mapIdSessao.containsKey(idSessao))
			throw new IllegalArgumentException("Sessão inexistente");

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());

		if (donoDaCarona == null)
			throw new IllegalArgumentException("Usuário não encontrado");

		Carona carona = donoDaCarona.cadastrarCarona(
				donoDaCarona.getIdUsuario(), origem, destino, data, hora,
				vagas, ordemParaCaronas++);
		
		if (carona != null) {
			atualizaMensagensEmPerfis(carona);
			return carona;
		} 
		else
			throw new IllegalArgumentException("Carona Inválida");
	}

	/**
	 * Abre sessao para usuario identificado por login e senha.
	 * 
	 * @param login
	 * @param senha
	 * @return sessao aberta
	 */
	public Sessao abrirSessao(String login, String senha) {
		Sessao s = null;
		Usuario user = null;
		if (login == null || login.equals(""))
			throw new IllegalArgumentException("Login inválido");
		if (senha == null || senha.equals(""))
			throw new IllegalArgumentException("Senha inválida");
		
		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(login)) {
				user = u;
				if (user.validaSenha(senha)) {
					s = new Sessao(user.getIdUsuario());
					this.mapIdSessao.put(s.getIdSessao(), s);
					return s;
				}
				throw new IllegalArgumentException("Senha inválida");
			}
		}
		throw new UsuarioInexistenteException();
	}

	/**
	 * Retorna uma lista de id's de caronas. Localiza caronas compativeis com os
	 * parametros passados por usuario.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return lista com as caronas localizadas
	 */
	public List<Carona> localizarCarona(Integer idSessao, String origem,
			String destino) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (origem == null
				|| (!origem.equals("") && origem.toUpperCase().equals(origem)))
			throw new IllegalArgumentException("Origem inválida");
		if (destino == null
				|| (!destino.equals("") && destino.toUpperCase()
						.equals(destino)))
			throw new IllegalArgumentException("Destino inválido");

		List<Carona> listaCaronas = new LinkedList<Carona>();
		
		//Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> it = u.localizarCarona(origem, destino).iterator();
			while (it.hasNext()) {
				listaCaronas.add(it.next());
			}
		}

		Collections.sort(listaCaronas); // ordena lista pela ordem de insercao
										// das caronas no sistema
		return listaCaronas;
	}

	/**
	 * Retorna trajeto da carona oferecida pelo usuario que a registrou (Dono da
	 * carona).
	 * 
	 * @param idCarona
	 * @return trajeto
	 */
	public String[] getTrajeto(Integer idCarona) {
		if (idCarona == null)
			throw new IllegalArgumentException("Trajeto Inválida");

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getMapIdCaronasOferecidas().containsKey(idCarona))
				return u.getTrajeto(idCarona);
		}
		throw new IllegalArgumentException("Trajeto Inexistente");
	}

	/**
	 * Retorna a carona oferecida pelo usuario que a registrou.
	 * 
	 * @param idCarona
	 * @return string com dados da carona
	 * @throws Exception
	 */
	public Carona getCarona(Integer idCarona) {
		if (idCarona == null)
			throw new IllegalArgumentException("Carona Inválida");

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getMapIdCaronasOferecidas().containsKey(idCarona))
				return u.getCarona(idCarona);
		}
		throw new IllegalArgumentException("Carona Inexistente");
	}

	/**
	 * Remove sessao do mapa de sessoes do sistema.
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		Iterator<Sessao> it = this.mapIdSessao.values().iterator();
		while (it.hasNext()) {
			if (this.mapIdUsuario.get(it.next().getIdUser()).getLogin()
					.equals(login)) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Torna null todos os objetos que estao sendo manipulados atualmente pelo
	 * sistema.
	 */
	public void zerarSistema() {
		this.mapIdUsuario.clear();
		this.mapIdSessao.clear();

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			u.zerarSistema();
		}
		gerenciadorDeDados.zerarSistema();
	}

	/**
	 * Persiste os dados do sistema e limpa mapa atual.
	 */
	public void encerrarSistema() {
		gerenciadorDeDados.encerrarSistema();
		this.mapIdUsuario.clear();
	}

	/**
	 * Repovoa o mapa de usuario do sistema.
	 */
	public void reiniciarSistema() {
		mapIdUsuario = gerenciadorDeDados.reiniciarSistema();
	}

	/**
	 * Usuario indicado por idSessao sugere um ponto de encontro para carona
	 * identificada por idCarona.
	 * 
	 * @param idSessao
	 *            : id do usuario q faz a solicitacao/sugestao
	 * @param idCarona
	 *            : carona a qual está destinada a solicitacao
	 * @param pontos
	 *            : ponto de encontro sugerido
	 * @return id da sugestao feita
	 */
	public Sugestao sugerirPontoEncontro(Integer idSessao, Integer idCarona,
			String pontos) {
		if (idSessao == null)
			throw new IllegalArgumentException("Ponto Inválido"); // "Ponto Inválido"
		if (idCarona == null)
			throw new IllegalArgumentException("IdCarona inválido");

		Usuario donoDaSugestao = getMapIdUsuario().get(
				getMapIdSessao().get(idSessao).getIdUser());
		if (donoDaSugestao == null)
			throw new IllegalArgumentException("Sessão inválida");
		
		Sugestao sugestaoFeita = procuraCarona(idCarona).addSugestaoPontoEncontro(pontos); 
		
		donoDaSugestao.addSugestaoFeita(sugestaoFeita);
		
		return sugestaoFeita;
	}

	/**
	 * Procura carona no usuario indicado por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return carona
	 */
	private Carona procuraCarona(Integer idCarona) {
		for (Iterator<Usuario> itUsuario = getMapIdUsuario().values()
				.iterator(); itUsuario.hasNext();) {
			Usuario u = itUsuario.next();
			for (Iterator<Carona> itCarona = u.getMapIdCaronasOferecidas()
					.values().iterator(); itCarona.hasNext();) {
				Carona c = itCarona.next();
				if (c.getIdCarona().equals(idCarona)) {
					return c;
				}
			}
		}
		throw new IllegalArgumentException("Carona Inexistente");
	}

	/**
	 * 
	 * @param idSessao
	 *            : id do usuario q responde a solicitacao/sugestao
	 * @param idCarona
	 *            : carona a qual está destinada a solicitacao
	 * @param idSolicitacao
	 *            : id da solicitacao feita por outro usuario
	 * @param ponto
	 *            : ponto de encontro sugerido pelo dono da carona
	 * 
	 *             obs.: o ponto encontro da carona eh setado como esse q eh
	 *             respondido pelo dono da carona. O ponto pode ser identico ou
	 *             nao ao ponto sugerido pelo usuario solicitante.
	 */
	public void responderSugestaoPontoEncontro(Integer idSessao,
			Integer idCarona, Integer idSugestao, String pontos) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null)
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		if (idSugestao == null)
			throw new IllegalArgumentException("Id de sugestão inválido");
		if (pontos == null || pontos.equals(""))
			throw new IllegalArgumentException("Ponto Inválido");

		// Iterator Pattern
		iteratorIdSessao = this.mapIdSessao.values().iterator();
		while (iteratorIdSessao.hasNext()) { // procura donoDaCarona
			Sessao s = iteratorIdSessao.next();
			if (s.getIdSessao().equals(idSessao)) {
				Usuario donoDaCarona = this.mapIdUsuario.get(s.getIdUser());
				donoDaCarona.responderSugestaoPontoEncontro(idCarona,
						idSugestao, pontos);
			}
		}
	}

	/**
	 * Solicita vaga em carona e sugere ponto encontro.
	 * 
	 * @param idSessao
	 *            : id do usuario q faz a solicitacao/sugestao
	 * @param idCarona
	 *            : carona a qual está destinada a solicitacao
	 * @param ponto
	 *            : ponto de encontro sugerido
	 * @return id da solicitacao feita
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 * @throws CadastroEmCaronaPreferencialException 
	 */
	public Solicitacao solicitarVagaPontoEncontro(Integer idSessao,
			Integer idCarona, String ponto) throws CaronaInvalidaException, CaronaInexistenteException, CadastroEmCaronaPreferencialException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");

		if(idCarona == null)
			throw new CaronaInvalidaException();

		Usuario solicitante = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		if (solicitante == null)
			throw new IllegalArgumentException("Id solicitação inválido");

		Usuario donoDaCarona = null;

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) { // procura pelo donoDaCarona
			Usuario u = iteratorIdUsuario.next();
			if (u.getMapIdCaronasOferecidas().containsKey(idCarona)) {
				donoDaCarona = u;
				if(u.getIdUsuario().equals(solicitante.getIdUsuario()))
					throw new IllegalArgumentException("Você não pode solicitar uma vaga em sua própria carona.");
				break;
			}
		}

		if (donoDaCarona == null)
			throw new IllegalArgumentException("Usuário inexistente");

		Solicitacao solicitacaoFeita = donoDaCarona.solicitarVagaPontoEncontro(idCarona, donoDaCarona,
				solicitante, ponto);
		
		solicitante.addSolicitacaoFeita(solicitacaoFeita);
		
		return solicitacaoFeita;
	}

	/**
	 * Aceita uma solicitacão.
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * @throws IllegalArgumentException 
	 */
	public void aceitarSolicitacaoPontoEncontro(Integer idSessao,
			Integer idSolicitacao) throws CaronaInexistenteException, IllegalArgumentException, EstadoSolicitacaoException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		
		if (idSolicitacao == null)
			throw new IllegalArgumentException("Solicitação inexistente");

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		Solicitacao solicitacao = donoDaCarona
				.aceitarSolicitacaoPontoEncontro(idSolicitacao);

		Integer idUsuarioDonoDaCarona = solicitacao.getDonoDaCarona()
				.getIdUsuario();
		Integer idUsuarioDonoDaSolicitacao = solicitacao.getDonoDaSolicitacao()
				.getIdUsuario();
		Integer idCarona = solicitacao.getIdCarona();

		Carona carona = this.mapIdUsuario.get(idUsuarioDonoDaCarona)
				.getMapIdCaronasOferecidas().get(solicitacao.getIdCarona());
		
		this.mapIdUsuario.get(idUsuarioDonoDaSolicitacao)
				.adicionarIdCaronaPega(idCarona, carona);
	}

	/**
	 * Aceita solicitacao
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws IllegalArgumentException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void aceitarSolicitacao(Integer idSessao, Integer idSolicitacao) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idSolicitacao == null)
			throw new IllegalArgumentException("Solicitação inexistente");

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		Solicitacao solicitacao = donoDaCarona
				.aceitarSolicitacao(idSolicitacao);
		
		Integer idUsuarioDonoDaCarona = solicitacao.getDonoDaCarona()
				.getIdUsuario();
		Integer idUsuarioDonoDaSolicitacao = solicitacao.getDonoDaSolicitacao()
				.getIdUsuario();
		Integer idCarona = solicitacao.getIdCarona();

		Carona carona = this.mapIdUsuario.get(idUsuarioDonoDaCarona)
				.getMapIdCaronasOferecidas().get(solicitacao.getIdCarona());
		
		this.mapIdUsuario.get(idUsuarioDonoDaSolicitacao)
				.adicionarIdCaronaPega(idCarona, carona);
	}

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
	 */
	public Solicitacao solicitarVaga(Integer idSessao, Integer idCarona) throws CaronaInvalidaException, IllegalArgumentException, CadastroEmCaronaPreferencialException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idCarona == null)
			throw new CaronaInvalidaException();

		Usuario solicitante = null;
		// Iterator Pattern
		iteratorIdSessao = this.mapIdSessao.values().iterator();
		while (iteratorIdSessao.hasNext()) { // procura pelo usuario solicitante
												// dentre os usuarios
			Sessao s = iteratorIdSessao.next();
			if (s.getIdSessao().equals(idSessao)) {
				solicitante = this.mapIdUsuario.get(s.getIdUser());
				break;
			}
		}
		if (solicitante == null)
			throw new IllegalArgumentException("Usuário inexistente");

		Usuario donoDaCarona = null;
		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) { // procura pelo donoDaCarona
			Usuario u = iteratorIdUsuario.next();
			if (u.getMapIdCaronasOferecidas().containsKey(idCarona)) {
				donoDaCarona = u;
				if(u.getIdUsuario().equals(solicitante.getIdUsuario()))
					throw new IllegalArgumentException("Você não pode solicitar uma vaga em sua própria carona.");
				break;
			}
		}
		if (donoDaCarona == null)
			throw new IllegalArgumentException("Usuário inexistente");

		Solicitacao solicitacaoFeita =
				donoDaCarona.solicitarVaga(idCarona, donoDaCarona, solicitante);
		
		solicitante.addSolicitacaoFeita(solicitacaoFeita);
		
		return solicitacaoFeita;
	}

	/**
	 * Usuario dono da carona, indicado por idSessao, rejeita solicitacao,
	 * indicada por idSolicitacao.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void rejeitarSolicitacao(Integer idSessao, Integer idSolicitacao) throws CaronaInexistenteException, EstadoSolicitacaoException {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (idSolicitacao == null)
			throw new IllegalArgumentException("Id solicitação inválido");

		// Iterator Pattern
		iteratorIdSessao = this.mapIdSessao.values().iterator();
		while (iteratorIdSessao.hasNext()) { // procura pelo donoDaCarona
			Sessao s = iteratorIdSessao.next();
			if (s.getIdSessao().equals(idSessao)) {
				Usuario donoDaCarona = this.mapIdUsuario.get(s.getIdUser());
				donoDaCarona.rejeitarSolicitacao(idSolicitacao);
				break;
			}
		}
	}

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
	 */
	public void desistirRequisicao(Integer idSessao, Integer idCarona,
			Integer idSolicitacao) throws CaronaInvalidaException, CaronaInexistenteException, EstadoSolicitacaoException {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null )
			throw new CaronaInvalidaException();
		if (idSolicitacao == null )
			throw new IllegalArgumentException("Id solicitação inválido");

		Usuario donoDaSolicitacao = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser()); // O(logm + logn)
		donoDaSolicitacao.desistirRequisicao(idCarona, idSolicitacao);
	}

	/**
	 * Pega usuario associado a sessao indicada por idSessao e pega o perfil
	 * dele.
	 * 
	 * @param idSessao
	 * @param login
	 * @return idPerfil
	 */
	public Usuario visualizarPerfil(Integer idSessao, String login) {
		if (idSessao == null )
			throw new IllegalArgumentException("IdSessao inválido");

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(login))
				return u.visualizarPerfil();
		}
		throw new IllegalArgumentException("Login inválido");

	}

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
	public void reviewVagaEmCarona(Integer idSessao, Integer idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");

		if (idCarona == null )
			throw new CaronaInvalidaException();

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());

		if (donoDaCarona == null)
			throw new UsuarioInexistenteException();

		Integer idCaroneiro = null;

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(loginCaroneiro)) {
				idCaroneiro = u.getIdUsuario();
			}
		}
		if (idCaroneiro == null)
			throw new UsuarioInexistenteException();

		Usuario caroneiro = this.mapIdUsuario.get(idCaroneiro);
		if (!caroneiro.getMapIdCaronasPegas().containsKey(idCarona)) {
			throw new IllegalArgumentException(
					"Usuário não possui vaga na carona.");
		}

		donoDaCarona.reviewVagaEmCarona(idCarona, idCaroneiro, review);
	}

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
	 *            : id da sessao do caroneiro
	 * @param idCarona
	 *            : id da carona pega por ele (caroneiro)
	 * @param review
	 *            : review do caroneiro presente
	 */
	public void reviewCarona(Integer idSessao, Integer idCarona, String review)
			throws UsuarioInexistenteException {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idCarona == null)
			throw new IllegalArgumentException("Carona inválida");
		
		Sessao s = this.mapIdSessao.get(idSessao);

		if (s == null) {
			throw new IllegalArgumentException("Sessão inválida");
		}

		Integer idCaroneiro = mapIdSessao.get(idSessao).getIdUser();

		if (idCaroneiro == null)
			throw new UsuarioInexistenteException();

		Usuario caroneiro = mapIdUsuario.get(idCaroneiro);

		if (!caroneiro.getMapIdCaronasPegas().containsKey(idCarona)) { // garante  que o caroneiro foi aceito pelo dono da carona
			throw new IllegalArgumentException(
					"Usuário não possui vaga na carona.");
		}
		
		
		
		EnumCaronaReview eReview = caroneiro.setReviewCarona(idCaroneiro, idCarona, review);
		if(eReview.equals(EnumCaronaReview.SEGURA_E_TRANQUILA)) {
			Usuario donoDaCarona = getUsuarioAPartirDeIdCarona(idCarona);
			donoDaCarona.addCaroneiroPreferencial(idCaroneiro);
		}
	}
	
	private Usuario getUsuarioAPartirDeIdCarona(Integer idCarona) {
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		Usuario donoDaCarona;
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorIdCarona = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorIdCarona.hasNext()) {
				Carona c = iteratorIdCarona.next();
				if(c.getIdCarona().equals(idCarona)) {
					return donoDaCarona;
				}
			}
		}
		throw new IllegalArgumentException("IdCarona inválido");
	}

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
	 */
	public Carona cadastrarCaronaMunicipal(Integer idSessao, String origem,
			String destino, String cidade, String data, String hora,
			Integer vagas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("IdSessao inválido");
		if (vagas == null || vagas.equals(""))
			throw new IllegalArgumentException("Vagas inválida");

		Usuario donoDaCarona = mapIdUsuario.get(mapIdSessao.get(idSessao)
				.getIdUser());

		if (donoDaCarona == null)
			throw new IllegalArgumentException("Usuário inexistente");

		Carona caronaMunicipal = donoDaCarona.cadastrarCaronaMunicipal(origem,
				destino, cidade, data, hora, vagas, ordemParaCaronas++);
		if (caronaMunicipal != null) {
			atualizaMensagensEmPerfis(caronaMunicipal);
			return caronaMunicipal;
		} else
			throw new IllegalArgumentException("Carona Inválida");
	}

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem as especificacoes requeridas pelo o cliente.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @param origem
	 * @param destino
	 * @return lista de caronas localizadas
	 */
	public List<Carona> localizarCaronaMunicipal(Integer idSessao,
			String cidade, String origem, String destino) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (origem == null
				|| (!origem.equals("") && origem.toUpperCase().equals(origem)))
			throw new IllegalArgumentException("Origem inválida");
		if (destino == null
				|| (!destino.equals("") && destino.toUpperCase()
						.equals(destino)))
			throw new IllegalArgumentException("Destino inválido");

		List<Carona> listaCaronas = new LinkedList<Carona>();
		
		//Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> it = u.localizarCaronaMunicipal(origem, destino, cidade).iterator();
			while (it.hasNext()) {
				Carona carona = it.next();
				listaCaronas.add(carona);
			}
		}
		
		Collections.sort(listaCaronas);

		return listaCaronas;
	}

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem a especificacao requerida pelo o cliente, no caso cidade
	 * da carona.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @return lista de caronas
	 */
	public List<Carona> localizarCaronaMunicipal(Integer idSessao, String cidade) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");

		List<Carona> listaCaronas = new LinkedList<Carona>();
		
		//Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> it = u.localizarCaronaMunicipal(cidade).iterator();
			while (it.hasNext()) {
				Carona carona = it.next();
				listaCaronas.add(carona);
			}
		}
		
		Collections.sort(listaCaronas);
		return listaCaronas;
	}

	/**
	 * Retorna carona do usuario identificado por idSessao. O indexCarona vai
	 * trabalhar com a ordemParaCaronas, jah que o indice vai está na mesma
	 * ordem relativa de inserção das caronas no sistema.
	 * 
	 * @param idSessao
	 * @param indexCarona
	 * @return carona
	 */
	public Carona getCaronaUsuario(Integer idSessao, int indexCarona) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");

		Usuario usuario = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao)
				.getIdUser());
		return usuario.getCaronaUsuario(indexCarona);
	}

	/**
	 * Retorna todas as caronas cadastradas pelo usuario identificado por
	 * idSessao.
	 * 
	 * @param idSessao
	 * @return lista de caronas
	 */
	public List<Carona> getTodasCaronasUsuario(Integer idSessao) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");

		Usuario usuario = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao)
				.getIdUser());
		return usuario.getTodasCaronasUsuario();
	}

	/**
	 * Retorna lista de ids de solicitacoes confirmadas para a carona (idCarona)
	 * oferecida pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista solicitacoes confirmadas
	 */
	public List<Solicitacao> getSolicitacoesConfirmadas(Integer idSessao,
			Integer idCarona) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null)
			throw new IllegalArgumentException("IdCarona inválida");

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		return donoDaCarona.getSolicitacoesConfirmadas(idCarona);
	}

	/**
	 * Retorna lista de solicitacoes pendentes de serem respondidas pelo usuario
	 * dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de solicitacoes
	 * @throws CaronaInvalidaException 
	 */
	public List<Solicitacao> getSolicitacoesPendentes(Integer idSessao,
			Integer idCarona) throws CaronaInvalidaException {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null )
			throw new CaronaInvalidaException();

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		return donoDaCarona.getSolicitacoesPendentes(idCarona);
	}

	/**
	 * Retorna string com o ponto sugerido para idCarona. O usuario identificado
	 * por idSessao deve ser o dono da carona.
	 * 
	 * @param idSessao
	 *            : id da sessao do dono da carona
	 * @param idCarona
	 *            : id da carona referida
	 * @return lista de pontos sugeridos
	 */
	public List<String> getPontosSugeridos(Integer idSessao, Integer idCarona) {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null )
			throw new IllegalArgumentException("IdCarona inválida");

		return procuraCarona(idCarona).getPontosSugeridos();
	}

	/**
	 * Retorna ponto de encontro para a carona identificada por idCarona.
	 * idSessao eh o identificador do usuario dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return string com ponto de encontro
	 * @throws CaronaInvalidaException 
	 */
	public String getPontosEncontro(Integer idSessao, Integer idCarona) throws CaronaInvalidaException {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null )
			throw new CaronaInvalidaException();

		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());
		return donoDaCarona.getPontosEncontro(idCarona);
	}

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
	 * @return interesse
	 * @throws CaronaInvalidaException 
	 */
	public Interesse cadastrarInteresse(Integer idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Usuario donoDoInteresse = this.mapIdUsuario.get(this.mapIdSessao.get(
				idSessao).getIdUser());

		Interesse interesse = donoDoInteresse.cadastrarInteresseUsuario(origem, destino,
				data, horaInicio, horaFim);
		
		Iterator<Carona> itCaronas = buscaCaronasCorrespondentes(interesse).iterator();
		while (itCaronas.hasNext()) {
			Carona carona = itCaronas.next();
			donoDoInteresse.atualizaPerfilUsuarioInteressado(carona,
					getEmailDonoDeCarona(carona));
		}
		return interesse;
	}

	/**
	 * Retorna o email do dono da carona.
	 * 
	 * @param carona
	 * @return emailDonoDaCarona
	 * @throws CaronaInvalidaException 
	 */
	private String getEmailDonoDeCarona(Carona carona) throws CaronaInvalidaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		
		String emailDonoDaCarona = null;

		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> it = u.getMapIdCaronasOferecidas().values().iterator();
			while (it.hasNext()) {
				Carona c = it.next();
				if (c.getIdCarona().equals(carona.getIdCarona())) {
					emailDonoDaCarona = u.getEmail();
					break;
				}
			}
		}
		return emailDonoDaCarona;
	}

	/**
	 * Atualiza mensagens em perfis dos usuarios interessados que tem algum
	 * interesse relacionado com a nova carona cadastrada.
	 * 
	 * Usa padrão Observer.
	 * 
	 * @param idNovaCarona
	 *            : id da nova carona cadastrada no sistema
	 * @throws CaronaInvalidaException 
	 */
	private void atualizaMensagensEmPerfis(Carona novaCarona) throws CaronaInvalidaException {
		if(novaCarona == null)
			throw new CaronaInvalidaException();
		
		for(Usuario u : mapIdUsuario.values()) {
			for(Interesse i : u.getMapIdInteresse().values()) {
				if(i.verificaCorrespondencia(novaCarona)) {
					u.atualizaPerfilUsuarioInteressado(novaCarona, getEmailDonoDeCarona(novaCarona));
				}
			}
		}
	}

	/**
	 * Busca caronas correspondentes ao interesse do usuario, segundo as regras
	 * especificadas pelo cliente.
	 * 
	 * Para os horários o sistema poderá deixar o usuário livre, ou seja, ele
	 * poderá não colocar horaInicio(pegará todos a partir das 0h do dia
	 * especificado ou horaFim(pegará todos até às 11:59 do dia especificado)
	 * que a consulta será realizada. data="" retornará todas as caronas que tem
	 * marcadas da data atual em diante.
	 * 
	 * @param interesse
	 * @return lista caronas
	 */
	private List<Carona> buscaCaronasCorrespondentes(Interesse interesse) {
		List<Carona> listaCaronas = new LinkedList<Carona>();
		
		// Iterator Pattern
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> itCaronas = u.getMapIdCaronasOferecidas().values()
					.iterator();
			while (itCaronas.hasNext()) {
				Carona carona = itCaronas.next();
				if (interesse.verificaCorrespondencia(carona)) {
					listaCaronas.add(carona);
				}
			}
		}
		return listaCaronas;
	}

	/**
	 * Resume as mensagens sobre as caronas sobre as quais o usuario demonstra
	 * interesse.
	 * 
	 * @param idSessao
	 * @return mensagens
	 */
	public List<String> verificarMensagensPerfil(Integer idSessao) {
		if (idSessao == null )
			throw new IllegalArgumentException("Sessão inválida");

		Usuario usuario = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao)
				.getIdUser());
		return usuario.verificarMensagensPerfil();
	}

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
	public boolean enviarEmail(Integer idSessao, String destino, String message)
			throws MessagingException {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		
		Usuario remetente = this.mapIdUsuario.get(this.mapIdSessao
				.get(idSessao).getIdUser());
		return remetente.enviarEmail(destino, message);
	}

	/**
	 * Retorna mapIdSessao.
	 * 
	 * @return mapIdSessao
	 */
	public Map<Integer, Sessao> getMapIdSessao() {
		return this.mapIdSessao;
	}

	/**
	 * Retorna mapIdUsuario.
	 * 
	 * @return mapIdUsuario
	 */
	public Map<Integer, Usuario> getMapIdUsuario() {
		return this.mapIdUsuario;
	}

	/**
	 * Retorna gerenciadorDeDados
	 * 
	 * @return gerenciadorDeDados
	 */
	public GerenciadorDeDados getGerenciadorDeDados() {
		return gerenciadorDeDados;
	}

	/**
	 * Retorna todas as caronas pegas de um usuario.
	 * 
	 * @param idSessao
	 * @return todasCaronasPegas
	 */
	public List<Carona> getTodasCaronasPegas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		List<Carona> caronasPegas = new LinkedList<Carona>();
		
		caronasPegas.addAll(u.getMapIdCaronasPegas().values());
			
		return caronasPegas;
	}
	
	/**
	 * Metodo para adicionar usuarios e caronas automaticamente
	 * ao sistema.
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 */
	public void adicionaUsuarioECaronasAutomaticamente() throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		Adder adder = new Adder(uniqueInstance);
		adder.addElements();
	}

	/**
	 * Modifica a senha de um usuario.
	 * 
	 * @param idSessao
	 * @param novaSenha
	 */
	public void setSenha(Integer idSessao, String novaSenha) {
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		u.setSenha(novaSenha);
		
	}
	
	/**
	 * Retorna mapa de solicitacoes feitas pelo
	 * usuario identificado a partir de idSessao
	 * 
	 * @param idSessao
	 * @return mapa de solicitacoes feitas
	 */
	public Map<Integer, Solicitacao> getMapaSolicitacoesFeitas(Integer idSessao) {
		Usuario u = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao).getIdUser());
		return u.getMapIdSolicitacoesFeitas();
	}
	
	/**
	 * Retorna usuario identificado a partir de idSessao
	 * 
	 * @param idSessao
	 * @return usuario
	 */
	public Usuario getUsuarioAPartirDeIDSessao(Integer idSessao) {
		return this.mapIdUsuario.get(this.mapIdSessao.get(idSessao).getIdUser());
	}
	
	/**
	 * Retorna usuario a partir de idUsuario.
	 * 
	 * @param idUsuario
	 * @return usuario
	 */
	public Usuario getUsuarioAPartirDeIDUsuario(Integer idUsuario) {
		return this.mapIdUsuario.get(idUsuario);
	}
	
	/**
	 * Retorna mapa de sugestoes feitas do usuario
	 * identificado por idSessao
	 * 
	 * @param idSessao
	 * @return mapa de sugestoes
	 */
	public Map<Integer, Sugestao> getMapSugestoesFeitas(Integer idSessao) {
		Usuario u = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao).getIdUser());
		return u.getMapIdSugestoesFeitas();
	}
	
	/**
	 * Modifica o nome de um usuario.
	 * 
	 * @param idSessao
	 * @param novoNome
	 */
	public void setNome(Integer idSessao, String novoNome) {
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		u.setNome(novoNome);
		
	}
	
	/**
	 * Modifica o email de um usuario.
	 * 
	 * @param idSessao
	 * @param novoEmail
	 */
	public void setEmail(Integer idSessao, String novoEmail) {
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		u.setEmail(novoEmail);
		
	}
	
	/**
	 * Modifica o endereco de um usuario.
	 * 
	 * @param idSessao
	 * @param novoEndereco
	 */
	public void setEndereco(Integer idSessao, String novoEndereco) {
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		u.setEndereco(novoEndereco);
		
	}
	
	/**
	 * Modifica o login de um usuario.
	 * 
	 * @param idSessao
	 * @param novoLogin
	 */
	public void setLogin(Integer idSessao, String novoLogin) {
		Sessao s = getMapIdSessao().get(idSessao);
		Usuario u = getMapIdUsuario().get(s.getIdUser());
		u.setLogin(novoLogin);
		
	}
	
	/**
	 * Cadastra uma carona relampago, a qual
	 * so acontece se o minimo de caroneiros for
	 * alcancado até 48h antes da saída da carona.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param minimoCaroneiros
	 * @param minimoCaroneiros2 
	 * @return idCarona
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 */
	public Integer cadastrarCaronaRelampago(Integer idSessao, String origem, 
			String destino, String dataIda, String dataVolta, 
			String hora, Integer vagas, Integer minimoCaroneiros) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDaCarona = this.mapIdUsuario.get(this.mapIdSessao.get(idSessao).getIdUser());
		
		if(donoDaCarona == null)
			throw new UsuarioInexistenteException();
		
		return donoDaCarona.cadastrarCaronaRelampago(donoDaCarona.getIdUsuario(), 
				origem, destino, dataIda, dataVolta, hora, vagas,minimoCaroneiros, ordemParaCaronas++).getIdCarona();
	}
	
	/**
	 * Retorna minimo de caroneiros para a carona
	 * identificada por idCarona.
	 * 
	 * @param idCarona
	 * @return minimo de caroneiros
	 * @throws CaronaInexistenteException 
	 * @throws CaronaInvalidaException 
	 */
	public Integer getMinimoCaroneiros(Integer idCarona) throws CaronaInexistenteException, CaronaInvalidaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		Usuario donoDaCarona;
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorIdCarona = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorIdCarona.hasNext()) {
				Carona c = iteratorIdCarona.next();
				if(c.getIdCarona().equals(idCarona)) {
					return c.getMinimoCaroneiros();
				}
			}
		}
		throw new IllegalArgumentException("IdCarona inválido");
	}
	
	/**
	 * Retorna carona relampago identificada por idCarona
	 * 
	 * @param idCarona
	 * @return carona
	 * @throws CaronaInvalidaException 
	 */
	public Carona getCaronaRelampago(Integer idCarona) throws CaronaInvalidaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		Usuario donoDaCarona;
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorCarona = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorCarona.hasNext()) {
				Carona carona = iteratorCarona.next();
				if(carona.getIdCarona().equals(idCarona)) {
					return carona;
				}
			}
		}
		throw new IllegalArgumentException("IdCarona inválido");
	}
	
	/**
	 * Configura carona identificada por
	 * idCarona para expirada.
	 * 
	 * @param idCarona
	 * @return carona
	 * @throws CaronaInvalidaException 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoCaronaException 
	 * @throws MessagingException 
	 */
	public Carona setCaronaRelampagoExpired(Integer idCarona) throws CaronaInvalidaException, CaronaInexistenteException, MessagingException, EstadoCaronaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		
		Usuario donoDaCarona;
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorCaronas = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorCaronas.hasNext()) {
				Carona caronaRelampago = iteratorCaronas.next();
				if(caronaRelampago.getIdCarona().equals(idCarona)) {
					caronaRelampago.setExpired(true);
					return caronaRelampago;
				}
			}
		}
		throw new IllegalArgumentException("IdCarona inválido");
	}
	
	/**
	 * Define uma carona como preferencial.
	 * 
	 * @param idCarona
	 */
	public void definirCaronaPreferencial(Integer idCarona) {
		boolean flag = true;
		//Iterator pattern
		Usuario donoDaCarona;
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorCaronas = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorCaronas.hasNext()) {
				Carona carona = iteratorCaronas.next();
				if(carona.getIdCarona().equals(idCarona)) {
					donoDaCarona.definirCaronaComoPreferencial(idCarona);
					flag = flag ? true : true;
				}
			}
		}
		if(!flag)
			throw new IllegalArgumentException("IdCarona inválido");
	}
	
	/**
	 * Retorna se uma carona identificada por idCarona eh preferencial.
	 * 
	 * @param idCarona
	 * @return true se carona eh preferencial
	 */
	public boolean isCaronaPreferencial(Integer idCarona) {
		Usuario donoDaCarona;
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			Iterator<Carona> iteratorCaronas = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorCaronas.hasNext()) {
				Carona carona = iteratorCaronas.next();
				if(carona.getIdCarona().equals(idCarona)) {
					return carona.isCaronaPreferencial();
				}
			}
		}
		throw new IllegalArgumentException("IdCarona inválido");
	}
	
	/**
	 * Retorna lista de usuarios preferenciais associada
	 * a carona identificada por idCarona.
	 * 
	 * @param idCarona
	 * @return lista de usuarios
	 * @throws CaronaInexistenteException 
	 */
	public List<Sessao> getUsuariosPreferenciaisCarona(Integer idCarona) throws CaronaInexistenteException {
		List<Integer> listaUsuarios = new LinkedList<Integer>();
		Usuario donoDaCarona;
		iteratorIdUsuario = mapIdUsuario.values().iterator();
		while(iteratorIdUsuario.hasNext()) {
			donoDaCarona = iteratorIdUsuario.next();
			if(donoDaCarona.getMapIdCaronasOferecidas().containsKey(idCarona)) {
				listaUsuarios = donoDaCarona.getListaIdsUsuariosPreferenciais();
			}
		}
		if(listaUsuarios == null)
			throw new UsuarioInexistenteException();
		else
			return getListaIdsSessoesDeUsuarios(listaUsuarios);
		
	}
	
	private List<Sessao> getListaIdsSessoesDeUsuarios(List<Integer> listaUsuarios) {
		List<Sessao> listaIdsSessoes = new LinkedList<Sessao>();
		Iterator<Integer> iteratorUsuarios = listaUsuarios.iterator();
		while(iteratorUsuarios.hasNext()) {
			Integer idUsuario = iteratorUsuarios.next();
			iteratorIdSessao = mapIdSessao.values().iterator();
			while(iteratorIdSessao.hasNext()) {
				Sessao s = iteratorIdSessao.next();
				if(s.getIdUser().equals(idUsuario)) {
					listaIdsSessoes.add(s);
				}
			}
		}
		return listaIdsSessoes;
	}

	/**
	 * Retorna lista de usuarios ranqueados segundo
	 * avaliacoes feitas por outros usuario e registradas
	 * no sistema e que estao com sessao aberta atualmente.
	 * 
	 * @param ordem
	 * @return lista de usuarios
	 */
	public List<Usuario> getRankingUsuarios(String ordem) {
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		List<Usuario> listaUsuarios = new SpecialLinkedListBrackets<Usuario>();
		if(ordem.equals(EnumOrdemParaRanking.CRESCENTE.getOrdem())) {
			while(iteratorIdUsuario.hasNext()) {
				Usuario u = iteratorIdUsuario.next();
				listaUsuarios.add(u);
			}
		}
		Collections.sort(listaUsuarios);
		
		return listaUsuarios;
	}
	
	/**
	 * Retorna lista de usuarios de acordo com nome de usuario fornecido
	 * para busca.
	 * @param nome
	 * @return lista de usuarios
	 */
	public List<Usuario> pesquisaUsuariosNoSistema(String nome){
		iteratorIdUsuario = this.mapIdUsuario.values().iterator();
		List<Usuario> result = new SpecialLinkedListBrackets<Usuario>();
		while(iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if(u.getNome().toLowerCase().contains(nome.toLowerCase())){
				result.add(u);
			}
		}
		return result;
	}

	/**
	 * Deleta interesse do mapa de interesses do
	 * usuario ao qual pertence o interesse.
	 * 
	 * @param idInteresse
	 * @param idInteresse2 
	 */
	public void deletarInteresse(Integer idSessao, Integer idInteresse) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idInteresse == null)
			throw new IllegalArgumentException("Interesse inválido");
		Usuario donoDoInteresse = getUsuarioAPartirDeIDSessao(idSessao);
		donoDoInteresse.deletarInteresse(idInteresse);
	}
	
	/**
	 * Retorna lista de caronas oferecidas
	 * pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @return lista de caronas oferecidas
	 */
	public List<Carona> getListaCaronasOferecidas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasOfercidas();
	}
	
	/**
	 * Retorna lista de carona pegas
	 * pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @return lista de caronas pegas
	 */
	public List<Carona> getListaCaronasPegas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDaSessao = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDaSessao.getListaCaronasPegas();
	}
	
	/**
	 * Retorna lista de caronas confirmadas
	 * ate o momento atual, dentre as caronas oferecidas
	 * por ele.
	 * 
	 * @param idSessao
	 * @return lista de caronas confirmadas
	 */
	public List<Carona> getListaCaronasConfirmadas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasConfirmadas();
	}
	
	/**
	 * Retorna lista de caronas canceladas
	 * ate o momento atual, dentre as caronas oferecidas
	 * por ele.
	 * 
	 * @param idSessao
	 * @return lista de caronas canceladas
	 */
	public List<Carona> getListaCaronasCanceladas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasCanceladas();
	}
	
	/**
	 * Retorna lista de caronas ocorrendo
	 * ate o momento atual, dentre as caronas oferecidas
	 * por ele.
	 * 
	 * @param idSessao
	 * @return lista de caronas ocorrendo
	 */
	public List<Carona> getListaCaronasOcorrendo(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasOcorrendo();
	}
	
	/**
	 * Retorna lista de caronas encerradas
	 * ate o momento atual, dentre as caronas oferecidas
	 * por ele.
	 * 
	 * @param idSessao
	 * @return lista de caronas encerradas
	 */
	public List<Carona> getListaCaronasEncerradas(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasEncerradas();
	}
	
	/**
	 * Retorna lista de caronas expiradas
	 * ate o momento atual, dentre as caronas oferecidas
	 * por ele.
	 * 
	 * @param idSessao
	 * @return lista de caronas expiradas
	 */
	public List<Carona> getListaCaronasExpired(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasExpired();
	}
	
	/**
	 * Retorna lista de caronas comuns do usuario,
	 * identificado por idSessao, dentre as caronas oferecidas por ele.
	 * 
	 * @param idSessao
	 * @return lista caronas comuns
	 */
	public List<Carona> getListaCaronasComuns(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasComuns();
	}
	
	/**
	 * Retorna lista de caronas municipais do usuario,
	 * identificado por idSessao, dentre as caronas oferecidas por ele.
	 * 
	 * @param idSessao
	 * @return lista caronas municipais
	 */
	public List<Carona> getListaCaronasMunicipais(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasMunicipais();
	}
	
	/**
	 * Retorna lista de caronas relampago do usuario,
	 * identificado por idSessao, dentre as caronas oferecidas por ele.
	 * 
	 * @param idSessao
	 * @return lista caronas relampago
	 */
	public List<Carona> getListaCaronasRelampago(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasRelampago();
	}
	
	/**
	 * Retorna lista de caronas preferenciais do usuario,
	 * identificado por idSessao, dentre as caronas oferecidas por ele.
	 * 
	 * @param idSessao
	 * @return lista caronas preferenciais
	 */
	public List<Carona> getListaCaronasPreferenciais(Integer idSessao) {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		Usuario donoDasCaronas = getUsuarioAPartirDeIDSessao(idSessao);
		return donoDasCaronas.getListaCaronasPreferenciais();
	}
	
	/**
	 * Encerra carona apos a carona ser concluida.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @throws CaronaInvalidaException 
	 * @throws EstadoCaronaException 
	 */
	public void encerrarCarona(Integer idSessao, Integer idCarona) throws CaronaInvalidaException, EstadoCaronaException {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idCarona == null)
			throw new CaronaInvalidaException();
		Usuario donoDaCarona = getUsuarioAPartirDeIDSessao(idSessao);
		donoDaCarona.encerrarCarona(idCarona);
	}
	
	/**
	 * Cancela carona identificada por idCarona,
	 * a carona ate este momento estava confirmada
	 * mas nao havia sido concluida.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * @throws EstadoCaronaException 
	 */
	public void cancelarCarona(Integer idSessao, Integer idCarona) throws CaronaInvalidaException, MessagingException, EstadoCaronaException {
		if(idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if(idCarona == null)
			throw new CaronaInvalidaException();
		Usuario donoDaCarona = getUsuarioAPartirDeIDSessao(idSessao);
		donoDaCarona.cancelarCarona(idCarona);
	}
}
