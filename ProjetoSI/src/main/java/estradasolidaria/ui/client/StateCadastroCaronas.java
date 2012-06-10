package estradasolidaria.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class StateCadastroCaronas extends Composite {

	final EstradaSolidaria estrada;
	final Widget panel= this;
	private EstradaSolidariaServiceAsync estradaSolidariaService;

	@SuppressWarnings("deprecation")
	public StateCadastroCaronas(EstradaSolidaria estradaSolidaria, final EstradaSolidariaServiceAsync estradaSolidariaService) {
		this.estrada = estradaSolidaria;
		AbsolutePanel absPanelCadastroCarona = new AbsolutePanel();
		initWidget(absPanelCadastroCarona);
		
		Label lblCadastreUmaN = new Label("Cadastre uma nova carona");
		lblCadastreUmaN.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblCadastreUmaN.setStyleName("gwt-LabelEstradaSolidaria2");
		absPanelCadastroCarona.add(lblCadastreUmaN);
		
		FlexTable flexTable = new FlexTable();
		absPanelCadastroCarona.add(flexTable, 84, 73);
		flexTable.setSize("288px", "174px");
		
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
		dateBox.setFormat(new DefaultFormat(DateTimeFormat.getFullDateFormat()));
		flexTable.setWidget(3, 1, dateBox);
		
		Label lblVagas = new Label("Vagas:");
		lblVagas.setStyleName("gwt-LabelEstradaSolidaria4");
		flexTable.setWidget(4, 0, lblVagas);
		
		final TextBox textBoxVagas = new TextBox();
		flexTable.setWidget(4, 1, textBoxVagas);
		
		Button btnEnviar = new Button("Enviar");
		btnEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// verifica todos os campos
				if(textBoxOrigem.getText().length() == 0 || textBoxDestino.getText().length() == 0 
						|| textBoxHora.getText().length() == 0 ||  dateBox.getTextBox().getText().length() == 0){
					Window.alert("Todos os campos devem ser preenchidos corretamente.");
				} else {
					textBoxOrigem.setText("");
					textBoxDestino.setText("");
					textBoxHora.setText("");
					dateBox.setValue(null);
					textBoxVagas.setText("");
					
					Window.alert("Carona cadastrada com sucesso.");
				}
				
				
			}
		});
		flexTable.setWidget(5, 1, btnEnviar);
	}

}
