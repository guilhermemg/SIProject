package estradasolidaria.ui.server.logic;

/**
 * Classe que representa uma solicitacao de um
 * usuario feita a outro usuario.
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
	
	private String respostaPontoEncontro;
	
	private Usuario donoDaCaronaSolicitacao;
	private Usuario donoDaSolicitacao;
	
	private Integer idSolicitacao;
	
	private EstadoSolicitacaoInterface estado;
	private Integer idCarona;
	
	/**
	 * Construtor 1 da classe Solicitacao.
	 * @param origem
	 * @param destino
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param ponto
	 */
	public Solicitacao(Integer idCarona, String origem, String destino, Usuario donoDaCarona, 
			Usuario donoDaSolicitacao, String ponto) {
		setIdCarona(idCarona);
		setOrigemCaronaSolicitacao(origem); // id da sess�o do usuario requerente
		setDestinoCaronaSolicitacao(destino);
		setDonoDaCarona(donoDaCarona);
		setDonoDaSolicitacao(donoDaSolicitacao);
		setPontoEncontroCaronaSolicitacao(ponto);
		setEstado(new EstadoSolicitacaoPendente());
		
		setIdSolicitacao(this.hashCode());
	}

	// NOTE: pontoEncontroCaronaSolicitacao = null
	/**
	 * Construtor 2 da classe Solicitacao.
	 * @param origem2
	 * @param destino2
	 * @param donoDaCarona2
	 * @param idDonoDaSolicitacao2
	 * @param estado 
	 * @param ponto 
	 */
	public Solicitacao(Integer idCarona, String origem2, String destino2, Usuario donoDaCarona2,
			Usuario donoDaSolicitacao2) { 
		setIdCarona(idCarona);
		setOrigemCaronaSolicitacao(origem2); // id da sessao do usuario requerente
		setDestinoCaronaSolicitacao(destino2);
		setDonoDaCarona(donoDaCarona2);
		setDonoDaSolicitacao(donoDaSolicitacao2);
		setEstado(new EstadoSolicitacaoPendente());
		
		setIdSolicitacao(this.hashCode());
	}
	
	private void setIdCarona(Integer idCarona2) {
		this.idCarona = idCarona2;
	}

	@Override
	public String toString() {
		return "Solicitacao [origemCaronaSolicitacao="
				+ origemCaronaSolicitacao + ", destinoCaronaSolicitacao="
				+ destinoCaronaSolicitacao
				+ ", pontoEncontroCaronaSolicitacao="
				+ pontoEncontroCaronaSolicitacao
				+ ", idDonoDaCaronaSolicitacao=" + donoDaCaronaSolicitacao
				+ ", idDonoDaSolicitacao=" + donoDaSolicitacao
				+ ", idSolicitacao=" + idSolicitacao + "]";
	}

	/**
	 * Configura Id da solicitacao.
	 * 
	 * @param idSolicitacao
	 */
	private void setIdSolicitacao(Integer idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	/**
	 * Configura id do solicitante.
	 * 
	 * @param idDonoDaSolicitacao2
	 */
	private void setDonoDaSolicitacao(Usuario donoDaSolicitacao2) {
		this.donoDaSolicitacao = donoDaSolicitacao2;
	}

	/**
	 * Configura dono da carona.
	 * 
	 * @param donoDaCarona2
	 */
	private void setDonoDaCarona(Usuario donoDaCarona2) {
		this.donoDaCaronaSolicitacao = donoDaCarona2;
	}
	
	/**
	 * Retorna solicitante.
	 * 
	 * @return donoDaSolicitacao
	 */
	public Usuario getDonoDaSolicitacao() {
		return this.donoDaSolicitacao;
	}
	
	/**
	 * Retorna dono da carona.
	 * 
	 * @return donoDaCaronaSolicitacao
	 */
	public Usuario getDonoDaCarona() {
		return this.donoDaCaronaSolicitacao;
	}

	/**
	 * Retorna valor de atributo de solicitacao de acordo com par�metro fornecido.
	 * 
	 * @param atributo
	 * @return atributo
	 * @
	 */
	public Object getAtributo(String atributo)  {
		if(atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inexistente");
		
		if(atributo.equals(EnumSolicitacao.ORIGEM.getNomeAtributo()))
			return this.getOrigem();
		else if(atributo.equals(EnumSolicitacao.DESTINO.getNomeAtributo()))
			return this.getDestino();
		else if(atributo.equals(EnumSolicitacao.DONO_CARONA.getNomeAtributo()))
			return this.getDonoDaCarona();
		else if(atributo.equals(EnumSolicitacao.DONO_SOLICITACAO.getNomeAtributo()))
			return this.getDonoDaSolicitacao();
		else if(atributo.equals(EnumSolicitacao.PONTO_ENCONTRO.getNomeAtributo()))
			return this.getPontoEncontro();
		else if(atributo.equals(EnumSolicitacao.ESTADO_SOLICITACAO.getNomeAtributo()))
			return this.getEstado();
		else
			throw new IllegalArgumentException("Atributo inexistente");
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((destinoCaronaSolicitacao == null) ? 0
						: destinoCaronaSolicitacao.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime
				* result
				+ ((donoDaCaronaSolicitacao == null) ? 0
						: donoDaCaronaSolicitacao.hashCode());
		result = prime
				* result
				+ ((donoDaSolicitacao == null) ? 0 : donoDaSolicitacao
						.hashCode());
		result = prime * result
				+ ((idSolicitacao == null) ? 0 : idSolicitacao.hashCode());
		result = prime
				* result
				+ ((origemCaronaSolicitacao == null) ? 0
						: origemCaronaSolicitacao.hashCode());
		result = prime
				* result
				+ ((pontoEncontroCaronaSolicitacao == null) ? 0
						: pontoEncontroCaronaSolicitacao.hashCode());
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
		Solicitacao other = (Solicitacao) obj;
		if (destinoCaronaSolicitacao == null) {
			if (other.destinoCaronaSolicitacao != null)
				return false;
		} else if (!destinoCaronaSolicitacao
				.equals(other.destinoCaronaSolicitacao))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (donoDaCaronaSolicitacao == null) {
			if (other.donoDaCaronaSolicitacao != null)
				return false;
		} else if (!donoDaCaronaSolicitacao
				.equals(other.donoDaCaronaSolicitacao))
			return false;
		if (donoDaSolicitacao == null) {
			if (other.donoDaSolicitacao != null)
				return false;
		} else if (!donoDaSolicitacao.equals(other.donoDaSolicitacao))
			return false;
		if (idSolicitacao == null) {
			if (other.idSolicitacao != null)
				return false;
		} else if (!idSolicitacao.equals(other.idSolicitacao))
			return false;
		if (origemCaronaSolicitacao == null) {
			if (other.origemCaronaSolicitacao != null)
				return false;
		} else if (!origemCaronaSolicitacao
				.equals(other.origemCaronaSolicitacao))
			return false;
		if (pontoEncontroCaronaSolicitacao == null) {
			if (other.pontoEncontroCaronaSolicitacao != null)
				return false;
		} else if (!pontoEncontroCaronaSolicitacao
				.equals(other.pontoEncontroCaronaSolicitacao))
			return false;
		return true;
	}

	/**
	 * Retorna origem.
	 * @return origem
	 */
	public String getOrigem() {
		return origemCaronaSolicitacao;
	}

	/**
	 * Configura origem.
	 * @param origem
	 */
	public void setOrigemCaronaSolicitacao(String origem) {
		this.origemCaronaSolicitacao = origem;
	}

	/**
	 * Retorna destino.
	 * @return destino
	 */
	public String getDestino() {
		return destinoCaronaSolicitacao;
	}

	/**
	 * Configura destino.
	 * @param destino
	 */
	public void setDestinoCaronaSolicitacao(String destino) {
		this.destinoCaronaSolicitacao = destino;
	}

	/**
	 * Retorna ponto de encontro.
	 * @return ponto de encontro
	 */
	public String getPontoEncontro() {
		return pontoEncontroCaronaSolicitacao;
	}

	/**
	 * Configura ponto de encontro.
	 * @param pontoEncontro
	 */
	public void setPontoEncontroCaronaSolicitacao(String pontoEncontro) {
		this.pontoEncontroCaronaSolicitacao = pontoEncontro;
	}

	/**
	 * Retorna id da solicitacao.
	 * 
	 * @return idSolicitacao
	 */
	public Integer getIdSolicitacao() {
		return this.idSolicitacao;
	}
	
	/**
	 * Retorna estado da solicitacao.
	 * 
	 * @return estado
	 */
	public EstadoSolicitacaoInterface getEstado() {
		return estado;
	}

	/**
	 * Configura estado da solicitacao.
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoSolicitacaoInterface estado) {
		this.estado = estado;
	}
	
	/**
	 * Muda estado da solicitacao para aceita.
	 * 
	 * @param c: carona a qual a solicitacao pertence
	 */
	public void aceitar(Carona c) {
		estado.aceitar(this, c);
	}
	
	/**
	 * Muda estado da solicitacao para rejeitada.
	 * 
	 * @param c: carona a qual a solicitacao pertence
	 */
	public void rejeitar(Carona c) {
		estado.rejeitar(this, c);
	}
	
	/**
	 * Cancela solicitacao.
	 */
	public void cancelar() {
		estado.cancelar(this);
	}
	
	/**
	 * Verifica se estado da solicitacao eh aceito.
	 * 
	 * @return true se estado eh aceito
	 */
	public boolean isAceita() {
		return estado instanceof EstadoSolicitacaoAceita;
	}
	
	/**
	 * Verifica se estado da solicitacao eh pendente.
	 * 
	 * @return true se estado eh pendente
	 */
	public boolean isPendente() {
		return estado instanceof EstadoSolicitacaoPendente;
	}
	
	/**
	 * Retorna resposta do ponto
	 * de encontro sugerido.
	 * 
	 * @return respostaPontoEncontro
	 */
	public String getRespostaPontoEncontro() {
		return respostaPontoEncontro;
	}
	
	/**
	 * Configura resposta do ponto de encontro
	 * sugerido.
	 * 
	 * @param respostaPontoEncontro
	 */
	public void setRespostaPontoEncontro(String respostaPontoEncontro) {
		this.respostaPontoEncontro = respostaPontoEncontro;
	}

	public Integer getIdCarona() {
		return idCarona;
	}
}
