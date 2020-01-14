//  HolaMundoRmiO.java
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
//     Creacion: 26-Jun-1999  10:05:23
//     Revision: 09-Feb-2002  21:01:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el objeto remoto para la version RMI del mensaje
 * de saludo "Hola Mundo"
 */
import java.rmi.*;
import java.rmi.server.*;

public class HolaMundoRmiO extends UnicastRemoteObject
  implements HolaMundoRmiI {

  // Constructor del objeto remoto
  public HolaMundoRmiO() throws RemoteException {
    super();
    }

  public String objRemotoHola( String cliente )
    throws RemoteException {
    return( "Hola "+cliente );
    }
  }

//------------------------------------- Final del fichero HolaMundoRmiO.java