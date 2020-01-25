package aprendiendojava.prueba;

public class Prueba07 {

	public static void main(String[] args) {
		int[] notas = new int[4];
		
		notas[1] = 20;
		
		// Recorrido indexado
		for (int i = 0; i < notas.length; i++) {
			int nota = notas[i];
			System.out.println(nota);
		}
		
	}
	
}
