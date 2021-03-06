//  java2001S.java
//  Copyright (c) 1999,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 26-Jun-1999  13:43:17
//     Revision: 09-Feb-2002  21:02:10
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor remoto de la version del programa de saludos que
 * soporta la utilizacion de dos objetos
 */
import java.rmi.*;
import java.rmi.server.*;

public class java2001S {

  public static void main( String args[] ) {

    try {
      // Se instala el controlador de seguridad
      if( System.getSecurityManager() == null ) {
        System.setSecurityManager( new RMISecurityManager() );
        }

      // Se crean los dos objetos remotos
      java2001O objRemoto1 = new java2001O( "Objeto Remoto 1" );
      java2001O objRemoto2 = new java2001O( "Objeto Remoto 2" );

      Naming.rebind( "ObjetoRemoto1",objRemoto1 );
      Naming.rebind( "ObjetoRemoto2",objRemoto2 );

      System.out.println( "Objeto remoto preparado" );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//----------------------------------------- Final del fichero java2001S.java