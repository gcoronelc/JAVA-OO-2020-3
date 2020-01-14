//
//  java1413.java
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
//     Creacion: 04-Ago-1998  11:46:17
//     Revision: 07-Feb-2002  05:51:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de las barras de progreso y barras de
 * desplazamiento, tal como las implementa Swing.
 * Se utiliza el Look Metal para hacer la prueba. Desplazando la barra
 * que aparece en la zona inferior, la barra de progreso de la parte
 * superior se desplazará de forma semejante y en la misma proporción
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class java1413 extends JPanel {
  JProgressBar barraProg = new JProgressBar();
  JSlider barraSlid = new JSlider( JSlider.HORIZONTAL,0,100,60 );

  public java1413() {
    setLayout( new GridLayout(2,1) );
    add( barraProg );
    barraSlid.setValue( 0 );
    barraSlid.setPaintTicks( true );
    barraSlid.setMajorTickSpacing( 20 );
    barraSlid.setMinorTickSpacing( 5 );
    barraSlid.setBorder( new TitledBorder("Desplazame") );
    barraSlid.addChangeListener( new ChangeListener() {
      public void stateChanged( ChangeEvent evt ) {
        barraProg.setValue(barraSlid.getValue() );
        }
      } );
    add( barraSlid );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1413(),BorderLayout.CENTER );
    frame.setSize( 200,150 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1413.java