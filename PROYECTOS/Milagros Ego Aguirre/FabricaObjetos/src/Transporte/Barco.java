
package Transporte;

public class Barco implements Transporte {

    @Override
    public void arrancar() {
        System.out.println("Arranca el barco");
    }

    @Override
    public void detener() {
        System.out.println("Se detiene el barco");
    }

    @Override
    public String tipo() {
        return "Barco";
    }
    
}
