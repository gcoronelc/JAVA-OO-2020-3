//  HolaMundoRmiS.java
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
//     Creacion: 26-Jun-1999  09:45:12
//     Revision: 09-Feb-2002  21:01:26
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa es el servidor remoto para la version RMI del mensaje
 * de saludo "Hola Mundo"
 */
import java.rmi.*;
import java.rmi.server.*;

public class HolaMundoRmiS {

  public static void main( String args[] ) {

    try {
      // Se instala el controlador de seguridad
      if( System.getSecurityManager() == null ) {
        System.setSecurityManager( new RMISecurityManager() );
        }

      HolaMundoRmiO objRemoto = new HolaMundoRmiO();

      Naming.rebind( "ObjetoHola",objRemoto );

      System.out.println( "Objeto remoto preparado" );
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------- Final del fichero HolaMundoRmiS.java