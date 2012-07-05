package estradasolidaria.ui.server.logic;

public class SenderMessage {
	/**
	 * Envia um mensagem para usuario. 
	 * 
	 * @param destinatario
	 * @param remetente
	 * @param texto
	 * @return true se mensagem foi enviada com sucesso
	 */
	public boolean sendMessage(Usuario destinatario, Usuario remetente, String texto) {
		Mensagem m = new Mensagem(destinatario, remetente, texto);
		destinatario.addMensagem(m);
		return true;
	}

	/**
	 * 
	 * 
	 * @param destinatario
	 * @param msg
	 * @return
	 */
	public boolean sendMessage(Usuario destinatario, String msg) {
		Mensagem m = new Mensagem(destinatario, msg);
		destinatario.addMensagem(m);
		return true;
	}
}
