package estradasolidaria.ui.server.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class para manipulacao de calendario, data e hora.
 * Eh usada em Carona.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class DateUtil {
	private int dia;
	private int mes;
	private int ano;
	
	Calendar calendar;
	
	public DateUtil(Calendar calendar) {
		this.calendar= calendar;
	}
	
	/**
	 * Valida formato da data.
	 * 
	 * @param data
	 * @return true se o formato da data for valido
	 * @throws Exception
	 */
	public boolean validaData(String data) {
		try {
			String[] dataSplited = data.split("/");
			String d = dataSplited[0];
			String m = dataSplited[1];
			String a = dataSplited[2];
	
			setDia(d);
			setMes(m);
			setAno(a);
		
			final int MAIOR_ANO = Integer.MAX_VALUE, MENOR_ANO = 1970;
			
			if ((ano < MENOR_ANO) || (ano > MAIOR_ANO)) {
				return false;
			} else if (dia < 1 || dia > 31) {
				return false;
			} else if ((mes > 12) || (mes < 1)) {
				return false;
			}
			// 01 <= mes <= 12
			calendar.set(GregorianCalendar.DAY_OF_MONTH, dia);
			calendar.set(GregorianCalendar.MONTH, --mes); // 0=janeiro, 1=fevereiro, ...
			calendar.set(GregorianCalendar.YEAR, ano);
			
			if (dia > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				return false;
			}
			else {
				return true;
			}
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Data inválida");
		}
	}
	
	/**
	 * Valida se data ja passou
	 * 
	 * @return true se data ainda nao passou
	 */
	public boolean validaDataJaPassou() {
		if(calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Configura o dia.
	 * 
	 * @param d
	 * @throws Exception
	 */
	public void setDia(String d) {
		int day;
		try {
			day = Integer.parseInt(d);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Data inválida");
		}
		this.dia = day;
	}
	
	/**
	 * Configura o mes.
	 * 
	 * @param m
	 * @throws Exception
	 */
	public void setMes(String m) {
		int month;
		try {
			month = Integer.parseInt(m);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Data inválida");
		}
		this.mes = month;
	}

	/** 
	 * Configura o ano.
	 * @param a
	 * @throws Exception
	 */
	public void setAno(String a) {
		int year;
		try {
			year = Integer.parseInt(a);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Data inválida");
		}
		this.ano = year;
	}
	
	/**
	 * Valida hora.
	 * 
	 * @param hora
	 * @return true se hora eh valida
	 */
	public boolean validaHora(String hora) {
		if (!hora.contains(":"))
			throw new IllegalArgumentException("Hora inválida");
		
		try {
			String[] horaSplited = hora.split(":");
			
			int h = Integer.parseInt(horaSplited[0]);
			int m = Integer.parseInt(horaSplited[1]);
			
			if(h < 0 || h > 23 || m < 0 || m > 59)
				return false;
		
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH,	calendar.get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, h);
			calendar.set(Calendar.MINUTE, m);
			
			return true;
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Hora inválida");
		}
	}
	
	/**
	 * Retorna o Calendar nos
	 * quais as operacoes foram realizadas.
	 * 
	 * @return calendar
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}
}
