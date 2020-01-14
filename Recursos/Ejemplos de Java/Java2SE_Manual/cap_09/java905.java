//
//  java905.java
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
//     Creacion: 09-Oct-1997  05:15:49
//     Revision: 03-Feb-2002  11:31:22
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra cómo se pueden tratar las excepciones
 * dos veces, estableciendo dos contextos en los que se puede
 * capturar la misma excepción
 */
class java905 {
  static void demoproc() {
    try {
      throw new NullPointerException( "demo" );
    } catch( NullPointerException e ) {
      System.out.println( "Capturada la excepcion en demoproc" );
      throw e;
      }
    }

  public static void main( String args[] ) {
    try {
      demoproc();
    } catch( NullPointerException e ) {
      System.out.println( "Capturada de nuevo: " + e );
      }
    }
  }

//------------------------------------------- Final del fichero java905.java