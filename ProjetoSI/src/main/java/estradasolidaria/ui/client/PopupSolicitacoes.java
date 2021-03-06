package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;

public class PopupSolicitacoes extends PopupPanel {

	private final EstradaSolidaria estrada;
	private final EstradaSolidariaServiceAsync estradaSolidariaService;
	private TextButton btnRejeitar;
	private ListBox listBox;
	private Image fotoSolicitante;
	private Label lblNome;
	private TextButton btnAceitar;
	private AbsolutePanel absolutePanel;
	private TextButton btnOK;
	private Integer idSessao;
	private Integer idCarona;
	private LinkedList<Solicitante> listSolicitantes;
	private DialogBox dialogBox;
	private TextButton buttonOK;
	private Resources resources;
	private Label lblMensagemDeErro;
	
	class Solicitante {
		String nomeUsuario;
		Integer idUsuario;
		private final Integer idSolicitacao;
		public Solicitante(Integer idUsuario, String nomeUsuario, Integer idSolicitacao) {
			this.idUsuario = idUsuario;
			this.nomeUsuario = nomeUsuario;
			this.idSolicitacao = idSolicitacao;
		}
	}

	public PopupSolicitacoes(EstradaSolidaria estrada,
			EstradaSolidariaServiceAsync estradaSolidariaService, String idCaronaString) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		this.resources = GWT.create(Resources.class);
		
		try {
			this.idCarona = Integer.parseInt(idCaronaString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		this.idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("380px", "302px");
		
		listBox = new ListBox();
		absolutePanel.add(listBox, 126, 10);
		listBox.setSize("223px", "144px");
		listBox.setVisibleItemCount(5);
		
//		image = new Image((String) null);
//		absolutePanel.add(image, 30, 10);
//		image.setSize("137px", "144px");
		
		lblNome = new Label("Nome:");
		absolutePanel.add(lblNome, 30, 160);
		
		btnAceitar = new TextButton("Aceitar");
		btnAceitar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				aceitarSolicitacao();
				
			}
		});
		btnAceitar.setText("Aceitar");
		absolutePanel.add(btnAceitar, 40, 183);
		btnAceitar.setSize("123px", "74px");
		
		btnRejeitar = new TextButton("Rejeitar");
		btnRejeitar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rejeitarSolicitacao();
				
			}
		});
		btnRejeitar.setText("Rejeitar");
		absolutePanel.add(btnRejeitar, 190, 183);
		btnRejeitar.setSize("109px", "74px");
		
		btnOK = new TextButton("OK");
		btnOK.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(btnOK, 324, 267);
		btnOK.setSize("46px", "25px");
		
		Image littlePhotoUser = new Image(resources.getGenericLittleUserImage());
		absolutePanel.add(littlePhotoUser, 30, 28);
		littlePhotoUser.setSize("74px", "74px");
		
		lblMensagemDeErro = new Label("MensagemDeErro");
		absolutePanel.add(lblMensagemDeErro, 30, 276);
		lblMensagemDeErro.setVisible(false);
		
		dialogBox = new DialogBox();
		
		dialogBox.center();
		buttonOK = new TextButton();
		buttonOK.setText("OK");
		buttonOK.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		dialogBox.add(buttonOK);
		dialogBox.hide();
		atualizaSolicitacoes();
	}

	private void rejeitarSolicitacao() {
		Integer idSolicitacao = listSolicitantes.get(listBox.getSelectedIndex()).idSolicitacao;
		
		this.estradaSolidariaService.rejeitarSolicitacao(idSessao, idSolicitacao, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				lblMensagemDeErro.setText("Erro: " + caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
//				dialogBox.setTitle("Erro");
//				dialogBox.setText("Erro: " + caught.getMessage());
//				dialogBox.show();
				
			}

			@Override
			public void onSuccess(Void result) {
				lblMensagemDeErro.setText("Solicitação Rejeitada!");
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria10");
				lblMensagemDeErro.setVisible(true);
//				dialogBox.setTitle("Sistema");
//				dialogBox.setText("Solicitação Rejeitada!");
//				dialogBox.show();
			}
		});
		
	}

	private void aceitarSolicitacao() {
		Integer idSolicitacao = listSolicitantes.get(listBox.getSelectedIndex()).idSolicitacao;
		
		this.estradaSolidariaService.aceitarSolicitacao(idSessao, idSolicitacao, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				lblMensagemDeErro.setText("Erro: " + caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
//				dialogBox.setTitle("Erro");
//				dialogBox.setText("Erro: " + caught.getMessage());
//				dialogBox.show();
				
			}

			@Override
			public void onSuccess(Void result) {
				lblMensagemDeErro.setText("Solicitação Aceita!");
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria10");
				lblMensagemDeErro.setVisible(true);
//				dialogBox.setTitle("Sistema");
//				dialogBox.setText("Solicitação Aceita!");
//				dialogBox.show();
				
			}
		});
		
	}

	private void atualizaSolicitacoes() {
		this.estradaSolidariaService.getSolicitacoes(idSessao, idCarona, new AsyncCallback<List<String[]>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(List<String[]> result) {
				geraListSolicitantes(result);
				
			}
		});
		
	}
	
	private void geraListSolicitantes(List<String[]> result) {
		listSolicitantes = new LinkedList<Solicitante>();
		for (String[] solicitanteArray : result) {
			Integer idUsuarioInt = null,
					idSolicitacao = null;
			String nomeSolicitante = null;
			try {
				idUsuarioInt = Integer.parseInt(solicitanteArray[0]);
				idSolicitacao = Integer.parseInt(solicitanteArray[2]);
				nomeSolicitante = solicitanteArray[1];
				listSolicitantes.add(new Solicitante(idUsuarioInt, nomeSolicitante, idSolicitacao));
				
				//Adiciona item a listBox
				listBox.addItem(nomeSolicitante);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
