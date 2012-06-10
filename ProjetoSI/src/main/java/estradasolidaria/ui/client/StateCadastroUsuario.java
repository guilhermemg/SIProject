package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StateCadastroUsuario extends AbsolutePanel implements StatePanel {
	
	StatePanel state; 
	final EstradaSolidaria estrada;
	final Widget panel = this;
	private EstradaSolidariaServiceAsync estradaSolidariaService = 
			GWT.create(EstradaSolidariaService.class);

	public StateCadastroUsuario(EstradaSolidaria estradaSolidaria) {
		this.estrada = estradaSolidaria;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("h2");
		add(absolutePanel, 109, 83);
		absolutePanel.setSize("530px", "301px");
		
		Label lblNome = new Label("Nome:");
		absolutePanel.add(lblNome, 60, 10);
		lblNome.setStyleName("gwt-LabelEstradaSolidaria4");
		lblNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblNome.setDirectionEstimator(true);
		lblNome.setSize("104px", "21px");
		
		Label lblLogin = new Label("Login:");
		lblLogin.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblLogin, 60, 44);
		lblLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblLogin.setSize("104px", "15px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblSenha, 60, 72);
		lblSenha.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblSenha.setSize("104px", "15px");
		
		Label lblComfirmaASenha = new Label("Confirme a senha:");
		lblComfirmaASenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblComfirmaASenha, 60, 102);
		
		final TextBox textBbxNome = new TextBox();
		absolutePanel.add(textBbxNome, 206, 0);
		textBbxNome.setName("Digite seu nome");
		textBbxNome.setSize("262px", "17px");
		
		final TextBox textBoxLogin = new TextBox();
		absolutePanel.add(textBoxLogin, 206, 31);
		textBoxLogin.setName("Digite seu nome");
		textBoxLogin.setSize("262px", "17px");
		
		final PasswordTextBox textBoxPassword = new PasswordTextBox();
		absolutePanel.add(textBoxPassword, 206, 62);
		textBoxPassword.setSize("262px", "17px");
		
//		final PasswordTextBox passwordTextBox_1 = new PasswordTextBox();
//		absolutePanel.add(passwordTextBox_1, 206, 93);
//		passwordTextBox_1.setSize("262px", "17px");
		
		Label lblEndereo = new Label("Endereço:");
		lblEndereo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblEndereo.setDirectionEstimator(false);
		absolutePanel.add(lblEndereo, 60, 129);
		lblEndereo.setStyleName("gwt-LabelEstradaSolidaria4");
		lblEndereo.setSize("104px", "15px");
		
		Label lblEmail = new Label("Email:");
		lblEmail.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblEmail, 60, 161);
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblEmail.setSize("104px", "15px");
		
		final TextBox textBoxEndereco = new TextBox();
		absolutePanel.add(textBoxEndereco, 206, 130);
		textBoxEndereco.setName("Digite seu nome");
		textBoxEndereco.setSize("262px", "17px");
		
		final TextBox textBoxEmail = new TextBox();
		absolutePanel.add(textBoxEmail, 206, 161);
		textBoxEmail.setName("Digite seu nome");
		textBoxEmail.setSize("262px", "17px");
		
		Button btnConfirmar = new Button("Confirmar");
		btnConfirmar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBbxNome.getText().length() == 0|| textBoxLogin.getText().length() == 0 || textBoxEndereco.getText().length() == 0 || 
						textBoxEmail.getText().length() == 0 || textBoxPassword.getText().length() == 0 || textBoxPassword.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else {
					cadastraUsuarioGUI(textBoxLogin, textBoxPassword, textBbxNome, textBoxEndereco, textBoxEmail);
				}
			}
		});
		absolutePanel.add(btnConfirmar, 395, 232);
		btnConfirmar.setText("Criar");
		btnConfirmar.setSize("81px", "24px");
		
		
		Button btnVoltar = new Button("Cancelar");
		btnVoltar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel.add(btnVoltar, 206, 232);
		btnVoltar.setSize("81px", "24px");
		
		Label lblCadastroDeUsurio = new Label("Cadastro de Usuário");
		lblCadastroDeUsurio.setStyleName("gwt-LabelEstradaSolidaria2");
		add(lblCadastroDeUsurio, 301, 10);
		
	}

	protected void cadastraUsuarioGUI(TextBox textBoxLogin, PasswordTextBox textBoxPassword, TextBox textBbxNome, TextBox textBoxEndereco, TextBox textBoxEmail) {
		String login = textBoxLogin.getText(), senha = textBoxPassword.getText(),
				nome = textBbxNome.getText(), endereco = textBoxEndereco.getText(), email = textBoxEmail.getText();
		
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Remote Procedure Call - Failure: " + this.toString());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Remote Procedure Call is succefull");
			}
		  };
		
		try {
			estradaSolidariaService.criarUsuario(login, senha, nome, endereco, email, callback);
			
			estrada.rootPanel.remove(panel);
			Widget newPanel = new StateCadastroUsuarioAceito(estrada);
			newPanel.setSize("600px", "417px");
			estrada.setStatePanel(newPanel);
			
		} catch (Exception e) {
			Window.alert(e.getMessage());
		}
		
	}
}
