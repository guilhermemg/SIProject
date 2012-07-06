package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

public class StateInicio extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessao;
	private DataGrid<GWTMensagem> dataGrid;
	private SelectionModel<GWTMensagem> selectionModel;
	private Column<GWTMensagem, Boolean> checkColumn;
	
	@SuppressWarnings("static-access")
	public StateInicio(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaService) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaService;
		idSessao = estrada.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		
		Label lblMensagens = new Label("Mensagens:");
		absolutePanel.add(lblMensagens, 10, 10);
		
		dataGrid = new DataGrid<GWTMensagem>();
		absolutePanel.add(dataGrid, 10, 56);
		dataGrid.setSize("430px", "200px");
		
		selectionModel = new MultiSelectionModel<GWTMensagem>();
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
				return mensagem.getTexto();
			}
		};
		dataGrid.addColumn(columnTexto, "Mensagem");
		
		getListaDeMensagemsGUI();
//		GWTMensagem m = new GWTMensagem();
//		GWTMensagem n = new GWTMensagem();
//		m.setRemetente("Italo");
//		m.setTexto("A civilização minoica surgiu durante a Idade do Bronze Grega em Creta, a maior ilha do Mar Egeu, " +
//				"e floresceu aproximadamente entre os séculos XXX e XV a.C");
//		n.setRemetente("Cara Legal ;)");
//		n.setTexto("Sport Club Corinthians Paulista conquista a Copa Libertadores da América de 2012.");
//		List<GWTMensagem> lista = new LinkedList<GWTMensagem>();
//		lista.add(m);
//		lista.add(n);
//		dataGrid.setRowCount(lista.size(), true);
//		dataGrid.setRowData(lista);
		
	}

	private void getListaDeMensagemsGUI() {
		estradaSolidariaService.getListaDeMensagens(idSessao, new AsyncCallback<Queue<GWTMensagem>>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Queue<GWTMensagem> result) {
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData((List<? extends GWTMensagem>) result);
			}
		  });
		
	}
}
