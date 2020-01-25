package aprendiendojava.prueba;

import java.util.ArrayList;
import java.util.List;

public class Prueba10 {

	public static void main(String[] args) {
		List<Integer> notas = new ArrayList<>();
		
		notas.add(12);
		notas.add(18);
		notas.add(13);
		notas.add(15);
		
		// Recorrido indexado
		for (int i = 0; i < notas.size(); i++) {
			System.out.println(notas.get(i));
		}
		
	}
	
}
