//
//  java511.java
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
//     Creacion: 02-Oct-1997  02:48:38
//     Revision: 02-Feb-2002  21:11:40
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra la sobreescritura del método equals()de la clase
 * Object para determinar si dos objetos son equivalentes.
 * Esta comprobación no es igual a comprobar que dos referencias
 * apuntando a un mismo objeto a través del operador relacional ==
 */

class java511 {
  int miDato;

  // Constructor parametrizado
  java511( int dato ) {
    miDato = dato;
    }

  public static void main(String args[] ) {
    // Se instancian los objetos que se van a testear
    java511 obj1 = new java511( 2 );
    java511 obj2 = new java511( 2 );
    java511 obj3 = new java511( 3 );
    String obj4 = "Un objeto String";

    // Se realizan las comprobaciones y se presenta por pantalla
    // el resultado de cada una de ellas
    System.out.println( "obj1 equals obj1: " +
      obj1.equals( obj1 ) );
    System.out.println( "obj1 equals obj2: " +
      obj1.equals( obj2 ) );
    System.out.println( "obj1 equals obj3: " +
      obj1.equals( obj3 ) );
    System.out.println( "obj1 equals obj4: " +
      obj1.equals( obj4 ) );
    }

  // Se sobreescribe el metodo equals()
  public boolean equals( Object arg ) {
    // Se comprueba que el argumento es del tipo adecuado y
    // que no es nulo. Si lo anterior se cumple se realiza
    // la comprobacion de equivalencia de los datos.
    // Observese que se ha empleado el operador instanceof
    if ( (arg != null) && (arg instanceof java511) ) {
      // Hacemos un moldeado del Object general a tipo java511
      java511 temp = (java511)arg;
      // Se realiza la comparacion y se devuelve el resultado
      return( this.miDato == temp.miDato );
      }
    else {
      // No es del tipo esperado
      return( false );
      }
    }
  }

//------------------------------------------- Final del fichero java511.java