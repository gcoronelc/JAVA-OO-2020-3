//
//  java906.java
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
//     Creacion: 09-Oct-1997  05:30:41
//     Revision: 03-Feb-2002  11:32:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se indica que el metodo demoproc() puede lanzar
 * una excepción de tipo IllegalAccess, pero no se podrá compilar
 * porque es imprescindible indicar esta circunstancia con throws
 */
class java906 {
  static void demoproc() {
    System.out.println( "Capturada la excepcion en demoproc" );
    throw new IllegalAccessException( "demo" );
    }

  public static void main( String args[] ) {
    demoproc();
    }

  }

//------------------------------------------- Final del fichero java906.java