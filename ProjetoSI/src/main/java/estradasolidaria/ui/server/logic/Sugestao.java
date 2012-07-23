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
	private String origem;
	private String destino;

	/**
	 * Cria uma nova sugestão.
	 * 
	 * @param ponto
	 * @param donoDaSugestao 
	 */
	public Sugestao(String ponto, String origem, String destino) {
		synchronized (Sugestao.class) {
			setSugestaoPontoEncontro(ponto);
			setOrigem(origem);
			setDestino(destino);
			setIdSugestao(hashCode());
		}
	}
	
	/**
	 * Configura destino da sugestao.
	 * 
	 * @param destino2
	 */
	private synchronized void setDestino(String destino2) {
		this.destino = destino2;
	}

	/**
	 * Configura origem da sugestao.
	 * 
	 * @param origem2
	 */
	private synchronized void setOrigem(String origem2) {
		this.origem = origem2;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result
				+ ((idSugestao == null) ? 0 : idSugestao.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result
				+ ((resposta == null) ? 0 : resposta.hashCode());
		result = prime
				* result
				+ ((sugestaoPontoDeEncontro == null) ? 0
						: sugestaoPontoDeEncontro.hashCode());
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
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (idSugestao == null) {
			if (other.idSugestao != null)
				return false;
		} else if (!idSugestao.equals(other.idSugestao))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		if (sugestaoPontoDeEncontro == null) {
			if (other.sugestaoPontoDeEncontro != null)
				return false;
		} else if (!sugestaoPontoDeEncontro
				.equals(other.sugestaoPontoDeEncontro))
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

	/**
	 * Retorna origem de sugestao.
	 * 
	 * @return origem
	 */
	public synchronized String getOrigem() {
		return origem;
	}

	/**
	 * Retorna destino de sugestao.
	 * 
	 * @return destino
	 */
	public synchronized String getDestino() {
		return destino;
	}
}