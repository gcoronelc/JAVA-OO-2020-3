//
//  java807.java
//  Copyright (c) 2001, Agustin Froufe
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
//     Creacion: 16-Dic-2001  11:29:21
//     Revision: 03-Feb-2002  07:17:07
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Versión modificada del ejemplo java806.
 * Lectura de un fichero de texto a través de un buffer de lectura,
 * lo cual acelerará la lectura del fichero. También se incorpora un
 * contador para cuantificar el tiempo que se tarda en leer el
 * archivo.
 */

import java.io.*;
import java.util.prefs.*;

public class java807 {
  public static void main( String args[] ) {
    long intervalo = System.currentTimeMillis();
    BufferedReader bReader = null;
    int c;

    try {
      bReader = new BufferedReader( new FileReader("prueba.txt") );
      while( (c = bReader.read()) != -1 ) {
        }
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      try {
        if( bReader != null )
          bReader.close();
      } catch( Exception e ) {
        e.printStackTrace();
        }
      }
    System.out.println( "Tiempo: "+
      (System.currentTimeMillis()-intervalo)+ " msgs." );
    }
  }

//------------------------------------------- Final del fichero java807.java