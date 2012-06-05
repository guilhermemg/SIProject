package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StatePesquisaCarona extends Composite {
	
	final EstradaSolidaria estrada;
	
	public StatePesquisaCarona(EstradaSolidaria estradaSolidaria) {
		
		this.estrada = estradaSolidaria;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("387px", "209px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 28, 52);
		flexTable.setSize("314px", "66px");
		
		Label lblPesuisa = new Label("Pesquisa:");
		lblPesuisa.setStyleName("gwt-LabelHomePage");
		flexTable.setWidget(0, 0, lblPesuisa);
		
		TextBox textBoxPesquisa = new TextBox();
		flexTable.setWidget(0, 1, textBoxPesquisa);
		
		Button btnPesquisa = new Button("New button");
		btnPesquisa.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Widget newPanel = new StateCaronasEncontradas();
				estrada.setStatePanel(newPanel);
			}
		});
		btnPesquisa.setText("Go!");
		flexTable.setWidget(0, 2, btnPesquisa);
		
		Label lblPesquiseUmaCarona = new Label("Pesquise uma carona");
		lblPesquiseUmaCarona.setStyleName("gwt-LabelHomePage2");
		absolutePanel.add(lblPesquiseUmaCarona, 28, 10);
	}
}