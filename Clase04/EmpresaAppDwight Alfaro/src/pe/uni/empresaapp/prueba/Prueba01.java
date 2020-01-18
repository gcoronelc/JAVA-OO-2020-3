
package pe.uni.empresaapp.prueba;

import pe.uni.empresaapp.dto.EmpresaDto;
import pe.uni.empresaapp.service.EmpresaService;

public class Prueba01 {
    public static void main(String[] args) {
        
        //Variables
        EmpresaDto dto = new EmpresaDto();
        
        //Datos
        dto.setPago_hora(20);
        dto.setHora_trabajada(8);
        dto.setDias_trabajada(17);
        
        //Proceso
        EmpresaService service = new EmpresaService();
        dto = service.procesar(dto);
        
        //Reporte
        System.out.println("DATOS");
        System.out.println("Pago por hora1; " + dto.getPago_hora());
        System.out.println("Horas trabajadas; " + dto.getHora_trabajada());
        System.out.println("Dias trabajados " + dto.getDias_trabajada());
        System.out.println("");
        System.out.println("RESULTADO");
        System.out.println("El pago total al trabajdo es de: " + dto.getPago_total());
        System.out.println("La retencion es de : " + dto.getRetencion());
        
        
    }
}
