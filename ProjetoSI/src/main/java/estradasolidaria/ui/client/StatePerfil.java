package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;

public class StatePerfil extends Composite {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	final ScrollPanel scrollPanel;
	private DockPanel dockPanel;
	private AbsolutePanel headerPanel;
	private AbsolutePanel leftSideBarPanel;
	private AbsolutePanel rightSidebarPanel;
	private AbsolutePanel bodyPanel;
	private AbsolutePanel mainPanel;
	private String[] dadosUsuario;
	private Integer idSessao;
	
	@SuppressWarnings("static-access")
	public StatePerfil(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService, String[] result) {
		Resources resources = GWT.create(Resources.class);
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		this.dadosUsuario = result;
		idSessao = estrada.getIdSessaoAberta();
		
		//Atualiza o tamanho do dockPanel para o tamanho redimensionado	
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				String comprimentoDaTela = event.getWidth() + "px";
				String alturaDaTela = event.getHeight() + "px";
				setSize(comprimentoDaTela, alturaDaTela);
				
				dockPanel.setSize(comprimentoDaTela, alturaDaTela);
			}
		});
		
		mainPanel = new AbsolutePanel();
		initWidget(mainPanel);
		mainPanel.setSize("1398px", "814px");
		
		dockPanel = new DockPanel();
		mainPanel.add(dockPanel, 10, 10);
		dockPanel.setSize("1344px", "693px");
		
		headerPanel = new AbsolutePanel();
		headerPanel.setStyleName("headerPainelPerfil");
		dockPanel.add(headerPanel, DockPanel.NORTH);
		headerPanel.setSize("1338px", "172px");
		
		Label lblNomeDoUsuario = new Label("Olá " + dadosUsuario[2] + "!");
		headerPanel.add(lblNomeDoUsuario, 155, 10);
		lblNomeDoUsuario.setStyleName("gwt-LabelEstradaSolidaria7");
		headerPanel.add(lblNomeDoUsuario, 155, 10);
		lblNomeDoUsuario.setSize("126px", "17px");
		
		
		Label lblEndereco = new Label("Endereco: " + dadosUsuario[3]);
		lblEndereco.setStyleName("gwt-LabelEstradaSolidaria7");
		headerPanel.add(lblEndereco, 155, 33);
		
		Label lblEmail = new Label("Email: " + dadosUsuario[4]);
		lblEmail.setStyleName("gwt-LabelEstradaSolidaria7");
		headerPanel.add(lblEmail, 155, 55);
		
		MenuBar menuBar = new MenuBar(false);
		headerPanel.add(menuBar, 1127, 10);
		menuBar.setSize("168px", "19px");
		
		MenuItem menuItemOpcoes = new MenuItem("Editar Perfil", false, new Command() {
			public void execute() {
				scrollPanel.clear();
				bodyPanel.clear();
				scrollPanel.setVisible(false);
				Widget editarPerfil= new StateEditarPerfil(estradaSolidariaService, dadosUsuario);
				bodyPanel.add(editarPerfil);
				bodyPanel.setVisible(true);
				editarPerfil.setSize("100%", "100%");
			}
		});
		menuBar.addItem(menuItemOpcoes);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		
		MenuItem menuItemSair = new MenuItem("Sair", false, new Command() {
			public void execute() {
				encerrarSessaoGUI(estrada.getIdSessaoAberta());
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada, estradaSolidariaService);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		menuBar.addItem(menuItemSair);
		
		Image photoPerfil = new Image(resources.getGenericUserImage());
		headerPanel.add(photoPerfil, 23, 10);
		photoPerfil.setSize("126px", "132px");
		
		final TextBox txtbxPesquisarPorUsurio = new TextBox();
		txtbxPesquisarPorUsurio.setStyleName("gwt-LabelEstradaSolidaria8");
		txtbxPesquisarPorUsurio.setText("Pesquisar por usuário");
		headerPanel.add(txtbxPesquisarPorUsurio, 583, 10);
		txtbxPesquisarPorUsurio.setSize("286px", "13px");
		
		TextButton txtbtnOk = new TextButton("OK");
		txtbtnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pesquisarUsuarioGUI(txtbxPesquisarPorUsurio.getText());
			}
		});
		headerPanel.add(txtbtnOk, 876, 10);
		txtbtnOk.setSize("37px", "28px");
		
		leftSideBarPanel = new AbsolutePanel();
		leftSideBarPanel.setStylePrimaryName("painelPerfil2");
		dockPanel.add(leftSideBarPanel, DockPanel.WEST);
		leftSideBarPanel.setSize("141px", "493px");
		
		TextButton txtbtnCadastrarCarona = new TextButton("Cadastrar Carona");
		leftSideBarPanel.add(txtbtnCadastrarCarona, 10, 80);
		txtbtnCadastrarCarona.setSize("122px", "64px");
		
		TextButton txtbtnPesquisarCarona = new TextButton("Pesquisar Carona");
		leftSideBarPanel.add(txtbtnPesquisarCarona, 10, 219);
		txtbtnPesquisarCarona.setSize("122px", "63px");
		
		TextButton btnInicio = new TextButton("Início");
		leftSideBarPanel.add(btnInicio, 10, 10);
		btnInicio.setSize("122px", "64px");
		
		TextButton txtbtnMeusInteresses = new TextButton("Meus Interesses");
		leftSideBarPanel.add(txtbtnMeusInteresses, 10, 288);
		txtbtnMeusInteresses.setSize("122px", "56px");
		
		TextButton txtbtnMinhasCaronas = new TextButton("Minhas Caronas");
		leftSideBarPanel.add(txtbtnMinhasCaronas, 10, 150);
		txtbtnMinhasCaronas.setSize("122px", "63px");
		
		TextButton txtbtnSugestoes = new TextButton("Minhas Sugestões\n");
		leftSideBarPanel.add(txtbtnSugestoes, 10, 350);
		txtbtnSugestoes.setSize("122px", "63px");
		
		TextButton txtbtnSolicitaesFeitasPara = new TextButton("Solicitações");
		txtbtnSolicitaesFeitasPara.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				solicitacoesGUI();
			}
		});
		leftSideBarPanel.add(txtbtnSolicitaesFeitasPara, 11, 419);
		txtbtnSolicitaesFeitasPara.setSize("121px", "56px");
		
		bodyPanel = new AbsolutePanel();
		bodyPanel.setStyleName("painelCentralPerfil");
		dockPanel.add(bodyPanel, DockPanel.WEST);
		bodyPanel.setSize("950px", "493px");
		
		scrollPanel = new ScrollPanel();
		scrollPanel.setTouchScrollingDisabled(false);
		scrollPanel.setStyleName("painelCentralPerfil");
		dockPanel.add(scrollPanel, DockPanel.WEST);
		scrollPanel.setSize("950px", "493px");
		scrollPanel.setVisible(false);
		
		rightSidebarPanel = new AbsolutePanel();
		rightSidebarPanel.setStylePrimaryName("painelPerfil1");
		dockPanel.add(rightSidebarPanel, DockPanel.EAST);
		rightSidebarPanel.setSize("220px", "494px");
		
		Label lblAmigos = new Label("Amigos:");
		lblAmigos.setStyleName("gwt-LabelEstradaSolidaria8");
		rightSidebarPanel.add(lblAmigos, 25, 0);
		lblAmigos.setSize("57px", "15px");
		
		PushButton txtbtnVerAmigos = new PushButton("Ver todos");
		txtbtnVerAmigos.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				visualizarAmigosGUI();
			}
		});
		rightSidebarPanel.add(txtbtnVerAmigos, 125, 0);
		txtbtnVerAmigos.setSize("60px", "15px");
		
		DatePicker datePicker = new DatePicker();
		rightSidebarPanel.add(datePicker, 23, 320);
		datePicker.setSize("170px", "145px");
		
		Label lblProximasCaronas = new Label("Próximas Caronas");
		lblProximasCaronas.setStyleName("gwt-LabelEstradaSolidaria8");
		rightSidebarPanel.add(lblProximasCaronas, 35, 297);
		
		Image image = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image, 25, 26);
		image.setSize("76px", "74px");
		
		Image image_1 = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image_1, 125, 26);
		image_1.setSize("76px", "74px");
		
		Image image_2 = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image_2, 25, 120);
		image_2.setSize("76px", "74px");
		
		Image image_3 = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image_3, 125, 120);
		image_3.setSize("76px", "74px");
		
		Image image_4 = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image_4, 25, 217);
		image_4.setSize("76px", "74px");
		
		Image image_5 = new Image(resources.getGenericLittleUserImage());
		rightSidebarPanel.add(image_5, 125, 217);
		image_5.setSize("76px", "74px");
		
		inicio();
		
		btnInicio.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				inicio();
			}
		});
		
		txtbtnMinhasCaronas.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			      	minhasCaronasGUI();
				}
			});
		
		txtbtnCadastrarCarona.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				cadastrarCaronaGUI();
			}
		});
		
		txtbtnPesquisarCarona.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				pesquisarCaronaGUI();
			}
		});
		
		txtbtnSugestoes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				minhasSugestoesGUI();
			}
		});
		
		txtbtnMeusInteresses.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				meusInteressesGUI();
			}
		});
	}

	protected void visualizarAmigosGUI() {
		estradaSolidariaService.getListaDeAmigos(idSessao, new AsyncCallback<List<GWTUsuario>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()); 
			}

			@Override
			public void onSuccess(List<GWTUsuario> result) {
				bodyPanel.clear();
				scrollPanel.clear();
				Widget solicitacoes = new StateVisualizarAmigos(estrada, estradaSolidariaService, result, bodyPanel);
				bodyPanel.add(solicitacoes);
				bodyPanel.setVisible(true);
				solicitacoes.setSize("100%", "100%");
				
			}
		});
	}

	protected void solicitacoesGUI() {
		bodyPanel.clear();
		scrollPanel.clear();
		Widget solicitacoes = new StateSolicitacoes(estrada, estradaSolidariaService);
		bodyPanel.add(solicitacoes);
		bodyPanel.setVisible(true);
		solicitacoes.setSize("100%", "100%");
	}

	protected void encerrarSessaoGUI(Integer idSessao) {
		estradaSolidariaService.encerrarSessao(idSessao, new AsyncCallback<Void>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()); 
			}
			
			@Override
			public void onSuccess(Void result) {
				// does nothing
			}
		});
	}

	protected void pesquisarUsuarioGUI(String text) {
		estradaSolidariaService.pesquisaUsuariosNoSistema(text, new AsyncCallback<List<GWTUsuario>>(){ 
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()); 
			}

			@Override
			public void onSuccess(List<GWTUsuario> result) {
				bodyPanel.clear();
				Widget pesquisarCarona = new StateUsuariosEncontrados(estrada, estradaSolidariaService, result, bodyPanel);
				bodyPanel.add(pesquisarCarona);
				bodyPanel.setVisible(true);
				pesquisarCarona.setSize("100%", "100%");
			}
		  });
		
	}

	protected void inicio() {
		bodyPanel.clear();
		scrollPanel.clear();
		Widget inicio = new StateInicio(estrada, estradaSolidariaService, bodyPanel, scrollPanel);
		bodyPanel.add(inicio);
		bodyPanel.setVisible(true);
		inicio.setSize("100%", "100%");
	}

	protected void cadastrarCaronaGUI() {
		scrollPanel.clear();
		scrollPanel.setVisible(false);
		bodyPanel.clear();
		Widget cadastrarCarona = new StateCadastroCaronas(estrada, estradaSolidariaService);
		bodyPanel.add(cadastrarCarona);
		bodyPanel.setVisible(true);
		cadastrarCarona.setSize("100%", "100%");
	}

	protected void pesquisarCaronaGUI() {
		bodyPanel.clear();
		scrollPanel.clear();
		scrollPanel.setVisible(false);
		Widget pesquisarCarona = new StatePesquisaCarona(estrada, estradaSolidariaService, bodyPanel);
		bodyPanel.add(pesquisarCarona);
		bodyPanel.setVisible(true);
		pesquisarCarona.setSize("100%", "100%");
	}
	
	protected void minhasCaronasGUI() {
		scrollPanel.clear();
		scrollPanel.setVisible(false);
		bodyPanel.clear();
		Widget minhasCarona= new StateMinhasCaronas(estrada, estradaSolidariaService);
		bodyPanel.add(minhasCarona);
		minhasCarona.setSize("100%", "100%");
	}
	
	protected void meusInteressesGUI() {
		scrollPanel.clear();
		scrollPanel.setVisible(false);
		bodyPanel.clear();
		StateMeusInteresses mi = new StateMeusInteresses(estrada, estradaSolidariaService);
		bodyPanel.add(mi);
		mi.setSize("100%", "100%");
	}
	
	private void minhasSugestoesGUI() {
		scrollPanel.clear();
		scrollPanel.setVisible(false);
		bodyPanel.clear();
		Widget ms = new StateMinhasSugestoes(estradaSolidariaService);
		ms.setSize("100%", "100%");
		bodyPanel.add(ms);
	}
}
