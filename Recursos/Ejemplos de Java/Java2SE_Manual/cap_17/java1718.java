//
//  java1718.java
//  Copyright (c) 2002 Agustin Froufe
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
//     Creacion: 09-Feb-2002  08:02:28
//     Revision: 09-Feb-2002  14:23:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa recupera una página a partir desde un servidor
 * seguro, presentando información del certificado que presenta
 * el servidor Web para confirmar la autorización de descarga.
 * El lector puede modificar el servidor y página que se solicita,
 * e incluso puede modificar el programa para que solicite al
 * usuario servidor y archivo a descargar.
 * El archivo debe indicarse con su camino completo dentro del
 * servidor Web seguro
 */
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.cert.*;

public class java1718 {
  static String servidor = "digitalid.verisign.com";
  static String archivo = "/index.html";

  public static void main( String[] args ) throws IOException {
    // Obtenemos el objeto de tipo Factory para crear sockets SSL
    SSLSocketFactory fact =
      (SSLSocketFactory)SSLSocketFactory.getDefault();

    // Utilizamos el objeto para crear un socket seguro conectado al
    // puerto HTTPS del servidor web
    SSLSocket socketSsl=(SSLSocket)fact.createSocket( servidor,443 );

  // Recogemos el certificado que presenta el servidor Web
  SSLSession sesion = socketSsl.getSession();
  X509Certificate cert;
  try {
    cert = (X509Certificate)sesion.getPeerCertificates()[0];
  } catch( SSLPeerUnverifiedException e ) {
    // Si no hay certificado o no es válido
    System.err.println(sesion.getPeerHost() +
      " no presenta un certificado válido." );
    return;
    }

  // Presentamos la información del certificado
  System.out.println( sesion.getPeerHost() +
    " presenta un certificado perteneciente a:" );
  System.out.println( "  ["+ cert.getSubjectDN().getName() +"]" );
  System.out.println(
    "El certificado contiene una firma validada por:" );
  System.out.println( "  ["+ cert.getIssuerDN().getName() +"]" );
  // Preguntamos al usuario si confía en el certificado
  System.out.print( "¿Confía en este certificado (s/n)? " );
  System.out.flush();
  BufferedReader consola =
    new BufferedReader( new InputStreamReader(System.in) );
  // Si no pulsa la "s" es que quiere abortar y no acepta el
  // certificado que envía el servidor Web
  if( Character.toLowerCase( consola.readLine().charAt(0)) != 's' )
    return;

  // Utilizamos el socket seguro como si se tratase de cualquier
  // socket, enviando la petición GET habitual sobre el socket SSL
  PrintWriter salida = new PrintWriter( socketSsl.getOutputStream() );
  salida.print( "GET "+ archivo +" HTTP/1.0\r\n\r\n" );
  salida.flush();

  // Leemos la respuesta que envía el servidor y la volcamos
  // sobre la consola
  BufferedReader entrada = new BufferedReader(
    new InputStreamReader(socketSsl.getInputStream()) );
  String linea;
  while( (linea = entrada.readLine()) != null )
    System.out.println( linea );

  // Para acabar, cerramos el socket
  socketSsl.close();
  }
}

//------------------------------------------ Final del fichero java1718.java
