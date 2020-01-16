
package prueba;

import javaapplication1.service.javaService;


public class Prueba {
        public static void main(String[] args) {
        //datos 
            double[] notas = new double[5];
           notas[0]= 12;
           notas[1]=15;
           notas[2]=14;
           notas[3]=17;
           notas[4]=12;
        //pedidos
        double minimo,promedio;
        boolean condicion;
    javaService service=new javaService(); 
    minimo=service.minimo(notas, 5);
    promedio=service.promedio(notas, 5);
    condicion=service.condicion(promedio);
      //muestra de datos
       System.out.println("NOTAS");
      for(int i=0;i<5;i++){
          System.out.println(notas[i]);
      }
      System.out.println("MINIMO " + minimo);
      System.out.println("PROMEDIO "+ promedio);
       System.out.println("CONDICION ");
       if(condicion==true){
            System.out.println("Alumno aprobado");
       }else{
            System.out.println("Alumno desaprobado");
       }
        }
}
