package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.DockPanel;

public class StatePerfil2 extends Composite {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	final AbsolutePanel bodyPanel;
	private DockPanel dockPanel;
	private AbsolutePanel headerPanel;
	private AbsolutePanel leftSideBarPanel;
	private AbsolutePanel rightSidebarPanel;
	private AbsolutePanel mainPanel;
	public StatePerfil2(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
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
		mainPanel.setSize("1000px", "710px");
		
		dockPanel = new DockPanel();
		mainPanel.add(dockPanel, 10, 10);
		dockPanel.setSize("100%", "100%");
		
		headerPanel = new AbsolutePanel();
		dockPanel.add(headerPanel, DockPanel.NORTH);
		headerPanel.setSize("100%", "100%");
		
		Label lblNomeDoUsuario = new Label("Nome do usuario");
		headerPanel.add(lblNomeDoUsuario, 10, 10);
		
		MenuBar menuBar = new MenuBar(false);
		headerPanel.add(menuBar, 730, 10);
		menuBar.setSize("258px", "19px");
		
		MenuItem menuItemOpcoes = new MenuItem("Editar Perfil", false, new Command() {
			public void execute() {
				editarPerfilGUI();
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
		
		leftSideBarPanel = new AbsolutePanel();
		dockPanel.add(leftSideBarPanel, DockPanel.WEST);
		leftSideBarPanel.setSize("140px", "248px");
		
		Button txtbtnCadastrarCarona = new Button("Cadastrar Carona");
		leftSideBarPanel.add(txtbtnCadastrarCarona, 10, 66);
		txtbtnCadastrarCarona.setSize("122px", "24px");
		
		Button txtbtnVisualizarCaronas = new Button("Visualizar Caronas");
		leftSideBarPanel.add(txtbtnVisualizarCaronas, 10, 111);
		txtbtnVisualizarCaronas.setSize("122px", "24px");
		
		Button txtbtnPesquisarCarona = new Button("Pesquisar carona");
		leftSideBarPanel.add(txtbtnPesquisarCarona, 10, 156);
		txtbtnPesquisarCarona.setSize("122px", "24px");
		
		Button btnInicio = new Button("Inicio");
		leftSideBarPanel.add(btnInicio, 10, 21);
		btnInicio.setSize("122px", "24px");
		
		Button btnMeusInteresses = new Button("Meus Interesses");
		btnMeusInteresses.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		leftSideBarPanel.add(btnMeusInteresses, 10, 196);
		btnMeusInteresses.setSize("122px", "25px");
		
		rightSidebarPanel = new AbsolutePanel();
		dockPanel.add(rightSidebarPanel, DockPanel.EAST);
		rightSidebarPanel.setSize((EstradaSolidaria.comprimentoDoBrowser * 0.2) + "px", "487px");
		
		Label lblAmigos = new Label("Amigos:");
		rightSidebarPanel.add(lblAmigos, 91, 0);
		lblAmigos.setSize("57px", "15px");
		
		FlexTable flexTable = new FlexTable();
		rightSidebarPanel.add(flexTable, 14, 21);
		flexTable.setSize("212px", "213px");
		
		DatePicker datePicker = new DatePicker();
		rightSidebarPanel.add(datePicker, 14, 313);
		datePicker.setSize("210px", "162px");
		
		Label lblProximasCaronas = new Label("Próximas Caronas");
		rightSidebarPanel.add(lblProximasCaronas, 66, 292);
		
		bodyPanel = new AbsolutePanel();
		dockPanel.add(bodyPanel, DockPanel.CENTER);
		bodyPanel.setSize((EstradaSolidaria.comprimentoDoBrowser * 0.6) + "px", "487px");

		btnInicio.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				inicio();
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
		
		txtbtnVisualizarCaronas.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
		
				visualizarCaronaGUI();
			}
		});
	}

	protected void editarPerfilGUI() {
		bodyPanel.clear();
		Widget editarPerfil= new StateEditarPerfil(estradaSolidariaService);
		bodyPanel.add(editarPerfil);
		editarPerfil.setSize("100%", "100%");
		
	}

	protected void inicio() {
		
		bodyPanel.clear();	
	}

	protected void visualizarCaronaGUI() {
		bodyPanel.clear();
		Widget visualizarCarona= new StateVisualizarCaronas(estrada, estradaSolidariaService);
		visualizarCarona.setSize("100%", "100%");
		bodyPanel.add(visualizarCarona);
		
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
}
