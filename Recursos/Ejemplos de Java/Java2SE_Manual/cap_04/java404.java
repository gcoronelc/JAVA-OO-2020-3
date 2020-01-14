//
//  java404.java
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
//     Creacion: 29-Jul-1997  12:05:31
//     Revision: 02-Feb-2002  20:15:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ejemplo para ilustrar la asignación de un array a otro, con lo que
 * acabamos teniendo dos referencias al mismo array
 */
class java404 {
  int primerArray[];
  int segundoArray[];

  // Constructor de la clase
  java404() {
    primerArray = new int[3];

    for ( int i=0; i < 3; i++ )
      primerArray[i] = i;

    segundoArray = new int[3];
    segundoArray = primerArray;
    }

  public static void main( String args[] ) {
    java404 obj = new java404();

    System.out.println( "Contenido del primerArray" );
    for ( int i=0; i < 3; i++ )
      System.out.print( obj.primerArray[i] + " " );
    System.out.println();

    System.out.println( "Contenido del segundoArray" );
    for ( int i = 0; i < 3; i++ )
      System.out.print( obj.segundoArray[i] + " " );
    System.out.println();

    System.out.println( "--> Cambiamos un valor en el primerArray" );
    obj.primerArray[1] = 10;
    System.out.println( "Contenido del primerArray" );
    for ( int i=0; i < 3; i++ )
      System.out.print( obj.primerArray[i] + " " );
    System.out.println();

    System.out.println( "Contenido del segundoArray" );
    for ( int i=0; i < 3; i++ )
      System.out.print( obj.segundoArray[i] + " " );
    System.out.println();
    }
  }

//------------------------------------------- Final del fichero java404.java