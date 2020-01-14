//
//  HolaMundo.java
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
//     Creacion: 30-Jul-1997  05:26:10
//     Revision: 02-Feb-2002  20:03:41
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y est  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

//
// Applet HolaMundo de ejemplo
//
import java.awt.Graphics;
import java.applet.Applet;

public class HolaMundo extends Applet {

  public void paint( Graphics g ) {
    // Pinta el mensaje en la posición indicada
    g.drawString( "Hola Mundo!",25,25 ) ;
    }
  }

//---------------------------------------- Final del fichero HolaMundo.java