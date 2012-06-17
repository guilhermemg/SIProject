package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.widget.client.TextButton;

public class PopUpEditarReview extends PopupPanel {
//	private Image image;
	private ListBox comboBoxCaroneiros;
	private ListBox comboBoxTiposDeReview;
	private TextButton txtbtnOk;
	private TextButton txtbtnCancelar;
	private final EstradaSolidariaServiceAsync estradaService;
	private final EstradaSolidaria estrada;
	private final boolean isDono;

	public PopUpEditarReview(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaService, boolean isDono, String idCarona) {
		super(true);
		this.estrada = estrada;
		this.estradaService = estradaService;
		this.isDono = isDono;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("348px", "278px");
		
//		image = new Image("gwt/standard/images/splitPanelThumb.png");
//		image.setStyleName("PopupEditarReviewImage");
//		absolutePanel.add(image, 88, 26);
//		image.setSize("172px", "139px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 22, 188);
		flexTable.setSize("172px", "80px");
		
		Label lblNewLabel = new Label("Usuário:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		comboBoxCaroneiros = new ListBox();
		flexTable.setWidget(0, 1, comboBoxCaroneiros);
		comboBoxCaroneiros.setSize("100%", "100%");
		
		Label lblReview = new Label("Review:");
		flexTable.setWidget(1, 0, lblReview);
		
		comboBoxTiposDeReview = new ListBox();
		flexTable.setWidget(1, 1, comboBoxTiposDeReview);
		if (isDono) {
			comboBoxTiposDeReview.addItem("Não Faltou");
			comboBoxTiposDeReview.addItem("Faltou");
		} else {
			comboBoxTiposDeReview.addItem("Segura e tranquila");
			comboBoxTiposDeReview.addItem("Não funcionou");
		}
		
		comboBoxTiposDeReview.setSize("100%", "100%");
		
		txtbtnOk = new TextButton("OK");
		absolutePanel.add(txtbtnOk, 214, 239);
		
		txtbtnCancelar = new TextButton("Cancelar");
		absolutePanel.add(txtbtnCancelar, 262, 239);
	}
}
