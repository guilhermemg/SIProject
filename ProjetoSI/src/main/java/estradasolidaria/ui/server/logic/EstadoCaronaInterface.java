package estradasolidaria.ui.server.logic;

import javax.mail.MessagingException;

/**
 * Classe que representa o estado
 * de uma carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public interface EstadoCaronaInterface { 
	/**
	 * Muda estado da carona para confirmada.
	 * 
	 * @param carona
	 * 	 * @throws CaronaInvalidaException 
	 * @throws EstadoCaronaException 
	 */
	public void confirmar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException;
	
	/**
	 * Muda estado da carona para cancelada.
	 * 
	 * @param carona
	 * @throws MessagingException 
	 */
	public void cancelar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException, MessagingException;
	
	/**
	 * Muda estado da carona para ocorrendo.
	 * 
	 * @param carona
	 * @throws CaronaInvalidaException 
	 * @throws EstadoCaronaException 
	 */
	public void realizar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException;
	
	/**
	 * Muda estado da carona para encerrada.
	 * 
	 * @param carona
	 * @throws CaronaInvalidaException 
	 * @throws EstadoCaronaException 
	 */
	public void encerrar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException;;
	
	/**
	 * Muda estado da carona para expirada.
	 * 
	 * @param carona
	 * @throws CaronaInvalidaException 
	 * @throws EstadoCaronaException 
	 */
	public void expirar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException;

	/**
	 * Retorna nome do estado da carona.
	 * 
	 * @return nome do estado
	 */
	public EnumNomeEstadoDaCarona getNomeEstado();

	/**
	 * Muda estado da carona para esperando.
	 * 
	 * @param carona
	 * @throws EstadoCaronaException 
	 * @throws CaronaInvalidaException 
	 */
	public void esperar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException, CaronaInvalidaException;
}
