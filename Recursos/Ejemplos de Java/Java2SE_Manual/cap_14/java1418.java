//
//  java1418.java
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
//     Creacion: 04-Ago-1998  18:01:54
//     Revision: 07-Feb-2002  06:08:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es un compendio de todos los demás que se han utilizado como
 * ejemplos de este capítulo, recogiendo todos los ejemplos en un panel con
 * pestañas, correspondiendo cada una de ellas a la instanciación de un
 * objeto de cada una de las clases
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class java1418 extends JPanel {
  static Object objetos[][] = {
    { "AWT-Clon",java1401.class},
    { "Bordes",java1402.class},
    { "Botones",java1404.class},
    { "Grupo de Botones",java1406.class},
    { "Listas y Combo",java1407.class},
    { "Barras",java1413.class},
    { "Arbol",java1414.class},
    { "Tabla",java1416.class},
    };

  static JPanel creaPanel( Class clase ) {
    String titulo = clase.getName();
    titulo = titulo.substring( titulo.lastIndexOf('.') + 1 );
    JPanel panel = null;
    try {
      panel = (JPanel)clase.newInstance();
    } catch ( Exception e ) {
      System.out.println( e );
      }
    panel.setBorder( new TitledBorder( titulo ) );
    return( panel );
    }


  public java1418() {
    setLayout( new BorderLayout() );
    JTabbedPane pestana = new JTabbedPane();
    for ( int i=0; i < objetos.length; i++ ) {
      pestana.addTab( (String)objetos[i][0],
        creaPanel( (Class)objetos[i][1] ) );
      }
    add( pestana,BorderLayout.CENTER );
    pestana.setSelectedIndex( objetos.length/2 );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1418(),BorderLayout.CENTER );
    frame.setSize( 460,350 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1418.java