package estradasolidaria.ui.server.logic;

public class EstadoSolicitacaoAceita implements EstadoSolicitacaoInterface {

	public void aceitar(Solicitacao s, Carona carona) {
		throw new IllegalArgumentException("Solicitação inexistente");
	}

	public void cancelar(Solicitacao s) {
		return;
	}

	public void rejeitar(Solicitacao s, Carona carona) {
		return;

	}

}
