	package estradasolidaria.ui.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EstradaSolidaria implements EntryPoint {
	private static Integer idSessaoAberta;
	Widget statePanel;
	final RootPanel rootPanel = RootPanel.get();
	public static Integer alturaDoBrowser = Window.getClientHeight();
	public static Integer comprimentoDoBrowser = Window.getClientWidth();
	
	private final EstradaSolidariaServiceAsync estradaSolidariaService = 
			(EstradaSolidariaServiceAsync) GWT.create(EstradaSolidariaService.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		statePanel = new StateHomePage(this, estradaSolidariaService);
		statePanel.setSize(comprimentoDoBrowser + "px", alturaDoBrowser + "px");
		rootPanel.setSize(comprimentoDoBrowser +"px", alturaDoBrowser + "px");
		rootPanel.add(statePanel);
	}

	public void setStatePanel(Widget statePanel) {
		rootPanel.remove(this.statePanel);
		this.statePanel = statePanel;
		this.statePanel.setSize("100%", "100%");
		rootPanel.add(statePanel);
	}

	public static Integer getIdSessaoAberta() {
		return idSessaoAberta;
	}

	public static void setIdSessaoAberta(Integer id) {
		idSessaoAberta = id;
	}
}
