package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItemSeparator;

public class StateSolicitacaoEmCurso extends Composite {

	public StateSolicitacaoEmCurso() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("631px", "503px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 10, 10);
		flexTable.setSize("611px", "52px");
		
		Label lblVocAguardaSolicitao = new Label("Você aguarda solicitação a uma vaga feita de:");
		flexTable.setWidget(0, 0, lblVocAguardaSolicitao);
		lblVocAguardaSolicitao.setSize("268px", "16px");
		
		Label lblDadosDaCarona = new Label("idUsuario, toString");
		flexTable.setWidget(0, 1, lblDadosDaCarona);
		
		TextButton txtbtnVerCarona = new TextButton("Ver carona");
		flexTable.setWidget(0, 2, txtbtnVerCarona);
	}
}
