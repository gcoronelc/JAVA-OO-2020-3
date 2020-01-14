//
//  java426.java
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
//     Creacion: 19-Feb-1998  09:18:07
//     Revision: 02-Feb-2002  20:44:29
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es una demostración de la utilización de métodos definidos en los
 * interfaces de Collection que no funcionan en ciertas ocasiones
 */
import java.util.*;

public class java426 {
  private static String s[] = {
    "uno", "dos", "tres", "cuatro", "cinco",
    "seis", "siete", "ocho", "nueve", "diez",
    };
  static List a = Arrays.asList( s );
  static List a2 = Arrays.asList( new String[] { s[3],s[4],s[5]} );

  public static void main( String args[] ) {
    java421.print( a );        // Iteración
    System.out.println(
      "a.contains("+s[0]+") = "+a.contains(s[0]) );
    System.out.println(
      "a.containsAll(a2) = "+a.containsAll(a2) );
    System.out.println( "a.isEmpty() = "+a.isEmpty() );
    System.out.println(
      "a.indexOf("+s[5]+") = "+a.indexOf(s[5]) );

    // Movimientos hacia atrás
    ListIterator lit = a.listIterator( a.size() );
    while ( lit.hasPrevious() )
      System.out.print( lit.previous() );
    System.out.println();

    // Fija algunos elementos a valores diferentes
    for ( int i=0; i < a.size(); i++ )
      a.set( i,"47" );
    java421.print( a );

    // Lo siguente compila, pero no funciona
    lit.add( "X" );            // Operación no soportada
    a.clear();                 // No soportada
    a.add( "once" );           // No soportada
    a.addAll( a2 );            // No soportada
    a.retainAll( a2 );         // No soportada
    a.remove( s[0] );          // No soportada
    a.removeAll( a2 );         // No soportada
    }
  }

//------------------------------------------- Final del fichero java426.java