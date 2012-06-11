package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;

import estradasolidaria.ui.server.util.SenderMail;


/**
 * Classe que representa um usuario do sistema.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = -3288136633613252896L;
	
	private Integer idUsuario;
	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	private List<String> mensagensPerfil = new LinkedList<String>();

	private Map<Integer, Carona> mapIdCaronasOferecidas = new TreeMap<Integer, Carona>();
	private Iterator<Carona> iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas
			.values().iterator();

	// contem dados temporarios (at� a carona acontecer de fato)
	private Map<Integer, Carona> mapIdCaronasPegas = new TreeMap<Integer, Carona>();
	private Iterator<Carona> iteratorCaronasPegas = this.mapIdCaronasPegas
			.values().iterator();

	private Map<Integer, Interesse> mapIdInteresse = new TreeMap<Integer, Interesse>();

	/**
	 * Construtor da classe Usuario.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * 
	 */
	public Usuario(String login, String senha, String nome, String endereco,
			String email) {
		setLogin(login);
		setSenha(senha);

		setIdUsuario(this.hashCode());

		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
	}

	/**
	 * Configura o id do usuario.
	 * 
	 * @param id
	 */
	private void setIdUsuario(Integer id) {
		this.idUsuario = id;
	}

	/**
	 * Retorna o id do usuario.
	 * 
	 * @return idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Retorna o login do usu�rio.
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Configura o login do usu�rio.
	 * 
	 * @param login
	 * 
	 */
	public void setLogin(String login) {
		if (login == null || login.equals(""))
			throw new IllegalArgumentException("Login inválido");
		this.login = login;
	}

	/**
	 * Retorna email do usu�rio.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Configura e-mail do usu�rio.
	 * 
	 * @param email
	 * 
	 */
	public void setEmail(String email) {
		if (email == null || email.equals(""))
			throw new IllegalArgumentException("Email inválido");
		this.email = email;
	}

	/**
	 * Retorna senha do usu�rio.
	 * 
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Configura senha do usu�rio.
	 * 
	 * @param senha
	 * 
	 */
	public void setSenha(String senha) {
		if (senha == null || senha.equals(""))
			throw new IllegalArgumentException("Senha inválida");
		this.senha = senha;
	}

	/**
	 * Retorna nome do usu�rio.
	 * 
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Configura nome do usu�rio.
	 * 
	 * @param nome
	 * 
	 */
	public void setNome(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException("Nome inválido");
		this.nome = nome;
	}

	/**
	 * Retorna endere�o do usu�rio.
	 * 
	 * @return endere�o
	 */
	public String getEndereco() {
		return this.endereco;
	}

	/**
	 * Configura endere�o do usu�rio.
	 * 
	 * @param endereco
	 * 
	 */
	public void setEndereco(String endereco) {
		if (endereco == null || endereco.equals(""))
			throw new IllegalArgumentException("Endereço inválido");
		this.endereco = endereco;
	}

	

	/**
	 * Cria carona e a adiciona em mapIdCaronasOferecidas
	 * 
	 * @param idUsuario
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param ordemParaCaronas
	 * @return carona cadastrada
	 * 
	 */
	public Carona cadastrarCarona(Integer idUsuario, String origem,
			String destino, String data, String hora, Integer vagas,
			int ordemParaCaronas) {

		Carona carona = new Carona(idUsuario, origem, destino, data, hora,
				vagas, ordemParaCaronas);
		this.mapIdCaronasOferecidas.put(carona.getIdCarona(), carona);
		return carona;
	}

	/**
	 * Verifica se senha � v�lida, i.e., confere com a senha do usuario.
	 * 
	 * @param senha
	 * @return true se senha eh valida
	 */
	public boolean validaSenha(String senha) {
		return this.senha.equals(senha);
	}

	/**
	 * Retorna todas as caronas cadastradas de acordo com origem e destino
	 * podendo receber "" como destino ou como origem indicando que devem ser
	 * retornadas todas as caronas com aquela origem ou com aquele destino.
	 * 
	 * @param origem
	 * @param destino
	 * @return lista de caronas
	 */
	public List<Carona> localizarCarona(String origem, String destino) {
		List<Carona> caronas = new LinkedList<Carona>();

		if (origem.equals("") && !destino.equals("")) {
			for (Carona c : this.mapIdCaronasOferecidas.values()) {
				if (c.getDestino().equals(destino)) {
					caronas.add(c);
				}
			}
		} else if (!origem.equals("") && destino.equals("")) {
			for (Carona c : this.mapIdCaronasOferecidas.values()) {
				if (c.getOrigem().equals(origem)) {
					caronas.add(c);
				}
			}
		} else if (!origem.equals("") && !destino.equals("")) {
			for (Carona c : this.mapIdCaronasOferecidas.values()) {
				if (c.getOrigem().equals(origem)
						&& c.getDestino().equals(destino)) {
					caronas.add(c);
				}
			}
		} else {
			for (Carona c : this.mapIdCaronasOferecidas.values()) {
				caronas.add(c);
			}
		}
		return caronas;
	}

	

	/**
	 * Procura caroneiro e adiciona solicitacao com aquele ponto de encontro.
	 * 
	 * @param idCarona
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param pontos
	 * @return idSolicitacao
	 * 
	 */
	public Solicitacao sugerirPontoEncontro(String idCarona, Usuario donoDaCarona,
			Usuario donoDaSolicitacao, String pontos) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c == null)
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		return c.addSolicitacao(c.getOrigem(), c.getDestino(), donoDaCarona,
				donoDaSolicitacao, pontos);
	}

	/**
	 * Procura caroneiro e adiciona solicitacao com aquele ponto de encontro.
	 * 
	 * @param idCarona
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param pontos
	 * @return idSolicitacao
	 * 
	 */
	public Solicitacao solicitarVagaPontoEncontro(Integer idCarona,
			Usuario donoDaCarona, Usuario donoDaSolicitacao, String pontos) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		return c.addSolicitacao(c.getOrigem(), c.getDestino(), donoDaCarona,
				donoDaSolicitacao, pontos);
	}

	/**
	 * Retorna mapa de caronas cadastradas no caroneiro.
	 * 
	 * @return mapa de caronas
	 */
	public Map<Integer, Carona> getMapIdCaronasOferecidas() {
		return this.mapIdCaronasOferecidas;
	}

	/**
	 * Retorna string com o trajeto da carona.
	 * 
	 * @param idCarona
	 * @return trajeto da carona
	 * 
	 */
	public String[] getTrajeto(Integer idCarona) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c == null)
			throw new IllegalArgumentException("Identificador do carona é inválido");
		return c.getTrajeto();
	}

	/**
	 * Retorna string com detalhes da carona.
	 * 
	 * @param idCarona
	 * @return toString da carona
	 */
	public String getCarona(Integer idCarona) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c == null)
			throw new IllegalArgumentException("Identificador do carona é inválido");
		return c.getCarona();
	}

	/**
	 * Torna null objetos manipulados pelo sistema.
	 */
	public void zerarSistema() {
		this.mapIdCaronasOferecidas.clear();
		this.mapIdCaronasPegas.clear();
	}

	/**
	 * Muda estado da solicitacao para respondida e 
	 * seta ponto de encontro da carona para o 
	 * ponto sugerido, SEM COM ISSO DIMINUIR O
	 * NUMERO DE VAGAS NA CARONA.
	 * 
	 * @param idCarona: id da carona
	 * @param idSugestao: id da sugestao
	 * @param pontos: ponto sugerido
	 */
	public void responderSugestaoPontoEncontro(Integer idCarona,
			Integer idSugestao, String pontos) {
		if (pontos.equals(""))
			throw new IllegalArgumentException("Ponto Inválido");
		// Iterator Pattern
		iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			if (c.getIdCarona().equals(idCarona)) {
				c.setSolicitacaoPontoEncontro(idSugestao, pontos);
			}
		}
	}

	/**
	 * Retorna id do dono da solicitacao.
	 * 
	 * @param idSolicitacao
	 * @return
	 */
	public Solicitacao aceitarSolicitacaoPontoEncontro(Integer idSolicitacao) {

		// Iterator Pattern
		iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			// Iterator Pattern
			Iterator<Solicitacao> it = c.getMapIdSolicitacaoComPontoEncontro().values()
					.iterator();
			while (it.hasNext()) {
				Solicitacao s = it.next();
				if (s.getIdSolicitacao().equals(idSolicitacao)) {
					c.setPontoEncontro(s.getPontoEncontro()); // seta ponto de encontro para carona apos aceitar ponto encontro
					c.decrementaNumeroDeVagas();
					return s;
				}
			}
		}
		throw new IllegalArgumentException("Solicitação inexistente");
	}

	/**
	 * Adiciona id-carona no mapa de ids-caronas do gerenciador de dados do
	 * sistema.
	 * 
	 * QUANDO A CARONA EH ADICIONADA, AS CARONAS PEGAS SIGNIFICA QUE O DONO DA
	 * CARONA JAH ACEITOU A SOLICITACAO DO USUARIO SOLICITANTE (CARONEIRO).
	 * 
	 * @param idCarona
	 * @param carona
	 */
	public void adicionarIdCaronaPega(Integer idCarona, Carona c) {
		this.mapIdCaronasPegas.put(idCarona, c);
	}

	/**
	 * Retorna solicitacao aceita.
	 * 
	 * @param idSolicitacao
	 * @return solicitacao
	 * 
	 */
	public Solicitacao aceitarSolicitacao(Integer idSolicitacao) throws IllegalArgumentException {
		// Iterator Pattern
		iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			Iterator<Solicitacao> it = c.getMapIdSolicitacao().values()
					.iterator();
			while (it.hasNext()) {
				Solicitacao s = it.next();
				if (s.getIdSolicitacao().equals(idSolicitacao)) {
					// nao seta pontoEncontro da carona ==> fica null
					c.aceitarSolicitacao(s);
					return s;
				}
			}
		}
		throw new IllegalArgumentException("Solicitação inexistente");
	}

	// pure fabrication
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + getNome() + "]";
	}

@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	/**
	 * Solicita vaga na carona.
	 * 
	 * @param idCarona
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @return idSolicitacao
	 * 
	 */
	public Solicitacao solicitarVaga(Integer idCarona, Usuario donoDaCarona,
			Usuario donoDaSolicitacao) throws IllegalArgumentException {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c != null)
			return c.addSolicitacao(c.getOrigem(), c.getDestino(),
					donoDaCarona, donoDaSolicitacao);
		throw new IllegalArgumentException("Identificador do carona é inválido");
	}

	/**
	 * Procura qual carona possui solicitacao a ser rejeitada e a rejeita, nao
	 * eliminando-a do historico, apendas mudando o seu estado.
	 * 
	 * @param idSolicitacao
	 * 
	 */
	public void rejeitarSolicitacao(Integer idSolicitacao) {
		// Iterator Pattern
		iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			Iterator<Solicitacao> it = c.getMapIdSolicitacao().values()
					.iterator();
			while (it.hasNext()) {
				Solicitacao s = it.next();
				if (s.getIdSolicitacao().equals(idSolicitacao)) {
					s.rejeitar(c);
					break;
				}
			}
		}
	}

	/**
	 * Retorna id de perfil que eh identico a idUsuario.
	 * 
	 * @return idPerfil
	 */
	public Usuario visualizarPerfil() {
		return this;
	}

	/**
	 * Retorna atributo de acordo com par�metro fornecido.
	 * 
	 * @param atributo
	 * @return
	 * 
	 */
	public Object getAtributoPerfil(String atributo) {
		if (atributo == null || atributo.equals(""))
			throw new IllegalArgumentException("Atributo inexistente");

		if (atributo.equals(EnumPerfil.NOME.getNomeAtributo()))
			return getNome();
		else if (atributo.equals(EnumPerfil.ENDERECO.getNomeAtributo()))
			return getEndereco();
		else if (atributo.equals(EnumPerfil.EMAIL.getNomeAtributo()))
			return getEmail();
		else if (atributo.equals(EnumPerfil.HISTORICO_DE_CARONAS
				.getNomeAtributo())) {
			return getHistoricoDeCaronas();
		} else if (atributo.equals(EnumPerfil.HISTORICO_DE_VAGAS_EM_CARONAS
				.getNomeAtributo())) {
			return getHistoricoDeVagasEmCaronas();
		} else if (atributo.equals(EnumPerfil.CARONAS_SEGURAS_E_TRANQUILAS
				.getNomeAtributo())) {
			return getCaronasSegurasETranquilas();
		} else if (atributo.equals(EnumPerfil.CARONAS_QUE_NAO_FUNCIONARAM
				.getNomeAtributo())) {
			return getCaronasQueNaoFuncionaram();
		} else if (atributo.equals(EnumPerfil.FALTAS_EM_VAGAS_DE_CARONAS
				.getNomeAtributo())) {
			return getFaltasEmVagasDeCaronas();
		} else if (atributo.equals(EnumPerfil.PRESENCAS_EM_VAGAS_DE_CARONAS
				.getNomeAtributo())) {
			return getPresencasEmVagasDeCaronas();
		}

		throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Retorna numero de caronas pegas com review "nao faltou".
	 * 
	 * @return numero de presen�as
	 */
	public Integer getPresencasEmVagasDeCaronas() {
		int sum = 0;
		// Iterator Pattern
		iteratorCaronasPegas = this.mapIdCaronasPegas.values().iterator();
		while (iteratorCaronasPegas.hasNext()) {
			Carona c = iteratorCaronasPegas.next();
			Iterator<EnumCaronaReview> it = c.getMapDonoReviewCaroneiro()
					.values().iterator();
			while (it.hasNext()) {
				EnumCaronaReview review = it.next();
				if (review.equals(EnumCaronaReview.NAO_FALTOU)) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Retorna numero de caronas pegas com review "faltou".
	 * 
	 * @return numero de faltas
	 */
	public Integer getFaltasEmVagasDeCaronas() {
		int sum = 0;

		// Iterator Pattern
		iteratorCaronasPegas = this.mapIdCaronasPegas.values().iterator();
		while (iteratorCaronasPegas.hasNext()) {
			Carona c = iteratorCaronasPegas.next();
			Iterator<EnumCaronaReview> it = c.getMapDonoReviewCaroneiro()
					.values().iterator();
			while (it.hasNext()) {
				EnumCaronaReview review = it.next();
				if (review.equals(EnumCaronaReview.FALTOU)) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Retorna numero de caronas oferecidas com review "nao funcionou".
	 * 
	 * @return numero de caronas que nao funcionaram
	 */
	public Integer getCaronasQueNaoFuncionaram() {
		int sum = 0;
		// Iterator Pattern
		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			Iterator<EnumCaronaReview> it = c.getMapCaroneiroReviewDono().values().iterator();
			while (it.hasNext()) {
				EnumCaronaReview review = it.next();
				if (review.equals(EnumCaronaReview.NAO_FUNCIONOU)) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Retorna numero de caronas oferecidas com review "segura e tranquila".
	 * 
	 * @return numero de caronas seguras
	 */
	public Integer getCaronasSegurasETranquilas() {
		int sum = 0;
		// Iterator Pattern
		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			Iterator<EnumCaronaReview> it = c.getMapCaroneiroReviewDono().values().iterator();
			while (it.hasNext()) {
				EnumCaronaReview review = it.next();
				if (review.equals(EnumCaronaReview.SEGURA_E_TRANQUILA)) {
					sum++;
				}
			}
		}
		return sum;
	}

	 /**
     * Retorna o historico de caronas pegas.
     *  
     * @return string do historico
     */
    public List<Carona> getHistoricoDeVagasEmCaronas() {
        List<Carona> caronasPegas = new LinkedList<Carona>(); 
        caronasPegas.addAll(mapIdCaronasPegas.values());
        Collections.sort(caronasPegas);
        return caronasPegas;
    }

	/**
	 * Historico de caronas oferecidas em forma de string.
	 * 
	 * @return string do historico
	 */
	public List<Carona> getHistoricoDeCaronas() {
		List<Carona> caronasOferecidas = new LinkedList<Carona>(); 
        caronasOferecidas.addAll(mapIdCaronasOferecidas.values());
        Collections.sort(caronasOferecidas);
        return caronasOferecidas;
	}

	/**
	 * Seta se usuario esteve na carona, o dono da carona eh quem faz esse
	 * review.
	 * 
	 * @param idUsuario
	 * @param idCarona
	 * @param loginCaroneiro
	 * 
	 */
	public void reviewVagaEmCarona(Integer idCarona, Integer idCaroneiro,
			String review) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c == null)
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		c.setReviewVagaEmCarona(idCaroneiro, review);
	}

	/**
	 * Seta o review do caroneiro em relacao a carona cadastrada pelo dono da
	 * carona. Insere no mapIdUsuarioReview o id do caroneiro junto com o review
	 * feito por ele.
	 * 
	 * @param idCaroneiro
	 * @param idCarona
	 * @param review
	 * 
	 */
	public void setReviewCarona(Integer idCaroneiro, Integer idCarona,
			String review) {
		Carona c = mapIdCaronasPegas.get(idCarona);
		if (c == null)
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		c.setDonoReviewCaroneiro(idCaroneiro, review);
	}

	/**
	 * Retorna mapa de caronas pegas.
	 * 
	 * @return mapa de caronas
	 */
	public Map<Integer, Carona> getMapIdCaronasPegas() {
		return this.mapIdCaronasPegas;
	}

	/**
	 * Localizar caronas adaptado para caronas municipais.
	 * 
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @return lista das caronas municipais localizadas
	 * 
	 */
	public List<Carona> localizarCaronaMunicipal(String origem, String destino,
			String cidade) {
		List<Carona> caronas = new LinkedList<Carona>();

		if (cidade == null || cidade.equals(""))
			throw new IllegalArgumentException("Cidade inexistente");

		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();

		if (origem.equals("") && !destino.equals("")) {

			// Iterator Pattern
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				if (c.getDestino().equals(destino) && c.isMunicipal()) {
					caronas.add(c);
				}
			}
		} else if (!origem.equals("") && destino.equals("")) {
			// Iterator Pattern
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				if (c.getOrigem().equals(origem) && c.isMunicipal()) {
					caronas.add(c);
				}
			}
		} else if (!origem.equals("") && !destino.equals("")) {
			// Iterator Pattern
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				if (c.getOrigem().equals(origem)
						&& c.getDestino().equals(destino) && c.isMunicipal()) {
					caronas.add(c);
				}
			}
		} else {
			// Iterator Pattern
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				caronas.add(c);
			}
		}

		return caronas;
	}

	/**
	 * Localiza caronasMunicipais contidas na cidade indicada.
	 * 
	 * @param cidade
	 * @return lista das caronas municipais localizadas
	 * 
	 */
	public List<Carona> localizarCaronaMunicipal(String cidade) {
		List<Carona> caronas = new LinkedList<Carona>();

		if (cidade == null || cidade.equals(""))
			throw new IllegalArgumentException("Cidade inexistente");

		// Iterator Pattern
		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			if (c.isMunicipal() && c.getCidade().equals(cidade)) {
				caronas.add(c);
			}
		}

		return caronas;
	}

	/**
	 * Cria uma caronaMunicipal e seta o atributo municipal de Carona para true
	 * no construtor de CaronaMunicipal
	 * 
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param ordemParaCaronas
	 * 
	 * @return carona municipal cadastrada
	 * 
	 * @see CaronaMunicipal
	 */
	public Carona cadastrarCaronaMunicipal(String origem, String destino,
			String cidade, String data, String hora, Integer vagas,
			int ordemParaCaronas) {
		Carona cm = new Carona(getIdUsuario(), origem, destino, data, hora,
				vagas, cidade, ordemParaCaronas);
		mapIdCaronasOferecidas.put(cm.getIdCarona(), cm);
		return cm;
	}

	/**
	 * Retorna carona localizada a partir do indice.
	 * 
	 * @param indexCarona
	 * @return id carona
	 * 
	 */
	public Carona getCaronaUsuario(int indexCarona) {
		if (indexCarona < 0) {
			throw new IllegalArgumentException("Indice de carona inválido");
		}
		List<Carona> listaCaronas = new LinkedList<Carona>();

		// Iterator Pattern
		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			listaCaronas.add(c);
		}

		Collections.sort(listaCaronas);
		return listaCaronas.get(indexCarona - 1);
	}

	/**
	 * Retorna lista de ids das caronas cadastradas pelo usuario.
	 * 
	 * @return lista ids caronas
	 */
	public List<Carona> getTodasCaronasUsuario() {
		List<Carona> listaCaronasCadastradas = new LinkedList<Carona>();

		// Iterator Pattern
		iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values()
				.iterator();
		while (iteratorIdCaronasOferecidas.hasNext()) {
			Carona c = iteratorIdCaronasOferecidas.next();
			listaCaronasCadastradas.add(c);
		}

		Collections.sort(listaCaronasCadastradas);

		return listaCaronasCadastradas;
	}

	/**
	 * Retorna lista de ids de solicitacoes confirmadas (aceitas) para uma
	 * carona oferecida pelo usuario dono da carona.
	 * 
	 * @param idCarona
	 * @return lista ids solicitacoes
	 */
	public List<Solicitacao> getSolicitacoesConfirmadas(Integer idCarona) {
		List<Solicitacao> listaSolicitacoesConfirmadas = new LinkedList<Solicitacao>();
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);

		// Iterator Pattern
		Iterator<Solicitacao> it = c.getMapIdSolicitacao().values().iterator();
		while (it.hasNext()) {
			Solicitacao s = it.next();
			if (s.isAceita()) // Utilizando instanceOf
				listaSolicitacoesConfirmadas.add(s);
		}
		return listaSolicitacoesConfirmadas;
	}

	/**
	 * Retorna lista de ids de solicitacoes pendentes numa carona oferecidas
	 * pelo usuario dono da carona.
	 * 
	 * @param idCarona
	 * @return lista ids solicitacoes
	 */
	public List<Solicitacao> getSolicitacoesPendentes(Integer idCarona) {
		List<Solicitacao> listaSolicitacoesPendentes = new LinkedList<Solicitacao>();
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		// Iterator Pattern
		Iterator<Solicitacao> it = c.getMapIdSolicitacao().values().iterator();
		while (it.hasNext()) {
			Solicitacao s = it.next();
			if (s.isPendente()) // Utilizando instanceOf
				listaSolicitacoesPendentes.add(s);
		}
		return listaSolicitacoesPendentes;
	}

	/**
	 * Retorna string com o ponto sugerido para idCarona.
	 * 
	 * @param idCarona
	 * @return string com pontos sugeridos
	 */
	public List<String> getPontosSugeridos(Integer idCarona) {
		List<String> pontosSugeridos = new LinkedList<String>();
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);

		// Iterator Pattern
		Iterator<Solicitacao> it = c.getMapIdSolicitacao().values().iterator();
		while (it.hasNext()) {
			Solicitacao s = it.next();
			if (s.getPontoEncontro() != null)
				pontosSugeridos.add(s.getPontoEncontro());
		}
		return pontosSugeridos;
	}

	/**
	 * Retorna ponto de encontro atual para a carona.
	 * 
	 * @param idCarona
	 * @return string com ponto de encontro
	 */
	public String getPontosEncontro(Integer idCarona) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		if (c.getPontoEncontro() != null)
			return "[" + c.getPontoEncontro() + "]";
		else
			return "[]";
	}

	/**
	 * Usuario solicitante desiste da requisicao feita.
	 * 
	 * obs.: como a solicitacao ja foi aceita, a carona estah na lista de
	 * caronas pegas pelo solicitante.
	 * 
	 * @param idCarona
	 * @param idSolicitacao
	 */
	public void desistirRequisicao(Integer idCarona, Integer idSolicitacao) {
		Carona c = this.mapIdCaronasPegas.get(idCarona);
		c.desistirRequisicao(idSolicitacao);
	}

	/**
	 * A listaCaronasCorrespondentes ja eh passada com a formatacao para
	 * exibicao no perfil do usuario interessado, uma vez que passar a carona
	 * toda sairia muito caro.
	 * 
	 * @param interesse
	 * @param listaCaronas
	 *            : lista de caronas resultado da busca feita no sistema
	 */
	public void atualizaPerfilUsuarioInteressado(Carona carona,
			String emailDonoDaCarona) {
		notificaPerfil(carona, emailDonoDaCarona);
	}

	/**
	 * Adiciona mensagem ao perfil do usuario, indicando a nova carona como
	 * correspondente ao interesse dele.
	 * 
	 * AQUI A CARONA PASSADA COMO ARGUMENTO JAH ESTAH CONFIRMADA COMO
	 * INTERESSANTE A ESTE USUARIO.
	 * 
	 * @param carona
	 * @param emailDonoDaCarona
	 */
	public void notificaPerfil(Carona carona, String emailDonoDaCarona) {
		mensagensPerfil.add(new String("Carona cadastrada no dia "
				+ carona.getData() + ", " + "às " + carona.getHora()
				+ " de acordo com os seus interesses registrados. "
				+ "Entrar em contato com " + emailDonoDaCarona));
	}

	/**
	 * Retorna lista de mensagens do perfil do usuario.
	 * 
	 * @return lista de mensagens
	 */
	public List<String> getMensagens() {
		return mensagensPerfil;
	}

	/**
	 * Adiciona interesse ao mapa de interesses
	 * do usuario (this).
	 * 
	 * @param interesse
	 */
	public void addInteresse(Interesse interesse) {
		mapIdInteresse.put(interesse.getIdInteresse(), interesse);
	}

	/**
	 * Retorna string com resumo das mensagens
	 * presentes no perfil do usuario.
	 * 
	 * @return string com mensagens
	 */
	public List<String> verificarMensagensPerfil() {
		List<String> listaMensagens = new LinkedList<String>(); 
		Iterator<String> it = getMensagens().iterator();
		while (it.hasNext()) {
			listaMensagens.add(it.next());
			
		}
		return listaMensagens;
	}
	
	/**
	 * Retorna mapa de id-interesses do usuario.
	 * 
	 * @return mapIdInteresses
	 */
	public Map<Integer, Interesse> getMapIdInteresses() {
		return this.mapIdInteresse;
	}

	/**
	 * Envia email para destino com a mensagem <message>.
	 * 
	 * @param destino: email de destino
	 * @param message: corpo da mensagem
	 * @return true se email foi enviado com sucesso
	 * @throws MessagingException
	 */
	public boolean enviarEmail(String destino, String message)
			throws MessagingException {
		SenderMail.sendMail(getEmail(), destino, message);
		return true;
	}
}
