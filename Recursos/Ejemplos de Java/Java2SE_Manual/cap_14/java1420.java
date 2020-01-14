//
//  java1420.java
//  Copyright (c) 1996,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 09-Sep-1998  17:48:46
//     Revision: 07-Feb-2002  06:14:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muesta un botón, tras cuya pulsación aparece la ventana de
 * Diálogo que Swing presenta para pedir consulta sobre confirmación de
 * acciones. Permite que el programador presente cualquier mensaje, y por
 * defecto aparecen los dos botones de confirmación correspondientes al
 * mensaje, YES y NO
 */
import java.awt.event.*;
import javax.swing.*;

public class java1420 extends JFrame implements ActionListener {

  public java1420() {
    JButton boton = new JButton( "Muestra Ventana" );
    getContentPane().add( boton,"Center" );
    pack();
    boton.addActionListener( this );
    }


  public void actionPerformed( ActionEvent evt ) {
    int res = JOptionPane.showConfirmDialog( this,"Responda Yes o No",
      "Tutorial de Java, Swing",JOptionPane.YES_NO_OPTION );
    String respuesta = null;

    if ( res == JOptionPane.YES_OPTION )
      respuesta = "Si";
    else
      respuesta = "No";
    System.out.println( "Respuesta: "+respuesta );
    }


  public static void main( String args[] ) {
    new java1420().setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1420.java