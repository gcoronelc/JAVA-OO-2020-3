//
//  java1719.java
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
//     Creacion: 09-Feb-2002  04:23:25
//     Revision: 09-Feb-2002  19:12:09
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa es el servidor Multicast que implementa el ejemplo
 * de envío de información a un grupo de clientes.
 * Envía la fecha y hora del servidor continuamente, hasta que se
 * corte la ejecución del servidor.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class java1719 {
  static final int puerto = 1200;

  public static void main( String[] args ) throws Exception {
    DatagramPacket paquete;

    // Creamos el socket para el envío Multicast
    MulticastSocket socket = new MulticastSocket();
    // Obtenemos la dirección que identifica al ordenador
    InetAddress direccion = InetAddress.getByName( args[0] );
    System.out.println( "Dirección: "+direccion );

    // Nos incorporamos a un grupo Multicast y enviamos
    // información a ese grupo
    socket.joinGroup( direccion );
    byte[] datos = null;

    // Nos quedamos enviando la fecha y hora continuamente
    while( true ) {
      // Mandamos la información cada segundo
      Thread.sleep( 1000 );
      // Convertimos la fecha en un array de bytes para el envío
      String fecha = new Date().toString();
      datos = fecha.getBytes();
      paquete = new DatagramPacket(
        datos,datos.length,direccion,puerto );
      // Enviamos el paquete con la fecha y hora
      socket.send( paquete );
      System.out.println( "Enviados... "+datos.length+" bytes" );
      }
    }
  }

//------------------------------------------ Final del fichero java1719.java
