//  java2004C.java
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
//     Creacion: 26-Jun-1999  23:08:32
//     Revision: 09-Feb-2002  21:04:24
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ventana que presenta una lista inicializaza con cadenas de texto
 * correspondientes a la implementacion del objeto Lista local, y un
 * boton, que al pulsarlo actualizara el contenido de la lista local con
 * los datos que solicita al objeto remoto
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;

class java2004C extends JFrame implements ActionListener {
  public static final String[] DATOS = {
    "Primero","Segundo","Tercero","Cuarto","Quinto"
    };

  // Elementos gráficos de la ventana
  JButton boton;
  JPanel panel;
  // Lista local y lista remota
  java2004L lista;
  java2004I listaRemota;

  java2004C() {
    // Creamos la lista local y la inicializamos
    lista = new java2004L( 4 );
    lista.setDatos( DATOS );

    // Botón de actualización de la lista
    boton = new JButton( "Actualizar" );
    boton.addActionListener( this );

    // Contenedor de todos los componentes
    panel = new JPanel();
    panel.setLayout( new BorderLayout() );
    panel.setBackground( Color.white );
    getContentPane().add( panel );
    // Se incorporan los componentes al panel
    panel.add( "Center",lista );
    panel.add( "South",boton );
    }

  // Este es el método en el que se realizan las acciones que se
  // originan al pulsar el botón de actualización
  public void actionPerformed( ActionEvent evt ) {
    // Obtenemos el origen del evento
    Object origen = evt.getSource();

    if( origen == boton ) {
      // Pedimos los datos de la lista al objeto remoto y actualizamos
      // la lista local
      String direccion = "rmi://localhost/";
      try {
        listaRemota = (java2004I)Naming.lookup( direccion+"ListaRemota" );
        lista.setDatos( listaRemota.getDatos() );
      } catch( Exception e ) {
        e.printStackTrace();
        }
      }
    }

  // Método principal del cliente
  public static void main( String[] args ) {
    java2004C frame = new java2004C();
    frame.setTitle( "Tutorial de Java, RMI" );

    WindowListener wl = new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      };

    frame.addWindowListener( wl );
    frame.pack();
    frame.setVisible( true );
    }
  }

//----------------------------------------- Final del fichero java2004C.java