package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class StateMinhasSugestoes extends AbsolutePanel {
	final Widget panel = this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private PopupInfo popupInfo;
	private CellTable<GWTSugestao> cellTableSugestoes;
												
	public StateMinhasSugestoes(EstradaSolidariaServiceAsync estradaSolidariaService) {
		setSize("590px", "400px");
		this.estradaSolidariaService = estradaSolidariaService;
		
		TabPanel tabPanel = new TabPanel();
		add(tabPanel, 0, 0);
		tabPanel.setSize("100%", "100%");
		
		ScrollPanel scrollPanel = new ScrollPanel();
		tabPanel.add(scrollPanel, "Sugestões", false);
		scrollPanel.setSize("100%", "100%");
		
		cellTableSugestoes = new CellTable<GWTSugestao>();
		scrollPanel.setWidget(cellTableSugestoes);
		cellTableSugestoes.setSize("465px", "100%");
		
		TextColumn<GWTSugestao> textColumnDonoDaCarona = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnDonoDaCarona, "Dono da Carona");
		cellTableSugestoes.setColumnWidth(textColumnDonoDaCarona, "100%");
		
		TextColumn<GWTSugestao> textColumnOrigem = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnOrigem, "Origem");
		cellTableSugestoes.setColumnWidth(textColumnOrigem, "100%");
		
		TextColumn<GWTSugestao> textColumnDestino = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnDestino, "Destino");
		cellTableSugestoes.setColumnWidth(textColumnDestino, "100%");
		
		TextColumn<GWTSugestao> textColumnData = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnData, "Data");
		cellTableSugestoes.setColumnWidth(textColumnData, "100%");
		
		TextColumn<GWTSugestao> textColumnHora = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnHora, "Hora");
		cellTableSugestoes.setColumnWidth(textColumnHora, "100%");
		
		TextColumn<GWTSugestao> textColumnResposta = new TextColumn<GWTSugestao>() {
			@Override
			public String getValue(GWTSugestao object) {
				return object.toString();
			}
		};
		cellTableSugestoes.addColumn(textColumnResposta, "Resposta");
		cellTableSugestoes.setColumnWidth(textColumnResposta, "100%");
		
		colocarSugestoesNaCellTable();
		
		//Seleciona a tab com índice 0
		tabPanel.selectTab(0);
		
	}

	private void colocarSugestoesNaCellTable() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaSolidariaService.getSugestoes(idSessao, new AsyncCallback<List<GWTSugestao>>() {

			@Override
			public void onFailure(Throwable caught) {
				exibirPopupInfo(caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<GWTSugestao> result) {
				cellTableSugestoes.setRowCount(result.size(), true);
				cellTableSugestoes.setRowData(0, result);
			}
		});
	}
	
	private void exibirPopupInfo(String mensagem) {
		popupInfo = new PopupInfo(mensagem);
		popupInfo.center();
		popupInfo.show();
	}
}
