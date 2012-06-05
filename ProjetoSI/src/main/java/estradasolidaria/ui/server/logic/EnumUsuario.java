package estradasolidaria.ui.server.logic;

/**
 * Classe representante dos atributos do usuario,
 * eh usada em getAtributo(String) e em getAtributoPerfil(String).
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumUsuario {
	HISTORICO_DE_CARONAS("historico de caronas"),
	HISTORICO_DE_VAGAS_EM_CARONAS("historico de vagas em caronas"),
	CARONAS_SEGURAS_E_TRANQUILAS("caronas seguras e tranquilas"),
	CARONAS_QUE_NAO_FUNCIONARAM("caronas que não funcionaram"),
	FALTAS_EM_VAGAS_DE_CARONAS("faltas em vagas de caronas"),
	PRESENCAS_EM_VAGAS_DE_CARONAS("presenças em vagas de caronas"),
	NOME("nome"),
	ENDERECO("endereco"),
	EMAIL("email"),
	LOGIN("login"),
	SENHA("senha");
	
	private String nomeAtributo;
	private EnumUsuario(String menssagem) {
		this.nomeAtributo = menssagem;
	}
	
	public String getNomeAtributo(){
		return nomeAtributo;
	}
}
