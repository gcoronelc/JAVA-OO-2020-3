//
//  FechaApp.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 07-Jun-1996  02:35:08
//     Revision: 02-Feb-2002  21:24:07
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.util.Date;

class FechaApp {

  public static void main( String args[] ) {
    // Obtiene la fecha del sistema
    Date hoy = new Date();

    // La imprime en la consola
    System.out.println( hoy );
    }
  }

//------------------------------------------ Final del fichero FechaApp.java