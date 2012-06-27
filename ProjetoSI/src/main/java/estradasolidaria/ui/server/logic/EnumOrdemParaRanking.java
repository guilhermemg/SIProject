package estradasolidaria.ui.server.logic;

/**
 * Enum que representa a ordem
 * de ranking dos usuarios.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumOrdemParaRanking {
	DECRESCENTE("decrescente"), CRESCENTE("crescente");
	
	private String ordem;

	EnumOrdemParaRanking(String ordem) {
		this.ordem = ordem;
	}
	
	public String getOrdem() {
		return this.ordem;
	}
}
