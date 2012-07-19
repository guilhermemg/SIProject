package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class DialogBoxInteresse extends DialogBox {
	private Label lblNewLabel;
	private Button btnOk;
	public DialogBoxInteresse() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("280px", "110px");
		
		setBtnOk(new Button("OK"));
		absolutePanel.add(getBtnOk(), 164, 75);
		
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(btnCancelar, 201, 75);
		
		setLblNewLabel(new Label(""));
		absolutePanel.add(getLblNewLabel(), 0, 10);
		getLblNewLabel().setWidth("100%");
	}
	
	public Label getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(Label lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public Button getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(Button btnOk) {
		this.btnOk = btnOk;
	}
}
