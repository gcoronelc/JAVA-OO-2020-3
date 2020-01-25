package aprendiendojava.prueba;

import aprendiendojava.service.impl.MateImpl;
import aprendiendojava.service.spec.MateSpec;

public class Prueba01 {
	
	public static void main(String[] args) {
		// Variables
		int n1, n2, suma, resta;
		// Datos
		n1 = 21;
		n2 = 18;
		// Proceso
		MateSpec mateService = new MateImpl();
		suma = mateService.sumar(n1, n2);
		resta = mateService.restar(n1, n2);
		// Reporte
		System.out.println("DATOS");
		System.out.println("Número 1: " + n1);
		System.out.println("Número 2: " + n2);
		System.out.println("REPORTE");
		System.out.println("Suma: " + suma);
		System.out.println("Resta: " + resta);
	}
}
