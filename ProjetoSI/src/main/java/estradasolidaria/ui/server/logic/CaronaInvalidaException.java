package estradasolidaria.ui.server.logic;

import java.io.Serializable;

public class CaronaInvalidaException extends Exception implements Serializable {
	private static final long serialVersionUID = 7518537318773422048L;

	public CaronaInvalidaException() {
		super("Carona Inválida");
	}
}
