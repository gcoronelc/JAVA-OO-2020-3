package pe.uni.promedioapp.service;

import pe.uni.promedioapp.dto.PromedioDto;

public class PromedioService {

  public PromedioDto procesar(PromedioDto dto) {
    // Variables
    int menorNota, promedio;
    String condicion;
    // Proceso
    menorNota = minimo(dto.getNota1(), dto.getNota2(),
            dto.getNota3(), dto.getNota4(), dto.getNota5());
    promedio = promedio(dto.getNota1(), dto.getNota2(),
            dto.getNota3(), dto.getNota4(), dto.getNota5());
    condicion = condicion(promedio);
    // Reporte
    dto.setMenorNota(menorNota);
    dto.setPromedio(promedio);
    dto.setCondicion(condicion);
    return dto;
  } // Fin de procesar

  private int minimo(int n1, int n2, int n3, int n4, int n5) {
    // variables 
    int minimo = 20;
    // proceso
    if (n1 < minimo) {
      minimo = n1;
    }
    if (n2 < minimo) {
      minimo = n2;
    }
    if (n3 < minimo) {
      minimo = n3;
    }
    if (n4 < minimo) {
      minimo = n4;
    }
    if (n5 < minimo) {
      minimo = n5;
    }
    // Reporte
    return minimo;
  } // Fin de minimo

  private int promedio(int n1, int n2, int n3, int n4, int n5) {
    // variables 
    int promedio, minimo;
    // proceso
    minimo = minimo(n1, n2, n3, n4, n5);
    promedio = ((n1 + n2 + n3 + n4 + n5) - minimo) / 4;
    // Reporte
    return promedio;
  } // Fin promedio

  private String condicion(int nota) {
    // variables 
    String condicion;
    // proceso
    condicion = "APROBADO";
    if (nota < 14) {
      condicion = "DESAPROBADO";
    } 
    // Reporte
    return condicion;
  } // condicion
  
} // Fin de PromedioService
