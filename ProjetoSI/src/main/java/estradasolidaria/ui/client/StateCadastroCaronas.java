package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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
		absPanelCadastroCarona.setSize("494px", "334px");
		
		Label lblCadastreUmaN = new Label("Cadastre uma nova carona");
		lblCadastreUmaN.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblCadastreUmaN.setStyleName("gwt-LabelEstradaSolidaria9");
		absPanelCadastroCarona.add(lblCadastreUmaN, 83, 34);
		lblCadastreUmaN.setSize("209px", "16px");
		
		FlexTable flexTable = new FlexTable();
		absPanelCadastroCarona.add(flexTable, 84, 73);
		flexTable.setSize("235px", "174px");
		
		Label lblOrigem = new Label("Origem:");
		lblOrigem.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(0, 0, lblOrigem);
		
		final TextBox textBoxOrigem = new TextBox();
		flexTable.setWidget(0, 1, textBoxOrigem);
		
		Label lblDestino = new Label("Destino:");
		lblDestino.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(1, 0, lblDestino);
		
		final TextBox textBoxDestino = new TextBox();
		flexTable.setWidget(1, 1, textBoxDestino);
		
		Label lblHora = new Label("Hora:");
		lblHora.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(2, 0, lblHora);
		
		final TextBox textBoxHora = new TextBox();
		flexTable.setWidget(2, 1, textBoxHora);
		
		Label lblData = new Label("Data:");
		lblData.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(3, 0, lblData);
		
		final DateBox dateBox = new DateBox();
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		flexTable.setWidget(3, 1, dateBox);
		
		Label lblVagas = new Label("Vagas:");
		lblVagas.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(4, 0, lblVagas);
		
		final TextBox textBoxVagas = new TextBox();
		flexTable.setWidget(4, 1, textBoxVagas);
		
		TextButton btnEnviar = new TextButton("Enviar");
		absPanelCadastroCarona.add(btnEnviar, 234, 284);
		btnEnviar.setSize("77px", "40px");
		
		lblMensagemDeErro = new Label("Campo(s) origatório(s)");
		lblMensagemDeErro.setStyleName("gwt-LabelEstradaSolidaria5");
		absPanelCadastroCarona.add(lblMensagemDeErro, 197, 253);
		lblMensagemDeErro.setVisible(false);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		absPanelCadastroCarona.add(verticalPanel, 325, 73);
		verticalPanel.setSize("35px", "174px");
		
		lblErroorige = new Label("*");
		lblErroorige.setStyleName("gwt-LabelEstradaSolidaria6");
		verticalPanel.add(lblErroorige);
		lblErroorige.setVisible(false);
		
		lblErrodestino = new Label("*");
		lblErrodestino.setStyleName("gwt-LabelEstradaSolidaria6");
		verticalPanel.add(lblErrodestino);
		lblErrodestino.setWidth("19px");
		lblErrodestino.setVisible(false);
		
		lblErrohora = new Label("*");
		lblErrohora.setStyleName("gwt-LabelEstradaSolidaria6");
		verticalPanel.add(lblErrohora);
		lblErrohora.setVisible(false);
		
		lblErrodata = new Label("*");
		lblErrodata.setStyleName("gwt-LabelEstradaSolidaria6");
		verticalPanel.add(lblErrodata);
		lblErrodata.setVisible(false);
		
		lblErrovagas = new Label("*");
		lblErrovagas.setStyleName("gwt-LabelEstradaSolidaria6");
		verticalPanel.add(lblErrovagas);
		lblErrovagas.setVisible(false);
		
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
				Window.alert("Remote Procedure Call - Failure: " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("Carona cadastrada com sucesso.");
				
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
