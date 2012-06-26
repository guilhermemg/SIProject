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
		
		final TextBox textBoxHora = new TextBox();
		textBoxHora.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				lblErrohora.setVisible(false);
				lblMensagemDeErro.setVisible(false);
			}
		});
		absolutePanel.add(textBoxHora, 52, 65);
		
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
		lblErrohora.setVisible(false);
		lblErrovagas.setVisible(false);
		lblErrodata.setVisible(false);
		lblErrodestino.setVisible(false);
		lblErroorige.setVisible(false);
		
		btnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// verifica todos os campos
				if(textBoxOrigem.getText().length() == 0 || textBoxDestino.getText().length() == 0 
						|| textBoxHora.getText().length() == 0 ||  dateBox.getTextBox().getText().length() == 0 
						|| textBoxVagas.getText().length() == 0){
					if(textBoxOrigem.getText().length() == 0){
						lblErroorige.setVisible(true);
					}
					if(textBoxDestino.getText().length() == 0){
						lblErrodestino.setVisible(true);
					}
					if(textBoxHora.getText().length() == 0){
						lblErrohora.setVisible(true);
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
					cadastraCaronaGUI(textBoxOrigem, textBoxDestino, dateBox, textBoxHora, textBoxVagas);
					
					//LIMPA TEXTBOX's
					textBoxOrigem.setText("");
					textBoxDestino.setText("");
					textBoxHora.setText("");
					dateBox.setValue(null);
					textBoxVagas.setText("");
					
				}
				
				
			}
		});
		
		
	}
	private void cadastraCaronaGUI(TextBox textBoxOrigem, TextBox textBoxDestino, DateBox dateBox, TextBox textBoxHora, TextBox textBoxVagas) {
		Integer idSessao = EstradaSolidaria.getIdSessaoAberta();
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
												textBoxHora.getText(),
												textBoxVagas.getText(), 
												callback);
		
	}
}
