package com.google.gwt.cell.client;

public class ButtonCellBase_DefaultAppearance_TemplateImpl implements com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Template {
  
  public com.google.gwt.safehtml.shared.SafeHtml iconContentLayout(java.lang.String arg0,java.lang.String arg1,int arg2,com.google.gwt.safehtml.shared.SafeHtml arg3,com.google.gwt.safehtml.shared.SafeHtml arg4) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class=\"");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\" style=\"position:relative;padding-");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append(":");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg2)));
    sb.append("px;zoom:0;\">");
    sb.append(arg3.asString());
    sb.append(arg4.asString());
    sb.append("</div>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

public com.google.gwt.safehtml.shared.SafeHtml iconWrapper(java.lang.String arg0,int arg1,com.google.gwt.safehtml.shared.SafeHtml arg2) {
StringBuilder sb = new java.lang.StringBuilder();
sb.append("<div style=\"position:absolute;");
sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
sb.append(":0px;top:50%;line-height:0px;margin-top:-");
sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg1)));
sb.append("px;\">");
sb.append(arg2.asString());
sb.append("</div>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}
