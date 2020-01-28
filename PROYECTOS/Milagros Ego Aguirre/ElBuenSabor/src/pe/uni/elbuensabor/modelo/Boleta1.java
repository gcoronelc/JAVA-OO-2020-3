
package pe.uni.elbuensabor.modelo;

public class Boleta1 extends Comprobante implements Comprobante1 {

    @Override
    public String imprime(double total) {
        super.setTotal(total);
        super.setServicio(0.1*total);
        super.setTotal_general(getTotal()+getServicio());
        
        return ("Total: "+getTotal()+"\nServicio: "+getServicio()+"\nTotal General: "+getTotal_general());
    }
}
