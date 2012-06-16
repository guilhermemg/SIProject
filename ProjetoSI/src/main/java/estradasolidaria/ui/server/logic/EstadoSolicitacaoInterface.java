package estradasolidaria.ui.server.logic;

/**
 * Interface que representa um estado
 * de solicitacao.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public interface EstadoSolicitacaoInterface {
	public void aceitar(Solicitacao s, Carona c) throws CaronaInexistenteException;
	public void cancelar(Solicitacao s);
	public void rejeitar(Solicitacao s, Carona c);
}
