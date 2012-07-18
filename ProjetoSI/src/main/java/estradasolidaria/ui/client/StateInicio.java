package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.RadioButton;

public class StateInicio extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Integer idSessao;
	private DataGrid<GWTMensagem> dataGrid;
	private SingleSelectionModel<GWTMensagem> selectionModel;
	private Column<GWTMensagem, Boolean> checkColumn;
	private Label labelEnviarConvite;
	private AbsolutePanel bodyPanel;
	private ScrollPanel scrollPanel;
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public StateInicio(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaService, AbsolutePanel body, ScrollPanel scroll) {
		this.estrada = entryPoint;
		this.estradaSolidariaService = estradaService;
		bodyPanel = body;
		scrollPanel = scroll;
		idSessao = estrada.getIdSessaoAberta();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("901px", "508px");
		
		dataGrid = new DataGrid<GWTMensagem>();
		absolutePanel.add(dataGrid, 10, 147);
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
		dataGrid.addColumn(columnMensagemStatus, "Status");
		
		Column<GWTMensagem, String> column = new Column<GWTMensagem, String>(new ButtonCell()) {
			@Override
			public String getValue(GWTMensagem object) {
				return "Abrir";
			}
		};
		column.setFieldUpdater(new FieldUpdater<GWTMensagem, String>() {
			@Override
			public void update(int index, GWTMensagem object, String value) {
				marcarMensagemLidaGUI(object.getIdMensagem());
				getListaDeMensagemsGUI();
				DialogBox dialogBox = new DialogBoxVerMensagem(object);
				dialogBox.center();
				dialogBox.show();
			}
		});
		dataGrid.addColumn(column, "");
		
		MenuBar menuBar = new MenuBar(false);
		absolutePanel.add(menuBar, 814, 119);
		MenuBar menuBar_1 = new MenuBar(true);
		
		MenuItem mntmOpes = new MenuItem("Opções:", false, menuBar_1);
		
		MenuItem mntmMarcarComoLida = new MenuItem("Marcar como lida", false, new Command() {
			public void execute() {
				Integer idMensagem = selectionModel.getSelectedObject().getIdMensagem();
				marcarMensagemLidaGUI(idMensagem);
				getListaDeMensagemsGUI();
			}
		});
		menuBar_1.addItem(mntmMarcarComoLida);
		
		MenuItem mntmApagar = new MenuItem("Apagar", false, new Command() {
			public void execute() {
				Integer idMensagem = selectionModel.getSelectedObject().getIdMensagem();
				apagarMensagemGUI(idMensagem);
				getListaDeMensagemsGUI();
			}
		});
		menuBar_1.addItem(mntmApagar);
		menuBar.addItem(mntmOpes);
		
		DisclosurePanel disclosurePanel = new DisclosurePanel("Convide um amigo!", false);
		disclosurePanel.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(disclosurePanel, 10, 22);
		disclosurePanel.setSize("250px", "66px");
		
		FlexTable flexTable = new FlexTable();
		disclosurePanel.setContent(flexTable);
		flexTable.setSize("93px", "39px");
		
		Label label = new Label("Email:");
		label.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(0, 0, label);
		
		final TextBox textBoxEnviarConvite = new TextBox();
		textBoxEnviarConvite.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				labelEnviarConvite.setVisible(false);
			}
		});
		flexTable.setWidget(0, 1, textBoxEnviarConvite);
		
		Button button = new Button("Enviar convite");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(textBoxEnviarConvite.getText().equals("")){
					labelEnviarConvite.setText("Email inválido");
					labelEnviarConvite.setStyleName("gwt-LabelEstradaSolidaria5");
					labelEnviarConvite.setVisible(true);
				} else {
					enviarConviteGUI(textBoxEnviarConvite.getText());
				}
			}
		});
		flexTable.setWidget(0, 2, button);
		button.setWidth("102px");
		
		labelEnviarConvite = new Label("");
		labelEnviarConvite.setVisible(false);
		flexTable.setWidget(1, 2, labelEnviarConvite);
		
		Label lblMensagens = new Label("Mensagens:");
		lblMensagens.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblMensagens, 24, 125);
		
		DisclosurePanel disclosurePanel_1 = new DisclosurePanel("Visualizar ranking de usuários", false);
		disclosurePanel_1.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(disclosurePanel_1, 378, 22);
		disclosurePanel_1.setSize("250px", "102px");
		
		FlexTable flexTable_1 = new FlexTable();
		disclosurePanel_1.setContent(flexTable_1);
		flexTable_1.setSize("5cm", "40px");
		
		final RadioButton rdbtnOrdemCrescente = new RadioButton("new name", "Ordem crescente");
		flexTable_1.setWidget(0, 1, rdbtnOrdemCrescente);
		
		final RadioButton rdbtnOrdemDecrescente = new RadioButton("new name", "Ordem decrescente");
		flexTable_1.setWidget(1, 1, rdbtnOrdemDecrescente);
		
		Button btnVer = new Button("Ver");
		btnVer.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(rdbtnOrdemCrescente.getValue()){
					getRankingusuariosGUI("crescente");
				} else if (rdbtnOrdemDecrescente.getValue()){
					getRankingusuariosGUI("decrescente");
				}
			}
		});
		flexTable_1.setWidget(2, 1, btnVer);

		getListaDeMensagemsGUI();
	}

	protected void getRankingusuariosGUI(final String ordem) {
		estradaSolidariaService.getRankingUsuarios(ordem, new AsyncCallback<List<GWTUsuario>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<GWTUsuario> result) {
				bodyPanel.clear();
				scrollPanel.clear();
				StateVisualizarRanking state = new StateVisualizarRanking(result, ordem);
				bodyPanel.add(state);
				bodyPanel.setVisible(true);
				state.setSize("100%", "100%");
				
			}
		});
	}

	protected void enviarConviteGUI(String emailDoAmigo) {
		estradaSolidariaService.convidarAmigo(idSessao, emailDoAmigo, new AsyncCallback<Boolean>(){
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				labelEnviarConvite.setText(caught.getMessage());
				labelEnviarConvite.setStyleName("gwt-LabelEstradaSolidaria5");
				labelEnviarConvite.setVisible(true);
			}

			@Override
			public void onSuccess(Boolean result) {
				labelEnviarConvite.setText("Convite enviado!");
				labelEnviarConvite.setStyleName("gwt-LabelEstradaSolidaria10");
				labelEnviarConvite.setVisible(true);
			}
			
		});
	}

	protected void apagarMensagemGUI(Integer idMensagem) {
		estradaSolidariaService.apagarMensagem(idSessao, idMensagem, new AsyncCallback<Void>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("Erro ao apagar mensagem: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				// does nothing
			}
		});
		
	}

	protected void marcarMensagemLidaGUI(Integer idMensagem) {
		estradaSolidariaService.marcarMensagemComoLida(idSessao, idMensagem, new AsyncCallback<Void>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				// does nothing
			}
		});
		
	}

	protected void getListaDeMensagemsGUI() {
		estradaSolidariaService.getListaDeMensagens(idSessao, new AsyncCallback<List<GWTMensagem>>() { 
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<GWTMensagem> result) {
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(result);
			}
		});
	}
}
