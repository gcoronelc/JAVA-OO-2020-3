//
//  java402.java
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
//     Creacion: 29-Jul-1997  13:12:29
//     Revision: 02-Feb-2002  20:13:27
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ejemplo para ilustrar el uso del operador ternario if-then-else
 * Para generar la excepción de división por cero, comentar la linea
 * que asigna este operador a la expresión de la variable f y descomentar
 * la versión que realiza la división únicamente
 */
class java402 {
  public static void main( String args[] ) {
    int a = 28;
    int b = 4;
    int c = 45;
    int d = 0;

    // Utilizamos el operador ternario para asignar valores a
    // las dos variables e y f, que son resultado de la
    // evaluación realizada por el operador
    int e = (b == 0) ? 0 : (a / b);
    int f = (d == 0) ? 0 : (c / d);
    // int f = c / d;

    System.out.println( "a = " + a );
    System.out.println( "b = " + b );
    System.out.println( "c = " + c );
    System.out.println( "d = " + d );
    System.out.println();
    System.out.println( "a / b = " + e );
    System.out.println( "c / d = " + f );
    }
  }

//------------------------------------------- Final del fichero java402.java