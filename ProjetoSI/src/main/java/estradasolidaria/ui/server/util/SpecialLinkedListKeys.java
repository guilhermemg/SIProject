package estradasolidaria.ui.server.util;

import java.util.LinkedList;

/**
 * Classe para sobrescrever o toString()
 * de LinkedList no formato do teste de aceitacao,
 * usa chaves para isso.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 * 
 */
public class SpecialLinkedListKeys<T> extends LinkedList<T> {

	private static final long serialVersionUID = 2333194496228525598L;

	/**
	 * Formata listas para String em formato especial.
	 */
	@Override
	public String toString() {
		String result = "{";
		for(int i = 0; i < size(); i++) {
			if(i==0)
				result += get(0);
			else
				result += "," + get(i);
		}
		return result+"}";
	}
}
