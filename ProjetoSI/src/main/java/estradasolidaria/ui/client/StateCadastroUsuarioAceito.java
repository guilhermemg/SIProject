package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StateCadastroUsuarioAceito extends Composite implements StatePanel{
	
	final EstradaSolidaria estrada;
	final Widget panel= this;

	public StateCadastroUsuarioAceito(EstradaSolidaria estradaSolidaria) {
		
		this.estrada = estradaSolidaria;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("580px", "220px");
		
		Button btnVerPerfil = new Button("Ver perfil");
		btnVerPerfil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StatePerfil2(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel.add(btnVerPerfil, 445, 142);
		
		Button btnPginaInicial = new Button("Página inicial");
		btnPginaInicial.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel.add(btnPginaInicial, 297, 142);
		
		Label lblNewLabel = new Label("Cadastro efetuado com sucesso!");
		lblNewLabel.setStyleName("gwt-LabelEstradaSolidaria3");
		absolutePanel.add(lblNewLabel, 54, 48);
		lblNewLabel.setSize("436px", "88px");
	}
}
