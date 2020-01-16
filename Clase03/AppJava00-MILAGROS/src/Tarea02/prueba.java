/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea02;
import Tarea02.PromedioAlumno;

/**
 *
 * @author Mili
 */
public class prueba {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
      double NMayor = 0;
      double NMenor = 0;
      double Prom = 0;
      boolean Apro = true;
      double n1 = 20;
      double n2 = 18;
      double n3 = 19;
      double n4 = 15;
      double n5 = 18;
      
      PromedioAlumno PAlu;
      PAlu = new PromedioAlumno();

      NMayor = PAlu.NotaMayor(n1, n2, n3, n4, n5);
      NMenor = PAlu.NotaMenor(n1,n2,n3,n4,n5);
      Prom = PAlu.NotaPromedio(n1,n2,n3,n4,n5);
      Apro = PAlu.Aprobado(Prom);


      System.out.println("Nota mayor: "+NMayor);
      System.out.println("Nota menor: "+NMenor);
      System.out.println("Promedio: "+Prom);
      if(Apro) { 
          System.out.println("Aprobado"); }
      else {
          System.out.println("Jalado");
      }
  }  
}


