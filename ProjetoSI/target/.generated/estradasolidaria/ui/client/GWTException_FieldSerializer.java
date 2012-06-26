package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class GWTException_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getMessage(estradasolidaria.ui.client.GWTException instance) /*-{
    return instance.@estradasolidaria.ui.client.GWTException::message;
  }-*/;
  
  private static native void setMessage(estradasolidaria.ui.client.GWTException instance, java.lang.String value) 
  /*-{
    instance.@estradasolidaria.ui.client.GWTException::message = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, estradasolidaria.ui.client.GWTException instance) throws SerializationException {
    setMessage(instance, streamReader.readString());
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static estradasolidaria.ui.client.GWTException instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new estradasolidaria.ui.client.GWTException();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, estradasolidaria.ui.client.GWTException instance) throws SerializationException {
    streamWriter.writeString(getMessage(instance));
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return estradasolidaria.ui.client.GWTException_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    estradasolidaria.ui.client.GWTException_FieldSerializer.deserialize(reader, (estradasolidaria.ui.client.GWTException)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    estradasolidaria.ui.client.GWTException_FieldSerializer.serialize(writer, (estradasolidaria.ui.client.GWTException)object);
  }
  
}
