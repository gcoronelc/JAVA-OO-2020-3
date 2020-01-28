package pe.uni.elbuensabor.util;

import javax.swing.JLabel;

public class Validaciones {

	private Validaciones() {
	}

	public static boolean validarNumeroPositivo(String texto, JLabel mensaje){
		// Varibles
		int numero;
		// Proceso
		mensaje.setText("");
		try {
			numero =Integer.parseInt(texto);
		} catch (Exception e) {
			mensaje.setText("Dato incorrecto.");
			return false;
		}
		if( numero < 1){
			mensaje.setText("Solo números positivos.");
			return false;
		}
		// Reporte
		return true;
	}
	
}
