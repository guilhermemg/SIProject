package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupSugerirPonto extends PopupPanel {
	private TextBox textBox;
	private Integer idCaronaEscolhida;
	private EstradaSolidariaServiceAsync estradaSolidariaService;

	public PopupSugerirPonto(EstradaSolidariaServiceAsync estradaSolidariaService, Integer idCarona) {
		
		this.estradaSolidariaService = estradaSolidariaService;
		this.idCaronaEscolhida = idCarona;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("212px", "113px");
		
		textBox = new TextBox();
		absolutePanel.add(textBox, 10, 33);
		textBox.setSize("182px", "17px");
		
		TextButton txtbtnNewButton = new TextButton("Sugerir");
		txtbtnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (textBox.getText().equals("")) {
					PopupInfo p = new PopupInfo("Texto vazio, sugira um ponto de encontro!");
					p.center();
					p.show();
				} else {
					sugerirPonto(idCaronaEscolhida);
				}
			}
		});
		absolutePanel.add(txtbtnNewButton, 10, 68);
		txtbtnNewButton.setSize("88px", "29px");
		
		Label lblSugiraUmPonto = new Label("Sugira um ponto de encontro");
		absolutePanel.add(lblSugiraUmPonto, 10, 10);
		lblSugiraUmPonto.setSize("192px", "17px");
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 104, 67);
		txtbtnCancelar.setSize("94px", "29px");
	}

	private void sugerirPonto(Integer idCaronaEscolhida) {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		String pontoDeEncontro = textBox.getText();
		estradaSolidariaService.sugerirPontoEncontro(idSessao, idCaronaEscolhida, pontoDeEncontro, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupInfo p = new PopupInfo(caught.getMessage());
				p.center();
				p.show();
				
			}

			@Override
			public void onSuccess(Void result) {
				PopupInfo p = new PopupInfo("Sugestão realizada!");
				p.center();
				p.show();
				
			}
		});
	}

}
