package estradasolidaria.ui.server.logic;

/**
 * Classe que representa os
 * atributo de uma carona relampago
 * expirada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumExpired {
	EMAIL_TO("emailTo");
	
	private String nome;

	EnumExpired(String nome) {
		this.nome = nome;
	}
	
	public String getNomeAtributo() {
		return this.nome;
	}
}
