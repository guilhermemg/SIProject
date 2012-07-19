package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.widget.client.TextButton;

public class StateMeusInteresses extends AbsolutePanel {
	private List<GWTInteresse> listaDeInteresses;

	@SuppressWarnings("unused")
	private EstradaSolidaria estrada;

	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private TextButton btnAdicionar;
	private DataGrid<GWTInteresse> dataGrid;

	private StateMeusInteresses referenciaThis;

	private Column<GWTInteresse, Boolean> checkBoxcolumn;

	private SelectionModel<GWTInteresse> selectionModel;

	private TextButton txtbtnDeletar;

	protected GWTInteresse interesseEscolhido;

	private AbsolutePanel absolutePanel_1;

	public StateMeusInteresses(final EstradaSolidaria estrada, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		referenciaThis = this;
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		setTitle("Interresses");
		listaDeInteresses = new LinkedList<GWTInteresse>();

		absolutePanel_1 = new AbsolutePanel();
		add(absolutePanel_1);
		absolutePanel_1.setSize("100%", "");

		btnAdicionar = new TextButton("Adicionar");
		btnAdicionar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupPanel p = new PopUpAdicionarInteresse(estrada, estradaSolidariaService, referenciaThis);
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
		selectionModel = new MultiSelectionModel<GWTInteresse>(
				new ProvidesKey<GWTInteresse>() {
					@Override
					public Object getKey(GWTInteresse item) {
						return item;
					}
				});

		dataGrid.setSelectionModel(selectionModel);

		checkBoxcolumn = new Column<GWTInteresse, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(GWTInteresse interesse) {
				return selectionModel.isSelected(interesse);
			}
		};
		checkBoxcolumn.setFieldUpdater(new FieldUpdater<GWTInteresse, Boolean>() {

			@Override
			public void update(int index, GWTInteresse interesse, Boolean value) {
				if (value) {
					interesseEscolhido = interesse;
				} else {
					interesseEscolhido = null;
				}
			}
		});
		checkBoxcolumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dataGrid.addColumn(checkBoxcolumn);
		dataGrid.setColumnWidth(checkBoxcolumn, "40px");

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

		txtbtnDeletar = new TextButton("Deletar");
		txtbtnDeletar.addClickHandler(new ClickHandler() {
			private DialogBoxInteresse dialogBox;

			public void onClick(ClickEvent event) {
				if (interesseEscolhido == null) {	
					PopupAlerta popup = new PopupAlerta("Escolha um interesse a ser removido!");
					popup.center();
					popup.show();
				} else {
					dialogBox = new DialogBoxInteresse();
					dialogBox.setText("Deseja deletar o interesse?");
					dialogBox.getLblNewLabel().setText(interesseEscolhido.toString());
					dialogBox.getBtnOk().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							deletarInteresse();
							dialogBox.hide();
						}
					});
					dialogBox.center();
					dialogBox.show();
				}
			}
		});
		absolutePanel_1.add(txtbtnDeletar, 88, 0);

		colocarInteressesNoGrid();
	}

	private void deletarInteresse() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaSolidariaService.deletarInteresse(idSessao, interesseEscolhido.getIdInteresse(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupAlerta popup = new PopupAlerta(caught.getMessage());
				popup.center();
				popup.show();

			}

			@Override
			public void onSuccess(Void result) {
				colocarInteressesNoGrid();
				PopupInteresseRemovido popup = new PopupInteresseRemovido();
				popup.center();
				popup.show();
			}
		});

	}

	protected void colocarInteressesNoGrid() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		estradaSolidariaService.getInteresses(idSessao, new AsyncCallback<List<GWTInteresse>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupAlerta popup = new PopupAlerta(caught.getMessage());
				popup.center();
				popup.show();
			}

			@Override
			public void onSuccess(List<GWTInteresse> result) {
				listaDeInteresses = result;
				dataGrid.setRowCount(listaDeInteresses.size(), true);
				dataGrid.setRowData(listaDeInteresses);

			}
		});

	}
}