package estradasolidaria.ui.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTException extends Exception implements Serializable, IsSerializable {
	
	private static final long serialVersionUID = 757308123348406335L;
	private String message;
	
	public GWTException() {
		
	}
	
	public GWTException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
