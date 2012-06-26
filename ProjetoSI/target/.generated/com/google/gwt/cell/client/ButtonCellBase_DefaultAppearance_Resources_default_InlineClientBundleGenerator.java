package com.google.gwt.cell.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator implements com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Resources {
  private static ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator _instance0 = new ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator();
  private void buttonCellBaseBackgroundInitializer() {
    buttonCellBaseBackground = new com.google.gwt.resources.client.impl.ImageResourcePrototype(
      "buttonCellBaseBackground",
      com.google.gwt.safehtml.shared.UriUtils.fromTrustedString(com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ?externalImage_rtl : externalImage),
      0, 0, 32, 31, false, false
    );
  }
  private static class buttonCellBaseBackgroundInitializer {
    static {
      _instance0.buttonCellBaseBackgroundInitializer();
    }
    static com.google.gwt.resources.client.ImageResource get() {
      return buttonCellBaseBackground;
    }
  }
  public com.google.gwt.resources.client.ImageResource buttonCellBaseBackground() {
    return buttonCellBaseBackgroundInitializer.get();
  }
  private void buttonCellBaseStyleInitializer() {
    buttonCellBaseStyle = new com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "buttonCellBaseStyle";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? ((".GPBYFDEA{height:" + ((ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getHeight() + "px")  + ";overflow:" + ("hidden")  + ";background:" + ("url(\"" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getSafeUri().asString() + "\") -" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getLeft() + "px -" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getTop() + "px  repeat-x")  + ";height:" + ("auto")  + ";background-color:" + ("#f1f1f1")  + ";margin:" + ("0")  + ";padding:" + ("5px"+ " " +"7px")  + ";text-decoration:" + ("none")  + ";cursor:" + ("pointer")  + ";cursor:" + ("hand")  + ";font-size:") + (("small")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#bbb")  + ";border-bottom:" + ("1px"+ " " +"solid"+ " " +"#a0a0a0")  + ";border-radius:" + ("5px")  + ";-moz-border-radius:" + ("5px")  + ";}.GPBYFDEF{background:" + ("#666")  + ";color:" + ("white")  + ";}.GPBYFDEE{background:" + ("#940000")  + ";color:" + ("white")  + ";}.GPBYFDEA:hover{border-color:" + ("#939393")  + ";}.GPBYFDEA[disabled]{cursor:" + ("default") ) + (";color:" + ("#888")  + ";}.GPBYFDEA[disabled]:hover{border-color:" + ("#bbb"+ " " +"#bbb"+ " " +"#a0a0a0")  + ";}.GPBYFDEG{border:" + ("1px"+ " " +"inset"+ " " +"#ccc")  + ";}.GPBYFDEB{border-top-right-radius:" + ("0")  + ";border-bottom-right-radius:" + ("0")  + ";-moz-border-radius-topleft:" + ("0")  + ";-moz-border-radius-bottomleft:" + ("0")  + ";}.GPBYFDEC{border-left:" + ("none")  + ";border-top-left-radius:" + ("0")  + ";border-bottom-left-radius:" + ("0")  + ";-moz-border-radius-topright:") + (("0")  + ";-moz-border-radius-bottomright:" + ("0")  + ";}")) : ((".GPBYFDEA{height:" + ((ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getHeight() + "px")  + ";overflow:" + ("hidden")  + ";background:" + ("url(\"" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getSafeUri().asString() + "\") -" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getLeft() + "px -" + (ButtonCellBase_DefaultAppearance_Resources_default_InlineClientBundleGenerator.this.buttonCellBaseBackground()).getTop() + "px  repeat-x")  + ";height:" + ("auto")  + ";background-color:" + ("#f1f1f1")  + ";margin:" + ("0")  + ";padding:" + ("5px"+ " " +"7px")  + ";text-decoration:" + ("none")  + ";cursor:" + ("pointer")  + ";cursor:" + ("hand")  + ";font-size:") + (("small")  + ";border:" + ("1px"+ " " +"solid"+ " " +"#bbb")  + ";border-bottom:" + ("1px"+ " " +"solid"+ " " +"#a0a0a0")  + ";border-radius:" + ("5px")  + ";-moz-border-radius:" + ("5px")  + ";}.GPBYFDEF{background:" + ("#666")  + ";color:" + ("white")  + ";}.GPBYFDEE{background:" + ("#940000")  + ";color:" + ("white")  + ";}.GPBYFDEA:hover{border-color:" + ("#939393")  + ";}.GPBYFDEA[disabled]{cursor:" + ("default") ) + (";color:" + ("#888")  + ";}.GPBYFDEA[disabled]:hover{border-color:" + ("#bbb"+ " " +"#bbb"+ " " +"#a0a0a0")  + ";}.GPBYFDEG{border:" + ("1px"+ " " +"inset"+ " " +"#ccc")  + ";}.GPBYFDEB{border-top-left-radius:" + ("0")  + ";border-bottom-left-radius:" + ("0")  + ";-moz-border-radius-topleft:" + ("0")  + ";-moz-border-radius-bottomleft:" + ("0")  + ";}.GPBYFDEC{border-right:" + ("none")  + ";border-top-right-radius:" + ("0")  + ";border-bottom-right-radius:" + ("0")  + ";-moz-border-radius-topright:") + (("0")  + ";-moz-border-radius-bottomright:" + ("0")  + ";}"));
      }
      public java.lang.String buttonCellBase(){
        return "GPBYFDEA";
      }
      public java.lang.String buttonCellBaseCollapseLeft(){
        return "GPBYFDEB";
      }
      public java.lang.String buttonCellBaseCollapseRight(){
        return "GPBYFDEC";
      }
      public java.lang.String buttonCellBaseDefault(){
        return "GPBYFDED";
      }
      public java.lang.String buttonCellBaseNegative(){
        return "GPBYFDEE";
      }
      public java.lang.String buttonCellBasePrimary(){
        return "GPBYFDEF";
      }
      public java.lang.String buttonCellBasePushing(){
        return "GPBYFDEG";
      }
    }
    ;
  }
  private static class buttonCellBaseStyleInitializer {
    static {
      _instance0.buttonCellBaseStyleInitializer();
    }
    static com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style get() {
      return buttonCellBaseStyle;
    }
  }
  public com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style buttonCellBaseStyle() {
    return buttonCellBaseStyleInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static final java.lang.String externalImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAfCAYAAACGVs+MAAAAL0lEQVR42u3OMQ0AAAzDsPJnWwQbjD6OlN+5cWl7ywEAAAAAAAAAAAAAAAAA5oAHQZjT082H5T4AAAAASUVORK5CYII=";
  private static final java.lang.String externalImage_rtl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAfCAYAAACGVs+MAAAAL0lEQVR42u3OMQ0AAAzDsPJnWwQbjD6OlN+5cWl7ywEAAAAAAAAAAAAAAAAA5oAHQZjT082H5T4AAAAASUVORK5CYII=";
  private static com.google.gwt.resources.client.ImageResource buttonCellBaseBackground;
  private static com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style buttonCellBaseStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      buttonCellBaseBackground(), 
      buttonCellBaseStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("buttonCellBaseBackground", buttonCellBaseBackground());
        resourceMap.put("buttonCellBaseStyle", buttonCellBaseStyle());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'buttonCellBaseBackground': return this.@com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Resources::buttonCellBaseBackground()();
      case 'buttonCellBaseStyle': return this.@com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Resources::buttonCellBaseStyle()();
    }
    return null;
  }-*/;
}
