package estradasolidaria.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class Resources_default_StaticClientBundleGenerator implements estradasolidaria.ui.client.Resources {
  private static Resources_default_StaticClientBundleGenerator _instance0 = new Resources_default_StaticClientBundleGenerator();
  private void editarInitializer() {
    editar = new com.google.gwt.resources.client.impl.ImageResourcePrototype(
      "editar",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 181, 160, false, true
    );
  }
  private static class editarInitializer {
    static {
      _instance0.editarInitializer();
    }
    static com.google.gwt.resources.client.ImageResource get() {
      return editar;
    }
  }
  public com.google.gwt.resources.client.ImageResource editar() {
    return editarInitializer.get();
  }
  private void homeInitializer() {
    home = new com.google.gwt.resources.client.impl.ImageResourcePrototype(
      "home",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
      0, 0, 375, 215, false, true
    );
  }
  private static class homeInitializer {
    static {
      _instance0.homeInitializer();
    }
    static com.google.gwt.resources.client.ImageResource get() {
      return home;
    }
  }
  public com.google.gwt.resources.client.ImageResource home() {
    return homeInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static final java.lang.String externalImage = GWT.getModuleBaseURL() + "3A61F27D1E4FE502CFB530ED1C840D5D.cache.jpg";
  private static final java.lang.String externalImage0 = GWT.getModuleBaseURL() + "82BBECB59B77332A3F4D8FDDAB683F92.cache.jpg";
  private static com.google.gwt.resources.client.ImageResource editar;
  private static com.google.gwt.resources.client.ImageResource home;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      editar(), 
      home(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("editar", editar());
        resourceMap.put("home", home());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'editar': return this.@estradasolidaria.ui.client.Resources::editar()();
      case 'home': return this.@estradasolidaria.ui.client.Resources::home()();
    }
    return null;
  }-*/;
}
