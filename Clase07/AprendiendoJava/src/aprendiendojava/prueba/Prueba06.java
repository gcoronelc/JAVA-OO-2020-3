package aprendiendojava.prueba;

import aprendiendojava.modelo.Clase1;
import aprendiendojava.modelo.Clase2;

public class Prueba06 {

	public static void main(String[] args) {
		Clase1 obj1 = new Clase2();
		Clase2 obj2 = (Clase2) obj1;
		
		System.out.println("9 + 9 = " + obj2.sumar(9, 9));
	}
	
}
