//
//  java1434.java
//  Copyright (c) 2001,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 28-Dic-2001  17:34:02
//     Revision: 07-Feb-2002  05:57:18
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase presenta una vista de �rbol de un sistema de ficheros
 * mostrando en su jerarqu�a en tama�o relativo de cada uno de los
 * nodos que la componen, en base a la informaci�n de su volumen
 */
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class java1434 {

  public static void main( String args[] ) {
    // Hay que indicar el directorio donde se encuentra el documento
    // raiz del �rbol de p�ginas que queremos servir
    if( args.length != 1 ) {
      System.err.println( "Uso: java java1434 directorio" );
      System.exit( 1 );
      }

    // Creamos una ventana en una nueva instancia de la clase de
    // ayuda. En el par�metro se pasa el directorio a partir del
    // cual se construir� la jerarqu�a completa
    final JVolumenFrame frame = new JVolumenFrame(
      "Tutorial de Java, Swing",new File(args[0]),
      new FileVolumenNodoHelper() );

    // Creamos un receptor de eventos para controlar las selecciones
    // que se hagan dentro de la jerarqu�a
    final ActionListener raizActionListener = new ActionListener() {
      // Cuandos e selecciona un nuevo nodo raiz, se aplica de nuevo
      // la clase de ayuda y se almacena la direcci�n del nuevo nodo
      public void actionPerformed( ActionEvent evt ) {
        final Object raiz = frame.getRoot();
        final JFileChooser selector = ( (raiz instanceof File) ?
          new JFileChooser((File)raiz) : new JFileChooser() );
        selector.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        final int estado = selector.showOpenDialog( frame );
        final File fichero = selector.getSelectedFile();

        if( (fichero != null) && (estado == JFileChooser.APPROVE_OPTION) )
          frame.setRoot( fichero );
        }
      };

    // A�adimos el receptor de eventos a la ventana
    frame.addRootActionListener( raizActionListener );
    // Fijamos su tama�o y posici�n y la hacemos visible
    frame.setBounds( 300,200,450,500 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1434.java
