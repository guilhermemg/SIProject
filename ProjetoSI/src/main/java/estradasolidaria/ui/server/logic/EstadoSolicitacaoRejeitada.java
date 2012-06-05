package estradasolidaria.ui.server.logic;

/**
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoRejeitada implements EstadoSolicitacaoInterface {

	/**
	 * Modifica o estado de uma Solicitação Rejeitada para Aceita.
	 */
	public void aceitar(Solicitacao s, Carona carona) throws IllegalArgumentException {
		s.setEstado(new EstadoSolicitacaoAceita());
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Rejeitada para Cancelada,
	 * porém essa operação não é possível, pois a Solicitação já está Rejeitada.
	 */
	public void cancelar(Solicitacao s) {
		return;
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Rejeitada para Rejeitada,
	 * porém essa operação não é possível, pois a Solicitação já está Rejeitada.
	 */
	public void rejeitar(Solicitacao s, Carona carona) {
		throw new IllegalArgumentException("Solicitação inexistente");
	}

}