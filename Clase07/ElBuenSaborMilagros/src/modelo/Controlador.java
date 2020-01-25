
package modelo;

public class Controlador {
    
    public String emiteBoleta(double tot){
        ConsumoCliente consumo = new ConsumoCliente();
        String res;

        consumo.setTotal(tot);
        consumo.calcula();
        res = "total: " + consumo.getTotal()+"\n";
        res = res + "servicio: " + consumo.getServicio()+"\n";
        res = res + "Total general: "+ consumo.gettGeneral();

        return res;
    }// fin emite boleta
    
    public String emiteFactura(double tot){
        ConsumoCliente consumo = new ConsumoCliente();
        String res;

        consumo.setTotal(tot);
        consumo.calcula();
        res = "Consumo: " + consumo.getConsumo()+"\n";
        res = res + "Impuesto: "+ consumo.getImpuesto()+"\n";
        res = res + "total: " + consumo.getTotal()+"\n";
        res = res + "servicio: " + consumo.getServicio()+"\n";
        res = res + "Total general: "+ consumo.gettGeneral();
        
        return res;
    }// fin emite factura
    
}// fin controlador
