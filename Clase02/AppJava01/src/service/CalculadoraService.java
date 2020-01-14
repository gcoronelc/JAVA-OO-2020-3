package service;

public class CalculadoraService {

  /**
   * Suma de dos números.
   *
   * @param n1 Número 1.
   * @param n2 Número 2.
   * @return Retorna la suma de n1 y n2.
   */
  public double sumar(double n1, double n2) {
    // Varibles
    double suma;
    // Proceso
    suma = n1 + n2;
    // Reporte
    return suma;
  }

  public double restar(double n1, double n2) {
    // Varibles
    double resta;
    // Proceso
    resta = n1 - n2;
    // Reporte
    return resta;
  }

  public double multiplicar(double n1, double n2) {
    // Varibles
    double producto;
    // Proceso
    producto = n1 * n2;
    // Reporte
    return producto;
  }

  public double dividir(double n1, double n2) {
    // Varibles
    double cociente;
    // Proceso
    cociente = n1 / n2;
    // Reporte
    return cociente;
  }

}
