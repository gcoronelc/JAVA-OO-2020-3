package uni.eu.planilla.service;

import uni.eu.planilla.dto.PlanillaDTO;

public class PlanillaService {

  public PlanillaDTO Proceso(PlanillaDTO datos) {
    int qhoras, dias;
    double pagoHrs;
    double pagoBruto;
    double impuesto = 0;
    double pagoNeto;

    qhoras = datos.getQhoras();
    dias = datos.getDias();
    pagoHrs = datos.getPagoHrs();

    pagoBruto = qhoras * dias * pagoHrs;
    if (pagoBruto > 1500) {
      impuesto = pagoBruto * 0.08;
    }
    pagoNeto = pagoBruto - impuesto;

    datos.setPagoBruto(pagoBruto);
    datos.setImpuesto(impuesto);
    datos.setPagoNeto(pagoNeto);

    return datos;

  } // Fin procesar

} // Fin PlanillaService


/*
    menorNota = minimo(dto.getNota1(), dto.getNota2(),
            dto.getNota3(), dto.getNota4(), dto.getNota5());


 */
