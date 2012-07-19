package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;

public class StateVisualizarPerfilAlheio extends Composite {

	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private GWTUsuario u;
	private Integer idSessao;
	
	@SuppressWarnings("static-access")
	public StateVisualizarPerfilAlheio(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaSolidariaService, GWTUsuario usuario) {
		
		this.estrada = entryPoint;
		this.estradaService = estradaSolidariaService;
		this.u = usuario;
		idSessao = estrada.getIdSessaoAberta();
		
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
		
		TextButton txtbtnEnviarMensagem = new TextButton("Enviar mensagem");
		txtbtnEnviarMensagem.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBox newDialog = new DialogBoxEnviarMensagem(estradaService, u.getIdUsuario(), idSessao);
				newDialog.center();
				newDialog.show();
			}
		});
		absolutePanel.add(txtbtnEnviarMensagem, 24, 206);
		
		TextButton txtbtnAdicionarComoAmigo = new TextButton("Adicionar amigo");
		txtbtnAdicionarComoAmigo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				adicionaAmigoGUI();
			}
		});
		absolutePanel.add(txtbtnAdicionarComoAmigo, 24, 240);
		txtbtnAdicionarComoAmigo.setSize("128px", "28px");
	}

	protected void adicionaAmigoGUI() {
		Integer idUsuario = Integer.parseInt(u.getIdUsuario());
		estradaService.adicionarAmigo(idSessao, idUsuario, new AsyncCallback<Void>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Amigo adicionado com sucesso");
			}
		});
	}
}
