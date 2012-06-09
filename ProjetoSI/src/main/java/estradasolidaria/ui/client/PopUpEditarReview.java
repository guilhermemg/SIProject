package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.widget.client.TextButton;

public class PopUpEditarReview extends PopupPanel {

	public PopUpEditarReview() {
		super(true);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("348px", "278px");
		
		Image image = new Image((String) null);
		absolutePanel.add(image, 88, 26);
		image.setSize("172px", "139px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 22, 188);
		flexTable.setSize("129px", "80px");
		
		Label lblNewLabel = new Label("Usuário:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		ListBox comboBox = new ListBox();
		flexTable.setWidget(0, 1, comboBox);
		
		Label lblReview = new Label("Review:");
		flexTable.setWidget(1, 0, lblReview);
		
		ListBox comboBox_1 = new ListBox();
		flexTable.setWidget(1, 1, comboBox_1);
		
		TextButton txtbtnOk = new TextButton("OK");
		absolutePanel.add(txtbtnOk, 288, 240);
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		absolutePanel.add(txtbtnCancelar, 255, 206);
	}
}
