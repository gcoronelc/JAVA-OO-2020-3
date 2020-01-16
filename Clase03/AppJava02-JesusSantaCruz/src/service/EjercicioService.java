package service;

public class EjercicioService {

  public double MenorNota(double n1, double n2, double n3, double n4, double n5) {
    // Variables
    double men;
    double arr[] = {n1, n2, n3, n4, n5};
    // Proceso
    men = 20;
    if ((n1 >= 0 && n1 <= 20) && (n2 >= 0 && n2 <= 20) && (n3 >= 0 && n3 <= 20) && (n4 >= 0 && n4 <= 20) && (n5 >= 0 && n5 <= 20)) {
      for (int i = 0; i < 5; i++) {
        if (arr[i] < men) {
          men = arr[i];
        }
      }
    } else {// Reporte
      return 0;
    }
    // Reporte
    return men;
  }

  public double PromedioNotas(double n1, double n2, double n3, double n4, double n5) {
    // Variables
    double men, sum, promedio;
    double arr[] = {n1, n2, n3, n4, n5};
    // Proceso
    men = 20;
    sum = 0;
    promedio = 0;
    if ((n1 >= 0 && n1 <= 20) && (n2 >= 0 && n2 <= 20) && (n3 >= 0 && n3 <= 20) && (n4 >= 0 && n4 <= 20) && (n5 >= 0 && n5 <= 20)) {
      for (int i = 0; i < 5; i++) {
        if (arr[i] < men) {
          men = arr[i];
        }
      }
      for (int i = 0; i < 5; i++) {
        sum = sum + arr[i];
      }
    } else {//Reporte
      return 0;
    }
    promedio = (sum - men) / 4;
    //Reporte
    return promedio;
  }
}
