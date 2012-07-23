package estradasolidaria.ui.client;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.widget.client.TextButton;

import estradasolidaria.ui.resources.Resources;

public class StateVisualizarAmigos extends Composite {
	private EstradaSolidaria estrada;
	private EstradaSolidariaServiceAsync estradaService;
	private List<GWTUsuario> listaUsuarios;
	private AbsolutePanel bodyPanel;
	private GWTUsuario u;
	private Label lblSelecioneUmItem;
	
	public StateVisualizarAmigos(EstradaSolidaria entryPoint, EstradaSolidariaServiceAsync estradaSolidariaService, List<GWTUsuario> result, AbsolutePanel panel) {
		
		this.estrada = entryPoint;
		this.estradaService = estradaSolidariaService;
		this.listaUsuarios = result;
		this.bodyPanel = panel;
		
		Resources resources = GWT.create(Resources.class);
		ImageResource images = resources.getGenericLittleUserImage();
		final String imageHtml = AbstractImagePrototype.create(images).getHTML();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("590px", "390px");
		
	    ScrollPanel scrollPanel = new ScrollPanel();
	    absolutePanel.add(scrollPanel, 38, 120);
	    scrollPanel.setSize("362px", "246px");
	    
		Label lblUsuariosEncontrados = new Label("Meus amigos:");
		lblUsuariosEncontrados.setStyleName("gwt-LabelEstradaSolidaria2");
		absolutePanel.add(lblUsuariosEncontrados, 10, 10);
		
	    Label label = new Label("Mostrando " + listaUsuarios.size() + " amigos.");
	    label.setStyleName("gwt-LabelEstradaSolidaria8");
	    absolutePanel.add(label, 38, 85);
	    
	    lblSelecioneUmItem = new Label("Selecione um item na lista.");
	    absolutePanel.add(lblSelecioneUmItem, 427, 154);
	    lblSelecioneUmItem.setStyleName("gwt-LabelEstradaSolidaria5");
	    lblSelecioneUmItem.setVisible(false);

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
	        	lblSelecioneUmItem.setVisible(false);
	        }
	      });
	    
	    scrollPanel.setWidget(cellList);
	    scrollPanel.setAlwaysShowScrollBars(true);
	    cellList.setSize("100%", "100%");
	    cellList.setSelectionModel(selectionModel);
	    cellList.setRowCount(listaUsuarios.size(), true);
	    cellList.setRowData(listaUsuarios);
	    
	    TextButton txtbtnNewButton = new TextButton("Visualizar perfil");
	    txtbtnNewButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		if(selectionModel.getSelectedObject() == null){
	    			lblSelecioneUmItem.setVisible(true);
	    		} else {
		    		u = selectionModel.getSelectedObject();
					bodyPanel.clear();
					Widget perfil = new StateVisualizarPerfilAlheio(estrada, estradaService, u);
					bodyPanel.add(perfil);
					bodyPanel.setVisible(true);
					perfil.setSize("100%", "100%");
	    		}
	    	}
	    });
	    absolutePanel.add(txtbtnNewButton, 427, 120);
	}

}
