package estradasolidaria.ui.server.logic;

/**
 * Classe que representa uma mudanca de estado
 * de solicitacao inválida.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoSolicitacaoException extends Exception {
	private static final long serialVersionUID = 5545240938891792910L;
	
	public EstadoSolicitacaoException(String msg) {
		super(msg);
	}
}
