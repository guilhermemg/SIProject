package estradasolidaria.ui.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class DialogBoxAlterarSenha extends DialogBox {
	
	private TextBox textBoxSenha;
	private TextBox textBoxSenha2;
	private Integer idSessao;
	private EstradaSolidariaServiceAsync estradaService;

	public DialogBoxAlterarSenha(EstradaSolidariaServiceAsync estradaSolidariaService) {
		
		this.estradaService = estradaSolidariaService;
		this.idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		setHTML("Editar senha");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("329px", "154px");
		
		Label lblSenha = new Label("Senha:");
		absolutePanel.add(lblSenha, 10, 20);
		
		Label lblConfirmeASenh = new Label("Confirme a senha:");
		absolutePanel.add(lblConfirmeASenh, 10, 53);
		
		textBoxSenha = new TextBox();
		absolutePanel.add(textBoxSenha, 165, 20);
		
		textBoxSenha2 = new TextBox();
		absolutePanel.add(textBoxSenha2, 165, 53);
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		txtbtnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!textBoxSenha.getText().equals(textBoxSenha2.getText())){
					Window.alert("Redigite a senha");
				}
				editarSenhaGUI(idSessao, textBoxSenha2.getText());
			}
		});
		absolutePanel.add(txtbtnEnviar, 235, 106);
	}

	protected void editarSenhaGUI(Integer idSessao2, String text) {
		estradaService.editarSenha(idSessao, text, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				hide();
			}
		  });
		
	}
}
