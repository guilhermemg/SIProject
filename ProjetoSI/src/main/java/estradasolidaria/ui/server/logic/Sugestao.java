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

	private final String origem;

	private final String destino;

	/**
	 * Cria uma nova sugestão.
	 * 
	 * @param ponto
	 * @param donoDaSugestao 
	 */
	public Sugestao(String ponto, String origem, String destino) {
		synchronized (Sugestao.class) {
			setSugestaoPontoEncontro(ponto);
			setIdSugestao(hashCode());
			this.origem = origem;
			this.destino = destino;
			
		}
	}
	
	/**
	 * Configura id de sugestao.
	 * 
	 * @param hashCode
	 */
	private synchronized void setIdSugestao(int hashCode) {
		this.idSugestao = hashCode;
	}

	/**
	 * Retorna a sugestão do ponto de encontro.
	 * 
	 * @return sugestaoPontoEncontro
	 */
	public synchronized String getPontoSugerido() {
		return this.sugestaoPontoDeEncontro;
	}

	/**
	 * Retorna o id da sugestão.
	 * 
	 * @return idSugestao
	 */
	public synchronized Integer getIdSugestao() {
		return this.idSugestao;
	}

	/**
	 * Retorna a resposta desta sugestão.
	 * 
	 * @return resposta.
	 */
	public synchronized String getResposta() {
		return resposta;
	}

	/**
	 * Modifica a resposta desta sugestão.
	 * @param resposta
	 */
	public synchronized void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sugestaoPontoDeEncontro == null) ? 0 : sugestaoPontoDeEncontro.hashCode());
		return result;
	}

	@Override
	public synchronized boolean equals(Object obj) {
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

	/**
	 * Configura ponto de encontro
	 * para a carona.
	 * 
	 * @param ponto
	 */
	public synchronized void setSugestaoPontoEncontro(String ponto) {
		this.sugestaoPontoDeEncontro = ponto;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}
}