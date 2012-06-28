package estradasolidaria.ui.client;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;

public class StatePesquisaCarona extends Composite {
	
	final EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private AbsolutePanel bodyPanel;
	final TextBox textBoxOrigem;
	final TextBox textBoxDestino; 
	
	public StatePesquisaCarona(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService, AbsolutePanel bodyPanel) {
		
		this.estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		this.bodyPanel = bodyPanel;
		final Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("340px", "219px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 28, 52);
		flexTable.setSize("283px", "53px");
		
		Label lblPesuisa = new Label("Origem:");
		lblPesuisa.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(0, 0, lblPesuisa);
		
		textBoxOrigem = new TextBox();
		flexTable.setWidget(0, 1, textBoxOrigem);
		
		Label lblDestino = new Label("Destino:");
		lblDestino.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(1, 0, lblDestino);
		
		textBoxDestino = new TextBox();
		flexTable.setWidget(1, 1, textBoxDestino);
		
		Label lblCidade = new Label("Cidade:");
		lblCidade.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(2, 0, lblCidade);
		
		TextBox textBoxCidade = new TextBox();
		flexTable.setWidget(2, 1, textBoxCidade);
		
		Label lblPesquiseUmaCarona = new Label("Pesquise uma carona");
		lblPesquiseUmaCarona.setStyleName("gwt-LabelHomePage2");
		absolutePanel.add(lblPesquiseUmaCarona, 28, 10);
		
		TextButton btnPesquisa = new TextButton("New button");
		absolutePanel.add(btnPesquisa, 278, 176);
		btnPesquisa.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pesquisarCaronasNoSistema(idSessao, textBoxOrigem.getText(), textBoxDestino.getText());
			}
		});
		btnPesquisa.setText("Go!");
	}

	private void pesquisarCaronasNoSistema(Integer sessao, String origem, String destino) {
		estradaSolidariaService.localizarCarona(sessao, origem, destino,  new AsyncCallback<Map<String, Integer>>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Map<String, Integer> result) {
				bodyPanel.clear();
				Widget caronasEncontradas = new StateCaronasEncontradas(estradaSolidariaService, estrada, result);
				bodyPanel.add(caronasEncontradas);
				caronasEncontradas.setSize("100%", "100%");
			}
		  });

	}
}
