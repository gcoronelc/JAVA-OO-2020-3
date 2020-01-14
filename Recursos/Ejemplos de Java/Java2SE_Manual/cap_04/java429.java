//
//  java429.java
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
//     Creacion: 20-Feb-1998  03:25:10
//     Revision: 02-Feb-2002  20:46:48
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En esta clase se implementa el interfaz Comparable
 */
import java.util.*;

public class java429 implements Comparable {
  private int dato;

  public java429( int num ) {
    dato = num;
    }

  public int compareTo( Object obj ) {
    int aux = ((java429)obj).dato;

    // Comprobaciones de tipo
    if ( dato == aux )
      return( 0 );
    if ( dato < aux )
      return( -1 );
    return( 1 );
    }

  public static void print( Object a[] ) {
    for ( int i=0; i < a.length; i++ )
      System.out.print( a[i]+" " );
    System.out.println();
    }

  public String toString() {
    return( dato+"" );
    }

  public static void main( String args[] ) {
    java429 a[] = new java429[20];

    for ( int i=0; i < a.length; i++ )
      a[i] = new java429( (int)(Math.random()*100) );
    print( a );
    Arrays.sort( a );
    print( a );

    int loc = Arrays.binarySearch( a,a[3] );
    System.out.println( "Posicion de "+a[3]+" = "+loc );
    }
  }

//------------------------------------------- Final del fichero java429.java