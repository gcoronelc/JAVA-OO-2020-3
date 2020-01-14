//
//  Recibir.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 07-Dic-2001  18:51:08
//     Revision: 09-Feb-2002  13:55:25
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase permite al Cliente crear objetos que leen los mensajes
 * del Servidor y los pasan al cliente para que los presente en la
 * ventana.
 * Se ejecuta en un thread separado del general para permitir que se
 * puedan enviar y recibir mensajes simutáneamente.
 */
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.util.*;

public class Recibir extends Thread {
  // Area de texto para presentar los mensajes de la charla
  protected TextArea zonaMensajes;
  // Mensaje enviado por el servidor
  protected String linea = null;
  protected ObjSerie objSerie = null;
  protected boolean enEjecucion = false;
  protected ClienteChat apCliente;
  // Canal de entrada de comunicación con el servidor
  protected ObjectInputStream entrada;
  // Lista de usuarios enviada por el servidor
  protected Set usuarios;
  // Indica si el servidor ha aceptado la incorporación
  protected boolean loginOk = false;

  public Recibir( Socket s,TextArea zonaMensajes,ClienteChat apCliente ) {
    this.zonaMensajes = zonaMensajes;
    this.apCliente = apCliente;
    try {
      entrada = new ObjectInputStream(
        new BufferedInputStream(s.getInputStream()) );
    } catch( IOException e ) {
      // Si no podemos crear esta tarea, no podemos arrancar el cliente
      // así que lo cerramos todo
      enEjecucion = false;
      apCliente.eliminarTareas();
      return;
      }
    this.start();
    }

  // Este es el método de ejecución de la tarea, que se queda
  // esperando a que el servidor diga algo,
  public void run() {
    System.out.println( "Tarea Recibir lista..." );
    enEjecucion = true;

    while( true ) {
      try {
        objSerie = (ObjSerie)entrada.readObject();
        linea = objSerie.getMensaje();
        usuarios = objSerie.getUsuarios();
        loginOk = objSerie.loginOK();

        if( loginOk ) {
          apCliente.setLogin( loginOk );
          }

        if( usuarios != null ) {
          apCliente.setClientes( usuarios );
          }
      } catch( IOException e ) {
        enEjecucion = false;
        apCliente.eliminarTareas();
        return;
      } catch( ClassNotFoundException e ) {
        e.printStackTrace();
        }

      // Si se nos indica la desconexión, cerramos la comunicación
      // con el servidor
      if( objSerie.Salida() ) {
        System.out.println( "Tarea Recibir cerrada..." );
        enEjecucion = false;
        apCliente.eliminarTareas();
        return;
        }
      // Se actualizan los mensajes en la ventana
      apCliente.actualizaZonaMensajes( linea+"\n" );
      }
    }
  }

//------------------------------------------- Final del fichero Recibir.java
