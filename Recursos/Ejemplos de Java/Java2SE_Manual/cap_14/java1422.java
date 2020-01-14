//
//  java1422.java
//  Copyright (c) 1996,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 10-Sep-1998  15:48:10
//     Revision: 07-Feb-2002  06:17:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muesta un bot�n, tras cuya pulsaci�n aparece la ventana de
 * Di�logo que Swing presenta para pedir la introducci�n de un dato. Es
 * la ventana ideal para cuando se necesita la introducci�n de datos
 * simples, como en este ejemplo en que se solicita el nombre del
 * usuario, y, otro de los usos en que se suele emplear es en las
 * entradas de confirmaci�n de acceso, mediante la introducci�n de un
 * nombre y una contrase�a
 */
import java.awt.event.*;
import javax.swing.*;

public class java1422 extends JFrame implements ActionListener {
  public java1422() {
    JButton boton = new JButton( "Muestra Ventana" );
    getContentPane().add( boton,"Center" );
    pack();
    boton.addActionListener( this );
    }


  public void actionPerformed( ActionEvent evt ) {
    String respuesta = JOptionPane.showInputDialog( this,"Nombre ",
      "Tutorial de Java, Swing",JOptionPane.DEFAULT_OPTION );
    System.out.println( "Respuesta: "+respuesta );
    }


  public static void main( String argv[] ) {
    new java1422().setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1422.java