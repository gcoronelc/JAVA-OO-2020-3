//  java2005S.java
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
//     Creacion: 27-Jun-1999  08:16:23
//     Revision: 09-Feb-2002  21:05:42
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor remoto RMI para el ejemplo de comunicacion entre
 * objetos. Implementa el interface y los dos metodos que permiten enviar
 * y recibir datos a los objetos remotos.
 */
import java.rmi.*;
import java.rmi.server.*;

class java2005S extends UnicastRemoteObject implements java2005I {
  private String texto;

  // Constructor de la clase
  public java2005S() throws RemoteException {
    super();
    }

  // Método que envía la cadena de texto al otro objeto Remoto
  public void enviaTexto( String texto ) {
    this.texto = texto;
    }

  // Método que recoge la cadena de texto
  public String getTexto() {
    return( texto );
    }

  public static void main(String[] args){

    try {
      // Se instala el controlador de seguridad
      if( System.getSecurityManager() == null ) {
        System.setSecurityManager( new RMISecurityManager() );
        }

      // Se crea el objeto remoto
      java2005I objRemoto = new java2005S();

      Naming.rebind( "ObjetoRemoto",objRemoto );

      System.out.println( "Objeto remoto preparado" );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//----------------------------------------- Final del fichero java2005S.java