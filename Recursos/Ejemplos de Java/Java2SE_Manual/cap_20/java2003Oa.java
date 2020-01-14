//  java2003Oa.java
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
//     Creacion: 26-Jun-1999  17:37:00
//     Revision: 09-Feb-2002  21:03:54
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es una clase remota RMI diseñada para mostrar la diferencia que hay
 * entre el paso/retorno de datos entre objetos remotos y objetos locales,
 * los cuales no implementan en interface Remote. Esta clase implementa el
 * interface Remote indirectamente a traves de herencia.
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class java2003Oa extends UnicastRemoteObject implements java2003Ia {

  private Date fecha = new Date();
  private java2003Ob objRemoto;

  // Constructor del objeto
  public java2003Oa() throws RemoteException{
    objRemoto = new java2003Ob( 500 );
    }

  // Metodo que devuelve la fecha del objeto
  public Date getFecha() throws RemoteException {
    return( fecha );
    }

  // Metodo que devuelve el objeto remoto
  public java2003Ib getObjeto() throws RemoteException {
    return( objRemoto );
    }
  }

//---------------------------------------- Final del fichero java2003Oa.java