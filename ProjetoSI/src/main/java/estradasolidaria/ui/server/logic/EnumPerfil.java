/**
 * 
 */
package estradasolidaria.ui.server.logic;

/**
<<<<<<< HEAD
 * Classe representante dos atributos do perfil do usuario,
 * eh usada em getAtributoPerfil(String).
=======
>>>>>>> branch 'master' of ssh://git@github.com/guilhermemg/SIProject.git
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumPerfil {
	HISTORICO_DE_CARONAS("historico de caronas"),
	HISTORICO_DE_VAGAS_EM_CARONAS("historico de vagas em caronas"),
	CARONAS_SEGURAS_E_TRANQUILAS("caronas seguras e tranquilas"),
	CARONAS_QUE_NAO_FUNCIONARAM("caronas que não funcionaram"),
	FALTAS_EM_VAGAS_DE_CARONAS("faltas em vagas de caronas"),
	PRESENCAS_EM_VAGAS_DE_CARONAS("presenças em vagas de caronas"),
	NOME("nome"),
	ENDERECO("endereco"),
	EMAIL("email");
	
	private String nomeAtributo;
	private EnumPerfil(String menssagem) {
		this.nomeAtributo = menssagem;
	}
	
	public String getNomeAtributo(){
		return nomeAtributo;
	}
}
