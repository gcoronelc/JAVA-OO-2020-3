//
//  java1702.java
//  Copyright (c) 1999,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 03-Jan-1999  07:29:45
//     Revision: 01-Feb-2002  18:49:31
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo trata de mostrar el uso de los cuatro tipos de
 * constructores y de los métodos de la clase URL. Además,
 * en el programa se utiliza la clase URLEncoder para
 * convertir una cadena conteniendo espacios y otra en la
 * que se sigue el formato x-www-form-url
 */
import java.net.*;

class  java1702{
  public static void main( String[] args ) {
    java1702 obj = new java1702();

    try {
      System.out.println(
        "Constructor simple para URL principal" );
      obj.display( new URL( "http://usuarios.tripod.es" ) );

      System.out.println(
        "Constructor de cadena para URL + directorio" );
      obj.display( new URL( "http://usuarios.tripod.es/froufe" ) );

      System.out.println(
        "Constructor con protocolo, host y directorio" );
      obj.display( new URL(
        "http","usuarios.tripod.es","/froufe" ) );

      System.out.println(
        "Constructor com protocolo, host, puerto y directorio" );
      obj.display( new URL(
        "http","usuarios.tripod.es",80,"/froufe" ) );

      System.out.println(
        "Construye una direccion absoluta a partir de la \n"+
        "direccion del Host y una URL relativa" );
      URL baseURL = new URL(
        "http://usuarios.tripod.es/froufe/index.html");
      obj.display( new URL( baseURL,
        "/froufe/introduccion/indice.html" ) );

      System.out.println(
        "Uso de URLEncoder para crear una cadena x-www-form-url" );
      System.out.println( URLEncoder.encode(
        "http://espacio .tilde~.mas+.com","ISO-8859-1" ) );
    } catch( Exception e ) {
      System.out.println( e );
      }
    }

  // Método para poder presentar en la pantalla partes de una
  // dirección URL
  void display( URL url ) {
    System.out.print( url.getProtocol()+" " );
    System.out.print( url.getHost()+" " );
    System.out.print( url.getPort()+" " );
    System.out.print( url.getFile()+" " );
    System.out.println( url.getRef() );

    // Presentamos la dirección completa como una cadena
    System.out.println( url.toString() );
    System.out.println();
    }
  }

//------------------------------------------ Final del fichero java1702.java