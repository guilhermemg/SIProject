package estradasolidaria.ui.client;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupSolicitacoes extends PopupPanel {

	private final EstradaSolidaria estrada;
	private final EstradaSolidariaServiceAsync estradaSolidariaService;
	private Button btnRejeitar;
	private ListBox listBox;
	private Image image;
	private Label lblNome;
	private Button btnAceitar;
	private AbsolutePanel absolutePanel;
	private Button btnSair;
	private Integer idSessao;
	private Integer idCarona;
	private LinkedList<Solicitante> listSolicitantes;
	
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
		
		try {
			this.idCarona = Integer.parseInt(idCaronaString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		this.idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("344px", "302px");
		
		listBox = new ListBox();
		absolutePanel.add(listBox, 173, 10);
		listBox.setSize("137px", "144px");
		listBox.setVisibleItemCount(5);
		
//		image = new Image((String) null);
//		absolutePanel.add(image, 30, 10);
//		image.setSize("137px", "144px");
		
		lblNome = new Label("Nome:");
		absolutePanel.add(lblNome, 30, 160);
		
		btnAceitar = new Button("Aceitar");
		btnAceitar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				aceitarSolicitacao();
				
			}
		});
		btnAceitar.setText("Aceitar");
		absolutePanel.add(btnAceitar, 30, 183);
		btnAceitar.setSize("137px", "81px");
		
		btnRejeitar = new Button("Rejeitar");
		btnRejeitar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rejeitarSolicitacao();
				
			}
		});
		btnRejeitar.setText("Rejeitar");
		absolutePanel.add(btnRejeitar, 173, 183);
		btnRejeitar.setSize("137px", "81px");
		
		btnSair = new Button("Sair");
		btnSair.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(btnSair, 310, 277);
		btnSair.setSize("34px", "25px");
		
		atualizaSolicitacoes();
	}

	private void rejeitarSolicitacao() {
		Integer idSolicitacao = listSolicitantes.get(listBox.getSelectedIndex()).idSolicitacao;
		
		this.estradaSolidariaService.rejeitarSolicitacao(idSessao, idSolicitacao, new AsyncCallback<Void>() {

			private DialogBox d;
			private Button b;

			@Override
			public void onFailure(Throwable caught) {
				d = new DialogBox();
				d.setTitle("Erro");
				d.setText("Erro: " + caught.getMessage());
				
				b = new Button();
				b.setText("OK");
				b.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						d.hide();
					}
				});
				
				d.add(b);
				d.show();
				
			}

			@Override
			public void onSuccess(Void result) {
				d = new DialogBox();
				d.setTitle("Sistema");
				d.setText("Solicitação Rejeitada!");
				b = new Button();
				b.setText("OK");
				b.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						d.hide();
					}
				});
				d.add(b);
				d.show();
			}
		});
		
	}

	private void aceitarSolicitacao() {
		Integer idSolicitacao = listSolicitantes.get(listBox.getSelectedIndex()).idSolicitacao;
		
		this.estradaSolidariaService.aceitarSolicitacao(idSessao, idSolicitacao, new AsyncCallback<Void>() {

			private DialogBox d;
			private Button b;

			@Override
			public void onFailure(Throwable caught) {
				d = new DialogBox();
				d.setTitle("Erro");
				d.setText("Erro: " + caught.getMessage());
				
				b = new Button();
				b.setText("OK");
				b.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						d.hide();
					}
				});
				
				d.add(b);
				d.show();
				
			}

			@Override
			public void onSuccess(Void result) {
				d = new DialogBox();
				d.setTitle("Sistema");
				d.setText("Solicitação Aceita!");
				b = new Button();
				b.setText("OK");
				b.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						d.hide();
					}
				});
				d.add(b);
				d.show();
				
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
