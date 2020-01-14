//  java2007.java
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
//     Creacion: 29-Jun-1999  04:43:09
//     Revision: 09-Feb-2002  21:06:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo permite la validaci�n de los objetos que se leen desde
 * fichero
 */
import java.io.*;
import java.util.Date;
import java.util.Vector;
import java.util.Enumeration;

class Arbol implements Serializable,ObjectInputValidation {
  Vector hijos;
  Arbol padre;
  String nombre;

  // Declaramos una variable para se�alar el instante en que el objeto
  // es instanciado y el momento en que es leido del stream
  transient Date instante;
  // Declaramos otr variable, contador, para marcar el objeto cuando se
  // lee del stream
  transient int contador = 0;

  // Constructor de la clase, que implementa un Vector de
  // cinco elementos y fija el instante en que se instancia el objeto
  public Arbol( String s ) {
    hijos = new Vector( 5 );
    nombre = s;
    instante = new Date();
    }

  // M�todo para incorporar elementos al Vector
  public void addRama( Arbol n ) {
    hijos.addElement( n );
    n.padre = this;
    }

  // Este es el m�todo que escribe el objeto en el canal que se le
  // pasa como par�metro
  private void writeObject( ObjectOutputStream canal ) throws IOException {
    try {
      canal.defaultWriteObject();
      // Se escribe el contador
      canal.writeInt( contador );
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }

  // M�todo para leee el objeto del canal
  private void readObject( ObjectInputStream canal ) throws IOException {
    try {
      canal.registerValidation( this,0 );
      canal.defaultReadObject();
      contador = canal.readInt()+1;
      instante = new Date();
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }

  // Comprueba cada uno de los hijos que tiene ese objeto
  public void validateObject() throws InvalidObjectException {
    Enumeration enu = hijos.elements();

    while( enu.hasMoreElements() ) {
      if( ((Arbol)enu.nextElement()).padre != this )
        throw new InvalidObjectException( getClass().getName() );
      }
    }

  // Este es el m�todo que vuelca en contenido del vector
  // a un formato legible en pantalla, imprimiento el contenido
  // de los elementos del Arbol
  public String toString() {
    Enumeration enu = hijos.elements();
    StringBuffer buffer = new StringBuffer( 100 );

    buffer.append( "[" +nombre+ "(Cont:" +contador+ ")(Inst:" +
      instante+ ") : " );
    while( enu.hasMoreElements() ) {
      buffer.append( enu.nextElement().toString() );
      }
    buffer.append( "] " );
    return( buffer.toString() );
    }
  }


public class java2007 {
  public static void main(String[] args) {
    // En primer construimos el �rbol
    Arbol raiz = new Arbol("raiz");
    raiz.addRama(new Arbol("izqda"));
    raiz.addRama(new Arbol("drcha"));

    // Lo imprimimos para ver lo que aparece
    System.out.println( "Arbol Original:  \n" +raiz.toString() );

    try {
      // Ahora grabamos el �rbol completo en un fichero
      FileOutputStream fOut = new FileOutputStream( "prueba.rmi" );
      ObjectOutput out = new ObjectOutputStream( fOut );
      out.writeObject( raiz );
      out.flush();
      out.close();

      // Recuperamos el �rbol del fichero
      FileInputStream fIn = new FileInputStream( "prueba.rmi" );
      ObjectInputStream in = new ObjectInputStream( fIn );
      Arbol n = (Arbol)in.readObject();
      in.close();
      // Imprimimos el resultado de la lectura del fichero
      System.out.println( "Arbol Leido: \n" +n.toString() );
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------ Final del fichero java2007.java