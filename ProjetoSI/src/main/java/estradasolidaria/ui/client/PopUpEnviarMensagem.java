package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.TextArea;

public class PopUpEnviarMensagem extends PopupPanel {

	public PopUpEnviarMensagem() {
		super(true);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("448px", "221px");
		
		Label lblEnviarMensagem = new Label("Digite sua mensagem");
		absolutePanel.add(lblEnviarMensagem, 10, 10);
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		absolutePanel.add(txtbtnEnviar, 381, 183);
		
		TextArea textArea = new TextArea();
		absolutePanel.add(textArea, 10, 44);
		textArea.setSize("418px", "127px");
	}
}
