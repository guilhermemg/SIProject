package estradasolidaria.ui.server.logic;

public class UsuarioException extends Exception {
	private static final long serialVersionUID = 2528256827894930961L;

	public UsuarioException() {
		super("Usuario nao encontrado");
	}
}
