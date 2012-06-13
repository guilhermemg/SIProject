package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextBox;

public class StateEditarPerfil extends Composite {
	
	private Image imagem;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessaoAberta;
	private TextBox textBoxNovaSenha;

	public StateEditarPerfil(EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estradaSolidariaService = estradaSolidariaService;
		this.idSessaoAberta = EstradaSolidaria.getIdSessaoAberta();
		
		Resources resources = GWT.create(Resources.class);
		
		AbsolutePanel absolutePanel_EditarPerfil = new AbsolutePanel();
		initWidget(absolutePanel_EditarPerfil);
		absolutePanel_EditarPerfil.setSize("713px", "512px");
		
		Label lblEditarPerfil = new Label("Editar Perfil");
		absolutePanel_EditarPerfil.add(lblEditarPerfil, 10, 10);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel_EditarPerfil.add(flexTable, 43, 69);
		flexTable.setSize("214px", "191px");
		
		Label lblNewLabel = new Label("Login:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		Label lblLogindousuario = new Label("login_do_usuario");
		flexTable.setWidget(0, 1, lblLogindousuario);
		
		Button btnEditarLogin = new Button("Editar");
		btnEditarLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarLogin();
			}
		});
		flexTable.setWidget(0, 2, btnEditarLogin);
		
		Label lblSenha = new Label("Senha:");
		flexTable.setWidget(1, 0, lblSenha);
		
		textBoxNovaSenha = new TextBox();
		flexTable.setWidget(1, 1, textBoxNovaSenha);
		
		Button btnEditar_1 = new Button("Editar");
		btnEditar_1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarSenha();
			}
		});
		flexTable.setWidget(1, 2, btnEditar_1);
		
		Label lblNome = new Label("Nome:");
		flexTable.setWidget(2, 0, lblNome);
		
		TextBox textBoxNovoNome = new TextBox();
		flexTable.setWidget(2, 1, textBoxNovoNome);
		
		Button btnEditar_2 = new Button("Editar");
		flexTable.setWidget(2, 2, btnEditar_2);
		
		Label lblEmail = new Label("Email:");
		flexTable.setWidget(3, 0, lblEmail);
		
		Label lblEmaildousuario = new Label("email_do_usuario");
		flexTable.setWidget(3, 1, lblEmaildousuario);
		
		Button btnEditar_3 = new Button("Editar");
		flexTable.setWidget(3, 2, btnEditar_3);
		
		Label lblEndereo = new Label("Endereço:");
		flexTable.setWidget(4, 0, lblEndereo);
		
		Label lblEnderecodousuario = new Label("endereco_do_usuario");
		flexTable.setWidget(4, 1, lblEnderecodousuario);
		
		Button btnEditar_4 = new Button("Editar");
		flexTable.setWidget(4, 2, btnEditar_4);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel_EditarPerfil.add(absolutePanel, 43, 295);
		absolutePanel.setSize("423px", "191px");
		
		imagem= new Image(resources.editar());
		absolutePanel.add(imagem, 10, 10);
		imagem.setSize("181px", "160px");
		
		Button btnEditar_5 = new Button("Editar");
		absolutePanel.add(btnEditar_5, 197, 10);
		
		FileUpload fileUpload = new FileUpload();
		absolutePanel.add(fileUpload, 206, 144);
		fileUpload.setSize("189px", "22px");
		
		
	}
	private void editarLogin() {
		
	}
	
	private void editarSenha() {
		String novaSenha = textBoxNovaSenha.getText();
		estradaSolidariaService.editarSenha(idSessaoAberta, novaSenha, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Senha alterada com sucesso!");
			}
		
		});
	}
}
