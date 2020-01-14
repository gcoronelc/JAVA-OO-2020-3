//
//  java401.java
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
//     Creacion: 28-Jul-1997  12:06:29
//     Revision: 02-Feb-2002  20:13:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ejemplo para ilustrar el funcionamiento del operador módulo
 * tanto con números enteros como con números en coma flotante
 */
class java401 {
  public static void main( String args[] ) {
    int x=33;
    double y=33.3;

    System.out.println( "x mod 10 = " + x%10 );
    System.out.println( "y mod 10 = " + y%10 );
    }
  }

//------------------------------------------- Final del fichero java401.java