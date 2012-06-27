/**
 * 
 */
package estradasolidaria.ui.server.logic;

/**
 * Classe representante dos atributos de carona,
 * eh usada em getAtributoCarona(String).
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumCarona {
	ORIGEM("origem"),
	DESTINO("destino"),
	DATA("data"),
	HORA("hora"),
	PONTO_ENCONTRO("Ponto de Encontro"),
	VAGA("vagas"),
	EH_MUINICIPAL("ehMunicipal"),
	CIDADE("cidade"),
	MINIMO_CARONEIROS("minimoCaroneiros"), 
	EXPIRED("expired");
	
	private String nomeAtributo;
	private EnumCarona(String menssagem) {
		this.nomeAtributo = menssagem;
	}
	
	public String getNomeAtributo(){
		return nomeAtributo;
	}
}
