package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe responsavel por acompanhar
 * a execucao do intervalo de tempo (24h)
 * dado para o registro de caroneiros preferenciais
 * em uma carona preferencial. Somente caroneiros
 * na lista de caroneiros preferenciais do usuario
 * dono da carona pode se registrar na carona, os demais
 * tem que esperar o intervalo de tempo monitorado por esta
 * thread acabar.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class ThreadIntervaloDeTempoParaRegistroEmCaronaPreferencial extends Thread 
	implements Runnable, Serializable {
	private static final long serialVersionUID = -3593260910123320831L;
	private Carona carona;
	
	public ThreadIntervaloDeTempoParaRegistroEmCaronaPreferencial(String name, Carona carona) {
		super(name);
		this.carona = carona;
	}
	
	/**
	 * Faz a Thread dormir por 24h para q somente caroneiros
	 * que estao na lista de caroneiros preferenciais possam
	 * solicitar uma vaga na carona. 
	 */
	@Override
	public void run() {
		Calendar actualTimeMais24h = Calendar.getInstance();
		actualTimeMais24h.add(Calendar.HOUR_OF_DAY, 24); // acrescenta 24 horas a hora atual
		try {
			EstadoCaronaInterface estadoAtual = carona.getEstadoDaCarona();
			sleep(actualTimeMais24h.getTimeInMillis() - 
					Calendar.getInstance().getTimeInMillis()); 
			carona.setEstadoDaCarona(estadoAtual);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
