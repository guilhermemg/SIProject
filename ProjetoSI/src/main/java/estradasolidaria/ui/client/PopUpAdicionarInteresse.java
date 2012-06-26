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

public class PopUpAdicionarInteresse extends PopupPanel {

	private final EstradaSolidaria estrada;
	private final EstradaSolidariaServiceAsync estradaSolidariaService;
	private TextBox textBoxOrigem;
	private TextBox textBoxDestino;
	private DateBox dateBox;
	private TextBox textBoxHoraInicio;
	private TextBox textBoxHoraFim;

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
		absolutePanel.add(flexTable, 10, 66);
		flexTable.setSize("282px", "100px");
		
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
		
		textBoxHoraInicio = new TextBox();
		flexTable.setWidget(3, 1, textBoxHoraInicio);
		
		Label lblHoraFim = new Label("Hora fim:");
		flexTable.setWidget(4, 0, lblHoraFim);
		
		textBoxHoraFim = new TextBox();
		flexTable.setWidget(4, 1, textBoxHoraFim);
		
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
						horaInicio = textBoxHoraInicio.getText(),
						horaFim = textBoxHoraFim.getText();
		
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