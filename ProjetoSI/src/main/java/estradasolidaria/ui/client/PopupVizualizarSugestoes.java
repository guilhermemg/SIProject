package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextBox;

public class PopupVizualizarSugestoes extends PopupPanel {

	private final EstradaSolidariaServiceAsync estradaSolidariaService;
	private TextBox txtbxResposta;
	private ListBox listBoxSugestoes;
	private final GWTCarona caronaEscolhida;
	private List<GWTSugestao> listaDeSugestoes;
	private PopupInfo popupInfo;

	public PopupVizualizarSugestoes(
			EstradaSolidariaServiceAsync estradaSolidariaService,
			GWTCarona caronaEscolhida) {
		this.estradaSolidariaService = estradaSolidariaService;
		this.caronaEscolhida = caronaEscolhida;
		this.listaDeSugestoes = new LinkedList<GWTSugestao>();

		colocarSugestoesNaLista();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("218px", "200px");

		listBoxSugestoes = new ListBox();
		absolutePanel.add(listBoxSugestoes, 10, 24);

		for (GWTSugestao s : listaDeSugestoes) {
			listBoxSugestoes.addItem(s.getSugestaoPontoDeEncontro());
		}

		listBoxSugestoes.setSize("196px", "73px");
		listBoxSugestoes.setVisibleItemCount(5);

		Label lblSugestes = new Label("Sugestões");
		absolutePanel.add(lblSugestes, 10, 0);

		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 120, 161);
		txtbtnCancelar.setSize("88px", "29px");

		Label lblResposta = new Label("Resposta:");
		absolutePanel.add(lblResposta, 10, 103);

		txtbxResposta = new TextBox();
		absolutePanel.add(txtbxResposta, 10, 126);
		txtbxResposta.setSize("186px", "17px");

		TextButton txtbtnReposnder = new TextButton("Reponder");
		txtbtnReposnder.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (listBoxSugestoes.getSelectedIndex() != -1
						&& !txtbxResposta.getSelectedText().equals("")) {
					responderSugestao(listBoxSugestoes.getSelectedIndex(),
							txtbxResposta.getSelectedText());
				}
			}
		});
		absolutePanel.add(txtbtnReposnder, 10, 161);
		txtbtnReposnder.setSize("88px", "29px");

	}

	private void colocarSugestoesNaLista() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		Integer idCarona = Integer.parseInt(caronaEscolhida.getIdCarona());

		this.estradaSolidariaService.getSugestoesDaCarona(idSessao, idCarona,
				new AsyncCallback<List<GWTSugestao>>() {

					@Override
					public void onFailure(Throwable caught) {
						exibirPopupInfo(caught.getMessage());

					}

					@Override
					public void onSuccess(List<GWTSugestao> result) {
						System.out.println("Result: " + result);
						listaDeSugestoes = result;
					}
				});
	}

	private void responderSugestao(int indiceSelecionado, String resposta) {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		Integer idSugestao = listaDeSugestoes.get(
				listBoxSugestoes.getSelectedIndex()).getIdSugestao();
		Integer idCarona = Integer.parseInt(caronaEscolhida.getIdCarona());
		estradaSolidariaService.responderSugestaoPontoEncontro(idSessao,
				idCarona, idSugestao, resposta, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						exibirPopupInfo(caught.getMessage());

					}

					@Override
					public void onSuccess(Void result) {
						exibirPopupInfo("Resposta enviada com sucesso!");
					}
				});

	}

	private void exibirPopupInfo(String mensagem) {
		popupInfo = new PopupInfo(mensagem);
		popupInfo.center();
		popupInfo.show();
	}
}
