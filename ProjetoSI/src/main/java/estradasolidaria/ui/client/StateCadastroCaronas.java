package estradasolidaria.ui.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable;

public class StateCadastroCaronas extends Composite {

	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;
	private Label lblMensagemDeErro;
	private Label lblErroorige;
	private Label lblErrodestino;
	private Label lblErrohora;
	private Label lblErrodata;
	private Label lblErrovagas;
	private NumberFormat numberFormat;

	public StateCadastroCaronas(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		numberFormat = NumberFormat.getFormat("00");
		
		AbsolutePanel absPanelCadastroCarona = new AbsolutePanel();
		initWidget(absPanelCadastroCarona);
		absPanelCadastroCarona.setSize("322px", "277px");
		
		Label lblCadastreUmaN = new Label("Cadastre uma nova carona");
		lblCadastreUmaN.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblCadastreUmaN.setStyleName("gwt-LabelEstradaSolidaria9");
		absPanelCadastroCarona.add(lblCadastreUmaN, 38, 0);
		lblCadastreUmaN.setSize("209px", "16px");
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		
		TextButton btnEnviar = new TextButton("Enviar");

		absPanelCadastroCarona.add(btnEnviar, 204, 219);
		btnEnviar.setSize("77px", "40px");
		
		lblMensagemDeErro = new Label("Campo(s) origatório(s)");
		lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
		absPanelCadastroCarona.add(lblMensagemDeErro, 21, 219);
		lblMensagemDeErro.setVisible(false);
		
		FlexTable flexTable = new FlexTable();
		absPanelCadastroCarona.add(flexTable, 21, 22);
		flexTable.setSize("271px", "187px");
		
		Label lblOrigem = new Label("Origem:");
		flexTable.setWidget(0, 0, lblOrigem);
		lblOrigem.setStyleName("gwt-LabelEstradaSolidaria4");
		
		FlexTable flexTable_2 = new FlexTable();
		flexTable.setWidget(0, 1, flexTable_2);
		flexTable_2.setSize("100%", "100%");
		
		final TextBox textBoxOrigem = new TextBox();
		flexTable_2.setWidget(0, 0, textBoxOrigem);
		textBoxOrigem.setSize("184px", "100%");
		
		lblErroorige = new Label("*");
		flexTable_2.setWidget(0, 1, lblErroorige);
		lblErroorige.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErroorige.setVisible(false);
		textBoxOrigem.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroorige.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		
		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);
		lblDestino.setStyleName("gwt-LabelEstradaSolidaria4");
		
		FlexTable flexTable_3 = new FlexTable();
		flexTable.setWidget(1, 1, flexTable_3);
		flexTable_3.setSize("100%", "100%");
		
		final TextBox textBoxDestino = new TextBox();
		flexTable_3.setWidget(0, 0, textBoxDestino);
		textBoxDestino.setSize("184px", "100%");
		
		lblErrodestino = new Label("*");
		flexTable_3.setWidget(0, 1, lblErrodestino);
		lblErrodestino.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErrodestino.setSize("100%", "100%");
		lblErrodestino.setVisible(false);
		textBoxDestino.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrodestino.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		
		Label lblHora = new Label("Hora:");
		flexTable.setWidget(2, 0, lblHora);
		lblHora.setStyleName("gwt-LabelEstradaSolidaria4");
		
		FlexTable flexTable_1 = new FlexTable();
		flexTable.setWidget(2, 1, flexTable_1);
		flexTable_1.setSize("100%", "100%");
		
		final ListBox comboBoxHora = new ListBox();
		for (int i = 0; i <= 23; i++) {
			comboBoxHora.addItem(numberFormat.format(i));
		}
		flexTable_1.setWidget(0, 0, comboBoxHora);
		comboBoxHora.setSize("100%", "100%");
		comboBoxHora.setName("horas");
		
		final ListBox comboBoxMinutos = new ListBox();
		for (int i = 0; i <= 59; i++) {
			comboBoxMinutos.addItem(numberFormat.format(i));
		}
		
		Label lblH = new Label("h");
		flexTable_1.setWidget(0, 1, lblH);
		flexTable_1.setWidget(0, 2, comboBoxMinutos);
		comboBoxMinutos.setSize("100%", "100%");
		comboBoxMinutos.setName("minutos");
		
		Label lblMin = new Label("min");
		flexTable_1.setWidget(0, 3, lblMin);
		
		lblErrohora = new Label("*");
		flexTable_1.setWidget(0, 4, lblErrohora);
		lblErrohora.setSize("100%", "100%");
		lblErrohora.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErrohora.setVisible(false);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(3, 0, lblData);
		lblData.setSize("54px", "17px");
		lblData.setStyleName("gwt-LabelEstradaSolidaria4");
		
		FlexTable flexTable_4 = new FlexTable();
		flexTable.setWidget(3, 1, flexTable_4);
		flexTable_4.setSize("100%", "100%");
		
		final DateBox dateBox = new DateBox();
		flexTable_4.setWidget(0, 0, dateBox);
		dateBox.setSize("184px", "100%");
		dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				lblErrodata.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		
		lblErrodata = new Label("*");
		flexTable_4.setWidget(0, 1, lblErrodata);
		lblErrodata.setSize("100%", "100%");
		lblErrodata.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErrodata.setVisible(false);
		
		Label lblVagas = new Label("Vagas:");
		flexTable.setWidget(4, 0, lblVagas);
		lblVagas.setStyleName("gwt-LabelEstradaSolidaria4");
		
		FlexTable flexTable_5 = new FlexTable();
		flexTable.setWidget(4, 1, flexTable_5);
		flexTable_5.setSize("100%", "100%");
		
		final TextBox textBoxVagas = new TextBox();
		flexTable_5.setWidget(0, 0, textBoxVagas);
		textBoxVagas.setSize("184px", "100%");
		
		lblErrovagas = new Label("*");
		flexTable_5.setWidget(0, 1, lblErrovagas);
		lblErrovagas.setSize("5px", "16px");
		lblErrovagas.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErrovagas.setVisible(false);
		textBoxVagas.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrovagas.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		
		btnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				// verifica todos os campos
				if(textBoxOrigem.getText().length() == 0 || textBoxDestino.getText().length() == 0 
						|| dateBox.getTextBox().getText().length() == 0 
						|| textBoxVagas.getText().length() == 0){
					if(textBoxOrigem.getText().length() == 0){
						lblErroorige.setVisible(true);
					}
					if(textBoxDestino.getText().length() == 0){
						lblErrodestino.setVisible(true);
					}
					if(dateBox.getTextBox().getText().length() == 0){
						lblErrodata.setVisible(true);
					}
					if(textBoxVagas.getText().length() == 0){
						lblErrovagas.setVisible(true);
					}
					lblMensagemDeErro.setText("Campo(s) origatório(s)");
					lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
					lblMensagemDeErro.setVisible(true);
				} else {
					cadastraCaronaGUI(textBoxOrigem, textBoxDestino, dateBox, comboBoxHora, comboBoxMinutos, textBoxVagas);
					
					//LIMPA TEXTBOX's
					textBoxOrigem.setText("");
					textBoxDestino.setText("");
					dateBox.setValue(null);
					textBoxVagas.setText("");
				}
			}
		});
		
		
	}
	private void cadastraCaronaGUI(TextBox textBoxOrigem, TextBox textBoxDestino, DateBox dateBox, ListBox comboBoxHora, 
			ListBox comboBoxMinutos, TextBox textBoxVagas) {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
		String horaCarona = comboBoxHora.getSelectedIndex() + "";
		String minutosCarona = comboBoxMinutos.getSelectedIndex() + "";
		String horarioCarona = horaCarona + ":" + minutosCarona;
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				lblMensagemDeErro.setText(caught.getMessage());
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
				lblMensagemDeErro.setVisible(true);
			}

			@Override
			public void onSuccess(String result) {
				lblMensagemDeErro.setText("Carona cadastrada com sucesso.");
				lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria10");
				lblMensagemDeErro.setVisible(true);
			}
		};
		estradaSolidariaService.cadastrarCarona(idSessao, 
												textBoxOrigem.getText(), 
												textBoxDestino.getText(), 
												dateBox.getTextBox().getText(),
												horarioCarona,
												textBoxVagas.getText(), 
												callback);
		
	}
}
