/**
 * 
 */
package estradasolidaria.ui.server.logic;

import java.io.Serializable;

import javax.mail.MessagingException;

/**
 * Classe que representa o estado de uma carona (relampago) expirada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaExpired implements EstadoCaronaInterface, Serializable {

	private static final long serialVersionUID = -1827365304567883123L;

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#confirmar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void confirmar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona expirada não pode ser confirmada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#cancelar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void cancelar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, MessagingException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona expirada não pode ser cancelada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#realizar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void realizar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona expirada não pode mais ser realizada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#encerrar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void encerrar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona expirada não pode se encerrada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#expirar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void expirar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona expirada não pode ser expirada novamente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#getNomeEstado()
	 */
	@Override
	public EnumNomeEstadoDaCarona getNomeEstado() {
		return EnumNomeEstadoDaCarona.EXPIRED;
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
		throw new EstadoCaronaException("Uma carona expirada não pode ser posta em estado de espera");
	}

}
