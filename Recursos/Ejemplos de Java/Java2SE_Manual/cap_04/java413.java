//
//  java413.java
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
//     Creacion: 16-Feb-1998  06:58:08
//     Revision: 02-Feb-2002  20:29:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo presenta una forma de hacer que java casque
 */
import java.util.*;

public class java413 {
  public String toString() {
    return( "Direccion del objeto: "+this+"\n" );
    }

  public static void main( String args[] ) {
    Vector v = new Vector();

    for ( int i=0; i < 10; i++ )
      v.addElement( new java413() );
    System.out.println( v );
    }
  }

//------------------------------------------- Final del fichero java413.java