package si1project2.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 * @see Usuario
 */
public class Perfil {
	private String nome;
	private String endereco;
	private String email;
	private String idPerfil;
	
	private Usuario usuario;
	
	private List<String> historicoDeCaronas; // lista de ids caronas oferecidas
	private List<String> historicoDeVagasEmCaronas; // lista de caronas pegas
	
	//GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();
	
	private Integer caronasSegurasETranquilas;
	private Integer caronasQueNaoFuncionaram;
	private Integer faltasEmVagasDeCaronas;
	private Integer presencasEmVagasDeCaronas;
	
	
	public Perfil(String login, String senha, String nome, String endereco, String email) throws Exception {
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		
		setHistoricoDeCaronas(new LinkedList<String>());
		setHistoricoDeVagasEmCaronas(new LinkedList<String>());
		
		setIdPerfil(hashCode() + "");
		
		usuario = new Usuario(login, senha);
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) throws Exception {
		if(nome == null || nome.equals(""))
			throw new Exception("Nome inválido");
		this.nome = nome;
	}

	public String getEmail() throws Exception {
		if(email == null || email.equals(""))
			throw new Exception("Email inválido");
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(String endereco) throws Exception {
		if(endereco == null || endereco.equals(""))
			throw new Exception("Endereço inválido");
		this.endereco = endereco;
	}
	
	private void setHistoricoDeVagasEmCaronas(List<String> historico) {
		this.historicoDeVagasEmCaronas = historico;
	}

	private void setHistoricoDeCaronas(List<String> historico) {
		this.historicoDeCaronas = historico;
	}

	public String getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Object getAtributoPerfil(String atributo) throws Exception {
		if (atributo.equals("nome")) {
			return getNome();
		}else if (atributo.equals("endereco")) {
			return getEndereco();
		}else if (atributo.equals("email")) {
			return getEmail();
	/*	}else if (atributo.equals("historico de caronas")) {
			return usuario.getHistoricoDeCaronas();
		}else if (atributo.equals("historico de vagas em caronas")) {
			return usuario.getHistoricoDeVagasEmCaronas();
		}else if (atributo.equals("caronas seguras e tranquilas")) {
			return usuario.getCaronasSegurasETranquilas();
		}else if (atributo.equals("caronas que não funcionaram")) {
			return usuario.getCaronasQueNaoFuncionaram();
		}else if (atributo.equals("faltas em vagas de caronas")) {
			return usuario.getFaltasEmVagasDeCaronas();
		}else if (atributo.equals("presenças em vagas de caronas")) {
			return usuario.getPresencasEmVagasDeCaronas();*/
		}
		throw new Exception("Atributo inválido");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((caronasQueNaoFuncionaram == null) ? 0
						: caronasQueNaoFuncionaram.hashCode());
		result = prime
				* result
				+ ((caronasSegurasETranquilas == null) ? 0
						: caronasSegurasETranquilas.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime
				* result
				+ ((faltasEmVagasDeCaronas == null) ? 0
						: faltasEmVagasDeCaronas.hashCode());
		result = prime
				* result
				+ ((historicoDeCaronas == null) ? 0 : historicoDeCaronas
						.hashCode());
		result = prime
				* result
				+ ((historicoDeVagasEmCaronas == null) ? 0
						: historicoDeVagasEmCaronas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((presencasEmVagasDeCaronas == null) ? 0
						: presencasEmVagasDeCaronas.hashCode());
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
		Perfil other = (Perfil) obj;
		if (caronasQueNaoFuncionaram == null) {
			if (other.caronasQueNaoFuncionaram != null)
				return false;
		} else if (!caronasQueNaoFuncionaram
				.equals(other.caronasQueNaoFuncionaram))
			return false;
		if (caronasSegurasETranquilas == null) {
			if (other.caronasSegurasETranquilas != null)
				return false;
		} else if (!caronasSegurasETranquilas
				.equals(other.caronasSegurasETranquilas))
			return false;
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
		if (faltasEmVagasDeCaronas == null) {
			if (other.faltasEmVagasDeCaronas != null)
				return false;
		} else if (!faltasEmVagasDeCaronas.equals(other.faltasEmVagasDeCaronas))
			return false;
		if (historicoDeCaronas == null) {
			if (other.historicoDeCaronas != null)
				return false;
		} else if (!historicoDeCaronas.equals(other.historicoDeCaronas))
			return false;
		if (historicoDeVagasEmCaronas == null) {
			if (other.historicoDeVagasEmCaronas != null)
				return false;
		} else if (!historicoDeVagasEmCaronas
				.equals(other.historicoDeVagasEmCaronas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (presencasEmVagasDeCaronas == null) {
			if (other.presencasEmVagasDeCaronas != null)
				return false;
		} else if (!presencasEmVagasDeCaronas
				.equals(other.presencasEmVagasDeCaronas))
			return false;
		return true;
	}

	public List<String> getHistoricoDeCaronas() {
		return usuario.getHistoricoDeCaronas();
	}
	
	public List<String> getHistoricoDeVagasEmCaronas() {
		return usuario.getHistoricoDeVagasEmCaronas();
	}
	
	public String getCaronasSegurasETranquilas() {
		return usuario.getCaronasSegurasETranquilas();
	}
	
	public String getCaronasQueNaoFuncionaram() {
		return usuario.getCaronasQueNaoFuncionaram();
	}
	
	public String getFaltasEmVagasDeCaronas() {
		return this.faltasEmVagasDeCaronas.toString();
	}
	
	public String getPresencasEmVagasDeCaronas() {
		return this.presencasEmVagasDeCaronas.toString();
	}

	public String visualizarPerfil() {
		return this.idPerfil;
	}

	public void setCaronasSegurasETranquilas(Integer numCaronasSegurasETranquilas) {
		this.caronasSegurasETranquilas = numCaronasSegurasETranquilas;
	}

	public String getLogin() {
		return usuario.getLogin();
	}

	public String cadastrarCarona(String idUsuario, String origem,
			String destino, String data, String hora, String vagas) throws Exception {
		return usuario.cadastrarCarona(idUsuario, origem, destino, data, hora, vagas);
	}

	public String getIdUsuario() {
		return usuario.getIdUsuario();
	}

	public boolean validaSenha(String senha) {
		return usuario.validaSenha(senha);
	}

	public Object getAtributoUsuario(String nomeAtributo) throws Exception {
		return usuario.getAtributoUsuario(nomeAtributo);
	}

	public List<String> localizarCarona(String origem, String destino) {
		return usuario.localizarCarona(origem, destino);
	}

	public Map<String, Carona> getMapIdCaronasOferecidas() {
		return usuario.getMapIdCaronasOferecidas();
	}

	public Object getAtributoCarona(String idCarona, String nomeAtributo) throws Exception {
		return usuario.getAtributoCarona(idCarona, nomeAtributo);
	}

	public String getTrajeto(String idCarona) throws Exception {
		return usuario.getTrajeto(idCarona);
	}

	public String getCarona(String idCarona) throws Exception {
		return usuario.getCarona(idCarona);
	}

	public void zerarSistema() {
		usuario.zerarSistema();
	}

	public String sugerirPontoEncontro(String idCarona, String idDonoDaCarona,
			String idDonoDaSolicitacao, String pontos) throws Exception {
		return usuario.sugerirPontoEncontro(idCarona, idDonoDaCarona, idDonoDaSolicitacao, pontos);
	}

	public void responderSugestaoPontoEncontro(String idCarona,
			String idSugestao, String pontos) throws Exception {
		usuario.responderSugestaoPontoEncontro(idCarona, idSugestao, pontos);
	}

	public String solicitarVagaPontoEncontro(String idCarona, String idDonoDaCarona,
			String idDonoDaSolicitacao, String pontos) throws Exception {
		return usuario.solicitarVagaPontoEncontro(idCarona, idDonoDaCarona, idDonoDaSolicitacao, pontos);
	}

	public String[] aceitarSolicitacaoPontoEncontro(String idSolicitacao) throws Exception {
		return usuario.aceitarSolicitacaoPontoEncontro(idSolicitacao);
	}

	public void adicionarIdCaronaAprovada(String idCarona) {
		usuario.adicionarIdCaronaAprovada(idCarona);
	}

	public String[] aceitarSolicitacao(String idSolicitacao) throws Exception {
		return usuario.aceitarSolicitacao(idSolicitacao);
	}

	public String solicitarVaga(String idCarona, String idDonoDaCarona,
			String idDonoDaSolicitacao) {
		return usuario.solicitarVaga(idCarona, idDonoDaCarona, idDonoDaSolicitacao);
	}

	public void rejeitarSolicitacao(String idSolicitacao) throws Exception {
		usuario.rejeitarSolicitacao(idSolicitacao);
	}

	public void removerSolicitacao(String idCarona, String idSolicitacao) {
		usuario.removerSolicitacao(idCarona, idSolicitacao);
	}
}
