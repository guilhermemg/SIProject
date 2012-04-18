package si1project2.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class ManipulaCalendario
 * 
 * @author Andre Yuri, Leonardo Alves, Hemã Vidal;
 */
public class DateUtil {
	/**
	 * Variaveis Staticas fimDeSemana, MAIOR_ANO, MENOR_ANO,
	 * 
	 * OBJETO: calendar
	 */
	static int fimDeSemana;
	static final int MAIOR_ANO = Integer.MAX_VALUE,
			MENOR_ANO = Integer.MIN_VALUE;
	static GregorianCalendar calendar = new GregorianCalendar();
	/**
	 * @param int dia, int mes, int ano
	 * @return retorna um boolean indicando se o intervalo da data existe
	 * 
	 */
	public static boolean verificaDataValida(int dia, int mes, int ano)
	
	{
		mes--;

		if ((ano < MENOR_ANO) || (ano > MAIOR_ANO)) {
			return false;
		} else if (dia < 1) {
			return false;
		} else if ((mes > 12) || (mes < 1)) {
			return false;
		}
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);

		if (dia > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		return true;
	}

	public boolean validaData(String data) throws Exception {
		String data2 = ""+data;
		String[] data1 = data2.split("/");

		int dia, mes, ano;
		try {
			dia = Integer.parseInt(data1[0]);
			mes = Integer.parseInt(data1[1]);
			ano = Integer.parseInt(data1[2]);
		} catch (Exception e) {
			throw new Exception("Data inválida");
		}
		return verificaDataValida(dia, mes, ano);
	}

	public boolean datajapassou(String data) throws Exception {
		String[] data1 = data.split("/");
		int dia, mes, ano;
		try {
			 dia = Integer.parseInt(data1[0]);
			 mes = Integer.parseInt(data1[1]);
			 ano = Integer.parseInt(data1[2]);
		} catch (Exception e) {
			throw new Exception("Data inválida");
		}
		Calendar calendario2 = new GregorianCalendar();

		calendario2.set(Calendar.DAY_OF_MONTH, dia);
		calendario2.set(Calendar.MONTH, mes + 1);
		calendario2.set(Calendar.YEAR, ano);
		System.out.println(calendario2.compareTo(calendar));
		if (calendario2.compareTo(calendar) < 0) {
			System.out.println(calendario2.compareTo(calendar));
			return true;
		}
		return false;
	}

//	public static void main(String[] args) {
//		String data = "25/12/2011";
//		try {
//DateUtil dateUtil = new DateUtil();
//			//			System.out.println(data);
//			System.out.println(dateUtil.datajapassou(data));
////			System.out.println(data);
//			System.out.println(!dateUtil.validaData(data));
////			System.out.println(data);
//			System.out.println(dateUtil.datajapassou(data));
////			System.out.println(data);
////			System.out.println(DateUtil.validaData(data));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
