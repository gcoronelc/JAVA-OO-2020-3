/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.uni.elbuensabor.modelo;

/**
 *
 * @author YANET
 */
public class Boleta extends Comprobante{
    
    public Boleta(){
        
    }
    public String imprimir_boleta(double total){
        super.setTotal(total);
        super.setServicio(0.1*total);
        super.setTotal_general(getTotal()+getServicio());
        
        return ("Total: "+getTotal()+"\nServicio: "+getServicio()+"\nTotal General: "+getTotal_general());
        
    }     
    
}
