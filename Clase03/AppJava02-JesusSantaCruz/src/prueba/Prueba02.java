package prueba;

import service.EjercicioService;

public class Prueba02 {

  public static void main(String[] args) {
    // Variables
    double n1, n2, n3, n4, n5;
    double menorn, prom;
    // Datos
    n1 = 20;
    n2 = 12.3;
    n3 = 17;
    n4 = 8;
    n5 = 17;
    // Proceso
    EjercicioService ejerc = new EjercicioService();
    menorn = ejerc.MenorNota(n1, n2, n3, n4, n5);
    prom = ejerc.PromedioNotas(n1, n2, n3, n4, n5);
    // Reporte
    System.out.println("DATOS");
    System.out.println("Nota 1: " + n1);
    System.out.println("Nota 2: " + n2);
    System.out.println("Nota 3: " + n3);
    System.out.println("Nota 4: " + n4);
    System.out.println("Nota 5: " + n5);
    System.out.println("RESULTADO");
    System.out.println("La menor nota es: " + menorn);
    System.out.println("El promedio de notas es: " + prom);
  }
}
