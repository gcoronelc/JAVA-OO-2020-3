package aprendiendojava.modelo;

public class Clase2 extends Clase1 {

	public Clase2() {
		super("MILAGROS");
	}
	
	

	/**
	 * Nueva forma de sumar creda por alumnos de SistemasUNI
	 *
	 * @param n1 Número 1
	 * @param n2 Numero 2
	 * @return Retorna la nueva suma
	 */
	@Override
	public int sumar(int n1, int n2) {
		int suma = super.sumar(n1, n2);
		int resta = n1 - n2;
		return suma * resta;
	}

	public int restar(int n1, int n2) {
		return (n1 - n2);
	}

}
