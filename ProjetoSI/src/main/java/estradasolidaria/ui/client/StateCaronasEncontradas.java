package estradasolidaria.ui.client;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;

public class StateCaronasEncontradas extends Composite {
	private Map<String, Integer> mapaIdCaronaToString;
	private EstradaSolidariaServiceAsync estradaSolidariaService;

	public StateCaronasEncontradas(final EstradaSolidariaServiceAsync estradaService, Map<String, Integer> map) {
		this.estradaSolidariaService = estradaService;
		this.mapaIdCaronaToString = map;
		Object[] arrayIdCaronaToString = mapaIdCaronaToString.keySet().toArray();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("593px", "404px");
		
		final ListBox listBoxCaronasEncontradas = new ListBox();
		for(int i = 0; i < arrayIdCaronaToString.length; i++){
			listBoxCaronasEncontradas.addItem((String) arrayIdCaronaToString[i]);
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
					Integer idCarona = mapaIdCaronaToString.get(listBoxCaronasEncontradas.getItemText(listBoxCaronasEncontradas.getSelectedIndex()));
					DialogBox newDialog = new DialogBoxPontoDeEncontro(estradaSolidariaService, mapaIdCaronaToString, idCarona);
					Widget source = (Widget) arg0.getSource();
		            int left = source.getAbsoluteLeft() + 10;
		            int top = source.getAbsoluteTop() + 10;
		            newDialog.setPopupPosition(left, top);
					newDialog.show();
				}
			}

		});
		absolutePanel.add(txtbtnRequisitarVaga, 374, 24);
	}
}
