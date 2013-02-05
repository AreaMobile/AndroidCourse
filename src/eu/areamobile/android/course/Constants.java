package eu.areamobile.android.course;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	
	public final static List<Programmatore> LIST = new ArrayList<Programmatore>();

	static{
		addProg("Domenico","Java");
		addProg("Tiberio", "Java");
		addProg("Flavio", "C");
		addProg("Antonio", "Objective-C");
		addProg("Federico", "Java");
		addProg("Claudio", "C");
		addProg("Roberto", "C");
		addProg("Daniela", "Java");
		addProg("Flavia", "C");
		addProg("Diego", "Java");
		addProg("Margherita", "C");
		addProg("Camelia", "Java");
		addProg("Dario", "Java");
		addProg("Nico", "PHP");
		addProg("Giacomo", "C++");
		addProg("Francesca", "Logo");
		addProg("Leonida", "Java");
		addProg("Leonida","ML");
		addProg("Andrea", "LISP");
		
	}

	private static void addProg(String name,String l) {
		LIST.add(new Programmatore(name, l));
	}
}
