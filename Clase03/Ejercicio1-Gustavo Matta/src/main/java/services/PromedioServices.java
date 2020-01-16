/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author HOGAR
 */
public class PromedioServices {
    
    
    /**
     * promedio de cinco numeros
     * @param n1 numero 1
     * @param n2 numero 2
     * @param n3 numero 3
     * @param n4 numero 4
     * @param n5 numero 5
     * @return retorna el promedio de n1 + n2 + n3 + n4 + n5
     */
    public double promedio(double n1, double n2, double n3, double n4 ,double n5){
        //variables
        double prom;
        //proceso
        prom = n1 + n2 + n3 + n4 +n5;
        //reporte
        return prom;
    }
}
