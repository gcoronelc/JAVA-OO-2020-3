package pe.uni.promedioapp.prueba;

import pe.uni.promedioapp.dto.PromedioDto;
import pe.uni.promedioapp.service.PromedioService;

public class Prueba01 {

  public static void main(String[] args) {
    //Variables
    PromedioDto dto = new PromedioDto();
    //Datos
    dto.setNota1(10);
    dto.setNota2(15);
    dto.setNota3(18);
    dto.setNota4(16);
    dto.setNota5(17);
    //Proceso
    PromedioService service = new PromedioService();
    dto = service.procesar(dto);
    //Reporte
    System.out.println("DATOS");
    System.out.println("Numero 1; " + dto.getNota1());
    System.out.println("Numero 2; " + dto.getNota2());
    System.out.println("Numero 3; " + dto.getNota3());
    System.out.println("Numero 4; " + dto.getNota4());
    System.out.println("Numero 5; " + dto.getNota5());
    System.out.println("");
    System.out.println("RESULTADO");
    System.out.println("La menor nota:: " + dto.getMenorNota());
    System.out.println("El promedio de notas: " + dto.getPromedio());
    System.out.println("Condicion: " + dto.getCondicion());
    System.out.println("Nota minima aprobatoria es 14");
  }
}
