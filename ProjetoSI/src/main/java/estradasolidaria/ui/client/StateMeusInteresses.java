package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class StateMeusInteresses extends Composite {
	private List<String> meusInteresses;
	public StateMeusInteresses(EstradaSolidariaServiceAsync estradaSolidariaService) {
		meusInteresses = new LinkedList<String>();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		
		ListBox listBox = new ListBox();
		absolutePanel.add(listBox, 10, 40);
		listBox.setSize("390px", "246px");
		listBox.setVisibleItemCount(5);
		
		Button btnCadastrarInteresse = new Button("Cadastrar Interesse");
		btnCadastrarInteresse.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		absolutePanel.add(btnCadastrarInteresse, 10, 9);
		
		Button btnDeletarInteresse = new Button("Deletar Interesse");
		absolutePanel.add(btnDeletarInteresse, 155, 9);
		
		
	}
}
