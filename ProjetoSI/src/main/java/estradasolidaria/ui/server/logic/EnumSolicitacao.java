package estradasolidaria.ui.server.logic;

public enum EnumSolicitacao {
	ORIGEM("origem"),
	DESTINO("destino"),
	DONO_CARONA("Dono da carona"),
	DONO_SOLICITACAO("Dono da solicitacao"),
	PONTO_ENCONTRO("Ponto de Encontro"),
	ESTADO_SOLICITACAO("Estado solicitacao");
		
	
	private String nomeAtributo;
	private EnumSolicitacao(String menssagem) {
		this.nomeAtributo = menssagem;
	}
	
	public String getNomeAtributo(){
		return nomeAtributo;
	}
}
