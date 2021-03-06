package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTInteresse implements IsSerializable {
	private Integer idInteresse;
	private String origem;
	private String destino;
	private String data;
	private String horaInicio;
	private String horaFim;

	public void setIdInteresse(Integer idInteresse) {
		this.idInteresse = idInteresse;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public Integer getIdInteresse() {
		return idInteresse;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public String getData() {
		return data;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}
	
	public String toString() {
		return this.getOrigem() + " para " + this.getDestino() 
		+ ", " + this.getData() + ". Horário: " + this.getHoraInicio() + 
			                        " - " + this.getHoraFim();
	}
}
