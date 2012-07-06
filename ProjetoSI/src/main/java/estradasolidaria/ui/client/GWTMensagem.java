package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTMensagem implements IsSerializable {

	private String destinatario;
	private String remetente;
	private String texto;
	private boolean mensagemLida;
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isMensagemLida() {
		return mensagemLida;
	}
	public void setMensagemLida(boolean mensagemLida) {
		this.mensagemLida = mensagemLida;
	}
}
