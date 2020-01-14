//
//  java433.java
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
//     Creacion: 15-Dic-2001  09:14:43
//     Revision: 02-Feb-2002  20:24:19
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.util.*;

public class java433 {
  static void muestraElementos( Object obj ) {
    // En el caso de una colección de tipo Map, hay que recuperar las
    // claves y los valores correspondientes a los elementos, para poder
    // utilizar el iterador sobre la lista de elementos que se obtenga
    if( obj instanceof Map )
      obj = ((Map)obj).entrySet();

    // Mostramos los elementos de la colección, siempre que se trate de
    // una colección
    if( obj instanceof Collection ) {
      Collection coleccion = (Collection)obj;
      Iterator iterador = coleccion.iterator();
      while( iterador.hasNext() )
        System.out.println( iterador.next() );
      }
    // En caso de que no estemos ante una colección, indicamos el fallo
    else {
      System.out.println( "No es una colección válida" );
      }
    }

  public static void main( String args[] ) {
    // List
    List lista = new ArrayList();
    lista.add( "linea 1" );
    lista.add( "linea 2" );
    muestraElementos( lista );

    // Set
    Set conjunto = new TreeSet();
    conjunto.add( "linea 3" );
    conjunto.add( "linea 4" );
    muestraElementos( conjunto );

    // Map
    Map mapa = new HashMap();
    mapa.put( "linea 5:clave","linea 5:valor" );
    mapa.put( "linea 6:clave","linea 6:valor" );
    muestraElementos( mapa );
    }
  }

//------------------------------------------- Final del fichero java433.java