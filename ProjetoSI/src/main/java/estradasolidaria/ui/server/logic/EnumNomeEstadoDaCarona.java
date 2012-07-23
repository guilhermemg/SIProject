package estradasolidaria.ui.server.logic;

/**
 * Enum que representa o nome do estado
 * da carona.
 *
 */
public enum EnumNomeEstadoDaCarona {
	OCORRENDO("Ocorrendo"),
	ENCERRADA("Encerrada"), 
	EXPIRED("Expired"), 
	CANCELADA("Cancelada"),
	CONFIRMADA("Confirmada"),
	ESPERANDO("Esperando");
	
	private String nome;
	
	private EnumNomeEstadoDaCarona(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
