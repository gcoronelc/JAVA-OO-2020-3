package prueba;

import modelo.Porcentaje;
import modelo.ConsumoCliente;

public class prueba1 {

	public static void main(String[] args) {
		double resultado, res1;
		double n1 = 100;
		double n2 = 18;
		double tot = 200;
		int porceImpuesto = 19;
		int porceServicio = 12;

		Porcentaje porce = new Porcentaje();
		ConsumoCliente consumo = new ConsumoCliente();

		// prueba de la clase Porcentaje
		//   resultado =  porce.calcula(n1, n2);
		porce.setValor(n1);
		porce.setPorciento(n2);
		resultado = porce.calcula();
		res1 = porce.getResultado();

		System.out.println(" el " + n2 + "% de " + n1 + " es " + resultado);
		System.out.println(" el " + n2 + "% de " + n1 + " es " + res1);

		//prueba de la clase ConsumoCliente
		consumo.setTotal(tot);
		consumo.setPorceImpuesto(porceImpuesto);
		consumo.setPorceServicio(porceServicio);
		consumo.calcula();
		System.out.println("Consumo: " + consumo.getConsumo());
		System.out.println("Impuesto: " + consumo.getImpuesto());
		System.out.println("total: " + consumo.getTotal());
		System.out.println("servicio: " + consumo.getServicio());
		System.out.println("Total general: " + consumo.gettGeneral());
	}
}
