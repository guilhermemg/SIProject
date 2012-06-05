package estradasolidaria.ui.server.logic;

public class UsuarioInexistenteException extends IllegalArgumentException {
	private static final long serialVersionUID = 6448489263839634117L;

	public UsuarioInexistenteException() {
		super("Usuário inexistente");
	}
}
