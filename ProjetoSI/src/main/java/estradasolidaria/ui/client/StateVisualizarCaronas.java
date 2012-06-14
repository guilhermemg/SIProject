package estradasolidaria.ui.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

public class StateVisualizarCaronas extends Composite {
	private List<GWTCarona> caronas_oferecidas;
	private CellTable<GWTCarona> oferecidas_cellTable;
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private CellTable<GWTCarona> pegas_cellTable;
	private List<GWTCarona> caronas_pegas;
	private CellTable<GWTCarona> solicitadas_cellTable;
	private ArrayList<GWTCarona> caronas_solicitadas;
	
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
		protected String origem;
		protected String destino;
		protected String data;
		protected String hora;
		protected String vagas;
		protected String review;
		protected String dono;
	}
	
	public StateVisualizarCaronas(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");
		
//----- CARONAS OFERECIDAS -----------------------------
		TabPanel tabPanel = new TabPanel();
		absolutePanel.add(tabPanel, 10, 10);
		tabPanel.setSize("100%", "100%");
		
		FlexTable flexTable = new FlexTable();
		tabPanel.add(flexTable, "Oferecidas", false);
		flexTable.setSize("100%", "100%");
		
		oferecidas_cellTable = new CellTable<GWTCarona>();
		flexTable.setWidget(0, 0, oferecidas_cellTable);
		oferecidas_cellTable.setHeight("145px");
		oferecidas_cellTable.setWidth("100%", true);
		
		
		final SelectionModel<GWTCarona> selectionModel = new MultiSelectionModel<GWTCarona>( new ProvidesKey<GWTCarona>() {
			@Override
			public Object getKey(GWTCarona item) {
				return item;
			}
		});
		oferecidas_cellTable.setSelectionModel(selectionModel,
		DefaultSelectionEventManager.<GWTCarona> createCheckboxManager());
		Column<GWTCarona, Boolean> checkBox_column = new Column<GWTCarona, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(GWTCarona carona) {
				return selectionModel.isSelected(carona);
			}
		};
		checkBox_column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(checkBox_column);
		oferecidas_cellTable.setColumnWidth(checkBox_column, "100%");
		
		TextColumn<GWTCarona> origem_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.origem;
			}
		};
		origem_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(origem_textColumn, "Origem");
		oferecidas_cellTable.setColumnWidth(origem_textColumn, "100%");
//		
		
		TextColumn<GWTCarona> destino_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.destino;
			}
		};
		destino_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(destino_textColumn, "Destino");
		oferecidas_cellTable.setColumnWidth(destino_textColumn, "100%");
		
		TextColumn<GWTCarona> data_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.data;
			}
		};
		data_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(data_textColumn, "Data");
		oferecidas_cellTable.setColumnWidth(data_textColumn, "100%");
		
		TextColumn<GWTCarona> hora_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.hora;
			}
		};
		hora_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(hora_textColumn, "Hora-Saida");
		oferecidas_cellTable.setColumnWidth(hora_textColumn, "100%");
		
		TextColumn<GWTCarona> vagas_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.vagas;
			}
		};
		vagas_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(vagas_textColumn, "Vagas");
		oferecidas_cellTable.setColumnWidth(vagas_textColumn, "100%");
		
		Column<GWTCarona, String> review_textColumn = new Column<GWTCarona, String>(new ButtonCell()) {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.review;
			}
		};
		review_textColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		oferecidas_cellTable.addColumn(review_textColumn, "Review");
		oferecidas_cellTable.setColumnWidth(review_textColumn, "100%");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
        //COLOCAR DADOS NA TABELA	
		geraListaDeCaronasOferecidas(idSessao);
		
//----- CARONAS PEGAS -----------------------------------
		
		FlexTable flexTable_pegas = new FlexTable();
		tabPanel.add(flexTable_pegas, "Pegas", false);
		flexTable_pegas.setSize("100%", "100%");
		
		pegas_cellTable = new CellTable<GWTCarona>();
		flexTable_pegas.setWidget(0, 0, pegas_cellTable);
		pegas_cellTable.setHeight("145px");
		pegas_cellTable.setWidth("100%", true);
		
		
		Column<GWTCarona, Boolean> checkBox_column_2 = new Column<GWTCarona, Boolean>(new CheckboxCell()) {
			public Boolean getValue(GWTCarona carona) {
				return selectionModel.isSelected(carona);
			}
		};
		pegas_cellTable.addColumn(checkBox_column_2);
		pegas_cellTable.setColumnWidth(checkBox_column_2, "100%");
		
		TextColumn<GWTCarona> dono_textColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona object) {
				return object.toString();
			}
		};
		pegas_cellTable.addColumn(dono_textColumn, "Dono");
		pegas_cellTable.setColumnWidth(dono_textColumn, "100%");
		
		TextColumn<GWTCarona> origem_textColumn_2 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.origem;
			}
		};
		pegas_cellTable.addColumn(origem_textColumn_2, "Origem");
		pegas_cellTable.setColumnWidth(origem_textColumn_2, "100%");
		
		TextColumn<GWTCarona> destino_textColumn_2 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.destino;
			}
		};
		pegas_cellTable.addColumn(destino_textColumn_2, "Destino");
		pegas_cellTable.setColumnWidth(destino_textColumn_2, "100%");
		
		TextColumn<GWTCarona> data_textColumn_2 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.data;
			}
		};
		pegas_cellTable.addColumn(data_textColumn_2, "Data");
		pegas_cellTable.setColumnWidth(data_textColumn_2, "100%");
		
		TextColumn<GWTCarona> hora_textColumn_2 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.hora;
			}
		};
		pegas_cellTable.addColumn(hora_textColumn_2, "Hora-Saida");
		pegas_cellTable.setColumnWidth(hora_textColumn_2, "100%");
		
		TextColumn<GWTCarona> vagas_textColumn_2 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.vagas;
			}
		};
		pegas_cellTable.addColumn(vagas_textColumn_2, "Vagas");
		pegas_cellTable.setColumnWidth(vagas_textColumn_2, "100%");
		
		Column<GWTCarona, String> review_column_2 = new Column<GWTCarona, String>(new ButtonCell()) {
			public String getValue(GWTCarona carona) {
				return carona.review;
			}
		};
		pegas_cellTable.addColumn(review_column_2, "Review");
		pegas_cellTable.setColumnWidth(review_column_2, "100%");
		
		flexTable_pegas.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		//COLOCA OS DADOS NA TABELA DE CARONAS PEGAS
		geraListaDeCaronasPegas(idSessao);
		
//----- CARONAS SOLICITADAS -------------------------------
		FlexTable flexTable_2 = new FlexTable();
		tabPanel.add(flexTable_2, "Solicitadas", false);
		flexTable_2.setSize("100%", "100%");
		
		solicitadas_cellTable = new CellTable<GWTCarona>();
		flexTable_2.setWidget(0, 0, solicitadas_cellTable);
		solicitadas_cellTable.setHeight("145px");
		solicitadas_cellTable.setWidth("100%", true);
		
		Column<GWTCarona, Boolean> checkBox_column_3 = new Column<GWTCarona, Boolean>(new CheckboxCell()) {
			public Boolean getValue(GWTCarona carona) {
				return selectionModel.isSelected(carona);
			}
		};
		solicitadas_cellTable.addColumn(checkBox_column_3);
		solicitadas_cellTable.setColumnWidth(checkBox_column_3, "100%");
		
		TextColumn<GWTCarona> dono_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return "Dono"/*carona.dono*/;
			}
		};
		solicitadas_cellTable.addColumn(dono_textColumn_3, "Dono");
		solicitadas_cellTable.setColumnWidth(dono_textColumn_3, "100%");
		
		TextColumn<GWTCarona> origem_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.origem;
			}
		};
		solicitadas_cellTable.addColumn(origem_textColumn_3, "Origem");
		solicitadas_cellTable.setColumnWidth(origem_textColumn_3, "100%");
		
		TextColumn<GWTCarona> destino_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.destino;
			}
		};
		solicitadas_cellTable.addColumn(destino_textColumn_3, "Destino");
		solicitadas_cellTable.setColumnWidth(destino_textColumn_3, "100%");
		
		TextColumn<GWTCarona> data_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.data;
			}
		};
		solicitadas_cellTable.addColumn(data_textColumn_3, "Data");
		solicitadas_cellTable.setColumnWidth(data_textColumn_3, "100%");
		
		TextColumn<GWTCarona> hora_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.hora;
			}
		};
		solicitadas_cellTable.addColumn(hora_textColumn_3, "Hora-Saida");
		solicitadas_cellTable.setColumnWidth(hora_textColumn_3, "100%");
		
		TextColumn<GWTCarona> vagas_textColumn_3 = new TextColumn<GWTCarona>() {
			public String getValue(GWTCarona carona) {
				return carona.vagas;
			}
		};
		solicitadas_cellTable.addColumn(vagas_textColumn_3, "Vagas");
		solicitadas_cellTable.setColumnWidth(vagas_textColumn_3, "100%");
		
		Column<GWTCarona, String> review_column_3 = new Column<GWTCarona, String>(new ButtonCell()) {
			public String getValue(GWTCarona carona) {
				return carona.review;
			}
		};
		solicitadas_cellTable.addColumn(review_column_3, "Review");
		solicitadas_cellTable.setColumnWidth(review_column_3, "100%");
		flexTable_2.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		geraListaDeCaronasSolicitadas(idSessao);
		
	}

	private void geraListaDeCaronasOferecidas(Integer idSessao) {
		caronas_oferecidas = new ArrayList<GWTCarona>();
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
					caronas_oferecidas.add(gwt_c);
				}
				oferecidas_cellTable.setRowCount(caronas_oferecidas.size(), true);
				oferecidas_cellTable.setRowData(0, caronas_oferecidas);
			}
		});
	}
	
	private void geraListaDeCaronasPegas(Integer idSessao) {
		caronas_pegas= new ArrayList<GWTCarona>();
		this.estradaSolidariaService.getTodasCaronasPegas(idSessao, new AsyncCallback<List<List<String>>>() {

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
					caronas_pegas.add(gwt_c);
				}
				pegas_cellTable.setRowCount(caronas_pegas.size(), true);
				pegas_cellTable.setRowData(0, caronas_pegas);
			}
		});
	}

	private void geraListaDeCaronasSolicitadas(Integer idSessao) {
//		caronas_solicitadas = new ArrayList<GWTCarona>();
//		this.estradaSolidariaService.getSolicitacoesConfirmadas(idSessao, new AsyncCallback<List<List<String>>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<List<String>> result) {
//				for (List<String> c : result) {
//					GWTCarona gwt_c = new GWTCarona(c.get(0),
//													c.get(1),
//													c.get(2),
//													c.get(3),
//													c.get(4),
//													c.get(5));
//					gwt_c.dono = c.get(6);
//					caronas_solicitadas.add(gwt_c);
//				}
//				solicitadas_cellTable.setRowCount(caronas_solicitadas.size(), true);
//				solicitadas_cellTable.setRowData(0, caronas_solicitadas);
//			}
//		});
	}
}
