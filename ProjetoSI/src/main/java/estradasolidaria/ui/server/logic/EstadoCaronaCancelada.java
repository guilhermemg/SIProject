package estradasolidaria.ui.server.logic;

/**
 * Classe que representa o estado
 * de uma carona cancelada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaCancelada implements EstadoCaronaInterface {

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#confirmar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void confirmar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser confirmada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#cancelar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void cancelar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser cancelada novamente");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#realizar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void realizar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser mais realizada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#encerrar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void encerrar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser encerrada");
	}

	/* (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#expirar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void expirar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser expirada");
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#esperar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void esperar(Carona carona) throws EstadoCaronaException, CaronaInvalidaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona cancelada não pode ser posta em estado de espera");
	}
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#getNomeEstado()
	 */
	@Override
	public EnumNomeEstadoDaCarona getNomeEstado() {
		return EnumNomeEstadoDaCarona.CANCELADA;
	}
}
