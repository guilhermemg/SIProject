package si1project2.logic;

public class Perfil {
	private String nomeEmPerfil;
	private String enderecoEmPerfil;
	private String emailEmPerfil;
	private String idPerfil;
	
	public Perfil(String nomeEmPerfil, String enderecoEmPerfil, String emailEmPerfil) {
		setNomeEmPerfil(nomeEmPerfil);
		setEnderecoEmPerfil(enderecoEmPerfil);
		setEmailEmPerfil(emailEmPerfil);
		
		setIdPerfil(hashCode() + "");
	}
	
	public String getNomeEmPerfil() {
		return nomeEmPerfil;
	}

	public void setNomeEmPerfil(String nomeEmPerfil) {
		this.nomeEmPerfil = nomeEmPerfil;
	}

	public String getEnderecoEmPerfil() {
		return enderecoEmPerfil;
	}

	public void setEnderecoEmPerfil(String enderecoEmPerfil) {
		this.enderecoEmPerfil = enderecoEmPerfil;
	}

	public String getEmailEmPerfil() {
		return emailEmPerfil;
	}

	public void setEmailEmPerfil(String emailEmPerfil) {
		this.emailEmPerfil = emailEmPerfil;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	
	
	public Object getAtributoPerfil(String atributo) {
		// TODO Auto-generated method stub
		return null;
	}
	//TODO
	//TODO
}
