package estradasolidaria.ui.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

import estradasolidaria.ui.server.logic.Carona;

public class StateVisualizarCaronas extends Composite {
	private List<Carona> caronas = new ArrayList<Carona>();
	
	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	
	public StateVisualizarCaronas(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("798px", "487px");
		
		TabPanel tabPanel = new TabPanel();
		absolutePanel.add(tabPanel, 10, 10);
		tabPanel.setSize("754px", "467px");
		
		FlexTable flexTable = new FlexTable();
		tabPanel.add(flexTable, "Oferecidas", false);
		flexTable.setSize("561px", "3cm");
		
		CellTable<Carona> caronas_cellTable = new CellTable<Carona>();
		flexTable.setWidget(0, 0, caronas_cellTable);
		caronas_cellTable.setWidth("526px");
		
		Column<Carona, Boolean> checkBox_column = new Column<Carona, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(Carona object) {
				return (Boolean) null;
			}
		};
		caronas_cellTable.addColumn(checkBox_column);
		
		TextColumn<Carona> origem_textColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getOrigem();
			}
		};
		caronas_cellTable.addColumn(origem_textColumn, "Origem");
		caronas_cellTable.setRowCount(caronas.size(), true);
	    caronas_cellTable.setRowData(0, caronas);
		
		TextColumn<Carona> destino_textColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.toString();
			}
		};
		caronas_cellTable.addColumn(destino_textColumn, "Destino");
		
		TextColumn<Carona> data_textColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.toString();
			}
		};
		caronas_cellTable.addColumn(data_textColumn, "Data");
		
		TextColumn<Carona> hora_textColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.toString();
			}
		};
		caronas_cellTable.addColumn(hora_textColumn, "Hora-Saida");
		
		Column<Carona, Number> vagas_column = new Column<Carona, Number>(new NumberCell()) {
			@Override
			public Number getValue(Carona object) {
				return (Number) null;
			}
		};
		caronas_cellTable.addColumn(vagas_column, "Vagas");
		
		Column<Carona, String> review_column = new Column<Carona, String>(new ButtonCell()) {
			@Override
			public String getValue(Carona object) {
				return (String) null;
			}
		};
		caronas_cellTable.addColumn(review_column, "Review");
		
		FlexTable flexTable_1 = new FlexTable();
		tabPanel.add(flexTable_1, "Pegas", false);
		flexTable_1.setSize("5cm", "3cm");
		
		FlexTable flexTable_2 = new FlexTable();
		tabPanel.add(flexTable_2, "Solicitadas", false);
		flexTable_2.setSize("5cm", "3cm");
		
		
	}
}
