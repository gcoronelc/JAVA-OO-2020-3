//
//  java428.java
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
//     Creacion: 19-Feb-1998  15:07:22
//     Revision: 02-Feb-2002  20:46:16
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utiliza un Comparador para realizar una Ordenacion
 * alfabetica de la estructura de datos del ejemplo java427
 */
import java.util.*;

public class java428 implements Comparator {
  public int compare( Object obj1,Object obj2 ) {
    // Suponemos que solamente vamos a utilizar cadenas
    String s1 = ( (String)obj1).toLowerCase();
    String s2 = ( (String)obj2).toLowerCase();

    return( s1.compareTo( s2 ) );
    }


  public static void main( String args[] ) {
    String s[] = java427.cadsAleat( 4,10 );

    java427.print( s );
    java428 ac = new java428();
    Arrays.sort( s,ac );
    java427.print( s );

    // Se debe utilizar un Comparador para realizar la búsqueda
    int loc = Arrays.binarySearch( s,s[3],ac );
    System.out.println( "Posicion de "+s[3]+" = "+loc );
    }
  }

//------------------------------------------- Final del fichero java428.java