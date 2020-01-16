package practica01;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class Practica01 {

    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        //VARIABLES
        double menor, nota_1, nota_2, nota_3, nota_4, nota_5;
        double promedio = 0;
        
        //DATOS
        System.out.print("Ingrese nota 1: ");
        nota_1 = in.nextDouble();
        in.nextLine();
        System.out.print("Ingrese nota 2: ");
        nota_2 = in.nextDouble();
        in.nextLine();
        System.out.print("Ingrese nota 3: ");
        nota_3 = in.nextDouble();
        in.nextLine();
        System.out.print("Ingrese nota 4: ");
        nota_4 = in.nextDouble();
        in.nextLine();
        System.out.print("Ingrese nota 5: ");
        nota_5 = in.nextDouble();
        
        //PROCESO
        in.nextLine();
        menor=nota_1;
        if(menor>nota_2)
            menor=nota_2;
        if(menor>nota_3)
            menor=nota_3;
        if(menor>nota_4)
            menor=nota_4;
        if(menor>nota_5)
            menor=nota_5;
        
        if(promedio>=14){
JOptionPane.showMessageDialog(null, "Aprobado");
}else{
JOptionPane.showMessageDialog(null, "Desaprobado");
        //REPORTE
        promedio=(nota_1+nota_2+nota_3+nota_4+nota_5-menor)/4;
        System.out.println("Valor de menor: " + menor);
        System.out.println("Valor de promedio: " + promedio);
    }
    }
}
