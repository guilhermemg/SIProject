package estradasolidaria.ui.server.logic;

/**
 * Classe que representa um mensagem enviada
 * para um usuario vinda de outro usuario.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Mensagem {
	private final String texto;
	private final Usuario destinatario;
	private final Usuario remetente;

	public Mensagem(Usuario destinatario, Usuario remetente, String texto) {
		this.destinatario = destinatario;
		this.remetente = remetente;
		this.texto = texto;
	}

	/**
	 * @return the remetente
	 */
	public Usuario getRemetente() {
		return remetente;
	}

	/**
	 * @return the destinatario
	 */
	public Usuario getDestinatario() {
		return destinatario;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
}
