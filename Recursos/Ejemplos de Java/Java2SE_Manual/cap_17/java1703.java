//
//  java1703.java
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
//     Creacion: 03-Jan-1999  07:48:09
//     Revision: 03-Feb-2002  09:47:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la forma de conectarse con una dirección URL y cómo
 * se lee un fichero desde esa dirección a través de un canal de entrada que
 * se establece con ella.
 * La salida del programa es el contenido del fichero que se solicita a la
 * dirección, "prueba01.html"
 */
import java.net.*;
import java.io.*;

class java1703 {
  public static void main( String[] args ) {
    String cadena;

    try {
      // Creamos un objeto de tipo URL
      URL url = new URL(
         "http://usuarios.tripod.es/froufe/clases/prueba01.html" );

      // Abrimos una conexión hacia esa URL, que devolverá un canal de
      // entrada por el cual se podrá leer todo lo que llegue
      BufferedReader paginaHtml =
        new BufferedReader( new InputStreamReader(
          url.openStream() ) );

      // Leemos y presentamos el contenido del fichero en pantalla
      // línea a línea
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

//------------------------------------------ Final del fichero java1703.java