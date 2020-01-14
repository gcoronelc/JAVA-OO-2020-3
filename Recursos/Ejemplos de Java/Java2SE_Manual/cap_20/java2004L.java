//  java2004L.java
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
//     Creacion: 26-Jun-1999  22:56:30
//     Revision: 09-Feb-2002  21:04:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Objeto lista que se utiliza para actualizar su contenido con los
 * datos que se piden al objeto Remoto
 */
import java.awt.*;
import java.awt.event.*;

public class java2004L extends List {

  public java2004L() {}

  public java2004L( int elementos ) {
    super( elementos );
    }

  public java2004L( int elementos,boolean modoMultiple ) {
    super( elementos,modoMultiple );
    }

  // Método de actualización de los datos
  public synchronized void setDatos( String[] datos ) {
    // Primero borramos el contenido actual
    removeAll();
    // Y copiamos los elementos que se pasan como parámetro como
    // nuevo contenido de la lista
    for( int i=0; i < datos.length; i++ )
      add( datos[i] );
    }
  }

//----------------------------------------- Final del fichero java2004L.java