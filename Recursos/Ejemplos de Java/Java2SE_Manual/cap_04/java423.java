//
//  java423.java
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
//     Creacion: 19-Feb-1998  07:54:50
//     Revision: 02-Feb-2002  20:39:30
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra algunas de las cosas que se pueden hacer con
 * Sets
 */
import java.util.*;

public class java423 {
  public static void testVisual( Set a ) {
    java421.fill( a );
    java421.fill( a );
    java421.fill( a );
    java421.print( a ); // No permite Duplicados!

    // Se añade otro Set al anterior
    a.addAll( a );
    a.add( "uno" );
    a.add( "uno" );
    a.add( "uno" );
    java421.print( a );

    // Buscamos ese elemento
    System.out.println( "a.contains(\"uno\"): "+a.contains( "uno" ) );
   }

  public static void main( String args[] ) {
    testVisual( new HashSet() );
    }
  }

//------------------------------------------- Final del fichero java423.java