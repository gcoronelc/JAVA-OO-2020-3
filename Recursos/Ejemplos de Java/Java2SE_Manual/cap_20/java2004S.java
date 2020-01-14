//  java2004S.java
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
//     Creacion: 26-Jun-1999  23:04:54
//     Revision: 09-Feb-2002  21:04:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor remoto que implementa el inferface de la lista
 * remota. Crea un objeto de este tipo que devuelve la lista de cadenas
 * con que se inicializa
 */
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;

public class java2004S extends UnicastRemoteObject implements java2004I {
  String[] datos = { "Uno","Dos","Tres","Cuatro","Cinco" };

  // Constructor
  public java2004S() throws RemoteException {}

  // Método para devolver la lista de cadenas
  public String[] getDatos() throws RemoteException {
    return( datos );
    }

  public static void main(String args[]) {

    try {
      // Se instala el controlador de seguridad
      if( System.getSecurityManager() == null ) {
        System.setSecurityManager( new RMISecurityManager() );
        }

      // Se crea el objeto remoto
      java2004S lista = new java2004S();
      // Se registra
      Naming.rebind( "ListaRemota",lista );
      System.out.println( "Objeto remoto Lista preparado" );
    } catch( Exception e ) {
      e.printStackTrace();
      return;
    }
  }
}

//----------------------------------------- Final del fichero java2004S.java