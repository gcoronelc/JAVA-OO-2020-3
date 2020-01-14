package prueba;

import service.CalculadoraService;

public class Prueba01 {
  
  public static void main(String[] args) {
    // Variables
    double n1, n2;
    double suma, resta, producto, cociente;
    // Datos
    n1 = 50.0;
    n2 = 23.0;
    // Proceso
    CalculadoraService service = new CalculadoraService();
    suma = service.sumar(n1, n2);
    resta = service.restar(n1, n2);
    producto = service.multiplicar(n1, n2);
    cociente = service.dividir(n1, n2);
    // Reporte
    System.out.println("DATOS");
    System.out.println("Número 1: " + n1);
    System.out.println("Número 2: " + n2);
    System.out.println("");
    System.out.println("RESULTADO");
    System.out.println("Suma: " + suma);
    System.out.println("Resta: " + resta);
    System.out.println("Producto: " + producto);
    System.out.println("Cociente: " + cociente);
  }
  
}
