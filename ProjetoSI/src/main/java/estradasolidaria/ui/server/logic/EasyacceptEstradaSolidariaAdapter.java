package estradasolidaria.ui.server.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import com.ibm.icu.text.SimpleDateFormat;

import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;
import estradasolidaria.ui.server.util.SpecialLinkedListKeys;

/**
 * Adapter para os testes do framework
 * easyaccept.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EasyacceptEstradaSolidariaAdapter implements AdapterInterface {

	private static EstradaSolidariaController sistema = EstradaSolidariaController.getInstance();

	private static volatile AdapterInterface uniqueInstance;

	/**
	 * Retorna unica instancia do adapter do controlador
	 * 
	 * @return instancia
	 */
	public static AdapterInterface getInstance() {
		if (uniqueInstance == null) {
			synchronized (EasyacceptEstradaSolidariaAdapter.class) {
				if (uniqueInstance == null)
					uniqueInstance = new EasyacceptEstradaSolidariaAdapter();
			}
		}
		return uniqueInstance;
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#criarUsuario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#cadastrarCarona(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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
		
		return sistema.cadastrarCarona(idSessao2, origem, destino, data, hora,
				vagasInt).getIdCarona().toString();
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#abrirSessao(java.lang.String, java.lang.String)
	 */
	@Override
	public String abrirSessao(String login, String senha) {
		try {
			return sistema.abrirSessao(login, senha).getIdSessao().toString();
		} catch (UsuarioInexistenteException e2) {
			throw new UsuarioInexistenteException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Login inválido");
		}
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtributoUsuario(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#localizarCarona(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtributoCarona(java.lang.String, java.lang.String)
	 */
	@Override
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
		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		
		if (nomeAtributo.equals(EnumCarona.ORIGEM.getNomeAtributo()))
			return c.getOrigem();
		else if (nomeAtributo.equals(EnumCarona.DESTINO.getNomeAtributo()))
			return c.getDestino();
		else if (nomeAtributo.equals(EnumCarona.DATA.getNomeAtributo()))
			return formatterData.format(c.getData().getTime());
		else if (nomeAtributo.equals(EnumCarona.HORA.getNomeAtributo()))
			return formatterHora.format(c.getHora().getTime());
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getTrajeto(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getCarona(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#encerrarSessao(java.lang.String)
	 */
	@Override
	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#zerarSistema()
	 */
	@Override
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#encerrarSistema()
	 */
	@Override
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#reiniciarSistema()
	 */
	@Override
	public void reiniciarSistema() {
		sistema.reiniciarSistema();
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtributoSolicitacao(java.lang.String, java.lang.String)
	 */
	@Override
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
		Iterator<Usuario> itUsuarios = sistema.getMapIdUsuario()
				.values().iterator();
		while (itUsuarios.hasNext()) {
			Usuario u = itUsuarios.next();
			Iterator<Carona> itCaronas = u.getMapIdCaronasOferecidas().values().iterator();
			while (itCaronas.hasNext()) {
				Carona c = itCaronas.next();
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
			throw new IllegalArgumentException("Solicitacao inexistente");
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
		else if (atributo.equals(EnumSolicitacao.DONO_SOLICITACAO.getNomeAtributo()))
			return s.getDonoDaSolicitacao().getNome();
		else if (atributo.equals(EnumSolicitacao.PONTO_ENCONTRO.getNomeAtributo()))
			return s.getPontoEncontro();
		else if (atributo.equals(EnumSolicitacao.ESTADO_SOLICITACAO.getNomeAtributo()))
			return s.getEstado();
		else
			throw new IllegalArgumentException("Atributo inexistente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#sugerirPontoEncontro(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#responderSugestaoPontoEncontro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#solicitarVagaPontoEncontro(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInvalidaException, CaronaInexistenteException {
		if (idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if(idCarona == null || idCarona.equals(""))
			throw new CaronaInexistenteException();
		
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#aceitarSolicitacaoPontoEncontro(java.lang.String, java.lang.String)
	 */
	@Override
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws CaronaInexistenteException, IllegalArgumentException, EstadoSolicitacaoException {
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#aceitarSolicitacao(java.lang.String, java.lang.String)
	 */
	@Override
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
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

		sistema.aceitarSolicitacao(idSessao2, idSolicitacao2);
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#solicitarVaga(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#rejeitarSolicitacao(java.lang.String, java.lang.String)
	 */
	@Override
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws CaronaInexistenteException, EstadoSolicitacaoException {
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#desistirRequisicao(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInvalidaException, CaronaInexistenteException, EstadoSolicitacaoException {
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#visualizarPerfil(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtributoPerfil(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#reviewVagaEmCarona(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#reviewCarona(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#cadastrarCaronaMunicipal(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#localizarCaronaMunicipal(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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
		List<String> listaIdsCaronas = new SpecialLinkedListKeys<String>();

		Iterator<Carona> it = listaCaronas.iterator();
		while (it.hasNext()) {
			Carona c = it.next();
			listaIdsCaronas.add(c.getIdCarona().toString());
		}

		return listaIdsCaronas;
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#localizarCaronaMunicipal(java.lang.String, java.lang.String)
	 */
	@Override
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
		List<String> listaIdsCaronas = new SpecialLinkedListKeys<String>();

		Iterator<Carona> it = listaCaronas.iterator();
		while (it.hasNext()) {
			Carona c = it.next();
			listaIdsCaronas.add(c.getIdCarona().toString());
		}

		return listaIdsCaronas;
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getCaronaUsuario(java.lang.String, int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getTodasCaronasUsuario(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getSolicitacoesConfirmadas(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getSolicitacoesPendentes(java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getPontosSugeridos(java.lang.String, java.lang.String)
	 */
	@Override
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

		String pontos = "[";
		int i = 0;
		for (String ponto : sistema.getPontosSugeridos(idSessao2, idCarona2)) {
			if (i == 0) {
				pontos += ponto;
			} else {
				pontos = ", " + ponto;
			}
		}
		return pontos + "]";
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getPontosEncontro(java.lang.String, java.lang.String)
	 */
	@Override
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

		String pontos = "[";
		int i = 0;
		for (String ponto : sistema.getPontosSugeridos(idSessao2, idCarona2)) {
			if (i == 0) {
				pontos += ponto;
			} else {
				pontos = ", " + ponto;
			}
		}
		return pontos + "]";
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#cadastrarInteresse(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#verificarMensagensPerfil(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#enviarEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
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
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#cadastrarCaronaRelampago(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer cadastrarCaronaRelampago(String idSessao, String origem, String destino,
			String data, String hora, String minimoCaroneiros) {
		if(idSessao == null || idSessao.equals(""))
			throw new IllegalArgumentException("Sessão inválida");
		if(minimoCaroneiros == null || minimoCaroneiros.equals(""))
			throw new IllegalArgumentException("Minimo Caroneiros inválido");
		
		Integer idSessao2 = null, minimoCaroneiros2 = null;
		try {
			idSessao2 = Integer.parseInt(idSessao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Sessão inexistente");
		}
		try {
			minimoCaroneiros2 = Integer.parseInt(minimoCaroneiros);
		} catch (Exception e) {
			throw new IllegalArgumentException("Minimo Caroneiros inválido");
		}
		
		return sistema.cadastrarCaronaRelampago(idSessao2, origem, destino, data, hora, minimoCaroneiros2);
	}
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtibutoCaronaRelampago(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Object getAtributoCaronaRelampago(String idCarona, String atributo) 
			throws CaronaInvalidaException, CaronaInexistenteException {
		if (idCarona == null)
			throw new IllegalArgumentException("Identificador do carona é inválido");
		if(idCarona.equals(""))
			throw new IllegalArgumentException("Identificador do carona é inválido");

		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new IllegalArgumentException("Item inexistente");
		}
		
		// Iterator Pattern
		Iterator<Usuario> itUsuarios = sistema.getMapIdUsuario()
				.values().iterator();
		while (itUsuarios.hasNext()) {
			Usuario u = itUsuarios.next();
			Iterator<Carona> itCaronas = u.getMapIdCaronasOferecidas().values().iterator();
			while (itCaronas.hasNext()) {
				Carona c = itCaronas.next();
				if (c.getIdCarona().equals(idCarona2)) {
					return getAtributoCaronaRelampago(c, atributo);
				}
			}
		}
		throw new IllegalArgumentException("Atributo inexistente");
	}
	
	private Object getAtributoCaronaRelampago(Carona c, String atributo) {
		if (atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");
		
		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		
		if (atributo.equals(EnumCarona.ORIGEM.getNomeAtributo()))
			return c.getOrigem();
		else if (atributo.equals(EnumCarona.DESTINO.getNomeAtributo()))
			return c.getDestino();
		else if (atributo.equals(EnumCarona.DATA.getNomeAtributo()))
			return formatterData.format(c.getData().getTime());
		else if (atributo.equals(EnumCarona.HORA.getNomeAtributo()))
			return formatterHora.format(c.getHora().getTime());
		else if (atributo.equals(EnumCarona.PONTO_ENCONTRO.getNomeAtributo()))
			return c.getPontoEncontro();
		else if (atributo.equals(EnumCarona.MINIMO_CARONEIROS.getNomeAtributo()))
			return c.getMinimoCaroneiros();
		else if(atributo.equals(EnumCarona.EXPIRED.getNomeAtributo()))
			return c.getExpired();
		else
			throw new IllegalArgumentException("Atributo inexistente");
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getMinimoCaroneiros(java.lang.Integer)
	 */
	@Override
	public Integer getMinimoCaroneiros(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		
		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sistema.getMinimoCaroneiros(idCarona2);
	}
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getCaronaRelampago(java.lang.Integer)
	 */
	@Override
	public Carona getCaronaRelampago(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		
		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new IllegalArgumentException("Carona Inexistente");
		}
		return sistema.getCaronaRelampago(idCarona2);
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#setCaronaRelampagoExpired(java.lang.Integer)
	 */
	@Override
	public Integer setCaronaRelampagoExpired(String idCarona) throws CaronaInvalidaException, CaronaInexistenteException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sistema.setCaronaRelampagoExpired(idCarona2).getIdCarona();
	}
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getAtributoExpired(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Object getAtributoExpired(String idExpired, String atributo) throws CaronaInvalidaException, CaronaInexistenteException {
		if(idExpired == null)
			throw new CaronaInvalidaException();
		if(idExpired.equals(""))
			throw new CaronaInvalidaException();
		
		Integer idExpired2 = null;
		try {
			idExpired2 = Integer.parseInt(idExpired);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<Usuario> iteratorUsuarios = sistema.getMapIdUsuario().values().iterator();
		Usuario donoDaCarona;
		while(iteratorUsuarios.hasNext()) {
			donoDaCarona = iteratorUsuarios.next();
			Iterator<Carona> iteratorCaronas = donoDaCarona.getMapIdCaronasOferecidas().values().iterator();
			while(iteratorCaronas.hasNext()) {
				Carona caronaRelampago = iteratorCaronas.next();
				if(caronaRelampago.getIdCarona().equals(idExpired2)) {
					return getAtributoExpired(caronaRelampago, atributo);
				}
			}
		}
		throw new IllegalArgumentException("IdExpired invalido");
	}
	
	private Object getAtributoExpired(Carona caronaRelampago, String atributo) throws CaronaInexistenteException {
		if(caronaRelampago == null)
			throw new CaronaInexistenteException();
		if(atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inválido");
		else if(atributo.equals(EnumExpired.EMAIL_TO.getNomeAtributo()))
			return caronaRelampago.getEmailTo().toString();
		throw new IllegalArgumentException("Atributo inexistente");
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#definirCaronaPreferencial(java.lang.Integer)
	 */
	@Override
	public void definirCaronaPreferencial(String idCarona) throws CaronaInvalidaException, CaronaInexistenteException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sistema.definirCaronaPreferencial(idCarona2);
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#isCaronaPreferencial(java.lang.Integer)
	 */
	@Override
	public boolean isCaronaPreferencial(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException {
		if(idCarona == null)
			throw new CaronaInvalidaException();
		if(idCarona.equals(""))
			throw new CaronaInexistenteException();
		Integer idCarona2 = null;
		try {
			idCarona2 = Integer.parseInt(idCarona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sistema.isCaronaPreferencial(idCarona2);
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.AdapterInterface#getUsuariosPreferenciaisCarona(java.lang.Integer)
	 */
	@Override
	public List<Usuario> getUsuariosPreferenciaisCarona(String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}
}
