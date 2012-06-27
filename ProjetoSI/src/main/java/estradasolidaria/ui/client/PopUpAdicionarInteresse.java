package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import java.util.Date;
import com.google.gwt.event.logical.shared.ValueChangeEvent;

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
	private NumberFormat numberFormat;
	private final StateMeusInteresses meusInteressesPanel;
	private Label lblMensagemDeErro;

	public PopUpAdicionarInteresse(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService, StateMeusInteresses meusInteressesPanel) {
		super(true);
		this.estrada = estrada;
		this.estradaSolidariaService = estradaSolidariaService;
		this.meusInteressesPanel = meusInteressesPanel;
		numberFormat = NumberFormat.getFormat("00");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("317px", "337px");
		
		Label lblAdicionaRinteresse = new Label("Adicionar Interesse");
		absolutePanel.add(lblAdicionaRinteresse, 10, 10);
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 10, 52);
		flexTable.setSize("297px", "200px");
		
		Label lblNewLabel = new Label("Origem:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		textBoxOrigem = new TextBox();
		textBoxOrigem.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblMensagemDeErro.setVisible(false);
			}
		});
		flexTable.setWidget(0, 1, textBoxOrigem);
		
		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);
		
		textBoxDestino = new TextBox();
		textBoxDestino.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblMensagemDeErro.setVisible(false);
			}
		});
		flexTable.setWidget(1, 1, textBoxDestino);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(2, 0, lblData);
		
		dateBox = new DateBox();
		dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				lblMensagemDeErro.setVisible(false);
			}
		});
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		flexTable.setWidget(2, 1, dateBox);
		
		Label lblHora = new Label("Hora início:");
		flexTable.setWidget(3, 0, lblHora);
		
		FlexTable flexTable_1 = new FlexTable();
		flexTable.setWidget(3, 1, flexTable_1);
		flexTable_1.setSize("100%", "100%");
		
		comboBoxHoraInicio = new ListBox();
		for (int i = 0; i <= 23; i++) {
			comboBoxHoraInicio.addItem(numberFormat.format(i));
		}
		flexTable_1.setWidget(0, 0, comboBoxHoraInicio);
		comboBoxHoraInicio.setSize("100%", "100%");
		
		Label lblH = new Label("h");
		flexTable_1.setWidget(0, 1, lblH);
		
		comboBoxMinutosInicio = new ListBox();
		for (int i = 0; i <= 59; i++) {
			comboBoxMinutosInicio.addItem(numberFormat.format(i));
		}
		flexTable_1.setWidget(0, 2, comboBoxMinutosInicio);
		comboBoxMinutosInicio.setSize("100%", "100%");
		
		Label lblMin = new Label("min");
		flexTable_1.setWidget(0, 3, lblMin);
		
		Label lblHoraFim = new Label("Hora fim:");
		flexTable.setWidget(4, 0, lblHoraFim);
		
		FlexTable flexTable_2 = new FlexTable();
		flexTable.setWidget(4, 1, flexTable_2);
		flexTable_2.setSize("100%", "100%");
		
		comboBoxHoraFim = new ListBox();
		for (int i = 0; i <= 23; i++) {
			comboBoxHoraFim.addItem(numberFormat.format(i));
		}
		flexTable_2.setWidget(0, 0, comboBoxHoraFim);
		comboBoxHoraFim.setSize("100%", "100%");
		
		Label label = new Label("h");
		flexTable_2.setWidget(0, 1, label);
		
		comboBoxMinutosFim = new ListBox();
		for (int i = 0; i <= 59; i++) {
			comboBoxMinutosFim.addItem(numberFormat.format(i));
		}
		flexTable_2.setWidget(0, 2, comboBoxMinutosFim);
		comboBoxMinutosFim.setSize("100%", "100%");
		
		Label label_1 = new Label("min");
		flexTable_2.setWidget(0, 3, label_1);
		
		TextButton txtbtnAdicionar = new TextButton("Adicionar");
		txtbtnAdicionar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cadastrarInteresse();
			}
		});
		absolutePanel.add(txtbtnAdicionar, 124, 299);
		
		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		absolutePanel.add(txtbtnCancelar, 221, 299);
		
		lblMensagemDeErro = new Label("Mensagem de erro");
		absolutePanel.add(lblMensagemDeErro, 10, 272);
		lblMensagemDeErro.setVisible(false);
	}

	private void cadastrarInteresse() {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		
		String horaInicio = comboBoxHoraInicio.getItemText(comboBoxHoraInicio.getSelectedIndex()),
			   minutosInicio = comboBoxMinutosInicio.getItemText(comboBoxMinutosInicio.getSelectedIndex()),
			   horaFim = comboBoxHoraFim.getItemText(comboBoxHoraFim.getSelectedIndex()),
			   minutosFim = comboBoxMinutosFim.getItemText(comboBoxMinutosFim.getSelectedIndex());
		
		String origem = textBoxOrigem.getText(),
						destino = textBoxDestino.getText(),
						data = dateBox.getTextBox().getText(),
						horarioInicio = horaInicio  + ":" + minutosInicio,
						horarioFim = horaFim + ":" + minutosFim;
		
		estradaSolidariaService.cadastrarInteresse(idSessao, origem, destino, data, horarioInicio, horarioFim, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				lblMensagemDeErro.setText(caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
			}

			@Override
			public void onSuccess(String result) {
				meusInteressesPanel.colocarInteressesNoGrid();
				hide();
			}
		});
	}
}