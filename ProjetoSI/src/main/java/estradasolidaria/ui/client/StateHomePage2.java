package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.AttachEvent;

public class StateHomePage2 extends Composite {
	
	private TextBox textBoxNome;
	private TextBox textBoxLogin;
	private PasswordTextBox TextBoxSenha;
	private PasswordTextBox TextBoxSenha2;
	private TextBox textBoxEndereco;
	private TextBox textBoxEmail;
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;

	public StateHomePage2(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estradaSolidaria;
		this.estradaService = estradaSolidariaService;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("823px", "673px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStylePrimaryName("gwt-LoginPanel");
		absolutePanel.add(absolutePanel_1, 474, 223);
		absolutePanel_1.setSize("318px", "218px");
		
		Label label = new Label("Já é usuário? Faça Login.");
		label.setStyleName("gwt-LabelEstradaSolidaria");
		absolutePanel_1.add(label, 23, 10);
		
		Label lblUser = new Label("Usuário:");
		lblUser.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_1.add(lblUser, 10, 51);
		lblUser.setSize("58px", "15px");
		
		final TextBox textBoxUser = new TextBox();
		absolutePanel_1.add(textBoxUser, 74, 51);
		textBoxUser.setSize("180px", "13px");
		
		Label lblPassword = new Label("Senha:");
		lblPassword.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_1.add(lblPassword, 10, 78);
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		absolutePanel_1.add(passwordTextBox, 74, 78);
		passwordTextBox.setSize("180px", "17px");
		
		Button button = new Button("Login");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxUser.getText().length() == 0|| passwordTextBox.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else {
					 abrirSessaoGUI(textBoxUser, passwordTextBox);
				}
			}
		});
		absolutePanel_1.add(button, 170, 128);
		button.setSize("81px", "24px");
		
		CheckBox checkBox = new CheckBox("lembrar-me");
		checkBox.setHTML("Lembrar-me");
		absolutePanel_1.add(checkBox, 170, 168);
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absolutePanel_2.setStyleName("h2");
		absolutePanel.add(absolutePanel_2, 23, 222);
		absolutePanel_2.setSize("445px", "328px");
		
		Label lblNome = new Label("Nome:");
		lblNome.setStyleName("gwt-LabelEstradaSolidaria4");
		lblNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblNome.setDirectionEstimator(true);
		absolutePanel_2.add(lblNome, 37, 60);
		lblNome.setSize("104px", "21px");
		
		Label lblLogin = new Label("Login:");
		lblLogin.setStyleName("gwt-LabelEstradaSolidaria4");
		lblLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel_2.add(lblLogin, 37, 94);
		lblLogin.setSize("104px", "15px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		lblSenha.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel_2.add(lblSenha, 37, 122);
		lblSenha.setSize("104px", "15px");
		
		Label lblSenha2 = new Label("Confirme a senha:");
		lblSenha2.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_2.add(lblSenha2, 37, 152);
		
		Label lblEndereco = new Label("Endereço:");
		lblEndereco.setStyleName("gwt-LabelEstradaSolidaria4");
		lblEndereco.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblEndereco.setDirectionEstimator(false);
		absolutePanel_2.add(lblEndereco, 37, 179);
		lblEndereco.setSize("104px", "15px");
		
		Label lblEmail = new Label("Email:");
		lblEmail.setStyleName("gwt-LabelEstradaSolidaria4");
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel_2.add(lblEmail, 37, 211);
		lblEmail.setSize("104px", "15px");
		
		textBoxNome = new TextBox();
		absolutePanel_2.add(textBoxNome, 183, 50);
		textBoxNome.setSize("208px", "17px");
		
		textBoxLogin = new TextBox();
		absolutePanel_2.add(textBoxLogin, 183, 81);
		textBoxLogin.setSize("208px", "17px");
		
		TextBoxSenha = new PasswordTextBox();
		absolutePanel_2.add(TextBoxSenha, 183, 112);
		TextBoxSenha.setSize("208px", "17px");
		
		TextBoxSenha2 = new PasswordTextBox();
		absolutePanel_2.add(TextBoxSenha2, 183, 143);
		TextBoxSenha2.setSize("208px", "17px");
		
		textBoxEndereco = new TextBox();
		absolutePanel_2.add(textBoxEndereco, 183, 180);
		textBoxEndereco.setSize("208px", "17px");
		
		textBoxEmail = new TextBox();
		absolutePanel_2.add(textBoxEmail, 183, 211);
		textBoxEmail.setSize("208px", "17px");
		
		TextButton textButton = new TextButton("Criar");
		textButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxNome.getText().length() == 0|| textBoxLogin.getText().length() == 0 || textBoxEndereco.getText().length() == 0 || 
						textBoxEmail.getText().length() == 0 || TextBoxSenha.getText().length() == 0 || TextBoxSenha2.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else if(!TextBoxSenha.getText().equals(TextBoxSenha2.getText())){
					Window.alert("Senha invalida");
				} else {
					cadastraUsuarioGUI(textBoxLogin, TextBoxSenha, textBoxNome, textBoxEndereco, textBoxEmail);
				}
			}
		});
		absolutePanel_2.add(textButton, 249, 256);
		textButton.setSize("145px", "47px");
		
		Label lblRegistrese = new Label("Registre-se!");
		lblRegistrese.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel_2.add(lblRegistrese, 188, 0);
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_3, 283, 60);
		absolutePanel_3.setSize("330px", "111px");
		
		Label label_9 = new Label("Bem-vindo ao Estrada Solidária!");
		label_9.setStyleName("gwt-LabelHomePage2");
		label_9.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absolutePanel_3.add(label_9, 48, 24);
		label_9.setSize("216px", "29px");
		
		AbsolutePanel absolutePanel_4 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_4, 569, 549);
		absolutePanel_4.setSize("173px", "50px");
		
		Hyperlink hprlnkNewHyperlink = new Hyperlink("teste", false, "newHistoryToken");
		hprlnkNewHyperlink.setHTML("http://www.ogame.com.br/");
		hprlnkNewHyperlink.addAttachHandler(new Handler() {
			public void onAttachOrDetach(AttachEvent event) {
			}
		});
		absolutePanel_4.add(hprlnkNewHyperlink, 10, 22);
	}
	
	protected void cadastraUsuarioGUI(final TextBox textBoxLogin, final PasswordTextBox textBoxPassword, TextBox textBbxNome, TextBox textBoxEndereco, TextBox textBoxEmail) {
		String login = textBoxLogin.getText(), senha = textBoxPassword.getText(),
				nome = textBbxNome.getText(), endereco = textBoxEndereco.getText(), email = textBoxEmail.getText();
		
		estradaService.criarUsuario(login, senha, nome, endereco, email, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Remote Procedure Call - Failure: " + this.toString());
			}

			@Override
			public void onSuccess(Void result) {
				abrirSessaoGUI(textBoxLogin, textBoxPassword);
				Window.alert("Usuário cadastrado!");
			}
		  });
	}
	
	protected void abrirSessaoGUI(TextBox textBoxLogin, PasswordTextBox textBoxPassword) {
	String login = textBoxLogin.getText(), senha = textBoxPassword.getText();
		
		estradaService.abrirSessao(login, senha, new AsyncCallback<Integer>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				EstradaSolidaria.setIdSessaoAberta(result);
				getUsuarioGUI();
			}
		  });
	}
	
	protected void getUsuarioGUI() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaService.getUsuario(idSessao, new AsyncCallback<String[]>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String[] result) {
				estrada.setStatePanel(new StatePerfil(estrada, estradaService, result));
			}
		});
	}
}
