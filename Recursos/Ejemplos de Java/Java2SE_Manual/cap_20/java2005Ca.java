//  java2005Ca.java
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
//     Creacion: 27-Jun-1999  08:28:54
//     Revision: 09-Feb-2002  21:05:09
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;

class java2005Ca extends JFrame implements ActionListener {
  JLabel etiq;
  JLabel pulsado;
  JButton boton;
  JPanel panel;
  JTextField campoTexto;

  java2005Ca() {
    etiq = new JLabel( "Texto a enviar:" );
    // Campo de texto cuyo contenido se va a enviar al otro objeto
    campoTexto = new JTextField( 20 );
    // Botón de envío
    boton = new JButton( "Enviar" );
    boton.addActionListener( this );

    // Contenedor de todos los componentes
    panel = new JPanel();
    panel.setLayout( new BorderLayout() );
    panel.setBackground( Color.white );
    getContentPane().add( panel );
    // Se incorporan los componentes al panel
    panel.add( "North",etiq );
    panel.add( "Center",campoTexto );
    panel.add( "South",boton );
    }

  // Este es el método en el que se realizan las acciones que se
  // originan al pulsar el botón de envío
  public void actionPerformed( ActionEvent evt ) {
    // Obtenemos el origen del evento
    Object origen = evt.getSource();

    if( origen == boton ) {
      // Se recoge el texto
      String texto = campoTexto.getText();
      String direccion = "rmi://localhost/";
      try {
        java2005I refObj =
          (java2005I)Naming.lookup( direccion+"ObjetoRemoto" );
        // Se envía al objeto remoto
        refObj.enviaTexto( texto );
      }catch( Exception e ) {
        e.printStackTrace();
        }
      // Dejamos el campo de texto vacío de nuevo
      campoTexto.setText( new String("") );
      }
    }

  // Método principal del cliente
  public static void main( String[] args ) {
    java2005Ca frame = new java2005Ca();
    frame.setTitle( "Tutorial de Java, RMI 1" );

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

//---------------------------------------- Final del fichero java2005Ca.java