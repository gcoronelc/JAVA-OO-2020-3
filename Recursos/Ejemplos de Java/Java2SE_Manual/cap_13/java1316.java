//
//  java1316.java
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
//     Creacion: 02-May-1998  06:02:20
//     Revision: 05-Feb-2002  05:52:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;

public class java1316 extends java.applet.Applet {

  public void init() {
    add( new Button( "Uno" ) );
    add( new Button( "Dos" ) );
    }

  public static void main( String args[] ) {
    Frame f = new Frame( "Tutorial de Java" );
    java1316 ejemplo = new java1316();

    ejemplo.init();

    f.add( "Center",ejemplo );
    f.pack();
    f.show();
    }
  }

//------------------------------------------ Final del fichero java1316.java