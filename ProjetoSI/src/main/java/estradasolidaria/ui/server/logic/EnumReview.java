package estradasolidaria.ui.server.logic;

public enum EnumReview {
	NAO_FALTOU("não faltou"), 
	FALTOU("faltou"), 
	NAO_FUNCIONOU("não funcionou"),
	SEGURA_E_TRANQUILA("segura e tranquila");
	
	private String nome;

	EnumReview(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	
}
