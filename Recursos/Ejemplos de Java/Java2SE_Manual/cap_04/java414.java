//
//  java414.java
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
//     Creacion: 16-Feb-1998  08:12:46
//     Revision: 02-Feb-2002  20:32:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo demuestra la utilización de BitSets y un poco de su
 * manipulación
 */
import java.util.*;

public class java414 {
  public static void main(String args[]) {
    Random aleat = new Random();

    // Coge el bit menos significativo devuelto por nextInt()
    byte bt = (byte)aleat.nextInt();
    BitSet bbyte = new BitSet();
    for ( int i=7; i >= 0; i-- ) {
      if ( ( (1 << i) & bt ) != 0 )
        bbyte.set( i );
      else
        bbyte.clear( i );
      }
    System.out.println( "Valor byte: "+bt );
    printBitSet( bbyte );

    short st = (short)aleat.nextInt();
    BitSet bshort = new BitSet();
    for ( int i=15; i >= 0; i-- ) {
      if ( ( (1 << i) & st ) != 0 )
        bshort.set( i );
      else
        bshort.clear( i );
      }
    System.out.println( "Valor short: "+st );
    printBitSet( bshort );

    int it = aleat.nextInt();
    BitSet bint = new BitSet();
    for ( int i=31; i >= 0; i-- ) {
      if ( ( (1 << i) & it ) != 0 )
        bint.set( i );
      else
        bint.clear( i );
      }
    System.out.println( "Valor int: "+it );
    printBitSet(bint);

    // Prueba BitSets mayores o iguales a 64 bits
    BitSet b1 = new BitSet();
    b1.set( 127 );
    System.out.println( "Fija el bit 127: "+b1 );

    BitSet b2 = new BitSet( 65 );
    b2.set(255);
    System.out.println( "Fija el bit 255: "+b2 );

    BitSet b3 = new BitSet( 512 );
    b3.set( 1023 );
    System.out.println( "Fija el bit 1023: "+b3 );
    }

  static void printBitSet( BitSet b ) {
    System.out.println( "Bits: "+b );

    String bbits = new String();
    for ( int j=0; j < b.size(); j++ )
      bbits += ( b.get( j ) ? "1" : "0" );
    System.out.println( "Patron de bits: "+bbits );
    }
  }

//------------------------------------------- Final del fichero java414.java