package estradasolidaria.ui.server.logic;

/**
 * Classe que representa a excecao
 * lancada quando da tentativa de fazer
 * uma solicitacao a uma carona em estado
 * preferencial.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class CadastroEmCaronaPreferencialException extends Exception {
	private static final long serialVersionUID = -346358274119378223L;

	public CadastroEmCaronaPreferencialException() {
		super("Usuário não está na lista preferencial da carona");
	}
}
