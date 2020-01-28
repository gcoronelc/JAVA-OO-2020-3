
package pe.uni.impuestorenta.service;
/**
 *
 * @author Jose Miguel
 */
public class ImpuestoService {
    
    public double deduccion(double deduccion){
      //datos
      double deduccionfinal =deduccion;
      
      //proceso
      if(deduccion<=100800){
          deduccionfinal= deduccion*0.2;
      }
      return deduccionfinal;
    }
    
    public double rentaneta(double deduccionfinal,double deduccion){
        //datos
        double rentaneta=deduccion;
        
        //proceso
        if(deduccionfinal!=0){
        rentaneta= deduccion-deduccionfinal;
        }
        return rentaneta;
    }
    
    public double totalcuartayquintacategoria(double rentaquintacategoria,double rentaneta){
       //datos
        double rentaquintacuarta=0;
        
        //proceso
        rentaquintacuarta=rentaquintacategoria+rentaneta;
      
        return rentaquintacuarta;
        
    }
    
    public double deduccionuit(double rentaquintacuarta){
        
        //variables
        double uit=4200;
        double ingresoanual;
        double deduccionuit=rentaquintacuarta;
        
        //proceso
        ingresoanual=29400;
        if(rentaquintacuarta>=ingresoanual){
            deduccionuit=rentaquintacuarta-ingresoanual;
        }
        return deduccionuit;
    }
    
     public double totalrentaneta
        (double rentaquintacuarta, double deduccionuit ){
        
        double totalrentacuartayquinta;
        
        totalrentacuartayquinta=rentaquintacuarta-deduccionuit;
        return totalrentacuartayquinta;
    }
        
    public double impuestoalarenta(double deduccionuit){
        
        //datos
        double rentaportramo=deduccionuit;
        
        //proceso
        if(deduccionuit>=0 && deduccionuit<=21000){
          rentaportramo=deduccionuit*0.08;
        }
        if(deduccionuit>21000 && deduccionuit<=84000){
          rentaportramo=deduccionuit*0.14;
        }
        if(deduccionuit>84000 && deduccionuit<=147000){
          rentaportramo=deduccionuit*0.17;
        }
        if( deduccionuit>=147000 && deduccionuit<189000){
          rentaportramo=deduccionuit*0.2;
        }
       if(deduccionuit>=189000){
          rentaportramo=deduccionuit*0.3;
        }
     
        return rentaportramo;
    }
    
   
}
