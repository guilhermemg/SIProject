package estradasolidaria.ui.server.logic;

/**
 * Classe que representa o estado de uma solicitacao rejeitada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoRejeitada implements EstadoSolicitacaoInterface {

	/**
	 * Tenta modificar o estado de uma Solicitação Rejeitada para Aceita.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void aceitar(Solicitacao s, Carona carona) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação rejeitada não pode ser mais aceita");
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Rejeitada para Cancelada,
	 * porém essa operação não é possível, pois a Solicitação já está Rejeitada.
	 * 
	 * @throws EstadoSolicitacaoException 
	 * @throws CaronaInexistenteException 
	 */
	public void cancelar(Solicitacao s, Carona carona) throws EstadoSolicitacaoException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação rejeitada não pode ser cancelada");
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Rejeitada para Rejeitada,
	 * porém essa operação não é possível, pois a Solicitação já está Rejeitada.
	 * 
	 * @throws CaronaInexistenteException 
	 */
	public void rejeitar(Solicitacao s, Carona carona) throws CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new IllegalArgumentException("Solicitação inexistente");
	}

}