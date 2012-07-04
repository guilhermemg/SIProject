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
}
