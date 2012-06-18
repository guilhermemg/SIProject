package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

public class StateVisualizarCaronas extends AbsolutePanel {
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;

	private CellTable<GWTCarona> caronasCellTable;

	private LinkedList<GWTCarona> caronasGWT; //Lista de Caronas transformadas para a classe GWTCarona
	private FlexTable flexTableOferecidas;
	private SelectionModel<GWTCarona> selectionModel;
	private Column<GWTCarona, String> donoDaCaronaColumn;
	private TextColumn<GWTCarona> origemColumn;
	private TextColumn<GWTCarona> destinoColumn;
	private Column<GWTCarona, Boolean> checkBoxColumn;
	private TextColumn<GWTCarona> dataColumn;
	private TextColumn<GWTCarona> horaColumn;
	private TextColumn<GWTCarona> vagasColumn;
	private Column<GWTCarona, String> reviewColumn;
	private HasHorizontalAlignment pontoDeEncontroColumn;
	private Integer idSessao;
	private FlexTable flexTablePegas;
	private TabPanel tabPanel;
	private FlexTable flexTableSolicitadas;
	private boolean isOferecida;
	
	public StateVisualizarCaronas(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		setSize("586px", "487px");
		
				tabPanel = new TabPanel();
				add(tabPanel);
				tabPanel.setSize("100%", "100%");
				
						tabPanel.getTabBar().addSelectionHandler(new SelectionHandler<Integer>() {
				
							@Override
							public void onSelection(SelectionEvent<Integer> event) {
								if (event.getSelectedItem().equals(0)) {
									isOferecida = true;
									processarTabOferecidas();
								} else if (event.getSelectedItem().equals(1)) {
									isOferecida = false;
									processarTabPegas();
								} else if (event.getSelectedItem().equals(2)) {
									isOferecida = false;
				//					processarSolicitadas();
								}
							}
						});
						flexTableOferecidas = new FlexTable();
						tabPanel.add(flexTableOferecidas, "Oferecidas", false);
						flexTableOferecidas.setSize("100%", "100%");
						flexTableOferecidas.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
						flexTablePegas = new FlexTable();
						tabPanel.add(flexTablePegas, "Pegas", false);
						flexTablePegas.setSize("549px", "426px");
						flexTablePegas.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
						flexTableSolicitadas = new FlexTable();
						flexTableSolicitadas.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								gerarListaDeCaronasSolicitadas(idSessao);
								zerarCaronasCellTable();
								iniciarColunas();
								colocarColunasEmCaronasCellTableSolicitadas();
							}

							private void colocarColunasEmCaronasCellTableSolicitadas() {
								// TODO Auto-generated method stub

							}
						});
						tabPanel.add(flexTableSolicitadas, "Solicitadas", false);
						flexTableSolicitadas.setSize("549px", "426px");
						flexTableSolicitadas.setWidget(0, 0, caronasCellTable);
						flexTableSolicitadas.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
						caronasCellTable = new CellTable<GWTCarona>();
						caronasCellTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<GWTCarona> createCheckboxManager());
						caronasCellTable.setHeight("145px");
						caronasCellTable.setWidth("100%", true);


		iniciarColunas();

		iniciarCaronasCellTable();

		iniciarFlexTableOferecidas();

		iniciarFlexTablePegas();

		iniciarFlexTableSolicitadas();
	}


	private void iniciarFlexTableOferecidas() {
	}

	private void iniciarFlexTablePegas() {
	}

	private void iniciarFlexTableSolicitadas() {
	}

	private void iniciarCaronasCellTable() {
		selectionModel = new MultiSelectionModel<GWTCarona>( new ProvidesKey<GWTCarona>() {
			@Override
			public Object getKey(GWTCarona item) {
				return item;
			}
		});
		zerarCaronasCellTable();
	}

	private void zerarCaronasCellTable() {
	}

	private void gerarListaDeCaronasOferecidas(Integer idSessao) {
		caronasGWT = new LinkedList<GWTCarona>();
		this.estradaSolidariaService.getTodasCaronasUsuario(idSessao, new AsyncCallback<List<List<String>>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<List<String>> result) {
				atualizaListaGWTCaronas(result);

				caronasCellTable.setRowCount(caronasGWT.size(), true);
				caronasCellTable.setRowData(0, caronasGWT);
			}
		});
	}

	private void gerarListaDeCaronasPegas(Integer idSessao) {
		this.estradaSolidariaService.getTodasCaronasPegas(idSessao, new AsyncCallback<List<List<String>>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<List<String>> result) {
				atualizaListaGWTCaronas(result);
				caronasCellTable.setRowCount(caronasGWT.size(), true);
				caronasCellTable.setRowData(0, caronasGWT);
			}
		});
	}

	private void gerarListaDeCaronasSolicitadas(Integer idSessao) {
//		this.estradaSolidariaService.getSolicitacoesConfirmadas(idSessao, new AsyncCallback<List<List<String>>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<List<String>> result) {
//				atualizaListaGWTCaronas(result);
//				solicitadas_cellTable.setRowCount(caronasGWT.size(), true);
//				solicitadas_cellTable.setRowData(0, caronasGWT);
//			}
//		});
	}

	private void atualizaListaGWTCaronas(List<List<String>> result) {
		//ZERA LISTA
		caronasGWT = new LinkedList<GWTCarona>();
		//ADICIONA AS CARONAS GWT
		for (List<String> carona : result) {
			GWTCarona gwt_c = new GWTCarona();
			
			gwt_c.idDono = carona.get(0);
			gwt_c.origem = carona.get(1);
			gwt_c.destino = carona.get(2);
			gwt_c.data = carona.get(3);
			gwt_c.hora = carona.get(4);
			gwt_c.vagas = carona.get(5);
			gwt_c.pontoEncontro = carona.get(6);
			gwt_c.nomeDono = carona.get(7);
			gwt_c.idCarona = carona.get(8);

			caronasGWT.add(gwt_c);
		}
	}

	private void iniciarColunas() {
		//Coluna de checkBox das Caronas
		checkBoxColumn = new Column<GWTCarona, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(GWTCarona carona) {
				return selectionModel.isSelected(carona);
			}
		};
		checkBoxColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de Donos das Caronas
		donoDaCaronaColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.nomeDono;
			}
		};
		donoDaCaronaColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de origens das Caronas
		origemColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.origem;
			}
		};
		origemColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de destinos das Caronas
		destinoColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.destino;
			}
		};
		destinoColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de datas das Caronas
		dataColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.data;
			}
		};
		dataColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de horas de saida da Caronas
		horaColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.hora;
			}
		};
		horaColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de vagas das Caronas
		vagasColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.vagas;
			}
		};
		vagasColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		//Coluna de reviews das Caronas
		reviewColumn = new Column<GWTCarona, String>(new ButtonCell()) {
			@Override
			public String getValue(GWTCarona carona) {
				return "Review";
			}
		};
		reviewColumn.setFieldUpdater(new FieldUpdater<GWTCarona, String>() {
			
			@Override
			public void update(int index, GWTCarona carona, String value) {
				PopupPanel popupPanelEditarReview = new PopUpEditarReview(estrada, estradaSolidariaService, isOferecida, carona.idCarona);
				popupPanelEditarReview.center();
				popupPanelEditarReview.show();
			}
		});
		reviewColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		//Coluna de pontos de encontro das Caronas
		pontoDeEncontroColumn = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.pontoEncontro;
			}
		};
		pontoDeEncontroColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

	}

	private void colocarColunasEmCaronasCellTableOferecidas() {
		zerarCaronasCellTable();

		caronasCellTable.addColumn(checkBoxColumn);
		caronasCellTable.setColumnWidth(checkBoxColumn, "100%");

		caronasCellTable.addColumn(origemColumn, "Origem");
		caronasCellTable.setColumnWidth(origemColumn, "100%");

		caronasCellTable.addColumn(destinoColumn, "Destino");
		caronasCellTable.setColumnWidth(destinoColumn, "100%");

		caronasCellTable.addColumn(dataColumn, "Data");
		caronasCellTable.setColumnWidth(dataColumn, "100%");

		caronasCellTable.addColumn(horaColumn, "Hora-Saída");
		caronasCellTable.setColumnWidth(horaColumn, "100%");

		caronasCellTable.addColumn(vagasColumn, "Vagas");
		caronasCellTable.setColumnWidth(vagasColumn, "100%");

		caronasCellTable.addColumn(reviewColumn, "Review");
		caronasCellTable.setColumnWidth(reviewColumn, "100%");

		//COLOCA caronasCellTable em flexTableOferecidas
		flexTableOferecidas.setWidget(0, 0, caronasCellTable);
	}

	private void colocarColunasEmCaronasCellTablePegas() {
		zerarCaronasCellTable();

		caronasCellTable.addColumn(checkBoxColumn);
		caronasCellTable.setColumnWidth(checkBoxColumn, "100%");

		caronasCellTable.addColumn(donoDaCaronaColumn, "Dono");
		caronasCellTable.setColumnWidth(donoDaCaronaColumn, "100%");

		caronasCellTable.addColumn(origemColumn, "Origem");
		caronasCellTable.setColumnWidth(origemColumn, "100%");

		caronasCellTable.addColumn(destinoColumn, "Destino");
		caronasCellTable.setColumnWidth(destinoColumn, "100%");

		caronasCellTable.addColumn(dataColumn, "Data");
		caronasCellTable.setColumnWidth(dataColumn, "100%");

		caronasCellTable.addColumn(horaColumn, "Hora-Saída");
		caronasCellTable.setColumnWidth(horaColumn, "100%");

		caronasCellTable.addColumn(vagasColumn, "Vagas");
		caronasCellTable.setColumnWidth(vagasColumn, "100%");

		caronasCellTable.addColumn(reviewColumn, "Review");
		caronasCellTable.setColumnWidth(reviewColumn, "100%");

		//COLOCA caronasCellTable em flexTablePegas
		flexTablePegas.setWidget(0, 0, caronasCellTable);

	}

	private void processarTabOferecidas() {
		gerarListaDeCaronasOferecidas(idSessao);
		colocarColunasEmCaronasCellTableOferecidas();
	}

	private void processarTabPegas() {
		gerarListaDeCaronasPegas(idSessao);
		colocarColunasEmCaronasCellTablePegas();
	}

	private void processarTabSolicitadas() {
		gerarListaDeCaronasSolicitadas(idSessao);
//		colocarColunasEmCaronasCellTableSolicitadas();
	}
}