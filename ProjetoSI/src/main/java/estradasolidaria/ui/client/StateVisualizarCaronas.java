package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.SimpleRadioButton;
import com.google.gwt.user.client.ui.FlexTable;

public class StateVisualizarCaronas extends Composite {

	public StateVisualizarCaronas() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("636px", "479px");
		
		ListBox comboBox = new ListBox();
		absolutePanel.add(comboBox, 561, 10);
		
		Label lblTipo = new Label("Tipo:");
		absolutePanel.add(lblTipo, 513, 10);
		
		Label lblVisualizarCaronas = new Label("Visualizar Caronas");
		absolutePanel.add(lblVisualizarCaronas, 64, 35);
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 20, 80);
		absolutePanel_1.setSize("606px", "295px");
		
		CellTable<Object> cellTable = new CellTable<Object>();
		absolutePanel_1.add(cellTable, 95, 74);
		cellTable.setSize("501px", "192px");
		
		Button btnAdd = new Button("Add");
		absolutePanel_1.add(btnAdd, 494, 31);
		
		Button btnExcluir = new Button("Excluir");
		absolutePanel_1.add(btnExcluir, 545, 31);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel_1.add(flexTable, 27, 60);
		flexTable.setSize("62px", "206px");
		
		SimpleRadioButton simpleRadioButton = new SimpleRadioButton("new name");
		flexTable.setWidget(1, 0, simpleRadioButton);
		simpleRadioButton.setSize("13px", "13px");
		
		SimpleRadioButton simpleRadioButton_1 = new SimpleRadioButton("new name");
		flexTable.setWidget(2, 0, simpleRadioButton_1);
		
		SimpleRadioButton simpleRadioButton_2 = new SimpleRadioButton("new name");
		flexTable.setWidget(3, 0, simpleRadioButton_2);
		
		SimpleRadioButton simpleRadioButton_3 = new SimpleRadioButton("new name");
		flexTable.setWidget(4, 0, simpleRadioButton_3);
		
		SimpleRadioButton simpleRadioButton_4 = new SimpleRadioButton("new name");
		flexTable.setWidget(5, 0, simpleRadioButton_4);
		
		Label lblObsNumeroDe = new Label("Obs: numero de SimpleBox fixos ou fazer com que apareçam a medida que a tabela cresça, mais facil selecionando uma linha da tabela do que adicinando chechBoxes");
		absolutePanel.add(lblObsNumeroDe, 136, 413);
	}
}
