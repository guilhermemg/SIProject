package estradasolidaria.ui.server.logic;

import java.io.Serializable;

/**
 * Classe que representa o
 * enviador de mensagens do
 * sistema, ele tambem eh usado no
 * envio de mensagens entre usuarios.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class SenderMessage implements Serializable {
	private static final long serialVersionUID = -3174997058762030389L;

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
