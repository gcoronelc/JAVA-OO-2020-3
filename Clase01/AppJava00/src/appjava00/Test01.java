package appjava00;

import java.util.Date;
import javax.swing.JOptionPane;

public class Test01 {

    public static void main(String[] args) {
        // variables
        double a = Double.parseDouble(JOptionPane.showInputDialog("Ingrse numero 1"));        
        double b = Double.parseDouble(JOptionPane.showInputDialog("Ingrse numero 2"));
        //procesar
        double s = a + b;
        //imprimir resultados
        System.out.println("La suma de " + a + "+" + b + "=" + s);
        //ver fecha del sistema
        Date hoy=new Date();
        System.out.println("Hoy es :"+hoy);
    }

}
