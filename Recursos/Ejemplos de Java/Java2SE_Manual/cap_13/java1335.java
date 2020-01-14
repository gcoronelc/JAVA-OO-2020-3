//
//  java1335.java
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
//     Creacion: 29-Dec-2001  21:51:50
//     Revision: 05-Feb-2002  05:47:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se ilustra la utilizacion de las caracterísiticas de
 * ausencia de decoración y cambios en el estado de maximización de las
 * ventanas que se ah incorporado al JDK 1.4
 */
import java.awt.*;
import java.awt.event.*;

public class java1335 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  Frame miFrame;

  public IHM() {
    // Se instancian tres botones con textos indicando lo que
    // hacen cuando se pulse sobre ellos
    Button botonMaximizar = new Button( " Maximizar " );
    Button botonIconizar = new Button( " Iconizar " );
    Button botonNormal = new Button( " Normal " );
    Button botonCerrar = new Button( " Cerrar " );

    // Instancia un objeto Frame y se elimina la decoración
    miFrame = new Frame();
    miFrame.setUndecorated( true );

    // Añade los objetos Button al Frame
    miFrame.setLayout( new GridLayout(5,1) );
    miFrame.add( botonMaximizar );
    miFrame.add( botonIconizar );
    miFrame.add( botonNormal );
    miFrame.add( botonCerrar );

    // Fija el tamaño del Frame y lo hace visible
    miFrame.setSize( 300,300 );
    miFrame.setVisible( true );

    // Instancia y registra objetos ActionListener sobre los
    // botones utilizando la sintaxis abreviada de las
    // clases anidadas y asignando las acciones correspondientes
    // al estado que indica su rótulo
    botonMaximizar.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        miFrame.setExtendedState( Frame.MAXIMIZED_BOTH );
        }
      } );

    botonIconizar.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent evt ) {
        miFrame.setExtendedState( Frame.ICONIFIED |
          miFrame.getExtendedState() );
        }
      } );

    botonNormal.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        miFrame.setExtendedState( Frame.NORMAL );
        }
      } );

    botonCerrar.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        // Concluye la aplicacion cuando el usuario pulse sobre
        // este botón
        System.exit( 0 );
        }
      } );
    }
  }

//------------------------------------------ Final del fichero java1335.java
