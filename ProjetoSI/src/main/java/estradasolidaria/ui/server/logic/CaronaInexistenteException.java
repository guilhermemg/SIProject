package estradasolidaria.ui.server.logic;

import java.io.Serializable;

public class CaronaInexistenteException extends Exception implements Serializable {
	private static final long serialVersionUID = -7124522201555379482L;

	public CaronaInexistenteException() {
		super("Carona Inexistente");
	}
}
