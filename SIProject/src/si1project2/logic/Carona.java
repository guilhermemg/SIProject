package si1project2.logic;

import java.util.Map;
import java.util.TreeMap;

public class Carona {
	private String idDonoDaCarona;
	private String idCarona;
	private String origem;
	private String destino;
	private String data;
	private String vagas;
	private String hora;
	
	private Map<String, Solicitacao> mapIdSolicitacao = new TreeMap<String, Solicitacao>();
	private String pontoEncontro;

	public Carona(String idDonoDaCarona, String origem2, String destino2,
		String data2, String hora2, String vagas2) throws Exception {
		
		setIdDonaDaCarona(idDonoDaCarona);
		setOrigem(origem2);
		setDestino(destino2);
		setData(data2);
		setHora(hora2);
		setVagas(vagas2);
		setIdCarona(this.hashCode() + "");
	}

	public String getIdCarona() {
		return idCarona;
	}

	public void setIdCarona(String hashCode) {
		this.idCarona = hashCode;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) throws Exception {
		if(validaHora(hora))
			this.hora = hora;
	}

	private boolean validaHora(String hora2) throws Exception {
		if(hora2 == null || hora2.equals(""))
			throw new Exception("Hora inválida");
		//TODO
		return Character.isDigit(hora2.charAt(0)) &&
				Character.isDigit(hora2.charAt(1)) &&
				!Character.isDigit(hora2.charAt(2)) &&
				hora2.charAt(2)==':' &&
				Character.isDigit(hora2.charAt(3)) &&
				Character.isDigit(hora2.charAt(4));
	}

	public void setIdDonaDaCarona(String idDonoDaCarona) {
		this.idDonoDaCarona = idDonoDaCarona;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) throws Exception {
		if(origem == null || origem.equals(""))
			throw new Exception("Origem inválida");
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) throws Exception {
		if(destino == null || destino.equals(""))
			throw new Exception("Destino inválido");
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws Exception {
		if(data == null || data.equals(""))
			throw new Exception("Data inválida");
		
		if(data.charAt(0) > 1 || data.charAt(1) > 9 ||
				data.charAt(3) > 1 || data.charAt(4) > 9 ||
			data.charAt(7) > 1)
		//TODO
		this.data = data;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas2) throws Exception {
		if(vagas2 == null || vagas2.equals(""))
			throw new Exception("Vaga inválida");
		
		if(Integer.parseInt(vagas2) <= 0)
			throw new Exception("Vaga inválida");
		
		// NAO TAH FUNCIONANDO DIREITO
		/*if(data == null || data.equals("") || !DateUtil.validaData(data) || DateUtil.datajapassou(data)){
			throw new Exception("Data inválida");
		}*/
		
		this.vagas = vagas2;
	}

	public String getTrajeto() {
		return this.origem + " - " + this.destino;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result
				+ ((idCarona == null) ? 0 : idCarona.hashCode());
		result = prime * result
				+ ((idDonoDaCarona == null) ? 0 : idDonoDaCarona.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result + ((vagas == null) ? 0 : vagas.hashCode());
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
		if (!(obj instanceof Carona)) {
			return false;
		}
		Carona other = (Carona) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (destino == null) {
			if (other.destino != null) {
				return false;
			}
		} else if (!destino.equals(other.destino)) {
			return false;
		}
		if (hora == null) {
			if (other.hora != null) {
				return false;
			}
		} else if (!hora.equals(other.hora)) {
			return false;
		}
		if (idCarona == null) {
			if (other.idCarona != null) {
				return false;
			}
		} else if (!idCarona.equals(other.idCarona)) {
			return false;
		}
		if (idDonoDaCarona == null) {
			if (other.idDonoDaCarona != null) {
				return false;
			}
		} else if (!idDonoDaCarona.equals(other.idDonoDaCarona)) {
			return false;
		}
		if (origem == null) {
			if (other.origem != null) {
				return false;
			}
		} else if (!origem.equals(other.origem)) {
			return false;
		}
		if (vagas == null) {
			if (other.vagas != null) {
				return false;
			}
		} else if (!vagas.equals(other.vagas)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + hora;
	}

	public Object getAtributo(String nomeAtributo) throws Exception {
		if(nomeAtributo == null || nomeAtributo.equals(""))
			throw new Exception("Atributo inválido");
		
		if(nomeAtributo.equals("origem"))
			return this.getOrigem();
		else if(nomeAtributo.equals("destino"))
			return this.getDestino();
		else if(nomeAtributo.equals("data"))
			return this.getData();
		else if(nomeAtributo.equals("hora"))
			return this.getHora();
		else if(nomeAtributo.equals("vagas"))
			return this.getVagas();
		else if(nomeAtributo.equals("Ponto de Encontro"))
			return this.getPontoEncontro();
		else
			throw new Exception("Atributo inexistente");
	}
	
	public String getPontoEncontro() {
		return this.pontoEncontro;
	}
	
	public void setPontoEncontro(String ponto) {
		this.pontoEncontro = ponto;
	}

	public String getCarona() {
		return this.toString();
	}

	public String getIdDonoDaCarona() {
		return null;
	}
	
	/**
	 * Retorna id da solicitacao adicionada.
	 * 
	 * @param idDonoDaCarona2
	 * @param idDonoDaSolicitacao
	 * @param ponto : ponto de encontro
	 * @return id da solicitacao
	 * @throws Exception 
	 */
	public String addSolicitacao(String origem, String destino, 
			String idDonoDaCarona2,	String idDonoDaSolicitacao,
			String ponto) throws Exception {
		if(ponto == null)
			throw new Exception("Ponto Inválido");
		if(ponto.equals("") || ponto.equals(pontoEncontro))
			throw new Exception("Ponto Inválido");
		Solicitacao s = new Solicitacao(origem, destino, idDonoDaCarona2, idDonoDaSolicitacao, ponto);
		mapIdSolicitacao.put(s.getIdSolicitacao(), s);
		return s.getIdSolicitacao();
	}

	public Map<String, Solicitacao> getMapIdSolicitacao() {
		return this.mapIdSolicitacao;
	}

	public Object getAtributoSolicitacao(String idSolicitacao, String atributo) throws Exception {
		return this.mapIdSolicitacao.get(idSolicitacao).getAtributo(atributo);
	}

	public void removeSolicitacao(String idSolicitacao) {
		for(Solicitacao s : mapIdSolicitacao.values()) {
			if(s.getIdSolicitacao().equals(idSolicitacao)) {
				mapIdSolicitacao.remove(idSolicitacao);
				break;
			}
		}
	}

	public void setSolicitacaoPontoEncontro(String idSugestao, String ponto) throws Exception {
		if(ponto == null)
			throw new Exception("Ponto Inválido");
		if(ponto.equals(""))
			throw new Exception("Ponto Inválido");
		
		for(Solicitacao s : mapIdSolicitacao.values()) {
			if(s.getIdSolicitacao().equals(idSugestao)) {
				s.setPontoEncontroCaronaSolicitacao(ponto);
				break;
			}
		}
	}

	public String addSolicitacao(String origem2, String destino2,
			String idDonoDaCarona2, String idDonoDaSolicitacao) {
		Solicitacao s = new Solicitacao(origem2, destino2, idDonoDaCarona2, idDonoDaSolicitacao);
		mapIdSolicitacao.put(s.getIdSolicitacao(), s);
		return s.getIdSolicitacao();
	}
	
	/**
	 * Rejeita solicitacao, removendo-a
	 * das solicitacoes associada a esta
	 * carona.
	 * 
	 * @param idSolicitacao
	 */
	public void rejeitarSolicitacao(String idSolicitacao) {
		mapIdSolicitacao.remove(idSolicitacao);
	}
}
