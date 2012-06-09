package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widget.client.TextButton;

public class PopUpAdicionarInteresse extends PopupPanel {

	public PopUpAdicionarInteresse() {
		super(true);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("317px", "308px");
		
		Label lblAdicionaRinteresse = new Label("Adicionar Interesse");
		absolutePanel.add(lblAdicionaRinteresse, 10, 10);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 10, 66);
		flexTable.setSize("282px", "100px");
		
		Label lblNewLabel = new Label("Origem:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		TextBox textBox = new TextBox();
		flexTable.setWidget(0, 1, textBox);
		
		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);
		
		TextBox textBox_1 = new TextBox();
		flexTable.setWidget(1, 1, textBox_1);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(2, 0, lblData);
		
		DateBox dateBox = new DateBox();
		flexTable.setWidget(2, 1, dateBox);
		
		Label lblHora = new Label("Hora início:");
		flexTable.setWidget(3, 0, lblHora);
		
		TextBox textBox_3 = new TextBox();
		flexTable.setWidget(3, 1, textBox_3);
		
		Label lblHoraFim = new Label("Hora fim:");
		flexTable.setWidget(4, 0, lblHoraFim);
		
		TextBox textBox_4 = new TextBox();
		flexTable.setWidget(4, 1, textBox_4);
		
		TextButton txtbtnAdicionar = new TextButton("Adicionar");
		absolutePanel.add(txtbtnAdicionar, 119, 258);
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		absolutePanel.add(txtbtnCancelar, 226, 258);
	}
}
