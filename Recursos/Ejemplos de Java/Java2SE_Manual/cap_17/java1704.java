//
//  java1704.java
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
//     Creacion: 03-Jan-1999  08:02:32
//     Revision: 03-Feb-2002  09:51:25
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra como se conecta a una dirección y se
 * crea un objeto URLConection. Se utiliza un objeto URL para
 * obtener y presentar la dirección, la última fecha en que
 * se ha modificado y el tipo de contenido. También se usa
 * el objeto URLConnection para obtener un canal de entrada,
 * y luego emplearlo para leer y presentar el fichero.
 * Si no estás conectado se lanzará una excepción de tipo
 * UnknownHostException.
 */
import java.net.*;
import java.io.*;
import java.util.*;

class java1704 {
  public static void main( String[] args ) {
    String cadena;

    try {
      // Creamos un objeto de tipo URL
      URL url = new URL(
         "http://usuarios.tripod.es/froufe/clases/prueba01.html" );
      // Se abre una conexión hacia la dirección recogiendola en un
      // objeto de tipo URLConnection
      URLConnection conexion = url.openConnection();

      // Se utiliza la conexión para leer y presentar la dirección
      System.out.println( conexion.getURL() );

      // Se utiliza la conexión para leer y presentar la fecha de
      // última modificación
      Date fecha = new Date( conexion.getLastModified() );
      System.out.println( fecha );

      // Se utiliza la conexión para leer y presentar el tipo de
      // contenido
      System.out.println( conexion.getContentType() );

      // Se utiliza la conexión para conesguir un objeto de tipo
      // InputStream, que luego se emplea para instanciar un
      // objeto DataInputStream
      BufferedReader paginaHtml =
        new BufferedReader( new InputStreamReader(url.openStream()) );

      // Se utiliza el objeto DataInputStream para leer y
      // presentar el fichero línea a línea
      while( (cadena = paginaHtml.readLine()) != null ) {
        System.out.println( cadena );
      }
    } catch( UnknownHostException e ) {
      e.printStackTrace();
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
    } catch( MalformedURLException e ) {
      e.printStackTrace();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------ Final del fichero java1704.java