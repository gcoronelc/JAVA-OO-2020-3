//
//  java1323.java
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
//     Creacion: 05-May-1998  12:36:30
//     Revision: 05-Feb-2002  06:00:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este programa se instancian cinco botones y se colocan sobre
 * un objeto Frame, dejando que el sistema fije como controlador de
 * posiciones de componenentes sobre el objeto Frame, el Layout
 * por defecto del sistema, el Borderlayout
 */
import java.awt.*;
import java.awt.event.*;

public class java1323 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );

    miFrame.add( new Button( "Sur" ),"South" );
    miFrame.add( new Button( "Oeste" ),"West" );
    miFrame.add( new Button( "Este" ),"North" );
    miFrame.add( new Button( "Boton del Este" ),"East" );
    miFrame.add( new Button( "Centro" ),"Center" );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1323.java