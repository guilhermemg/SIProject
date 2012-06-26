package estradasolidaria.ui.client;

import java.util.LinkedList;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TabPanel;

public class StateSolicitacoes extends AbsolutePanel {
	final EstradaSolidaria estrada;
	final Widget panel = this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;

	private CellTable<GWTSolicitacao> solicitacoesCellTable;

	private LinkedList<GWTSolicitacao> solicitacoesGWT; // Lista de Solicitacoes transformadas para a classe GWTSolicitacao
	private Integer idSessao;
												
	public StateSolicitacoes(EstradaSolidaria estrada,
			EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		
		TabPanel tabPanel = new TabPanel();
		add(tabPanel, 0, 0);
		tabPanel.setSize("100%", "100%");
		idSessao = EstradaSolidaria.getIdSessaoAberta();
		//TODO terminar StateSolicitacoes
	}
}
