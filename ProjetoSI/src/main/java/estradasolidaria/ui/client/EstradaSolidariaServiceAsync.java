package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EstradaSolidariaServiceAsync {

	void abrirSessao(String login, String senha, AsyncCallback<Integer> callback);

	void aceitarSolicitacao(Integer idSessao, Integer idSolicitacao,
			AsyncCallback<Void> callback);

	void aceitarSolicitacaoPontoEncontro(Integer idSessao, Integer idSolicitacao,
			AsyncCallback<Void> callback);

	void cadastrarCarona(Integer idSessao, String origem, String destino,
			String data, String hora, String vagas,
			AsyncCallback<String> callback);

	void cadastrarCaronaMunicipal(Integer idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas, AsyncCallback<String> callback);

	void cadastrarInteresse(Integer idSessao, String origem, String destino,
			String data, String horaInicio, String horaFim,
			AsyncCallback<String> callback);

	void criarUsuario(String login, String senha, String nome, String endereco,
			String email, AsyncCallback<Void> callback);

	void desistirRequisicao(Integer idSessao, Integer idCarona,
			Integer idSolicitacao, AsyncCallback<Void> callback);

	void encerrarSessao(String login, AsyncCallback<Void> callback);

	void encerrarSistema(AsyncCallback<Void> callback);

	void enviarEmail(Integer idSessao, String destino, String message,
			AsyncCallback<Boolean> callback);

	void getCarona(Integer idCarona, AsyncCallback<List<String>> callback);

	void getCaronaUsuario(Integer idSessao, int indexCarona,
			AsyncCallback<String> callback);

	void getPontosEncontro(Integer idSessao, Integer idCarona,
			AsyncCallback<String> callback);

	void getPontosSugeridos(Integer idSessao, Integer idCarona,
			AsyncCallback<String> callback);

	void getSolicitacoesFeitasConfirmadas(Integer idSessao, AsyncCallback<List<List<String>>> callback);

	void getSolicitacoesFeitasPendentes(Integer idSessao,
			AsyncCallback<List<List<String>>> callback);

	void getTodasCaronasUsuario(Integer idSessao, AsyncCallback<List<GWTCarona>> asyncCallback);

	void getTrajeto(Integer idCarona, AsyncCallback<String> callback);

	void localizarCarona(Integer idSessao, String origem, String destino,
			AsyncCallback<List<GWTCarona>> callback);

	void localizarCaronaMunicipal(Integer idSessao, String cidade,
			AsyncCallback<List<String>> callback);

	void localizarCaronaMunicipal(Integer idSessao, String cidade,
			String origem, String destino, AsyncCallback<List<String>> callback);

	void rejeitarSolicitacao(Integer idSessao, Integer idSolicitacao,
			AsyncCallback<Void> callback);

	void reiniciarSistema(AsyncCallback<Void> callback);

	void responderSugestaoPontoEncontro(Integer idSessao, Integer idCarona,
			String idSugestao, String pontos, AsyncCallback<Void> callback);

	void reviewCarona(Integer idSessao, Integer idCarona, String review,
			AsyncCallback<Void> callback);

	void reviewVagaEmCarona(Integer idSessao, Integer idCarona,
			String loginCaroneiro, String review, AsyncCallback<Void> callback);

	void solicitarVaga(Integer idSessao, Integer idCarona,
			AsyncCallback<String> callback);

	void solicitarVagaPontoEncontro(Integer idSessao, Integer idCarona,
			String ponto, AsyncCallback<String> callback);

	void sugerirPontoEncontro(Integer idSessao, Integer idCarona,
			String pontos, AsyncCallback<Void> callback);

	void verificarMensagensPerfil(Integer idSessao,
			AsyncCallback<String> callback);

	void visualizarPerfil(Integer idSessao, String login,
			AsyncCallback<String> callback);

	void zerarSistema(AsyncCallback<Void> callback);

	void getTodasCaronasPegas(Integer idSessao,
			AsyncCallback<List<GWTCarona>> asyncCallback);

	void editarSenha(Integer idSessaoAberta, String novaSenha,
			AsyncCallback<Void> asyncCallback);

	void getCaroneiros(Integer idSessaoAberta,
			String idCarona,
			AsyncCallback<List<List<String>>> asyncCallback);
	
	void editarLogin(Integer idSessaoAberta, String novoLogin, 
			AsyncCallback<Void> asyncCallback);
	
	void editarNome(Integer idSessaoAberta, String novoNome, 
			AsyncCallback<Void> asyncCallback);
	
	void editarEmail(Integer idSessaoAberta, String novoEmail, 
			AsyncCallback<Void> asyncCallback);
	
	void editarEndereco(Integer idSessaoAberta, String novoEndereco, 
			AsyncCallback<Void> asyncCallback);
	
	void getUsuario(Integer idSessao, AsyncCallback<String[]> asyncCallback);

	void getSolicitacoes(Integer idSessao, Integer idCarona,
			AsyncCallback<List<String[]>> asyncCallback);

	void getInteresses(Integer idSessao,
			AsyncCallback<List<GWTInteresse>> callback);
	
	void pesquisaUsuariosNoSistema(String nome,
			AsyncCallback<List<GWTUsuario>> callback);
	
	void getUsuarioNoSistema(Integer idUsuario,
			AsyncCallback<List<String>> callback);

	void deletarInteresse(Integer idSessao, Integer idInteresse,
			AsyncCallback<Void> asyncCallback);

	void encerrarCarona(Integer idSessao, Integer idCaronaEscolhida,
			AsyncCallback<Void> asyncCallback);

	void cancelarCarona(Integer idSessao, Integer idCaronaEscolhida,
			AsyncCallback<Void> asyncCallback);

	void marcarCaronaComoPreferencial(Integer idSessao,
			Integer idCaronaEscolhida, AsyncCallback<Void> asyncCallback);
	
	void getListaDeMensagens(Integer idSessao, AsyncCallback<List<GWTMensagem>> asyncCallback);
	
	void marcarMensagemComoLida(Integer idSessao, Integer idMensagem, AsyncCallback<Void> asyncCallback);
	
	void apagarMensagem(Integer idSessao, Integer idMensagem, AsyncCallback<Void> asyncCallback);
	
	void encerrarSessao(Integer idSessao, AsyncCallback<Void> asyncCallback);
	
	void convidarAmigo(Integer idSessao, String emailDoAmigo, AsyncCallback<Boolean> asyncCallback);
}
