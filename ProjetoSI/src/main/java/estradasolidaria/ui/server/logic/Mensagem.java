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
	
	private Integer idMensagem;
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
		setIdMensagem(hashCode());
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
		setIdMensagem(hashCode());
	}

	/**
	 * Configura id da mensagem.
	 * 
	 * @param hashCode
	 */
	private void setIdMensagem(int hashCode) {
		this.idMensagem = hashCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result
				+ ((idMensagem == null) ? 0 : idMensagem.hashCode());
		result = prime * result + ((lida == null) ? 0 : lida.hashCode());
		result = prime * result
				+ ((remetente == null) ? 0 : remetente.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (idMensagem == null) {
			if (other.idMensagem != null)
				return false;
		} else if (!idMensagem.equals(other.idMensagem))
			return false;
		if (lida != other.lida)
			return false;
		if (remetente == null) {
			if (other.remetente != null)
				return false;
		} else if (!remetente.equals(other.remetente))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	/**
	 * Retorna id da mensagem.
	 * 
	 * @return id
	 */
	public Integer getIdMensagem() {
		return this.idMensagem;
	}

	/**
	 * Configura texto da mensagem.
	 * 
	 * @param texto2
	 */
	private void setTexto(String texto2) {
		this.texto = texto2;
	}
	
	/**
	 * Configura remetente da mensagem.
	 * 
	 * @param remetente2
	 */
	private void setRemetente(Usuario remetente2) {
		this.remetente = remetente2;
	}
	
	/**
	 * Configura destinatario da mensagem.
	 * 
	 * @param destinatario2
	 */
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
