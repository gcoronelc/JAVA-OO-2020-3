package pe.uni.proyectosunat.prueba;

import pe.uni.proyectosunat.app.dto.ProyectoSunatDto;
import pe.uni.proyectosunat.service.ProyectoSunatService;


public class Prueba01 {
    public static void main(String[] args) {
        
        //Variables
        ProyectoSunatDto dto = new ProyectoSunatDto();
        
        //Datos
        dto.setRenta_cuarta(40000);
        dto.setRenta_quinta(32000);
        
        //Proceso
        ProyectoSunatService service = new ProyectoSunatService();
        dto = service.procesar(dto);
        
        //Reporte
        System.out.println("DATOS");
        System.out.println("Renta cuarta categoria: " + dto.getRenta_cuarta());
        System.out.println("Deducción (20% del monto consignado: " + dto.getDeduccion()) ;
        System.out.println("Renta Neta obtenida por el ejercicio individual: " + dto.getRentaNeta());
        System.out.println("");
        System.out.println("Renta quinta categoria: " + dto.getRenta_quinta());
        System.out.println("Total rentas de cuarta y quinta categoría: " + dto.getTotalRentas());
        System.out.println("Deducción de 7 UIT: " + dto.getDeduccion2());
        System.out.println("");
        System.out.println("TOTAL RENTA NETA DE CUARTA Y QUINTA CATEGORÍA: " + dto.getCas_512());
        System.out.println("Total Renta Imponible de Trabajo y Fuente Extranjera: " + dto.getCas_513());
        System.out.println("Saldo de la Deuda Tributaria: " + dto.getCas_120());
    }
}
