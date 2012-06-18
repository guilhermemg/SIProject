package estradasolidaria.ui.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class StateHomePage extends AbsolutePanel {
	StatePanel state;
	final EstradaSolidaria estrada;
	final Widget panel= this;
	Image imagem;
	
	EstradaSolidariaServiceAsync estradaSolidariaService;
	private DockPanel dockPanel;
	private AbsolutePanel absolutePanel_1;
	private AbsolutePanel absolutePanel_3;
	private AbsolutePanel absolutePanel;
	private AbsolutePanel absolutePanel_2;
	private AbsolutePanel panelCadastro;
	private Button btnCadastro;
	private CheckBox chckbxLembrarme;
	private Integer idSessao; 
	
	public StateHomePage(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
		Resources resources = GWT.create(Resources.class);
		//imagem = new Image("http://www.fhwa.dot.gov/policyinformation/motorfuel/hwytaxes/2008/index_clip_image002.jpg");
		
		setStylePrimaryName("dialogVPanel");
		
		//Redimensiona o painel
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				String comprimentoDaTela = event.getWidth() + "px";
				String alturaDaTela = event.getHeight() + "px";
				setSize(comprimentoDaTela, alturaDaTela);
				
				dockPanel.setSize(comprimentoDaTela, alturaDaTela);
				
			}
		});
		dockPanel = new DockPanel();
		add(dockPanel, 30, 25);
		dockPanel.setSize("100%", "100%");
		
		absolutePanel_1 = new AbsolutePanel();
		dockPanel.add(absolutePanel_1, DockPanel.NORTH);
		absolutePanel_1.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel_1.setSize("100%", "100%");
		
		absolutePanel_3 = new AbsolutePanel();
		absolutePanel_1.add(absolutePanel_3, 428, 95);
		absolutePanel_3.setSize("314px", "109px");
		
		Label lblBemvindoAoEstrada = new Label("Bem-vindo ao Estrada Solidária!");
		absolutePanel_3.add(lblBemvindoAoEstrada, 49, 40);
		lblBemvindoAoEstrada.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblBemvindoAoEstrada.setStyleName("gwt-LabelHomePage2");
		lblBemvindoAoEstrada.setSize("216px", "29px");
		
		absolutePanel = new AbsolutePanel();
		dockPanel.add(absolutePanel, DockPanel.CENTER);
		absolutePanel.setSize("100%", "100%");
		
		AbsolutePanel panelLogin = new AbsolutePanel();
		absolutePanel.add(panelLogin, 624, 19);
		panelLogin.setStylePrimaryName("gwt-LoginPanel");
		panelLogin.setSize("318px", "218px");
		
		Label lblL = new Label("Já é usuário? Faça Login.");
		lblL.setStyleName("gwt-LabelEstradaSolidaria");
		panelLogin.add(lblL, 23, 10);
		
		Label lblUsurio = new Label("Usuário:");
		lblUsurio.setStyleName("gwt-LabelEstradaSolidaria4");
		panelLogin.add(lblUsurio, 10, 51);
		lblUsurio.setSize("58px", "15px");
		
		final TextBox txtboxNome = new TextBox();
		panelLogin.add(txtboxNome, 74, 51);
		txtboxNome.setSize("180px", "13px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		panelLogin.add(lblSenha, 10, 78);
		
		final PasswordTextBox textBoxPassword = new PasswordTextBox();
		panelLogin.add(textBoxPassword, 74, 78);
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
		
		
		panelLogin.add(btnLogin, 170, 128);
		btnLogin.setSize("81px", "24px");
		
		chckbxLembrarme = new CheckBox("lembrar-me");
		chckbxLembrarme.setHTML("Lembrar-me");
		panelLogin.add(chckbxLembrarme, 170, 168);
		
		panelCadastro = new AbsolutePanel();
		absolutePanel.add(panelCadastro, 213, 19);
		panelCadastro.setStyleName("gwt-LoginPanel");
		panelCadastro.setStylePrimaryName("gwt-LoginPanel");
		panelCadastro.setSize("346px", "218px");
		
		Label lblNaoCadastrado = new Label("Ainda não é Usuário? ");
		lblNaoCadastrado.setStyleName("gwt-LabelEstradaSolidaria");
		panelCadastro.add(lblNaoCadastrado, 27, 10);
		lblNaoCadastrado.setSize("193px", "31px");
		
		btnCadastro = new Button("Cadastre-se Aqui!");
		
		btnCadastro.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cadastraUsuarioGUI();
			}
		});
		btnCadastro.setStyleName("gwt-LabelEstradaSolidaria4");
		panelCadastro.add(btnCadastro, 27, 92);
		btnCadastro.setSize("182px", "40px");
		
		absolutePanel_2 = new AbsolutePanel();
		dockPanel.add(absolutePanel_2, DockPanel.SOUTH);
		absolutePanel_2.setSize("100%", "100%");
		imagem = new Image(resources.home());
		absolutePanel_2.add(imagem, 408, 41);
		imagem.setSize("353px", "217px");
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
				getUsuarioGUI();
			}
		  });
	}
	
	protected void getUsuarioGUI() {
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaSolidariaService.getUsuario(idSessao, new AsyncCallback<String[]>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String[] result) {
				estrada.setStatePanel(new StatePerfil2(estrada, estradaSolidariaService, result));
			}
		});
	}
}
