//  java2002Ob.java
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
//     Creacion: 26-Jun-1999  16:45:41
//     Revision: 09-Feb-2002  21:03:10
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el objeto remoto de la version del programa de saludos que
 * soporta la utilizacion de dos objetos, que soporta los dos metodos de
 * saludo y despedida
 */
import java.rmi.*;
import java.rmi.server.*;

public class java2002Ob extends UnicastRemoteObject implements java2002Ib {
  String idObj;

  // Constructor del objeto remoto
  public java2002Ob( String idObj ) throws RemoteException {
    // Almacenamos el identificador del objeto
    this.idObj = idObj;
    }

  // M�todo para el saludo de bienvenida
  public String hola( String cliente ) throws RemoteException {
    return( "Hola " +cliente+ " desde el " +idObj );
    }

  // M�todo para el saludo de despedida
  public String adios( String cliente ) throws RemoteException {
    return( "Adios " +cliente+ " desde el " +idObj );
    }
  }

//---------------------------------------- Final del fichero java2002Ob.java