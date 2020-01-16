package Prueba;

import Service.AlumnoService;

public class Prueba01 {
    public static void main(String[] args) {
        //Variables
        double n1,n2,n3,n4,n5;
        double minimo, promedio;
        String condicion;
        //Datos
        n1=10.0;
        n2=15.0;
        n3=16.0;
        n4=18.0;
        n5=17.0;
        
        //Proceso
        AlumnoService service=new AlumnoService();
        minimo=service.minimo(n1, n2, n3, n4, n5);
        promedio=service.promedio(n1, n2, n3, n4, n5, minimo);
        condicion=service.condicion(promedio);
                
        
        //Reporte
        System.out.println("DATOS");
        System.out.println("Numero 1; "+n1);
        System.out.println("Numero 2; "+n2);
        System.out.println("Numero 3; "+n3);
        System.out.println("Numero 4; "+n4);
        System.out.println("Numero 5; "+n5);
        System.out.println("");
        System.out.println("RESULTADO");
        System.out.println("La menor nota:: "+minimo);
        System.out.println("El promedio de notas: "+promedio);
        System.out.println("Condicion: "+condicion);
        System.out.println("Nota minima aprobatoria es 14");
        
        
    }
    
}
