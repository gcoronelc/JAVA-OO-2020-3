//
//  java700.java
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
//     Creacion: 30-Sep-1996  06:11:40
//     Revision: 03-Feb-2002  06:10:53
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

class java700 {

  public static void main( String args[] ) {
    int x;
    double rand,y,z;
    float max;

    rand = Math.random();
    x = Math.abs( -123 );
    y = Math.round( 123.567 );
    z = Math.pow( 2,4 );
    max = Math.max( (float)1e10,(float)3e9 );

    // Se imprimen en consola los números obtenidos de las operaciones
    // anteriores para comprobar los resultados de la aplicación de
    // los métodos definidos en la clase Math
    System.out.println( rand );
    System.out.println( x );
    System.out.println( y );
    System.out.println( z );
    System.out.println( max );
    }
  }

//------------------------------------------ Final del fichero java700.java