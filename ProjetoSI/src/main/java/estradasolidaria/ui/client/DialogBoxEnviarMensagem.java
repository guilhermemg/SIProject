package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class DialogBoxEnviarMensagem extends DialogBox {

	private EstradaSolidariaServiceAsync estradaService;
	private Integer idDonoCarona;
	private Integer idDaSessao;
	private Label lblMensagemDeErro;
	private TextArea textArea;
	
	public DialogBoxEnviarMensagem(EstradaSolidariaServiceAsync estradaSolidariaService, String idDonoDaCarona, Integer idSessao) {
		this.estradaService = estradaSolidariaService;
		this.idDonoCarona = Integer.parseInt(idDonoDaCarona);
		this.idDaSessao = idSessao;
		
		setHTML("Enviar mensagem");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("448px", "221px");
		
		Label lblEnviarMensagem = new Label("Digite sua mensagem");
		absolutePanel.add(lblEnviarMensagem, 10, 10);

		textArea = new TextArea();
		textArea.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(textArea, 10, 44);
		textArea.setSize("418px", "127px");
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		txtbtnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textArea.getText().length() == 0){
					lblMensagemDeErro.setText("Mensagem inválida");
					lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
					lblMensagemDeErro.setVisible(true);
				} else {
					getEmailUsuarioGUI();
				}
			}
		});
		absolutePanel.add(txtbtnEnviar, 349, 183);
		txtbtnEnviar.setSize("87px", "28px");
		
		
		TextButton txtbtnCancelar = new TextButton("Fechar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 263, 183);
		txtbtnCancelar.setSize("80px", "28px");
		
		lblMensagemDeErro = new Label("mensagem de erro");
		absolutePanel.add(lblMensagemDeErro, 10, 183);
		lblMensagemDeErro.setVisible(false);
	}

	protected void getEmailUsuarioGUI() {
		estradaService.getUsuarioNoSistema(idDonoCarona, new AsyncCallback<List<String>>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemDeErro.setText(caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
			}

			@Override
			public void onSuccess(List<String> result) {
				// 1 = indice do id do dono da carona na lista retornada. 
				String emailDonoDaCarona = result.get(2);
				enviaEmailUsuarioGUI(emailDonoDaCarona);
			}
		  });
		
	}

	protected void enviaEmailUsuarioGUI(String emailDonoDaCarona) {
		estradaService.enviarEmail(idDaSessao, emailDonoDaCarona, textArea.getText(), new AsyncCallback<Boolean>(){
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemDeErro.setText(caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
				
			}

			@Override
			public void onSuccess(Boolean result) {
				textArea.setText("");
				lblMensagemDeErro.setText("Mensagem enviada!");
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria10");
				lblMensagemDeErro.setVisible(true);
			}
		  });
		
	}
}
