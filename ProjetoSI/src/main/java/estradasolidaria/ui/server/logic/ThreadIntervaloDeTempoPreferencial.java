package estradasolidaria.ui.server.logic;

import java.util.Calendar;

/**
 * Classe responsavel por acompanhar
 * a execucao do intervalo de tempo (24h)
 * dado para o registro de caroneiros preferenciais
 * em uma carona preferencial.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class ThreadIntervaloDeTempoPreferencial extends Thread implements Runnable {
	private Carona carona;
	
	public ThreadIntervaloDeTempoPreferencial(String name, Carona carona) {
		super(name);
		this.carona = carona;
	}
	
	@Override
	public void run() {
		Calendar actualTimeMais24h = Calendar.getInstance();
		actualTimeMais24h.add(Calendar.HOUR_OF_DAY, 24); // acrescenta 24 horas a hora atual
		try {
			sleep(actualTimeMais24h.getTimeInMillis() - 
					Calendar.getInstance().getTimeInMillis()); 
			EstadoDaCarona estadoAtual = carona.getEstadoDaCarona();
			if(carona.getLimiteVagas() - carona.getVagas() < carona.getMinimoCaroneiros()) {
				// quantidade de caroneiros eh menor q numero de vagas ocupadas ate agora
				carona.cancelarCarona();
			}
			else {
				carona.setEstadoDaCarona(estadoAtual);
				carona.setIsFinalizedTimeInterval(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
