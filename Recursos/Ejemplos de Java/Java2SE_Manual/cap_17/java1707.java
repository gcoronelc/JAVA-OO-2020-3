//
//  java1707.java
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
//     Creacion: 03-Jan-1999  09:00:02
//     Revision: 03-Feb-2002  09:53:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es un navegador muy simple implementado mediante
 * sockets. Evidentemente las páginas que se pueden recibir
 * han de ser de lo más sencillas, no obstante, se implementa
 * lo suficiente del protocolo http para que la recepción sea
 * decente.
 * Hay que estar conectado para que el programa pueda funcionar,
 * sino se obtiene una excepción de tipo UnknownHostException.
 * La mayor parte del programa está encerrada en el bloque
 * try/catch que recoge las excepciones.
 * El programa abre un socket con el servidor y el puerto 80,
 * luego utiliza la clase BufferedReader junto con la clase
 * InputStreamReader para abrir un canal de entrada desde
 * el socket.
 * Luego se utiliza la clase PrintWriter junto con la clase
 * OutputStreamWriter para abrir un canal de salida al
 * socket. La salida se autolibera, lo cual es crítico, ya
 * que si esa salida no se libera, el servidor no responde,
 * probablemente lo que ocurra es que el servidor no
 * contesta hasta que tenga la información de la petición
 * completa.
 * El programa, actuando como navegador, cliente http,
 * envía un comando GET al servidor indicando un camino
 * y un fichero determinados, para que éste intente
 * encontrarlos y enviárselos de vuelta al cliente.
 * Cuando ya no haya más líneas que leer, se recibirá
 * un null, lo cual hará que el cliente salga del bucle
 * de entrada y cierre el socket.
 */
import java.net.*;
import java.io.*;

class java1707 {
  public static void main( String[] args ) {
    String servidor = "usuarios.tripod.es";   // servidor
    int puerto = 80;                          // puerto

    try {
      // Crea un socket, conectado al servidor y al puerto
      // que se indica
      Socket socket = new Socket( servidor,puerto );
      // Crea el canal de entrada desde el socket
      BufferedReader entrada = new BufferedReader(
        new InputStreamReader( socket.getInputStream() ) );
      // Crea el canal de salida
      PrintWriter salida = new PrintWriter(
        new OutputStreamWriter( socket.getOutputStream() ),true );

      // Envía un comando GET al servidor
      salida.println(
        "GET /froufe/clases/prueba01.html" );

      // En esta cadena se va a ir leyendo el fichero
      String linea = null;
      // Bucle para leer y presentar la líneas hasta que se
      // reciba un null
      while( (linea = entrada.readLine()) != null )
        System.out.println( linea );

      // Se cierra el socket
      socket.close();
    } catch( UnknownHostException e ) {
      e.printStackTrace();
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------ Final del fichero java1707.java