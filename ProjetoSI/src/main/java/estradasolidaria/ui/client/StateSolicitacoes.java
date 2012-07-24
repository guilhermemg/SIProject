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
	private DataGrid<GWTSolicitacao> dataGridConfirmadas;
	private DataGrid<GWTSolicitacao> dataGridPendentes;
	private DataGrid<GWTSolicitacao> dataGridRejeitadas;
	private DataGrid<GWTSolicitacao> dataGridCanceladas;
												
	public StateSolicitacoes(EstradaSolidaria entryPoint,
			EstradaSolidariaServiceAsync estradaSolidaria) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaSolidaria;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("977px", "571px");
		
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel, 10, 10);
		decoratedStackPanel.setSize("441px", "248px");
		
		dataGridConfirmadas = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel.add(dataGridConfirmadas, "Solicitações Confirmadas", false);
		dataGridConfirmadas.setSize("100%", "100%");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGridConfirmadas.addColumn(columnDonoDaCarona, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGridConfirmadas.addColumn(columnCarona, "Carona");
		
		DecoratedStackPanel decoratedStackPanel_1 = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel_1, 468, 10);
		decoratedStackPanel_1.setSize("441px", "248px");
		
		dataGridPendentes = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel_1.add(dataGridPendentes, "Solicitações Pendentes", false);
		dataGridPendentes.setSize("100%", "100%");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona_1 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGridPendentes.addColumn(columnDonoDaCarona_1, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona_1 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGridPendentes.addColumn(columnCarona_1, "Carona");
		
		DecoratedStackPanel decoratedStackPanel_2 = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel_2, 10, 264);
		decoratedStackPanel_2.setSize("441px", "248px");
		
		dataGridRejeitadas = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel_2.add(dataGridRejeitadas, "Solicitações Rejeitadas", false);
		dataGridRejeitadas.setSize("100%", "100%");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona_2 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGridRejeitadas.addColumn(columnDonoDaCarona_2, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona_2 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGridRejeitadas.addColumn(columnCarona_2, "Carona");
		
		DecoratedStackPanel decoratedStackPanel_3 = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel_3, 468, 264);
		decoratedStackPanel_3.setSize("441px", "248px");
		
		dataGridCanceladas = new DataGrid<GWTSolicitacao>();
		decoratedStackPanel_3.add(dataGridCanceladas, "Solicitações Canceladas", false);
		dataGridCanceladas.setSize("100%", "100%");
		
		TextColumn<GWTSolicitacao> columnDonoDaCarona_3 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return solicitacao.getNomeDono();
			}
		};
		dataGridCanceladas.addColumn(columnDonoDaCarona_3, "Dono da carona");
		
		TextColumn<GWTSolicitacao> columnCarona_3 = new TextColumn<GWTSolicitacao>() {
			@Override
			public String getValue(GWTSolicitacao solicitacao) {
				return "De " + solicitacao.getOrigemCarona() + " para " + solicitacao.getDestinoCarona() +
						" no dia " + solicitacao.getDataCarona() + " as " + solicitacao.getHoraCarona() +
						".";
			}
		};
		dataGridCanceladas.addColumn(columnCarona_3, "Carona");
		
		getSolicitacoesConfirmadas();
		getSolicitacoesPendentes();
		getSolicitacoesRejeitadas();
		getSolicitacoesCanceladas();
	}

	private void getSolicitacoesCanceladas() {
		estradaSolidariaService.getSolicitacoesFeitasCanceladas(idSessao, new AsyncCallback<List<GWTSolicitacao>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
			@Override
			public void onSuccess(List<GWTSolicitacao> result) {
				dataGridCanceladas.setRowCount(result.size(), true);
				dataGridCanceladas.setRowData(result);
			}
		});
		
	}

	private void getSolicitacoesRejeitadas() {
		estradaSolidariaService.getSolicitacoesFeitasRejeitadas(idSessao, new AsyncCallback<List<GWTSolicitacao>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
			@Override
			public void onSuccess(List<GWTSolicitacao> result) {
				dataGridRejeitadas.setRowCount(result.size(), true);
				dataGridRejeitadas.setRowData(result);
			}
		});
		
	}

	private void getSolicitacoesPendentes() {
		estradaSolidariaService.getSolicitacoesFeitasPendentes(idSessao, new AsyncCallback<List<GWTSolicitacao>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
			@Override
			public void onSuccess(List<GWTSolicitacao> result) {
				dataGridPendentes.setRowCount(result.size(), true);
				dataGridPendentes.setRowData(result);
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
				dataGridConfirmadas.setRowCount(result.size(), true);
				dataGridConfirmadas.setRowData(result);
			}
		});
	}
}
