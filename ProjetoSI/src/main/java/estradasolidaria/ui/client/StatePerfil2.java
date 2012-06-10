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
	
	public StatePerfil2(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		
		estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1000px", "710px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_2, 10, 215);
		absolutePanel_2.setSize("140px", "348px");
		
		Button txtbtnCadastrarCarona = new Button("Cadastrar Carona");
		absolutePanel_2.add(txtbtnCadastrarCarona, 10, 39);
		
		Button txtbtnVisualizarCaronas = new Button("Visualizar Caronas");
		absolutePanel_2.add(txtbtnVisualizarCaronas, 10, 73);
		txtbtnVisualizarCaronas.setSize("122px", "44px");
		
		Button txtbtnPesquisarCarona = new Button("Pesquisar carona");
		absolutePanel_2.add(txtbtnPesquisarCarona, 10, 123);
		txtbtnPesquisarCarona.setSize("122px", "28px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 748, 215);
		absolutePanel_1.setSize("233px", "487px");
		
		Label lblAmigos = new Label("Amigos:");
		absolutePanel_1.add(lblAmigos, 91, 0);
		lblAmigos.setSize("57px", "15px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel_1.add(flexTable, 14, 21);
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
		absolutePanel_1.add(datePicker, 14, 313);
		datePicker.setSize("210px", "162px");
		
		Label lblProximasCaronas = new Label("Próximas Caronas");
		absolutePanel_1.add(lblProximasCaronas, 66, 292);
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_3, 10, 43);
		absolutePanel_3.setSize("971px", "140px");
		
		Label lblNomeDoUsuario = new Label("Nome do usuario");
		absolutePanel_3.add(lblNomeDoUsuario, 21, 115);
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel_3.add(menuBar, 701, 10);
		menuBar.setSize("258px", "19px");
		
		MenuItem menuItemOpcoes = new MenuItem("Opções", false, (Command) null);
		menuBar.addItem(menuItemOpcoes);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		
		MenuItem menuItemSair = new MenuItem("Sair", false, new Command() {
			public void execute() {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada, estradaSolidariaService);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
				Window.alert("Logoff efetuado com sucesso!");
			}
		});
		menuBar.addItem(menuItemSair);
		
		AbsolutePanel absolutePanel_4 = new AbsolutePanel();
		absolutePanel_3.add(absolutePanel_4, 147, 150);
		absolutePanel_4.setSize("586px", "461px");
		
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

	protected void visualizarCaronaGUI() {
		// TODO Auto-generated method stub
		
	}

	protected void cadastrarCaronaGUI() {
		// TODO Auto-generated method stub
		
	}

	protected void pesquisarCaronaGUI() {
		// TODO Auto-generated method stub
		
	}
}
