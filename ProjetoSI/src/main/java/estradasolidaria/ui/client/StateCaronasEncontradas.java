package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;

public class StateCaronasEncontradas extends Composite {

	public StateCaronasEncontradas() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("593px", "222px");
		
		ListBox listBoxCaronasEncontradas = new ListBox();
		absolutePanel.add(listBoxCaronasEncontradas, 80, 66);
		listBoxCaronasEncontradas.setSize("248px", "128px");
		listBoxCaronasEncontradas.setVisibleItemCount(10);
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar Vaga em carona");
		absolutePanel.add(txtbtnRequisitarVaga, 363, 166);
	}
}
