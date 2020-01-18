
package pe.uni.importe.prueba;

import pe.uni.importe.dto.Importe;
import pe.uni.importe.service.ImporteService;

public class Prueba02 {
    public static void main(String[] args) {
        
        Importe imp= new Importe();
        //Datos:
        imp.setTht(42);//total horas trabajadas
        imp.setCdt(5);//cantidad dias trabajados
        imp.setPph(6);//pago por hora
        //proceso
        ImporteService impser= new ImporteService();
        imp = impser.procesar(imp);
        
        //reporte
        System.out.println("DATOS:");
        System.out.println("TOTAL HORAS TRABAJADAS: "+imp.getTht());
        System.out.println("CANTIDAD DE DIAS TRABAJADOS: "+imp.getCdt());
        System.out.println("PAGO POR HORA: "+imp.getPph());
        System.out.println("");
        System.out.println("Ganancia: " +imp.getIngreso());
        System.out.println("IMPUESTO A LA RENTA: "+imp.getImpuesto());
        System.out.println("TOTAL A PAGAR: "+imp.getTotalLiquido());
    }
  
}
