//
//  Enviar.java
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
//     Creacion: 07-Dic-2001  18:46:43
//     Revision: 09-Feb-2002  13:55:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase es la encargada de ller lo que va tecleando el usuario y
 * enviarlo al servidor. Está creada por el cliente en un thread
 * aparte para que se puedan enviar y recibir mensajes al mismo tiempo
 */
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.util.*;

public class Enviar extends Thread {
  // Canal de salida hacia el socket
  protected ObjectOutputStream sout;
  // Campo de texto de entrada del mensajes a enviar
  protected TextField txtMensaje;
  // Campo de texto de entrada del identificador de usuario
  protected TextField txtUsuario;
  protected boolean enEjecucion = false;
  protected ClienteChat apCliente;
  protected long idLinea = 0;

  public Enviar( Socket s,TextField txtMensaje,
    TextField txtUsuario,ClienteChat ap ) {
    this.txtMensaje = txtMensaje;
    this.txtUsuario = txtUsuario;
    this.apCliente = ap;
    try {
      sout = new ObjectOutputStream( s.getOutputStream() );
      System.out.println( "Tarea Enviar lista..." );
    } catch( IOException e ) {
      // Si no podemos crear esta tarea, no podemos arrancar el cliente
      // así que lo cerramos todo
      e.printStackTrace();
      enEjecucion = false;
      apCliente.eliminarTareas();
      return;
      }
    this.start();
    }

  public void run() {
    enEjecucion = true;
    if( isInterrupted() ) {
      enEjecucion = false;
      this.finalize();
      }
    }

  protected void finalize() {
    try {
      // Cerramos el canal de salida
      sout.close();
      System.out.println( "Tarea Enviar cerrada..." );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }

  // Enviamos el mensaje de texto al servidor, junto con el resto
  // de la información del cliente, como el grupo de usuarios...
  public void enviaMensaje() {
    String linea = txtMensaje.getText();
    // Resetamos la zona de texto
    txtMensaje.setText( "" );

    linea = linea.trim();
    Set setUsuarios = (HashSet)apCliente.getClientes();
    String[] arrayUsuarios = apCliente.getArrayClientes();
    try {
      if( (arrayUsuarios != null) && (arrayUsuarios.length > 0) ) {
        // De nuevo creamos el segundo array para evitar los
        // problemas de la serialización
        String[] arrayUsuarios2 = new String[arrayUsuarios.length+1];
        for( int i=0; i < arrayUsuarios.length; i++ ) {
          arrayUsuarios2[i] = arrayUsuarios[i];
          }
        arrayUsuarios2[arrayUsuarios.length] = apCliente.getNombreCliente();
        // Este array es el que serializamos
        sout.writeObject( new ObjSerie(linea,arrayUsuarios2) );
        }
      else {
        sout.writeObject( new ObjSerie(linea) );
        }
      sout.flush();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    idLinea++;
    }

  // Se envía una petición de login al servidor para que nos permita
  // la incorporación a la charla
  public void login() {
    String nombreUsuario = txtUsuario.getText();
    nombreUsuario = nombreUsuario.trim();

    try {
      // Se manda la petición al servidor
      sout.writeObject( new ObjSerie(true,nombreUsuario) );
      sout.flush();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    idLinea++;
    }

  // Envía una petición de desconexión al servidor
  public void logoff() {
    try {
      // Se manda el mensaje al servidor
      sout.writeObject( new ObjSerie("ADIOS",true) );
      sout.flush();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    // Se matan todas las tareas del cliente
    apCliente.eliminarTareas();
    }
  }

//-------------------------------------------- Final del fichero Enviar.java
