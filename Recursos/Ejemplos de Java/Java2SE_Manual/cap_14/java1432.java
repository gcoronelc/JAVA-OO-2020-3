//
//  java1432.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 11-Nov-2001  08:32:34
//     Revision: 07-Feb-2002  06:10:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la posibilidad que ofrece JTabbedPane de presentar
 * todas las pesta�as en una sola l�nea, independientemente del n�mero
 * de ellas que se deseen visualizar.
 * Por defecto, se presenta en la parte superior. Si el lector descomenta
 * la l�nea que contiene la colocaci�n LEFT, las pesta�as aparecer�n a
 * la izquierda de la ventana.
 */

import javax.swing.*;
import java.text.*;
import java.awt.*;

public class java1432 {
  public static void main( String args[] ) {
    // Nombres de los meses para el bot�n
    String[] txtMeses = new DateFormatSymbols().getMonths();
    // Iniciales de los meses para las pesta�as
    String[] meses = new DateFormatSymbols().getShortMonths();
    JTabbedPane tabPanel = new JTabbedPane();
    // Aseguramos que solamente haya una l�nea de pesta�as
    tabPanel.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );
    // Colocamos las pesta�as verticalmente en la parte izquierda,
    // por defecto se colocan en la parte superior
//    tabPanel.setTabPlacement( JTabbedPane.LEFT );

    // Creamos los 12 paneles que corresponden a los meses, que
    // contendr�n solamente un bot�n y la pesta�a correspondiente
    for( int i=0; i < 12; i++ ) {
      JPanel panel = new JPanel( new BorderLayout() );
      // Creamos un bot�n en el que presentamos el nombre del
      // mes correspondiente a la pesta�a seleccionada
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
