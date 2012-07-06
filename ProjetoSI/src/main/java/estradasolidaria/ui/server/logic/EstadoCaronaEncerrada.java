package estradasolidaria.ui.server.logic;

import java.io.Serializable;

import javax.mail.MessagingException;

/**
 * Classe que representa o estado de uma carona encerrada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaEncerrada implements EstadoCaronaInterface, Serializable {

	private static final long serialVersionUID = -4208267710221172090L;

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#confirmar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void confirmar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode ser confirmada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#cancelar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void cancelar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, MessagingException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode ser cancelada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#realizar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void realizar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode ser realizada novamente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#encerrar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void encerrar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode ser encerrada novamente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#expirar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void expirar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode mais ser expirada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#getNomeEstado()
	 */
	@Override
	public EnumNomeEstadoDaCarona getNomeEstado() {
		return EnumNomeEstadoDaCarona.ENCERRADA;
	}

	@Override
	public void esperar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, CaronaInvalidaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona encerrada não pode entrar em estado de espera");
	}

}
