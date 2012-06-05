package estradasolidaria.ui.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;

public class StatePerfil2 extends Composite {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	
	public StatePerfil2(EstradaSolidaria estradaSolidaria) {
		
		estrada = estradaSolidaria;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("781px", "592px");
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("carona1");
		comboBox.addItem("carona2");
		absolutePanel.add(comboBox, 66, 515);
		comboBox.setSize("212px", "20px");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 528, 23);
		
		MenuItem mntmOpes = new MenuItem("Opções", false, (Command) null);
		menuBar.addItem(mntmOpes);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		
		MenuItem mntmSair = new MenuItem("Sair", false, new Command() {
			public void execute() {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
				Window.alert("Logoff efetuado com sucesso!");
			}
		});
		menuBar.addItem(mntmSair);
		
		TextButton txtbtnNewButton = new TextButton("Cadastrar uma carona");
		absolutePanel.add(txtbtnNewButton, 66, 278);
		
		Image image = new Image((String) null);
		absolutePanel.add(image, 54, 65);
		image.setSize("100px", "100px");
		
		Label lblNomeDoUsuario = new Label("Nome do usuario");
		absolutePanel.add(lblNomeDoUsuario, 54, 176);
		
		TextButton txtbtnNewButton_1 = new TextButton("Pesquisar carona");
		absolutePanel.add(txtbtnNewButton_1, 66, 402);
		
		Label lblVisualizarCaronasCadastradas = new Label("Visualizar caronas cadastradas");
		absolutePanel.add(lblVisualizarCaronasCadastradas, 66, 475);
		
		Label lblMinhasCaronasOferecidas = new Label("Eu motorista:");
		absolutePanel.add(lblMinhasCaronasOferecidas, 54, 249);
		
		Label lblEuCaroneiro = new Label("Eu Caroneiro:");
		absolutePanel.add(lblEuCaroneiro, 54, 376);
		
		Label lblEndereo = new Label("Endereço:");
		absolutePanel.add(lblEndereo, 250, 87);
		
		Label lblEmail = new Label("Email:");
		absolutePanel.add(lblEmail, 250, 109);
		
		Label lblResidoEm = new Label("Resido em:");
		lblResidoEm.setStyleName("gwt-LabelHomePage");
		absolutePanel.add(lblResidoEm, 250, 65);
		
		Label lblInteresses = new Label("Interesses:");
		absolutePanel.add(lblInteresses, 250, 131);
		
		Label lblAmigos = new Label("Amigos:");
		absolutePanel.add(lblAmigos, 507, 249);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 507, 278);
		flexTable.setSize("212px", "213px");
		
		Image image_1 = new Image((String) null);
		flexTable.setWidget(0, 0, image_1);
		
		Image image_2 = new Image((String) null);
		flexTable.setWidget(0, 1, image_2);
		
		Image image_3 = new Image((String) null);
		flexTable.setWidget(1, 0, image_3);
		
		Image image_4 = new Image((String) null);
		flexTable.setWidget(1, 1, image_4);
		
		Image image_5 = new Image((String) null);
		flexTable.setWidget(2, 0, image_5);
		
		Image image_6 = new Image((String) null);
		flexTable.setWidget(2, 1, image_6);
		
		TextButton txtbtnVisualizarTodosOs = new TextButton("Visualizar todos os amigos");
		absolutePanel.add(txtbtnVisualizarTodosOs, 540, 507);
		txtbtnVisualizarTodosOs.setSize("182px", "28px");
		
		TextButton txtbtnVerCanoasQue = new TextButton("Ver caronas que ofereço");
		absolutePanel.add(txtbtnVerCanoasQue, 66, 312);
		
		TextButton txtbtnVerCaronasEm = new TextButton("Ver caronas em que estou cadastrado");
		absolutePanel.add(txtbtnVerCaronasEm, 66, 436);
		
		TextButton txtbtnNewButton_2 = new TextButton("Pesquisar um amigo");
		absolutePanel.add(txtbtnNewButton_2, 539, 541);
	}
}
