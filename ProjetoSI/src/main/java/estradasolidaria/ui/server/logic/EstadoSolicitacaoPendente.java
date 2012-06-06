package estradasolidaria.ui.server.logic;

public class EstadoSolicitacaoPendente implements EstadoSolicitacaoInterface {

	public void aceitar(Solicitacao s, Carona carona) throws IllegalArgumentException {
		s.setEstado(new EstadoSolicitacaoAceita());
		carona.decrementaNumeroDeVagas();
	}

	public void cancelar(Solicitacao s) {
		s.setEstado(new EstadoSolicitacaoCancelada());
	}

	public void rejeitar(Solicitacao s, Carona carona) throws IllegalArgumentException {
		s.setEstado(new EstadoSolicitacaoRejeitada());
		carona.aumentaVaga();
	}

	
}
