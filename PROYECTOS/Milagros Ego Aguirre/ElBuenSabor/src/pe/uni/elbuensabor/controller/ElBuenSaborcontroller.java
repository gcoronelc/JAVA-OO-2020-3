
package pe.uni.elbuensabor.controller;


import pe.uni.elbuensabor.modelo.Boleta;
import pe.uni.elbuensabor.modelo.Factura;

import pe.uni.elbuensabor.modelo.Fabrica;
import pe.uni.elbuensabor.modelo.Comprobante1;


public class ElBuenSaborcontroller {

    public String crearcdp1 (double total, String tipo){
        //Variables
        String rpta="";
        
//        Comprobante1 comp = (Comprobante1) Fabrica.contruir(tipo);
        Comprobante1 comp =  Fabrica.construir(tipo);
        rpta = comp.imprime(total);
        return rpta;
    }
    
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
