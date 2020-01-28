
package pe.uni.elbuensabor.modelo;

public class Factura1 extends Comprobante implements Comprobante1 {

        //atributos
    private double consumo;
    private double impuesto;

    @Override
    public String imprime(double total) {
        super.setTotal(total);
        super.setServicio(0.1*total);
        super.setTotal_general(getTotal()+getServicio());
        impuesto=(0.19/1.19)*getTotal();
        consumo=getTotal()-impuesto;
        return ("Consumo: "+consumo+"\nImpuesto: "+impuesto+"\nTotal: "+getTotal()+"\nServicio: "+getServicio()+"\nTotal General: "+getTotal_general());

    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }
    
}
