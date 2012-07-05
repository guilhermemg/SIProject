package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PopupInfo extends PopupPanel{

	public PopupInfo(String mensagem) {
		
		FlexTable flexTable = new FlexTable();
		setWidget(flexTable);
		flexTable.setSize("184px", "80px");
		
		AbsolutePanel absPanel = new AbsolutePanel();
		absPanel.setSize("100%", "100%");
		
		Label lblNewLabel = new Label(mensagem);
		lblNewLabel.setSize("100%", "20px");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		absPanel.add(lblNewLabel);
		flexTable.setWidget(0, 0, absPanel);
		
		AbsolutePanel absPanel_1 = new AbsolutePanel();
		absPanel_1.setSize("100%", "100%");
		
		TextButton txtbtnNewButton = new TextButton("OK");
		txtbtnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		txtbtnNewButton.setSize("38px", "100%");
		absPanel_1.add(txtbtnNewButton, 70, 0);
		flexTable.setWidget(1, 0, absPanel_1);
		
	}
	
}
