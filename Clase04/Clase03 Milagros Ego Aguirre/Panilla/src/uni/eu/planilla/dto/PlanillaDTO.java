/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.eu.planilla.dto;

/**
 *
 * @author Mili
 */
public class PlanillaDTO {
// DATOS

  private int qhoras;
  private int dias;
  private double pagoHrs;

// |RESULTADO
  private double pagoBruto;
  private double impuesto;
  private double pagoNeto;

  public int getQhoras() {
    return qhoras;
  }

  public void setQhoras(int qhoras) {
    this.qhoras = qhoras;
  }

  public int getDias() {
    return dias;
  }

  public void setDias(int dias) {
    this.dias = dias;
  }

  public double getPagoHrs() {
    return pagoHrs;
  }

  public void setPagoHrs(double pagoHrs) {
    this.pagoHrs = pagoHrs;
  }

  public double getPagoBruto() {
    return pagoBruto;
  }

  public void setPagoBruto(double pagoBruto) {
    this.pagoBruto = pagoBruto;
  }

  public double getImpuesto() {
    return impuesto;
  }

  public void setImpuesto(double impuesto) {
    this.impuesto = impuesto;
  }

  public double getPagoNeto() {
    return pagoNeto;
  }

  public void setPagoNeto(double pagoNeto) {
    this.pagoNeto = pagoNeto;
  }

} // Fin PlanillaDTO
