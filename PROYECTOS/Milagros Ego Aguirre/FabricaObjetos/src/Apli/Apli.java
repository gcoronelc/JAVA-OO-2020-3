
package Apli;
import java.lang.*;
import Transporte.Transporte;
import Transporte.Fabrica;
//import Transporte.Moto;
//import Transporte.Carro;

public class Apli {

    public static void main(String[] args) {
        
        Transporte tr = Fabrica.construir("moto");
        tr.arrancar();
        tr.detener();
    }
    
}
