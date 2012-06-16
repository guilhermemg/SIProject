package estradasolidaria.ui.client;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.TextBox;

public class StateCaronasEncontradas extends Composite {
	private Map<String, Integer> mapaIdCaronaToString;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private EstradaSolidaria entryPoint;
	private Integer idSessao;

	@SuppressWarnings("static-access")
	public StateCaronasEncontradas(final EstradaSolidariaServiceAsync estradaService, EstradaSolidaria estrada, Map<String, Integer> map) {
		this.estradaSolidariaService = estradaService;
		this.entryPoint = estrada;
		this.mapaIdCaronaToString = map;
		Object[] arrayIdCaronaToString = mapaIdCaronaToString.keySet().toArray();
		
		idSessao  = entryPoint.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("593px", "491px");
		
		final ListBox listBoxCaronasEncontradas = new ListBox();
		for(int i = 0; i < arrayIdCaronaToString.length; i++){
			listBoxCaronasEncontradas.addItem((String) arrayIdCaronaToString[i]);
		}

		absolutePanel.add(listBoxCaronasEncontradas, 80, 164);
		listBoxCaronasEncontradas.setSize("477px", "294px");
		listBoxCaronasEncontradas.setVisibleItemCount(10);
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 80, 74);
		absolutePanel_1.setSize("477px", "70px");
		
		Label lblNewLabel = new Label("Sugira um local de encontro: (Opcional)");
		absolutePanel_1.add(lblNewLabel, 10, 10);
		
		final TextBox textBox = new TextBox();
		absolutePanel_1.add(textBox, 10, 32);
		textBox.setSize("217px", "16px");
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar Vaga em carona");
		absolutePanel_1.add(txtbtnRequisitarVaga, 267, 28);
		txtbtnRequisitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				Integer idCarona = mapaIdCaronaToString.get(listBoxCaronasEncontradas.getItemText(listBoxCaronasEncontradas.getSelectedIndex()));
				if(textBox.getText().equals("")){
					solicitarVagaGUI(idSessao, idCarona);
					DialogBox newDialog = new DialogBoxNovaSolicitacao(estradaService, idCarona);
					Widget source = (Widget) arg0.getSource();
		            int left = source.getAbsoluteLeft() - 300;
		            int top = source.getAbsoluteTop() - 150;
		            newDialog.setPopupPosition(left, top);
					newDialog.show();
				} else {
					solicitarVagaComPontoDeEncontroGUI(idSessao, idCarona, textBox.getText());
				}
			}

		});
	}
	
	protected void solicitarVagaGUI(Integer idSessao, Integer idCarona) {
		estradaSolidariaService.solicitarVaga(idSessao, idCarona, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				//hide();
			}
		  });	
	}
	
	protected void solicitarVagaComPontoDeEncontroGUI(Integer idSessao, Integer idCarona, String local) {
		estradaSolidariaService.solicitarVagaPontoEncontro(idSessao, idCarona, local, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				//hide();
				Window.alert(result);
			}
		  });	
	}
}
