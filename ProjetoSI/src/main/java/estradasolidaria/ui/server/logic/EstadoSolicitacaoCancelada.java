package estradasolidaria.ui.server.logic;

/**
 * Classe que representa o estado de uma solicitacao cancelada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoCancelada implements EstadoSolicitacaoInterface {
	
	/**
	 * Tenta modificar o estado de uma Solicitação Cancelada para Aceita.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void aceitar(Solicitacao s, Carona carona) throws EstadoSolicitacaoException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação cancelada não pode mais ser aceita.");
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Cancelada para Cancelada.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void cancelar(Solicitacao s, Carona carona) throws EstadoSolicitacaoException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação cancelada não pode ser novamente cancelada");
	}
	
	/**
	 * Tenta modificar o estado de uma Solicitação Cancelada para Rejeitada.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void rejeitar(Solicitacao s, Carona carona) throws CaronaInexistenteException, EstadoSolicitacaoException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação cancelada não pode ser rejeitada.");
	}

}
