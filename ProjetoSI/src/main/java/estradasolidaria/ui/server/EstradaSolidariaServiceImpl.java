package estradasolidaria.ui.server;

import java.util.List;

import javax.mail.MessagingException;

import estradasolidaria.ui.client.EstradaSolidariaService;
import estradasolidaria.ui.server.logic.CaronaInexistenteException;
import estradasolidaria.ui.server.logic.CaronaInvalidaException;
import estradasolidaria.ui.server.logic.TrajetoInexistenteException;
import estradasolidaria.ui.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EstradaSolidariaServiceImpl extends RemoteServiceServlet implements
		EstradaSolidariaService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String abrirSessao(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAtributoUsuario(String login, String atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String localizarCarona(String idSessao, String origem, String destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAtributoCarona(String idCarona, String nomeAtributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCarona(String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encerrarSessao(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zerarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reiniciarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAtributoSolicitacao(String idSolicitacao, String atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInvalidaException,
			CaronaInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String visualizarPerfil(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAtributoPerfil(String login, String atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewCarona(String idSessao, String idCarona, String review)
			throws CaronaInexistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(String idSessao,
			String cidade, String origem, String destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(String idSessao, String cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaronaUsuario(String idSessao, int indexCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTodasCaronasUsuario(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSolicitacoesPendentes(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosSugeridos(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosEncontro(String idSessao, String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verificarMensagensPerfil(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean enviarEmail(String idSessao, String destino, String message)
			throws MessagingException {
		// TODO Auto-generated method stub
		return false;
	}
}
