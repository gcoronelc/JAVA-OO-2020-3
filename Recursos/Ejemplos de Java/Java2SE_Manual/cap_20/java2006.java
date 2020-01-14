//  2006.java
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
//     Creacion: 29-Jun-1999  04:23:34
//     Revision: 09-Feb-2002  21:05:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa declara una clase Arbol, que implementa un vector y es una
 * clase serializable, se puede almacenar en un stream.
 * En la ejecución se crea un objetod e este tipo que se graba en fichero
 * y se imprime en pantalla. Seguidamente se recupera el objeto del fichero
 * y se vuelve a imprimir el contenido del objeto recuperado del fichero
 */
import java.io.*;
import java.util.Date;
import java.util.Vector;
import java.util.Enumeration;

class Arbol implements Serializable {
  Vector hijos;
  Arbol padre;
  String nombre;

  // Constructor de la clase, que implementa un Vector de
  // cinco elementos
  public Arbol( String s ) {
    hijos = new Vector( 5 );
    nombre = s;
    }

  // Método para incorporar elementos al Vector
  public void addRama( Arbol n ) {
    hijos.addElement( n );
    n.padre = this;
    }

  // Este es el método que vuelca en contenido del vector
  // a un formato legible en pantalla, imprimiento el contenido
  // de los elementos del Arbol
  public String toString() {
    Enumeration enu = hijos.elements();
    StringBuffer buffer = new StringBuffer( 100 );

    buffer.append( "[" +nombre+ ": " );
    while( enu.hasMoreElements() ) {
      buffer.append( enu.nextElement().toString() );
      }
    buffer.append("] ");
    return( buffer.toString() );
    }
  }


public class java2006 {
  public static void main(String[] args) {
    // En primer lugar construimos el árbol
    Arbol raiz = new Arbol("raiz" );
    raiz.addRama( new Arbol("izqda") );
    raiz.addRama( new Arbol("drcha") );

    // Lo imprimimos para ver lo que aparece
    System.out.println( "Arbol Original:  \n" +raiz.toString() );

    try {
      // Ahora grabamos el árbol completo en un fichero
      FileOutputStream fOut = new FileOutputStream( "prueba.rmi" );
      ObjectOutput out = new ObjectOutputStream( fOut );
      out.writeObject( raiz );
      out.flush();
      out.close();

      // Recuperamos el árbol del fichero
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

//------------------------------------------ Final del fichero java2006.java