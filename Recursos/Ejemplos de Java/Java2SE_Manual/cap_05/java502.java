//
//  java502.java
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
//     Creacion: 29-Sep-1997  16:46:15
//     Revision: 02-Feb-2002  21:05:11
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra la forma de instanciacion de un objeto sin
 * la declaracion del nombre. Se instancia un objeto Date y se le pasa al
 * sistema de Entrada/Salida para que lo presente en pantalla.
 * La clase Date se encuentra en el paquete java.util, que es necesario
 * importar
 *
 * La salida que se produce es del tipo:
 * Sat Feb 02 21:05:26 CET 2002
 */
import java.util.*;

class java502 {
  public static void main( String args[] ) {
    System.out.println( new Date() );
    }
  }

//------------------------------------------- Final del fichero java502.java