package pe.uni.promedioapp.dto;

public class PromedioDto {

  // Datos
  private int nota1;
  private int nota2;
  private int nota3;
  private int nota4;
  private int nota5;
  // Resultado
  private int menorNota;
  private int promedio;
  private String condicion;

  // Constructor por defecto
  public PromedioDto() {
  }

  public int getNota1() {
    return nota1;
  }

  public void setNota1(int nota1) {
    this.nota1 = nota1;
  }

  public int getNota2() {
    return nota2;
  }

  public void setNota2(int nota2) {
    this.nota2 = nota2;
  }

  public int getNota3() {
    return nota3;
  }

  public void setNota3(int nota3) {
    this.nota3 = nota3;
  }

  public int getNota4() {
    return nota4;
  }

  public void setNota4(int nota4) {
    this.nota4 = nota4;
  }

  public int getNota5() {
    return nota5;
  }

  public void setNota5(int nota5) {
    this.nota5 = nota5;
  }

  public int getMenorNota() {
    return menorNota;
  }

  public void setMenorNota(int menorNota) {
    this.menorNota = menorNota;
  }

  public int getPromedio() {
    return promedio;
  }

  public void setPromedio(int promedio) {
    this.promedio = promedio;
  }

  public String getCondicion() {
    return condicion;
  }

  public void setCondicion(String condicion) {
    this.condicion = condicion;
  }

} // Fin de PromedioDto
