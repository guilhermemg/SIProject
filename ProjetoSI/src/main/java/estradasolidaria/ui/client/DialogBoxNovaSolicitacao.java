package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;

public class DialogBoxNovaSolicitacao extends DialogBox {
	
	EstradaSolidariaServiceAsync estradaService;
	Integer idDaCarona;

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
		
		TextButton txtbtnVerCarona = new TextButton("Ver carona");
		txtbtnVerCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
				DialogBox newDialog = new DialogBoxVerCarona();
				newDialog.center();
				newDialog.show();
			}
		});
		absolutePanel.add(txtbtnVerCarona, 117, 107);
		
		Label lblLoginusuario = new Label("loginUsuario");
		absolutePanel.add(lblLoginusuario, 168, 10);
		
		Label lblGettrajeto = new Label("");
		absolutePanel.add(lblGettrajeto, 168, 40);
		
		getTrajetoGUI(idDaCarona, lblGettrajeto);
	}

	private void getTrajetoGUI(Integer idDaCarona2, final Label lblGettrajeto) {
		estradaService.getTrajeto(idDaCarona2, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				lblGettrajeto.setText(result);
			}
		  });
	}
}
