package estradasolidaria.ui.server.logic;

import java.util.Calendar;
import java.util.GregorianCalendar;

import estradasolidaria.ui.server.util.DateUtil;

/**
 * Classe que monitora o tempo ate a carona ser realizada,
 * quando esse momento chega a carona entra em estado de 
 * ocorrendo automaticamente.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class ThreadMonitoramentoDeOcorrenciaDeCarona extends Thread implements Runnable {
	private Carona carona;
	private DateUtil dateUtil = new DateUtil(new GregorianCalendar());
	
	public ThreadMonitoramentoDeOcorrenciaDeCarona(String nome, Carona carona) {
		super(nome);
		this.carona = carona;
	}

	@Override
	public void run() {
		Long caronaTime = dateUtil.getTimeDaCarona(carona.getData(), carona.getHora()).getTimeInMillis();
		try {
			sleep(caronaTime - Calendar.getInstance().getTimeInMillis());
			carona.realizarCarona();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
