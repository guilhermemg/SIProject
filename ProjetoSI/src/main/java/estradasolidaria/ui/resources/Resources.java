package estradasolidaria.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	@Source("homePageImage.jpg")
	ImageResource getHomePageImage();

	@Source("genericUserImage.jpg")
	ImageResource getGenericUserImage();
	
	@Source("genericLittleUserImage.jpg")
	ImageResource getGenericLittleUserImage();
	
	@Source("icone_fechar.jpg")
	ImageResource getCloseIcon();
}
