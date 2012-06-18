package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopUpEditarReview extends PopupPanel {
//	private Image image;
	private ListBox comboBoxCaroneiros;
	private ListBox comboBoxTiposDeReview;
	private TextButton txtbtnEditar;
	private TextButton txtbtnSair;
	private final EstradaSolidariaServiceAsync estradaService;
	private final EstradaSolidaria estrada;
	private final boolean isDono;
	private Integer idSessaoAberta;
	private List<InfReviewCaroneiros> listInfReviewCaroneiros;
	
	private class InfReviewCaroneiros {
		String idCaroneiro;
		String nomeCaroneiro;
		Integer reviewRecebido;
	}

	public PopUpEditarReview(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService, boolean isDono, String idCarona) {
		super(true);
		this.estrada = estrada;
		this.estradaService = estradaSolidariaService;
		this.isDono = isDono;
		this.idSessaoAberta = EstradaSolidaria.getIdSessaoAberta();
		
		setWidth((Window.getClientWidth() * 0.5) + "px");
		setHeight((Window.getClientHeight() * 0.5) + "px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("348px", "278px");
		
//		image = new Image("gwt/standard/images/splitPanelThumb.png");
//		image.setStyleName("PopupEditarReviewImage");
//		absolutePanel.add(image, 88, 26);
//		image.setSize("172px", "139px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 88, 188);
		flexTable.setSize("172px", "80px");
		
		Label lblNewLabel = new Label("Usuário:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		
		
		Label lblReview = new Label("Review:");
		flexTable.setWidget(1, 0, lblReview);
		
		comboBoxTiposDeReview = new ListBox();
		flexTable.setWidget(1, 1, comboBoxTiposDeReview);
		if (isDono) {
			comboBoxTiposDeReview.addItem("Não Faltou");
			comboBoxTiposDeReview.addItem("Faltou");
		} else {
			comboBoxTiposDeReview.addItem("Segura e tranquila");
			comboBoxTiposDeReview.addItem("Não funcionou");
		}
		
		comboBoxTiposDeReview.setSize("100%", "100%");
		
		comboBoxCaroneiros = new ListBox();
		comboBoxCaroneiros.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				atualizarComboBoxReview();
			}
		});
		flexTable.setWidget(0, 1, comboBoxCaroneiros);
		comboBoxCaroneiros.setSize("100%", "100%");
		
		txtbtnEditar = new TextButton("Editar");
		absolutePanel.add(txtbtnEditar, 281, 204);
		
		txtbtnSair = new TextButton("Sair");
		txtbtnSair.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnSair, 281, 239);
		txtbtnSair.setSize("57px", "29px");
		
		if (isDono) {
			estradaSolidariaService.getCaroneiros(idSessaoAberta, idCarona, new AsyncCallback<List<List<String>>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
					
				}

				@Override
				public void onSuccess(List<List<String>> result) {
					atualizarListaInfReviewCaroneiros(result);
					atualizarComboBoxCaroneiros();
					atualizarComboBoxReview();
				}
			});		
		}
	}
	
	private void atualizarListaInfReviewCaroneiros(List<List<String>> result) {
		listInfReviewCaroneiros = new LinkedList<InfReviewCaroneiros>();
		for (List<String> list : result) {
			InfReviewCaroneiros infCaroneiro = new InfReviewCaroneiros();
			
			infCaroneiro.idCaroneiro = list.get(0);
			infCaroneiro.nomeCaroneiro = list.get(1);
			
			try {
				infCaroneiro.reviewRecebido = Integer.parseInt(list.get(2));
			} catch (NumberFormatException e) {
				Window.alert("Review Error parseInt:" + list.get(2));
			}
			
			listInfReviewCaroneiros.add(infCaroneiro);
		}
		
	}

	private void atualizarComboBoxCaroneiros() {
		comboBoxCaroneiros.clear();
		for (InfReviewCaroneiros caroneiro : listInfReviewCaroneiros) {
			comboBoxCaroneiros.addItem(caroneiro.nomeCaroneiro);
		}
	}
	
	private void atualizarComboBoxReview() {
		if (listInfReviewCaroneiros.size() > 0) {
			InfReviewCaroneiros caroneiroSelecionado = listInfReviewCaroneiros
					.get(comboBoxCaroneiros.getSelectedIndex());
			Integer indexCaroneiroSelecionado = caroneiroSelecionado.reviewRecebido;
			comboBoxTiposDeReview.setSelectedIndex(listInfReviewCaroneiros
					.get(indexCaroneiroSelecionado).reviewRecebido);
		}
		
	}
}
