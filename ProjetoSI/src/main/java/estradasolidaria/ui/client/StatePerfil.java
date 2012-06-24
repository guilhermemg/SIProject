package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import estradasolidaria.ui.resources.*;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.resources.client.ImageResource;

public class StatePerfil extends Composite {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	final AbsolutePanel bodyPanel;
	private DockPanel dockPanel;
	private AbsolutePanel headerPanel;
	private AbsolutePanel leftSideBarPanel;
	private AbsolutePanel mainPanel;
	private String[] dadosUsuario;
	
	public StatePerfil(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService, String[] result) {
		Resources resources = GWT.create(Resources.class);
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		this.dadosUsuario = result;
		
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
		mainPanel.setSize("1343px", "710px");
		
		dockPanel = new DockPanel();
		mainPanel.add(dockPanel, 10, 10);
		dockPanel.setSize("1333px", "690px");
		
		headerPanel = new AbsolutePanel();
		dockPanel.add(headerPanel, DockPanel.NORTH);
		headerPanel.setSize("1327px", "180px");
		
		Label lblNomeDoUsuario = new Label("Olá " + dadosUsuario[2] + "!");
		headerPanel.add(lblNomeDoUsuario, 33, 153);
		lblNomeDoUsuario.setSize("126px", "17px");
		
		MenuBar menuBar = new MenuBar(false);
		headerPanel.add(menuBar, 1127, 10);
		menuBar.setSize("168px", "19px");
		
		MenuItem menuItemOpcoes = new MenuItem("Editar Perfil", false, new Command() {
			public void execute() {
				bodyPanel.clear();
				Widget editarPerfil= new StateEditarPerfil(estradaSolidariaService, dadosUsuario);
				bodyPanel.add(editarPerfil);
				editarPerfil.setSize("100%", "100%");
			}
		});
		menuBar.addItem(menuItemOpcoes);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		
		MenuItem menuItemSair = new MenuItem("Sair", false, new Command() {
			public void execute() {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada, estradaSolidariaService);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		menuBar.addItem(menuItemSair);
		
		Image photoPerfil = new Image(resources.getGenericUserImage());
		headerPanel.add(photoPerfil, 23, 10);
		photoPerfil.setSize("126px", "139px");
		
		leftSideBarPanel = new AbsolutePanel();
		leftSideBarPanel.setStylePrimaryName("painelPerfil");
		dockPanel.add(leftSideBarPanel, DockPanel.WEST);
		leftSideBarPanel.setSize("152px", "444px");
		
		TextButton txtbtnCadastrarCarona = new TextButton("Cadastrar Carona");
		leftSideBarPanel.add(txtbtnCadastrarCarona, 10, 91);
		txtbtnCadastrarCarona.setSize("122px", "64px");
		
		TextButton txtbtnPesquisarCarona = new TextButton("Pesquisar Carona");
		leftSideBarPanel.add(txtbtnPesquisarCarona, 10, 230);
		txtbtnPesquisarCarona.setSize("122px", "63px");
		
		TextButton btnInicio = new TextButton("Início");
		leftSideBarPanel.add(btnInicio, 10, 21);
		btnInicio.setSize("122px", "64px");
		
		TextButton btnMeusInteresses = new TextButton("Meus Interesses");
		btnMeusInteresses.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//TODO fazer MeusInteresses
			}
		});
		leftSideBarPanel.add(btnMeusInteresses, 10, 299);
		btnMeusInteresses.setSize("122px", "56px");
		
		TextButton buttonMinhasCaronas = new TextButton("Minhas Caronas");
		leftSideBarPanel.add(buttonMinhasCaronas, 10, 161);
		buttonMinhasCaronas.setSize("122px", "63px");
		
		TextButton txtbtnMinhasSugestoes = new TextButton("Minhas Sugestões\n");
		txtbtnMinhasSugestoes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				//TODO fazer Minhas Sugestões
			}
		});
		leftSideBarPanel.add(txtbtnMinhasSugestoes, 10, 361);
		txtbtnMinhasSugestoes.setSize("122px", "63px");
		
		bodyPanel = new AbsolutePanel();
		dockPanel.add(bodyPanel, DockPanel.CENTER);
		bodyPanel.setSize("934px", "487px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStylePrimaryName("painelPerfil");
		dockPanel.add(absolutePanel, DockPanel.EAST);
		absolutePanel.setSize("220px", "488px");
		
		Label label = new Label("Amigos:");
		absolutePanel.add(label, 91, 0);
		label.setSize("57px", "15px");
		
		DatePicker datePicker = new DatePicker();
		absolutePanel.add(datePicker, 10, 315);
		datePicker.setSize("210px", "162px");
		
		Label label_1 = new Label("Próximas Caronas");
		absolutePanel.add(label_1, 66, 292);
		
		Image image = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image, 25, 21);
		image.setSize("76px", "74px");
		
		Image image_1 = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image_1, 125, 21);
		image_1.setSize("76px", "74px");
		
		Image image_2 = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image_2, 25, 115);
		image_2.setSize("76px", "74px");
		
		Image image_3 = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image_3, 125, 115);
		image_3.setSize("76px", "74px");
		
		Image image_4 = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image_4, 25, 212);
		image_4.setSize("76px", "74px");
		
		Image image_5 = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(image_5, 125, 212);
		image_5.setSize("76px", "74px");

		btnInicio.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				inicio();
			}
		});
		
		buttonMinhasCaronas.addClickHandler(new ClickHandler() {
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
	}

	protected void inicio() {
		bodyPanel.clear();	
	}

	protected void cadastrarCaronaGUI() {
		bodyPanel.clear();
		Widget cadastrarCarona = new StateCadastroCaronas(estrada, estradaSolidariaService);
		bodyPanel.add(cadastrarCarona);
		cadastrarCarona.setSize("100%", "100%");
	}

	protected void pesquisarCaronaGUI() {
		bodyPanel.clear();
		Widget pesquisarCarona = new StatePesquisaCarona(estrada, estradaSolidariaService, bodyPanel);
		bodyPanel.add(pesquisarCarona);
		pesquisarCarona.setSize("100%", "100%");
	}
	
	protected void minhasCaronasGUI() {
		bodyPanel.clear();
		Widget visualizarCarona= new StateMinhasCaronas(estrada, estradaSolidariaService);
		visualizarCarona.setSize("100%", "100%");
		bodyPanel.add(visualizarCarona);
	}
}
