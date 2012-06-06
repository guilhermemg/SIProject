package estradasolidaria.ui.server.logic;

import java.io.Serializable;

/**
 * Classe que representa uma sessao aberta pelo
 * usuario cadastrado.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Sessao implements Serializable {
	private static final long serialVersionUID = 2692231013716399639L;
	
	private Integer idUser;
	private Integer idSessao;

	/**
	 * Construtor da classe Sessao.
	 * 
	 * @param idUsuario
	 */
	public Sessao(Integer idUsuario) {
		setIdUser(idUsuario);
		setId(this.hashCode());// id da propria sessao
	}
	
	/**
	 * Retorna o id da sessao.
	 * 
	 * @return idSessao
	 */
	public Integer getIdSessao() {
		return idSessao;
	}

	/**
	 * Configura o id da sessao.
	 * 
	 * @param idSessao
	 */
	public void setIdSessao(Integer idSessao) {
		this.idSessao = idSessao;
	}
	
	/**
	 * Retorna o id do usuario.
	 * 
	 * @return idUsuario
	 */
	public Integer getIdUser() {
		return idUser;
	}
	
	/**
	 * Configura id da sessao.
	 * 
	 * @param hashCode
	 */
	private void setId(Integer hashCode) {
		this.idSessao = hashCode;
	}

	/**
	 * Configura o id do usuario.
	 * 
	 * @param idUsuario
	 */
	private void setIdUser(Integer idUsuario) {
		this.idUser = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSessao == null) ? 0 : idSessao.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Sessao)) {
			return false;
		}
		Sessao other = (Sessao) obj;
		if (idSessao == null) {
			if (other.idSessao != null) {
				return false;
			}
		} else if (!idSessao.equals(other.idSessao)) {
			return false;
		}
		if (idUser == null) {
			if (other.idUser != null) {
				return false;
			}
		} else if (!idUser.equals(other.idUser)) {
			return false;
		}
		return true;
	}
}
