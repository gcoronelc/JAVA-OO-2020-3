//
//  java1309.java
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
//     Creacion: 06-Mar-1998  23:16:18
//     Revision: 05-Feb-2002  05:43:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase Scrollbar.
 * Crea un selector de color a través de tres barras de desplazamiento
 * que permitirían seleccionar la itnensidad de cada uno de los
 * componentes básicos de un color
 */
import java.awt.*;
import java.awt.event.*;

class java1309 extends Frame {
  public static void main( String args[] ) {
    // Se instancia un objeto del tipo de la clase
    new java1309();
    }

  public java1309() {
    Scrollbar rojo,verde,azul;

    rojo = new Scrollbar( Scrollbar.VERTICAL,0,1,0,255 );
    verde = new Scrollbar( Scrollbar.VERTICAL,0,1,0,255 );
    azul = new Scrollbar( Scrollbar.VERTICAL,0,1,0,255 );

    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );

    // Se incorporan las tres barras de desplazamiento al objeto Frame
    miFrame.add( rojo );
    miFrame.add( verde );
    miFrame.add( azul );

    // Se fija el tamaño del Frame y se hace que aparezca todo
    // en pantalla
    miFrame.setSize( 250,100 );
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

//------------------------------------------ Final del fichero java1309.java