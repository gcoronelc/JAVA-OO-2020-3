//
//  java403.java
//  Copyright (c) 1997,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 29-Jul-1997  09:06:29
//     Revision: 02-Feb-2002  20:13:55
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ejemplo para ilustrar la creaci�n y manipulaci�n de un array de dos
 * dimensiones en donde los subarrays tienen diferente tama�o
 */
class java403 {
  public static void main( String args[] ) {

    // Declaramos un array de dos dimensiones con un tama�o de 3 en la
    // primera dimensi�n y diferentes tama�os en la segunda
    int miArray[][] = new int[3][];  // No especificamos el tama�o de la
    // segunda dimensi�n
    miArray[0] = new int[2]; // El tama�o de la segunda dimensi�n es 2
    miArray[1] = new int[3]; // El tama�o de la segunda dimensi�n es 3
    miArray[2] = new int[4]; // El tama�o de la segunda dimensi�n es 4

    // Rellenamos el array con datos
    for ( int i=0; i < 3; i++ ) {
      for ( int j=0; j < miArray[i].length; j++ )
        miArray[i][j] = i * j;
      }

    // Visualizamos los datos que contiene el array
    for ( int i=0; i < 3; i++ ) {
      for ( int j=0; j < miArray[i].length; j++ )
        System.out.print( miArray[i][j] );
      System.out.println();
     }

    // Hacemos un intento de acceder a un elemento que se encuentre
    // fuera de los l�mites del array
    System.out.println( "Acceso a un elemento fuera de limites" );
    miArray[4][0] = 7;
    // Esa sentencia originar� el lanzamiento de una excepci�n de
    // tipo ArrayIndexOutOfBounds
    }
  }

//------------------------------------------- Final del fichero java403.java