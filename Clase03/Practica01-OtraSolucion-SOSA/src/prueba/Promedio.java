package prueba;

import service.PromedioFinal;

public class Promedio {
  
  public static void main(String[] args) {
      
    // Variables
    double p1,p2,p3,p4,p5;
    double eparcial;
    double efinal;
    
    // Datos
    p1 = 15.0;
    p2 = 18.0;
    p3 = 20.0;
    p4 = 11.0;
    p5 = 17.0;
    
    eparcial=12;
    efinal=15;
    
    // Proceso
   PromedioFinal pf= new PromedioFinal();
   pf.promedioPracticas(p1, p2, p3, p4, p5);
   pf.promedioFinal(eparcial, efinal);
   pf.menorNota(p1, p2, p3, p4, p5);
   
    // Reporte
      System.out.println("DATOS DEL PROMEDIO REALIZADO");
      System.out.println(" ");
      System.out.println("PROMEDIO DE PRACTICAS: "+pf.getPp());
      System.out.println("PROMEDIO DE EXAMEN PARCIAL: "+pf.getEparcial());
      System.out.println("PROMEDIO DE EXAMEN FINAL: "+pf.getEfinal());
      System.out.println("LA MENOR NOTA ES: "+pf.getMenorNota());
      System.out.println("PROMEDIO FINAL DEL ALUMNO: "+pf.getPfinal());
      System.out.println("CONDICION: "+pf.condicion());
  
  }
  
}
