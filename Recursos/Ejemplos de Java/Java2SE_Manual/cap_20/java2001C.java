//  java2001C.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 26-Jun-1999  13:12:23
//     Revision: 09-Feb-2002  21:01:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el programa cliente de la version del programa de saludos que
 * soporta la utilizacion de dos objetos, cada uno de ellos con dos
 * metodos
 */
import java.rmi.*;

public class java2001C {

  public static void main( String[] args ) {

    String direccion = "rmi://localhost/";
    try {
      java2001I refObj1 =
        (java2001I)Naming.lookup( direccion+"ObjetoRemoto1" );
      java2001I refObj2 =
        (java2001I)Naming.lookup( direccion+"ObjetoRemoto2" );

      System.out.println( refObj1.hola( "Agustin" ) );
      System.out.println( refObj1.adios( "Agustin" ) );
      System.out.println( refObj2.hola( "Agustin" ) );
      System.out.println( refObj2.adios( "Agustin" ) );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    System.exit( 0 );
    }
  }

//----------------------------------------- Final del fichero java2001C.java