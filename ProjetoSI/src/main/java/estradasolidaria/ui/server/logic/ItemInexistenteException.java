package estradasolidaria.ui.server.logic;

public class ItemInexistenteException extends IllegalArgumentException {
	private static final long serialVersionUID = -4254277190824190372L;

	public ItemInexistenteException() {
		super("Item inexistente");
	}
}
