package estradasolidaria.ui.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class StateVisualizarCaronas extends Composite {
	private List<Object> caronas = new ArrayList<Object>();
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	class GWTCarona {
		public GWTCarona(String origem, String destino, String data,
				String hora, String vagas, String review) {
					this.origem = origem;
					this.destino = destino;
					this.data = data;
					this.hora = hora;
					this.vagas = vagas;
					this.review = review;
		}
		String origem;
		String destino;
		String data;
		String hora;
		String vagas;
		String review;
	}
	
	public StateVisualizarCaronas(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		this.estradaSolidariaService.getTodasCaronasUsuario(idSessao, new AsyncCallback<List<List<String>>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<List<String>> result) {
				for (List<String> carona : result) {
					GWTCarona gwt_c = new GWTCarona(carona.get(0),
													carona.get(1),
													carona.get(2),
													carona.get(3),
													carona.get(4),
													carona.get(5));
					caronas.add(gwt_c);
				}
			}
		});
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("586px", "487px");
		
		TabPanel tabPanel = new TabPanel();
		absolutePanel.add(tabPanel, 10, 10);
		tabPanel.setSize("547px", "467px");
		
		FlexTable flexTable = new FlexTable();
		tabPanel.add(flexTable, "Oferecidas", false);
		flexTable.setSize("532px", "3cm");
		
		CellTable<Object> caronas_cellTable = new CellTable<Object>();
		flexTable.setWidget(0, 0, caronas_cellTable);
		caronas_cellTable.setWidth("526px");
		
		Column<Object, Boolean> checkBox_column = new Column<Object, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(Object object) {
				return (Boolean) null;
			}
		};
		caronas_cellTable.addColumn(checkBox_column);
		
		TextColumn<Object> origem_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return ((GWTCarona)object).origem;
			}
		};
//		
		caronas_cellTable.addColumn(origem_textColumn, "Origem");
		
		TextColumn<Object> destino_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return ((GWTCarona)object).destino;
			}
		};
		caronas_cellTable.addColumn(destino_textColumn, "Destino");
		
		TextColumn<Object> data_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return ((GWTCarona)object).data;
			}
		};
		caronas_cellTable.addColumn(data_textColumn, "Data");
		
		TextColumn<Object> hora_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return ((GWTCarona)object).hora;
			}
		};
		caronas_cellTable.addColumn(hora_textColumn, "Hora-Saida");
		
//		Column<Object, Number> vagas_column = new Column<Object, Number>(new NumberCell()) {
//			@Override
//			public Number getValue(Object object) {
//				return (Number) null;
//			}
//		};
//		Objects_cellTable.addColumn(vagas_column, "Vagas");
//		
//		Column<Object, String> review_column = new Column<Object, String>(new ButtonCell()) {
//			@Override
//			public String getValue(Object object) {
//				return (String) null;
//			}
//		};
//		Objects_cellTable.addColumn(review_column, "Review");
		
		FlexTable flexTable_1 = new FlexTable();
		tabPanel.add(flexTable_1, "Pegas", false);
		flexTable_1.setSize("5cm", "3cm");
		
		FlexTable flexTable_2 = new FlexTable();
		tabPanel.add(flexTable_2, "Solicitadas", false);
		flexTable_2.setSize("5cm", "3cm");
		
//		COLOCAR DADOS NA TABELA	
		caronas_cellTable.setRowCount(caronas.size(), true);
		caronas_cellTable.setRowData(0, caronas);
	}
}
