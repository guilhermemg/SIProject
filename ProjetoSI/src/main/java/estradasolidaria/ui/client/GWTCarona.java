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

	public String getNomeDono() {
		return nomeDono;
	}

	public void setNomeDono(String nomeDono) {
		this.nomeDono = nomeDono;
	}

	public String getIdDono() {
		return idDono;
	}

	public void setIdDono(String idDono) {
		this.idDono = idDono;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getPontoEncontro() {
		return pontoEncontro;
	}

	public void setPontoEncontro(String pontoEncontro) {
		this.pontoEncontro = pontoEncontro;
	}

	public String getIdCarona() {
		return idCarona;
	}

	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	
}