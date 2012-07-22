package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupVizualizarPonto extends PopupPanel {
	public PopupVizualizarPonto(String ponto) {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("328px", "80px");
		
		Label lblPontoDeEncontro = new Label("Ponto de Encontro:");
		absolutePanel.add(lblPontoDeEncontro, 10, 10);
		
		Label lblPonto = new Label(ponto);
		absolutePanel.add(lblPonto, 138, 10);
		
		TextButton txtbtnNewButton = new TextButton("OK");
		txtbtnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnNewButton, 263, 41);
		txtbtnNewButton.setSize("55px", "29px");
	}
}
