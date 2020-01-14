//  java2005Cb.java
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
//     Creacion: 27-Jun-1999  08:43:09
//     Revision: 09-Feb-2002  21:05:21
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el cliente RMI que se encarga de recibir la cadena de texto que
 * se envia desde el otro cliente.
 * Todo lo que se vaya recibiendo se va guardando en el area de texto al
 * pulsar el boton de la ventana
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;

class java2005Cb extends JFrame implements ActionListener {
  JLabel texto;
  JLabel pulsado;
  JButton boton;
  JPanel panel;
  JTextArea areaTexto;

  java2005Cb() {
    texto = new JLabel( "Texto recibido:" );
    // Area de texto donde se presenta la cadena de texto recibida
    areaTexto = new JTextArea();
    // Botón de envío
    boton = new JButton( "Insertar" );
    boton.addActionListener( this );

    // Contenedor de todos los componentes
    panel = new JPanel();
    panel.setLayout( new BorderLayout() );
    panel.setBackground( Color.white );
    getContentPane().add( panel );
    // Se incorporan los componentes al panel
    panel.add( "North",texto );
    panel.add( "Center",areaTexto );
    panel.add( "South",boton );
    }

  // Este es el método en el que se realizan las acciones que se
  // originan al pulsar el botón de insertar el texto
  public void actionPerformed( ActionEvent evt ) {
    // Obtenemos el origen del evento
    Object origen = evt.getSource();

    if( origen == boton ) {
      try {
        String direccion = "rmi://localhost/";
        java2005I refObj =
          (java2005I)Naming.lookup( direccion+"ObjetoRemoto" );
        // Se recoge el texto recibido por el objeto
        String texto = refObj.getTexto();
        // Se incorpora el texto al área de visualización
        areaTexto.append( texto );
      }catch( Exception e ) {
        e.printStackTrace();
        }
      }
    }

  // Metodo principal del cliente
  public static void main( String[] args ) {
    java2005Cb frame = new java2005Cb();
    frame.setTitle( "Tutorial de Java, RMI 2" );

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

//---------------------------------------- Final del fichero java2005Cb.java