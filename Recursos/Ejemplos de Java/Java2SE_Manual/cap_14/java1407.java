//
//  java1407.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 05-Jun-1998  18:01:54
//     Revision: 07-Feb-2002  05:39:17
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo de implementación en Swing de Listas y cajas
 * Combo, que combinan una lista y un campo de texto
 * Se utiliza el grupo del botones del ejemplo java1406.java
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1407 extends JPanel {

  public java1407() {
    setLayout( new GridLayout( 2,1 ) );

    JList lista = new JList( java1406.ids );
    add( new JScrollPane( lista ) );

    JComboBox combo = new JComboBox();
    for ( int i=0; i < 100; i++ )
      combo.addItem( Integer.toString( i ) );
    add( combo );
    }


  public static void main( String args[] ) {
    java1407 lista = new java1407();
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( lista,BorderLayout.CENTER );
    ventana.setSize( 200,200 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1407.java