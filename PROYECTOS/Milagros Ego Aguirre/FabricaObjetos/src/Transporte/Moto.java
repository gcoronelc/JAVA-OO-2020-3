

package Transporte;

public class Moto implements Transporte{

//    @Override
    public void arrancar() {
        System.out.println("Estoy arrancando la moto");
    }

//    @Override
    public void detener() {
        System.out.println("Estoy deteniendo la moto");
    }

//    @Override
    public String tipo() {
        return "moto";
    }
    
}
