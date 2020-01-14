//
//  java722.java
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
//     Creacion: 16-Dic-2001  06:22:34
//     Revision: 03-Feb-2002  06:32:36
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de la clase Preferences para un
 * usuario determinado.
 * El contenido de los valores se almacena en un fichero XML que contiene
 * las claves y valores que se asignan. Este fichero se genera en el
 * mismo directorio en donde se ejecute esta aplicación
 */
import java.io.*;
import java.util.prefs.*;

public class java722 {
  public static void main( String args[] ) {
    String interprete[] = {
      "John Hartford",
      "Linda Ronstadt",
      "Flying Burrito Bros",
      "The Mavericks",
      "Shania Twain",
      "Dolly Parton",
      "Emmylou Harris"
      };
    String cancion[] = {
      "No End Of Love",
      "Blue Bayou",
      "Hot Burritos",
      "Mr Jones",
      "Come On Over",
      "Coat Of Many Colours",
      "Bluebird",
      };

    // Receptor de eventos que informa de los cambios habidos en los
    // nodos que forman la colección de Preferencias.
    // Solamente se tratan los eventos que incorporan y eliminan
    // hijos a la colección, como ejemplo
    NodeChangeListener nCL = new NodeChangeListener() {
        public void childAdded( NodeChangeEvent evt ) {
          Preferences padre = evt.getParent();
          Preferences hijo = evt.getChild();
          System.out.println( padre.name() +" incorpora al hijo "+
            hijo.name() );
          }
        public void childRemoved( NodeChangeEvent evt ) {
          Preferences padre = evt.getParent();
          Preferences hijo  = evt.getChild();
          System.out.println( padre.name() + " pierde al hijo " +
            hijo.name() );
          }
        };

    // Receptor de eventos que informa de cambios en los valores de
    // los elementos que constituyen la colección de preferencias
    PreferenceChangeListener pCL = new PreferenceChangeListener() {
        public void preferenceChange( PreferenceChangeEvent evt ) {
          String clave = evt.getKey();
          String valor = evt.getNewValue();
          Preferences nodo = evt.getNode();
          System.out.println( nodo.name() +" tiene el valor "+
            valor +" para la clave "+ clave );
          }
        };

    // Busca el nodo raíz de las preferencias
    Preferences prefs =
      Preferences.userRoot().node( "/Musica/Country" );

    // Añade los receptores de eventos para ser notificado de los
    // cambios que se produzcan
    prefs.addNodeChangeListener( nCL );
    prefs.addPreferenceChangeListener( pCL );

    // Guarda todas las parejas clave-valor que se han definido al
    // principio
    for( int i=0,n=interprete.length; i < n; i++ ) {
      prefs.put( interprete[i],cancion[i] );
      }

    // Presenta en pantalla todas las entradas
    try {
      String claves[] = prefs.keys();
      for( int i=0,n=claves.length; i < n; i++ )
        System.out.println( claves[i] +": "+
          prefs.get( claves[i],"Desconocido") );
    } catch( BackingStoreException e ) {
      e.printStackTrace();
      }

    // Creamos un hijo
    Preferences hijo = Preferences.userRoot().node(
      "/Musica/Country/bluegrass" );

    // Lo guardamos en un fichero XML
    try {
      FileOutputStream fos = new FileOutputStream( "prefs.xml" );
      prefs.exportNode( fos );
    } catch( Exception e ) {
      e.printStackTrace();
      }

    // Lo eliminamos todo
    try {
      prefs.removeNode();
    } catch( BackingStoreException e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------- Final del fichero java722.java