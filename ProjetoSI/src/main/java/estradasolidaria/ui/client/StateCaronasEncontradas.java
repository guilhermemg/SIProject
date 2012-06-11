package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.widget.client.TextButton;

public class StateCaronasEncontradas extends Composite {
	private List<String> listaDeResultados;

	public StateCaronasEncontradas(List<String> list) {
		this.listaDeResultados = list;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("593px", "404px");
		
		final ListBox listBoxCaronasEncontradas = new ListBox();
		for(String a : listaDeResultados){
			listBoxCaronasEncontradas.addItem(a);
		}

		absolutePanel.add(listBoxCaronasEncontradas, 80, 66);
		listBoxCaronasEncontradas.setSize("477px", "294px");
		listBoxCaronasEncontradas.setVisibleItemCount(10);
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar Vaga em carona");
		txtbtnRequisitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				if(listBoxCaronasEncontradas.isItemSelected(0) || listBoxCaronasEncontradas.isItemSelected(1) || 
						listBoxCaronasEncontradas.isItemSelected(2)){
					Window.alert("eh nóis truta" + listBoxCaronasEncontradas.getSelectedIndex());
				}
			}
		});
		absolutePanel.add(txtbtnRequisitarVaga, 374, 24);
	}
}
