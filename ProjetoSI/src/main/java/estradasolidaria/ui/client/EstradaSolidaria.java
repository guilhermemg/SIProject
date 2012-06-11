package estradasolidaria.ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EstradaSolidaria implements EntryPoint {
	private static Integer idSessaoAberta;
	Widget statepanel;
	final RootPanel rootPanel = RootPanel.get();
	
	private final EstradaSolidariaServiceAsync estradaSolidariaService = 
			(EstradaSolidariaServiceAsync) GWT.create(EstradaSolidariaService.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		rootPanel.setSize("1000px", "1000px");
		statepanel = new StateHomePage(this, estradaSolidariaService);
		rootPanel.add(statepanel, 68, 45);
		statepanel.setSize("1000px", "1000px");
	}

	public void setStatePanel(Widget statePanel) {
		this.statepanel = statePanel;
		rootPanel.add(statepanel);
		this.statepanel.setSize("1000px", "1000px");
	}

	public Integer getIdSessaoAberta() {
		return idSessaoAberta;
	}

	public static void setIdSessaoAberta(Integer id) {
		idSessaoAberta = id;
	}
}
