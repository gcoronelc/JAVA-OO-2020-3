package pe.uni.importe.service;

import pe.uni.importe.dto.Importe;

public class ImporteService {

  public Importe procesar(Importe imp) {

    double ingreso, impuesto, totalLiquido;

    ingreso = gananciaBruta(imp.getTht(), imp.getCdt(), imp.getPph());
    impuesto = validarImpuesto(ingreso);
    totalLiquido = ingreso - impuesto;
    imp.setIngreso(ingreso);
    imp.setImpuesto(impuesto);
    imp.setTotalLiquido(totalLiquido);

    return imp;
  }

  public double gananciaBruta(double tht, double cdt, double pph) {
    //variables
    double ingreso;

    ingreso = (tht * cdt * pph);

    return ingreso;

  }

  public double validarImpuesto(double ingreso) {
    //variables
    double impuesto = ingreso;
    //double ingreso;

    if (ingreso > 1500) {

      impuesto = ingreso * 0.08;
      //ingreso=ingreso-impuesto;
    } else {
      impuesto = 0;
    }

    return impuesto;

  }

  public double totalLiquido(double impuesto, double ingreso) {

    double ingreso1 = ingreso;

    if (ingreso > 1500) {
      impuesto = ingreso * 0.08;
    } else {

    }

    return ingreso;
  }

}
