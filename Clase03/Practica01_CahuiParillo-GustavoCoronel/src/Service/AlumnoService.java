package Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * promedio de notas.
 *
 * @param n1 nota 1.
 * @param n2 nota 2.
 * @param n3 nota 3.
 * @param n4 nota 4.
 * @param n5 nota 5.
 * @return Retorna el primedio de las 4 mejores notas.
 */
public class AlumnoService {

  public double minimo(double n1, double n2, double n3, double n4, double n5) {
    //variables 
    double[] notas = {n1,n2,n3,n4,n5};
    double menor;
    //proceso
    Arrays.sort(notas);
    menor = notas[0];
    //Reporte
    return menor;
  }

  public double promedio(double n1, double n2, double n3, double n4, double n5, double minimo) {
    //variables 
    double promedio;
    //proceso
    promedio = ((n1 + n2 + n3 + n4 + n5) - minimo) / 4;

    //Reporte
    return promedio;
  }

  public String condicion(double promedio) {
    //variables 
    String condicion;
    //proceso
    if (promedio < 14) {
      condicion = "DESAPROBADO";
    } else {
      condicion = "APROBADO";
    }

    //Reporte
    return condicion;
  }

}
