//
//  java1419.java
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
//     Creacion: 09-Sep-1998  17:31:42
//     Revision: 07-Feb-2002  06:13:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa presenta una ventana con dos botones que permiten la
 * apertura del selector de ficheros implementado por el AWT, que consiste
 * en una ventana de diálogo mucho más pobre visualmente de la que presenta
 * el selector de ficheros que implementa Swing.
 * Ninguno de los botones es realmente activo, sino que simplemente sirve el
 * ejemplo para que el usuario pueda abrir el selector implementado por las
 * dos librerías y familiarizarse con su manejo y apariencia
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1419 extends JPanel {
  static JFrame ventana;

  public java1419() {
    Button boton1 = new Button( "FileDialog AWT" );
    Button boton2 = new Button( "FileChooser SWING" );

    add( boton1,"North" );
    add( boton2,"South" );

    // FileDialog implementado por el AWT
    ActionListener fd = new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        new FileDialog(ventana).setVisible( true );
        }
      };

    // FileChooser implementado por Swing
    ActionListener fc = new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        new JFileChooser().showDialog( ventana,"Abrir" );
        }
      };

    boton1.addActionListener( fd );
    boton2.addActionListener( fc );
    }


  public static void main( String args[] ) {
    java1419 panel = new java1419();
    ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( panel,BorderLayout.CENTER );

    ventana.setSize( 300,80 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1419.java