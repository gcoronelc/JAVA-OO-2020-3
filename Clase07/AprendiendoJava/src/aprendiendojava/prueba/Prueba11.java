package aprendiendojava.prueba;

import java.util.ArrayList;
import java.util.List;

public class Prueba11 {

	public static void main(String[] args) {
		List<Integer> notas = new ArrayList<>();
		
		notas.add(12);
		notas.add(18);
		notas.add(13);
		notas.add(15);
		
		notas.add(2, 20);
		
		// Recorrido indexado
		for (Integer nota : notas) {
			System.out.println(nota);
		}
		
	}
	
}
