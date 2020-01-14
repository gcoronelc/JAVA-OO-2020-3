//
//  java427.java
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
//     Creacion: 19-Feb-1998  09:36:01
//     Revision: 02-Feb-2002  20:45:13
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo sirve para ilustrar la ordenacion y busqueda que se
 * realiza en Arrays
 */
import java.util.*;

public class java427 {
  static Random r = new Random();
  static String sorigen =
  "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
  "abcdefghijklmnopqrstuvwxyz";
  static char fuente[] = sorigen.toCharArray();

  // Se crea un String aleatorio a partir de la cadena que contiene
  // todas las letras
  public static String cadAleat( int longitud ) {
    int rnd;
    char buffer[] = new char[longitud];

    for ( int i=0; i < longitud; i++ ) {
      rnd = Math.abs( r.nextInt() ) % fuente.length;
      buffer[i] = fuente[rnd];
      }
    return( new String( buffer ) );
    }

  // Crea un array aleatorio de Strings
  public static String[] cadsAleat( int longitud,int tamano ) {
    String s[] = new String[tamano];

    for ( int i=0; i < tamano; i++ )
      s[i] = cadAleat( longitud );
    return( s );
    }

  public static void print( byte b[] ) {
    for ( int i=0; i < b.length; i++ )
      System.out.print( b[i]+" " );
    System.out.println();
    }

  public static void print( String s[] ) {
    for ( int i=0; i < s.length; i++ )
      System.out.print( s[i]+" " );
    System.out.println();
    }

  public static void main( String args[] ) {
    byte b[] = new byte[15];
    r.nextBytes( b ); // Se rellena el array de bytes
    print( b );
    Arrays.sort( b );
    print( b );
    int loc = Arrays.binarySearch( b,b[10] );
    System.out.println("Posicion de "+b[10]+" = "+loc );

    // Ahora ya se prueban la ordenación y la búsqueda
    String s[] = cadsAleat( 4,10 );
    print( s );
    Arrays.sort( s );
    print( s );
    loc = Arrays.binarySearch( s,s[4] );
    System.out.println( "Posicion de "+s[4]+" = "+loc );
    }
  }

//------------------------------------------- Final del fichero java427.java