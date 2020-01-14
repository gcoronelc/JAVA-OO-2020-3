//
//  java437.java
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
//     Creacion: 15-Dic-2001  18:25:37
//     Revision: 02-Feb-2002  20:48:23
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra el uso de la ordenación de los elementos de una
 * colección, teniendo en cuenta el orden que implica el uso de caracteres
 * especiales de un determiando idioma.
 * Se utiliza Collator, y de este modo, en español se encuentran las
 * letras minúsculas antes que las Mayúsculas y éstas antes que las letras
 * acentuadas. Por ello, independientemente del orden en que son
 * introducidos los elementos en la colección, el resultado que se
 * obtiene al ejecutar el programa es la ordenación lexicográfica correcta
 * correspondiente al idioma español, que es el "locale" correspondiente
 * al ordenador del autor, y el resultado es:
 *
 * [papa, Papa, papá, Pepa, Pepe, Pepi, Pepito, pipa, pipo]
 */
import java.text.*;
import java.util.*;

public class java437 {
  static private void imprimeColeccion( Collection coleccion ) {
    boolean primero = true;
    Iterator iterador = coleccion.iterator();
    System.out.print( "[" );
    while( iterador.hasNext() ) {
      if( primero )
        primero = false;
      else
        System.out.print( ", " );

      CollationKey clave = (CollationKey)iterador.next();
      System.out.print( clave.getSourceString() );
      }
    System.out.println( "]" );
    }

  public static void main( String args[] ) {
    Collator co = Collator.getInstance();
    CollationKey coClave1 = co.getCollationKey( "Papa" );
    CollationKey coClave2 = co.getCollationKey( "papa" );
    CollationKey coClave3 = co.getCollationKey( "Pepa" );
    CollationKey coClave4 = co.getCollationKey( "pipo" );
    CollationKey coClave5 = co.getCollationKey( "Pepi" );
    CollationKey coClave6 = co.getCollationKey( "Pepe" );
    CollationKey coClave7 = co.getCollationKey( "papá" );
    CollationKey coClave8 = co.getCollationKey( "pipa" );
    CollationKey coClave9 = co.getCollationKey( "Pepito" );

    Set set = new TreeSet();
    set.add( coClave1 );
    set.add( coClave2 );
    set.add( coClave3 );
    set.add( coClave4 );
    set.add( coClave5 );
    set.add( coClave6 );
    set.add( coClave7 );
    set.add( coClave8 );
    set.add( coClave9 );
    imprimeColeccion( set );
    }
  }

//------------------------------------------- Final del fichero java437.java