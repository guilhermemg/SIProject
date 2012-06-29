package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.widget.client.TextButton;

public class StateCaronasEncontradas extends Composite {
	private List<GWTCarona> listaDeCaronas;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private EstradaSolidaria entryPoint;
	private Integer idSessao;
	private Label lblMensagemdeerro;
	private DialogBox newDialog;
	
	private DataGrid<GWTCarona> dataGrid_1;
	private Column<GWTCarona, Boolean> checkBoxcolumn;
	private SelectionModel<GWTCarona> selectionModel;

	@SuppressWarnings("static-access")
	public StateCaronasEncontradas(final EstradaSolidariaServiceAsync estradaService, EstradaSolidaria estrada, List<GWTCarona> list) {
		this.estradaSolidariaService = estradaService;
		this.entryPoint = estrada;
		this.listaDeCaronas = list;
		
		this.idSessao  = entryPoint.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("659px", "491px");
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 80, 74);
		absolutePanel_1.setSize("477px", "96px");
		
		Label lblNewLabel = new Label("Sugira um local de encontro: (Opcional)");
		absolutePanel_1.add(lblNewLabel, 10, 10);
		
		final TextBox textBox = new TextBox();
		absolutePanel_1.add(textBox, 10, 32);
		textBox.setSize("217px", "16px");
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar Vaga em carona");
		absolutePanel_1.add(txtbtnRequisitarVaga, 267, 28);
		
		lblMensagemdeerro = new Label("MensagemDeErro");
		lblMensagemdeerro.setStyleName("gwt-LabelEstradaSolidaria5");
		lblMensagemdeerro.setVisible(false);
		absolutePanel_1.add(lblMensagemdeerro, 10, 70);
		
		dataGrid_1 = new DataGrid<GWTCarona>();
		absolutePanel.add(dataGrid_1, 78, 184);
		dataGrid_1.setSize("571px", "280px");
		
		selectionModel = new MultiSelectionModel<GWTCarona>();
		dataGrid_1.setSelectionModel(selectionModel);
			
	    Column<GWTCarona, Boolean> checkColumn =
		        new Column<GWTCarona, Boolean>(new CheckboxCell(true, false)) {
		          @Override
		          public Boolean getValue(GWTCarona object) {
		            // Get the value from the selection model.
		            return selectionModel.isSelected(object);
		          }
		        };
		dataGrid_1.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		dataGrid_1.setColumnWidth(checkColumn, 40, Unit.PX);
			    
		TextColumn<GWTCarona> columnOrigem = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.getOrigem();
			}
		};
		dataGrid_1.addColumn(columnOrigem, "Origem");
		
		TextColumn<GWTCarona> columnDestino = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.getDestino();
			}
		};
		dataGrid_1.addColumn(columnDestino, "Destino");
		
		TextColumn<GWTCarona> columnData = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.getData();
			}
		};
		dataGrid_1.addColumn(columnData, "Data");
		
		TextColumn<GWTCarona> columnHora = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.getHora();
			}
		};
		dataGrid_1.addColumn(columnHora, "Hora");
		
		TextColumn<GWTCarona> columnVagas = new TextColumn<GWTCarona>() {
			@Override
			public String getValue(GWTCarona carona) {
				return carona.getVagas();
			}
		};
		dataGrid_1.addColumn(columnVagas, "Vagas");
		
		dataGrid_1.setRowCount(listaDeCaronas.size(), true);
		dataGrid_1.setRowData(listaDeCaronas);
//		
		txtbtnRequisitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
					Window.alert("temporariamente fora de uso");
			}

		});
	}
	
	protected void solicitarVagaGUI(Integer idSessao, Integer idCarona) {
		estradaSolidariaService.solicitarVaga(idSessao, idCarona, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemdeerro.setText(caught.getMessage());
				lblMensagemdeerro.setVisible(true);
			}

			@Override
			public void onSuccess(String result) {
				newDialog.show();
			}
		  });	
	}
	
	protected void solicitarVagaComPontoDeEncontroGUI(Integer idSessao, Integer idCarona, String local) {
		estradaSolidariaService.solicitarVagaPontoEncontro(idSessao, idCarona, local, new AsyncCallback<String>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemdeerro.setText(caught.getMessage());
				lblMensagemdeerro.setVisible(true);
			}

			@Override
			public void onSuccess(String result) {
				newDialog.show();
			}
		  });	
	}
}
