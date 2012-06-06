package estradasolidaria.ui.server.logic;

import java.io.Serializable;

/**
 * Classe que representa uma sugestao
 * de um ponto de encontro para uma carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Sugestao implements Serializable {
	private static final long serialVersionUID = 5035043339505436230L;
	
	private String sugestaoPontoDeEncontro;
	private Integer idSugestao;
	private String resposta;
	/**
	 * Cria uma nova sugestão.
	 * 
	 * @param sugestao
	 */
	public Sugestao(String sugestao) {
		this.sugestaoPontoDeEncontro = sugestao;
		this.idSugestao = hashCode();
	}

	/**
	 * Retorna a sugestão do ponto de encontro.
	 * 
	 * @return sugestaoPontoEncontro
	 */
	public String getPontoSugerido() {
		return this.sugestaoPontoDeEncontro;
	}

	/**
	 * Retorna o id da sugestão.
	 * 
	 * @return idSugestao
	 */
	public Integer getIdSugestao() {
		return this.idSugestao;
	}

	/**
	 * Retorna a resposta desta sugestão.
	 * 
	 * @return resposta.
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * Modifica a resposta desta sugestão.
	 * @param resposta
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sugestaoPontoDeEncontro == null) ? 0 : sugestaoPontoDeEncontro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sugestao other = (Sugestao) obj;
		if (sugestaoPontoDeEncontro == null) {
			if (other.sugestaoPontoDeEncontro != null)
				return false;
		} else if (!sugestaoPontoDeEncontro.equals(other.sugestaoPontoDeEncontro))
			return false;
		return true;
	}

}