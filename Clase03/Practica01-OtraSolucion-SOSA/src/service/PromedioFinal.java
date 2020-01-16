package service;

public class PromedioFinal {
    
    //variables o atributos de la clase CalculadoraService
    private double p1;
    private double p2;
    private double p3;
    private double p4;
    private double p5;
    private double pp;
    private double menorNota;
    private double eparcial;
    private double efinal;
    private double pfinal;
    private String c;
    private String c1="APROBADO";
    private String c2="DESAPROBADO";
    
  /**
   * Suma de dos números.
   *
   * @param p1 Practica 1.
   * @param p2 Practica 2.
   * @param p3 Practica 3.
   * @param p4 Practica 4.
   * @param p5 Practica 5.
   * @return pp y menorNota retorna el promedio de practicas del metodo promedioPracticas y la menor nota de las practicas respectivamente.
   */
    
    
    //metodo para hallar el promedio de practicas
    
    public double promedioPracticas(double p1, double p2, double p3, double p4, double p5){
       
        //proceso
        this.pp=(p1+p2+p3+p4+p5)/5;
        //reporte
        return pp;
    }    
    
    //metodo para hallar la menor nota
    
    public double menorNota(double p1, double p2, double p3, double p4, double p5){
       
    //primera forma  
    
        menorNota=9999999;
        
        double array[]=new double[5];
        array[0]=p1;
        array[1]=p2;
        array[2]=p3;
        array[3]=p4;
        array[4]=p5;
        
        for(int i=0;i<array.length;i++){
            if(array[i]<menorNota){
                menorNota=array[i];
            }
        }
        
       //segunda forma
       
//        menorNota=9999999;
//        if(p1<p2 && p1<p3  && p1<p4 && p1<p5)
//        {
//          menorNota=p1;
//        }
//        if(p2<p1 && p2<p3 && p2<p4 && p2<p5)
//        {
//            menorNota=p2;
//        }
//        if(p3<p1 && p3<p2 && p3<p4 && p3<p5)
//        {
//          menorNota=p3;
//        }
//        if(p4<p1 && p4<p2 && p4<p3 && p4<p5)
//        {
//           menorNota=p4;
//        }
//        if(p5<p1 && p5<p2 && p5<p3 && p5<p4 )
//        {
//            menorNota=p5;
//        }
       return menorNota;
    }
    
    //metodo para hallar el promedio final
    
    public double promedioFinal(double eparcial,double efinal){
        this.efinal=efinal;
        this.eparcial=eparcial;
        this.pfinal=(pp+eparcial+efinal)/3;
        return pfinal;
    }

    //metodo para hallar la condicion del alumno
    
    public String condicion(){
        if(this.pfinal>=14){
         c=this.c1;
        }else{
         c=this.c2;
        }
            
        return c;
    }

    //metodos para obtener los atributos
    
    public String getC() {
        return c;
    }
   
    public double getPfinal() {
        return pfinal;
    }

    public double getPp() {
        return pp;
    }
    
    public double getEparcial() {
        return eparcial;
    }

    public double getEfinal() {
        return efinal;
    } 

    public double getMenorNota() {
        return menorNota;
    }
   
}
