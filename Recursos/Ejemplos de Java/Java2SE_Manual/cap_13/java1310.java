//
//  java1310.java
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
//     Creacion: 06-Mar-1998  23:26:34
//     Revision: 05-Feb-2002  05:43:47
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase Scrollbar.
 * Crea una barra de desplazamiento horizontal, que permite la selección
 * de valores entre 0 y 255, con un ancho del indicador de 64 pixels
 */
import java.awt.*;
import java.awt.event.*;

class java1310 extends Frame {
  public static void main( String args[] ) {
    // Se instancia un objeto del tipo de la clase
    new java1310();
    }

  public java1310() {
    Scrollbar rango;

    rango = new Scrollbar( Scrollbar.HORIZONTAL,0,64,0,255 );

    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    // Se incorpora la barra de desplazamiento al objeto Frame
    miFrame.add( rango,"Center" );

    // Se fija el tamaño del Frame y se hace que aparezca todo
    // en pantalla
    miFrame.setSize( 300,50 );
    miFrame.setVisible( true );

    // Se instancia y registra un objeto receptor de eventos de la
    // ventana para poder concluir la aplicación cuando el usuario
    // cierre el Frame
    Conclusion conclusion = new Conclusion();
    miFrame.addWindowListener( conclusion );
    }
  }


// Concluye la ejecucion de la aplicacion cuando el usuario cierra la
// ventana, porque se genera un evento windowClosing
class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1310.java