//  java2001I.java
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
//     Creacion: 26-Jun-1999  13:24:09
//     Revision: 09-Feb-2002  21:01:48
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el interface que declara los metodos de saludos que se
 * soportan en la utilizacion de los objetos remotos
 */
import java.rmi.*;

public interface java2001I extends Remote {
  // Los dos métodos que implementan los objetos remotos
  String hola( String cliente ) throws RemoteException;
  String adios( String cliente ) throws RemoteException;
  }

//----------------------------------------- Final del fichero java2001I.java