package estradasolidaria.ui.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;

import estradasolidaria.ui.server.logic.Carona;

public class StateVisualizarCaronas extends Composite {
	private Carona carona = new Carona(1, "Campina Grande", "João Pessoa", "12/12/2012", "08:30", 4, 1);
	public StateVisualizarCaronas() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");
		
		ListBox comboBox = new ListBox();
		absolutePanel.add(comboBox, 561, 10);
		
		Label lblTipo = new Label("Tipo:");
		absolutePanel.add(lblTipo, 513, 10);
		
		TabPanel tabPanel = new TabPanel();
		absolutePanel.add(tabPanel, 10, 10);
		tabPanel.setSize("780px", "600px");
		
		FlexTable flexTable = new FlexTable();
		tabPanel.add(flexTable, "Oferecidas", false);
		flexTable.setSize("100%", "100%");
		
		CellTable<Object> cellTable = new CellTable<Object>();
		flexTable.setWidget(0, 0, cellTable);
		
		Column<Object, Boolean> column = new Column<Object, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(Object object) {
				return ((CheckBox) object).getValue();
			}
		};
		cellTable.addColumn(column, SafeHtmlUtils.fromSafeConstant("<br/>"));
		
		TextColumn<Object> origem_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return object.toString();
			}
		};
		cellTable.addColumn(origem_textColumn, "Origem");
		origem_textColumn.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int arg0, Object arg1, String arg2) {
				
			}
		});
		
		origem_textColumn.getFieldUpdater().update(0, carona,  "");
		
		TextColumn<Object> destino_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return object.toString();
			}
		};
		cellTable.addColumn(destino_textColumn, "Destino");
		
		TextColumn<Object> data_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return object.toString();
			}
		};
		cellTable.addColumn(data_textColumn, "Data");
		
		TextColumn<Object> hora_saida_textColumn = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return object.toString();
			}
		};
		cellTable.addColumn(hora_saida_textColumn, "Hora:Saida");
		
		Column<Object, Number> vagas_column = new Column<Object, Number>(new NumberCell()) {
			@Override
			public Number getValue(Object object) {
				return ((Carona) object).getVagas();
			}
		};
		cellTable.addColumn(vagas_column, "Vagas");
		vagas_column.setFieldUpdater(new FieldUpdater<Object, Number>() {
			@Override
			public void update(int arg0, Object arg1, Number arg2) {
				
			}
		});
		
		Column<Object, String> column_2 = new Column<Object, String>(new ButtonCell()) {
			@Override
			public String getValue(Object object) {
				return (String) null;
			}
		};
		cellTable.addColumn(column_2, "Review");
		
		FlexTable flexTable_1 = new FlexTable();
		tabPanel.add(flexTable_1, "Pegas", false);
		flexTable_1.setSize("100%", "100%");
		
		FlexTable flexTable_2 = new FlexTable();
		tabPanel.add(flexTable_2, "Solicitadas", false);
		flexTable_2.setSize("100%", "100%");
	}
}
