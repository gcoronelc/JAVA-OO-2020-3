//
//  java1720.java
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
//     Creacion: 09-Feb-2002  04:26:03
//     Revision: 09-Feb-2002  19:13:54
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa es el cliente Multicast que implementa el ejemplo
 * de recepción de información desde un servidor multicast.
 * Recibe la fecha y hora del servidor continuamente, y la va presentando
 * en la ventana de ejecución del ejemplo.
 */
import java.io.*;
import java.net.*;

public class java1720 {
  static final int puerto = 1200;

  public static void main( String[] args ) throws IOException {
    DatagramPacket paquete;

    // Creamos el socket para el envío Multicast
    MulticastSocket socket = new MulticastSocket( puerto );
    // Obtenemos la dirección que identifica al ordenador
    InetAddress direccion = InetAddress.getByName( args[0] );

    // Nos incorporamos a un grupo Multicast y enviamos
    // información a ese grupo
    socket.joinGroup( direccion );
    byte[] datos = new byte[40];
    // Creamos el paquete que recogerá la información
    paquete = new DatagramPacket( datos,datos.length );

    // Nos quedamos enviando la fecha y hora continuamente
    while( true ) {
      // Quedamos a la espera de recibir información del servidor
      socket.receive( paquete );
      // Reconvertimos la trama de bytes recibidos a una cadena de
      // texto legible que presentamos en pantalla
      String fecha = new String( paquete.getData() );
      System.out.println( "Servidor: "+ paquete.getAddress()
        +" Fecha: "+ fecha );
      }
    }
  }

//------------------------------------------ Final del fichero java1720.java
