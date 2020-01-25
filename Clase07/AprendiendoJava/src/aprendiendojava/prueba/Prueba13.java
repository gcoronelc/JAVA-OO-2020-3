package aprendiendojava.prueba;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Prueba13 {

	public static void main(String[] args) {
		Map<String,Integer> notas;
		notas = new HashMap<>();
		
		notas.put("Gustavo", 20);
		notas.put("Milagros", 20);
		notas.put("Yanet", 20);
		notas.put("Gustavo", 15);
		
		Set<String> claves = notas.keySet();
		
		for (String clave : claves) {
			int nota = notas.get(clave);
			System.out.println(clave + " - " + nota);
		}
	}
	
}
