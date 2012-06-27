package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class DialogBoxAlterarSenha extends DialogBox {
	
	private PasswordTextBox textBoxSenha;
	private PasswordTextBox textBoxSenha2;
	private Integer idSessao;
	private EstradaSolidariaServiceAsync estradaService;
	private Label lblMensagemdeerro;
	private Label lblErro;

	public DialogBoxAlterarSenha(EstradaSolidariaServiceAsync estradaSolidariaService) {
		
		this.estradaService = estradaSolidariaService;
		this.idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		setHTML("Editar senha");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("349px", "154px");
		
		Label lblSenha = new Label("Senha:");
		absolutePanel.add(lblSenha, 10, 20);
		
		Label lblConfirmeASenh = new Label("Confirme a senha:");
		absolutePanel.add(lblConfirmeASenh, 10, 53);
		
		textBoxSenha = new PasswordTextBox();
		textBoxSenha.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblMensagemdeerro.setVisible(false);
				lblErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxSenha, 165, 20);
		
		textBoxSenha2 = new PasswordTextBox();
		textBoxSenha2.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblMensagemdeerro.setVisible(false);
				lblErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxSenha2, 165, 53);
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		txtbtnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!textBoxSenha.getText().equals(textBoxSenha2.getText())){
					lblMensagemdeerro.setText("Redigite a senha");
					lblMensagemdeerro.setVisible(true);
					lblErro.setVisible(true);
				} else {
					editarSenhaGUI(idSessao, textBoxSenha2.getText());
				}
			}
		});
		absolutePanel.add(txtbtnEnviar, 181, 105);
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 249, 105);
		
		lblMensagemdeerro = new Label("MensagemDeErro");
		lblMensagemdeerro.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel.add(lblMensagemdeerro, 10, 84);
		lblMensagemdeerro.setVisible(false);
		
		lblErro = new Label("*");
		lblErro.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel.add(lblErro, 336, 53);
		lblErro.setSize("13px", "16px");
		lblErro.setVisible(false);
	}

	protected void editarSenhaGUI(Integer idSessao2, String text) {
		estradaService.editarSenha(idSessao, text, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemdeerro.setText(caught.getMessage());
				lblMensagemdeerro.setVisible(true);
			}

			@Override
			public void onSuccess(Void result) {
				hide();
			}
		  });
		
	}
}
