//  java2008S.java
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
//     Creacion: 29-Jun-1999  05:23:32
//     Revision: 09-Feb-2002  21:06:26
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class java2008S {
  protected ServerSocket servidor;

  public static void main( String[] args ) {
    // Se fija el puerto a utiliza en la comunicación
    int puerto = 8002;
    // Se crea una instancia del servidor y se queda a la espera de que
    // se produzca una conexión
    java2008S s = new java2008S( puerto );
    s.accept();
    }

  public java2008S( int puerto ) {
    // Se crea el servidor escuchando en el puerto que se indique
    try {
      servidor = new ServerSocket( puerto,15 );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }

  public void accept() {
    ObjectOutput out = null;
    ObjectInput in = null;
    Socket socket = null;
    System.out.println( "Servidor esperando..." );

    while( true ) {
      try {
        socket = servidor.accept();
        // Lega una conexión desde el cliente
        System.out.println( "Conexion establecida..." );
        // Se crea el canal de salida
        out = new ObjectOutputStream( socket.getOutputStream() );
        // Se crea el canal de entrada
        in = new ObjectInputStream( socket.getInputStream() );

        // Se lee el árbol del canal de entrada, se le añade una rama
        // más y se devuelve
        java2008T n = (java2008T)in.readObject();
        n.addRama( new java2008T( "Rama Servidor" ) );
        out.writeObject( n );
        out.flush();
      } catch( IOException e ) {
        e.printStackTrace();
        // Concluimos el servidor
        System.exit( 1 );
      } catch( Exception e ) {
        e.printStackTrace();
        }
      }
    }
  }

//----------------------------------------- Final del fichero java2008S.java