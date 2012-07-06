package estradasolidaria.ui.server.logic;

import java.io.Serializable;

/**
 * Classe que representa o estado de uma solicitacao aceita.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoAceita implements EstadoSolicitacaoInterface, Serializable {
	
	private static final long serialVersionUID = 32374250147676222L;

	/**
	 * Tenta modificar o estado de uma Solicitação Aceita para Aceita.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void aceitar(Solicitacao s, Carona carona) throws CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitacao inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new IllegalArgumentException("Solicitacao ja foi aceita");
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Aceita para Cancelada,
	 * como a solicitacao eh cancelada, o numero de vagas da carona eh 
	 * incrementado.
	 * 
	 * @throws EstadoSolicitacaoException 
	 * @throws CaronaInexistenteException 
	 */
	public void cancelar(Solicitacao s, Carona carona) throws EstadoSolicitacaoException, CaronaInexistenteException {
		if(s == null)
			throw new IllegalArgumentException("Solicitacao inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		s.setEstado(new EstadoSolicitacaoCancelada());
		carona.incrementaNumeroDeVagas();
	}

	/**
	 * Tenta modificar o estado de uma Solicitação Aceita para Rejeitada.
	 * 
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public void rejeitar(Solicitacao s, Carona carona) throws CaronaInexistenteException, EstadoSolicitacaoException {
		if(s == null)
			throw new IllegalArgumentException("Solicitacao inexistente");
		if(carona == null)
			throw new CaronaInexistenteException();
		throw new EstadoSolicitacaoException("Uma solicitação aceita não pode ser mais rejeitada.");
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoSolicitacaoInterface#getEnumNomeDoEstadoDaSolicitacao()
	 */
	@Override
	public EnumNomeDoEstadoDaSolicitacao getEnumNomeDoEstadoDaSolicitacao() {
		return EnumNomeDoEstadoDaSolicitacao.ACEITA;
	}
}
