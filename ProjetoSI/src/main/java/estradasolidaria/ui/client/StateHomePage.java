package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;
import com.google.gwt.user.client.ui.Hyperlink;

public class StateHomePage extends Composite {
	
	private TextBox textBoxNome;
	private TextBox textBoxLogin;
	private PasswordTextBox TextBoxSenha;
	private PasswordTextBox TextBoxSenha2;
	private TextBox textBoxEndereco;
	private TextBox textBoxEmail;
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private Label lblMensagemDeErro;
	private Label lblErroLoginUser;
	private Label lblErroSenhaUser;
	private Label lblErrosenha;
	private Label lblErrosenha_1; 
	private Label lblErronome;
	private Label lblErrologin;
	private Label lblErroendereco;
	private Label lblErroemail;
	private Label lblMensagemErroLogin;
	
	public StateHomePage(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		Resources resources = GWT.create(Resources.class);
		this.estrada = estradaSolidaria;
		this.estradaService = estradaSolidariaService;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1384px", "686px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStylePrimaryName("gwt-LoginPanel");
		absolutePanel.add(absolutePanel_1, 687, 222);
		absolutePanel_1.setSize("313px", "256px");
		
		Label label = new Label("Já é usuário? Faça Login.");
		label.setStyleName("gwt-LabelEstradaSolidaria");
		absolutePanel_1.add(label, 23, 58);
		
		Label lblUser = new Label("Usuário:");
		lblUser.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_1.add(lblUser, 10, 99);
		lblUser.setSize("58px", "15px");
		
		final TextBox textBoxUser = new TextBox();
		textBoxUser.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroLoginUser.setVisible(false);
				lblMensagemErroLogin.setVisible(false);
			}
		});
		absolutePanel_1.add(textBoxUser, 74, 99);
		textBoxUser.setSize("169px", "13px");
		
		Label lblPassword = new Label("Senha:");
		lblPassword.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_1.add(lblPassword, 10, 126);
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		passwordTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroSenhaUser.setVisible(false);
				lblMensagemErroLogin.setVisible(false);
			}
		});
		absolutePanel_1.add(passwordTextBox, 74, 126);
		passwordTextBox.setSize("169px", "17px");
		
		
		lblErroLoginUser = new Label("");
		lblErroLoginUser.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel_1.add(lblErroLoginUser, 257, 99);
		lblErroLoginUser.setSize("15px", "15px");
		lblErroLoginUser.setVisible(false);
		
		lblErroSenhaUser = new Label("");
		lblErroSenhaUser.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel_1.add(lblErroSenhaUser, 257, 126);
		lblErroSenhaUser.setSize("15px", "20px");
		lblErroSenhaUser.setVisible(false);
		
		TextButton button = new TextButton("Login");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxUser.getText().length() == 0|| passwordTextBox.getText().length() == 0){
					//Window.alert("Digite todos os campos corretamente");
					if(textBoxUser.getText().length() == 0){
						lblErroLoginUser.setText("*");
						lblErroLoginUser.setVisible(true);
					}
					if(passwordTextBox.getText().length() == 0){
						lblErroSenhaUser.setText("*");
						lblErroSenhaUser.setVisible(true);
					}
					lblMensagemErroLogin.setText("Preencha o(s) campo(s) corretamente");
					lblMensagemErroLogin.setVisible(true);
				} else {
					 abrirSessaoGUI(textBoxUser, passwordTextBox);
				}
			}
		});
		absolutePanel_1.add(button, 168, 193);
		button.setSize("81px", "24px");
		
		CheckBox checkBox = new CheckBox("lembrar-me");
		checkBox.setHTML("Lembrar-me");
		absolutePanel_1.add(checkBox, 168, 226);
		
		lblMensagemErroLogin = new Label("Mensagem de erro");
		lblMensagemErroLogin.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel_1.add(lblMensagemErroLogin, 84, 157);
		lblMensagemErroLogin.setSize("169px", "16px");
		
		Hyperlink hprlnkLoginViaFacebook = new Hyperlink("Login Via Facebook", false, "newHistoryToken");
		absolutePanel_1.add(hprlnkLoginViaFacebook, 135, 10);
		lblMensagemErroLogin.setVisible(false);

		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absolutePanel_2.setStyleName("h2");
		absolutePanel.add(absolutePanel_2, 194, 222);
		absolutePanel_2.setSize("465px", "353px");
		
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
		textBoxNome.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErronome.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(textBoxNome, 183, 50);
		textBoxNome.setSize("208px", "17px");
		
		textBoxLogin = new TextBox();
		textBoxLogin.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrologin.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(textBoxLogin, 183, 81);
		textBoxLogin.setSize("208px", "17px");
		
		TextBoxSenha = new PasswordTextBox();
		TextBoxSenha.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrosenha.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(TextBoxSenha, 183, 112);
		TextBoxSenha.setSize("208px", "17px");
		
		TextBoxSenha2 = new PasswordTextBox();
		TextBoxSenha2.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrosenha_1.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(TextBoxSenha2, 183, 143);
		TextBoxSenha2.setSize("208px", "17px");
		
		textBoxEndereco = new TextBox();
		textBoxEndereco.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroendereco.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(textBoxEndereco, 183, 180);
		textBoxEndereco.setSize("208px", "17px");
		
		textBoxEmail = new TextBox();
		textBoxEmail.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroemail.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel_2.add(textBoxEmail, 183, 211);
		textBoxEmail.setSize("208px", "17px");
		
		TextButton textButton = new TextButton("Cadastrar");
		textButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxNome.getText().length() == 0|| textBoxLogin.getText().length() == 0 || textBoxEndereco.getText().length() == 0 || 
						textBoxEmail.getText().length() == 0 || TextBoxSenha.getText().length() == 0 || TextBoxSenha2.getText().length() == 0){
					//Window.alert("Digite todos os campos corretamente");
					if(textBoxNome.getText().length() == 0){
						lblErronome.setText("*");
						lblErronome.setVisible(true);
					}
					if(textBoxLogin.getText().length() == 0){
						lblErrologin.setText("*");
						lblErrologin.setVisible(true);
					}
					if(textBoxEndereco.getText().length() == 0){
						lblErroendereco.setText("*");
						lblErroendereco.setVisible(true);
					}
					if(textBoxEmail.getText().length() == 0){
						lblErroemail.setText("*");
						lblErroemail.setVisible(true);
					}
					if(TextBoxSenha.getText().length() == 0){
						lblErrosenha.setText("*");
						lblErrosenha.setVisible(true);
					}
					if(TextBoxSenha2.getText().length() == 0){
						lblErrosenha_1.setText("*");
						lblErrosenha_1.setVisible(true);
					}
					lblMensagemDeErro.setText("Campo(s) obrigatório(s)!");
					lblMensagemDeErro.setVisible(true);
				} else if(!TextBoxSenha.getText().equals(TextBoxSenha2.getText())){
					//Window.alert("Senha inválida");
					lblErrosenha.setText("*");
					lblErrosenha_1.setText("*");
					lblErrosenha.setVisible(true);
					lblErrosenha_1.setVisible(true);
					lblMensagemDeErro.setText("Senha incorreta!");
					lblMensagemDeErro.setVisible(true);
				} else {
					cadastraUsuarioGUI(textBoxLogin, TextBoxSenha, textBoxNome, textBoxEndereco, textBoxEmail);
				}
			}
		});
		absolutePanel_2.add(textButton, 254, 279);
		textButton.setSize("145px", "47px");
		
		Label lblCadastrese = new Label("Faça seu cadastro!");
		lblCadastrese.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel_2.add(lblCadastrese, 188, 0);
		
		lblMensagemDeErro = new Label("mensagem de erro");
		lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
		absolutePanel_2.add(lblMensagemDeErro, 193, 242);
		lblMensagemDeErro.setSize("213px", "31px");
		lblMensagemDeErro.setVisible(false);
		
		lblErrosenha = new Label("ErroSenha");
		lblErrosenha.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErrosenha, 405, 112);
		lblErrosenha.setVisible(false);
		
		lblErrosenha_1 = new Label("ErroSenha2");
		lblErrosenha_1.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErrosenha_1, 405, 143);
		lblErrosenha_1.setVisible(false);
		
		lblErronome = new Label("ErroNome");
		lblErronome.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErronome, 405, 50);
		lblErronome.setVisible(false);
		
		lblErrologin = new Label("ErroLogin");
		lblErrologin.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErrologin, 405, 81);
		lblErrologin.setVisible(false);
		
		lblErroendereco = new Label("ErroEndereco");
		lblErroendereco.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErroendereco, 404, 179);
		lblErroendereco.setVisible(false);
		
		lblErroemail = new Label("ErroEmail");
		lblErroemail.setStyleName("gwt-LabelEstradaSolidaria6");
		absolutePanel_2.add(lblErroemail, 404, 211);
		
		SimplePanel simplePanelAssinatura = new SimplePanel();
		simplePanelAssinatura.setStylePrimaryName("gwt-AssinaturaPanel");
		absolutePanel_2.add(simplePanelAssinatura, 150, 365);
		simplePanelAssinatura.setSize("706px", "78px");
		lblErroemail.setVisible(false);
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_3, 194, 59);
		absolutePanel_3.setSize("330px", "111px");
		
		Label label_9 = new Label("Bem-vindo ao Estrada Solidária!");
		label_9.setStyleName("gwt-LabelEstradaSolidaria2");
		label_9.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		label_9.setSize("216px", "29px");
		absolutePanel_3.add(label_9, 48, 24);
		
		Image homePageImage = new Image(resources.getHomePageImage());
		absolutePanel.add(homePageImage, 598, 41);
		homePageImage.setSize("225px", "129px");
		
		AbsolutePanel absolutePanel_4 = new AbsolutePanel();
		absolutePanel_4.setStyleName("gwt-AssinaturaPanel");
		absolutePanel.add(absolutePanel_4, 0, 615);
		absolutePanel_4.setSize("1384px", "69px");
		
		Label lblRepositrioDeDesenvolvimento = new Label("Repositório de desenvolvimento:");
		absolutePanel_4.add(lblRepositrioDeDesenvolvimento, 437, 37);
		
		Anchor hprlnkRepositrioDeDesenvolvimento = new Anchor("https://github.com/guilhermemg/SIProject", false, "newHistoryToken");
		hprlnkRepositrioDeDesenvolvimento.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				//TODO fazer acao de hyperlink
			}
		});
		
		absolutePanel_4.add(hprlnkRepositrioDeDesenvolvimento, 664, 37);
		
		Label lblAssinaturaDoSistema = new Label("Estrada Solidária foi desenvolvido por Guilherme Gadelha, Hemã Vidal, Ítalo Silva e Leonardo Santos.");
		absolutePanel_4.add(lblAssinaturaDoSistema, 381, 10);
		lblAssinaturaDoSistema.setSize("706px", "24px");
		
	}
	
	protected void cadastraUsuarioGUI(final TextBox txtBoxLogin, final PasswordTextBox txtBoxPassword, TextBox txtBbxNome, TextBox txtBoxEndereco, TextBox txtBoxEmail) {
		String login = txtBoxLogin.getText(), senha = txtBoxPassword.getText(),
				nome = txtBbxNome.getText(), endereco = txtBoxEndereco.getText(), email = txtBoxEmail.getText();
		
		estradaService.criarUsuario(login, senha, nome, endereco, email, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				lblMensagemDeErro.setText(this.toString());
				lblMensagemDeErro.setVisible(true);
				textBoxNome.setText("");
				textBoxEmail.setText("");
				textBoxEndereco.setText("");
				textBoxLogin.setText("");
				TextBoxSenha.setText("");
				TextBoxSenha2.setText("");
				
			}

			@Override
			public void onSuccess(Void result) {
				abrirSessaoGUI(txtBoxLogin, txtBoxPassword);
			}
		  });
	}
	
	protected void abrirSessaoGUI(TextBox textBoxLogin, PasswordTextBox textBoxPassword) {
	String login = textBoxLogin.getText(), senha = textBoxPassword.getText();
		
		estradaService.abrirSessao(login, senha, new AsyncCallback<Integer>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				lblMensagemErroLogin.setText(caught.getMessage());
				lblMensagemErroLogin.setVisible(true);
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
				lblMensagemErroLogin.setText(caught.getMessage());
				lblMensagemErroLogin.setVisible(true);
			}

			@Override
			public void onSuccess(String[] result) {
				estrada.setStatePanel(new StatePerfil(estrada, estradaService, result));
			}
		});
	}
}
