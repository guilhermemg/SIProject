package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.widget.client.TextButton;

public class DialogBoxEnviarMensagem extends DialogBox {

	public DialogBoxEnviarMensagem() {
		setHTML("Enviar mensagem");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("448px", "221px");
		
		Label lblEnviarMensagem = new Label("Digite sua mensagem");
		absolutePanel.add(lblEnviarMensagem, 10, 10);
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		txtbtnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnEnviar, 349, 183);
		txtbtnEnviar.setSize("87px", "28px");
		
		TextArea textArea = new TextArea();
		absolutePanel.add(textArea, 10, 44);
		textArea.setSize("418px", "127px");
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 263, 183);
		txtbtnCancelar.setSize("80px", "28px");
	}
}
