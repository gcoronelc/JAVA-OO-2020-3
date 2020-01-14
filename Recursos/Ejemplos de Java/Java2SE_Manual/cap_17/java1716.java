//
//  java1716.java
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
//     Creacion: 10-Feb-2002  06:00:07
//     Revision: 10-Feb-2002  22:19:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el servidor de Eco seguro, que presenta la información que
 * envían los cientes que se conecten. No admitirá nada que no venga
 * validado con el certificado que configura la sesión como segura.
 * Es necesario lanzar este servidor antes que el cliente, y el comando
 * a utilizar es el siguiente:
 *
 * java -Djavax.net.ssl.keyStore=StoreSSL \
 *   -Djavax.net.ssl.keyStorePassword=cualquiera java1716
 *
 * teniendo el archivo "claveSSL.crt" en el mismo directorio que los
 * archivos .class de servidor y cliente.
 *
 * El archivo de certificado "claveSSL.crt" utilizado en el ejemplo
 * para validar la comunicación segura entre este servidor y los
 * clientes se genera utilizando la herramienta "keytool" utilizando
 * los comandos siguientes:
 *
 * >keytool -genkey -alias claveSsl -keyalg RSA -keystore StoreSSL
 * >keytool -export -alias claveSsl -keystore StoreSSL -rfc -file claveSSL.crt
 * >keytool -import -alias claveTSsl -file claveSSL.crt -keystore TrustSSL
 *
 * proporcionando la información que solicita la herramienta, y usando
 * como contraseña el literal "cualquiera".
 */
import java.io.*;
import javax.net.ssl.*;

public class java1716 {
  public static void main( String[] args ) throws IOException {
    // Obtenemos el objeto de tipo Factory para crear sockets SSL
    SSLServerSocketFactory fact =
      (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
    // Utilizamos el objeto para crear un socket servidor seguro
    SSLServerSocket socketServidorSsl =
      (SSLServerSocket)fact.createServerSocket( 9999 );
    SSLSocket socketSsl = (SSLSocket)socketServidorSsl.accept();

    // Creamos un canal de entrada sobre el socket seguro que
    // hemos abierto
    BufferedReader entrada = new BufferedReader(
      new InputStreamReader(socketSsl.getInputStream()));

    String linea = null;
    System.out.println( "Esperando..." );
    // Presentamos todas las líneas que vayan llegan entrando en
    // el canal a través del socket
    while( (linea = entrada.readLine()) != null ) {
      System.out.println( linea );
      System.out.flush();
      }
    }
  }

//------------------------------------------ Final del fichero java1716.java
