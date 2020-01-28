package pe.uni.elbuensabor.modelo;
//import pe.uni.Compobante1.Metodos;

public class Fabrica {
    public static Comprobante1 construir(String tipo){
        switch(tipo.toLowerCase()){
            case "boleta":
                return  new Boleta1();
            case "factura":
                return  new Factura1();
            default:  return null;
        }
    }
}
