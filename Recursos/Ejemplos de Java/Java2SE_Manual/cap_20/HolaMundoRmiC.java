//  HolaMundoRmiC.java
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
//     Creacion: 26-Jun-1999  10:24:19
//     Revision: 09-Feb-2002  21:00:49
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa es el cliente para la version RMI del mensaje
 * de saludo "Hola Mundo"
 */
import java.rmi.*;

public class HolaMundoRmiC {

  public static void main( String[] args ) {
    // Direccion de la maquina remota, en este caso la maquina local,
    // si se va a ejecutar en una maquina diferente, se debera cambiar
    // a algo semejante a: "rmi://www.servidor.com"
    String direccion = "rmi://localhost/";
    try {
      HolaMundoRmiI hm =
        (HolaMundoRmiI)Naming.lookup( direccion+"ObjetoHola" );
      System.out.println( hm.objRemotoHola( "Mundo" ) );
    } catch( Exception e ) {
      e.printStackTrace();
      }
    System.exit( 0 );
  }
}

//------------------------------------- Final del fichero HolaMundoRmiC.java