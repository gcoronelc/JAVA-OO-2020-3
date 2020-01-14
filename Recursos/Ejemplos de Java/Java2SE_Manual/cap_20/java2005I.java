//  java2005I.java
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
//     Creacion: 27-Jun-1999  08:14:00
//     Revision: 09-Feb-2002  21:05:35
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el interface que implementan los objetos que se van a enviar
 * datos entre ellos. Declara los dos metodos que se utilizan: uno para
 * recibir datos y otro para enviarlos
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface java2005I extends Remote {
  public void enviaTexto( String texto ) throws RemoteException;
  public String getTexto() throws RemoteException;
}

//----------------------------------------- Final del fichero java2005I.java