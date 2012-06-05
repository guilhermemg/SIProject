package estradasolidaria.ui.server.logic;

public class SessaoInexistenteException extends Exception {
	private static final long serialVersionUID = -2810488145982925925L;

	public SessaoInexistenteException() {
		super("Sessão inexistente");
	}
}
