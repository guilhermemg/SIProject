package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupAlerta extends PopupPanel {

	private String mensagem;
	
	public PopupAlerta(String string) {
		super(true);
		mensagem = string;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("245px", "77px");
		
		Label label = new Label(mensagem);
		label.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel.add(label, 26, 10);
		
		Button btnOk = new Button("OK");
		btnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(btnOk, 192, 38);
	}
}
