//
//  java1433.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 07-Ene-2002  15:19:38
//     Revision: 07-Feb-2002  05:43:27
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra el uso del receptor de eventos CaretListener,
 * que permite conocer cuando se mueve el cursor de texto, y así poder
 * presentar la posición en que se encuentra ese cursor respecto al
 * texto, mostrándose en la ventana la columna y la fila en la que está
 * situado el cursor de texto.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class java1433 extends JPanel {
  // Zona de texto editable
  static JTextArea texto;
  JScrollPane panel;
  JTextField fila;
  JTextField columna;

  public java1433() {
    setLayout( new BorderLayout() );
    // Elementos de la interfaz, la zona de texto y los dos
    // campos para presentar número de columna y fila
    texto = new JTextArea( 250,300 );
    columna = new JTextField();
    fila = new JTextField();

    panel = new JScrollPane( texto );
    panel.setPreferredSize( new Dimension(250,300) );

    // Incorporamos los elementos al panel
    add( panel,BorderLayout.CENTER );
    add( columna,BorderLayout.NORTH );
    add( fila,BorderLayout.SOUTH );

    // Receptor de eventos de movimiento del cursor de texto
    texto.addCaretListener( new CaretListener() {
      public void caretUpdate( CaretEvent evt ) {
        try {
          // Posición en el texto
          int pos = texto.getCaretPosition();
          // Obtenemos la fila y columna que correspoden a esa
          // posición
          int fil,col;
          fil = texto.getLineOfOffset( texto.getCaretPosition() );
          col = texto.getCaretPosition()
            - texto.getLineStartOffset( fil );
          System.out.print( "Posición cursor: " +pos+"  \r" );
          columna.setText( "Columna cursor: " +col );
          fila.setText( "Fila cursor: " +fil );
        } catch( Exception e ) {
          e.printStackTrace();
          }
        }
      } );
    }

  public static void main( String[] arg ) {
    JFrame f = new JFrame( "Tutorial de Java, Swing" );
    f.getContentPane().add( new java1433() );
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    f.pack();
    f.setVisible( true );
    // Dejamos el foco en la zona de texto
    texto.requestFocus();
    }
  }

//------------------------------------------ Final del fichero java1433.java


