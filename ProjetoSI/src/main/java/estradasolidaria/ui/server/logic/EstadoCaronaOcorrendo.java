package estradasolidaria.ui.server.logic;

import javax.mail.MessagingException;

/**
 * Classe que representa o estado de uma carona que esta ocorrendo
 * neste momento.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaOcorrendo implements EstadoCaronaInterface {

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#confirmar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void confirmar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona que já está ocorrendo não pode ser confirmada.");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#cancelar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void cancelar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, MessagingException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona que está ocorrendo não pode mais ser cancelada.");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#realizar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void realizar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona que já está ocorrendo não pode ocorrer novamente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#encerrar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void encerrar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		carona.setEstadoDaCarona(new EstadoCaronaEncerrada());
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#expirar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void expirar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona que já está ocorrendo não pode ser expirada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#getNomeEstado()
	 */
	@Override
	public EnumNomeEstadoDaCarona getNomeEstado() {
		return EnumNomeEstadoDaCarona.OCORRENDO;
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#esperar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void esperar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, CaronaInvalidaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona que está ocorrendo não pode ser posta em estado de espera");
	}

}
