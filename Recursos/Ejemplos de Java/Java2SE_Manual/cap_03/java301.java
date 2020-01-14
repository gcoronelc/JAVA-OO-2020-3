//
//  java301.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 20-Oct-1996  03:31:05
//     Revision: 02-Feb-2002  20:05:23
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y est  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra en pantalla los argumentos que se pasan
 * en la línea de comandos
 */
class java301 {
  public static void main( String args[] ) {
    for ( int i=0; i < args.length; i++ )
      System.out.println( args[i] );
    }
  }

//----------------------------------------- Final del fichero java301.java
