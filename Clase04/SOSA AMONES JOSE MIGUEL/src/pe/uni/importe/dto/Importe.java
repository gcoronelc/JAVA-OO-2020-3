package pe.uni.importe.dto;

public class Importe {

  private double tht;
  private double cdt;
  private double pph;
  private double ingreso;
  private double impuesto;
  private double totalLiquido;

  public Importe() {

  }

  public double getTotalLiquido() {
    return totalLiquido;
  }

  public void setTotalLiquido(double totalLiquido) {
    this.totalLiquido = totalLiquido;
  }

  public double getTht() {
    return tht;
  }

  public void setTht(double tht) {
    this.tht = tht;
  }

  public double getCdt() {
    return cdt;
  }

  public void setCdt(double cdt) {
    this.cdt = cdt;
  }

  public double getPph() {
    return pph;
  }

  public void setPph(double pph) {
    this.pph = pph;
  }

  public double getIngreso() {
    return ingreso;
  }

  public void setIngreso(double ingresos) {
    this.ingreso = ingresos;
  }

  public double getImpuesto() {
    return impuesto;
  }

  public void setImpuesto(double impuesto) {
    this.impuesto = impuesto;
  }

}
