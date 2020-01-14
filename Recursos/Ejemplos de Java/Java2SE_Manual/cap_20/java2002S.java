//  java2001S.java
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
//     Creacion: 26-Jun-1999  13:43:17
//     Revision: 09-Feb-2002  21:03:20
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor remoto de la version del programa de saludos que
 * soporta la utilizacion de dos objetos de diferentes tipos
 */
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

public class java2002S {

  public static void main( String args[] ) {

    try {
      // Se instala el controlador de seguridad
      if( System.getSecurityManager() == null ) {
        System.setSecurityManager( new RMISecurityManager() );
        }

      // Se crean los dos objetos remotos
      java2002Oa objRemoto1 = new java2002Oa( "Objeto Remoto A-1" );
      java2002Ob objRemoto2 = new java2002Ob( "Objeto Remoto B-2" );

      // Se crea el registro del objeto sobre la máquina local y el
      // puerto de RMI
      LocateRegistry.createRegistry( 1099 );

      Naming.rebind( "ObjetoRemoto1",objRemoto1 );
      Naming.rebind( "ObjetoRemoto2",objRemoto2 );

      System.out.println( "Objeto remoto preparado" );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//----------------------------------------- Final del fichero java2002S.java