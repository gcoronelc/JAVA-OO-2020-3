//
//  java1315.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 08-Oct-1997  13:12:43
//     Revision: 05-Feb-2002  05:51:07
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;

public class java1315 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Se construyen tres Paneles con fondos de color diferente
    // y sin contener ningun elemento activo
    Panel panelIzqdo = new Panel();
    panelIzqdo.setBackground( Color.yellow );
    panelIzqdo.add( new TextField( "Panel Izquierdo -> amarillo" ) );

    Panel panelCentral = new Panel();
    panelCentral.setBackground( Color.red );
    panelCentral.add( new Label( "Panel Central -> rojo" ) );

    Panel panelDrcho = new Panel();
    panelDrcho.setBackground( Color.blue );
    panelDrcho.add( new Button( "Panel Derecho -> azul" ) );

    // Se instancia un objeto Frame utilizando un FlowLayout y
    // se colocan los tres objetos Panel sobre el Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );

    miFrame.add( panelIzqdo );
    miFrame.add( panelCentral );
    miFrame.add( panelDrcho );
    miFrame.setSize( 500,200 );
    miFrame.setVisible( true );

    miFrame.addWindowListener( new Conclusion() );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye la aplicacion cuando el usuario cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1315.java