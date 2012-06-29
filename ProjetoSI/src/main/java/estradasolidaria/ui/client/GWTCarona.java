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

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public String getHora() {
		return hora;
	}

	public String getVagas() {
		return vagas;
	}

	public String getData() {
		return data;
	}
	
	public String getIdCarona() {
		return idCarona;
	}

	public void setData(String format) {
		this.data = format;
	}

	public void setDestino(String destino2) {
		this.destino = destino2;
	}

	public void setOrigem(String origem2) {
		this.origem = origem2;
	}

	public void setIdCarona(String string) {
		this.idCarona = string;
	}

	public void setHora(String format) {
		this.hora = format;
	}

	public void setVagas(String vagas2) {
		this.vagas = vagas2;
	}
}