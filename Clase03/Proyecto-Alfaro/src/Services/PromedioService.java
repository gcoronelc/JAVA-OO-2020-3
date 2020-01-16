package Services;

//eggc.uni@gmail.com
import javax.swing.JOptionPane;

public class PromedioService {

  public double MenorNota(double n1, double n2, double n3, double n4, double n5) {
    //variables
    double menor = 0;

    //proceso
    if (n1 < n2 && n1 < n3 && n1 < n4 && n1 < n5) {
      menor = n1;
    } else if (n2 < n1 && n2 < n3 && n2 < n4 && n2 < n5) {
      menor = n2;
    } else if (n3 < n1 && n3 < 2 && n3 < n4 && n3 < n5) {
      menor = n3;
    } else if (n4 < n1 && n4 < n2 && n4 < n3 && n4 < n5) {
      menor = n4;
    } else if (n5 < n1 && n5 < n2 && n5 < n3 && n5 < n4) {
      menor = n5;
    } else {
      JOptionPane.showOptionDialog(null, "Por favor escriba otras notas, la nota menor no se debe repetir",
              "AVISO", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      System.exit(0);
    }

    //reporte    
    return menor;
  }

  public double Promedio(double n1, double n2, double n3, double n4, double n5) {
    //Variables
    double promedio;
    double menor;

    //Proceso
    if (n1 < n2 && n1 < n3 && n1 < n4 && n1 < n5) {
      menor = n1;
      promedio = (n2 + n3 + n4 + n5) / 4;

    } else if (n2 < n1 && n2 < n3 && n2 < n4 && n2 < n5) {
      menor = n2;
      promedio = (n1 + n3 + n4 + n5) / 4;

    } else if (n3 < n1 && n3 < 2 && n3 < n4 && n3 < n5) {
      menor = n3;
      promedio = (n1 + n2 + n4 + n5) / 4;

    } else if (n4 < n1 && n4 < n2 && n4 < n3 && n4 < n5) {
      menor = n4;
      promedio = (n1 + n2 + n3 + n5) / 4;

    } else {
      menor = n5;
      promedio = (n1 + n2 + n3 + n4) / 4;

    }
    return promedio;
  }

}
