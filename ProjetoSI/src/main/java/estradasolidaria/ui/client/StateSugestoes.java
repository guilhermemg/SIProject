package estradasolidaria.ui.client;

import java.util.LinkedList;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TabPanel;

public class StateSugestoes extends AbsolutePanel {
		final EstradaSolidaria estrada;
		final Widget panel = this;
		private EstradaSolidariaServiceAsync estradaSolidariaService;

		private CellTable<GWTSugestao> sugestoesCellTable;

		private LinkedList<GWTSugestao> sugestoesGWT; // Lista de Sugestoes transformadas para a classe GWTSugestoes
		private Integer idSessao;
													
		public StateSugestoes(EstradaSolidaria estrada,
				EstradaSolidariaServiceAsync estradaSolidariaService) {
			this.estrada = estrada;
			this.estradaSolidariaService = estradaSolidariaService;
			
			TabPanel tabPanel = new TabPanel();
			add(tabPanel, 0, 0);
			tabPanel.setSize("450px", "300px");
			idSessao = EstradaSolidaria.getIdSessaoAberta();
			//TODO terminar StateSugestoes
		}
}
