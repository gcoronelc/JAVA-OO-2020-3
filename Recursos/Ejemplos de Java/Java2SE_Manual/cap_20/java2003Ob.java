//  java2003Ob.java
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
//     Creacion: 26-Jun-1999  17:47:00
//     Revision: 09-Feb-2002  21:04:04
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

public class java2003Ob extends UnicastRemoteObject implements java2003Ib {
  private int dato;

  // Constructor del objeto
  public java2003Ob( int dato ) throws RemoteException{
    // Inicializamos el dato del objeto
    this.dato = dato;
    }

  public void setDato( int dato ) throws RemoteException {
    this.dato = dato;
    }

  public int getDato() throws RemoteException{
    return( dato );
    }
  }

//---------------------------------------- Final del fichero java2003Ob.java