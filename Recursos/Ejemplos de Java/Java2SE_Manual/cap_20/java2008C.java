//  java2008C.java
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
//     Creacion: 29-Jun-1999  05:40:21
//     Revision: 09-Feb-2002  21:06:16
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Programa cliente que permite demostrar la serialización de objetos a
 * través de una red TCP/IP. Se conecta con un programa servidor en el otro
 * lado de la red y le envía, serializado, un objeto de tipo java2008T, que
 * constituye un árbol implementado sobre un Vector, al cual el servidor
 * incorpora un nuevo elemento y se lo devuelve al cliente, presentando
 * éste los identificadores de las ramas del objeto en pantalla
 */
import java.io.*;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetAddress;

public class java2008C {
  protected DataInputStream in;
  protected PrintStream out;
  protected Socket cliente;

  public static void main(String[] args) {
    // Fijamos el puerto a utilizar
    int puerto = 8002;
    // Creamos un nuevo cliente
    try {
      java2008C c = new java2008C(
        InetAddress.getLocalHost().getHostAddress(),puerto );
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }

  public java2008C( String servidor,int puerto )
    throws IOException,UnknownHostException {
    // Nos conectamos al servidor a través del puerto especificado
    cliente = new Socket( servidor,puerto );
    System.out.println( "Conexion establecida..." );

    // En primer lugar construimos el árbol
    java2008T raiz = new java2008T( "raiz" );
    raiz.addRama( new java2008T("izqda") );
    raiz.addRama( new java2008T("drcha") );

    // Lo imprimimos para ver lo que aparece
    System.out.println( "Arbol original:  \n" +raiz.toString() );

    try {
      // Creamos el canal de salida hacia el servidor
      ObjectOutput out = new ObjectOutputStream( cliente.getOutputStream() );
      // Ahora enviamos el árbol completo a través de ese canal
      out.writeObject( raiz );
      out.flush();

      // Creamos el canal de lectura desde el servidor
      ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
      // Recogemos el objeto del canal
      java2008T n = (java2008T)in.readObject();

      // Imprimimos el resultado de la lectura del objeto recibido
      // a través de ese canal
      System.out.println("Arbol Leido: \n" + n.toString());
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//----------------------------------------- Final del fichero java2008C.java