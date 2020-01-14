//  java2003S.java
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
//     Revision: 09-Feb-2002  21:04:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor remoto diseñado para mostrar la diferencia que hay
 * entre el paso/retorno de datos entre objetos remotos y objetos locales,
 * los cuales no implementan en interface Remote
 */
import java.rmi.*;
import java.rmi.server.*;

public class java2003S {

  public static void main( String args[] ) {
    // Se instala el controlador de seguridad
    if( System.getSecurityManager() == null ) {
      System.setSecurityManager( new RMISecurityManager() );
      }

    try {
      // Se crea el objeto remoto princiapl
      java2003Oa objRemoto = new java2003Oa();

      Naming.rebind( "ObjetoRemoto",objRemoto );

      System.out.println( "Objeto remoto preparado" );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//----------------------------------------- Final del fichero java2003S.java