package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;

public class StateSolicitacoes extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessao;
	private DataGrid<GWTSolicitacao> dataGrid;
	private DataGrid<GWTSolicitacao> dataGrid_1;
												
	public StateSolicitacoes(EstradaSolidaria entryPoint,
			EstradaSolidariaServiceAsync estradaSolidaria) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaSolidaria;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("977px", "463px");
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel, 10, 10);
		decoratedStackPanel.setSize("943px", "212px");
		
		DataGrid<GWTSolicitacao> dataGrid = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel.add(dataGrid, "Solicitações Confirmadas", false);
		dataGrid.setSize("100%", "100%");
		
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGrid.addColumn(columnDonoDaCarona, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGrid.addColumn(columnCarona, "Carona");
		
		DecoratedStackPanel decoratedStackPanel_1 = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel_1, 10, 229);
		decoratedStackPanel_1.setSize("943px", "212px");
		
		dataGrid_1 = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel_1.add(dataGrid_1, "Solicitações Pendentes", false);
		dataGrid_1.setSize("100%", "100%");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona_1 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGrid_1.addColumn(columnDonoDaCarona_1, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona_1 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGrid_1.addColumn(columnCarona_1, "Carona");
		
		getSolicitacoesConfirmadas();
		getSolicitacoesPendentes();
	}

	private void getSolicitacoesPendentes() {
		estradaSolidariaService.getSolicitacoesFeitasPendentes(idSessao, new AsyncCallback<List<GWTSolicitacao>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
			@Override
			public void onSuccess(List<GWTSolicitacao> result) {
				dataGrid_1.setRowCount(result.size(), true);
				dataGrid_1.setRowData(result);
			}
		});
		
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
