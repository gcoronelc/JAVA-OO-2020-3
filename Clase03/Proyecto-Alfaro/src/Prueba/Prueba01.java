package Prueba;

import Services.PromedioService;
import javax.swing.JOptionPane;

public class Prueba01 {

  public static void main(String[] args) {
    //variables
    double n1, n2, n3, n4, n5;
    double menor = 0, promedio;

    //datos
    n1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 1"));
    n2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 2"));
    n3 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 3"));
    n4 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 4"));
    n5 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 5"));

    //Proceso
    PromedioService service = new PromedioService();
    menor = service.MenorNota(n1, n2, n3, n4, n5);
    promedio = service.Promedio(n1, n2, n3, n4, n5);

    //Reporte
    System.out.println("DATOS");
    System.out.println("Nota 1: " + n1);
    System.out.println("Nota 2: " + n2);
    System.out.println("Nota 3: " + n3);
    System.out.println("Nota 4: " + n4);
    System.out.println("Nota 5: " + n5);
    System.out.println("");
    System.out.println("Nota menor : " + menor);
    System.out.println("El promedio es: " + promedio);
    if (service.Promedio(n1, n2, n3, n4, n5) >= 14) {
      System.out.println("Condicion: APROBADO");
    } else {
      System.out.println("Condicion: DESAPROBADO");
    }

  }
}
