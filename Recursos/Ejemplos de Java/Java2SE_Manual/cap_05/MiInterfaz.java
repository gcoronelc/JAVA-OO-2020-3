//
//  MiInterfaz.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 02-Oct-1997  03:12:33
//     Revision: 02-Feb-2002  21:14:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Definicion del interfaz utilizado en el ejemplo java514
 * Ilustra el uso de "interface".
 * Este interfaz contiene las declaraciones de los metodos get() y put()
 */

public interface MiInterfaz {
  void put( int dato );
  int get();
  }

//---------------------------------------- Final del fichero MiInterfaz.java