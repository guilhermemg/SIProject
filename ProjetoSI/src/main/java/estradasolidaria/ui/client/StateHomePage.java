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

import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.Sessao;

public class StateHomePage extends AbsolutePanel implements StatePanel {
//	EstradaSolidariaController sistema = EstradaSolidariaController.getInstance();
	
	StatePanel state;
	final EstradaSolidaria estrada;
	final Widget panel= this;
	Image imagem;
	
	/**
	 * Create a remote service proxy to talk to the server-side EstradaSolidaria service.
	 */
	private final EstradaSolidariaServiceAsync estradaSolidariaService = GWT
			.create(EstradaSolidariaService.class);
	
	public StateHomePage(EstradaSolidaria estradaSolidaria) {
		estrada = estradaSolidaria;
		
		Resources resources = GWT.create(Resources.class);
		//imagem = new Image("http://www.fhwa.dot.gov/policyinformation/motorfuel/hwytaxes/2008/index_clip_image002.jpg");
		
		setStylePrimaryName("dialogVPanel");
		
		setSize("838px", "657px");
		
		AbsolutePanel absPanelLogin = new AbsolutePanel();
		absPanelLogin.setStylePrimaryName("gwt-LoginPanel");
		add(absPanelLogin, 319, 151);
		absPanelLogin.setSize("268px", "214px");
		
		Label lblL = new Label("Já é usuário? Faça Login.");
		lblL.setStyleName("gwt-LabelEstradaSolidaria");
		absPanelLogin.add(lblL, 23, 10);
		
		Label lblUsurio = new Label("Usuário:");
		lblUsurio.setStyleName("gwt-LabelEstradaSolidaria4");
		absPanelLogin.add(lblUsurio, 10, 51);
		lblUsurio.setSize("58px", "15px");
		
		final TextBox userName = new TextBox();
		absPanelLogin.add(userName, 74, 51);
		userName.setSize("180px", "13px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absPanelLogin.add(lblSenha, 10, 78);
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		absPanelLogin.add(passwordTextBox, 74, 78);
		passwordTextBox.setSize("180px", "17px");
		
		Button btnLogin = new Button("Login");
		btnLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(userName.getText().length() == 0|| passwordTextBox.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else {
					String login = userName.getText(),
						   senha = passwordTextBox.getText();
					
					try {
						estradaSolidariaService.abrirSessao(login, senha, 
								new AsyncCallback<Sessao>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("RPC Falhou!");
									}

									@Override
									public void onSuccess(Sessao result) {
										Window.alert("RPC funcionou");
									}
						});
						
						estrada.rootPanel.remove(panel);
						Widget newPanel = new StatePerfil2(estrada);
						newPanel.setSize("781px", "592px");
						estrada.setStatePanel(newPanel);
					} catch (Exception e) {
						Window.alert(e.getMessage());
					}
					
					
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
		add(absolutePanel, 37, 151);
		absolutePanel.setSize("245px", "214px");
		
		Label lblNaoCadastrado = new Label("Ainda não é Usuário? ");
		lblNaoCadastrado.setStyleName("gwt-LabelEstradaSolidaria");
		absolutePanel.add(lblNaoCadastrado, 27, 10);
		lblNaoCadastrado.setSize("193px", "31px");
		
		Button btnCadastro = new Button("Cadastre-se Aqui!");
		
		btnCadastro.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateCadastroUsuario(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		btnCadastro.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(btnCadastro, 27, 92);
		btnCadastro.setSize("182px", "40px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStyleName("gwt-LabelEstradaSolidaria2");
		add(absolutePanel_1, 37, 21);
		absolutePanel_1.setSize("550px", "100px");
		
		Label lblBemvindoAoEstrada = new Label("Bem-vindo ao Estrada Solidária!");
		lblBemvindoAoEstrada.setStyleName("gwt-LabelHomePage2");
		absolutePanel_1.add(lblBemvindoAoEstrada, 85, 38);
		imagem = new Image(resources.home());
		add(imagem, 151, 412);
		imagem.setSize("368px", "222px");
	}

	

	@Override
	public void nextState() {
		//estrada.setStatePanel(new StateCadastroUsuario());
	}
}
