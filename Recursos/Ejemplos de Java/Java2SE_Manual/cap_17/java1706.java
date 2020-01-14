//
//  java1706.java
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
//     Creacion: 02-Jan-1999  10:09:12
//     Revision: 03-Feb-2002  09:52:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la fecha y hora del servidor de la Casa Blanca, en
 * comparación con la hora actual de la máquina en que se está ejecutando,
 * en este caso en la residencia del Autor del Tutorial, Sevilla
 * La secuencia del programa es smejante a la de los ejemplos anteriores,
 * solamente que en este caso se conecta al puerto 13 del servidor de la
 * Casa Blanca, que es el puerto estándar para el servicio de fecha.
 * Una vez que se establece la conexión, no es necesario preguntar nada
 * al servidor, éste, por el mero hecho de haber establecido la conexión
 * en ese puerto, envía la hora automáticamente.
 * Finalmente se imprime la fecha y hora de la máquina utilizando la
 * clase Date.
 */
import java.net.*;
import java.io.*;
import java.util.*;

class java1706 {
  public static void main( String[] args ) {
    String servidor = "www.whitehouse.net";
    int puerto = 13;             // puerto de daytime

    try {
      // Abrimos un socket conectado al servidor y al
      // puerto estándar de echo
      Socket socket = new Socket( servidor,puerto );
      System.out.println( "Socket Abierto." );

      // Conseguimos el canal de entrada
      BufferedReader entrada = new BufferedReader(
        new InputStreamReader( socket.getInputStream() ) );

      System.out.println( "Hora actual en www.whitehouse.net:" );
      System.out.println( "\t"+entrada.readLine() );
      System.out.println( "Hora Actual en Sevilla, Spain:" );
      System.out.println( "\t"+new Date() );

      // Cerramos el socket
      socket.close();
    } catch( UnknownHostException e ) {
      System.out.println( e );
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
    } catch( IOException e ) {
      System.out.println( e );
      }
    }
  }

//------------------------------------------ Final del fichero java1706.java