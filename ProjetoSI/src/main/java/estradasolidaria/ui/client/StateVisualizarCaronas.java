package estradasolidaria.ui.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

public class StateVisualizarCaronas extends Composite {
	private List<GWTCarona> caronas;
	private CellTable<GWTCarona> caronas_cellTable;
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
		caronas = new ArrayList<GWTCarona>();
		
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("586px", "487px");
		
		TabPanel tabPanel = new TabPanel();
		absolutePanel.add(tabPanel, 10, 10);
		tabPanel.setSize("566px", "467px");
		
		FlexTable flexTable = new FlexTable();
		tabPanel.add(flexTable, "Oferecidas", false);
		flexTable.setSize("549px", "426px");
		
		caronas_cellTable = new CellTable<GWTCarona>();
		flexTable.setWidget(0, 0, caronas_cellTable);
		caronas_cellTable.setHeight("145px");
		caronas_cellTable.setWidth("100%", true);
		
		
		final SelectionModel<GWTCarona> selectionModel = new MultiSelectionModel<GWTCarona>( new ProvidesKey<GWTCarona>() {

			@Override
			public Object getKey(GWTCarona item) {
				return item;
			}
		});
		caronas_cellTable.setSelectionModel(selectionModel,
		DefaultSelectionEventManager.<GWTCarona> createCheckboxManager());
		Column<GWTCarona, Boolean> checkBox_column = new Column<GWTCarona, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(GWTCarona carona) {
				return selectionModel.isSelected(carona);
			}
		};
		checkBox_column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(checkBox_column);
		caronas_cellTable.setColumnWidth(checkBox_column, "100%");
		
		TextColumn<GWTCarona> origem_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.origem;
			}
		};
		origem_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(origem_textColumn, "Origem");
		caronas_cellTable.setColumnWidth(origem_textColumn, "100%");
//		
		
		TextColumn<GWTCarona> destino_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.destino;
			}
		};
		destino_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(destino_textColumn, "Destino");
		caronas_cellTable.setColumnWidth(destino_textColumn, "100%");
		
		TextColumn<GWTCarona> data_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.data;
			}
		};
		data_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(data_textColumn, "Data");
		caronas_cellTable.setColumnWidth(data_textColumn, "100%");
		
		TextColumn<GWTCarona> hora_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.hora;
			}
		};
		hora_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(hora_textColumn, "Hora-Saida");
		caronas_cellTable.setColumnWidth(hora_textColumn, "100%");
		
		TextColumn<GWTCarona> vagas_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.vagas;
			}
		};
		vagas_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(vagas_textColumn, "Vagas");
		caronas_cellTable.setColumnWidth(vagas_textColumn, "100%");
		
		Column<GWTCarona, String> review_textColumn = new Column<GWTCarona, String>(new ButtonCell()) {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.review;
			}
		};
		review_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		caronas_cellTable.addColumn(review_textColumn, "Review");
		caronas_cellTable.setColumnWidth(review_textColumn, "100%");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable flexTable_1 = new FlexTable();
		tabPanel.add(flexTable_1, "Pegas", false);
		flexTable_1.setSize("5cm", "3cm");
		
		FlexTable flexTable_2 = new FlexTable();
		tabPanel.add(flexTable_2, "Solicitadas", false);
		flexTable_2.setSize("5cm", "3cm");
		
//		COLOCAR DADOS NA TABELA	
		geraListaDeCaronas(idSessao);
		
	}

	private void geraListaDeCaronas(Integer idSessao) {
		this.estradaSolidariaService.getTodasCaronasUsuario(idSessao, new AsyncCallback<List<List<String>>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<List<String>> result) {
				System.out.println(result.size() + " caronas cadastradas!");
				for (List<String> carona : result) {
					GWTCarona gwt_c = new GWTCarona(carona.get(0),
													carona.get(1),
													carona.get(2),
													carona.get(3),
													carona.get(4),
													carona.get(5));
					caronas.add(gwt_c);
				}
				System.out.println(caronas.size() + " CaronasGWT");
				caronas_cellTable.setRowCount(caronas.size(), true);
				caronas_cellTable.setRowData(0, caronas);
			}
		});
	}
}
