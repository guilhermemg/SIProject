package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class StateMeusInteresses extends AbsolutePanel {
	private List<GWTInteresse> listaDeInteresses;

	private EstradaSolidaria estrada;

	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Button btnAdicionar;
	private DataGrid<GWTInteresse> dataGrid;
	
	public StateMeusInteresses(final EstradaSolidaria estrada, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		setTitle("Interresses");
		listaDeInteresses = new LinkedList<GWTInteresse>();
		
		
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		add(absolutePanel_1);
		absolutePanel_1.setSize("100%", "");
		
		btnAdicionar = new Button("Adicionar");
		btnAdicionar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupPanel p = new PopUpAdicionarInteresse(estrada, estradaSolidariaService);
				p.center();
				p.show();
			}
		});
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		add(absolutePanel);
		absolutePanel.setSize("100%", "95%");
		
		dataGrid = new DataGrid<GWTInteresse>();
		absolutePanel.add(dataGrid, 0, 0);
		dataGrid.setSize("100%", "100%");
		
		TextColumn<GWTInteresse> columnOrigem = new TextColumn<GWTInteresse>() {
			@Override
			public String getValue(GWTInteresse interesse) {
				return interesse.getOrigem();
			}
		};
		dataGrid.addColumn(columnOrigem, "Origem");
		
		TextColumn<GWTInteresse> columnDestino = new TextColumn<GWTInteresse>() {
			@Override
			public String getValue(GWTInteresse interesse) {
				return interesse.getDestino();
			}
		};
		columnDestino.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		dataGrid.addColumn(columnDestino, "Destino");
		
		TextColumn<GWTInteresse> columnData = new TextColumn<GWTInteresse>() {
			@Override
			public String getValue(GWTInteresse interesse) {
				return interesse.getData();
			}
		};
		dataGrid.addColumn(columnData, "Data");
		
		TextColumn<GWTInteresse> columnHoraInicio = new TextColumn<GWTInteresse>() {
			@Override
			public String getValue(GWTInteresse interesse) {
				return interesse.getHoraInicio();
			}
		};
		dataGrid.addColumn(columnHoraInicio, "Hora-Início");
		
		TextColumn<GWTInteresse> columnHoraFim = new TextColumn<GWTInteresse>() {
			@Override
			public String getValue(GWTInteresse interesse) {
				return interesse.getHoraFim();
			}
		};
		dataGrid.addColumn(columnHoraFim, "Hora-Fim");
		absolutePanel_1.add(btnAdicionar);
		
		colocarInteressesNoGrid();
	}

	private void colocarInteressesNoGrid() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaSolidariaService.getInteresses(idSessao, new AsyncCallback<List<GWTInteresse>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(List<GWTInteresse> result) {
				listaDeInteresses = result;
				System.out.println("size: " + result.size());
				dataGrid.setRowCount(listaDeInteresses.size(), true);
				dataGrid.setRowData(listaDeInteresses);
				
			}
		});
		
	}
}