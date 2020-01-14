//
//  java1712.java
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
//     Revision: 03-Feb-2002  09:57:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es una mejora del ejemplo java1705, que comprobaba el
 * protocolo de echo. En este caso también se incluye el echo a través
 * de UDP.
 * El programa realiza dos comprobaciones, la primera enviando un mensaje
 * al puerto 7 para probar la comunicación TCP/IP, y la segunda es a
 * través de un datagrama UDP, también a través de ese puerto.
 * La prte TCP es iguañl al ejemplo anterior, pero el mensaje que se
 * envía por UDP se convierte a un array de bytes, que estará contenido
 * en un objeto de tipo DatagramPacket, que además contendrá la
 * dirección del servidor, que previamente ha obtenido instanciando un
 * objeto de tipo InetAddress, y el número del puerto de ese servidor.
 * Luego se invoca al método send() del socket, pasándole el paquete
 * como parámetro, para que sea enviado.
 * Los datos en el paquete se sobreescriben con "x" para confirmar que se
 * reciben datos del paquete, y que no residuo del mensaje anterior.
 * Luego se invoca el étodo receive() sobre el objeto DatagramSocket,
 * pasándole el paquete como parámetro, esto hace que el hilo se bloquee
 * hasta que le llegue el paquete por el mismo puerto por el cual lo ha
 * enviado. Cuando el paquete original es recibido, los datos en este
 * paquete físico se extraen y escriben en la parte de datos del
 * objeto que se croporciona como parámetro al método receive().
 * El hilo se desbloquea y el control del programa sigue con la
 * presentación de los datos que contenga el paquete en la pantalla que,
 * como era de esperar, coincide con el que se envió originalmente al
 * puerto de eco del servidor.
 */
import java.net.*;
import java.io.*;
import java.util.*;

class java1712 {
  public static void main( String[] args ) {
    String servidor = "www.fie.us.es";         // servidor
    int puerto = 7;                            // puerto eco
    String cadTcp = "Prueba de Eco TCP";
    String cadUdp = "Prueba de Eco UDP";

    // Primero realizamos el test de Eco con TCP/IP
    try {
      // Abrimos un socket conectado al servidor y al
      // puerto estándar de echo
      Socket socket = new Socket( servidor,puerto );

      // Conseguimos el canal de entrada
      BufferedReader entrada = new BufferedReader(
        new InputStreamReader( socket.getInputStream() ) );

      // Conseguimos el canal de salida, con liberación automática
      PrintWriter salida = new PrintWriter(
        new OutputStreamWriter( socket.getOutputStream() ),true );

      // Envía la línea de texto del mensaje al servidor
      salida.println( cadTcp );
      // Y recoge la respuesta del servidor, presentándola en pantalla
      System.out.println( entrada.readLine() );

      // se cierra el socket TCP
      socket.close();


      // Ahora se realiza la prueba con datagramas sobre el mimso
      // puerto del mismo servidor
      // Convertimos el mensaje en un array de bytes
      byte[] mensajeUdp = cadUdp.getBytes();
      // Obtenemos la dirección IP del servdor
      InetAddress dirIp = InetAddress.getByName( servidor );
      // Creamos el paquete que se va a enviar al pueto
      DatagramPacket paquete =
        new DatagramPacket( mensajeUdp,mensajeUdp.length,dirIp,puerto );
      // Abrimos un socket datagrama para enviarle el mensaje
      DatagramSocket socketDgrama = new DatagramSocket();
      // Y lo enviamos
      socketDgrama.send( paquete );

      // Sobreescrimos el mensaje en el paquete para confirmar que el
      // eco es realmente lo que llega
      byte[] arrayDatos = paquete.getData();
      for( int cnt=0; cnt < paquete.getLength(); cnt++ )
        arrayDatos[cnt] = (byte)'x';
      // Escribimos esta versión del mensaje
      System.out.println( new String( paquete.getData() ) );
      // Ahora recibimos el eco en ese mismo paquete, de forma que
      // sobreescriba las "x" que se habían colocado
      socketDgrama.receive( paquete );
      // Presentamos en pantalla el eco
      System.out.println( new String( paquete.getData() ) );

      // Se cierra el socket
      socketDgrama.close();
    } catch( UnknownHostException e ) {
      e.printStackTrace();
      System.out.println(
        "Debes estar conectado para que esto funcione bien." );
    } catch( SocketException e ) {
      e.printStackTrace();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------ Final del fichero java1712.java