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
	private String texto;
	private Usuario destinatario;
	private Usuario remetente;

	/**
	 * Primeiro construtor para mensagem.
	 * 
	 * @param destinatario
	 * @param remetente
	 * @param texto
	 */
	public Mensagem(Usuario destinatario, Usuario remetente, String texto) {
		this.destinatario = destinatario;
		this.remetente = remetente;
		this.texto = texto;
	}

	/**
	 * Segundo construtor para mensagem,
	 * ele eh usado quando o sistema manda
	 * mensagens para o usuario.
	 * 
	 * NOTE: o campo de remetente fica null.
	 * 
	 * @param destinatario
	 * @param msg
	 */
	public Mensagem(Usuario destinatario, String msg) {
		this.destinatario = destinatario;
		this.texto = msg;
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
