package si1project2.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import si1project2.util.SpecialLinkedList;

/**
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Usuario {
	private String idUsuario;
	private String login;
	private String senha;

	//GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();
	private Map<String, Carona> mapIdCaronasOferecidas = new TreeMap<String, Carona>();
	
	// somente contem caronas concluidas (julgadas, revisadas pelo motorista)
	private List<String> listaIdsCaronasPegas = new LinkedList<String>(); 
	
	// caronas aprovadas mas que ainda nao foram julgadas pelo motorista (dono da carona)
	private List<String> listaIdsCaronasAprovadas = new LinkedList<String>();
	
	// somente contem caronas concluidas (julgadas, revisadas pelo motorista)
	private List<String> listaIdsCaronasEmQueFaltou = new LinkedList<String>();

	public Usuario(String login, String senha) throws Exception{
		setLogin(login);
		setSenha(senha);
		
		setIdUsuario(this.hashCode() + "");
	}
	
	private void setIdUsuario(String string) {
		this.idUsuario = string;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws Exception {
		if( login == null || login.equals("") )
			throw new Exception("Login inválido");
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		if(senha == null || senha.equals(""))
			throw new Exception("Senha inválida");
		this.senha = senha;
	}



	public Object getAtributoUsuario(String nomeAtributo) throws Exception {
		if(nomeAtributo == null || nomeAtributo.equals(""))
			throw new Exception("Atributo inválido");
		
		else if (nomeAtributo.equals("login"))
			return this.getLogin();
		else if(nomeAtributo.equals("senha"))
			return this.getSenha();
		throw new Exception("Atributo inexistente");
	}

	public String cadastrarCarona(String idUsuario, String origem, String destino, 
			String data, String hora, String vagas) throws Exception {
		
		Carona carona = new Carona(idUsuario, origem, destino, data, hora, vagas);
		this.mapIdCaronasOferecidas.put(carona.getIdCarona(), carona);
		//getPerfil().setHistoricoDeCaronas(getPerfil().getHistoricoDeCaronas() + carona.toString2());
		return carona.getIdCarona();
	}

	public boolean validaSenha(String senha){
		return this.senha.equals(senha);
	}

	public List<String> localizarCarona(String origem, String destino) {
		List<String> caronas = new SpecialLinkedList<String>();
		
		if(origem.equals("") && !destino.equals("")) {
			for(Carona c : this.mapIdCaronasOferecidas.values()) {
				if(c.getDestino().equals(destino)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else if(!origem.equals("") && destino.equals("")) {
			for(Carona c : this.mapIdCaronasOferecidas.values()) {
				if(c.getOrigem().equals(origem)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else if(!origem.equals("") && !destino.equals("")){
			for(Carona c : this.mapIdCaronasOferecidas.values()) {
				if(c.getOrigem().equals(origem) && c.getDestino().equals(destino)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else {
			for(Carona c : this.mapIdCaronasOferecidas.values()) {
				caronas.add(c.getIdCarona());
			}
		}
			
		return caronas;
	}

	

	public Object getAtributoCarona(String idCarona, String nomeAtributo) throws Exception {
		return this.mapIdCaronasOferecidas.get(idCarona).getAtributo(nomeAtributo);
	}
	
	public String sugerirPontoEncontro(String idCarona, String idDonoDaCarona,
			String idDonoDaSolicitacao, String pontos) throws Exception {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona); 
		return c.addSolicitacao(c.getOrigem(),c.getDestino(), idDonoDaCarona, idDonoDaSolicitacao, pontos);
	}

	public Map<String, Carona> getMapIdCaronasOferecidas() {
		return this.mapIdCaronasOferecidas;
	}
	
	public String getTrajeto(String idCarona) throws Exception {
		return this.mapIdCaronasOferecidas.get(idCarona).getTrajeto(); 
	}

	public String getCarona(String idCarona) throws Exception {
			
		return this.mapIdCaronasOferecidas.get(idCarona).getCarona();
	}

	public void zerarSistema() {
		this.mapIdCaronasOferecidas.clear();
		this.listaIdsCaronasPegas.clear();
	}
	
	/**
	 * Muda estado da solicitacao para respondida.
	 * 
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 * @throws Exception 
	 */
	public void responderSugestaoPontoEncontro(String idCarona,
			String idSugestao, String pontos) throws Exception {
		if(pontos.equals(""))
			throw new Exception("Ponto Inválido");
		for(Carona c : this.mapIdCaronasOferecidas.values()) {
			if(c.getIdCarona().equals(idCarona)) {
				c.setSolicitacaoPontoEncontro(idSugestao, pontos);
			}
		}
	}

	public String solicitarVagaPontoEncontro(String idCarona,
			String idDonoDaCarona, String idDonoDaSolicitacao, String pontos) throws Exception {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona); 
		return c.addSolicitacao(c.getOrigem(), c.getDestino(), idDonoDaCarona, idDonoDaSolicitacao, pontos);
	}

	/**
	 * Retorna id do dono da solicitacao.
	 * 
	 * @param idSolicitacao
	 * @return
	 * @throws Exception 
	 */
	public String[] aceitarSolicitacaoPontoEncontro(String idSolicitacao) throws Exception {
		String resp[] = new String[2];
		for(Carona c : this.mapIdCaronasOferecidas.values()) {
			for(Solicitacao solic : c.getMapIdSolicitacao().values()) {
				if(solic.getIdSolicitacao().equals(idSolicitacao)) {
					c.setPontoEncontro(solic.getPontoEncontro()); // seta ponto de encontro para carona apos aceitar ponto encontro
					c.setVagas((Integer.parseInt(c.getVagas())-1)+""); // decrementa o numero de vagas
					c.removeSolicitacao(idSolicitacao);
					resp[0] = solic.getIdDonoDaSolicitacao(); 
					resp[1] = c.getIdCarona();
					return resp;
				}
			}
		}
		throw new Exception("Solicitação inexistente");
	}
	
	/**
	 * Retorna array com
	 * id do dono da solicitacao e id
	 * da carona em questao.
	 * 
	 * @param idSolicitacao
	 * @return array
	 * @throws Exception
	 */
	public String[] aceitarSolicitacao(String idSolicitacao) throws Exception {
		String resp[] = new String[2];
		for(Carona c : this.mapIdCaronasOferecidas.values()) {
			for(Solicitacao solic : c.getMapIdSolicitacao().values()) {
				if(solic.getIdSolicitacao().equals(idSolicitacao)) {
					// nao seta pontoEncontro da carona ==> fica null
					c.setVagas((Integer.parseInt(c.getVagas())-1)+""); // decrementa o numero de vagas
					c.removeSolicitacao(idSolicitacao);
					resp[0] = solic.getIdDonoDaSolicitacao(); 
					resp[1] = c.getIdCarona();
					return resp;
				}
			}
		}
		throw new Exception("Solicitação inexistente");
	}

	// pure fabrication
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + this.getLogin() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public String solicitarVaga(String idCarona, String idDonoDaCarona, String idDonoDaSolicitacao) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona); 
		return c.addSolicitacao(c.getOrigem(), c.getDestino(), idDonoDaCarona, idDonoDaSolicitacao);
	}

	public void rejeitarSolicitacao(String idSolicitacao) throws Exception {
		boolean flag = false;
		for(Carona c : this.mapIdCaronasOferecidas.values()) {
			for(Solicitacao s : c.getMapIdSolicitacao().values()) {
				if(s.getIdSolicitacao().equals(idSolicitacao)) {
					c.rejeitarSolicitacao(idSolicitacao);
					flag = flag ? true : true;
				}
			}
		}
		if(!flag)
			throw new Exception("Solicitação inexistente");
	}

	public void removerSolicitacao(String idCarona, String idSolicitacao) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona); // O(logn)
		c.removeSolicitacao(idSolicitacao);
	}

	// corresponde ao julgamento feito pelos caroneiros sobre a carona
	/*public void setReviewCarona(String idCarona, String review) {
		Carona c = this.listaIdsCaronasPegas.get(idCarona);
		c.setReview(review);
	}*/
	
	/**
	 * Verifica se usuario esteve
	 * na carona e seta o review
	 * dele para ela.
	 * 
	 * @param idUsuario
	 * @param idCarona
	 * @param loginCaroneiro
	 * @param review
	 */
/*	public void reviewVagaEmCarona(String idCarona, String idCaroneiro,
			String reviewVagaEmCarona) {
		Carona c = this.mapIdCaronasOferecidas.get(idCarona);
		c.setReviewVagaEmCarona(idCaroneiro, reviewVagaEmCarona)
		
	}*/
	
	/**
	 * Adiciona id de uma carona
	 * aprovada para este usuario (this), pelo
	 * dono da carona.
	 * 
	 * A lista de caronas aprovadas estah com
	 * o dono da carona.
	 * 
	 * @param idCarona
	 */
	public void adicionarIdCaronaAprovada(String idCarona) {
		listaIdsCaronasAprovadas.add(idCarona);
	}

	/*public String getCaronasSegurasETranquilas() {
		int sum = 0;
		for(Carona c : this.listaIdsCaronasPegas.values()) {
			if(c.getReview().equals("carona segura e tranquila")) {
				sum++;
			}
		}
		return sum+"";
	}
	
	public String getHistoricoDeVagasEmCaronas() {
		for(Carona c : this.listaIdsCaronasPegas.values()) {
			
		}
	}*/
}
