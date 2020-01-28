
package pe.uni.impuestorenta.controller;

import pe.uni.impuestorenta.service.ImpuestoService;

/**
 *
 * @author Jose Miguel
 */
public class ImpuestoRentaController {
    private ImpuestoService impuestoService;
    
    public ImpuestoRentaController(){
        impuestoService= new ImpuestoService();
    }
    
    public double deduccion(double deduccion){
   return impuestoService.deduccion(deduccion);
    }
    
    public double rentaneta(double deduccionfinal,double deduccion){
        return impuestoService.rentaneta(deduccionfinal, deduccion);
    }
    
    public double totalcuartayquintacategoria(double rentaquintacategoria,double rentaneta){
        return impuestoService.totalcuartayquintacategoria(rentaquintacategoria, rentaneta);
    }
    
    public double deduccionuit(double rentaquintacuarta){
        return impuestoService.deduccionuit(rentaquintacuarta);
    }
    
     public double impuestoalarenta(double deduccionuit){
         return impuestoService.impuestoalarenta(deduccionuit);
     }
     
     public double totalrentaneta(double rentaquintacuarta, double deduccionuit ){
     return impuestoService.totalrentaneta(rentaquintacuarta, deduccionuit);
}
     
}
