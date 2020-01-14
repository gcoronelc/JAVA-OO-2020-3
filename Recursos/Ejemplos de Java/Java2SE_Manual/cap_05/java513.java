//
//  java513.java
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
//     Creacion: 29-Sep-1997  18:32:23
//     Revision: 02-Feb-2002  21:14:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra como se sobreescribe el método toString()
 * de la clase Object
 */

class java513 {
  // Se definen las variables de instancia para la clase
  String uno;
  String dos;
  String tres;

  // Constructor de la clase
  java513( String a,String b,String c ) {
    uno = a;
    dos = b;
    tres = c;
    }


  public static void main( String args[] ) {
    // Se instancia un objeto de la clase
    java513 obj = new java513( "Tutorial","de","Java" );

    // Se presenta el objeto utilizando el metodo sobreescrito
    System.out.println( obj.toString() );
    }


  // Sobreescritura del metodo toString() de la clase Object
  public String toString() {
    // Convierte un objeto a cadena y lo devuelve
    return( uno+" "+dos+" "+tres );
    }
  }

//------------------------------------------- Final del fichero java513.java