
package pe.uni.elbuensabor.controller;

import pe.uni.elbuensabor.modelo.Boleta;
import pe.uni.elbuensabor.modelo.Factura;

/**
 *
 * @author YANET
 */
public class ElBuenSaborcontroller {
    
    public String crearcdp (double total, int tipo_comp){
        //Variables
        String rpta="";
        switch (tipo_comp) {
            case 0:
                Boleta bol=new Boleta();
                rpta=bol.imprimir_boleta(total);
                break;
            case 1:
                Factura fact;
                fact=new Factura();
                rpta=fact.ImprimirFactura(total);
                break;

        }
        return rpta;
    }
    
}
