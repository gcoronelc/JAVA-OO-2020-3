package javaapplication1.service;

public class javaService {

  public double minimo(double notas[], int n) {
    //variables
    double minimo = 20;
    //datos
    n = 5;
    //proceso
    for (int i = 0; i < n; i++) {
      if (notas[i] < minimo) {
        minimo = notas[i];
      }
    }
    //retorno
    return minimo;
  }

  public double promedio(double notas[], int n) {
    //variables
    double promedio;
    double sum = 0;
    //datos
    n = 5;
    //proceso
    for (int i = 0; i < n; i++) {
      sum = sum + notas[i];
    }
    sum = sum - minimo(notas, n);
    promedio = sum / 4;
    //retorno
    return promedio;
  }

  public boolean condicion(double promedio) {
    boolean condicion = true;
    if (promedio < 14) {
      condicion = false;
    }

    return condicion;
  }

}
