//
//  java512.java
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
//     Creacion: 29-Sep-1997  18:12:43
//     Revision: 02-Feb-2002  21:12:41
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se ilustra el uso del método getClass() de la clase
 * Object y algunos otros métodos de la clase Class.
 * También se muestra la instanciación de nuevos objetos de tipos de los
 * que el compilador no sabe nada acerca de ellos en tiempo de
 * compilación
 *
 * Si se introduce un 0, la salida que genera el programa es:
 *  Nombre de la clase de obj1: java512
 *  Nombre de la superclase de obj1: class java.lang.Object
 *  Introduce un 0 o un 1
 *  0
 *  Nombre de la clase de obj2: java.lang.String
 *  Nombre de la superclase de obj2: class java.lang.Object
 *
 * Si se introduce un 1, la salida que genera el programa es:
 *  Nombre de la clase de obj1: java512
 *  Nombre de la superclase de obj1: class java.lang.Object
 *  Introduce un 0 o un 1
 *  1
 *  Nombre de la clase de obj2: java512
 *  Nombre de la superclase de obj2: class java.lang.Object
 */
import java.io.*;

class java512 {

  public static void main( String args[] ) {
    java512 obj1 = new java512();

    // Se usa el metodo getClass() de la clase Object y dos
    // metodos de la clase Class para obtener y presentar
    // informacion acerca del objeto
    System.out.println( "Nombre de la clase de obj1: "
      + obj1.getClass().getName() );
    System.out.println( "Nombre de la superclase de obj1: "
      + obj1.getClass().getSuperclass() );

    // Ahora se instancia otro objeto basandose en la entrada
    // del usuario, de forma que el compilador no tiene forma
    // de conocer el tipo del objeto en tiempo de compilacion
    // Se declara una referencia a un objeto generico
    Object obj2 = null;

    // Se pide la entrada al usuario
    System.out.println( "Introduce un 0 o un 1" );
    try {
      // Captura la entrada del usuario
      int dato = System.in.read();
      // Si se ha introducido un 0
      if ( (char)dato == '0' )
        // Se crea un objeto String
        obj2 = "Esto es un objeto String";
      // Sino, se crea un nuevo objeto del mismo tipo que el anterior
      else
        obj2 = obj1.getClass().newInstance();
    } catch( Exception e ) {
      System.out.println( "Excepcion " + e );
      }

    // Ahora se indican la clase y superclase del nuevo objeto
    System.out.println( "Nombre de la clase de obj2: "
      + obj2.getClass().getName() );
    System.out.println( "Nombre de la superclase de obj2: "
      + obj2.getClass().getSuperclass());
    }
  }

//------------------------------------------- Final del fichero java512.java