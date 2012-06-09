package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;

public class StateEditarPerfil extends Composite {

	public StateEditarPerfil() {
		
		AbsolutePanel absolutePanel_EditarPerfil = new AbsolutePanel();
		initWidget(absolutePanel_EditarPerfil);
		absolutePanel_EditarPerfil.setSize("683px", "371px");
		
		Label lblEditarPerfil = new Label("Editar Perfil");
		absolutePanel_EditarPerfil.add(lblEditarPerfil, 10, 10);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel_EditarPerfil.add(flexTable, 43, 69);
		flexTable.setSize("214px", "191px");
		
		Label lblNewLabel = new Label("Login:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		Label lblLogindousuario = new Label("login_do_usuario");
		flexTable.setWidget(0, 1, lblLogindousuario);
		
		Button btnEditar = new Button("Editar");
		flexTable.setWidget(0, 2, btnEditar);
		
		Label lblSenha = new Label("Senha:");
		flexTable.setWidget(1, 0, lblSenha);
		
		Label lblSenhadousuario = new Label("senha_do_usuario");
		flexTable.setWidget(1, 1, lblSenhadousuario);
		
		Button btnEditar_1 = new Button("Editar");
		flexTable.setWidget(1, 2, btnEditar_1);
		
		Label lblNome = new Label("Nome:");
		flexTable.setWidget(2, 0, lblNome);
		
		Label lblNomedousuar = new Label("nome_do_usuario");
		flexTable.setWidget(2, 1, lblNomedousuar);
		
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
		absolutePanel_EditarPerfil.add(absolutePanel, 381, 69);
		absolutePanel.setSize("347px", "191px");
		
		Image image = new Image((String) null);
		absolutePanel.add(image, 10, 10);
		image.setSize("181px", "160px");
		
		Button btnEditar_5 = new Button("Editar");
		absolutePanel.add(btnEditar_5, 197, 10);
	}
}
