package aprendiendojava.prueba;

import java.util.HashSet;
import java.util.Set;

public class Prueba12 {

	public static void main(String[] args) {
		Set<Integer> notas = new HashSet<>();
		
		notas.add(12);
		notas.add(13);
		notas.add(20);
		notas.add(15);
		notas.add(18);
		
		// Recorrido indexado
		for (Integer nota : notas) {
			System.out.println(nota);
		}
		
	}
	
}
