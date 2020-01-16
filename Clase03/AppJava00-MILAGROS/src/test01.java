
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alumno
 */
public class test01 {
    
    public static void main (String[] args){
        
        double a=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero 1"));
        double b=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero 2"));
   //  double a=19;
   //  double b=7.9;
    
    double s=a+b;
    
    System.out.println ("La suma de " + a + "+" + b + " es " + s);
    
    Date hoy=new Date();
    
        System.out.println("Hoy es " + hoy);
            
            
}
}
