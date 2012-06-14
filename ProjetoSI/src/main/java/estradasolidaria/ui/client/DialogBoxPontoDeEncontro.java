package estradasolidaria.ui.client;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;

public class DialogBoxPontoDeEncontro extends DialogBox {
	
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idCarona;

	public DialogBoxPontoDeEncontro(EstradaSolidariaServiceAsync estradaSolidariaService, Map<String, Integer> map, Integer idDaCarona) {
		this.estradaSolidariaService = estradaSolidariaService;
		this.idCarona = idDaCarona;
		final Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		setHTML("Ponto de encontro");
		
		final AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("269px", "108px");
		
		Label lblSugerirPontoDe = new Label("Sugira um local de encontro: (Opcional)");
		absolutePanel.add(lblSugerirPontoDe, 10, 10);
		lblSugerirPontoDe.setSize("247px", "16px");
		
		final TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 10, 36);
		textBox.setSize("239px", "16px");
		
		TextButton txtbtnOk = new TextButton("OK");
		txtbtnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBox.getText().equals("")){
					solicitarVagaGUI(idSessao, idCarona);
				} else {
					solicitarVagaComPontoDeEncontroGUI(idSessao, idCarona, textBox.getText());
				}
			}
		});
		absolutePanel.add(txtbtnOk, 220, 66);
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
				hide();
				Window.alert(result);
			}
		  });	
	}
	
	protected void solicitarVagaComPontoDeEncontroGUI(Integer idSessao, Integer idCarona, String ponto) {
		estradaSolidariaService.solicitarVagaPontoEncontro(idSessao, idCarona, ponto, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				hide();
				Window.alert(result);
			}
		  });	
	}
}
