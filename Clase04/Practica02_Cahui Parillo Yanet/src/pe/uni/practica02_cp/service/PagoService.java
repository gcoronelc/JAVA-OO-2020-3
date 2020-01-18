
package pe.uni.practica02_cp.service;

import pe.uni.practica02_cp.dto.PagoDto;

/**
 *
 * @author Alumno
 */
public class PagoService {
    public PagoDto procesar(PagoDto dto){
        //Variables
        double sueldo_bruto,retencion,sueldo_neto;
        
        
        //Proceso
        sueldo_bruto=sueldo_bruto(dto.getHoras(),dto.getDias(),dto.getPagohora());
        retencion=retencion(dto.getHoras(),dto.getDias(),dto.getPagohora());
        sueldo_neto=neto(dto.getHoras(),dto.getDias(),dto.getPagohora());
        //Reporte
        dto.setSueldo_bruto(sueldo_bruto);
        dto.setRetencion(retencion);
        dto.setSueldo_neto(sueldo_neto);

        return dto;
    }
    
    private double sueldo_bruto(double horas,double dias,double pagohora) {
        //variables 
        double pagotrab;
        
        //proceso
        pagotrab=(horas*dias*pagohora);                    
        
                      
        //Reporte
        return pagotrab;
    }
    private double retencion(double horas,double dias,double pagohora) {
        //variables 
        double retencion=0;
        double sueldo_bruto;
        double porcentaje=0.08;
        
        //proceso
        sueldo_bruto=sueldo_bruto(horas, dias, pagohora);
        if(sueldo_bruto>1500){
            retencion=sueldo_bruto*porcentaje;    
        }
                             
        //Reporte
        return retencion;
        
    }
     private double neto(double horas,double dias,double pagohora) {
        //variables 
        double sueldo_neto;
       
        
        //proceso
        sueldo_neto=sueldo_bruto(horas, dias, pagohora)-retencion(horas, dias, pagohora);
                          
        
                      
        //Reporte
        return sueldo_neto;
    }
    
}
