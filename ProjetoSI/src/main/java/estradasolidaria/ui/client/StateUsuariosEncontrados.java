package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class StateUsuariosEncontrados extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private List<String> listaNomesUsuarios;

	public StateUsuariosEncontrados(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService, List<String> result) {
		this.estrada = estrada;
		this.estradaService = estradaSolidariaService;
		this.listaNomesUsuarios = result;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("533px", "390px");
		
		Label lblUsuariosEncontrados = new Label("Usuários Encontrados:");
		lblUsuariosEncontrados.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblUsuariosEncontrados, 10, 10);
		
		ListBox listBox = new ListBox();
		absolutePanel.add(listBox, 10, 98);
		listBox.setSize("439px", "86px");
		listBox.setVisibleItemCount(5);
		for(String nome : listaNomesUsuarios){
			listBox.addItem(nome);
		}
	}
}
