package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ibm.icu.text.SimpleDateFormat;

import estradasolidaria.ui.server.util.DateUtil;
import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;


/**
 * Classe representante de uma carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class Carona implements Comparable<Carona>, Serializable {
	private static final long serialVersionUID = -467338420076141417L;

	private DateUtil dateUtil;

	private Integer idDonoDaCarona;
	private Integer idCarona;
	private String origem;
	private String destino;
	private Calendar hora;
	private Calendar data;
	private Integer vagas;
	private String pontoEncontro;
	private int LIMITE_VAGAS;

	private int posicaoNaInsercaoNoSistema;

	private boolean municipal = false; // muda para true quando uma carona municipal eh cadastrada
	private String cidade;

	// as solicitacoes sao apagadas apos aceitas pelo dono da carona
	private Map<Integer, Solicitacao> mapIdSolicitacoes = new TreeMap<Integer, Solicitacao>();
	private Iterator<Solicitacao> iteratorIdSolicitacoes = this.mapIdSolicitacoes
			.values().iterator();

	// corresponde ao historico do sistema
	// ------------------------------------------------------------------

	// contem o mapeamento de cada usuario que compareceu a carona para o review
	// q ele faz dela.
	private Map<Integer, EnumCaronaReview> mapCaroneiroReviewDono = new TreeMap<Integer, EnumCaronaReview>();

	// contem o mapeamento de cada usuario presente para o review q o dono da
	// carona faz dele.
	private Map<Integer, EnumCaronaReview> mapDonoReviewCaroneiro = new TreeMap<Integer, EnumCaronaReview>();
	private Map<Integer, Sugestao> mapSugestoesPontoDeEncontro = new TreeMap<Integer, Sugestao>();

	private Integer minimoCaroneiros;
	private boolean expired;

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
			String data, String hora, Integer vagas, Integer ordemParaCaronas) {

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
			Integer ordemParaCaronas) {
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
	 * Construtor de caronas relampago.
	 * 
	 * obs.: note que vagas sera igual
	 * ao minimo de caroneiros aqui, mas
	 * na interface se passa apenas o minimo de
	 * vagas.
	 * 
	 * @param idDonoDaCarona
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param minimoCaroneiros
	 */
	public Carona(Integer idDonoDaCarona, String origem, String destino, String data,
			String hora, Integer vagas, Integer minimoCaroneiros, Integer posicaoNaInsercaoNoSistema) {
		setIdDonoDaCarona(idDonoDaCarona);
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setHora(hora);
		setMinimoCaroneiros(minimoCaroneiros);
		setVagas(vagas);
		setPosicaoNaInsercaoNoSistema(posicaoNaInsercaoNoSistema);
		setExpired(false);
		setIdCarona(hashCode());
	}
	
	/**
	 * Configura estado da carona relampago.
	 * 
	 * @param expired
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	/**
	 * Retorna estado da carona relampago.
	 * 
	 * @return estado da carona
	 */
	public boolean getExpired() {
		return this.expired;
	}

	/**
	 * Configura o minimo de caroneiros para
	 * carona relampago. Se esse minimo de caroneiros
	 * nao for atingido até 48h antes da hora da carona,
	 * o sistema manda um email cancelando a carona.
	 * 
	 * @param minimoCaroneiros
	 */
	private void setMinimoCaroneiros(Integer minimoCaroneiros) {
		if(minimoCaroneiros == null || minimoCaroneiros <= 0)
			throw new IllegalArgumentException("Minimo Caroneiros inválido");
		this.minimoCaroneiros = minimoCaroneiros;
	}
	
	/**
	 * Retorna minimo de caroneiros para essa carona.
	 * 
	 * @return minimo de caroneiros.
	 */
	public Integer getMinimoCaroneiros() {
		return this.minimoCaroneiros;
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
	public Calendar getHora() {
		return this.hora;
	}
	
	/**
	 * Configura hora da carona.
	 * 
	 * @param hora
	 */
	public void setHora(String hora) {
		dateUtil = new DateUtil(new GregorianCalendar());
		if(hora == null || hora.equals(""))
			throw new IllegalArgumentException("Hora inválida");
		else if(dateUtil.validaHora(hora))
			this.hora = dateUtil.getCalendar();
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
		dateUtil = new DateUtil(new GregorianCalendar());
		if(data == null || data.equals(""))
			throw new IllegalArgumentException("Data inválida");
		else if(dateUtil.validaData(data) && dateUtil.validaDataJaPassou())
			this.data = dateUtil.getCalendar();
		else
			throw new IllegalArgumentException("Data inválida");
	}
	
	/**
	 * Modifica o id do usuario dono da carona.
	 * 
	 * @param idDonoDaCarona2
	 */
	public void setIdDonoDaCarona(Integer idDonoDaCarona2) {
		if(idDonoDaCarona2 == null)
			throw new IllegalArgumentException("IdDonoDaCarona inválido");
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
	public Calendar getData() {
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
	 * Modifica o número de vagas da carona. Se o parametro for null ou um
	 * numero negativo.
	 * 
	 * @param vagas
	 */
	public void setVagas(Integer vagas) {
		if (vagas == null || vagas < 0)
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
				+ ((mapCaroneiroReviewDono == null) ? 0 : mapCaroneiroReviewDono
						.hashCode());
		result = prime
				* result
				+ ((mapDonoReviewCaroneiro == null) ? 0
						: mapDonoReviewCaroneiro.hashCode());
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
		if (mapCaroneiroReviewDono == null) {
			if (other.mapCaroneiroReviewDono != null)
				return false;
		} else if (!mapCaroneiroReviewDono.equals(other.mapCaroneiroReviewDono))
			return false;
		if (mapDonoReviewCaroneiro == null) {
			if (other.mapDonoReviewCaroneiro != null)
				return false;
		} else if (!mapDonoReviewCaroneiro
				.equals(other.mapDonoReviewCaroneiro))
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
		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		return this.origem + " para " + this.destino + ", no dia " + formatterData.format(this.data.getTime())
				+ ", as " + formatterHora.format(this.hora.getTime());
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
	 * Retorna esta carona.
	 * 
	 * @return Carona
	 */
	public Carona getCarona() {
		return this;
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
				return false; // achou uma solicitacao identica já feita pelo mesmo usuario
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
				donoDaSolicitacao, EnumTipoSolicitacao.SOLICITACAO_SEM_PONTO_ENCONTRO);
		this.mapIdSolicitacoes.put(s.getIdSolicitacao(), s);
		return s;
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
					donoDaSolicitacao, ponto, EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
			this.mapIdSolicitacoes.put(s.getIdSolicitacao(), s);
			return s;
		} else {
			throw new IllegalArgumentException("Ponto Inválido");
		}
	}

	/**
	 * Retorna mapa de reviews associados a cada usuario que pegou ou nao a
	 * carona.
	 * 
	 * @return mapa
	 */
	public Map<Integer, EnumCaronaReview> getMapDonoReviewCaroneiro() {
		return this.mapDonoReviewCaroneiro;
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
	public void setDonoReviewCaroneiro(Integer idCaroneiro, String review) {
		EnumCaronaReview e = getReview(review);
		this.mapCaroneiroReviewDono.put(idCaroneiro, e);
		
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
		EnumCaronaReview e = getReview(review);
		this.mapDonoReviewCaroneiro.put(idCaroneiro, e);
	}

	/**
	 * Verifica se uma caracteristica(review) é uma das opções: 1.faltou 2.não
	 * faltou 3.segura e tranquila 4.não funcionou
	 * 
	 * @param review
	 * @return true: se existe, false: se não existe @
	 */
	private EnumCaronaReview getReview(String review) {
		List<EnumCaronaReview> reviewsValidos = new LinkedList<EnumCaronaReview>();
		reviewsValidos.add(EnumCaronaReview.FALTOU);
		reviewsValidos.add(EnumCaronaReview.NAO_FALTOU);
		reviewsValidos.add(EnumCaronaReview.SEGURA_E_TRANQUILA);
		reviewsValidos.add(EnumCaronaReview.NAO_FUNCIONOU);

		for(EnumCaronaReview e : reviewsValidos) {
			if(e.getReview().equals(review))
				return e;
		}
		throw new IllegalArgumentException("Opção inválida.");
	}

	/**
	 * Retorna um mapa de id's de usuários(chaves) que compareceram à carona com
	 * suas respectivas caracteristicas(review) dentro desta carona.
	 * 
	 * @return mapIdUsuarioReview
	 */
	public Map<Integer, EnumCaronaReview> getMapCaroneiroReviewDono() {
		return this.mapCaroneiroReviewDono;
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
	 * @throws EstadoSolicitacaoException 
	 * @throws CaronaInexistenteException 
	 */
	public void desistirRequisicao(Integer idSolicitacao) throws EstadoSolicitacaoException, CaronaInexistenteException {
		if(idSolicitacao == null)
			throw new IllegalArgumentException("Id solicitacao inválido");
		this.mapIdSolicitacoes.get(idSolicitacao).cancelar(this);
	}

	/**
	 * Diminui o numero de vagas na carona.
	 * 
	 * @throws Exception
	 */
	public void decrementaNumeroDeVagas() throws IllegalArgumentException {
		if (!((getVagas() - 1) < 0)) {
			setVagas(getVagas() - 1); // decrementa o numero de vagas;
		}

	}

	/**
	 * Aumenta numero de vagas na carona.
	 * 
	 * @throws Exception
	 */
	public void incrementaNumeroDeVagas() throws IllegalArgumentException {
		if (getVagas() < LIMITE_VAGAS) {
			setVagas(getVagas() + 1);
		}

	}

	/**
	 * Muda estado de solicitacao para aceita.
	 * 
	 * @param s
	 *            : solicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * @throws Exception
	 */
	public void aceitarSolicitacao(Solicitacao s) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
		s.aceitar(this);
	}

	/**
	 * Retorna lista de pontos sugeridos para essa carona.
	 *
	 * @return lista de pontos de encontro
	 */
	public List<String> getPontosSugeridos() {
		List<String> pontosSugeridos = new LinkedList<String>();

		for (Iterator<Sugestao> iterator = getMapSugestoesPontoDeEncontro()
				.values().iterator(); iterator.hasNext();) {
			pontosSugeridos.add(iterator.next().getPontoSugerido());
		}

		return pontosSugeridos;
	}
	
	/**
	 * Retorna mapa de sugestoes com pontos de encontro
	 * associadas a essa carona.
	 * 
	 * @return mapa de sugestoes
	 */
	public Map<Integer, Sugestao> getSugestoesPontoDeEncontro() {
		return this.getMapSugestoesPontoDeEncontro();
	}
	
	/**
	 * 
	 * 
	 * @param idSolicitacao
	 * @param resposta
	 * @return
	 */
	public Solicitacao setRespostaSugestaoPontoEncontro(String idSolicitacao, String resposta) {
		Solicitacao s = getMapIdSolicitacao().get(idSolicitacao); 
		if (s != null) {
			s.setRespostaPontoEncontro(resposta);
		}
		return s;
	}

	/**
	 * Retorna mapa de sugestoes de ponto de encontro
	 * para essa carona.
	 * 
	 * @return mapa de sugestoes
	 */
	public Map<Integer, Sugestao> getMapSugestoesPontoDeEncontro() {
		return mapSugestoesPontoDeEncontro;
	}
	
	/**
	 * Atribui numeros ao review feito pelo
	 * dono dessa carona a um usuario identificado
	 * por idCaroneiro.
	 * 
	 * @param idCaroneiro
	 * @return numero correspondente ao review dado
	 */
	public Integer getDonoReviewCaroneiro(Integer idCaroneiro) {
		EnumCaronaReview review = getMapDonoReviewCaroneiro().get(idCaroneiro); 
		if (review == EnumCaronaReview.FALTOU) {
			return 0;
		}
		return 1; // review == EnumCaronaReview.NAO_FALTOU
	}
	
	/**
	 * Atribui numeros ao review feito pelo caroneiro,
	 * que pegou essa carona, ao usuario dono da carona,
	 * que identificado por idDonoDaCarona.
	 * 
	 * @param idDonoDaCarona
	 * @return numero correspondente ao review dado
	 */
	public Integer getCaroneiroReviewDono(Integer idDonoDaCarona) {
		EnumCaronaReview review = getMapCaroneiroReviewDono().get(idDonoDaCarona); 
		if (review == EnumCaronaReview.NAO_FUNCIONOU) {
			return 0;
		}
		return 1; // review == EnumCaronaReview.FUNCIONOU
	}
	
	/**
	 * Retorna lista de emails de usuarios
	 * que serao avisados do cancelamento da
	 * carona relampago, uma vez que o numero
	 * minimo de caroneiros nao foi atingido.
	 * 
	 * @return lista de logins
	 */
	public List<String> getEmailTo() {
		List<String> listaLogins = new SpecialLinkedListBrackets<String>();
		iteratorIdSolicitacoes = this.mapIdSolicitacoes.values().iterator();
		while(iteratorIdSolicitacoes.hasNext()) {
			Solicitacao s = iteratorIdSolicitacoes.next();
			if(s.getEstado().getEnumNomeDoEstadoDaSolicitacao().equals(EnumNomeDoEstadoDaSolicitacao.ACEITA)) {
				listaLogins.add(s.getDonoDaSolicitacao().getLogin());
			}
		}
		Collections.sort(listaLogins);
		return listaLogins;
	}
}
