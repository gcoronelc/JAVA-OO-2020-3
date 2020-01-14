//
//  java421.java
//  Copyright (c) 1998,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 18-Feb-1998  08:59:05
//     Revision: 02-Feb-2002  20:38:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra lo que se puede hacer con las Collections que
 * se han incorporado a la plataforma Java 2
 */
import java.util.*;

public class java421 {
  // Rellena la Colecci�n con el n�mero de elementos que se especifica
  // en 'tamano', comenzando a partir del elemento que indica el
  // par�metro 'primero'
  public static Collection fill( Collection c,int primero,
    int tamano ) {
    for ( int i=primero; i < primero+tamano; i++ )
      c.add( Integer.toString( i ) );
    return( c );
    }


  // Tambi�n rellena la Colecci�n con el n�mero de elementos que se
  // indique en el par�metro 'tamano', pero se inicia desde el
  // elemento 0
  public static Collection fill( Collection c,int tamano ) {
    return( fill( c,0,tamano ) );
    }


  // En este caso se rellena la Colecci�n comenzando en el elemento
  // 0 y rellenando 10 elementos
  public static Collection fill( Collection c ) {
    return( fill( c,0,10 ) );
    }


  // Crea una estructura de datos y la moldea a Colecci�n
  public static Collection nuevaColeccion() {
    // Se utiliza un ArrayList por simplicidad, pero se puede ver
    // como una Collection gen�rica en cualquier lugar del
    // programa
    return( fill( new ArrayList() ) );
    }


  // Rellena una Colecci�n con un rango de valores
  public static Collection nuevaColeccion( int primero,int tamano ) {
    return( fill( new ArrayList(),primero,tamano ) );
    }


  // Se desplaza a trav�s de una Lista utilizando un Iterador
  public static void print( Collection c ) {
    for ( Iterator x=c.iterator(); x.hasNext(); )
      System.out.print( x.next()+" " );
    System.out.println();
    }


  public static void main( String args[] ) {
    Collection c = nuevaColeccion();
    c.add( "diez" );
    c.add( "once" );
    print( c );

    // Busca los elementos m�ximo y m�nimo; esto puede hacerse
    // de diversas formas, dependiendo de como est� implementado
    // el interfaz Comparable
    System.out.println( "Collections.max(c) = "+
      Collections.max( c ) );
    System.out.println( "Collections.min(c) = "+
      Collections.min( c ) );

    // A�ade una Colecci�n a otra Colecci�n
    c.addAll( nuevaColeccion() );
    print( c );
    c.remove( "3" ); // Elimina el primero
    print( c );
    c.remove( "3" ); // Elimina el segundo
    print( c );

    // Elimina todos los componentes que esten en la Colecci�n
    // que se pasa como argumento
    c.removeAll( nuevaColeccion() );
    print( c );
    c.addAll( nuevaColeccion() );
    print( c );

    // Mira si un elemento determinado est� en la Colecci�n
    System.out.println( "c.contains(\"4\") = "+c.contains("4") );
    // Mira si la SubColecci�n est� en la Colecci�n base
    System.out.println( "c.containsAll( nuevaColeccion() ) = "+
      c.containsAll( nuevaColeccion() ) );

    Collection c2 = nuevaColeccion( 5,3 );
    // Mantiene todos los elementos que est�n en las colecciones
    // 'c' y 'c2'. Hace una intersecci�n de las dos colecciones
    c.retainAll( c2 );
    print( c );

    // Elimina de la colecci�n 'c' todos los elementos que se
    // encuentran tambi�n en la colecci�n 'c2'
    c.removeAll( c2 );
    System.out.println( "c.isEmpty() = "+c.isEmpty() );
    c = nuevaColeccion();
    print( c );

    // Se eliminan todos los elementos
    c.clear();
    System.out.println( "Despues de c.clear():" );
    print( c );
    }
  }

//------------------------------------------- Final del fichero java421.java