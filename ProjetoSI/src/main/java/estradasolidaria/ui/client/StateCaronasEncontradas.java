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
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.widget.client.TextButton;

public class StateCaronasEncontradas extends Composite {
	private List<GWTCarona> listaDeCaronas;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private EstradaSolidaria entryPoint;
	private Integer idSessao;
	private Label lblMensagemdeerro;
	private DialogBox newDialog;
	private TextBox textBox;
	private TextBox textBoxSolicitar;
	private Label lblMensagemDeErroSugerir;
	
	private DataGrid<GWTCarona> dataGrid_1;
	private SingleSelectionModel<GWTCarona> selectionModel;
	private Column<GWTCarona, Boolean> checkColumn;

	@SuppressWarnings("static-access")
	public StateCaronasEncontradas(final EstradaSolidariaServiceAsync estradaService, EstradaSolidaria estrada, List<GWTCarona> list) {
		this.estradaSolidariaService = estradaService;
		this.entryPoint = estrada;
		this.listaDeCaronas = list;
		
		this.idSessao  = entryPoint.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("885px", "481px");
		
		Label lblCaronasEncontradas = new Label("Caronas Encontradas:");
		lblCaronasEncontradas.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblCaronasEncontradas, 10, 24);
		
		dataGrid_1 = new DataGrid<GWTCarona>();
		absolutePanel.add(dataGrid_1, 78, 61);
		dataGrid_1.setSize("797px", "187px");
		
		selectionModel = new SingleSelectionModel<GWTCarona>();
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
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel, 78, 264);
		decoratedStackPanel.setSize("387px", "180px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		decoratedStackPanel.add(absolutePanel_1, "Requisitar vaga em carona", false);
		absolutePanel_1.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("Sugira um local de encontro: (Opcional)");
		lblNewLabel.setStyleName("gwt-LabelEstradaSolidaria8");
		absolutePanel_1.add(lblNewLabel, 10, 20);
		
		textBox = new TextBox();
		textBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblMensagemdeerro.setVisible(false);
			}
		});
		absolutePanel_1.add(textBox, 43, 42);
		textBox.setSize("325px", "16px");
		
		TextButton txtbtnRequisitarVaga = new TextButton("Requisitar ");
		absolutePanel_1.add(txtbtnRequisitarVaga, 296, 88);
		txtbtnRequisitarVaga.setSize("80px", "43px");
		
		lblMensagemdeerro = new Label("MensagemDeErro");
		lblMensagemdeerro.setStyleName("gwt-LabelEstradaSolidaria5");
		lblMensagemdeerro.setVisible(false);
		absolutePanel_1.add(lblMensagemdeerro, 43, 72);
		
		DecoratedStackPanel decoratedStackPanel_1 = new DecoratedStackPanel();
		absolutePanel.add(decoratedStackPanel_1, 488, 264);
		decoratedStackPanel_1.setSize("387px", "180px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		decoratedStackPanel_1.add(absolutePanel_2, "Sugerir ponto de encontro", false);
		absolutePanel_2.setSize("100%", "100%");
		
		Label lblLocal = new Label("Local:");
		absolutePanel_2.add(lblLocal, 10, 20);
		
		textBoxSolicitar = new TextBox();
		textBoxSolicitar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblMensagemDeErroSugerir.setVisible(false);
			}
		});
		absolutePanel_2.add(textBoxSolicitar, 32, 43);
		textBoxSolicitar.setSize("312px", "16px");
		
		lblMensagemDeErroSugerir = new Label("");
		absolutePanel_2.add(lblMensagemDeErroSugerir, 32, 73);
		lblMensagemDeErroSugerir.setStyleName("gwt-LabelEstradaSolidaria5");
		lblMensagemDeErroSugerir.setVisible(false);
		
		TextButton txtbtnEnviar = new TextButton("Enviar");
		txtbtnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(selectionModel.getSelectedObject() == null){
					lblMensagemDeErroSugerir.setText("Selecione uma carona na tabela");
					lblMensagemDeErroSugerir.setVisible(true);
				} else {
					if(textBoxSolicitar.getText().length() == 0){
						lblMensagemDeErroSugerir.setText("Ponto inválido");
						lblMensagemDeErroSugerir.setVisible(true);
					} else {
						Integer idCarona = Integer.parseInt(selectionModel.getSelectedObject().getIdCarona());
						sugerirPontoDeEncontroGUI(textBoxSolicitar.getText(), idCarona);
					}
				}
			}
		});
		absolutePanel_2.add(txtbtnEnviar, 295, 91);
		txtbtnEnviar.setSize("57px", "39px");
		
		txtbtnRequisitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				if(selectionModel.getSelectedObject() == null){
					lblMensagemdeerro.setText("Selecione uma carona na tabela");
					lblMensagemdeerro.setVisible(true);
				} else {
					Integer idCarona = Integer.parseInt(selectionModel.getSelectedObject().getIdCarona());
					newDialog = new DialogBoxNovaSolicitacao(estradaSolidariaService, idCarona, idSessao);
					newDialog.center();
					newDialog.hide();
					if(textBox.getText().length() == 0){
						solicitarVagaGUI(idSessao, idCarona);
					} else {
						solicitarVagaComPontoDeEncontroGUI(idSessao, idCarona, textBox.getText());
					}
				}
			}

		});
	}
	
	protected void sugerirPontoDeEncontroGUI(String ponto, Integer idCarona) {
		estradaSolidariaService.sugerirPontoEncontro(idSessao, idCarona, ponto, new AsyncCallback<Void>(){ 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user 
				lblMensagemDeErroSugerir.setText(caught.getMessage());
				lblMensagemDeErroSugerir.setVisible(true);
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Foi!");
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
