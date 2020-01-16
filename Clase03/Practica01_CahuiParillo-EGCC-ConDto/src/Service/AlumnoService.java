
package Service;

 /**
     * promedio de notas.
     * @param n1 nota 1.
     * @param n2 nota 2.
     * @param n3 nota 3.
     * @param n4 nota 4.
     * @param n5 nota 5.
     * @return Retorna el primedio de las 4 mejores notas.
     */

public class AlumnoService {
     
    public double minimo(double n1,double n2,double n3,double n4,double n5) {
        //variables 
        double minimo=20;
        
        //proceso
                            
        if (n1<minimo) {minimo=n1;}
        if (n2<minimo) {minimo=n2;}
        if (n3<minimo) {minimo=n3;}
        if (n4<minimo) {minimo=n4;}
        if (n5<minimo) {minimo=n5;}
                      
        //Reporte
        return minimo;
    }
     
    public double promedio(double n1,double n2,double n3,double n4,double n5,double minimo) {
        //variables 
        double promedio;
        //proceso
        promedio=((n1+n2+n3+n4+n5)-minimo)/4;
                      
        //Reporte
        return promedio;
    }
    
    public String condicion(double promedio) {
        //variables 
        String condicion;
        //proceso
        if (promedio<14) {
            condicion="DESAPROBADO";
        }else{
            condicion="APROBADO";    
        }
                              
        //Reporte
        return condicion;
    }
    
}
