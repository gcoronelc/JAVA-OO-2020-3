
package Transporte;

public class Carro implements Transporte {

//    @Override
    public void arrancar() {
        System.out.println("Arranca el carro");
    }

//    @Override
    public void detener() {
        System.out.println("El carro se detiene");
    }

//    @Override
    public String tipo() {
        return "Carro";
    }
    
}
