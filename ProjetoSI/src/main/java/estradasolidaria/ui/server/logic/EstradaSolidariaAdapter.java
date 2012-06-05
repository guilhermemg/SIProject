package estradasolidaria.ui.server.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;
import estradasolidaria.ui.server.util.SpecialLinkedListKeys;

public class EstradaSolidariaAdapter {

	private static EstradaSolidariaController sistema = EstradaSolidariaController.getInstance();

	private static volatile EstradaSolidariaAdapter uniqueInstance;

	/**
	 * Retorna unica instancia do adapter do controlador
	 * 
	 * @return instancia
	 */
	public static EstradaSolidariaAdapter getInstance() {
		if (uniqueInstance == null) {
			synchronized (EstradaSolidariaAdapter.class) {
				if (uniqueInstance == null)
					uniqueInstance = new EstradaSolidariaAdapter();
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
		sistema.criarUsuario(login, senha, nome, endereco, email);
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
	 * @return id da carona cadastrada
	 * 
	 * @see Usuario, SistemaCaronas
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Sessão inexistente");
		}

		if (!sistema.getMapIdSessao().containsKey(idSessao2)) {
			throw new IllegalArgumentException("Sessão inexistente");
		}

		Integer vagasInt;
		try {
			vagasInt = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new IllegalArgumentException("Vaga inválida");
		}

		return sistema
				.cadastrarCarona(idSessao2, origem, destino, data, hora,
						vagasInt).getIdCarona().toString();
	}

	/**
	 * Abre sessao para usuario identificado por login e senha.
	 * 
	 * @param login
	 * @param senha
	 * @return id sessao aberta
	 */
	public String abrirSessao(String login, String senha) {
		try {
			return sistema.abrirSessao(login, senha).getIdSessao().toString();
		} catch (UsuarioInexistenteException e2) {
			throw new UsuarioInexistenteException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Login inválido");
		}
	}

	/**
	 * Retorna atributo de um usuario.
	 * 
	 * @param login
	 * @param atributo
	 * @return atributo
	 */
	public Object getAtributoUsuario(String login, String atributo) {
		if (login == null || login.equals("")) {
			throw new IllegalArgumentException("Login inválido");
		}

		// Iterator Pattern
		Iterator<Usuario> iteratorIdUsuario = sistema.getMapIdUsuario()
				.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(login))
				return getAtributo(u, atributo);
		}

		throw new UsuarioInexistenteException();
	}

	private Object getAtributo(Usuario u, String nomeAtributo) {
		if (nomeAtributo == null || nomeAtributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");

		if (nomeAtributo.equals(EnumUsuario.NOME.getNomeAtributo()))
			return u.getNome();
		else if (nomeAtributo.equals(EnumUsuario.LOGIN.getNomeAtributo()))
			return u.getLogin();
		else if (nomeAtributo.equals(EnumUsuario.EMAIL.getNomeAtributo()))
			return u.getEmail();
		else if (nomeAtributo.equals(EnumUsuario.SENHA.getNomeAtributo()))
			return u.getSenha();
		else if (nomeAtributo.equals(EnumUsuario.ENDERECO.getNomeAtributo()))
			return u.getEndereco();
		throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Retorna lista de ids de carona localizadas.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return lista de ids de caronas como string
	 */
	public String localizarCarona(String idSessao, String origem, String destino) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Sessão inexistente");
		}

		List<Carona> listaCaronas = sistema.localizarCarona(idSessao2, origem,
				destino);
		// Iterator Pattern
		Iterator<Carona> it = listaCaronas.iterator();
		List<String> listaIdsCaronas = new SpecialLinkedListKeys<String>();
		while (it.hasNext()) {
			listaIdsCaronas.add(it.next().getIdCarona().toString());
		}
		return listaIdsCaronas.toString();
	}

	/**
	 * Retorna atributo de carona
	 * 
	 * @param idCarona
	 * @param nomeAtributo
	 * @return atributo
	 */
	public Object getAtributoCarona(String idCarona, String nomeAtributo) {
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");

		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new IllegalArgumentException("Item inexistente");
		}

		// Iterator Pattern
		Iterator<Usuario> iteratorIdUsuario = sistema.getMapIdUsuario()
				.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getMapIdCaronasOferecidas().containsKey(idCarona2))
				return getAtributoCarona(u, idCarona2, nomeAtributo);
		}
		throw new ItemInexistenteException();
	}

	/**
	 * Procura atributo na carona especificada e o retorna.
	 * 
	 * @param idCarona
	 * @param nomeAtributo
	 * @return atributo de carona
	 */
	private Object getAtributoCarona(Usuario u, Integer idCarona,
			String nomeAtributo) {
		Carona c = u.getMapIdCaronasOferecidas().get(idCarona);
		if (c == null)
			throw new IllegalArgumentException("IdCarona inválido");
		return getAtributo(c, nomeAtributo);
	}

	/**
	 * Retorna o atributo da carona dado pelo parâmetro. Se o atributo não
	 * existir, então é lançada uma exceção.
	 * 
	 * @param nomeAtributo
	 * @return atributo @
	 */
	private Object getAtributo(Carona c, String nomeAtributo) {
		if (nomeAtributo == null || nomeAtributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");

		if (nomeAtributo.equals(EnumCarona.ORIGEM.getNomeAtributo()))
			return c.getOrigem();
		else if (nomeAtributo.equals(EnumCarona.DESTINO.getNomeAtributo()))
			return c.getDestino();
		else if (nomeAtributo.equals(EnumCarona.DATA.getNomeAtributo()))
			return c.getData();
		else if (nomeAtributo.equals(EnumCarona.HORA.getNomeAtributo()))
			return c.getHora();
		else if (nomeAtributo.equals(EnumCarona.VAGA.getNomeAtributo()))
			return c.getVagas();
		else if (nomeAtributo.equals(EnumCarona.PONTO_ENCONTRO
				.getNomeAtributo()))
			return c.getPontoEncontro();
		else if (nomeAtributo
				.equals(EnumCarona.EH_MUINICIPAL.getNomeAtributo()))
			return c.isMunicipal();
		else
			throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Retorna trajeto da carona.
	 * 
	 * @param idCarona
	 * @return trajeto
	 * @throws TrajetoInexistenteException
	 */
	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException {
		if (idCarona == null)
			throw new IllegalArgumentException("Trajeto Inválida");
		else if (idCarona.equals("")) {
			throw new IllegalArgumentException("Trajeto Inexistente");
		}

		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new IllegalArgumentException("Trajeto Inexistente");
		}

		String[] trajeto = sistema.getTrajeto(idCarona2);
		return trajeto[0] + " - " + trajeto[1];
	}

	/**
	 * Retorna carona.
	 * 
	 * @param idCarona
	 * @return carona
	 */
	public String getCarona(String idCarona) {
		if (idCarona == null)
			throw new IllegalArgumentException("Carona Inválida");
		if (idCarona.equals(""))
			throw new IllegalArgumentException("Carona Inexistente");

		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new IllegalArgumentException("Carona Inexistente");
		}

		return sistema.getCarona(idCarona2).toString();
	}

	/**
	 * Encerra sessao aberta.
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	/**
	 * Torna null todos os objetos do sistema.
	 */
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/**
	 * Persiste e encerra o sistema.
	 */
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	/**
	 * Reinicia o sistema com os dados persistidos no encerramento.
	 */
	public void reiniciarSistema() {
		sistema.reiniciarSistema();
	}

	/**
	 * Retorna atributo da solicitacao a partir do nome do atributo.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return atributo
	 */
	public Object getAtributoSolicitacao(String idSolicitacao, String atributo) {
		if (idSolicitacao == null || idSolicitacao.equals(""))
			throw new IllegalArgumentException("Solicitação inválida");

		Integer idSolicitacao2 = null;
		try {
			idSolicitacao2 = Integer.parseInt(idSolicitacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Iterator Pattern
		Iterator<Usuario> iteratorIdUsuario = sistema.getMapIdUsuario()
				.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			Iterator<Carona> it = u.getMapIdCaronasOferecidas().values()
					.iterator();
			while (it.hasNext()) {
				Carona c = it.next();
				if (c.getMapIdSolicitacao().containsKey(idSolicitacao2)) {
					return getAtributoSolicitacao(c, idSolicitacao2, atributo);
				}
			}
		}
		throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Retorna o atributo de uma solicitação.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return atributoSolicitacao
	 */
	private Object getAtributoSolicitacao(Carona c, Integer idSolicitacao,
			String atributo) {
		Solicitacao s = c.getMapIdSolicitacao().get(idSolicitacao);
		if (s == null)
			throw new IllegalArgumentException("Atributo inexistente");
		return getAtributo(s, atributo);
	}

	/**
	 * Retorna valor de atributo de solicitacao de acordo com parâmetro
	 * fornecido.
	 * 
	 * @param atributo
	 * @return atrbuto
	 */
	private Object getAtributo(Solicitacao s, String atributo) {
		if (atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");

		if (atributo.equals(EnumSolicitacao.ORIGEM.getNomeAtributo()))
			return s.getOrigem();
		else if (atributo.equals(EnumSolicitacao.DESTINO.getNomeAtributo()))
			return s.getDestino();
		else if (atributo.equals(EnumSolicitacao.DONO_CARONA.getNomeAtributo()))
			return s.getDonoDaCarona().getNome();
		else if (atributo.equals(EnumSolicitacao.DONO_SOLICITACAO
				.getNomeAtributo()))
			return s.getDonoDaSolicitacao().getNome();
		else if (atributo.equals(EnumSolicitacao.PONTO_ENCONTRO
				.getNomeAtributo()))
			return s.getPontoEncontro();
		else if (atributo.equals(EnumSolicitacao.ESTADO_SOLICITACAO
				.getNomeAtributo()))
			return s.getEstado();
		else
			throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Sugere ponto de encontro para uma carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return id da sugestao feita
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) {

		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Ponto Inválido"); // "Ponto Inválido"
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException("IdCarona inválido");

		Integer idCarona2 = null;
		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.sugerirPontoEncontro(idSessao2, idCarona2, pontos)
				.getIdSugestao().toString();
	}

	/**
	 * Responde a uma sugestao feita por outro usuario.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 *            : ponto de encontro escolhido ate o momento para a carona
	 */
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		if (idSugestao == null || idSugestao.equals(""))
			throw new IllegalArgumentException("Id de sugestão inválido");

		Integer idSessao2 = null;
		Integer idCarona2 = null;
		Integer idSugestao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
			idSugestao2 = Integer.parseInt(idSugestao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.responderSugestaoPontoEncontro(idSessao2, idCarona2,
				idSugestao2, pontos);
	}

	/**
	 * Solicita vaga e sugere um ponto de encontro para a carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return id de solicitacao
	 * @throws CaronaInvalidaException
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.solicitarVagaPontoEncontro(idSessao2, idCarona2, ponto)
				.getIdSolicitacao().toString();
	}

	/**
	 * Aceita uma solicitacão.
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		if (idSolicitacao == null || idSolicitacao.equals(""))
			throw new IllegalArgumentException("Solicitação inexistente");

		Integer idSessao2 = null, idSolicitacao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idSolicitacao2 = Integer.parseInt(idSolicitacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.aceitarSolicitacaoPontoEncontro(idSessao2, idSolicitacao2);
	}

	/**
	 * Aceita solicitacao
	 * 
	 * @param idSessao
	 *            : id da sessao do usuario dono da carona
	 * @param idSolicitacao
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) {
		if (idSessao == null)
			throw new IllegalArgumentException("Sessão inválida");
		if (idSolicitacao == null)
			throw new IllegalArgumentException("Solicitação inexistente");

		Integer idSessao2 = null, idSolicitacao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idSolicitacao2 = Integer.parseInt(idSolicitacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.aceitarSolicitacao(idSessao2, idSolicitacao2);
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
	 */
	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new CaronaInvalidaException();

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.solicitarVaga(idSessao2, idCarona2).getIdSolicitacao()
				.toString();
	}

	/**
	 * Usuario dono da carona, indicado por idSessao, rejeita solicitacao,
	 * indicada por idSolicitacao.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idSolicitacao == null || idSolicitacao.equals(""))
			throw new IllegalArgumentException("Id solicitação inválido");

		Integer idSessao2 = null, idSolicitacao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idSolicitacao2 = Integer.parseInt(idSolicitacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.rejeitarSolicitacao(idSessao2, idSolicitacao2);
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
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new CaronaInvalidaException();
		if (idSolicitacao == null || idSolicitacao.equals(""))
			throw new IllegalArgumentException("Id solicitação inválido");

		Integer idSessao2 = null, idCarona2 = null, idSolicitacao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
			idSolicitacao2 = Integer.parseInt(idSolicitacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.desistirRequisicao(idSessao2, idCarona2, idSolicitacao2);
	}

	/**
	 * Pega usuario associado a sessao indicada por idSessao e pega o perfil
	 * dele.
	 * 
	 * @param idSessao
	 * @param login
	 * @return idPerfil
	 */
	public String visualizarPerfil(String idSessao, String login) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("IdSessao inválido");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.visualizarPerfil(idSessao2, login).getIdUsuario()
				.toString();
	}

	/**
	 * Pesquisa usuario com login e retorna um atributo do perfil dele.
	 * 
	 * @param login
	 * @param nomeAtributo
	 * @return atributo
	 */
	public Object getAtributoPerfil(String login, String atributo) {
		// Iterator Pattern
		Iterator<Usuario> iteratorIdUsuario = sistema.getMapIdUsuario()
				.values().iterator();
		while (iteratorIdUsuario.hasNext()) {
			Usuario u = iteratorIdUsuario.next();
			if (u.getLogin().equals(login))
				return getAtributoPerfil(u, atributo);
		}

		throw new IllegalArgumentException("Login inválido");
	}

	/**
	 * Retorna atributo de acordo com parametro fornecido.
	 * 
	 * @param atributo
	 * @return atributo
	 */
	private Object getAtributoPerfil(Usuario u, String atributo) {
		if (atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");

		if (atributo.equals(EnumPerfil.NOME.getNomeAtributo()))
			return u.getNome();
		else if (atributo.equals(EnumPerfil.ENDERECO.getNomeAtributo()))
			return u.getEndereco();
		else if (atributo.equals(EnumPerfil.EMAIL.getNomeAtributo()))
			return u.getEmail();
		else if (atributo.equals(EnumPerfil.HISTORICO_DE_CARONAS
				.getNomeAtributo())) {
			return getHistoricoDeCaronas(u);
		} else if (atributo.equals(EnumPerfil.HISTORICO_DE_VAGAS_EM_CARONAS
				.getNomeAtributo())) {
			return getHistoricoDeVagasEmCaronas(u);
		} else if (atributo.equals(EnumPerfil.CARONAS_SEGURAS_E_TRANQUILAS
				.getNomeAtributo())) {
			return u.getCaronasSegurasETranquilas();
		} else if (atributo.equals(EnumPerfil.CARONAS_QUE_NAO_FUNCIONARAM
				.getNomeAtributo())) {
			return u.getCaronasQueNaoFuncionaram();
		} else if (atributo.equals(EnumPerfil.FALTAS_EM_VAGAS_DE_CARONAS
				.getNomeAtributo())) {
			return u.getFaltasEmVagasDeCaronas();
		} else if (atributo.equals(EnumPerfil.PRESENCAS_EM_VAGAS_DE_CARONAS
				.getNomeAtributo())) {
			return u.getPresencasEmVagasDeCaronas();
		}

		throw new IllegalArgumentException("Atributo inexistente");
	}

	private Object getHistoricoDeVagasEmCaronas(Usuario u) {
		List<Integer> historico = new SpecialLinkedListBrackets<Integer>();
		for (Iterator<Carona> iterator = u.getHistoricoDeVagasEmCaronas().iterator(); iterator.hasNext();)
			historico.add(iterator.next().getIdCarona());
		return historico;
	}

	private Object getHistoricoDeCaronas(Usuario u) {
		List<Integer> historico = new SpecialLinkedListBrackets<Integer>();
		for(Iterator<Carona> iterator = u.getHistoricoDeCaronas().iterator(); iterator.hasNext(); )
			historico.add(iterator.next().getIdCarona());
		return historico.toString();
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
	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		if (idCarona == null || idCarona.equals(""))
			throw new CaronaInvalidaException();

		Integer idCarona2 = null;
		Integer idSessao2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.reviewVagaEmCarona(idSessao2, idCarona2, loginCaroneiro, review);
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
	 *            : id do caroneiro
	 * @param idCarona
	 *            : id da carona pega por ele (caroneiro)
	 * @param review
	 *            : review do caroneiro presente
	 */
	public void reviewCarona(String idSessao, String idCarona, String review)
			throws CaronaInexistenteException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException("Carona inválida");

		Integer idCarona2 = null;
		Integer idSessao2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sistema.reviewCarona(idSessao2, idCarona2, review);
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
	 */
	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("IdSessao inválido");
		if (vagas == null || vagas.equals(""))
			throw new IllegalArgumentException("Vagas inválida");

		Integer idSessao2 = null, vagas2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			vagas2 = Integer.parseInt(vagas);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema
				.cadastrarCaronaMunicipal(idSessao2, origem, destino, cidade,
						data, hora, vagas2).getIdCarona().toString();
	}

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
	public List<String> localizarCaronaMunicipal(String idSessao,
			String cidade, String origem, String destino) {

		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Sessão inválida");
		}

		List<Carona> listaCaronas = sistema.localizarCaronaMunicipal(idSessao2,
				cidade, origem, destino);
		List<String> listaIdsCaronas = new SpecialLinkedListBrackets<String>();

		Iterator<Carona> it = listaCaronas.iterator();
		while (it.hasNext()) {
			Carona c = it.next();
			listaIdsCaronas.add(c.getIdCarona().toString());
		}

		return listaIdsCaronas;
	}

	/**
	 * Pesquisa entre as caronas oferecidas por todos os usuarios as que sao
	 * municipais e tem a especificacao requerida pelo o cliente, no caso cidade
	 * da carona.
	 * 
	 * @param idSessao
	 * @param cidade
	 * @return lista de ids de caronas
	 */
	public List<String> localizarCaronaMunicipal(String idSessao, String cidade) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Sessão inválida");
		}

		List<Carona> listaCaronas = sistema.localizarCaronaMunicipal(idSessao2,
				cidade);
		List<String> listaIdsCaronas = new SpecialLinkedListBrackets<String>();

		Iterator<Carona> it = listaCaronas.iterator();
		while (it.hasNext()) {
			Carona c = it.next();
			listaIdsCaronas.add(c.getIdCarona().toString());
		}

		return listaIdsCaronas;
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
	public String getCaronaUsuario(String idSessao, int indexCarona) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.getCaronaUsuario(idSessao2, indexCarona).getIdCarona()
				.toString();
	}

	/**
	 * Retorna todas as caronas cadastradas pelo usuario identificado por
	 * idSessao.
	 * 
	 * @param idSessao
	 * @return lista de ids de caronas como string
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listaIdsCaronas = new SpecialLinkedListKeys<String>();

		// Iterator Pattern
		Iterator<Carona> iteratorIdCaronasOferecidas = sistema
				.getTodasCaronasUsuario(idSessao2).iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			listaIdsCaronas.add(c.getIdCarona().toString());
		}
		return listaIdsCaronas.toString();
	}

	/**
	 * Retorna lista de ids de solicitacoes confirmadas para a carona (idCarona)
	 * oferecida pelo usuario identificado por idSessao.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes confirmadas como string
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException("IdCarona inválida");

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listaSolicitacoesConfirmadas = new LinkedList<String>();
		for (Iterator<Solicitacao> iterator = sistema
				.getSolicitacoesConfirmadas(idSessao2, idCarona2).iterator(); iterator
				.hasNext();) {
			listaSolicitacoesConfirmadas.add(iterator.next().getIdSolicitacao()
					.toString());
		}
		String result = listaSolicitacoesConfirmadas.toString();
		result = result.replace("[", "{");
		result = result.replace("]", "}");

		return result;
	}

	/**
	 * Retorna lista de solicitacoes pendentes de serem respondidas pelo usuario
	 * dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return lista de ids de solicitacoes
	 * @throws CaronaInvalidaException
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new CaronaInvalidaException();

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listaSolicitacoesPendentes = new LinkedList<String>();
		for (Iterator<Solicitacao> iterator = sistema.getSolicitacoesPendentes(
				idSessao2, idCarona2).iterator(); iterator.hasNext();) {
			listaSolicitacoesPendentes.add(iterator.next().getIdSolicitacao()
					.toString());
		}
		String result = listaSolicitacoesPendentes.toString();
		result = result.replace("[", "{");
		result = result.replace("]", "}");

		return result;
	}

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
	public String getPontosSugeridos(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new CaronaInvalidaException();

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String pontos = "";
		int i = 0;
		for (String ponto : sistema.getPontosSugeridos(idSessao2, idCarona2)) {
			if (i == 0) {
				pontos += ponto;
			} else {
				pontos = ", " + ponto;
			}
		}
		return pontos + "";
	}

	/**
	 * Retorna ponto de encontro para a carona identificada por idCarona.
	 * idSessao eh o identificador do usuario dono da carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return string com ponto de encontro
	 */
	public String getPontosEncontro(String idSessao, String idCarona) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if (idCarona == null || idCarona.equals(""))
			throw new IllegalArgumentException("IdCarona inválida");

		Integer idSessao2 = null, idCarona2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String pontos = "";
		int i = 0;
		for (String ponto : sistema.getPontosSugeridos(idSessao2, idCarona2)) {
			if (i == 0) {
				pontos += ponto;
			} else {
				pontos = ", " + ponto;
			}
		}
		return pontos + "";
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
	 * @return idInteresse
	 */
	public String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema
				.cadastrarInteresse(idSessao2, origem, destino, data,
						horaInicio, horaFim).getIdInteresse().toString();
	}

	/**
	 * Resume as mensagens sobre as caronas sobre as quais o usuario demonstra
	 * interesse.
	 * 
	 * @param idSessao
	 * @return mensagens
	 */
	public String verificarMensagensPerfil(String idSessao) {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> listaMensagens = sistema
				.verificarMensagensPerfil(idSessao2);

		String resp = "[";
		Iterator<String> it = listaMensagens.iterator();
		while (it.hasNext()) {
			String mensagem = it.next();
			if (listaMensagens.size() == 1)
				resp += mensagem;
			else
				resp += ", " + mensagem;
		}
		return resp + "]";
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
	public boolean enviarEmail(String idSessao, String destino, String message)
			throws MessagingException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");

		Integer idSessao2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sistema.enviarEmail(idSessao2, destino, message);
	}
}
