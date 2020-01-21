package pe.angeles.mateapp.service;

public class MateService {
  
  public long factorial(int n){
    // Variable
    long fact;
    // Proceso
    fact = 1;
    while(n > 1){
      fact *= n--;
    }
    // Reporte
    return fact;
  }
  
  public int mcd(int n1, int n2){
    
    return 0;
  }
  
  public int mcm(int n1, int n2){
    
    return 0;
  }
  
  public String fibonacci(int n){
    
    return "0,1,1,2,3,5,8,13";
  }
  
  public String esPrimo(int n){
    
    return ("NO ES PRIMO");
  }
  
}
