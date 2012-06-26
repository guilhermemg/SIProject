package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTCarona implements IsSerializable {
	protected String nomeDono;
	protected String idDono;
	protected String origem;
	protected String destino;
	protected String data;
	protected String hora;
	protected String vagas;
	protected String review;
	protected String pontoEncontro;
	protected String idCarona;

	@Override
	public String toString() {
		return nomeDono +", "+origem+", " + destino + ", " + data +", " +vagas +", "+ review +", "+pontoEncontro;
	}
}