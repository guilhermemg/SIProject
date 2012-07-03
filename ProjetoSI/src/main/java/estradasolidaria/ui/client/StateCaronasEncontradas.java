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
	private Integer idCarona;
	private Label lblMensagemdeerro;
	private DialogBox newDialog;
	private TextBox textBox;
	
	private DataGrid<GWTCarona> dataGrid_1;
	private SelectionModel<GWTCarona> selectionModel;
	private Column<GWTCarona, Boolean> checkColumn;

	@SuppressWarnings("static-access")
	public StateCaronasEncontradas(final EstradaSolidariaServiceAsync estradaService, EstradaSolidaria estrada, List<GWTCarona> list) {
		this.estradaSolidariaService = estradaService;
		this.entryPoint = estrada;
		this.listaDeCaronas = list;
		
		this.idSessao  = entryPoint.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("885px", "547px");
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 80, 64);
		absolutePanel_1.setSize("797px", "106px");
		
		Label lblNewLabel = new Label("Sugira um local de encontro: (Opcional)");
		lblNewLabel.setStyleName("gwt-LabelEstradaSolidaria8");
		absolutePanel_1.add(lblNewLabel, 47, 46);
		
		textBox = new TextBox();
		absolutePanel_1.add(textBox, 294, 46);
		textBox.setSize("260px", "16px");
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar ");
		absolutePanel_1.add(txtbtnRequisitarVaga, 579, 42);
		
		lblMensagemdeerro = new Label("MensagemDeErro");
		lblMensagemdeerro.setStyleName("gwt-LabelEstradaSolidaria5");
		lblMensagemdeerro.setVisible(false);
		absolutePanel_1.add(lblMensagemdeerro, 294, 76);
		
		Label lblNewLabel_1 = new Label("Requisitar Vaga em carona");
		lblNewLabel_1.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel_1.add(lblNewLabel_1, 47, 24);
		
		dataGrid_1 = new DataGrid<GWTCarona>();
		absolutePanel.add(dataGrid_1, 80, 176);
		dataGrid_1.setSize("797px", "257px");
		
		selectionModel = new MultiSelectionModel<GWTCarona>();
		dataGrid_1.setSelectionModel(selectionModel);
			
	    checkColumn =
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

		txtbtnRequisitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				GWTCarona selecionada = null;
				for(GWTCarona c : listaDeCaronas){
					if(checkColumn.getValue(c)){
						selecionada = c;
					}
				}
				idCarona = Integer.parseInt(selecionada.getIdCarona());
				newDialog = new DialogBoxNovaSolicitacao(estradaSolidariaService, idCarona, idSessao);
				newDialog.center();
				newDialog.hide();
				if(textBox.getText().length() == 0){
					solicitarVagaGUI(idSessao, idCarona);
				} else {
					solicitarVagaComPontoDeEncontroGUI(idSessao, idCarona, textBox.getText());
				}
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
				textBox.setText("");
				newDialog.show();
			}
		  });	
	}
}
