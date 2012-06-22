package estradasolidaria.ui.server.logic;

/**
 * Classe que representa o estado de uma solicitacao pendente.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoPendente implements EstadoSolicitacaoInterface {
	
	/**
	 * Tenta modificar o estado de uma Solicitação Pendente para Aceita.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void aceitar(Solicitacao s, Carona carona) throws IllegalArgumentException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		s.setEstado(new EstadoSolicitacaoAceita());
		carona.decrementaNumeroDeVagas();
	}
	
	/**
	 * Tenta modificar o estado de uma Solicitação Pendente para Cancelada.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void cancelar(Solicitacao s, Carona carona) throws CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		s.setEstado(new EstadoSolicitacaoCancelada());
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Pendente para Rejeitada.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void rejeitar(Solicitacao s, Carona carona) throws IllegalArgumentException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		s.setEstado(new EstadoSolicitacaoRejeitada());
	}

	
}
