//
//  java417.java
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
//     Creacion: 19-Feb-1998  07:17:17
//     Revision: 02-Feb-2002  20:34:41
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es una demostración de la utilización de la clase HashTable
 */
import java.util.*;

class Contador {
  int i = 1;

  public String toString() {
    return( Integer.toString( i ) );
    }
  }


class java417 {
  public static void main( String args[] ) {
    Hashtable ht = new Hashtable();

    for ( int i=0; i < 10000; i++ ) {
      // Genera un número cuasi-aleatorio entre 0 y 20
      Integer r = new Integer( (int)( Math.random()*20 ) );
      if ( ht.containsKey( r ) )
        ( (Contador)ht.get( r ) ).i++;
      else
        ht.put( r,new Contador() );
      }
    System.out.println( ht );
    }
  }

//------------------------------------------- Final del fichero java417.java