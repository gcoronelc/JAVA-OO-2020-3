//
//  java702.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 02-Oct-1997  04:24:20
//     Revision: 03-Feb-2002  06:14:32
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la creacion de dos objetos Double, uno de ellos
 * es infinito y el otro es un no-numero
 */
class java702 {
  public static void main( String args[] ) {
    Double d1 = new Double( 1/0. );
    Double d2 = new Double( 0/0. );

    System.out.println( d1 + ": " + d1.isInfinite() + ", " +
      d1.isNaN() );
    System.out.println( d2 + ": " + d2.isInfinite() + ", " +
      d2.isNaN() );
    }
  }

//------------------------------------------- Final del fichero java702.java