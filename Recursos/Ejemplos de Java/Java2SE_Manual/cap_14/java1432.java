//
//  java1432.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 11-Nov-2001  08:32:34
//     Revision: 07-Feb-2002  06:10:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la posibilidad que ofrece JTabbedPane de presentar
 * todas las pestañas en una sola línea, independientemente del número
 * de ellas que se deseen visualizar.
 * Por defecto, se presenta en la parte superior. Si el lector descomenta
 * la línea que contiene la colocación LEFT, las pestañas aparecerán a
 * la izquierda de la ventana.
 */

import javax.swing.*;
import java.text.*;
import java.awt.*;

public class java1432 {
  public static void main( String args[] ) {
    // Nombres de los meses para el botón
    String[] txtMeses = new DateFormatSymbols().getMonths();
    // Iniciales de los meses para las pestañas
    String[] meses = new DateFormatSymbols().getShortMonths();
    JTabbedPane tabPanel = new JTabbedPane();
    // Aseguramos que solamente haya una línea de pestañas
    tabPanel.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );
    // Colocamos las pestañas verticalmente en la parte izquierda,
    // por defecto se colocan en la parte superior
//    tabPanel.setTabPlacement( JTabbedPane.LEFT );

    // Creamos los 12 paneles que corresponden a los meses, que
    // contendrán solamente un botón y la pestaña correspondiente
    for( int i=0; i < 12; i++ ) {
      JPanel panel = new JPanel( new BorderLayout() );
      // Creamos un botón en el que presentamos el nombre del
      // mes correspondiente a la pestaña seleccionada
      JButton boton = new JButton( txtMeses[i] );
      panel.add( boton );
      tabPanel.add( meses[i],panel );
      }
    // Creamos la ventana
    JFrame f = new JFrame( "Tutorial de Java, Swing" );
    f.getContentPane().add( tabPanel,BorderLayout.CENTER );
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    f.setBounds( 300,200,310,100 );
    f.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1432.java
