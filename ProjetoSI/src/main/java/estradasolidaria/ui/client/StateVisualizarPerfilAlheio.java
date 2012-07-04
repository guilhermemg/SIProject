package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import estradasolidaria.ui.resources.Resources;

public class StateVisualizarPerfilAlheio extends Composite {

	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private GWTUsuario u;
	private Integer idDaSessao;
	
	public StateVisualizarPerfilAlheio(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaSolidariaService, GWTUsuario usuario) {
		
		this.estrada = entryPoint;
		this.estradaService = estradaSolidariaService;
		this.u = usuario;
		idDaSessao = estrada.getIdSessaoAberta();
		
		Resources resources = GWT.create(Resources.class);
		Image imagem = new Image(resources.getGenericUserImage());
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("630px", "486px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 196, 28);
		flexTable.setSize("100px", "100px");
		
		Label lblNome = new Label("Nome:");
		flexTable.setWidget(0, 0, lblNome);
		
		Label lblUsername = new Label(u.getNome());
		flexTable.setWidget(0, 1, lblUsername);
		
		Label lblLogin = new Label("Login:");
		flexTable.setWidget(1, 0, lblLogin);
		
		Label lblUserlogin = new Label(u.getLogin());
		flexTable.setWidget(1, 1, lblUserlogin);
		
		Label lblEndereo = new Label("Endereço:");
		flexTable.setWidget(2, 0, lblEndereo);
		
		Label lblUseradress = new Label(u.getEndereco());
		flexTable.setWidget(2, 1, lblUseradress);
		
		Label lblEmail = new Label("Email:");
		flexTable.setWidget(3, 0, lblEmail);
		
		Label lblUseremail = new Label(u.getEmail());
		flexTable.setWidget(3, 1, lblUseremail);
		
		absolutePanel.add(imagem, 24, 28);
		imagem.setSize("143px", "149px");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 24, 179);
		menuBar.setSize("168px", "20px");
		
		MenuItem mntmEnviarMensagem = new MenuItem("Enviar mensagem", false, new Command() {
			public void execute() {
				DialogBox newDialog = new DialogBoxEnviarMensagem(estradaService, u.getIdUsuario(), idDaSessao);
				newDialog.center();
				newDialog.show();
			}
		});
		menuBar.addItem(mntmEnviarMensagem);
		
		MenuBar menuBar_1 = new MenuBar(false);
		absolutePanel.add(menuBar_1, 24, 199);
		
		MenuItem mntmAdicionarComoAmigo = new MenuItem("Adicionar como amigo", false, new Command() {
			public void execute() {
				Window.alert("fazer alguma coisa");
			}
		});
		menuBar_1.addItem(mntmAdicionarComoAmigo);
	}
}
