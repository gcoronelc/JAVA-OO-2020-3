//  java2008T.java
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
//     Creacion: 29-Jun-1999  04:59:46
//     Revision: 09-Feb-2002  21:06:36
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Clase para implementar el objeto a serializar. Consta de un vector al
 * que se permite añadir elementos y dispone de un método toString() que es
 * capaz de volcar el contenido del vector en formato legible
 */
import java.io.*;
import java.util.Date;
import java.util.Vector;
import java.util.Enumeration;

class java2008T implements Serializable {
  Vector hijos;
  java2008T padre;
  String nombre;

  // Constructor de la clase, que implementa un Vector de
  // cinco elementos
  public java2008T( String s ) {
    hijos = new Vector( 5 );
    nombre = s;
    }

  // Método para incorporar elementos al Vector
  public void addRama( java2008T n ) {
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

//--------------------------------------------- Final del fichero 2008T.java