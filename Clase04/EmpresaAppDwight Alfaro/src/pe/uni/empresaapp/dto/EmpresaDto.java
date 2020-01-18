package pe.uni.empresaapp.dto;

public class EmpresaDto {

  //Datos
  private int hora_trabajada;
  private int dias_trabajada;
  private double pago_hora;

  //Resultado
  private double pago_total;
  private double retencion;

  //constructor
  public EmpresaDto() {
  }

  //getter and setter
  public int getHora_trabajada() {
    return hora_trabajada;
  }

  public void setHora_trabajada(int hora_trabajada) {
    this.hora_trabajada = hora_trabajada;
  }

  public int getDias_trabajada() {
    return dias_trabajada;
  }

  public void setDias_trabajada(int dias_trabajada) {
    this.dias_trabajada = dias_trabajada;
  }

  public double getPago_hora() {
    return pago_hora;
  }

  public void setPago_hora(double pago_hora) {
    this.pago_hora = pago_hora;
  }

  public double getPago_total() {
    return pago_total;
  }

  public void setPago_total(double pago_total) {
    this.pago_total = pago_total;
  }

  public double getRetencion() {
    return retencion;
  }

  public void setRetencion(double retencion) {
    this.retencion = retencion;
  }

} //Fin de EmpresaDto.
