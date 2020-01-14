//
//  java1404.java
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
//     Creacion: 05-Jun-1998  12:59:28
//     Revision: 07-Feb-2002  05:33:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra los diferentes tipos de botones que incorpora la
 * libreria Swing a Java
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1404 extends JPanel {

  public java1404() {
    add( new JButton( "JButton" ) );
    add( new JToggleButton( "JToggleButton") );
    add( new JCheckBox( "JCheckBox" ) );
    add( new JRadioButton( "JRadioButton" ) );
    }

  public static void main( String args[] ) {
    java1404 panel = new java1404();
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( panel,BorderLayout.CENTER );
    ventana.setSize( 300,200 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1404.java