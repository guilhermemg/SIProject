package estradasolidaria.ui.server.logic;

import java.util.Calendar;
import java.util.GregorianCalendar;

import estradasolidaria.ui.server.util.DateUtil;

/**
 * Classe responsavel por acompanhar
 * a execucao do intervalo de tempo (ate 48h antes da carona começar)
 * dado para o registro de caroneiros
 * em uma carona relampago. Quando
 * chegar o momento de 48h antes de a carona comecar e a carona
 * nao tiver o minimo de caroneiros determinado, ela eh automaticamente
 * cancelada pelo sistema. 
 * 
 * obs.: nao sendo essa mesma carona preferencial, qualquer usuario
 * pode se registrar nela, pedindo uma vaga na carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class ThreadIntervaloDeTempoParaRegistroEmCaronaRelampago extends Thread 
 	implements Runnable {
	
	private Carona carona;
	private DateUtil dateUtil = new DateUtil(new GregorianCalendar()); 

	public ThreadIntervaloDeTempoParaRegistroEmCaronaRelampago(String name, Carona carona) {
		super(name);
		this.carona = carona;
	}
	
	@Override
	public void run() {
		Long actualTime = Calendar.getInstance().getTimeInMillis();
		Calendar timeDaCaronaMenos48h = dateUtil.getTimeDaCarona(carona.getData(), carona.getHora());
		timeDaCaronaMenos48h.add(Calendar.DAY_OF_MONTH, -2); // 2 dias == 48h
		EstadoDaCarona estadoAtual = carona.getEstadoDaCarona();
		try {
			sleep(timeDaCaronaMenos48h.getTimeInMillis() - actualTime);
			if(carona.getLimiteVagas() - carona.getVagas() < carona.getMinimoCaroneiros()) {
				// quantidade de caroneiros eh menor q numero de vagas ocupadas ate agora
				carona.cancelarCarona();
			}
			else {
				carona.setEstadoDaCarona(estadoAtual);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
