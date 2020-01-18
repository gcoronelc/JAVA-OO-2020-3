package pe.uni.empresaapp.service;

import pe.uni.empresaapp.dto.EmpresaDto;

public class EmpresaService {

  public EmpresaDto procesar(EmpresaDto dto) {

    //Variables
    double pago_total;
    double retencion;

    //Proceso
    pago_total = pagototal(dto.getHora_trabajada(), dto.getDias_trabajada(), dto.getPago_hora());
    retencion = retencion(dto.getHora_trabajada(), dto.getDias_trabajada(), dto.getPago_hora());

    //Reporte
    dto.setPago_total(pago_total);
    dto.setRetencion(retencion);
    return dto;

    //Fin de Proceso
  }

  private double pagototal(int horaTrabajada, int diasTrabajados, double pagoHora) {

    //Variable
    double pagototal, retencion;

    //Datos
    pagototal = horaTrabajada * diasTrabajados * pagoHora;

    //Reporte
    return pagototal;

  } //Fin pagototal 

  private double retencion(int horaTrabajada, int diasTrabajados, double pagoHora) {

    //Variables
    double retencion, pagototal;

    //Datos
    pagototal = pagototal(horaTrabajada, diasTrabajados, pagoHora);
    retencion = 0;

    //Proceso
    if (pagototal > 1500) {
      retencion = pagototal * 0.08;
    }

    //Reporte
    return retencion;
  }//Condicion

}// Fin de EmpresaService
