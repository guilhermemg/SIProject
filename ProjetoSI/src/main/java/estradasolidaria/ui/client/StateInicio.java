package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.view.client.SingleSelectionModel;

public class StateInicio extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessao;
	private DataGrid<GWTMensagem> dataGrid;
	private SingleSelectionModel<GWTMensagem> selectionModel;
	private Column<GWTMensagem, Boolean> checkColumn;
	
	private List<GWTMensagem> lista;
	
	@SuppressWarnings("static-access")
	public StateInicio(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaService) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaService;
		idSessao = estrada.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("901px", "486px");
		
		dataGrid = new DataGrid<GWTMensagem>();
		absolutePanel.add(dataGrid, 10, 104);
		dataGrid.setSize("881px", "339px");
		
		selectionModel = new SingleSelectionModel<GWTMensagem>();
		dataGrid.setSelectionModel(selectionModel);
			
	    checkColumn =
		        new Column<GWTMensagem, Boolean>(new CheckboxCell(true, false)) {
		          @Override
		          public Boolean getValue(GWTMensagem object) {
		            // Get the value from the selection model.
		            return selectionModel.isSelected(object);
		          }
		        };
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);
			    
		TextColumn<GWTMensagem> columnRemetente = new TextColumn<GWTMensagem>() {
			@Override
			public String getValue(GWTMensagem mensagem) {
				return mensagem.getRemetente();
			}
		};
		dataGrid.addColumn(columnRemetente, "Remetente");
		
		TextColumn<GWTMensagem> columnTexto = new TextColumn<GWTMensagem>() {
			@Override
			public String getValue(GWTMensagem mensagem) {
				return mensagem.getTexto().substring(0, 20) + "...";
			}
		};
		dataGrid.addColumn(columnTexto, "Mensagem");
		
		TextColumn<GWTMensagem> columnMensagemStatus = new TextColumn<GWTMensagem>() {
			@Override
			public String getValue(GWTMensagem mensagem) {
				if(mensagem.isMensagemLida()){
					return "Lida";
				} else {
					return "Não lida";
				}
			}
		};
		dataGrid.addColumn(columnMensagemStatus, "");
		
		Column<GWTMensagem, String> column = new Column<GWTMensagem, String>(new ButtonCell()) {
			@Override
			public String getValue(GWTMensagem object) {
				return "Ver";
			}
		};
		column.setFieldUpdater(new FieldUpdater<GWTMensagem, String>() {
			@Override
			public void update(int index, GWTMensagem object, String value) {
				object.setMensagemLida(true);
				atualizarGrid();
				DialogBox dialogBox = new DialogBoxVerMensagem(object);
				dialogBox.center();
				dialogBox.show();
			}
		});
		dataGrid.addColumn(column, "");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 814, 10);
		MenuBar menuBar_1 = new MenuBar(true);
		
		MenuItem mntmOpes = new MenuItem("Opções:", false, menuBar_1);
		
		MenuItem mntmMarcarComoLida = new MenuItem("Marcar como lida", false, new Command() {
			public void execute() {
				selectionModel.getSelectedObject().setMensagemLida(true);
				atualizarGrid();
			}
		});
		menuBar_1.addItem(mntmMarcarComoLida);
		
		MenuItem mntmApagar = new MenuItem("Apagar", false, new Command() {
			public void execute() {
				lista.remove(selectionModel.getSelectedObject());
				atualizarGrid();
			}
		});
		menuBar_1.addItem(mntmApagar);
		menuBar.addItem(mntmOpes);

		getListaDeMensagemsGUI();
	}

	protected void atualizarGrid() {
		dataGrid.setRowCount(lista.size(), true);
		dataGrid.setRowData(lista);
	}

	private void getListaDeMensagemsGUI() {
		estradaSolidariaService.getListaDeMensagens(idSessao, new AsyncCallback<List<GWTMensagem>>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<GWTMensagem> result) {
				setListaDeMensagens(result);
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(result);
			}
		});
	}
	
	private void setListaDeMensagens(List<GWTMensagem> result) {
		this.lista = result;
	}
}
