package estradasolidaria.ui.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;

public class PopUpAdicionarInteresse extends PopupPanel {

	private final EstradaSolidaria estrada;
	private final EstradaSolidariaServiceAsync estradaSolidariaService;
	private TextBox textBoxOrigem;
	private TextBox textBoxDestino;
	private DateBox dateBox;
	private ListBox comboBoxHoraInicio;
	private ListBox comboBoxMinutosInicio;
	private ListBox comboBoxHoraFim;
	private ListBox comboBoxMinutosFim;

	public PopUpAdicionarInteresse(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService) {
		super(true);
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("317px", "308px");
		
		Label lblAdicionaRinteresse = new Label("Adicionar Interesse");
		absolutePanel.add(lblAdicionaRinteresse, 10, 10);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 10, 52);
		flexTable.setSize("297px", "200px");
		
		Label lblNewLabel = new Label("Origem:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		textBoxOrigem = new TextBox();
		flexTable.setWidget(0, 1, textBoxOrigem);
		
		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);
		
		textBoxDestino = new TextBox();
		flexTable.setWidget(1, 1, textBoxDestino);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(2, 0, lblData);
		
		dateBox = new DateBox();
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		flexTable.setWidget(2, 1, dateBox);
		
		Label lblHora = new Label("Hora início:");
		flexTable.setWidget(3, 0, lblHora);
		
		@SuppressWarnings("deprecation")
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		flexTable.setWidget(3, 1, horizontalSplitPanel);
		
		comboBoxHoraInicio = new ListBox();
		comboBoxHoraInicio.addItem("00");
		comboBoxHoraInicio.addItem("01");
		comboBoxHoraInicio.addItem("02");
		comboBoxHoraInicio.addItem("03");
		comboBoxHoraInicio.addItem("04");
		comboBoxHoraInicio.addItem("05");
		comboBoxHoraInicio.addItem("06");
		comboBoxHoraInicio.addItem("07");
		comboBoxHoraInicio.addItem("08");
		comboBoxHoraInicio.addItem("09");
		comboBoxHoraInicio.addItem("10");
		comboBoxHoraInicio.addItem("11");
		comboBoxHoraInicio.addItem("12");
		comboBoxHoraInicio.addItem("13");
		comboBoxHoraInicio.addItem("14");
		comboBoxHoraInicio.addItem("15");
		comboBoxHoraInicio.addItem("16");
		comboBoxHoraInicio.addItem("17");
		comboBoxHoraInicio.addItem("18");
		comboBoxHoraInicio.addItem("19");
		comboBoxHoraInicio.addItem("20");
		comboBoxHoraInicio.addItem("21");
		comboBoxHoraInicio.addItem("22");
		comboBoxHoraInicio.addItem("23");
		horizontalSplitPanel.setLeftWidget(comboBoxHoraInicio);
		comboBoxHoraInicio.setSize("100%", "100%");
		
		comboBoxMinutosInicio = new ListBox();
		comboBoxMinutosInicio.addItem("00");
		comboBoxMinutosInicio.addItem("01");
		comboBoxMinutosInicio.addItem("02");
		comboBoxMinutosInicio.addItem("03");
		comboBoxMinutosInicio.addItem("04");
		comboBoxMinutosInicio.addItem("05");
		comboBoxMinutosInicio.addItem("06");
		comboBoxMinutosInicio.addItem("07");
		comboBoxMinutosInicio.addItem("08");
		comboBoxMinutosInicio.addItem("09");
		comboBoxMinutosInicio.addItem("10");
		comboBoxMinutosInicio.addItem("11");
		comboBoxMinutosInicio.addItem("12");
		comboBoxMinutosInicio.addItem("13");
		comboBoxMinutosInicio.addItem("14");
		comboBoxMinutosInicio.addItem("15");
		comboBoxMinutosInicio.addItem("16");
		comboBoxMinutosInicio.addItem("17");
		comboBoxMinutosInicio.addItem("18");
		comboBoxMinutosInicio.addItem("19");
		comboBoxMinutosInicio.addItem("20");
		comboBoxMinutosInicio.addItem("21");
		comboBoxMinutosInicio.addItem("22");
		comboBoxMinutosInicio.addItem("23");
		comboBoxMinutosInicio.addItem("24");
		comboBoxMinutosInicio.addItem("25");
		comboBoxMinutosInicio.addItem("26");
		comboBoxMinutosInicio.addItem("27");
		comboBoxMinutosInicio.addItem("28");
		comboBoxMinutosInicio.addItem("29");
		comboBoxMinutosInicio.addItem("30");
		comboBoxMinutosInicio.addItem("31");
		comboBoxMinutosInicio.addItem("32");
		comboBoxMinutosInicio.addItem("33");
		comboBoxMinutosInicio.addItem("34");
		comboBoxMinutosInicio.addItem("35");
		comboBoxMinutosInicio.addItem("36");
		comboBoxMinutosInicio.addItem("37");
		comboBoxMinutosInicio.addItem("38");
		comboBoxMinutosInicio.addItem("39");
		comboBoxMinutosInicio.addItem("40");
		comboBoxMinutosInicio.addItem("41");
		comboBoxMinutosInicio.addItem("42");
		comboBoxMinutosInicio.addItem("43");
		comboBoxMinutosInicio.addItem("44");
		comboBoxMinutosInicio.addItem("45");
		comboBoxMinutosInicio.addItem("46");
		comboBoxMinutosInicio.addItem("47");
		comboBoxMinutosInicio.addItem("48");
		comboBoxMinutosInicio.addItem("49");
		comboBoxMinutosInicio.addItem("50");
		comboBoxMinutosInicio.addItem("51");
		comboBoxMinutosInicio.addItem("52");
		comboBoxMinutosInicio.addItem("53");
		comboBoxMinutosInicio.addItem("54");
		comboBoxMinutosInicio.addItem("55");
		comboBoxMinutosInicio.addItem("56");
		comboBoxMinutosInicio.addItem("57");
		comboBoxMinutosInicio.addItem("58");
		comboBoxMinutosInicio.addItem("59");
		horizontalSplitPanel.setRightWidget(comboBoxMinutosInicio);
		comboBoxMinutosInicio.setSize("100%", "100%");
		
		Label lblHoraFim = new Label("Hora fim:");
		flexTable.setWidget(4, 0, lblHoraFim);
		
		HorizontalSplitPanel horizontalSplitPanel_1 = new HorizontalSplitPanel();
		flexTable.setWidget(4, 1, horizontalSplitPanel_1);
		
		comboBoxHoraFim = new ListBox();
		comboBoxHoraFim.addItem("00");
		comboBoxHoraFim.addItem("01");
		comboBoxHoraFim.addItem("02");
		comboBoxHoraFim.addItem("03");
		comboBoxHoraFim.addItem("04");
		comboBoxHoraFim.addItem("05");
		comboBoxHoraFim.addItem("06");
		comboBoxHoraFim.addItem("07");
		comboBoxHoraFim.addItem("08");
		comboBoxHoraFim.addItem("09");
		comboBoxHoraFim.addItem("10");
		comboBoxHoraFim.addItem("11");
		comboBoxHoraFim.addItem("12");
		comboBoxHoraFim.addItem("13");
		comboBoxHoraFim.addItem("14");
		comboBoxHoraFim.addItem("15");
		comboBoxHoraFim.addItem("16");
		comboBoxHoraFim.addItem("17");
		comboBoxHoraFim.addItem("18");
		comboBoxHoraFim.addItem("19");
		comboBoxHoraFim.addItem("20");
		comboBoxHoraFim.addItem("21");
		comboBoxHoraFim.addItem("22");
		comboBoxHoraFim.addItem("23");
		horizontalSplitPanel_1.setLeftWidget(comboBoxHoraFim);
		comboBoxHoraFim.setSize("100%", "100%");
		
		comboBoxMinutosFim = new ListBox();
		comboBoxMinutosFim.addItem("00");
		comboBoxMinutosFim.addItem("01");
		comboBoxMinutosFim.addItem("02");
		comboBoxMinutosFim.addItem("03");
		comboBoxMinutosFim.addItem("04");
		comboBoxMinutosFim.addItem("05");
		comboBoxMinutosFim.addItem("06");
		comboBoxMinutosFim.addItem("07");
		comboBoxMinutosFim.addItem("08");
		comboBoxMinutosFim.addItem("09");
		comboBoxMinutosFim.addItem("10");
		comboBoxMinutosFim.addItem("11");
		comboBoxMinutosFim.addItem("12");
		comboBoxMinutosFim.addItem("13");
		comboBoxMinutosFim.addItem("14");
		comboBoxMinutosFim.addItem("15");
		comboBoxMinutosFim.addItem("16");
		comboBoxMinutosFim.addItem("17");
		comboBoxMinutosFim.addItem("18");
		comboBoxMinutosFim.addItem("19");
		comboBoxMinutosFim.addItem("20");
		comboBoxMinutosFim.addItem("21");
		comboBoxMinutosFim.addItem("22");
		comboBoxMinutosFim.addItem("23");
		comboBoxMinutosFim.addItem("24");
		comboBoxMinutosFim.addItem("25");
		comboBoxMinutosFim.addItem("26");
		comboBoxMinutosFim.addItem("27");
		comboBoxMinutosFim.addItem("28");
		comboBoxMinutosFim.addItem("29");
		comboBoxMinutosFim.addItem("30");
		comboBoxMinutosFim.addItem("31");
		comboBoxMinutosFim.addItem("32");
		comboBoxMinutosFim.addItem("33");
		comboBoxMinutosFim.addItem("34");
		comboBoxMinutosFim.addItem("35");
		comboBoxMinutosFim.addItem("36");
		comboBoxMinutosFim.addItem("37");
		comboBoxMinutosFim.addItem("38");
		comboBoxMinutosFim.addItem("39");
		comboBoxMinutosFim.addItem("40");
		comboBoxMinutosFim.addItem("41");
		comboBoxMinutosFim.addItem("42");
		comboBoxMinutosFim.addItem("43");
		comboBoxMinutosFim.addItem("44");
		comboBoxMinutosFim.addItem("45");
		comboBoxMinutosFim.addItem("46");
		comboBoxMinutosFim.addItem("47");
		comboBoxMinutosFim.addItem("48");
		comboBoxMinutosFim.addItem("49");
		comboBoxMinutosFim.addItem("50");
		comboBoxMinutosFim.addItem("51");
		comboBoxMinutosFim.addItem("52");
		comboBoxMinutosFim.addItem("53");
		comboBoxMinutosFim.addItem("54");
		comboBoxMinutosFim.addItem("55");
		comboBoxMinutosFim.addItem("56");
		comboBoxMinutosFim.addItem("57");
		comboBoxMinutosFim.addItem("58");
		comboBoxMinutosFim.addItem("59");
		horizontalSplitPanel_1.setRightWidget(comboBoxMinutosFim);
		comboBoxMinutosFim.setSize("100%", "100%");
		
		TextButton txtbtnAdicionar = new TextButton("Adicionar");
		txtbtnAdicionar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cadastrarInteresse();
			}
		});
		absolutePanel.add(txtbtnAdicionar, 119, 258);
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 226, 258);
	}

	private void cadastrarInteresse() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		String origem = textBoxOrigem.getText(),
						destino = textBoxDestino.getText(),
						data = dateBox.getTextBox().getText(),
						horaInicio = (comboBoxHoraInicio.getSelectedIndex() + ":") + (comboBoxMinutosInicio.getSelectedIndex() + ""),
						horaFim = (comboBoxHoraFim.getSelectedIndex() + ":") + (comboBoxMinutosFim.getSelectedIndex() + "");
		
		estradaSolidariaService.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Remote Procedure Call - Failure: "
						+ caught.getMessage());
				
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("Interresse cadastrado!");
			}
		});
	}
}