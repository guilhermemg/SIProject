package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class StateVisualizarRanking extends Composite {

	private List<GWTUsuario> listaUsuarios;
	private String ordem;
	
	public StateVisualizarRanking(List<GWTUsuario> usuarios, String string) {
		
		listaUsuarios = usuarios;
		ordem = string;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("644px", "319px");
		
		Label lblRankingDeUsuarios = new Label("Ranking de usuarios:");
		lblRankingDeUsuarios.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblRankingDeUsuarios, 10, 21);
		
		Label lblOrdem = new Label("Ordem: " + ordem + ".");
		lblOrdem.setStyleName("gwt-LabelEstradaSolidaria4");
		absolutePanel.add(lblOrdem, 31, 43);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		absolutePanel.add(scrollPanel, 31, 65);
		scrollPanel.setSize("603px", "227px");
		
		DataGrid<GWTUsuario> dataGrid = new DataGrid<GWTUsuario>();
		scrollPanel.setWidget(dataGrid);
		dataGrid.setSize("603px", "227px");
		
		TextColumn<GWTUsuario> columnNome = new TextColumn<GWTUsuario>() {
			public String getValue(GWTUsuario usuario) {
				return usuario.getNome();
			}
		};
		dataGrid.addColumn(columnNome, "Nome");
		
		dataGrid.setRowCount(listaUsuarios.size(), true);
		dataGrid.setRowData(listaUsuarios);
	}
}
