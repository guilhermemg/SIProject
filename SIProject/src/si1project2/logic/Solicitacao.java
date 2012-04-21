package si1project2.logic;

/**
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Solicitacao {
	private String origemCaronaSolicitacao;
	private String destinoCaronaSolicitacao;
	private String pontoEncontroCaronaSolicitacao;
	
	private String idDonoDaCaronaSolicitacao;
	private String idDonoDaSolicitacao;
	
	private String idSolicitacao;
	
	public Solicitacao(String origem, String destino, String idDonoDaCarona, 
			String idDonoDaSolicitacao, String ponto) {
		setOrigemCaronaSolicitacao(origem); // id da sessao do usuario requerente
		setDestinoCaronaSolicitacao(destino);
		setIdDonoDaCarona(idDonoDaCarona);
		setIdDonoDaSolicitacao(idDonoDaSolicitacao);
		setPontoEncontroCaronaSolicitacao(ponto);

		setIdSolicitacao(this.hashCode() + "");
	}

	// NOTE: pontoEncontroCaronaSolicitacao = null
	public Solicitacao(String origem2, String destino2, String idDonoDaCarona2,
			String idDonoDaSolicitacao2) { 
		setOrigemCaronaSolicitacao(origem2); // id da sessao do usuario requerente
		setDestinoCaronaSolicitacao(destino2);
		setIdDonoDaCarona(idDonoDaCarona2);
		setIdDonoDaSolicitacao(idDonoDaSolicitacao2);

		setIdSolicitacao(this.hashCode() + "");
	}

	@Override
	public String toString() {
		return "Solicitacao [origemCaronaSolicitacao="
				+ origemCaronaSolicitacao + ", destinoCaronaSolicitacao="
				+ destinoCaronaSolicitacao
				+ ", pontoEncontroCaronaSolicitacao="
				+ pontoEncontroCaronaSolicitacao
				+ ", idDonoDaCaronaSolicitacao=" + idDonoDaCaronaSolicitacao
				+ ", idDonoDaSolicitacao=" + idDonoDaSolicitacao
				+ ", idSolicitacao=" + idSolicitacao + "]";
	}

	private void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	private void setIdDonoDaSolicitacao(String idDonoDaSolicitacao2) {
		this.idDonoDaSolicitacao = idDonoDaSolicitacao2;
	}

	private void setIdDonoDaCarona(String idDonoDaCarona2) {
		this.idDonoDaCaronaSolicitacao = idDonoDaCarona2;
	}
	
	public String getIdDonoDaSolicitacao() {
		return this.idDonoDaSolicitacao;
	}
	
	public String getIdDonoDaCarona() {
		return this.idDonoDaCaronaSolicitacao;
	}

	public Object getAtributo(String atributo) throws Exception {
		if(atributo == null || atributo.equals(""))
			throw new Exception("Atributo inválido");
		
		if(atributo.equals("origem"))
			return this.getOrigem();
		else if(atributo.equals("destino"))
			return this.getDestino();
		else if(atributo.equals("Dono da carona"))
			return this.getIdDonoDaCarona();
		else if(atributo.equals("Dono da solicitacao"))
			return this.getIdDonoDaSolicitacao();
		else if(atributo.equals("Ponto de Encontro"))
			return this.getPontoEncontro();
		else
			throw new Exception("Atributo inexistente");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinoCaronaSolicitacao == null) ? 0 : destinoCaronaSolicitacao.hashCode());
		result = prime * result
				+ ((idDonoDaCaronaSolicitacao == null) ? 0 : idDonoDaCaronaSolicitacao.hashCode());
		result = prime
				* result
				+ ((idDonoDaSolicitacao == null) ? 0 : idDonoDaSolicitacao
						.hashCode());
		result = prime * result
				+ ((idSolicitacao == null) ? 0 : idSolicitacao.hashCode());
		result = prime * result + ((origemCaronaSolicitacao == null) ? 0 : origemCaronaSolicitacao.hashCode());
		result = prime * result
				+ ((pontoEncontroCaronaSolicitacao == null) ? 0 : pontoEncontroCaronaSolicitacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Solicitacao)) {
			return false;
		}
		Solicitacao other = (Solicitacao) obj;
		if (destinoCaronaSolicitacao == null) {
			if (other.destinoCaronaSolicitacao != null) {
				return false;
			}
		} else if (!destinoCaronaSolicitacao.equals(other.destinoCaronaSolicitacao)) {
			return false;
		}
		if (idDonoDaCaronaSolicitacao == null) {
			if (other.idDonoDaCaronaSolicitacao != null) {
				return false;
			}
		} else if (!idDonoDaCaronaSolicitacao.equals(other.idDonoDaCaronaSolicitacao)) {
			return false;
		}
		if (idDonoDaSolicitacao == null) {
			if (other.idDonoDaSolicitacao != null) {
				return false;
			}
		} else if (!idDonoDaSolicitacao.equals(other.idDonoDaSolicitacao)) {
			return false;
		}
		if (idSolicitacao == null) {
			if (other.idSolicitacao != null) {
				return false;
			}
		} else if (!idSolicitacao.equals(other.idSolicitacao)) {
			return false;
		}
		if (origemCaronaSolicitacao == null) {
			if (other.origemCaronaSolicitacao != null) {
				return false;
			}
		} else if (!origemCaronaSolicitacao.equals(other.origemCaronaSolicitacao)) {
			return false;
		}
		if (pontoEncontroCaronaSolicitacao == null) {
			if (other.pontoEncontroCaronaSolicitacao != null) {
				return false;
			}
		} else if (!pontoEncontroCaronaSolicitacao.equals(other.pontoEncontroCaronaSolicitacao)) {
			return false;
		}
		return true;
	}

	public String getOrigem() {
		return origemCaronaSolicitacao;
	}

	public void setOrigemCaronaSolicitacao(String origem) {
		//TODO
		this.origemCaronaSolicitacao = origem;
	}

	public String getDestino() {
		return destinoCaronaSolicitacao;
	}

	public void setDestinoCaronaSolicitacao(String destino) {
		//TODO
		this.destinoCaronaSolicitacao = destino;
	}

	public String getPontoEncontro() {
		return pontoEncontroCaronaSolicitacao;
	}

	public void setPontoEncontroCaronaSolicitacao(String pontoEncontro) {
		//TODO
		this.pontoEncontroCaronaSolicitacao = pontoEncontro;
	}

	public String getIdSolicitacao() {
		return this.idSolicitacao;
	}
}
