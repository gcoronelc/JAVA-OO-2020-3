/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjava00;

/**
 *
 * @author Alumno
 */
public class Test03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String t1="Java";
        String t2="java";
        
        // comparar
        if (t1.equals(t2)) {
            System.out.println("verdadero");
        }else{
            System.out.println("falso");
        }
        
        if (t1.equalsIgnoreCase(t2)) {
            System.out.println("verdadero");
        }else{
            System.out.println("falso");
        }
         
         if (t1.equalsIgnoreCase(t2)) {
            System.out.println("verdadero");
        }else{
            System.out.println("falso");
        }

                
                
                
                
                System.out.println("fin de programa");
    }
    
}
