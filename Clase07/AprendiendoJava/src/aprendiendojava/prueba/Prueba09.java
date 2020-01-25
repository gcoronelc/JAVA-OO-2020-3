package aprendiendojava.prueba;

public class Prueba09 {

	public static void main(String[] args) {
		int[][] mat = new int[5][4];
		
		mat[3][2] = 34;
		mat[1][3] = 34;
		mat[2][1] = 34;
		
		for (int[] fila : mat) {
			String cadena = "";
			for (int i = 0; i < fila.length; i++) {
				int dato = fila[i];
				cadena += dato + "\t";
			}
			System.out.println(cadena);
		}
	}
	
}
