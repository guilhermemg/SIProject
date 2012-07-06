package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FormPanel;

import estradasolidaria.ui.resources.Resources;

public class DialogBoxVerMensagem extends DialogBox {
	
	private GWTMensagem mensagem;
	
	public DialogBoxVerMensagem(GWTMensagem object) {
		this.mensagem = object;
		
		Resources resources = GWT.create(Resources.class);
		Image imagem = new Image(resources.getCloseIcon());
		
		setHTML("Mensagem");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("543px", "385px");
		
		Label lblDe = new Label("De:");
		lblDe.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblDe, 10, 10);
		
		Label lblPara = new Label("Para:");
		lblPara.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblPara, 10, 38);
		
		PushButton pshbtnFechar = new PushButton(imagem);
		pshbtnFechar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(pshbtnFechar, 461, 10);
		
		Label lblRemetente = new Label(mensagem.getRemetente());
		lblRemetente.setStyleName("gwt-LabelEstradaSolidaria8");
		absolutePanel.add(lblRemetente, 54, 10);
		
		Label lblDestinatario = new Label(mensagem.getDestinatario());
		lblDestinatario.setStyleName("gwt-LabelEstradaSolidaria8");
		absolutePanel.add(lblDestinatario, 54, 38);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		absolutePanel.add(scrollPanel, 10, 78);
		scrollPanel.setSize("523px", "295px");
		
		Label label = new Label(mensagem.getTexto());
		label.setStyleName("gwt-LabelEstradaSolidaria8");
		scrollPanel.setWidget(label);
		label.setSize("523px", "295px");
	}
}
