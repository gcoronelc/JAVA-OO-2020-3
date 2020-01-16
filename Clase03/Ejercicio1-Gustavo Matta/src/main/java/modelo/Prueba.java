/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import services.PromedioServices;

/**
 *
 * @author HOGAR
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    //variables
    double n1, n2, n3, n4, n5;
    double prom;
    //datos
    n1 = 10.0;
    n2 = 20.0;
    n3 = 15.0;
    n4 = 5.0;
    n5 = 15.0;
    //proceso
    PromedioServices service = new PromedioServices();
    prom = service.promedio(n1,n2,n3,n4,n5);
    //reporte
        System.out.println("DATOS");
        System.out.println("Numero 1: " + n1);
        System.out.println("Numero 2: " + n2);
        System.out.println("Numero 3: " + n3);
        System.out.println("Numero 4: " + n4);
        System.out.println("Numero 5: " + n5);
        System.out.println("");
        System.out.println("RESULTADO");
        System.out.println("Promedio: " + prom);
        
        
    }
    
}
