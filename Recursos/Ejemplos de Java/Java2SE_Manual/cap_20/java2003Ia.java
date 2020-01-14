//  java2003Ia.java
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
//     Creacion: 26-Jun-1999  17:50:00
//     Revision: 09-Feb-2002  21:03:38
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es la definicion de un interface RMI diseñado para mostrar la
 * diferencia que hay entre el paso/retorno de datos entre objetos remotos
 * y objetos locales, los cuales no implementan en interface Remote. Este
 * interface extiende el interface Remote.
 */
import java.rmi.*;
import java.util.*;

public interface java2003Ia extends Remote {

  Date getFecha() throws RemoteException;

  java2003Ib getObjeto() throws RemoteException;
  }

//---------------------------------------- Final del fichero java2003Ia.java