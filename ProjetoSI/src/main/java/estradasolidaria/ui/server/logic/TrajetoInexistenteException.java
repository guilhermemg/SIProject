package estradasolidaria.ui.server.logic;

import java.io.Serializable;

public class TrajetoInexistenteException extends Exception implements Serializable {
	private static final long serialVersionUID = 6630133018662355930L;

	public TrajetoInexistenteException() {
		super("Trajeto Inexistente");
	}
}
