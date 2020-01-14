//
//  java903.java
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
//     Creacion: 08-Oct-1997  06:11:54
//     Revision: 03-Feb-2002  11:28:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra como se especifican y se recogen
 * excepciones
 */
import java.lang.Thread;

class java903 {
  public static void main( String args[] ) {
    // Se instancia un objeto
    java903 obj = new java903();
    // Se crea la secuencia try/catch que llamara al metodo que lanza
    // la excepcion
    try {
      // Llamada al metodo que genera la excepcion
      obj.miMetodo();
    } catch( InterruptedException e ) {
      } // Procesa la excepcion
    }

  // Este es el metodo que va a lanzar la excepcion
  void miMetodo() throws InterruptedException {
    Thread.currentThread().sleep( 1000 ); // currentThread() genera
                                          // una excepcion
    }
  }

//------------------------------------------- Final del fichero java903.java