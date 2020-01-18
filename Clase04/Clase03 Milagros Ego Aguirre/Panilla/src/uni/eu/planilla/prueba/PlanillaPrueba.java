/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.eu.planilla.prueba;

import uni.eu.planilla.dto.PlanillaDTO;
import uni.eu.planilla.service.PlanillaService;

public class PlanillaPrueba {

  public static void main(String[] args) {
    PlanillaDTO datos = new PlanillaDTO();
    datos.setQhoras(8);
    datos.setDias(20);
    datos.setPagoHrs(50);

    PlanillaService servicio = new PlanillaService();

    datos = servicio.Proceso(datos);

    System.out.println("horas: " + datos.getQhoras());
    System.out.println("dias: " + datos.getDias());
    System.out.println("pago por hora: " + datos.getPagoHrs());
    System.out.println("pago Bruto: " + datos.getPagoBruto());
    System.out.println("impuesto: " + datos.getImpuesto());
    System.out.println("pago neto: " + datos.getPagoNeto());

  } // Fin main
} // Fin PlanillaPrueba

