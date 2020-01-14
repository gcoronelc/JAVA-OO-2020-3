//
//  java1717.java
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
//     Creacion: 10-Feb-2002  06:05:33
//     Revision: 10-Feb-2002  21:10:16
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el cliente de Eco seguro, que enviará todas las líneas de
 * texto que se introduzcan en la consola en donde se haya lanzado.
 * No admitirá nada que no venga validado con el certificado que configura
 * la sesión como segura.
 * Es necesario lanzar este servidor antes que el cliente, y el comando
 * a utilizar es el siguiente:
 *
 * java -Djavax.net.ssl.trustStore=TrustSSL \
 *   -Djavax.net.ssl.trustStorePassword=cualquiera java1717
 *
 * teniendo el archivo "claveSSL.crt" en el mismo directorio que los
 * archivos .class de cliente y servidor.
 * Todo lo que se teclee a partir de ese momento en la consola en que
 * se haya lanzado el cliente; al pulsar Retorno se enviará la línea
 * al servidor y aparecerá en la consola en que se ha lanzado el
 * servidor.
 * En la cabecera del archivo fuente java1716.java se indica cómo
 * generar el fichero de certificado "claseSSL.crt".
 */

import java.io.*;
import javax.net.ssl.*;

public class java1717 {
  public static void main( String[] args ) throws IOException {
    // Obtenemos el objeto de tipo Factory para crear sockets SSL
    SSLSocketFactory fact = (SSLSocketFactory)SSLSocketFactory.getDefault();
    // Utilizamos el objeto para crear un socket seguro
    SSLSocket socketSsl = (SSLSocket)fact.createSocket( "localhost",9999 );

    // Consola desde la que leemos la entrada del usuario
    BufferedReader entrada = new BufferedReader(
      new InputStreamReader(System.in));
    // Canal de comunicación con el servidor de eco
    BufferedWriter salida = new BufferedWriter(
      new OutputStreamWriter(socketSsl.getOutputStream()));

    String linea = null;
    System.out.println( "Listo..." );
    // Vamos enviando las líneas al servidor
    while( (linea = entrada.readLine()) != null ) {
      salida.write( linea+'\n' );
      salida.flush();
      }
    }
  }

//------------------------------------------ Final del fichero java1717.java
