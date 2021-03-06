package estradasolidaria.ui.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;

public class StateVerCarona extends Composite {

	public StateVerCarona() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("441px", "267px");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 57, 210);
		menuBar.setSize("363px", "21px");
		
		MenuItem mntmEnviarMensagemAo = new MenuItem("Enviar mensagem ao motorista", false, new Command() {
			public void execute() {
				//TODO fazer acao de botao Enviar mensagem ao motorista em StateVerCarona
			}
		});
		menuBar.addItem(mntmEnviarMensagemAo);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		
		MenuItem mntmOK = new MenuItem("OK", false, new Command() {
			public void execute() {
				//TODO fazer acao de botao OK em StateVerCarona
			}
		});
		menuBar.addItem(mntmOK);
		
		Label lblCarona = new Label("Carona:");
		absolutePanel.add(lblCarona, 10, 20);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 57, 45);
		flexTable.setSize("322px", "123px");
		
		Label lblTrajeto = new Label("Trajeto:");
		flexTable.setWidget(0, 0, lblTrajeto);
		
		Label lblGettrajeto = new Label("getTrajeto");
		flexTable.setWidget(0, 1, lblGettrajeto);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(1, 0, lblData);
		
		Label lblGetdata = new Label("getData");
		flexTable.setWidget(1, 1, lblGetdata);
		
		Label lblHora = new Label("Hora:");
		flexTable.setWidget(2, 0, lblHora);
		
		Label lblGethora = new Label("getHora");
		flexTable.setWidget(2, 1, lblGethora);
		
		Label lblVagasRestantes = new Label("Vagas restantes:");
		flexTable.setWidget(3, 0, lblVagasRestantes);
		
		Label lblGetvagas = new Label("getVagas");
		flexTable.setWidget(3, 1, lblGetvagas);
	}
}
