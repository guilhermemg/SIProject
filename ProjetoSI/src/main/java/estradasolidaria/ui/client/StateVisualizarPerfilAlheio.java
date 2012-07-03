package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class StateVisualizarPerfilAlheio extends Composite {
	public StateVisualizarPerfilAlheio() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("630px", "486px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 26, 142);
		flexTable.setSize("100px", "100px");
		
		Label lblNome = new Label("Nome:");
		flexTable.setWidget(0, 0, lblNome);
		
		Label lblLogin = new Label("Login:");
		flexTable.setWidget(1, 0, lblLogin);
		
		Label lblEndereo = new Label("Endereço:");
		flexTable.setWidget(2, 0, lblEndereo);
		
		Label lblEmail = new Label("Email:");
		flexTable.setWidget(3, 0, lblEmail);
	}
}
