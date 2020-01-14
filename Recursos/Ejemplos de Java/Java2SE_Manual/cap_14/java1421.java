//
//  java1421.java
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
//     Creacion: 10-Sep-1998  16:00:29
//     Revision: 07-Feb-2002  06:17:35
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muesta un botón, tras cuya pulsación aparece la ventana de
 * Diálogo que Swing presenta para mostrar cualquier información al
 * usuario. Es la ventana ideal para cuando se necesita que el usuario
 * se entere de algunos sucesos. La ventana de error también se
 * presenta de esta forma.
 * Esta ventana de aviso solamente dispone de un botón para aceptar el
 * mensaje, es decir, para darse por enetrados de que se ha leido el
 * aviso
 */
import java.awt.event.*;
import javax.swing.*;

public class java1421 extends JFrame implements ActionListener {

  public java1421() {
    JButton boton = new JButton( "Muestra Ventana" );
    getContentPane().add( boton,"Center" );
    pack();
    boton.addActionListener( this );
    }

  public void actionPerformed( ActionEvent evt ) {
    JOptionPane.showMessageDialog( this,
      "Hola... Java... ;-)",
      "Tutorial de java, Swing",
      JOptionPane.WARNING_MESSAGE );
    }


  public static void main( String argv[] ) {
    new java1421().setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1421.java