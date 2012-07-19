package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTSugestao implements IsSerializable {
	private String sugestaoPontoDeEncontro;
	private Integer idSugestao;
	private String resposta;
	
	public String getSugestaoPontoDeEncontro() {
		return sugestaoPontoDeEncontro;
	}
	public void setSugestaoPontoDeEncontro(String sugestaoPontoDeEncontro) {
		this.sugestaoPontoDeEncontro = sugestaoPontoDeEncontro;
	}
	public Integer getIdSugestao() {
		return idSugestao;
	}
	public void setIdSugestao(Integer idSugestao) {
		this.idSugestao = idSugestao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
}
