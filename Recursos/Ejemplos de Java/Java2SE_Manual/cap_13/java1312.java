//
//  java1312.java
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
//     Creacion: 08-Oct-1997  15:02:52
//     Revision: 05-Feb-2002  05:45:56
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se ilustra la utilizacion de la clase Frame y
 * algunos de sus metodos
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class java1312 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  Frame miFrame;

  public IHM() {
    // Se instancian tres botones con textos indicando lo que
    // hacen cuando se pulse sobre ellos
    Button botonTitulo = new Button( "Imprime Titulo" );
    Button botonCursorMano = new Button( "Cursor Mano" );
    Button botonCursorFlecha = new Button( "Cursor Flecha" );

    // Instancia un objeto Frame con su titulo indicativo de que se
    // se trata, utilizando un FlowLayout
    miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );

    // Añade tres objetos Button al Frame
    miFrame.add( botonTitulo );
    miFrame.add( botonCursorMano );
    miFrame.add( botonCursorFlecha );

    // Fija el tamaño del Frame y lo hace visible
    miFrame.setSize( 250,200 );
    miFrame.setVisible( true );

    // Instancia y registra objetos ActionListener sobre los
    // tres botones utilizando la sintaxis abreviada de las
    // clases anidadas
    botonTitulo.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        System.out.println( miFrame.getTitle() );
        }
      } );

    botonCursorMano.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent evt ) {
        miFrame.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
      } );

    botonCursorFlecha.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        miFrame.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        }
      } );

    // Instancia y registra un objeto WindowListener sobre el objeto
    // Frame para terminar el programa cuando el usuario haga click
    // con el raton sobre el boton de cerrar la ventana que se
    // coloca sobre el objeto Frame
    miFrame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        // Concluye la aplicacion cuando el usuario cierra la
        // ventana
        System.exit( 0 );
        }
      } );
    }
  }

//------------------------------------------ Final del fichero java1312.java
