package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import estradasolidaria.ui.resources.Resources;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;

public class StateUsuariosEncontrados extends Composite {
	
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private List<GWTUsuario> listaUsuarios;

	public StateUsuariosEncontrados(EstradaSolidaria estrada, EstradaSolidariaServiceAsync estradaSolidariaService, List<GWTUsuario> result) {
		this.estrada = estrada;
		this.estradaService = estradaSolidariaService;
		this.listaUsuarios = result;

		Resources resources = GWT.create(Resources.class);
		ImageResource images = resources.getGenericLittleUserImage();
		final String imageHtml = AbstractImagePrototype.create(images).getHTML();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("533px", "390px");
		
	    ScrollPanel scrollPanel = new ScrollPanel();
	    absolutePanel.add(scrollPanel, 38, 120);
	    scrollPanel.setSize("362px", "246px");
	    
		Label lblUsuariosEncontrados = new Label("Usuários Encontrados:");
		lblUsuariosEncontrados.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblUsuariosEncontrados, 10, 10);

	    CellList<GWTUsuario> cellList = new CellList<GWTUsuario>(new AbstractCell<GWTUsuario>(){
	    	@Override
	    	public void render(Context context, GWTUsuario value, SafeHtmlBuilder sb) {
	    	    // Value can be null, so do a null check..
	    	    if (value == null) {
	    	      return;
	    	    }
	    	
	    	    sb.appendHtmlConstant("<table>");
	    	
	    	    // Add the contact image.
			    sb.appendHtmlConstant("<tr><td rowspan='3'>");
			    sb.appendHtmlConstant(imageHtml);
			    sb.appendHtmlConstant("</td>");
	    	
	    	    // Add the name and address.
	    	    sb.appendHtmlConstant("<td style='font-size:95%;'>");
	    	    sb.appendEscaped(value.getNome());
	    	    sb.appendHtmlConstant("</td></tr><tr><td>");
	    	    sb.appendEscaped(value.getEndereco());
	    	    sb.appendHtmlConstant("</td></tr></table>");
	    	}
	    });
	    
	    // Add a selection model so we can select cells.
	    final SingleSelectionModel<GWTUsuario> selectionModel = new SingleSelectionModel<GWTUsuario>();
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        GWTUsuario u = selectionModel.getSelectedObject();
	        Window.alert(u.toString());
	      }
	    });
	    
	    scrollPanel.setWidget(cellList);
	    scrollPanel.setAlwaysShowScrollBars(true);
	    cellList.setSize("100%", "100%");
	    cellList.setSelectionModel(selectionModel);
	    cellList.setRowCount(listaUsuarios.size(), true);
	    cellList.setRowData(listaUsuarios);
	    
	}
}