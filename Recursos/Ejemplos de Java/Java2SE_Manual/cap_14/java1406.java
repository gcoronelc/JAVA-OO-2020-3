//
//  java1406.java
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
//     Creacion: 06-Jun-1998  16:35:17
//     Revision: 07-Feb-2002  05:36:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utiliza la reflexión para crear grupos de
 * diferentes tipos a partir de AbstractButton
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.reflect.*;

public class java1406 extends JPanel {

  static String ids[] = {
    "Mortadelo","Filemon","Carpanta",
    "Rompetechos","Pepe Gotera","Otilio",
    };

  static JPanel creaPanelBotones( Class bClass,String ids[] ) {
    ButtonGroup botones = new ButtonGroup();
    JPanel panel = new JPanel();
    String titulo = bClass.getName();

    titulo = titulo.substring( titulo.lastIndexOf('.')+1 );
    panel.setBorder( new TitledBorder( titulo ) );

    for ( int i=0; i < ids.length; i++ ) {
      AbstractButton botonAbs = new JButton( "fallo" );
      try {
        // Se utiliza el constructor dinámico al que se pasa
        // una cadena como argumento
        Constructor ctor = bClass.getConstructor( new Class[] {
          String.class
          } );
        // Se crea un nuevo objeto del tipo del botón
        botonAbs = ( AbstractButton )ctor.newInstance( new Object[]{
          ids[i]
          } );
      } catch( Exception e ) {
        System.out.println( "No puedo crear " + bClass );
        }
      botones.add( botonAbs );
      panel.add( botonAbs );
      }

    return( panel );
    }


  public java1406() {
    add( creaPanelBotones( JButton.class,ids ) );
    add( creaPanelBotones( JToggleButton.class,ids ) );
    add( creaPanelBotones( JCheckBox.class,ids ) );
    add( creaPanelBotones( JRadioButton.class,ids ) );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1406(),BorderLayout.CENTER );
    frame.setSize( 600,300 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1406.java