//
//  java713.java
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
//     Creacion: 30-Sep-1997  13:16:24
//     Revision: 03-Feb-2002  06:29:31
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ilustra el uso de un objeto de tipo Property, para poder recoger
 * las características del sistema
 */
import java.util.*;
import java.lang.*;

class java713 {
  public static void main( String args[] ) {
    // Instancia y presenta un objeto Properties para presentar
    // las características del sistema
    Properties obj = new Properties( System.getProperties() );

    obj.list( System.out );
    }
  }

//------------------------------------------- Final del fichero java713.java