package estradasolidaria.ui.server.logic;

/**
 * Enum que representa se uma mensagem foi
 * lida ou nao.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumLida {
	LIDA("Lida"), NAO_LIDA("Não lida");
	
	private String name;

	EnumLida(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
