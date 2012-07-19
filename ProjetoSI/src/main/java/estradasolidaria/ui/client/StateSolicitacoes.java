package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;

public class StateSolicitacoes extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessao;
	
	private DataGrid<GWTSolicitacao> dataGrid;
												
	public StateSolicitacoes(EstradaSolidaria entryPoint,
			EstradaSolidariaServiceAsync estradaSolidaria) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaSolidaria;
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		TabPanel tabPanel = new TabPanel();
		initWidget(tabPanel);
		
		dataGrid = new DataGrid<GWTSolicitacao>();
		dataGrid.setVisible(true);
		tabPanel.add(dataGrid, "New tab", false);
		dataGrid.setSize("672px", "205px");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getDonoDaCarona().getLogin();
			}
		};
		dataGrid.addColumn(columnDonoDaCarona, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getCarona().getOrigem() + " para " + solicitacao.getCarona().getDestino() +
						" no dia " + solicitacao.getCarona().getData() + " as " + solicitacao.getCarona().getHora() +
						".";
			}
		};
		dataGrid.addColumn(columnCarona, "Carona");
		
		getSolicitacoesConfirmadas();
	}

	private void getSolicitacoesConfirmadas() {
		estradaSolidariaService.getSolicitacoesFeitasConfirmadas(idSessao, new AsyncCallback<List<GWTSolicitacao>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
			@Override
			public void onSuccess(List<GWTSolicitacao> result) {
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(result);
			}
		});
	}
}
