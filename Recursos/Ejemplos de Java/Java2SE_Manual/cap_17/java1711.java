//
//  java1711.java
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
//     Revision: 03-Feb-2002  09:54:17
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * El único propósito de este ejemplo es comprobar el controlador de
 * seguridad que se ha implementado en el ejemplo java1709, siendo
 * igual que el ejemplo java1707, excepto que se conecta con el
 * localhost e intenta recoger un fichero protegido de ese servidor,
 * intentando subir en el árbol de directorio, para acceder a otros
 * ficheros, que no tuviesen alcance de otra forma.
 * Recuerde el lector que el control de seguridad implementado no está
 * protegido contra peticiones de ficheros indicando el camino absoluto
 * en que se encuentra ese fichero. Si se desea implementar este caso
 * se puede utilizar el método isAbsolute() de la clase File.
 * El programa es pues un cliente http (navegador) implementado con
 * sockets, pero como el acceso a ficheros a ramas superiores a la de
 * entrada, se generará una excepción, que será enviada al cliente.
 */
import java.net.*;
import java.io.*;

class java1711 {
  public static void main( String[] args ) {
    String servidor = "localhost"; // servidor
    int puerto = 80;               // puerto
    try {
      // Crea un socket, conectado al servidor y al puerto
      // que se indica
      Socket socket = new Socket(servidor,puerto);
      // Crea el canal de entrada desde el socket
      BufferedReader inputStream =
        new BufferedReader( new InputStreamReader(
        socket.getInputStream() ) );
      // Crea el canal de salida
      PrintWriter outputStream =
        new PrintWriter( new OutputStreamWriter(
        socket.getOutputStream() ),true );

      // Envía un comando GET al servidor. La siguiente sentencia hará
      // que el servidor devuelva la excepción de violación de
      // seguridad, al intentar recoger el fichero del directorio
      // padre
//      outputStream.println( "GET ../prueba01.html" );
      // La siguiente sentencia sí que devuelve el fichero que se
      // solicite, si se encuentra ahí.
      // El lector debería protegerse contra ello
      outputStream.println( "GET c:/prueba01.html" );

      // En esta cadena se va a ir leyendo el fichero
      String line = null;
      // Bucle para leer y presentar la líneas hasta que se
      // reciba un null
      while( (line = inputStream.readLine()) != null )
        System.out.println( line );

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

//------------------------------------------ Final del fichero java1711.java