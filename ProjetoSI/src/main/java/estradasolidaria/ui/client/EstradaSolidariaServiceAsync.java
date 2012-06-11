package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EstradaSolidariaServiceAsync {

	void abrirSessao(String login, String senha, AsyncCallback<Integer> callback);

	void aceitarSolicitacao(String idSessao, String idSolicitacao,
			AsyncCallback<Void> callback);

	void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao,
			AsyncCallback<Void> callback);

	void cadastrarCarona(Integer idSessao, String origem, String destino,
			String data, String hora, String vagas,
			AsyncCallback<String> callback);

	void cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas, AsyncCallback<String> callback);

	void cadastrarInteresse(String idSessao, String origem, String destino,
			String data, String horaInicio, String horaFim,
			AsyncCallback<String> callback);

	void criarUsuario(String login, String senha, String nome, String endereco,
			String email, AsyncCallback<Void> callback);

	void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao, AsyncCallback<Void> callback);

	void encerrarSessao(String login, AsyncCallback<Void> callback);

	void encerrarSistema(AsyncCallback<Void> callback);

	void enviarEmail(String idSessao, String destino, String message,
			AsyncCallback<Boolean> callback);

	void getCarona(String idCarona, AsyncCallback<String> callback);

	void getCaronaUsuario(String idSessao, int indexCarona,
			AsyncCallback<String> callback);

	void getPontosEncontro(String idSessao, String idCarona,
			AsyncCallback<String> callback);

	void getPontosSugeridos(String idSessao, String idCarona,
			AsyncCallback<String> callback);

	void getSolicitacoesConfirmadas(Integer idSessao, Integer idCarona,
			AsyncCallback<List<List<String>>> callback);

	void getSolicitacoesPendentes(Integer idSessao, Integer idCarona,
			AsyncCallback<List<List<String>>> callback);

	void getTodasCaronasUsuario(Integer idSessao, AsyncCallback<List<List<String>>> callback);

	void getTrajeto(String idCarona, AsyncCallback<String> callback);

	void localizarCarona(String idSessao, String origem, String destino,
			AsyncCallback<String> callback);

	void localizarCaronaMunicipal(String idSessao, String cidade,
			AsyncCallback<List<String>> callback);

	void localizarCaronaMunicipal(String idSessao, String cidade,
			String origem, String destino, AsyncCallback<List<String>> callback);

	void rejeitarSolicitacao(String idSessao, String idSolicitacao,
			AsyncCallback<Void> callback);

	void reiniciarSistema(AsyncCallback<Void> callback);

	void responderSugestaoPontoEncontro(String idSessao, String idCarona,
			String idSugestao, String pontos, AsyncCallback<Void> callback);

	void reviewCarona(String idSessao, String idCarona, String review,
			AsyncCallback<Void> callback);

	void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review, AsyncCallback<Void> callback);

	void solicitarVaga(String idSessao, String idCarona,
			AsyncCallback<String> callback);

	void solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto, AsyncCallback<String> callback);

	void sugerirPontoEncontro(String idSessao, String idCarona, String pontos,
			AsyncCallback<String> callback);

	void verificarMensagensPerfil(String idSessao,
			AsyncCallback<String> callback);

	void visualizarPerfil(String idSessao, String login,
			AsyncCallback<String> callback);

	void zerarSistema(AsyncCallback<Void> callback);

	void getTodasCaronasPegas(Integer idSessao,
			AsyncCallback<List<List<String>>> asyncCallback);
}
