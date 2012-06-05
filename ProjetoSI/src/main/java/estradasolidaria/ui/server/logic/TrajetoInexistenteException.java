package estradasolidaria.ui.server.logic;

public class TrajetoInexistenteException extends Exception {
	private static final long serialVersionUID = 6630133018662355930L;

	public TrajetoInexistenteException() {
		super("Trajeto Inexistente");
	}
}
