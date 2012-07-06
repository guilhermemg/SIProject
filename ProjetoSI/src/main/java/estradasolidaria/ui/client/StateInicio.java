package estradasolidaria.ui.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	private GWTMensagem m;
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

//		getListaDeMensagemsGUI();
		GWTMensagem m = new GWTMensagem();
		GWTMensagem n = new GWTMensagem();
		GWTMensagem o = new GWTMensagem();
		GWTMensagem p = new GWTMensagem();
		m.setRemetente("Italo");
		m.setTexto("A civilização minoica surgiu durante a Idade do Bronze Grega em Creta, a maior ilha do Mar Egeu, " +
				"e floresceu aproximadamente entre os séculos XXX e XV a.C");
		m.setMensagemLida(false);
		o.setRemetente("Sheik");
		o.setTexto("O princípio da imparcialidade é um princípio adotado pela Wikipédia para abordar os assuntos tratados nos artigo");
		o.setMensagemLida(false);
		n.setRemetente("Cara Legal ;)");
		n.setTexto("Sport Club Corinthians Paulista conquista a Copa Libertadores da América de 2012.");
		n.setMensagemLida(false);
		p.setRemetente("Lola");
		p.setTexto("Armstrong nasceu numa família muito pobre. Passou a sua juventude na pobreza num " +
						"bairro de Nova Orleans, conhecido como 'as costas da cidade'. O seu pai, William " +
						"Armstrong, abandonou a família quando Louis ainda era criança e casou-se " +
						"com outra mulher. A sua mãe, Mary Albert Armstrong, deixou Louis com a sua tia, " +
						"o seu tio e a sua avó. Aos cinco anos ele voltou a viver com a sua mãe e via " +
						"o pai muito raramente. Ele esteve na Fisk School for Boys onde pela primeira " +
						"vez entrou em contacto com a música. Levou algum dinheiro para casa como " +
						"entrega-jornais e sapateiro ambulante. Contudo, isso não era suficiente para " +
						"manter a sua mãe longe da prostituição. Passou a entrar à socapa em bares de " +
						"música perto de sua casa para ouvir e ver os cantores.Conheceu dias muito difíceis, " +
						"e olhava para a sua juventude como o pior momento da sua vida e, por vezes, até " +
						"retirava inspiração dela: Every time I close my eyes blowing that trumpet of mine" +
						"I look right in the heart of good old New Orleans...It has given me something to live for" +
						"Todas as vezes que eu fecho os meus olhos tocando aquele meu trompete, eu olho logo no coração da boa " +
						"velha Nova Orleans... Ela deu-me algo pelo que viver.");
		p.setMensagemLida(false);
		lista = new LinkedList<GWTMensagem>();
		lista.add(m);
		lista.add(n);
		lista.add(p);
		lista.add(o);
		dataGrid.setRowCount(lista.size(), true);
		dataGrid.setRowData(lista);
		
	}

	protected void atualizarGrid() {
		dataGrid.setRowCount(lista.size(), true);
		dataGrid.setRowData(lista);
	}

	private void getListaDeMensagemsGUI() {
		estradaSolidariaService.getListaDeMensagens(idSessao, new AsyncCallback<Queue<GWTMensagem>>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(caught.getMessage());
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Queue<GWTMensagem> result) {
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData((List<? extends GWTMensagem>) result);
			}
		  });
		
	}
}
