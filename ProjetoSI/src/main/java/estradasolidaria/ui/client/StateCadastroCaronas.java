package estradasolidaria.ui.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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

	public StateCadastroCaronas(EstradaSolidaria estradaSolidaria, EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estradaSolidaria;
		this.estradaSolidariaService = estradaSolidariaService;
		
		
		AbsolutePanel absPanelCadastroCarona = new AbsolutePanel();
		initWidget(absPanelCadastroCarona);
		absPanelCadastroCarona.setSize("413px", "334px");
		
		Label lblCadastreUmaN = new Label("Cadastre uma nova carona");
		lblCadastreUmaN.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblCadastreUmaN.setStyleName("gwt-LabelEstradaSolidaria9");
		absPanelCadastroCarona.add(lblCadastreUmaN, 83, 34);
		lblCadastreUmaN.setSize("209px", "16px");
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		
		TextButton btnEnviar = new TextButton("Enviar");

		absPanelCadastroCarona.add(btnEnviar, 215, 275);
		btnEnviar.setSize("77px", "40px");
		
		lblMensagemDeErro = new Label("Campo(s) origatório(s)");
		lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
		absPanelCadastroCarona.add(lblMensagemDeErro, 164, 253);
		lblMensagemDeErro.setVisible(false);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absPanelCadastroCarona.add(absolutePanel, 83, 73);
		absolutePanel.setSize("242px", "174px");
		
		Label lblOrigem = new Label("Origem:");
		absolutePanel.add(lblOrigem, 2, 10);
		lblOrigem.setStyleName("gwt-LabelEstradaSolidaria4");
		
		Label lblDestino = new Label("Destino:");
		absolutePanel.add(lblDestino, 0, 43);
		lblDestino.setStyleName("gwt-LabelEstradaSolidaria4");
		
		Label lblHora = new Label("Hora:");
		absolutePanel.add(lblHora, 2, 77);
		lblHora.setStyleName("gwt-LabelEstradaSolidaria4");
		
		Label lblData = new Label("Data:");
		absolutePanel.add(lblData, 2, 109);
		lblData.setStyleName("gwt-LabelEstradaSolidaria4");
		
		Label lblVagas = new Label("Vagas:");
		absolutePanel.add(lblVagas, 2, 143);
		lblVagas.setStyleName("gwt-LabelEstradaSolidaria4");
		
		final TextBox textBoxOrigem = new TextBox();
		textBoxOrigem.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErroorige.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxOrigem, 52, -2);
		
		final TextBox textBoxDestino = new TextBox();
		textBoxDestino.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrodestino.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxDestino, 52, 32);
		
		final DateBox dateBox = new DateBox();
		dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				lblErrodata.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(dateBox, 52, 99);
		dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		
		final TextBox textBoxVagas = new TextBox();
		textBoxVagas.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrovagas.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxVagas, 52, 131);
		
		lblErroorige = new Label("*");
		absolutePanel.add(lblErroorige, 227, -2);
		lblErroorige.setStyleName("gwt-LabelEstradaSolidaria6");
		
		lblErrodestino = new Label("*");
		absolutePanel.add(lblErrodestino, 227, 32);
		lblErrodestino.setStyleName("gwt-LabelEstradaSolidaria6");
		lblErrodestino.setSize("9px", "16px");
		
		lblErrodata = new Label("*");
		absolutePanel.add(lblErrodata, 227, 99);
		lblErrodata.setSize("5px", "16px");
		lblErrodata.setStyleName("gwt-LabelEstradaSolidaria6");
		
		lblErrovagas = new Label("*");
		absolutePanel.add(lblErrovagas, 227, 131);
		lblErrovagas.setSize("5px", "16px");
		lblErrovagas.setStyleName("gwt-LabelEstradaSolidaria6");
		
		lblErrohora = new Label("*");
		absolutePanel.add(lblErrohora, 227, 64);
		lblErrohora.setSize("15px", "16px");
		lblErrohora.setStyleName("gwt-LabelEstradaSolidaria6");
		
		final ListBox comboBoxHora = new ListBox();
		comboBoxHora.addItem("00");
		comboBoxHora.addItem("01");
		comboBoxHora.addItem("02");
		comboBoxHora.addItem("03");
		comboBoxHora.addItem("04");
		comboBoxHora.addItem("05");
		comboBoxHora.addItem("06");
		comboBoxHora.addItem("07");
		comboBoxHora.addItem("08");
		comboBoxHora.addItem("09");
		comboBoxHora.addItem("10");
		comboBoxHora.addItem("11");
		comboBoxHora.addItem("12");
		comboBoxHora.addItem("13");
		comboBoxHora.addItem("14");
		comboBoxHora.addItem("15");
		comboBoxHora.addItem("16");
		comboBoxHora.addItem("17");
		comboBoxHora.addItem("18");
		comboBoxHora.addItem("19");
		comboBoxHora.addItem("20");
		comboBoxHora.addItem("21");
		comboBoxHora.addItem("22");
		comboBoxHora.addItem("23");
		comboBoxHora.setName("horas");
		absolutePanel.add(comboBoxHora, 62, 66);
		
		final ListBox comboBoxMinutos = new ListBox();
		comboBoxMinutos.addItem("00");
		comboBoxMinutos.addItem("01");
		comboBoxMinutos.addItem("02");
		comboBoxMinutos.addItem("03");
		comboBoxMinutos.addItem("04");
		comboBoxMinutos.addItem("05");
		comboBoxMinutos.addItem("06");
		comboBoxMinutos.addItem("07");
		comboBoxMinutos.addItem("08");
		comboBoxMinutos.addItem("09");
		comboBoxMinutos.addItem("10");
		comboBoxMinutos.addItem("11");
		comboBoxMinutos.addItem("12");
		comboBoxMinutos.addItem("13");
		comboBoxMinutos.addItem("14");
		comboBoxMinutos.addItem("15");
		comboBoxMinutos.addItem("16");
		comboBoxMinutos.addItem("17");
		comboBoxMinutos.addItem("18");
		comboBoxMinutos.addItem("19");
		comboBoxMinutos.addItem("20");
		comboBoxMinutos.addItem("21");
		comboBoxMinutos.addItem("22");
		comboBoxMinutos.addItem("23");
		comboBoxMinutos.addItem("24");
		comboBoxMinutos.addItem("25");
		comboBoxMinutos.addItem("26");
		comboBoxMinutos.addItem("27");
		comboBoxMinutos.addItem("28");
		comboBoxMinutos.addItem("29");
		comboBoxMinutos.addItem("30");
		comboBoxMinutos.addItem("31");
		comboBoxMinutos.addItem("32");
		comboBoxMinutos.addItem("33");
		comboBoxMinutos.addItem("34");
		comboBoxMinutos.addItem("35");
		comboBoxMinutos.addItem("36");
		comboBoxMinutos.addItem("37");
		comboBoxMinutos.addItem("38");
		comboBoxMinutos.addItem("39");
		comboBoxMinutos.addItem("40");
		comboBoxMinutos.addItem("41");
		comboBoxMinutos.addItem("42");
		comboBoxMinutos.addItem("43");
		comboBoxMinutos.addItem("44");
		comboBoxMinutos.addItem("45");
		comboBoxMinutos.addItem("46");
		comboBoxMinutos.addItem("47");
		comboBoxMinutos.addItem("48");
		comboBoxMinutos.addItem("49");
		comboBoxMinutos.addItem("50");
		comboBoxMinutos.addItem("51");
		comboBoxMinutos.addItem("52");
		comboBoxMinutos.addItem("53");
		comboBoxMinutos.addItem("54");
		comboBoxMinutos.addItem("55");
		comboBoxMinutos.addItem("56");
		comboBoxMinutos.addItem("57");
		comboBoxMinutos.addItem("58");
		comboBoxMinutos.addItem("59");
		comboBoxMinutos.addItem("");
		comboBoxMinutos.addItem("");
		comboBoxMinutos.setName("minutos");
		absolutePanel.add(comboBoxMinutos, 124, 67);
		lblErrohora.setVisible(false);
		lblErrovagas.setVisible(false);
		lblErrodata.setVisible(false);
		lblErrodestino.setVisible(false);
		lblErroorige.setVisible(false);
		
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
