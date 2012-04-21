package si1project2.data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import si1project2.logic.Carona;
import si1project2.logic.Sessao;
import si1project2.logic.Solicitacao;
import si1project2.logic.Usuario;

/**
 * Classe responsavel por fazer
 * a persistencia de dados e a 
 * manipulacao destes na base do
 * sistema. Ela contem tambem os
 * mapas do sistema, sendo instanciada
 * nas classes usuarias do gerenciador.
 * 
 * @author guilhermemg
 *
 */
public class GerenciadorDeDados {
	// usados em SistemaCaronas ------------------------------------------------------------------------------
//	private Map<String, Sessao> mapIdSessao = new TreeMap<String, Sessao>(); // contem apenas sessoes abertas
//	private Map<String, Usuario> mapIdUsuario = new TreeMap<String, Usuario>();
	// -------------------------------------------------------------------------------------------------------
	
	// usados em Carona, em Usuario e em Perfil --------------------------------------------------------------
	//private Map<String, Carona> mapIdCaronasOferecidas = new TreeMap<String, Carona>();
	//private Map<String, Carona> mapIdCaronasPegas = new TreeMap<String, Carona>();
	//private Map<String, Solicitacao> mapIdSolicitacao = new TreeMap<String, Solicitacao>();
//	private Map<String, List<Usuario>> mapIdCaronaUsuariosPresentes = new TreeMap<String, List<Usuario>>();
	// -------------------------------------------------------------------------------------------------------
	
	/*public Map<String, Carona> getMapIdCaronasOferecidas() {
		return mapIdCaronasOferecidas;
	}

	public void setMapIdCaronasOferecidas(Map<String, Carona> mapIdCaronasOferecidas) {
		this.mapIdCaronasOferecidas = mapIdCaronasOferecidas;
	}

	public Map<String, Solicitacao> getMapIdSolicitacao() {
		return mapIdSolicitacao;
	}

	public void setMapIdSolicitacao(Map<String, Solicitacao> mapIdSolicitacao) {
		this.mapIdSolicitacao = mapIdSolicitacao;
	}

	public Map<String, Carona> getMapIdCaronasPegas() {
		return mapIdCaronasPegas;
	}

	public void setMapIdCaronasPegas(Map<String, Carona> mapIdCaronasPegas) {
		this.mapIdCaronasPegas = mapIdCaronasPegas;
	}*/

//	public Map<String, Sessao> getMapIdSessao() {
//		return mapIdSessao;
//	}
//
//	public void setMapIdSessao(Map<String, Sessao> mapIdSessao) {
//		this.mapIdSessao = mapIdSessao;
//	}
//
//	public Map<String, Usuario> getMapIdUsuario() {
//		return mapIdUsuario;
//	}
//
//	public void setMapIdUsuario(Map<String, Usuario> mapIdUsuario) {
//		this.mapIdUsuario = mapIdUsuario;
//	}

}
