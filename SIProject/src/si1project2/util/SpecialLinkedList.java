package si1project2.util;

import java.util.LinkedList;

public class SpecialLinkedList<T> extends LinkedList<T> {
	@Override
	public String toString() {
		String result = "{";
		for(int i = 0; i < size(); i++) {
			if(i==0)
				result += get(0);
			else if(i==size()-1)
				result += "," + get(i);
			else
				result += "," + get(i);
		}
		return result+"}";
	}
}
