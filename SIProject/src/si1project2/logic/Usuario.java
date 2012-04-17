package si1project2.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import si1project2.util.SpecialLinkedList;

public class Usuario {
	private String idUsuario;
	private String login;
	private String senha;
	private Perfil perfil;

	private Map<String, Carona> mapIdCaronasOferecidas = new TreeMap<String, Carona>();
	private List<String> listaIdsCaronasPegas = new LinkedList<String>();

	public Usuario(String login, String senha, String nome, String endereco, String email) throws Exception{
		setLogin(login);
		setSenha(senha);
		
		criaPerfil(nome, endereco, email);
		setIdUsuario(this.hashCode() + "");
				
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
	}
	
	private void criaPerfil(String nome2, String endereco2, String email2) {
		setPerfil(new Perfil(nome2, endereco2, email2));
	}

	private void setPerfil(Perfil perfil2) {
		this.perfil = perfil2;
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

	public String getEmail() {
		return this.perfil.getEmail();
	}

	public void setEmail(String email) throws Exception {
		if(email == null || email.equals(""))
			throw new Exception("Email inválido");
		//TODO criar validador de emails
		this.perfil.setEmail(email);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		if(senha == null || senha.equals(""))
			throw new Exception("Senha inválida");
		this.senha = senha;
	}

	public String getNome() {
		return this.perfil.getNome();
	}

	public void setNome(String nome) throws Exception {
		if(nome == null || nome.equals(""))
			throw new Exception("Nome inválido");
		this.perfil.setNome(nome);
	}

	public String getEndereco() {
		return this.perfil.getEndereco();
	}

	public void setEndereco(String endereco) throws Exception {
		if(endereco == null || endereco.equals(""))
			throw new Exception("Endereço inválido");
		this.perfil.setEndereco(endereco);
	}

	public Object getAtributo(String nomeAtributo) throws Exception {
		if(nomeAtributo == null || nomeAtributo.equals(""))
			throw new Exception("Atributo inválido");
		
		if(nomeAtributo.equals("nome"))
			return this.perfil.getNome();
		else if (nomeAtributo.equals("login"))
			return this.getLogin();
		else if(nomeAtributo.equals("email"))
			return this.perfil.getEmail();
		else if(nomeAtributo.equals("senha"))
			return this.getSenha();
		else if(nomeAtributo.equals("endereco"))
			return this.perfil.getEndereco();
		else if(nomeAtributo.equals("perfil"))
			return this.getPerfil();
		throw new Exception("Atributo inexistente");
	}

	public String cadastrarCarona(String idUsuario, String origem, String destino, 
			String data, String hora, String vagas) throws Exception {
		
		Carona carona = new Carona(idUsuario, origem, destino, data, hora, vagas);
		mapIdCaronasOferecidas.put(carona.getIdCarona(), carona);
		//getPerfil().setHistoricoDeCaronas(getPerfil().getHistoricoDeCaronas() + carona.toString2());
		return carona.getIdCarona();
	}

	public boolean validaSenha(String senha){
		return this.senha.equals(senha);
	}

	public List<String> localizarCarona(String origem, String destino) {
		List<String> caronas = new SpecialLinkedList<String>();
		
		if(origem.equals("") && !destino.equals("")) {
			for(Carona c : mapIdCaronasOferecidas.values()) {
				if(c.getDestino().equals(destino)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else if(!origem.equals("") && destino.equals("")) {
			for(Carona c : mapIdCaronasOferecidas.values()) {
				if(c.getOrigem().equals(origem)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else if(!origem.equals("") && !destino.equals("")){
			for(Carona c : mapIdCaronasOferecidas.values()) {
				if(c.getOrigem().equals(origem) && c.getDestino().equals(destino)){
					caronas.add(c.getIdCarona());
				}
			}
		}
		else {
			for(Carona c : mapIdCaronasOferecidas.values()) {
				caronas.add(c.getIdCarona());
			}
		}
			
		return caronas;
	}

	

	public Object getAtributoCarona(String idCarona, String nomeAtributo) throws Exception {
		return mapIdCaronasOferecidas.get(idCarona).getAtributo(nomeAtributo);
	}
	
	public String sugerirPontoEncontro(String idCarona, String idDonoDaCarona,
			String idDonoDaSolicitacao, String pontos) throws Exception {
		Carona c = mapIdCaronasOferecidas.get(idCarona); 
		return c.addSolicitacao(c.getOrigem(),c.getDestino(), idDonoDaCarona, idDonoDaSolicitacao, pontos);
	}

	public Map<String, Carona> getMapIdCaronasOferecidas() {
		return this.mapIdCaronasOferecidas;
	}
	
	public String getTrajeto(String idCarona) throws Exception {
		return mapIdCaronasOferecidas.get(idCarona).getTrajeto(); 
	}

	public String getCarona(String idCarona) throws Exception {
			
		return mapIdCaronasOferecidas.get(idCarona).getCarona();
	}

	public void zerarSistema() {
		mapIdCaronasOferecidas.clear();
		listaIdsCaronasPegas.clear();
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
		for(Carona c : mapIdCaronasOferecidas.values()) {
			if(c.getIdCarona().equals(idCarona)) {
				c.setSolicitacaoPontoEncontro(idSugestao, pontos);
			}
		}
	}

	public String solicitarVagaPontoEncontro(String idCarona,
			String idDonoDaCarona, String idDonoDaSolicitacao, String pontos) throws Exception {
		Carona c = mapIdCaronasOferecidas.get(idCarona); 
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
		for(Carona c : mapIdCaronasOferecidas.values()) {
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

	public void adicionarIdCaronaPega(String idCarona) {
		listaIdsCaronasPegas.add(idCarona);
	}

	// ESTRANHO: FAZ QUASE A MESMA COISA QUE aceitarSolicitacaoPontoEncontro(...) !!!
	public String[] aceitarSolicitacao(String idSolicitacao) throws Exception {
		String resp[] = new String[2];
		for(Carona c : mapIdCaronasOferecidas.values()) {
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
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + perfil.getNome() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public String solicitarVaga(String idCarona, String idDonoDaCarona, String idDonoDaSolicitacao) {
		Carona c = mapIdCaronasOferecidas.get(idCarona); 
		return c.addSolicitacao(c.getOrigem(), c.getDestino(), idDonoDaCarona, idDonoDaSolicitacao);
	}

	public void rejeitarSolicitacao(String idSolicitacao) throws Exception {
		boolean flag = false;
		for(Carona c : mapIdCaronasOferecidas.values()) {
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
		Carona c = mapIdCaronasOferecidas.get(idCarona); // O(logn)
		c.removeSolicitacao(idSolicitacao);
	}

	public Perfil getPerfil() {
		// TODO Auto-generated method stub
		return null;
	}
}
