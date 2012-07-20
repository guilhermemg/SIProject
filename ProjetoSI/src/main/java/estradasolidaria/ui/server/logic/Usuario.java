package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class Usuario implements Serializable, Comparable<Usuario> {
	private static final long serialVersionUID = -3288136633613252896L;
	
	private Integer idUsuario;
	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;
	
	private Integer pontuacao;
	
	private List<String> mensagensPerfil = Collections.synchronizedList(new LinkedList<String>());
	private Lock lockMensagensPerfil = new ReentrantLock();
	
	private List<Mensagem> listaDeMensagens = Collections.synchronizedList(new LinkedList<Mensagem>());
	private Lock lockListaDeMensagens = new ReentrantLock();
	
	private Map<Integer, Carona> mapIdCaronasOferecidas = Collections.synchronizedMap(new TreeMap<Integer, Carona>());
	private Iterator<Carona> iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
	private Lock lockMapIdCaronasOferecidas = new ReentrantLock();

	// contem dados temporarios (at� a carona acontecer de fato)
	private Map<Integer, Carona> mapIdCaronasPegas = Collections.synchronizedMap(new TreeMap<Integer, Carona>());
	private Iterator<Carona> iteratorIdCaronasPegas = this.mapIdCaronasPegas.values().iterator();
	private Lock lockMapIdCaronasPegas = new ReentrantLock();

	private Map<Integer, Interesse> mapIdInteresse = Collections.synchronizedMap(new TreeMap<Integer, Interesse>());
	private Lock lockMapIdInteresse = new ReentrantLock();
	
	private Map<Integer, Solicitacao> mapIdSolicitacoesFeitas = Collections.synchronizedMap(new TreeMap<Integer, Solicitacao>());
	private Lock lockMapIdSolicitacoesFeitas = new ReentrantLock();

	private Map<Integer, Sugestao> mapIdSugestoesFeitas = Collections.synchronizedMap(new TreeMap<Integer, Sugestao>());
	private Lock lockMapIdSugestoesFeitas = new ReentrantLock();
	
	private List<Integer> listaIdsUsuariosPreferenciais = Collections.synchronizedList(new LinkedList<Integer>());
	private Lock lockListaUsuariosPreferenciais = new ReentrantLock();

	private Lock lockMapIdAmigo = new ReentrantLock();
	private Map<Integer, Usuario> mapIdAmigo = Collections.synchronizedMap(new TreeMap<Integer, Usuario>());
	
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
		synchronized (Usuario.class) {
			setLogin(login);
			setSenha(senha);
	
			setIdUsuario(this.hashCode());
	
			setNome(nome);
			setEndereco(endereco);
			setEmail(email);
			
			setPontuacao(0);
		}
	}

	/**
	 * Configura o id do usuario.
	 * 
	 * @param id
	 */
	private synchronized void setIdUsuario(Integer id) {
		this.idUsuario = id;
	}

	/**
	 * Retorna o id do usuario.
	 * 
	 * @return idUsuario
	 */
	public synchronized Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Retorna o login do usu�rio.
	 * 
	 * @return
	 */
	public synchronized String getLogin() {
		return login;
	}

	/**
	 * Configura o login do usu�rio.
	 * 
	 * @param login
	 * 
	 */
	public synchronized void setLogin(String login) {
		if (login == null || login.equals(""))
			throw new IllegalArgumentException("Login inválido");
		this.login = login;
	}

	/**
	 * Retorna email do usu�rio.
	 * 
	 * @return email
	 */
	public synchronized String getEmail() {
		return this.email;
	}

	/**
	 * Configura e-mail do usu�rio.
	 * 
	 * @param email
	 * 
	 */
	public synchronized void setEmail(String email) {
		if (email == null || email.equals(""))
			throw new IllegalArgumentException("Email inválido");
		this.email = email;
	}

	/**
	 * Retorna senha do usu�rio.
	 * 
	 * @return senha
	 */
	public synchronized String getSenha() {
		return senha;
	}

	/**
	 * Configura senha do usu�rio.
	 * 
	 * @param senha
	 * 
	 */
	public synchronized void setSenha(String senha) {
		if (senha == null || senha.equals(""))
			throw new IllegalArgumentException("Senha inválida");
		this.senha = senha;
	}

	/**
	 * Retorna nome do usu�rio.
	 * 
	 * @return
	 */
	public synchronized String getNome() {
		return this.nome;
	}

	/**
	 * Configura nome do usu�rio.
	 * 
	 * @param nome
	 * 
	 */
	public synchronized void setNome(String nome) {
		if (nome == null || nome.equals(""))
			throw new IllegalArgumentException("Nome inválido");
		this.nome = nome;
	}

	/**
	 * Retorna endere�o do usu�rio.
	 * 
	 * @return endere�o
	 */
	public synchronized String getEndereco() {
		return this.endereco;
	}

	/**
	 * Configura endere�o do usu�rio.
	 * 
	 * @param endereco
	 * 
	 */
	public synchronized void setEndereco(String endereco) {
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
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * 
	 */
	public Carona cadastrarCarona(Integer idUsuario, String origem,
			String destino, String data, String hora, Integer vagas,
			int ordemParaCaronas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = new Carona(idUsuario, origem, destino, data, hora,
					vagas, ordemParaCaronas);
			this.mapIdCaronasOferecidas.put(carona.getIdCarona(), carona);
			return carona;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Verifica se senha � v�lida, i.e., confere com a senha do usuario.
	 * 
	 * @param senha
	 * @return true se senha eh valida
	 */
	public synchronized boolean validaSenha(String senha) {
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
		try {
			lockMapIdCaronasOferecidas.lock();
			
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
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	

	/**
	 * Procura caroneiro e adiciona solicitacao com aquele ponto de encontro.
	 * 
	 * @param idCarona
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param pontos
	 * @return idSolicitacao
	 * @throws CadastroEmCaronaPreferencialException 
	 * 
	 */
	public Solicitacao sugerirPontoEncontro(String idCarona, Usuario donoDaCarona,
			Usuario donoDaSolicitacao, String pontos) throws CadastroEmCaronaPreferencialException {
		
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona c = this.mapIdCaronasOferecidas.get(idCarona);
			
			if (c == null)
				throw new IllegalArgumentException(
						"Identificador do carona é inválido");
			return c.addSolicitacao(c.getOrigem(), c.getDestino(), donoDaCarona,
					donoDaSolicitacao, pontos, listaIdsUsuariosPreferenciais);
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Procura caroneiro e adiciona solicitacao com aquele ponto de encontro.
	 * 
	 * @param idCarona
	 * @param donoDaCarona
	 * @param donoDaSolicitacao
	 * @param pontos
	 * @return idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws CadastroEmCaronaPreferencialException 
	 * 
	 */
	public Solicitacao solicitarVagaPontoEncontro(Integer idCarona,
			Usuario donoDaCarona, Usuario donoDaSolicitacao, String pontos) throws CaronaInexistenteException, CadastroEmCaronaPreferencialException {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = this.mapIdCaronasOferecidas.get(idCarona);
			
			if(carona != null) {
				return carona.addSolicitacao(carona.getOrigem(), carona.getDestino(), donoDaCarona,
							donoDaSolicitacao, pontos, listaIdsUsuariosPreferenciais);
			}
			throw new CaronaInexistenteException();
		}
		finally { 
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna mapa de caronas cadastradas no caroneiro.
	 * 
	 * @return mapa de caronas
	 */
	public Map<Integer, Carona> getMapIdCaronasOferecidas() {
		try {
			lockMapIdCaronasOferecidas.lock();
			return this.mapIdCaronasOferecidas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna string com o trajeto da carona.
	 * 
	 * @param idCarona
	 * @return trajeto da carona
	 * 
	 */
	public  String[] getTrajeto(Integer idCarona) {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona c = this.mapIdCaronasOferecidas.get(idCarona);
			
			if (c == null)
				throw new IllegalArgumentException("Identificador do carona é inválido");
			return c.getTrajeto();
		} finally { 
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna uma carona.
	 * 
	 * @param idCarona
	 * @return Carona
	 */
	public Carona getCarona(Integer idCarona) {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona c = this.mapIdCaronasOferecidas.get(idCarona);
			
			if (c == null)
				throw new IllegalArgumentException("Identificador do carona é inválido");
			return c.getCarona();
		} finally { 
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Torna null objetos manipulados pelo sistema.
	 */
	public void zerarSistema() {
		try {
			lockMapIdCaronasOferecidas.lock();
			lockMapIdCaronasPegas.lock();
			
			this.mapIdCaronasOferecidas.clear();
			this.mapIdCaronasPegas.clear();
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
			lockMapIdCaronasPegas.unlock();
		}
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
	 * @throws CaronaInexistenteException 
	 */
	public Sugestao responderSugestaoPontoEncontro(Integer idCarona,
			Integer idSugestao, String pontos) throws CaronaInexistenteException {
		if (pontos.equals(""))
			throw new IllegalArgumentException("Ponto Inválido");

		try {
			lockMapIdCaronasOferecidas.lock();
			// Iterator Pattern
			iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				if (c.getIdCarona().equals(idCarona)) {
					return c.setSolicitacaoPontoEncontro(idSugestao, pontos);
				}
			}
			
			throw new CaronaInexistenteException();
		} finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna id do dono da solicitacao.
	 * 
	 * @param idSolicitacao
	 * @return
	 * @throws EstadoSolicitacaoException 
	 * @throws CaronaInexistenteException 
	 * @throws IllegalArgumentException 
	 */
	public Solicitacao aceitarSolicitacaoPontoEncontro(Integer idSolicitacao) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {

		try {
			lockMapIdCaronasOferecidas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				// Iterator Pattern
				Iterator<Solicitacao> it = c.getMapIdSolicitacao().values().iterator();
				while (it.hasNext()) {
					Solicitacao s = it.next();
					if (s.getIdSolicitacao().equals(idSolicitacao) && s.getTipoSolicitacao().equals(EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO)) {
						s.aceitar(c);
						c.setPontoEncontro(s.getPontoEncontro()); // seta ponto de encontro para carona apos aceitar ponto encontro
						return s;
					}
				}
			}
			throw new IllegalArgumentException("Solicitação inexistente");
			
		} finally {
			lockMapIdCaronasOferecidas.unlock();
		}
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
	 * @throws CaronaInexistenteException 
	 */
	public void adicionarIdCaronaPega(Integer idCarona, Carona carona) throws CaronaInexistenteException {
		if(idCarona == null)
			throw new IllegalArgumentException("IdCarona inválido");
		if(carona == null)
			throw new CaronaInexistenteException();
		
		try {
			lockMapIdCaronasPegas.lock();
			this.mapIdCaronasPegas.put(idCarona, carona);
		}
		finally {
			lockMapIdCaronasPegas.unlock();
		}
	}

	/**
	 * Retorna solicitacao aceita.
	 * 
	 * @param idSolicitacao
	 * @return solicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * 
	 */
	public Solicitacao aceitarSolicitacao(Integer idSolicitacao) throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
		try {
			lockMapIdCaronasOferecidas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona carona = iteratorIdCaronasOferecidas.next();
				Iterator<Solicitacao> itSolicitacoes = carona.getMapIdSolicitacao().values()
						.iterator();
				while (itSolicitacoes.hasNext()) {
					Solicitacao s = itSolicitacoes.next();
					if (s.getIdSolicitacao().equals(idSolicitacao) &&
							s.getTipoSolicitacao().equals(EnumTipoSolicitacao.SOLICITACAO_SEM_PONTO_ENCONTRO)) {
						// nao seta pontoEncontro da carona ==> fica null
						carona.aceitarSolicitacao(s);
						return s;
					}
				}
			}
			throw new IllegalArgumentException("Solicitação inexistente");
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	// pure fabrication
	@Override
	public synchronized String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + getLogin() + "]";
	}

	@Override
	public synchronized int hashCode() {
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
	public synchronized boolean equals(Object obj) {
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
	 * @throws CadastroEmCaronaPreferencialException 
	 * 
	 */
	public Solicitacao solicitarVaga(Integer idCarona, Usuario donoDaCarona,
			Usuario donoDaSolicitacao) throws IllegalArgumentException, CadastroEmCaronaPreferencialException {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = this.mapIdCaronasOferecidas.get(idCarona);
			
			if (carona != null) {
				return carona.addSolicitacao(carona.getOrigem(), carona.getDestino(),
						donoDaCarona, donoDaSolicitacao, this.listaIdsUsuariosPreferenciais);
			}
			throw new IllegalArgumentException("Identificador do carona é inválido");
		} 
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Procura qual carona possui solicitacao a ser rejeitada e a rejeita, nao
	 * eliminando-a do historico, apendas mudando o seu estado.
	 * 
	 * @param idSolicitacao
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 * 
	 */
	public Solicitacao rejeitarSolicitacao(Integer idSolicitacao) throws CaronaInexistenteException, EstadoSolicitacaoException {
		try {
			lockMapIdCaronasOferecidas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				Iterator<Solicitacao> it = c.getMapIdSolicitacao().values()
						.iterator();
				while (it.hasNext()) {
					Solicitacao s = it.next();
					if (s.getIdSolicitacao().equals(idSolicitacao)) {
						s.rejeitar(c);
						return s;
					}
				}
			}
			throw new IllegalArgumentException("Solicitação inexistente");
		} 
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna id de perfil que eh identico a idUsuario.
	 * 
	 * @return idPerfil
	 */
	public synchronized Usuario visualizarPerfil() {
		return this;
	}

	/**
	 * Retorna numero de caronas pegas com review "nao faltou".
	 * 
	 * @return numero de presen�as
	 */
	public Integer getPresencasEmVagasDeCaronas() {
		try {
			int sum = 0;
			
			lockMapIdCaronasPegas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasPegas = this.mapIdCaronasPegas.values().iterator();
			while (iteratorIdCaronasPegas.hasNext()) {
				Carona c = iteratorIdCaronasPegas.next();
				Iterator<EnumCaronaReview> it = c.getMapIdDonoReviewCaroneiro().values().iterator();
				while (it.hasNext()) {
					EnumCaronaReview review = it.next();
					if (review.equals(EnumCaronaReview.NAO_FALTOU)) {
						sum++;
					}
				}
			}
			return sum;
		} finally {
			lockMapIdCaronasPegas.unlock();
		}
	}

	/**
	 * Retorna numero de caronas pegas com review "faltou".
	 * 
	 * @return numero de faltas
	 */
	public Integer getFaltasEmVagasDeCaronas() {
		try {
			int sum = 0;
			lockMapIdCaronasPegas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasPegas = this.mapIdCaronasPegas.values().iterator();
			while (iteratorIdCaronasPegas.hasNext()) {
				Carona c = iteratorIdCaronasPegas.next();
				Iterator<EnumCaronaReview> it = c.getMapIdDonoReviewCaroneiro()
						.values().iterator();
				while (it.hasNext()) {
					EnumCaronaReview review = it.next();
					if (review.equals(EnumCaronaReview.FALTOU)) {
						sum++;
					}
				}
			}
			return sum;
		} finally {
			lockMapIdCaronasPegas.unlock();
		}
	}

	/**
	 * Retorna numero de caronas oferecidas com review "nao funcionou".
	 * 
	 * @return numero de caronas que nao funcionaram
	 */
	public Integer getCaronasQueNaoFuncionaram() {
		try {
			int sum = 0;
			lockMapIdCaronasOferecidas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				Iterator<EnumCaronaReview> it = c.getMapIdCaroneiroReviewDono().values().iterator();
				while (it.hasNext()) {
					EnumCaronaReview review = it.next();
					if (review.equals(EnumCaronaReview.NAO_FUNCIONOU)) {
						sum++;
					}
				}
			}
			return sum;
		} finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna numero de caronas oferecidas com review "segura e tranquila".
	 * 
	 * @return numero de caronas seguras
	 */
	public Integer getCaronasSegurasETranquilas() {
		try {
			int sum = 0;
			lockMapIdCaronasOferecidas.lock();
			
			// Iterator Pattern
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				Iterator<EnumCaronaReview> it = c.getMapIdCaroneiroReviewDono()
						.values().iterator();
				while (it.hasNext()) {
					EnumCaronaReview review = it.next();
					if (review.equals(EnumCaronaReview.SEGURA_E_TRANQUILA)) {
						sum++;
					}
				}
			}
			return sum;
		} 
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	 /**
     * Retorna o historico de caronas pegas.
     *  
     * @return string do historico
     */
    public List<Carona> getHistoricoDeVagasEmCaronas() {
        try {
        	List<Carona> caronasPegas = new LinkedList<Carona>(); 
        	lockMapIdCaronasPegas.lock();
        	caronasPegas.addAll(mapIdCaronasPegas.values());
        	Collections.sort(caronasPegas);
        	return caronasPegas;
		}
        finally {
        	lockMapIdCaronasPegas.unlock();
        }
    }

	/**
	 * Historico de caronas oferecidas em forma de string.
	 * 
	 * @return string do historico
	 */
	public List<Carona> getHistoricoDeCaronas() {
        try {
        	List<Carona> caronasOferecidas = new LinkedList<Carona>(); 
        	lockMapIdCaronasOferecidas.lock();
        	caronasOferecidas.addAll(mapIdCaronasOferecidas.values());
        	Collections.sort(caronasOferecidas);
        	return caronasOferecidas;
		}
        finally {
        	lockMapIdCaronasOferecidas.unlock();
        }
	}

	/**
	 * Seta se usuario esteve na carona, o dono da carona eh quem faz esse
	 * review.
	 * 
	 * @param idUsuario
	 * @param idCarona
	 * @param loginCaroneiro
	 * @return 
	 * 
	 */
	public EnumCaronaReview reviewVagaEmCarona(Integer idCarona, Integer idCaroneiro,
			String review) {
		
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona c = this.mapIdCaronasOferecidas.get(idCarona);
			if (c == null)
				throw new IllegalArgumentException(
						"Identificador do carona é inválido");
			return c.setReviewVagaEmCarona(idCaroneiro, review);
		} 
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
		
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
	public EnumCaronaReview setReviewCarona(Integer idCaroneiro, Integer idCarona,
			String review) {
		try {
			lockMapIdCaronasPegas.lock();
			Carona c = mapIdCaronasPegas.get(idCarona);
			if (c == null)
				throw new IllegalArgumentException(
						"Identificador do carona é inválido");
			return c.setCaroneiroReviewDono(idCaroneiro, review);
		}
		finally {
			lockMapIdCaronasPegas.unlock();
		}
	}

	/**
	 * Retorna mapa de caronas pegas.
	 * 
	 * @return mapa de caronas
	 */
	public Map<Integer, Carona> getMapIdCaronasPegas() {
		try {
			lockMapIdCaronasPegas.lock();
			return this.mapIdCaronasPegas;
		}
		finally {
			lockMapIdCaronasPegas.unlock();
		}
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

		try {
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();

			if (origem.equals("") && !destino.equals("")) {

				// Iterator Pattern
				while (iteratorIdCaronasOferecidas.hasNext()) {
					Carona c = iteratorIdCaronasOferecidas.next();
					if (c.getDestino().equals(destino) && c.isCaronaMunicipal()) {
						caronas.add(c);
					}
				}
			} else if (!origem.equals("") && destino.equals("")) {
				// Iterator Pattern
				while (iteratorIdCaronasOferecidas.hasNext()) {
					Carona c = iteratorIdCaronasOferecidas.next();
					if (c.getOrigem().equals(origem) && c.isCaronaMunicipal()) {
						caronas.add(c);
					}
				}
			} else if (!origem.equals("") && !destino.equals("")) {
				// Iterator Pattern
				while (iteratorIdCaronasOferecidas.hasNext()) {
					Carona c = iteratorIdCaronasOferecidas.next();
					if (c.getOrigem().equals(origem)
							&& c.getDestino().equals(destino) && c.isCaronaMunicipal()) {
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
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
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

		try { 
			lockMapIdCaronasOferecidas.lock();
			// Iterator Pattern
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				if (c.isCaronaMunicipal() && c.getCidade().equals(cidade)) {
					caronas.add(c);
				}
			}
			return caronas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}

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
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 * 
	 * @see CaronaMunicipal
	 */
	public Carona cadastrarCaronaMunicipal(String origem, String destino,
			String cidade, String data, String hora, Integer vagas,
			int ordemParaCaronas) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		Carona cm = new Carona(getIdUsuario(), origem, destino, data, hora,
				vagas, cidade, ordemParaCaronas);
		
		try {
			lockMapIdCaronasOferecidas.lock();
			mapIdCaronasOferecidas.put(cm.getIdCarona(), cm);
			return cm;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
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

		try {
			lockMapIdCaronasOferecidas.lock();
			// Iterator Pattern
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				listaCaronas.add(c);
			}
			
			Collections.sort(listaCaronas);
			return listaCaronas.get(indexCarona - 1);
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna lista de ids das caronas cadastradas pelo usuario.
	 * 
	 * @return lista ids caronas
	 */
	public List<Carona> getTodasCaronasUsuario() {
		List<Carona> listaCaronasCadastradas = new LinkedList<Carona>();

		try {
			lockMapIdCaronasOferecidas.lock();
			// Iterator Pattern
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while (iteratorIdCaronasOferecidas.hasNext()) {
				Carona c = iteratorIdCaronasOferecidas.next();
				listaCaronasCadastradas.add(c);
			}
	
			Collections.sort(listaCaronasCadastradas);
			return listaCaronasCadastradas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
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

		try { 
			lockMapIdCaronasOferecidas.lock();
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
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
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
		
		try {
			lockMapIdCaronasOferecidas.lock();
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
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna string com o ponto sugerido para idCarona.
	 * 
	 * @param idCarona
	 * @return string com pontos sugeridos
	 */
	public List<String> getPontosSugeridos(Integer idCarona) {
		try {
			lockMapIdCaronasOferecidas.lock();
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
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna ponto de encontro atual para a carona.
	 * 
	 * @param idCarona
	 * @return string com ponto de encontro
	 */
	public String getPontosEncontro(Integer idCarona) {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona c = this.mapIdCaronasOferecidas.get(idCarona);
			if (c.getPontoEncontro() != null)
				return "[" + c.getPontoEncontro() + "]";
			else
				return "[]";
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Usuario solicitante desiste da requisicao feita.
	 * 
	 * obs.: como a solicitacao ja foi aceita, a carona estah na lista de
	 * caronas pegas pelo solicitante.
	 * 
	 * @param idCarona
	 * @param idSolicitacao
	 * @return solicitacao cancelada
	 * @throws CaronaInexistenteException 
	 * @throws EstadoSolicitacaoException 
	 */
	public Solicitacao desistirRequisicao(Integer idCarona, Integer idSolicitacao) throws CaronaInexistenteException, EstadoSolicitacaoException {
		if(idCarona == null)
			throw new CaronaInexistenteException();
		
		try {
			lockMapIdCaronasPegas.lock();
			Carona c = this.mapIdCaronasPegas.get(idCarona);
			return c.desistirRequisicao(idSolicitacao);
		}
		finally {
			lockMapIdCaronasPegas.unlock();
		}
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
	public String atualizaPerfilUsuarioInteressado(Carona carona,
			String emailDonoDaCarona) {
		return notificaPerfil(carona, emailDonoDaCarona);
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
	 * @return mensagem adicionada a lista de mensagens
	 */
	public String notificaPerfil(Carona carona, String emailDonoDaCarona) {
			SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		String msg = new String("Carona cadastrada no dia "
				+ formatterData.format(carona.getData().getTime()) + ", " 
				+ "às " + formatterHora.format(carona.getHora().getTime())
				+ " de acordo com os seus interesses registrados. "
				+ "Entrar em contato com " + emailDonoDaCarona);
		try {
			lockMensagensPerfil.lock();
			mensagensPerfil.add(msg);
		}
		finally {
			lockMensagensPerfil.unlock();
		}
		return msg;
	}

	/**
	 * Adiciona interesse ao mapa de interesses
	 * do usuario (this).
	 * 
	 * @param interesse
	 * @return interesse registrado
	 */
	public Interesse cadastrarInteresseUsuario(String origem, String destino, String data,
			String horaInicio, String horaFim) {
		Interesse interesse = new Interesse(origem, destino, data, horaInicio,
				horaFim);
		
		try {
			lockMapIdInteresse.lock();
			mapIdInteresse.put(interesse.getIdInteresse(), interesse);
		}
		finally {
			lockMapIdInteresse.unlock();
		}
		
		return interesse;
	}

	/**
	 * Retorna string com resumo das mensagens
	 * presentes no perfil do usuario.
	 * 
	 * @return string com mensagens
	 */
	public List<String> verificarMensagensPerfil() {
		try {
			lockMensagensPerfil.lock();
			return this.mensagensPerfil;
		}
		finally {
			lockMensagensPerfil.unlock();
		}
	}
	
	/**
	 * Retorna mapa de id-interesses do usuario.
	 * 
	 * @return mapIdInteresses
	 */
	public Map<Integer, Interesse> getMapIdInteresse() {
		synchronized (lockMapIdInteresse) {
			return this.mapIdInteresse;
		}
	}

	/**
	 * Envia email para destino com a mensagem <message>.
	 * 
	 * @param destino: email de destino
	 * @param message: corpo da mensagem
	 * @return true se email foi enviado com sucesso
	 * @throws MessagingException
	 */
	public synchronized boolean enviarEmail(String destino, String message)
			throws MessagingException {
		SenderMail.sendMail(getEmail(), destino, message);
		return true;
	}
	
	/**
	 * Adiciona solicitacao ao mapa de solicitacoes feitas por
	 * este usuario.
	 * 
	 * @param solicitacaoFeita
	 */
	public void addSolicitacaoFeita(Solicitacao solicitacaoFeita) {
		try {
			lockMapIdSolicitacoesFeitas.lock();
			this.mapIdSolicitacoesFeitas.put(solicitacaoFeita.getIdSolicitacao(), solicitacaoFeita);
		} 
		finally {
			lockMapIdSolicitacoesFeitas.unlock();
		}
	}
	
	/**
	 * Retorna mapa de solicitacoes feitas por este usuario.
	 * 
	 * @return mapa de solicitacoes feitas
	 */
	public Map<Integer, Solicitacao> getMapIdSolicitacoesFeitas() {
		synchronized (lockMapIdSolicitacoesFeitas) {
			return this.mapIdSolicitacoesFeitas;
		}
	}
	
	/**
	 * Adiciona sugestao feita ao mapa de sugestoes feitas
	 * 
	 * @param sugestaoFeita
	 */
	public void addSugestaoFeita(Sugestao sugestaoFeita) {
		synchronized (lockMapIdSugestoesFeitas) {
			this.mapIdSugestoesFeitas.put(sugestaoFeita.getIdSugestao(), sugestaoFeita);
		}
	}
	
	/**
	 * Retorna lista de sugestoes feitas por este
	 * usuario.
	 * 
	 * @return mapa de sugestoes feitas
	 */
	public List<Sugestao> getListaDeSugestoesFeitas() {
		synchronized (lockMapIdSugestoesFeitas) {
			List<Sugestao> listaSugestoes = new LinkedList<Sugestao>();
			listaSugestoes.addAll(this.mapIdSugestoesFeitas.values());
			return listaSugestoes;
		}
	}

	/**
	 * Cadastra carona relampago.
	 * 
	 * obs.: note que minimoCaroneiros eh passado duas
	 * vezes para distinguir dos construtores ja existentes
	 * e nao ficar ambiguo.
	 * 
	 * 
	 * @param idDonoDaCarona
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param ordemNaInsercaoNoSistema
	 * @param minimoCaroneiros
	 * @param ordemParaCaronas 
	 * @return carona relampago
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 * @throws MessagingException 
	 */
	public Carona cadastrarCaronaRelampago(Integer idDonoDaCarona,
			String origem,String destino, String dataIda, String dataVolta, 
			String hora, Integer vagas, Integer minimoCaroneiros, Integer posicaoNaInsercao) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
			
		Carona caronaRelampago = new Carona(idDonoDaCarona, origem, 
					destino, dataIda, dataVolta, hora, vagas, 
					minimoCaroneiros, posicaoNaInsercao); 
		
		try {
			lockMapIdCaronasOferecidas.lock();
			this.mapIdCaronasOferecidas.put(caronaRelampago.getIdCarona(), caronaRelampago);
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
		
		return caronaRelampago;
	}

	@Override
	public synchronized int compareTo(Usuario other) {
		return this.getPontuacao() - other.getPontuacao();
	}
	
	/**
	 * @return the pontuacao
	 */
	public synchronized Integer getPontuacao() {
		return pontuacao;
	}

	/**
	 * @param pontuacao the pontuacao to set
	 */
	public synchronized void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	/**
	 * Deleta interesse do mapa de interesses
	 * desse usuario.
	 * 
	 * @param idInteresse
	 */
	public void deletarInteresse(Integer idInteresse) {
		try {
			lockMapIdInteresse.lock();
			this.mapIdInteresse.remove(idInteresse);
		}
		finally {
			lockMapIdInteresse.unlock();
		}
	}

	/**
	 * Retorna lista de caronas oferecidas por
	 * este usuario.
	 * 
	 * @return lista de caronas oferecidas
	 */
	public List<Carona> getListaCaronasOfercidas() {
		try {
			List<Carona> listaCaronasOferecidas = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = this.mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				listaCaronasOferecidas.add(iteratorIdCaronasOferecidas.next());
			}
			return listaCaronasOferecidas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna lista de caronas pegas por
	 * este usuario.
	 * 
	 * @return lista de caronas pegas
	 */
	public List<Carona> getListaCaronasPegas() {
		try {
			List<Carona> listaCaronasPegas = new LinkedList<Carona>();
			
			lockMapIdCaronasPegas.lock();
			iteratorIdCaronasPegas = this.mapIdCaronasPegas.values().iterator();
			while(iteratorIdCaronasPegas.hasNext()) {
				listaCaronasPegas.add(iteratorIdCaronasPegas.next());
			}
			return listaCaronasPegas;
		}
		finally {
			lockMapIdCaronasPegas.unlock();
		}
	}

	/**
	 * Retorna lista de caronas confirmadas
	 * ate o momento, dentre as caronas oferecidas por
	 * ele.
	 * 
	 * @return lista de caronas confirmadas
	 */
	public List<Carona> getListaCaronasConfirmadas() {
		try { 
			List<Carona> listaCaronasConfirmadas = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.CONFIRMADA))
					listaCaronasConfirmadas.add(caronaOferecida);
			}
			return listaCaronasConfirmadas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de caronas canceladas
	 * ate o momento, dentre as caronas oferecidas por
	 * ele.
	 * 
	 * @return lista de caronas canceladas
	 */
	public List<Carona> getListaCaronasCanceladas() {
		try {
			List<Carona> listaCaronasCanceladas = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.CANCELADA))
					listaCaronasCanceladas.add(caronaOferecida);
			}
			return listaCaronasCanceladas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de caronas expired
	 * ate o momento, dentre as caronas oferecidas por
	 * ele.
	 * 
	 * @return lista de caronas expired
	 */
	public List<Carona> getListaCaronasExpired() {
		try {
			List<Carona> listaCaronasExpired = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.EXPIRED))
					listaCaronasExpired.add(caronaOferecida);
			}
			return listaCaronasExpired;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de caronas ocorrendo
	 * ate o momento, dentre as caronas oferecidas por
	 * ele.
	 * 
	 * @return lista de caronas ocorrendo
	 */
	public List<Carona> getListaCaronasOcorrendo() {
		try {
			List<Carona> listaCaronasOcorrendo = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.OCORRENDO))
					listaCaronasOcorrendo.add(caronaOferecida);
			}
			return listaCaronasOcorrendo;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
		
	}
	
	/**
	 * Retorna lista de caronas encerradas
	 * ate o momento, dentre as caronas oferecidas por
	 * ele.
	 * 
	 * @return lista de caronas encerradas
	 */
	public List<Carona> getListaCaronasEncerradas() {
		try {
			List<Carona> listaCaronasEncerradas = new LinkedList<Carona>();

			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.ENCERRADA))
					listaCaronasEncerradas.add(caronaOferecida);
			}
			return listaCaronasEncerradas;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Retorna lista de caronas comuns
	 * oferecidas por este usuario.
	 * 
	 * @return lista de caronas comuns
	 */
	public List<Carona> getListaCaronasComuns() {
		try {
			List<Carona> listaCaronasComuns = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.isCaronaComum())
					listaCaronasComuns.add(caronaOferecida);
			}
			return listaCaronasComuns;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de caronas comuns
	 * oferecidas por este usuario.
	 * 
	 * @return lista de caronas comuns
	 */
	public List<Carona> getListaCaronasMunicipais() {
		
		try {
			List<Carona> listaCaronasMunicipais = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.isCaronaMunicipal())
					listaCaronasMunicipais.add(caronaOferecida);
			}
			return listaCaronasMunicipais;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de caronas relampago
	 * oferecidas por este usuario.
	 * 
	 * @return lista de caronas relampago
	 */
	public List<Carona> getListaCaronasRelampago() {
		try {
			List<Carona> listaCaronasRelampago = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.isCaronaRelampago())
					listaCaronasRelampago.add(caronaOferecida);
			}
			return listaCaronasRelampago;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
		
	}
	
	/**
	 * Retorna lista de caronas preferenciais
	 * oferecidas por este usuario.
	 * 
	 * @return lista de caronas preferenciais
	 */
	public List<Carona> getListaCaronasPreferenciais() {
		try {
			List<Carona> listaCaronasComuns = new LinkedList<Carona>();
			
			lockMapIdCaronasOferecidas.lock();
			iteratorIdCaronasOferecidas = mapIdCaronasOferecidas.values().iterator();
			while(iteratorIdCaronasOferecidas.hasNext()) {
				Carona caronaOferecida = iteratorIdCaronasOferecidas.next();
				if(caronaOferecida.isCaronaPreferencial())
					listaCaronasComuns.add(caronaOferecida);
			}
			return listaCaronasComuns;
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
		
	}

	/**
	 * Encerra a carona identificada por idCarona.
	 * 
	 * @param idCarona
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 */
	public void encerrarCarona(Integer idCarona) throws CaronaInvalidaException, EstadoCaronaException {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = mapIdCaronasOferecidas.get(idCarona);
			carona.encerrarCarona();
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Cancela carona identificada por idCarona.
	 * @param idCarona
	 * @throws MessagingException 
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 */
	public void cancelarCarona(Integer idCarona) throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = mapIdCaronasOferecidas.get(idCarona);
			carona.cancelarCarona();
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}

	/**
	 * Adiciona id de caroneiro
	 * a lista de usuarios preferenciais,
	 * este metodo somente eh usado internamente pelo sistema
	 * quando um usuario faz um review positivo do dono da carona.
	 * 
	 * @param idCaroneiro
	 */
	public void addCaroneiroPreferencial(Integer idCaroneiro) {
		try {
			lockListaUsuariosPreferenciais.lock();
			listaIdsUsuariosPreferenciais.add(idCaroneiro);
		}
		finally {
			lockListaUsuariosPreferenciais.unlock();
		}
	}
	
	/**
	 * Define a carona identificada por idCarona
	 * como preferencial.
	 * 
	 * @param idCarona
	 */
	public void definirCaronaComoPreferencial(
			Integer idCarona) {
		try {
			lockMapIdCaronasOferecidas.lock();
			Carona carona = this.mapIdCaronasOferecidas.get(idCarona);
			carona.definirCaronaComoPreferencial();
		}
		finally {
			lockMapIdCaronasOferecidas.unlock();
		}
	}
	
	/**
	 * Retorna lista de usuarios preferencias desse usuario.
	 * 
	 * @return lista de usuarios preferenciais
	 */
	public List<Integer> getUsuariosPreferenciaisCarona() {
		try {
			lockListaUsuariosPreferenciais.lock();
			return listaIdsUsuariosPreferenciais ;
		}
		finally {
			lockListaUsuariosPreferenciais.unlock();
		}
	}

	/**
	 * Retorna lista de ids de usuarios
	 * preferenciais desse usuario.
	 * 
	 * @return lista de ids de usuarios
	 */
	public List<Integer> getListaIdsUsuariosPreferenciais() {
		try {
			lockListaUsuariosPreferenciais.lock();
			return this.listaIdsUsuariosPreferenciais;
		}
		finally {
			lockListaUsuariosPreferenciais.unlock();
		}
	}

	/**
	 * @return the pilhaDeMensagens
	 */
	public List<Mensagem> getListaDeMensagens() {
		try {
			lockListaDeMensagens.lock();

			List<Mensagem> listaDeMensagensInvertida = new LinkedList<Mensagem>();

			for (int i = listaDeMensagens.size() - 1; i >= 0; i--) {
				listaDeMensagensInvertida.add(listaDeMensagens.get(i));
			}
			return listaDeMensagensInvertida;
		}
		finally {
			lockListaDeMensagens.unlock();
		}
	}

	/**
	 * Adiciona mensagem a lista de
	 * mensagens deste usuario.
	 * 
	 * @param texto
	 * @param remetente
	 * @throws MessageException 
	 */
	public void addMensagem(Mensagem mensagem) throws MessageException {
		try {
			lockListaDeMensagens.lock();
			listaDeMensagens.add(mensagem);
		}
		catch(Exception e) {
			throw new MessageException();
		}
		finally {
			lockListaDeMensagens.unlock();
		}
	}

	/**
	 * Apaga mensagem da lista de mensagens.
	 * 
	 * @param idMensagem
	 */
	public void apagarMensagem(Integer idMensagem) {
		try {
			lockListaDeMensagens.lock();
			Iterator<Mensagem> itMensagens = listaDeMensagens.iterator();
			Mensagem m = null;
			while(itMensagens.hasNext()) {
				m = itMensagens.next();
				if(m.getIdMensagem().equals(idMensagem)) {
					listaDeMensagens.remove(m);
					break;
				}
			}
			if(m == null)
				throw new IllegalArgumentException("Mensagem inexistente");
		}
		finally {
			lockListaDeMensagens.unlock();
		}
	}

	/**
	 * Marca mensagem como lida.
	 * 
	 * @param idMensagem
	 */
	public void marcarMensagemComoLida(Integer idMensagem) {
		try {
			lockListaDeMensagens.lock();
			Iterator<Mensagem> itMensagens = listaDeMensagens.iterator();
			while(itMensagens.hasNext()) {
				Mensagem m = itMensagens.next();
				if(m.getIdMensagem().equals(idMensagem)) {
					m.setLida(EnumLida.LIDA);
				}
			}
		}
		finally {
			lockListaDeMensagens.unlock();
		}
	}

	/**
	 * Adiciona amigo
	 * 
	 * @param amigo
	 */
	public void adicionarAmigo(Usuario amigo) {
		try {
			lockMapIdAmigo.lock();
			this.mapIdAmigo.put(amigo.getIdUsuario(), amigo);
		} 
		finally {
			lockMapIdAmigo.unlock();
		}
	}

	/**
	 * Retorna lista de amigos do usuario.
	 * 
	 * @return lista de amigos
	 */
	public List<Usuario> getListaDeAmigos() {
		try {
			lockMapIdAmigo.lock();
			List<Usuario> listaAmigos = new LinkedList<Usuario>();
			listaAmigos.addAll(this.mapIdAmigo.values());
			return listaAmigos;
		} finally {
			lockMapIdAmigo.unlock();
		}
	}

	/**
	 * Retorna mapa de sugestoes feitas por
	 * este usuario.
	 * 
	 * @return mapa de sugestoes
	 */
	public Map<Integer, Sugestao> getMapIdSugestoesFeitas() {
		synchronized (lockMapIdSugestoesFeitas) {
			return this.mapIdSugestoesFeitas;
		}
	}
}
