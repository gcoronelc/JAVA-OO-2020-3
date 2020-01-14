//
//  java434.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 15-Dic-2001  09:42:22
//     Revision: 02-Feb-2002  20:25:25
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra cómo se puede eliminar de forma segura un elemento
 * de una lista utilizando un iterador en el desplazamiento por los
 * elementos de la lista.
 * Si se utiliza directamente la lista para eliminar el elemento, se
 * produce una excepción.
 */
import java.util.*;

public class java434 {
  public static void main( String args[] ) {
    // Creamos la lista de elementos de prueba
    List lista = new ArrayList();
    lista.add( "linea 1" );
    lista.add( "linea 2" );
    lista.add( "linea 3" );
    lista.add( "linea 4" );
    lista.add( "linea 5" );
    lista.add( "linea 6" );
    lista.add( "linea 7" );

    // Creamos el iterador que utilizaremos para recorrer la lista
    Iterator iterador = lista.iterator();
    for( int i=0; iterador.hasNext(); i++ ) {
      String elemento = (String)iterador.next();
      // Intentamos eliminar el tercer elemento de la lista
      if( elemento.equals("linea 3") ) {
        // Esta línea provoca una excepción al eliminar el elemento de tipo
        // ConcurrentModificationException
        lista.remove( i );
        // Si se comenta la línea anterior y se descomenta la siguiente, se
        // elimina la excepción
        //iterador.remove();
        }
      // Imprimimos la lista original
      System.out.println( elemento );
      }
    // Imprimimos la lista sin el elemento eliminado antes
    iterador = lista.iterator();
    while( iterador.hasNext() )
      System.out.println( "--"+iterador.next() );
    }
  }

//------------------------------------------- Final del fichero java434.java