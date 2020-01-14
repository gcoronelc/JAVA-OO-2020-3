//
//  java714.java
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
//     Creacion: 05-Oct-1997  18:01:57
//     Revision: 03-Feb-2002  06:31:14
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

class java714 {
  public static void main( String args[] ) {
    Runtime r = Runtime.getRuntime();
    Process p = null;
    String comando[] = { "notepad","java714.java"};

    // Datos de la memoria del Sistema
    System.out.println( "Memoria Total = "+ r.totalMemory() +
      " Memoria Libre = "+ r.freeMemory() );

    // Intenta ejecutar el comando que se le indica, en este caso
    // lanzar el bloc de notas
    try {
      p = r.exec( comando );
    } catch( Exception e ) {
      System.out.println( "Error ejecutando "+comando[0] );
      }
    }
  }

//------------------------------------------- Final del fichero java714.java