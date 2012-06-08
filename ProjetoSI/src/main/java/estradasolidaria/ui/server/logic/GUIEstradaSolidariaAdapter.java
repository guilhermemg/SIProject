package estradasolidaria.ui.server.logic;

import java.util.List;

import javax.mail.MessagingException;

public class GUIEstradaSolidariaAdapter implements AdapterInterface {
	private EstradaSolidariaController controller = EstradaSolidariaController.getInstance();
	
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
			String ponto) throws CaronaInvalidaException {
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
