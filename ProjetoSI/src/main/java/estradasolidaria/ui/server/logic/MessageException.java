package estradasolidaria.ui.server.logic;

public class MessageException extends Exception {
	private static final long serialVersionUID = 7759431392984900724L;

	public MessageException() {
		super("Não foi possivel inserir a mensagem a lista de mensagens.");
	}
}
