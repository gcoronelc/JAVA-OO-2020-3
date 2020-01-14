//
//  java1701.java
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
//     Creacion: 02-Jan-1999  10:09:12
//     Revision: 01-Feb-2002  18:47:23
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se tara de ilustrar la utilización de varios de los
 * métodos de la clase InetAddress.
 * Para que el programa se ejecute correctamente y no aparezca una excepción
 * del tipo "UnknownHostException", hay que estar conectados
 * convenientemente. En caso de conectarse a un proveedor de Internet, la
 * asignación de direcciones es automática por parte del ISP, con lo cual
 * se va a obtener una dirección diferente en cada conexión.
 */
import java.net.*;

class java1701 {
  public static void main( String[] args ) {
    try {
      System.out.println(
        "-> Direccion IP de una URL, por nombre" );
      InetAddress address =
        InetAddress.getByName( "usuarios.tripod.es" );
      System.out.println( address );

      // Extrae la dirección IP a partir de la cadena que se
      // encuentra a la derecha de la barra /, luego proporciona
      // esta dirección IP como argumento de llamada al método
      // getByName()
      System.out.println(
        "-> Nombre a partir de la direccion" );
      int temp = address.toString().indexOf( '/' );
      address = InetAddress.getByName(
        address.toString().substring(temp+1) );
      System.out.println( address );

      System.out.println(
        "-> Direccion IP actual de LocalHost" );
      address = InetAddress.getLocalHost();
      System.out.println( address );

      System.out.println(
        "-> Nombre de LocalHost a partir de la direccion" );
      temp = address.toString().indexOf( '/' );
      address = InetAddress.getByName(
        address.toString().substring(temp+1) );
      System.out.println( address );

      System.out.println(
        "-> Nombre actual de LocalHost" );
      System.out.println( address.getHostName() );

      System.out.println(
        "-> Direccion IP actual de LocalHost" );
      // Coge la dirección IP como un array de bytes
      byte[] bytes = address.getAddress();
      // Convierte los bytes de la dirección IP a valores sin
      // signo y los presenta separados por espacios
      for( int cnt=0; cnt < bytes.length; cnt++ ) {
        int uByte = bytes[cnt] < 0 ? bytes[cnt]+256 : bytes[cnt];
        System.out.print( uByte+" " );
      }
      System.out.println();
    } catch( UnknownHostException e ) {
      System.out.println( e );
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
      }
    }
  }

//------------------------------------------ Final del fichero java1701.java