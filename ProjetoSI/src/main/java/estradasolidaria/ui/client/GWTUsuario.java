package estradasolidaria.ui.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTUsuario implements IsSerializable {

	private String nome;
	private String endereco;
	private String email;
	private String login;
	private String idUsuario;
	
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	public void setNome(String nome2) {
		// TODO Auto-generated method stub
		this.nome = nome2;
	}
	
	public String getEndereco() {
		// TODO Auto-generated method stub
		return endereco;
	}

	public void setEndereco(String endereco2) {
		// TODO Auto-generated method stub
		this.endereco = endereco2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String toString(){
		return "Nome: " + this.nome + ", Login: " + this.login + ", Endereco: " + this.endereco + ", Email: " + this.email + "."; 
	}

}
