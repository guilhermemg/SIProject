package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class StatePerfil2 extends Composite {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	final AbsolutePanel bodyPanel;
	public StatePerfil2(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
		AbsolutePanel mainPanel = new AbsolutePanel();
		initWidget(mainPanel);
		mainPanel.setSize("1000px", "710px");
		
		AbsolutePanel leftSideBarPanel = new AbsolutePanel();
		mainPanel.add(leftSideBarPanel, 10, 215);
		leftSideBarPanel.setSize("140px", "202px");
		
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
		
		AbsolutePanel rightSidebarPanel = new AbsolutePanel();
		mainPanel.add(rightSidebarPanel, 748, 215);
		rightSidebarPanel.setSize("233px", "487px");
		
		Label lblAmigos = new Label("Amigos:");
		rightSidebarPanel.add(lblAmigos, 91, 0);
		lblAmigos.setSize("57px", "15px");
		
		FlexTable flexTable = new FlexTable();
		rightSidebarPanel.add(flexTable, 14, 21);
		flexTable.setSize("212px", "213px");
		
//		Image image_1 = new Image((String) null);
//		flexTable.setWidget(0, 0, image_1);
//		
//		Image image_2 = new Image((String) null);
//		flexTable.setWidget(0, 1, image_2);
//		
//		Image image_3 = new Image((String) null);
//		flexTable.setWidget(1, 0, image_3);
//		
//		Image image_4 = new Image((String) null);
//		flexTable.setWidget(1, 1, image_4);
//		
//		Image image_5 = new Image((String) null);
//		flexTable.setWidget(2, 0, image_5);
//		
//		Image image_6 = new Image((String) null);
//		flexTable.setWidget(2, 1, image_6);
		
//		Button txtbtnVisualizarTodosOs = new Button("Ver Todos");
//		absolutePanel_1.add(txtbtnVisualizarTodosOs, 51, 244);
//		txtbtnVisualizarTodosOs.setSize("137px", "28px");
		
		DatePicker datePicker = new DatePicker();
		rightSidebarPanel.add(datePicker, 14, 313);
		datePicker.setSize("210px", "162px");
		
		Label lblProximasCaronas = new Label("Próximas Caronas");
		rightSidebarPanel.add(lblProximasCaronas, 66, 292);
		
		AbsolutePanel headerPanel = new AbsolutePanel();
		mainPanel.add(headerPanel, 10, 43);
		headerPanel.setSize("971px", "140px");
		
		Label lblNomeDoUsuario = new Label("Nome do usuario");
		headerPanel.add(lblNomeDoUsuario, 21, 115);
		
		MenuBar menuBar = new MenuBar(false);
		headerPanel.add(menuBar, 701, 10);
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
		
		bodyPanel = new AbsolutePanel();
		mainPanel.add(bodyPanel, 156, 215);
		bodyPanel.setSize("586px", "487px");

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
		Widget editarPerfil= new StateEditarPerfil();
		bodyPanel.add(editarPerfil);
		editarPerfil.setSize("100%", "100%");
		
	}

	protected void inicio() {
		
		bodyPanel.clear();	
	}

	protected void visualizarCaronaGUI() {
		bodyPanel.clear();
		Widget visualizarCarona= new StateVisualizarCaronas(estrada, estradaSolidariaService);
		bodyPanel.add(visualizarCarona);
		visualizarCarona.setSize("100%", "100%");
		
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
