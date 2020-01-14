//
//  java808.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 07-Ene-2002  06:55:20
//     Revision: 03-Feb-2002  09:06:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra el uso de Canales a través del paquete de
 * Entrada/Salida de Java.
 * Es muy simple y solamente copia el contenido del archivo que se
 * indica en primer lugar sobre el archivo destino, utilizando
 * la transferencia optimizada del nuevo paquete a través de
 * canales.
 */

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class java808 {
  public static void main( String[] args ) {
    FileChannel entrada = null;
    FileChannel salida = null;

    // Comprobamos los argumentos de invocación
    if( args.length < 2 ) {
      System.out.println( "Uso: java Copiar <origen> <destino>" );
      System.exit( 1 );
      }

    try {
      // Abre el archivo origen
      entrada = new FileInputStream(args[0]).getChannel();
      // Crea el archivo destino
      salida = new FileOutputStream(args[1]).getChannel();
      // Este es el método que realiza la copia del contenido
      // del archivo origen en el archivo destino
      salida.transferFrom( entrada,0L,(int)entrada.size() );
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      // Al terminar, cerramos los archivos
      try {
        if( entrada != null )
          entrada.close();
        if( salida != null )
          salida.close();
      } catch( IOException e ) {
        e.printStackTrace();
        }
      }
    }
  }

//------------------------------------------- Final del fichero java808.java
