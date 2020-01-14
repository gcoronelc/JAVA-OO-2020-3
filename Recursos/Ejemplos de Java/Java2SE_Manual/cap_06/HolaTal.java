//
//  HolaTal.java
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
//     Creacion: 06-Jun-1996  08:13:30
//     Revision: 02-Feb-2002  21:20:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.Graphics;
import java.applet.Applet;

public class HolaTal extends Applet {
  String nombre;

  public void init() {
    // Obtiene la cadena del parámetro "nombre" que se fija en la
    // llamada al applet desde la página html
    nombre = getParameter( "Nombre" );
    }


  public void paint( Graphics g ) {
    // Pinta el saludo en la posición indicada
    g.drawString( "Hola "+nombre+"!",25,25 );
    }
  }

//------------------------------------------- Final del fichero HolaTal.java