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
	
	GregorianCalendar calendar = new GregorianCalendar();

	/**
	 * Valida formato da data.
	 * 
	 * @param data
	 * @return true se o formato da data for valido
	 * @throws Exception
	 */
	public boolean validaData(String data) {
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
		calendar.set(Calendar.MONTH, --mes); // 0=janeiro, 1=fevereiro, ...
		calendar.set(Calendar.YEAR, ano);

		if (dia > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		else {
			return !verificaDataJaPassou();
		}
	}

	/**
	 * Verifica se data ja passou.
	 * 
	 * @param data
	 * @return true se data ja passou
	 */
	public boolean verificaDataJaPassou() {
		return calendar.getTimeInMillis() < getDataAtualInMillis() ? true : false;
	}

	/**
	 * Retorna o dia.
	 * 
	 * @return dia
	 */
	public int getDia() {
		return dia;
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
	 * Retorna o mes.
	 * @return mes
	 */
	public int getMes() {
		return mes;
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
	 * Retorna o ano.
	 * 
	 * @return ano
	 */
	public int getAno() {
		return ano;
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
	 * Retorna data atual em milisegundos.
	 * 
	 * @return data atual.
	 */
	public long getDataAtualInMillis() {
		return Calendar.getInstance().getTimeInMillis();
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
		
		if(hora.length() == 5)  { // date format == hh:mm
			if( Character.isDigit(hora.charAt(0))
				&& Character.isDigit(hora.charAt(1))
				&& !Character.isDigit(hora.charAt(2))
				&& hora.charAt(2) == ':' && Character.isDigit(hora.charAt(3))
				&& Character.isDigit(hora.charAt(4))) {
				int h = Character.digit(hora.charAt(0), 10)*10 + Character.digit(hora.charAt(1), 10);
				int m = Character.digit(hora.charAt(3), 10)*10 + Character.digit(hora.charAt(4), 10);
				return h >= 0 && h < 24 && m < 60 && m >= 0 ? true : false;
			}
		}
		else if(hora.length() == 4) { // date format == h:mm
			if( Character.isDigit(hora.charAt(0)) 
					&& !Character.isDigit(hora.charAt(1))
					&& hora.charAt(1) == ':' && Character.isDigit(hora.charAt(2))
					&& Character.isDigit(hora.charAt(3))) {
				int h = Character.digit(hora.charAt(0), 10);
				int m = Character.digit(hora.charAt(2), 10)*10 + Character.digit(hora.charAt(3), 10);
				return h >= 0 && h < 24 && m < 60 && m >= 0 ? true : false;
			}
		}
		throw new IllegalArgumentException("Hora inválida");
	}
}
