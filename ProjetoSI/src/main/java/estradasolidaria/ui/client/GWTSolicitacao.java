package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTSolicitacao implements IsSerializable {
	
	private GWTUsuario donoDaCarona;
	private GWTCarona carona;
	
	public GWTUsuario getDonoDaCarona() {
		return donoDaCarona;
	}
	public void setDonoDaCarona(GWTUsuario donoDaCarona) {
		this.donoDaCarona = donoDaCarona;
	}
	public GWTCarona getCarona() {
		return carona;
	}
	public void setCarona(GWTCarona carona) {
		this.carona = carona;
	}
	
}
