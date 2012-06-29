package estradasolidaria.ui.server.logic;

/**
 * Classe que representa uma excecao
 * lançada por uma mudança de estado ilegal.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaException extends Exception {
	private static final long serialVersionUID = -2168214022616452347L;

	public EstadoCaronaException(String msg) {
		super(msg);
	}
}
