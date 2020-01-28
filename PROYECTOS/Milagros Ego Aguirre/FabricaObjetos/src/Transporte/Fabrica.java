

package Transporte;

public class Fabrica {
    public static Transporte construir(String tipo){
        
        switch(tipo.toLowerCase()){
            case "barco":
                return  new Barco();
            case "carro":
                return new Carro();
            case "moto":
                return new Moto();
            default:
                System.out.println(tipo + " no esta considerado en la fabrica de transportes");
                return null;
        }
                
    }
    
}
