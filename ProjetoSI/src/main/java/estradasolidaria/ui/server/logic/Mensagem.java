package estradasolidaria.ui.server.logic;

import java.io.Serializable;

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
public class Mensagem implements Serializable {
	private static final long serialVersionUID = -1900764470719850618L;
	private String texto;
	private Usuario destinatario;
	private Usuario remetente;
	private EnumLida lida;

	/**
	 * Primeiro construtor para mensagem.
	 * 
	 * @param destinatario
	 * @param remetente
	 * @param texto
	 */
	public Mensagem(Usuario destinatario, Usuario remetente, String texto) {
		setDestinatario(destinatario);
		setRemetente(remetente);
		setTexto(texto);
		setLida(EnumLida.NAO_LIDA);
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
		setDestinatario(destinatario);
		setTexto(msg);
		setLida(EnumLida.NAO_LIDA);
	}

	private void setTexto(String texto2) {
		this.texto = texto2;
	}
	
	private void setRemetente(Usuario remetente2) {
		this.remetente = remetente2;
	}
	
	private void setDestinatario(Usuario destinatario2) {
		this.destinatario = destinatario2;
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

	/**
	 * @return the lida
	 */
	public boolean isLida() {
		return lida.equals(EnumLida.LIDA);
	}

	/**
	 * @param lida the lida to set
	 */
	public void setLida(EnumLida lida) {
		this.lida = lida;
	}


	/**
	 * Retorna objeto
	 * EnumLida desta mensagem.
	 * 
	 * @return lida
	 */
	public EnumLida getLida() {
		return this.lida;
	}
}
