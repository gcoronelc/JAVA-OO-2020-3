//
//  java1903.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 05-Dec-1998  14:34:12
//     Revision: 09-Feb-2002  19:28:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la parte cliente de la aplicación, que permite introducir una
 * sentencia SQL en el campo de texto, que será enviada al servidor a través
 * del socket, y por este mismo camino recibirá la respuesta del servidor y
 * la presentará en una lista.
 * Este applet asume que ya está instalado el puente JDBC-ODBC en el
 * servidor y que hay una base de datos llamada "Tutorial". Estos datos
 * pueden cambiarlos el lector para que se adecúen a los que disponga.
 * La ejecución del cliente no necesita ningún software especial instalado.
 */
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class java1903 extends Applet {
  static final int puerto = 6700;
  String cadConsulta = "No hay consulta todavia";
  String cadResultado = "No hay resultados";
  Button boton;
  TextArea texto;
  List lista;

  public void init() {
    setLayout( new GridLayout( 5,1 ) );
    texto = new TextArea( 20,40 );
    lista = new List();
    boton = new Button( "Ejecutar Consulta" );
    boton.addActionListener( new MiActionListener() );

    add( new Label( "Escribir la consulta aqui..." ) );
    add( texto );
    add( boton );
    add( new Label( "y examinar los resultados aqui" ) );
    add( lista );

    resize( 600,300 );
  }

  void abreSocket() {
    Socket s = null;
    try {
      s = new Socket( getCodeBase().getHost(),puerto );
      BufferedReader sinstream =
      new BufferedReader(new InputStreamReader(s.getInputStream()));
      PrintStream soutstream = new PrintStream( s.getOutputStream() );

      soutstream.println( texto.getText() );
      lista.removeAll();
      cadResultado = sinstream.readLine();
      while( cadResultado != null ) {
        lista.add( cadResultado );
        cadResultado = sinstream.readLine();
        }
    } catch( IOException e ) {
      System.err.println( e );
    }    finally {
      try {
        if( s != null )
          s.close();
      } catch( IOException e ) {
        }
      }
    }


  class MiActionListener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      abreSocket();
      }
    }
  }

//------------------------------------------ Final del fichero java1903.java