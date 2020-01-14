//
//  java907java
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
//     Creacion: 09-Oct-1997  05:50:05
//     Revision: 03-Feb-2002  11:37:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo es igual que el presentado en java906, pero
 * ya solucionado su problema de compilacion, al indicar que el
 * metodo demoproc() puede lanzar una excepción de tipo acceso
 * ilegal y al colocar un bloque try/catch en el procedimiento
 * main() de la clase para poder capturar esa excepcion cuando
 * se produzca
 */
class java907 {
  static void demoproc() throws IllegalAccessException {
    System.out.println( "Dentro de demoproc" );
    throw new IllegalAccessException( "demo" );
    }

  public static void main( String args[] ) {
    try {
      demoproc();
    } catch( IllegalAccessException e ) {
      System.out.println( "Capturada de nuevo: " + e );
      }
    }
  }

//------------------------------------------- Final del fichero java907.java