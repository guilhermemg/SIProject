package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;

public class StateEditarPerfil extends Composite {
	private EstradaSolidariaServiceAsync estradaService;
	private Integer idSessaoAberta;
	private TextBox textBoxNovoLogin;
	private TextBox textBoxNovoNome;
	private TextBox textBoxNovoEmail;
	private TextBox textBoxNovoEndereco;
	private TextButton txtbtnLoginOk;
	private TextButton txtbtnNomeOk;
	private TextButton txtbtnEmailOk;
	private TextButton txtbtnEnderecoOk;
	private Label lblLogindousuario;
	private Label lblNomeusuario;
	private Label lblEnderecodousuario;
	private Label lblEmaildousuario;
	private String[] dadosUsuario;
	
	public StateEditarPerfil(EstradaSolidariaServiceAsync estradaSolidariaService, String[] result) {
		this.estradaService = estradaSolidariaService;
		this.idSessaoAberta = EstradaSolidaria.getIdSessaoAberta();
		this.dadosUsuario = result;
		
		Resources resources = GWT.create(Resources.class);
		
		AbsolutePanel absolutePanel_EditarPerfil = new AbsolutePanel();
		initWidget(absolutePanel_EditarPerfil);
		absolutePanel_EditarPerfil.setSize("713px", "644px");
		
		Label lblEditarPerfil = new Label("Editar Perfil");
		absolutePanel_EditarPerfil.add(lblEditarPerfil, 10, 10);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel_EditarPerfil.add(absolutePanel, 41, 313);
		absolutePanel.setSize("423px", "191px");
		
		//Image image = new Image(resources.editarPerfil());
		Image image = new Image("genericPhoto.jpg");
		absolutePanel.add(image, 10, 10);
		image.setSize("181px", "160px");
		
		FileUpload fileUpload = new FileUpload();
		absolutePanel.add(fileUpload, 206, 144);
		fileUpload.setSize("189px", "22px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_EditarPerfil.add(absolutePanel_1, 41, 57);
		absolutePanel_1.setSize("552px", "250px");
		
		Label lblNewLabel = new Label("Login:");
		absolutePanel_1.add(lblNewLabel, 10, 10);
		
		lblLogindousuario = new Label(dadosUsuario[0]);
		absolutePanel_1.add(lblLogindousuario, 74, 10);
		
		Button btnEditarLogin = new Button("Editar");
		absolutePanel_1.add(btnEditarLogin, 74, 32);
		
		textBoxNovoLogin = new TextBox();
		absolutePanel_1.add(textBoxNovoLogin, 127, 28);
		textBoxNovoLogin.setVisible(false);
		
		txtbtnLoginOk = new TextButton("OK");
		txtbtnLoginOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarLoginGUI(idSessaoAberta, textBoxNovoLogin.getText());
			}
		});
		absolutePanel_1.add(txtbtnLoginOk, 294, 28);
		txtbtnLoginOk.setVisible(false);
		
		Label lblSenha = new Label("Senha:");
		absolutePanel_1.add(lblSenha, 417, 28);
		
		TextButton txtbtnAlterarSenha = new TextButton("Alterar senha");
		txtbtnAlterarSenha.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBox newDialog = new DialogBoxAlterarSenha(estradaService);
				newDialog.center();
				newDialog.show();
			}
		});
		absolutePanel_1.add(txtbtnAlterarSenha, 417, 53);
		
		Label lblNome = new Label("Nome:");
		absolutePanel_1.add(lblNome, 10, 65);
		
		lblNomeusuario = new Label(dadosUsuario[2]);
		absolutePanel_1.add(lblNomeusuario, 74, 65);
		
		Button btnEditarNome = new Button("Editar");
		absolutePanel_1.add(btnEditarNome, 74, 87);
		
		textBoxNovoNome = new TextBox();
		absolutePanel_1.add(textBoxNovoNome, 127, 87);
		textBoxNovoNome.setVisible(false);
		
		txtbtnNomeOk = new TextButton("OK");
		txtbtnNomeOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarNomeGUI(idSessaoAberta, textBoxNovoNome.getText());
			}
		});
		absolutePanel_1.add(txtbtnNomeOk, 294, 87);
		txtbtnNomeOk.setVisible(false);
		
		Label lblEmail = new Label("Email:");
		absolutePanel_1.add(lblEmail, 10, 124);
		
		lblEmaildousuario = new Label(dadosUsuario[4]);
		absolutePanel_1.add(lblEmaildousuario, 74, 124);
		
		Button btnEditarEmail = new Button("Editar");
		absolutePanel_1.add(btnEditarEmail, 74, 146);
		
		textBoxNovoEmail = new TextBox();
		absolutePanel_1.add(textBoxNovoEmail, 127, 146);
		textBoxNovoEmail.setVisible(false);
		
		txtbtnEmailOk = new TextButton("OK");
		txtbtnEmailOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarEmailGUI(idSessaoAberta, textBoxNovoEmail.getText());
			}
		});
		absolutePanel_1.add(txtbtnEmailOk, 294, 146);
		txtbtnEmailOk.setVisible(false);
		
		Label lblEndereo = new Label("Endereço:");
		absolutePanel_1.add(lblEndereo, 10, 182);
		
		lblEnderecodousuario = new Label(dadosUsuario[3]);
		absolutePanel_1.add(lblEnderecodousuario, 74, 182);
		
		Button btnEditarEndereco = new Button("Editar");
		absolutePanel_1.add(btnEditarEndereco, 74, 204);
		
		textBoxNovoEndereco = new TextBox();
		absolutePanel_1.add(textBoxNovoEndereco, 127, 204);
		textBoxNovoEndereco.setVisible(false);
		
		txtbtnEnderecoOk = new TextButton("OK");
		txtbtnEnderecoOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editarEnderecoGUI(idSessaoAberta, textBoxNovoEndereco.getText());
			}
		});
		absolutePanel_1.add(txtbtnEnderecoOk, 294, 204);
		txtbtnEnderecoOk.setVisible(false);
		
		btnEditarEndereco.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				textBoxNovoEndereco.setVisible(true);
				txtbtnEnderecoOk.setVisible(true);
			}
		});
		
		btnEditarEmail.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				textBoxNovoEmail.setVisible(true);
				txtbtnEmailOk.setVisible(true);
			}
		});
		
		btnEditarNome.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				textBoxNovoNome.setVisible(true);
				txtbtnNomeOk.setVisible(true);
			}
		});
		
		btnEditarLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				textBoxNovoLogin.setVisible(true);
				txtbtnLoginOk.setVisible(true);
				//editarLogin();
			}
		});
		
	}
	
	protected void editarNomeGUI(Integer idSessao, String text) {
		estradaService.editarNome(idSessao, text, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				lblNomeusuario.setText(textBoxNovoNome.getText());
				textBoxNovoNome.setText("");
				textBoxNovoNome.setVisible(false);
				txtbtnNomeOk.setVisible(false);
				//hide();
			}
		  });
	}

	private void editarLoginGUI(Integer idSessao, String novoLogin) {
		estradaService.editarLogin(idSessao, novoLogin, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				lblLogindousuario.setText(textBoxNovoLogin.getText());
				textBoxNovoLogin.setText("");
				textBoxNovoLogin.setVisible(false);
				txtbtnLoginOk.setVisible(false);
				//hide();
			}
		  });	
	}
	
	protected void editarEmailGUI(Integer idSessao, String text) {
		estradaService.editarEmail(idSessao, text, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				lblEmaildousuario.setText(textBoxNovoEmail.getText());
				textBoxNovoEmail.setText("");
				textBoxNovoEmail.setVisible(false);
				txtbtnEmailOk.setVisible(false);
				//hide();
			}
		  });
	}
	
	protected void editarEnderecoGUI(Integer idSessao, String text) {
		estradaService.editarEndereco(idSessao, text, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				lblEnderecodousuario.setText(textBoxNovoEndereco.getText());
				textBoxNovoEndereco.setText("");
				textBoxNovoEndereco.setVisible(false);
				txtbtnEnderecoOk.setVisible(false);
				//hide();
			}
		  });
	}
}
