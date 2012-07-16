package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupInteresseRemovido extends PopupPanel {

	public PopupInteresseRemovido() {
		super(true);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("261px", "77px");
		
		Label lblInteresseRemovidoCom = new Label("Interesse removido com sucesso.");
		lblInteresseRemovidoCom.setStyleName("gwt-LabelEstradaSolidaria10");
		absolutePanel.add(lblInteresseRemovidoCom, 34, 10);
		
		PushButton pshbtnOk = new PushButton("OK");
		pshbtnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(pshbtnOk, 197, 42);
	}
}
