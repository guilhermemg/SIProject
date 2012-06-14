package estradasolidaria.ui.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StateHomePage extends AbsolutePanel {
	StatePanel state;
	final EstradaSolidaria estrada;
	final Widget panel= this;
	Image imagem;
	
	EstradaSolidariaServiceAsync estradaSolidariaService;
	
	public StateHomePage(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
		Resources resources = GWT.create(Resources.class);
		//imagem = new Image("http://www.fhwa.dot.gov/policyinformation/motorfuel/hwytaxes/2008/index_clip_image002.jpg");
		
		setStylePrimaryName("dialogVPanel");
		
		setSize("838px", "657px");
		
		AbsolutePanel absPanelLogin = new AbsolutePanel();
		absPanelLogin.setStylePrimaryName("gwt-LoginPanel");
		add(absPanelLogin, 494, 151);
		absPanelLogin.setSize("268px", "214px");
		
		Label lblL = new Label("Já é usuário? Faça Login.");
		lblL.setStyleName("gwt-LabelEstradaSolidaria");
		absPanelLogin.add(lblL, 23, 10);
		
		Label lblUsurio = new Label("Usuário:");
		lblUsurio.setStyleName("gwt-LabelEstradaSolidaria4");
		absPanelLogin.add(lblUsurio, 10, 51);
		lblUsurio.setSize("58px", "15px");
		
		final TextBox txtboxNome = new TextBox();
		absPanelLogin.add(txtboxNome, 74, 51);
		txtboxNome.setSize("180px", "13px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absPanelLogin.add(lblSenha, 10, 78);
		
		final PasswordTextBox textBoxPassword = new PasswordTextBox();
		absPanelLogin.add(textBoxPassword, 74, 78);
		textBoxPassword.setSize("180px", "17px");
		
		Button btnLogin = new Button("Login");
		btnLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(txtboxNome.getText().length() == 0|| textBoxPassword.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else {
					 abrirSessaoGUI(txtboxNome, textBoxPassword);
				}
			}
		});
		
		
		absPanelLogin.add(btnLogin, 170, 128);
		btnLogin.setSize("81px", "24px");
		
		CheckBox chckbxLembrarme = new CheckBox("lembrar-me");
		chckbxLembrarme.setHTML("Lembrar-me");
		absPanelLogin.add(chckbxLembrarme, 170, 168);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("gwt-LoginPanel");
		absolutePanel.setStylePrimaryName("gwt-LoginPanel");
		add(absolutePanel, 212, 151);
		absolutePanel.setSize("245px", "214px");
		
		Label lblNaoCadastrado = new Label("Ainda não é Usuário? ");
		lblNaoCadastrado.setStyleName("gwt-LabelEstradaSolidaria");
		absolutePanel.add(lblNaoCadastrado, 27, 10);
		lblNaoCadastrado.setSize("193px", "31px");
		
		Button btnCadastro = new Button("Cadastre-se Aqui!");
		
		btnCadastro.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cadastraUsuarioGUI();
			}
		});
		btnCadastro.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(btnCadastro, 27, 92);
		btnCadastro.setSize("182px", "40px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStyleName("gwt-LabelEstradaSolidaria2");
		add(absolutePanel_1, 212, 20);
		absolutePanel_1.setSize("550px", "100px");
		
		Label lblBemvindoAoEstrada = new Label("Bem-vindo ao Estrada Solidária!");
		lblBemvindoAoEstrada.setStyleName("gwt-LabelHomePage2");
		absolutePanel_1.add(lblBemvindoAoEstrada, 85, 38);
		imagem = new Image(resources.home());
		add(imagem, 151, 412);
		imagem.setSize("368px", "222px");
	}

	protected void cadastraUsuarioGUI() {
		estrada.rootPanel.remove(panel);
		Widget newPanel = new StateCadastroUsuario(estrada, estradaSolidariaService);
		newPanel.setSize("600px", "417px");
		estrada.setStatePanel(newPanel);
	}

	private void abrirSessaoGUI(TextBox userName, PasswordTextBox passwordTextBox) {
		String login = userName.getText(), senha = passwordTextBox.getText();
		
		estradaSolidariaService.abrirSessao(login, senha, new AsyncCallback<Integer>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				EstradaSolidaria.setIdSessaoAberta(result);
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StatePerfil2(estrada, estradaSolidariaService);
				newPanel.setSize("781px", "592px");
				estrada.setStatePanel(newPanel);
			}
		  });
	}
}
