package estradasolidaria.ui.server.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import estradasolidaria.ui.server.util.DateUtil;


/**
 * Classe representante de uma carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class Carona implements Comparable<Carona> {
	private DateUtil dateUtil = new DateUtil();

	private Integer idDonoDaCarona;
	private Integer idCarona;
	private String origem;
	private String destino;
	private String hora;
	private String data;
	private Integer vagas;
	private String pontoEncontro;
	private int LIMITE_VAGAS;

	private int posicaoNaInsercaoNoSistema;

	private boolean municipal = false; // muda para true quando uma
										// carona municipal eh cadastrada
	private String cidade;

	// as solicitacoes sao apagadas apos aceitas pelo dono da carona
	private Map<Integer, Solicitacao> mapIdSolicitacoes = new TreeMap<Integer, Solicitacao>();
	private Iterator<Solicitacao> iteratorIdSolicitacoes = this.mapIdSolicitacoes
			.values().iterator();

	// corresponde ao historico do sistema
	// ------------------------------------------------------------------

	// contem o mapeamento de cada usuario que compareceu a carona para o review
	// q ele faz dela.
	private Map<Integer, String> mapIdUsuarioReview = new TreeMap<Integer, String>();

	// contem o mapeamento de cada usuario presente para o review q o dono da
	// carona faz dele.
	private Map<Integer, String> mapIdUsuarioReviewVagaEmCarona = new TreeMap<Integer, String>();
	private Map<Integer, Sugestao> mapSugestoesPontoDeEncontro = new TreeMap<Integer, Sugestao>();

	// ------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * Construtor da classe Carona.
	 * 
	 * @param idDonoDaCarona
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param ordemParaCaronas
	 */
	public Carona(Integer idDonoDaCarona, String origem, String destino,
			String data, String hora, Integer vagas, int ordemParaCaronas) {

		setIdDonoDaCarona(idDonoDaCarona);
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setHora(hora);
		setVagas(vagas);
		setLimiteVagas(vagas);
		setPosicaoNaInsercaoNoSistema(ordemParaCaronas);
		setIdCarona(this.hashCode());
	}

	/**
	 * Construtor para carona municipal.
	 * 
	 * @param idDonoDaCarona
	 * @param origem2
	 * @param destino2
	 * @param data2
	 * @param hora2
	 * @param vagas
	 * @param cidade
	 * @param ordemParaCaronas
	 */
	public Carona(Integer idDonoDaCarona, String origem2, String destino2,
			String data2, String hora2, Integer vagas, String cidade,
			int ordemParaCaronas) {
		setIdDonoDaCarona(idDonoDaCarona);
		setOrigem(origem2);
		setDestino(destino2);
		setData(data2);
		setHora(hora2);
		setVagas(vagas);
		setLimiteVagas(vagas);
		setPosicaoNaInsercaoNoSistema(ordemParaCaronas);
		setCidade(cidade);
		setMunicipal(true);
		
		setIdCarona(this.hashCode());
	}

	/**
	 * Configura limite de vagas para a carona.
	 * 
	 * @param limite
	 */
	private void setLimiteVagas(Integer limite) {
		if (limite >= 0)
			this.LIMITE_VAGAS = limite;
	}

	/**
	 * Retorna o id da carona.
	 * 
	 * @return idCarona
	 */
	public Integer getIdCarona() {
		return idCarona;
	}

	/**
	 * Modifica o id da carona.
	 * 
	 * @param id
	 */
	public void setIdCarona(Integer id) {
		this.idCarona = id;
	}

	/**
	 * Retorna a hora da carona.
	 * 
	 * @return hora
	 */
	public String getHora() {
		return this.hora;
	}
	
	/**
	 * Configura hora da carona.
	 * 
	 * @param hora
	 */
	public void setHora(String hora) {
		if(hora == null || hora.equals(""))
			throw new IllegalArgumentException("Hora inválida");
		else if(dateUtil.validaHora(hora))
			this.hora = hora;
		else
			throw new IllegalArgumentException("Hora inválida");
	}

	/**
	 * Modifica a data da carona. Se o parametro for null ou for igual a ""
	 * ou não for uma data no formato dd/mm/aaaa, é lançada uma exceção.
	 * 
	 * @param data
	 */
	public void setData(String data) {
		if(data == null || data.equals(""))
			throw new IllegalArgumentException("Data inválida");
		else if(dateUtil.validaData(data))
			this.data = data;
		else
			throw new IllegalArgumentException("Data inválida");
	}
	
	/**
	 * Modifica o id do usuario dono da carona.
	 * 
	 * @param idDonoDaCarona2
	 */
	public void setIdDonoDaCarona(Integer idDonoDaCarona2) {
		this.idDonoDaCarona = idDonoDaCarona2;
	}

	/**
	 * Retorna a origem da carona.
	 * 
	 * @return origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Modifica a origem da carona. Se o parametro for null ou uma palavra
	 * vazia, então é lançada uma exceção.
	 * 
	 * @param origem
	 */
	public void setOrigem(String origem) {
		if (origem == null || origem.equals(""))
			throw new IllegalArgumentException("Origem inválida");
		this.origem = origem;
	}

	/**
	 * Retorna o destino da carona.
	 * 
	 * @return destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Modifica o destino da carona. Se o parametro for null ou uma palavra
	 * vazia, então é lançada uma exceção.
	 * 
	 * @param destino
	 */
	public void setDestino(String destino) {
		if (destino == null || destino.equals(""))
			throw new IllegalArgumentException("Destino inválido");
		this.destino = destino;
	}

	/**
	 * Retorna a data da carona.
	 * 
	 * @return data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Retorna o número de vagas da carona.
	 * 
	 * @return vagas
	 */
	public Integer getVagas() {
		return vagas;
	}

	/**
	 * Modifica o número de vagas da carona. Se o parametro for null, ou uma
	 * palavra vazia, ou não for um caractere númérico, então é lançada uma
	 * exceção.
	 * 
	 * @param vagas
	 */
	public void setVagas(Integer vagas) {
		if (vagas == null)
			throw new IllegalArgumentException("Vaga inválida");

		if (vagas < 0)
			throw new IllegalArgumentException("Vaga inválida");

		this.vagas = vagas;
	}

	/**
	 * Retorna o trajeto da carona no formato: String[] {origem, destino}.
	 * 
	 * @return String[] {origem, destino}
	 */
	public String[] getTrajeto() {
		return new String[] { this.origem, this.destino };
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + LIMITE_VAGAS;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((dateUtil == null) ? 0 : dateUtil.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result
				+ ((idCarona == null) ? 0 : idCarona.hashCode());
		result = prime * result
				+ ((idDonoDaCarona == null) ? 0 : idDonoDaCarona.hashCode());
		result = prime
				* result
				+ ((iteratorIdSolicitacoes == null) ? 0
						: iteratorIdSolicitacoes.hashCode());
		result = prime
				* result
				+ ((mapIdSolicitacoes == null) ? 0 : mapIdSolicitacoes
						.hashCode());
		result = prime
				* result
				+ ((mapIdUsuarioReview == null) ? 0 : mapIdUsuarioReview
						.hashCode());
		result = prime
				* result
				+ ((mapIdUsuarioReviewVagaEmCarona == null) ? 0
						: mapIdUsuarioReviewVagaEmCarona.hashCode());
		result = prime
				* result
				+ ((mapSugestoesPontoDeEncontro == null) ? 0
						: mapSugestoesPontoDeEncontro.hashCode());
		result = prime * result + (municipal ? 1231 : 1237);
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result
				+ ((pontoEncontro == null) ? 0 : pontoEncontro.hashCode());
		result = prime * result + posicaoNaInsercaoNoSistema;
		result = prime * result + ((vagas == null) ? 0 : vagas.hashCode());
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
		Carona other = (Carona) obj;
		if (LIMITE_VAGAS != other.LIMITE_VAGAS)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (dateUtil == null) {
			if (other.dateUtil != null)
				return false;
		} else if (!dateUtil.equals(other.dateUtil))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (idCarona == null) {
			if (other.idCarona != null)
				return false;
		} else if (!idCarona.equals(other.idCarona))
			return false;
		if (idDonoDaCarona == null) {
			if (other.idDonoDaCarona != null)
				return false;
		} else if (!idDonoDaCarona.equals(other.idDonoDaCarona))
			return false;
		if (iteratorIdSolicitacoes == null) {
			if (other.iteratorIdSolicitacoes != null)
				return false;
		} else if (!iteratorIdSolicitacoes.equals(other.iteratorIdSolicitacoes))
			return false;
		if (mapIdSolicitacoes == null) {
			if (other.mapIdSolicitacoes != null)
				return false;
		} else if (!mapIdSolicitacoes.equals(other.mapIdSolicitacoes))
			return false;
		if (mapIdUsuarioReview == null) {
			if (other.mapIdUsuarioReview != null)
				return false;
		} else if (!mapIdUsuarioReview.equals(other.mapIdUsuarioReview))
			return false;
		if (mapIdUsuarioReviewVagaEmCarona == null) {
			if (other.mapIdUsuarioReviewVagaEmCarona != null)
				return false;
		} else if (!mapIdUsuarioReviewVagaEmCarona
				.equals(other.mapIdUsuarioReviewVagaEmCarona))
			return false;
		if (mapSugestoesPontoDeEncontro == null) {
			if (other.mapSugestoesPontoDeEncontro != null)
				return false;
		} else if (!mapSugestoesPontoDeEncontro
				.equals(other.mapSugestoesPontoDeEncontro))
			return false;
		if (municipal != other.municipal)
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (pontoEncontro == null) {
			if (other.pontoEncontro != null)
				return false;
		} else if (!pontoEncontro.equals(other.pontoEncontro))
			return false;
		if (posicaoNaInsercaoNoSistema != other.posicaoNaInsercaoNoSistema)
			return false;
		if (vagas == null) {
			if (other.vagas != null)
				return false;
		} else if (!vagas.equals(other.vagas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.origem + " para " + this.destino + ", no dia " + this.data
				+ ", as " + this.hora;
	}

	/**
	 * Retorna o id da sugestao de ponto de encontro adicionada.
	 * 
	 * @param idDonoDaCarona
	 * @param idDonoDaSolicitacao
	 * @param ponto: ponto de encontro
	 * @return id da sugestao
	 */
	public Sugestao addSugestaoPontoEncontro(String ponto){
		if (ponto == null || ponto.equals("")) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
		Sugestao s = new Sugestao(ponto);
		mapSugestoesPontoDeEncontro.put(s.getIdSugestao(), s);
		return s;
	}

	

	/**
	 * Retorna o ponto de encontro da carona.
	 * 
	 * @return pontoEncontro
	 */
	public String getPontoEncontro() {
		return this.pontoEncontro;
	}

	/**
	 * Modifica o ponto de encontro da carona.
	 * 
	 * @param ponto
	 */
	public void setPontoEncontro(String ponto) {
		this.pontoEncontro = ponto;
	}

	/**
	 * Retorna a carona no formato: origem - destino
	 * 
	 * @return "origem - destino"
	 */
	public String getCarona() {
		return this.toString();
	}

	/**
	 * Retorna o id do dono da carona.
	 * 
	 * @return idDonoDaCarona
	 */
	public Integer getIdDonoDaCarona() {
		return this.idDonoDaCarona;
	}

	/**
	 * Retorna o id da solicitacao adicionada.
	 * 
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param ponto
	 *            : ponto de encontro
	 * @return id da solicitacao
	 */
	public Solicitacao addSolicitacao(String origem, String destino,
			Usuario donoDaCarona, Usuario donoDaSolicitacao, String ponto) {
		if (validaPontoEncontro(donoDaSolicitacao, ponto)) {
			Solicitacao s = new Solicitacao(getIdCarona(), origem, destino, donoDaCarona,
					donoDaSolicitacao, ponto);
			this.mapIdSolicitacoes.put(s.getIdSolicitacao(), s);
			return s;
		} else {
			throw new IllegalArgumentException("Ponto Inválido");
		}
	}

	/**
	 * Verifica se usuario já não fez uma solicitação idêntica.
	 * 
	 * @param donoDaSolicitacao
	 * @param ponto
	 * @return true: não fez, false: já fez.
	 */
	private boolean validaPontoEncontro(Usuario donoDaSolicitacao, String ponto) {
		if (ponto == null || ponto.equals("")
				|| ponto.equals(this.pontoEncontro))
			throw new IllegalArgumentException("Ponto Inválido");
		// Iterator Pattern
		iteratorIdSolicitacoes = this.mapIdSolicitacoes.values().iterator();
		while (iteratorIdSolicitacoes.hasNext()) {
			Solicitacao s = iteratorIdSolicitacoes.next();
			if (s.getPontoEncontro().equals(ponto)
					&& s.getDonoDaSolicitacao().equals(donoDaSolicitacao)) {
				return false; // achou uma solicitacao identica já feita pelo
								// mesmo usuario
			}
		}
		return true;
	}

	/**
	 * Retorna um mapa de id's de solicitações da carona pendentes.
	 * 
	 * @return mapIdSolicitacoesPendentes
	 */
	public Map<Integer, Solicitacao> getMapIdSolicitacao() {
		return this.mapIdSolicitacoes;
	}

	/**
	 * Retorna o atributo de uma solicitação.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return atributoSolicitacao @
	 */
	public Object getAtributoSolicitacao(Integer idSolicitacao, String atributo) {
		Solicitacao s = this.mapIdSolicitacoes.get(idSolicitacao);
		if (s == null)
			throw new IllegalArgumentException("Solicitação inexistente");
		return s.getAtributo(atributo);
	}

	/**
	 * Modifica o ponto de encontro de uma solicitação.
	 * 
	 * @param idSugestao
	 * @param ponto
	 * 
	 */
	public void setSolicitacaoPontoEncontro(Integer idSugestao, String ponto) {
		if (ponto == null)
			throw new IllegalArgumentException("Ponto Inválido");
		if (ponto.equals(""))
			throw new IllegalArgumentException("Ponto Inválido");

		// Iterator Pattern
		iteratorIdSolicitacoes = this.mapIdSolicitacoes.values().iterator();
		while (iteratorIdSolicitacoes.hasNext()) {
			Solicitacao s = iteratorIdSolicitacoes.next();
			if (s.getIdSolicitacao().equals(idSugestao)) {
				s.setPontoEncontroCaronaSolicitacao(ponto);
				break;
			}
		}
	}

	/**
	 * Adiciona uma solicitação na carona.
	 * 
	 * @param origem2
	 * @param destino2
	 * @param donoDaCarona2
	 * @param donoDaSolicitacao
	 * @return idSolicitacao
	 * @throws Exception
	 */
	public Solicitacao addSolicitacao(String origem2, String destino2,
			Usuario donoDaCarona2, Usuario donoDaSolicitacao)
			throws IllegalArgumentException {
		Solicitacao s = new Solicitacao(this.getIdCarona(), origem2, destino2, donoDaCarona2,
				donoDaSolicitacao);
		aceitaVaga();
		this.mapIdSolicitacoes.put(s.getIdSolicitacao(), s);
		return s;
	}

	/**
	 * Retorna mapa de reviews associados a cada usuario que pegou ou nao a
	 * carona.
	 * 
	 * @return mapa
	 */
	public Map<Integer, String> getMapIdUsuarioReviewVagaEmCarona() {
		return this.mapIdUsuarioReviewVagaEmCarona;
	}

	/**
	 * Verifica se um caroneiro foi aceito pelo dono desta carona.
	 * 
	 * NAO VERIFICA SE CARONEIRO ESTEVE PRESENTE OU NAO NA CARONA, APENAS VALIDA
	 * O REVIEW.
	 * 
	 * @param idCaroneiro
	 * @param review
	 * 
	 */
	public void setReviewCarona(Integer idCaroneiro, String review) {
		if (validaReview(review)) {
			this.mapIdUsuarioReview.put(idCaroneiro, review);
		} else
			throw new IllegalArgumentException("Opção inválida.");
	}

	/**
	 * Atribui uma caracteristica ao caroneiro depois da carona ocorrer.
	 * Caracteristicas possíveis: 1.faltou 2.não faltou 3.segura e tranquila
	 * 4.não funcionou
	 * 
	 * @param idCaroneiro
	 * @param review
	 * 
	 */
	public void setReviewVagaEmCarona(Integer idCaroneiro, String review) {
		if (validaReview(review))
			this.mapIdUsuarioReviewVagaEmCarona.put(idCaroneiro, review);
	}

	/**
	 * Verifica se uma caracteristica(review) é uma das opções: 1.faltou 2.não
	 * faltou 3.segura e tranquila 4.não funcionou
	 * 
	 * @param review
	 * @return true: se existe, false: se não existe @
	 */
	private boolean validaReview(String review) {
		List<String> reviewsValidos = new LinkedList<String>();
		reviewsValidos.add(EnumCaronaReview.FALTOU.getReview());
		reviewsValidos.add(EnumCaronaReview.NAO_FALTOU.getReview());
		reviewsValidos.add(EnumCaronaReview.SEGURA_E_TRANQUILA.getReview());
		reviewsValidos.add(EnumCaronaReview.NAO_FUNCIONOU.getReview());

		if (!reviewsValidos.contains(review)) {
			throw new IllegalArgumentException("Opção inválida.");
		}
		return true;
	}

	/**
	 * Retorna um mapa de id's de usuários(chaves) que compareceram à carona com
	 * suas respectivas caracteristicas(review) dentro desta carona.
	 * 
	 * @return mapIdUsuarioReview
	 */
	public Map<Integer, String> getMapIdUsuarioReview() {
		return this.mapIdUsuarioReview;
	}

	/**
	 * Verifica se esta carona é uma carona municipal ou não.
	 * 
	 * @return true: se for municipal, false: se não for municipal
	 */
	public boolean isMunicipal() {
		return municipal;
	}

	/**
	 * Modifica o tipo da carona(municipal ou não).
	 * 
	 * @param municipal
	 */
	protected void setMunicipal(boolean municipal) {
		this.municipal = municipal;
	}

	/**
	 * Retorna cidade a qual pertence a carona. É usado em caronas municipais.
	 * 
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Modifica a cidade a qual a carona pertence. Eu usado em caronaMunicipal.
	 * Se a carona nao for municipal seu valor eh null.
	 * 
	 * @param cidade
	 * 
	 */
	public void setCidade(String cidade) {
		if (cidade == null || cidade.equals(""))
			throw new IllegalArgumentException("Cidade inexistente");
		this.cidade = cidade;
	}

	/**
	 * Compara esta carona com outra carona.
	 * 
	 * @return comparacao
	 */
	public int compareTo(Carona carona) {
		return this.getPosicaoNaInsercaoNoSistema()
				- carona.getPosicaoNaInsercaoNoSistema();
	}

	/**
	 * Retorna a posição em que esta carona foi inserida no sistema.
	 * 
	 * @return posicaoNaInsercaoNoSistema
	 */
	public int getPosicaoNaInsercaoNoSistema() {
		return posicaoNaInsercaoNoSistema;
	}

	/**
	 * Modifica a posição em que esta carona foi inserida no sistema.
	 * 
	 * @param posicaoNaInsercaoNoSistema
	 */
	public void setPosicaoNaInsercaoNoSistema(int posicaoNaInsercaoNoSistema) {
		this.posicaoNaInsercaoNoSistema = posicaoNaInsercaoNoSistema;
	}

	/**
	 * Muda estado da solicitacao para cancelada.
	 * 
	 * @param idSolicitacao
	 */
	public void desistirRequisicao(Integer idSolicitacao) {
		this.mapIdSolicitacoes.get(idSolicitacao).cancelar();
	}

	/**
	 * Diminui o numero de vagas na carona.
	 * 
	 * @throws Exception
	 */
	public void aceitaVaga() throws IllegalArgumentException {
		if (!((getVagas() - 1) < 0)) {
			setVagas(getVagas() - 1); // decrementa o numero de vagas;
		}

	}

	/**
	 * Aumenta numero de vagas na carona.
	 * 
	 * @throws Exception
	 */
	public void aumentaVaga() throws IllegalArgumentException {
		if (getVagas() < LIMITE_VAGAS) {
			setVagas(getVagas() + 1);
		}

	}

	/**
	 * Muda estado de solicitacao para aceita.
	 * 
	 * @param s
	 *            : solicitacao
	 * @throws Exception
	 */
	public void aceitarSolicitacao(Solicitacao s) throws IllegalArgumentException {
		s.aceitar(this);
	}

	public List<String> getPontosSugeridos() {
		List<String> pontosSugeridos = new LinkedList<String>();

		for (Iterator<Sugestao> iterator = getMapSugestoesPontoDeEncontro()
				.values().iterator(); iterator.hasNext();) {
			pontosSugeridos.add(iterator.next().getPontoSugerido());
		}

		return pontosSugeridos;
	}
	
	public Map<Integer, Sugestao> getSugestoesPontoDeEncontro() {
		return this.getMapSugestoesPontoDeEncontro();
	}

	public Solicitacao responderSugestaoPontoEncontro(String idSolicitacao, String resposta) {
		Solicitacao s = getMapIdSolicitacao().get(idSolicitacao); 
		if (s != null) {
			s.setRespostaPontoEncontro(resposta);
		}
		return s;
	}

	public Map<Integer, Sugestao> getMapSugestoesPontoDeEncontro() {
		return mapSugestoesPontoDeEncontro;
	}
}