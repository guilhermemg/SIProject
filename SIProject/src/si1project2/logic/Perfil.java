package si1project2.logic;

import java.util.LinkedList;
import java.util.List;

public class Perfil {
	private String nome;
	private String endereco;
	private String email;
	private String idPerfil;
	private List<String> historicoDeCaronas;
	private List<String> historicoDeVagasEmCaronas;
	private Integer caronasSegurasETranquilas;
	private Integer caronasQueNaoFuncionaram;
	private Integer faltasEmVagasDeCaronas;
	private Integer presencasEmVagasDeCaronas;
	
	
	public Perfil(String nome, String endereco, String email) {
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setHistoricoDeCaronas(new LinkedList<String>());
		setHistoricoDeVagasEmCaronas(new LinkedList<String>());
		setIdPerfil(hashCode() + "");
	}
	
	private void setHistoricoDeVagasEmCaronas(LinkedList<String> historico) {
		this.historicoDeVagasEmCaronas = historico;
	}

	private void setHistoricoDeCaronas(LinkedList<String> historico) {
		this.historicoDeCaronas = historico;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		}else if (atributo.equals("historico de caronas")) {
			return getHistoricoDeCaronas();
		}else if (atributo.equals("historico de vagas em caronas")) {
			return getHistoricoDeVagasEmCaronas();
		}else if (atributo.equals("caronas seguras e tranquilas")) {
			return getCaronasSegurasETranquilas();
		}else if (atributo.equals("caronas que não funcionaram")) {
			return getCaronasQueNaoFuncionaram();
		}else if (atributo.equals("faltas em vagas de caronas")) {
			return getFaltasEmVagasDeCaronas();
		}else if (atributo.equals("presenças em vagas de caronas")) {
			return getPresencasEmVagasDeCaronas();
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
		return this.historicoDeCaronas;
	}
	
	public List<String> getHistoricoDeVagasEmCaronas() {
		return this.historicoDeVagasEmCaronas;
	}
	
	public String getCaronasSegurasETranquilas() {
		return this.caronasSegurasETranquilas.toString();
	}
	
	public String getCaronasQueNaoFuncionaram() {
		return this.caronasQueNaoFuncionaram.toString();
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
}
