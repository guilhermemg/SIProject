package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;

public class DialogBoxNovaSolicitacao extends DialogBox {
	
	private EstradaSolidariaServiceAsync estradaService;
	private Integer idDaCarona;
	private Label lblLoginusuario;
	private Label lblGettrajeto;

	public DialogBoxNovaSolicitacao(EstradaSolidariaServiceAsync estradaSolidariaService, Integer idCarona) {
		this.estradaService = estradaSolidariaService;
		this.idDaCarona = idCarona;
		
		setHTML("Novo pedido de carona");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("361px", "145px");
		
		Label lblUsuario = new Label("Usuario dono da carona:");
		absolutePanel.add(lblUsuario, 10, 10);
		
		Label lblCarona = new Label("Percurso:");
		absolutePanel.add(lblCarona, 10, 40);
		
		TextButton txtbtnVerCarona = new TextButton("Enviar mensagem ao motorista");
		txtbtnVerCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
				DialogBox newDialog = new DialogBoxEnviarMensagem();
				newDialog.center();
				newDialog.show();
			}
		});
		absolutePanel.add(txtbtnVerCarona, 51, 107);
		
		lblLoginusuario = new Label("colocarLoginUsuario");
		absolutePanel.add(lblLoginusuario, 174, 10);
		
		lblGettrajeto = new Label("trajetoDaCarona");
		absolutePanel.add(lblGettrajeto, 174, 40);
		lblGettrajeto.setSize("166px", "16px");
		
		TextButton txtbtnOk = new TextButton("OK");
		txtbtnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnOk, 263, 107);
		txtbtnOk.setSize("53px", "28px");
		
		hide();
		getCaronaGUI(idDaCarona);
		show();
	}
	
	private void getCaronaGUI(Integer idCarona) {
		estradaService.getCarona(idCarona, new AsyncCallback<List<String>>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: 1 " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<String> result) {
				// 1 = indice do id do dono da carona na lista retornada. 
				String idDonoDaCarona = result.get(1);
				getLoginUsuarioGUI(idDonoDaCarona);
			}
		  });
		
	}
	
	private void getLoginUsuarioGUI(String idDonoDaCarona2) {
		Integer idDono = Integer.parseInt(idDonoDaCarona2);
		estradaService.getUsuarioNoSistema(idDono, new AsyncCallback<List<String>>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: 2 " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<String> result) {
				// 1 = indice do login do dono da carona na lista retornada. 
				lblLoginusuario.setText(result.get(1));
				getTrajetoGUI(idDaCarona);
			}
		  });
		
	}

	private void getTrajetoGUI(Integer idDaCarona2) {
		estradaService.getTrajeto(idDaCarona2, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: 3 " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				lblGettrajeto.setText(result);
			}
		  });
	}
}
