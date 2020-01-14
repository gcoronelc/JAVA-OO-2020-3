//
//  java703.java
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
//     Creacion: 02-Oct-1997  04:31:52
//     Revision: 03-Feb-2002  06:15:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la creacion de una clase Double pasando un valor
 * double y tambien pasando una cadena que se puede interpretar como
 * double
 */
class java703 {
  public static void main( String args[] ) {
    Double d1 = new Double( 3.14159 );
    Double d2 = new Double( "314159E-5" );
    System.out.println( d1 + " = " + d2 + " -> " + d1.equals( d2 ) );
    }
  }

//------------------------------------------- Final del fichero java703.java