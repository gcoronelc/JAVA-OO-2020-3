package pe.uni.practica02_cp.prueba;

import pe.uni.practica02_cp.dto.PagoDto;
import pe.uni.practica02_cp.service.PagoService;



public class Prueba01 {
    public static void main(String[] args) {
        //Variables
        PagoDto dto=new PagoDto();
        //Datos
        dto.setHoras(10);
        dto.setDias(20);
        dto.setPagohora(10);

        
        //Proceso
        PagoService service=new PagoService();
        dto=service.procesar(dto);        
        //Reporte
        System.out.println("DATOS");
        System.out.println("Cantidad de horas trabajadas; "+dto.getHoras());
        System.out.println("Cantidad de días trabajados; "+dto.getDias());
        System.out.println("Pago por hora (Soles); "+dto.getPagohora());
        System.out.println("");
        System.out.println("RESULTADO");
        System.out.println("Sueldo bruto: "+dto.getSueldo_bruto());
        System.out.println("Rentencion (8%): "+dto.getRetencion());
        System.out.println("Neto a pagar a trabajador: "+dto.getSueldo_neto());
        System.out.println("Si los ingresos superan los 1500.00 Nuevos Soles se debe retener el 8% del total.");
        
        
    }
    
}
