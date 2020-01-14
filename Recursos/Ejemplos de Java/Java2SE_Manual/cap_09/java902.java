//
//  java902.java
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
//     Creacion: 08-Oct-1997  05:53:25
//     Revision: 03-Feb-2002  11:28:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa no compila, deberia aparecer el siguiente error:
 *
 * java902.java:41: Exception java.lang.InterruptedException must be caught,
 * or it must be declared in the throws clause of this method.
 *    Thread.currentThread().sleep( 1000 ); // currentThread() genera
 *                        ^
 */
import java.lang.Thread;

class java902 {
  public static void main( String args[] ) {
    java902 obj = new java902();
    obj.miMetodo();
    }

  void miMetodo() {
    // Aqui se produce el segundo error de compilacion, porque no se esta
    // declarando la excepcion que genera este metodo
    Thread.currentThread().sleep( 1000 ); // currentThread() genera
                                          // una excepcion
    }
  }

//------------------------------------------- Final del fichero java902.java