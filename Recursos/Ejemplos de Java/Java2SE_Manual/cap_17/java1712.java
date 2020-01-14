//
//  java1712.java
//  Copyright (c) 1999,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
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
 * protocolo de echo. En este caso tambi�n se incluye el echo a trav�s
 * de UDP.
 * El programa realiza dos comprobaciones, la primera enviando un mensaje
 * al puerto 7 para probar la comunicaci�n TCP/IP, y la segunda es a
 * trav�s de un datagrama UDP, tambi�n a trav�s de ese puerto.
 * La prte TCP es igua�l al ejemplo anterior, pero el mensaje que se
 * env�a por UDP se convierte a un array de bytes, que estar� contenido
 * en un objeto de tipo DatagramPacket, que adem�s contendr� la
 * direcci�n del servidor, que previamente ha obtenido instanciando un
 * objeto de tipo InetAddress, y el n�mero del puerto de ese servidor.
 * Luego se invoca al m�todo send() del socket, pas�ndole el paquete
 * como par�metro, para que sea enviado.
 * Los datos en el paquete se sobreescriben con "x" para confirmar que se
 * reciben datos del paquete, y que no residuo del mensaje anterior.
 * Luego se invoca el �todo receive() sobre el objeto DatagramSocket,
 * pas�ndole el paquete como par�metro, esto hace que el hilo se bloquee
 * hasta que le llegue el paquete por el mismo puerto por el cual lo ha
 * enviado. Cuando el paquete original es recibido, los datos en este
 * paquete f�sico se extraen y escriben en la parte de datos del
 * objeto que se croporciona como par�metro al m�todo receive().
 * El hilo se desbloquea y el control del programa sigue con la
 * presentaci�n de los datos que contenga el paquete en la pantalla que,
 * como era de esperar, coincide con el que se envi� originalmente al
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
      // puerto est�ndar de echo
      Socket socket = new Socket( servidor,puerto );

      // Conseguimos el canal de entrada
      BufferedReader entrada = new BufferedReader(
        new InputStreamReader( socket.getInputStream() ) );

      // Conseguimos el canal de salida, con liberaci�n autom�tica
      PrintWriter salida = new PrintWriter(
        new OutputStreamWriter( socket.getOutputStream() ),true );

      // Env�a la l�nea de texto del mensaje al servidor
      salida.println( cadTcp );
      // Y recoge la respuesta del servidor, present�ndola en pantalla
      System.out.println( entrada.readLine() );

      // se cierra el socket TCP
      socket.close();


      // Ahora se realiza la prueba con datagramas sobre el mimso
      // puerto del mismo servidor
      // Convertimos el mensaje en un array de bytes
      byte[] mensajeUdp = cadUdp.getBytes();
      // Obtenemos la direcci�n IP del servdor
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
      // Escribimos esta versi�n del mensaje
      System.out.println( new String( paquete.getData() ) );
      // Ahora recibimos el eco en ese mismo paquete, de forma que
      // sobreescriba las "x" que se hab�an colocado
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