//
//  java1710.java
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
//     Creacion: 03-Jan-1999  13:21:42
//     Revision: 03-Feb-2002  09:53:49
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es igual que el programa java1705, ya presentado, pero se
 * ha cambiado el servidor de conexión para poder probar el ejemplo
 * del servidor de eco que se ha planteado en el servidor de ejemplo
 * del Tutorial.
 * Se trata simplemente de conectarse al servidor y enviar una línea de
 * texto al puerto 7, que es el puerto de ECO ese servidor.
 * El programa declara las variables necesarias, luego establece la
 * conexión con el puerto 7 del servidor y abre los canales de entrada y
 * salida a través de los cuales se va a comunicar. Una vez que están listos
 * envía la línea de texto al servidor, que la devuelve al cliente, y lo
 * que se hace aquí es presentar la respuesta del servidor en la
 * pantalla.
 */
import java.net.*;
import java.io.*;
import java.util.*;

class java1710 {
  public static void main( String[] args ) {
    String servidor = "localhost";
    int puerto = 7;    // puerto echo

    try {
      // Abrimos un socket conectado al servidor y al
      // puerto estándar de echo
      Socket socket = new Socket( servidor,puerto );

      // Conseguimos el canal de entrada
      BufferedReader entrada = new BufferedReader(
        new InputStreamReader( socket.getInputStream() ) );
      // Conseguimos el canal de salida
      PrintWriter salida = new PrintWriter(
        new OutputStreamWriter( socket.getOutputStream() ),true );

      // Enviamos una línea de texto al servidor
      salida.println( "Prueba de Eco" );
      // Recogemos la línea devuelta por el servidor y la
      // presentamos en pantalla
      System.out.println( entrada.readLine() );

      // Cerramos el socket
      socket.close();
    } catch( UnknownHostException e ) {
      e.printStackTrace();
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
    } catch( IOException e ) {
      System.out.println( e );
    }
  }
}

//------------------------------------------ Final del fichero java1710.java