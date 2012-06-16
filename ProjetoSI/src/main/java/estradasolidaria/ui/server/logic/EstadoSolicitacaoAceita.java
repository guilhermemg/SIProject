package estradasolidaria.ui.server.logic;

public class EstadoSolicitacaoAceita implements EstadoSolicitacaoInterface {

	public void aceitar(Solicitacao s, Carona carona) throws CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitacao inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new IllegalArgumentException("Solicitacao ja foi aceita");
	}

	public void cancelar(Solicitacao s) {
		s.setEstado(new EstadoSolicitacaoCancelada());
		return;
	}

	public void rejeitar(Solicitacao s, Carona carona) {
		return;
	}
}
