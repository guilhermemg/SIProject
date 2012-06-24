package estradasolidaria.ui.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.widget.client.TextButton;

public class StateCadastrarInteresse extends Composite {
	public StateCadastrarInteresse() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("308px", "266px");
		
		Label lblNewLabel = new Label("Origem:");
		absolutePanel.add(lblNewLabel, 10, 10);
		
		Label lblDestino = new Label("Destino:");
		absolutePanel.add(lblDestino, 10, 33);
		
		Label lblData = new Label("Data:");
		absolutePanel.add(lblData, 10, 63);
		
		Label lblHoraincio = new Label("Hora-Início:");
		absolutePanel.add(lblHoraincio, 10, 95);
		
		Label lblHorafim = new Label("Hora-Fim:");
		absolutePanel.add(lblHorafim, 10, 133);
		
		TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 103, 10);
		
		TextBox textBox_1 = new TextBox();
		absolutePanel.add(textBox_1, 103, 36);
		
		DateBox dateBox = new DateBox();
		absolutePanel.add(dateBox, 103, 63);
		
		TextBox textBox_2 = new TextBox();
		absolutePanel.add(textBox_2, 103, 95);
		
		TextBox textBox_3 = new TextBox();
		absolutePanel.add(textBox_3, 103, 121);
		
		TextButton btnCadastrar = new TextButton("Cadastrar");
		absolutePanel.add(btnCadastrar, 10, 174);
		
		TextButton btnCancelar = new TextButton("Cancelar");
		absolutePanel.add(btnCancelar, 106, 174);
		// TODO Auto-generated constructor stub
	}
}
