package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatePerfil extends AbsolutePanel implements StatePanel {
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	
	public StatePerfil(EstradaSolidaria estradaSolidaria) {
		
		this.estrada = estradaSolidaria;
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		add(absolutePanel_1, 0, 10);
		absolutePanel_1.setSize("467px", "318px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absolutePanel_2.setStyleName("painelPerfil");
		absolutePanel_1.add(absolutePanel_2, 10, 44);
		absolutePanel_2.setSize("447px", "134px");
		
		Image image_1 = new Image("WEB-INF/classes/br/com/estradasolidaria/shared/smart.jpg");
		absolutePanel_2.add(image_1, 126, 0);
		image_1.setSize("311px", "100px");
		
		Image image = new Image("estradasolidaria/gwt/chrome/images/vborder.png");
		image.setTitle("foto do usuario");
		absolutePanel_2.add(image, 10, 0);
		image.setSize("123px", "124px");
		
		Label lblCidade = new Label("Uma tabela com info do usuario aqui");
		absolutePanel_2.add(lblCidade, 157, 46);
		lblCidade.setSize("198px", "15px");
		
		Label lblNomeCompletoDo = new Label("Nome Completo do Usuario");
		absolutePanel_2.add(lblNomeCompletoDo, 155, 10);
		lblNomeCompletoDo.setStyleName("gwt-LabelDestaqueWhilte");
		lblNomeCompletoDo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		lblNomeCompletoDo.setSize("390px", "27px");
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_1.add(absolutePanel_3, 35, 184);
		absolutePanel_3.setStyleName("feedPanel");
		absolutePanel_3.setSize("397px", "124px");
		
		Button btnCadastrarCarona = new Button("<i>Cadastrar Carona</i>");
		btnCadastrarCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateCadastroCaronas(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel_3.add(btnCadastrarCarona, 10, 10);
		btnCadastrarCarona.setSize("115px", "33px");
		
		Button btnaceitarCarona = new Button("<i>Aceitar Carona</i>");
		btnaceitarCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Por enquanto não faz nada");
			}
		});
		absolutePanel_3.add(btnaceitarCarona, 142, 10);
		btnaceitarCarona.setSize("115px", "33px");
		
		Button btnprocurarCarona = new Button("<i>Procurar Carona</i>");
		btnprocurarCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StatePesquisaCarona(estrada);
				newPanel.setSize("387px", "209px");
				estrada.setStatePanel(newPanel);
			}
		});
		absolutePanel_3.add(btnprocurarCarona, 272, 10);
		btnprocurarCarona.setSize("115px", "33px");
		
		Button btnPginaInicial = new Button("Página Inicial");
		btnPginaInicial.setText("Sair");
		btnPginaInicial.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				estrada.rootPanel.remove(panel);
				Widget newPanel = new StateHomePage(estrada);
				newPanel.setSize("600px", "417px");
				estrada.setStatePanel(newPanel);
				Window.alert("Logoff efetuado com sucesso!");
			}
		});
		absolutePanel_1.add(btnPginaInicial, 421, 14);
		
		
	}

	@Override
	public void nextState() {
		// TODO Auto-generated method stub
		
	}
}
