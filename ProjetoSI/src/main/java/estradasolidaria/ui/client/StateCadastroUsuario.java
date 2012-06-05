package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StateCadastroUsuario extends AbsolutePanel implements StatePanel {
	
	StatePanel state; 
	final EstradaSolidaria estrada;
	final Widget panel = this;

	public StateCadastroUsuario(EstradaSolidaria estradaSolidaria) {
		this.estrada = estradaSolidaria;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("h2");
		add(absolutePanel, 109, 83);
		absolutePanel.setSize("530px", "301px");
		
		Label lblNome = new Label("Nome:");
		absolutePanel.add(lblNome, 60, 10);
		lblNome.setStyleName("gwt-LabelEstradaSolidaria4");
		lblNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblNome.setDirectionEstimator(true);
		lblNome.setSize("104px", "21px");
		
		Label lblLogin = new Label("Login:");
		lblLogin.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblLogin, 60, 44);
		lblLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblLogin.setSize("104px", "15px");
		
		Label lblSenha = new Label("Senha:");
		lblSenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblSenha, 60, 72);
		lblSenha.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblSenha.setSize("104px", "15px");
		
		Label lblComfirmaASenha = new Label("Confirme a senha:");
		lblComfirmaASenha.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblComfirmaASenha, 60, 102);
		
		final TextBox txtbxNome = new TextBox();
		absolutePanel.add(txtbxNome, 206, 0);
		txtbxNome.setName("Digite seu nome");
		txtbxNome.setSize("262px", "17px");
		
		final TextBox textBoxLogin = new TextBox();
		absolutePanel.add(textBoxLogin, 206, 31);
		textBoxLogin.setName("Digite seu nome");
		textBoxLogin.setSize("262px", "17px");
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		absolutePanel.add(passwordTextBox, 206, 62);
		passwordTextBox.setSize("262px", "17px");
		
		final PasswordTextBox passwordTextBox_1 = new PasswordTextBox();
		absolutePanel.add(passwordTextBox_1, 206, 93);
		passwordTextBox_1.setSize("262px", "17px");
		
		Label lblEndereo = new Label("Endereço:");
		lblEndereo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblEndereo.setDirectionEstimator(false);
		absolutePanel.add(lblEndereo, 60, 129);
		lblEndereo.setStyleName("gwt-LabelEstradaSolidaria4");
		lblEndereo.setSize("104px", "15px");
		
		Label lblEmail = new Label("Email:");
		lblEmail.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblEmail, 60, 161);
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblEmail.setSize("104px", "15px");
		
		final TextBox textBoxEndereco = new TextBox();
		absolutePanel.add(textBoxEndereco, 206, 130);
		textBoxEndereco.setName("Digite seu nome");
		textBoxEndereco.setSize("262px", "17px");
		
		final TextBox textBoxEmail = new TextBox();
		absolutePanel.add(textBoxEmail, 206, 161);
		textBoxEmail.setName("Digite seu nome");
		textBoxEmail.setSize("262px", "17px");
		
		Button btnComfirmar = new Button("Comfirmar");
		btnComfirmar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(txtbxNome.getText().length() == 0|| textBoxLogin.getText().length() == 0 || textBoxEndereco.getText().length() == 0 || 
						textBoxEmail.getText().length() == 0 || passwordTextBox.getText().length() == 0 || passwordTextBox_1.getText().length() == 0){
					Window.alert("Digite todos os campos corretamente");
				} else {
					estrada.rootPanel.remove(panel);
					Widget newPanel = new StateCadastroUsuarioAceito(estrada);
					newPanel.setSize("600px", "417px");
					estrada.setStatePanel(newPanel);
				}
			}
		});
		absolutePanel.add(btnComfirmar, 395, 232);
		btnComfirmar.setText("Criar");
		btnComfirmar.setSize("81px", "24px");
		
		
		Button btnVoltar = new Button("Cancelar");
		btnVoltar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel.add(btnVoltar, 206, 232);
		btnVoltar.setSize("81px", "24px");
		
		Label lblCadastroDeUsurio = new Label("Cadastro de Usuário");
		lblCadastroDeUsurio.setStyleName("gwt-LabelEstradaSolidaria2");
		add(lblCadastroDeUsurio, 301, 10);
		
	}

	@Override
	public void nextState() {
		// TODO Auto-generated method stub
		
	}
}
