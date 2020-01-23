package aprendiendojava.prueba;

import aprendiendojava.modelo.Clase1;
import aprendiendojava.modelo.Clase2;
import aprendiendojava.modelo.Clase3;
import aprendiendojava.modelo.Clase4;

public class Prueba03 {
	
	public static void main(String[] args) {
		
		Clase2 obj = new Clase2();
		
		System.out.println("ES COMPATIBLE CON");
		System.out.println("Object: " + (obj instanceof Object?"SI":"NO"));
		System.out.println("Clase1: " + (obj instanceof Clase1?"SI":"NO"));
		System.out.println("Clase2: " + (obj instanceof Clase2?"SI":"NO"));
		System.out.println("Clase3: " + (obj instanceof Clase3?"SI":"NO"));
		System.out.println("Clase4: " + (obj instanceof Clase4?"SI":"NO"));
		
	}
}
