//
//  java802.java
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
//     Creacion: 02-Jun-1999  06:15:54
//     Revision: 03-Feb-2002  07:05:50
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la utilización de canales de entrada y salida
 * formados por arrays de bytes hacia fichero.
 * Presenta un campo de entrada de texto cuyo contenido es grabado en un
 * fichero cuando se pulsa el botón.
 * Una vez pulsado el botón se presenta el contenido de otro fichero
 * y vuelta a empezar.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class java802 extends JFrame implements ActionListener {
  JLabel texto;
  JLabel pulsado;
  JButton boton;
  JButton botonClick;
  JPanel panel;
  JTextField campoTexto;

  // Constructor
  java802() {
    // Etiquetas informativas a colocar en la parte superior, indicando
    // Cuando se va a salvar a fichero y cuando se ha leido de fichero
    setFont( new Font("Helvetica",Font.PLAIN,14) );
    texto = new JLabel( "Texto a grabar en fichero:" );
    pulsado = new JLabel( "Texto recuperado de fichero:" );

    // Boton que se presenta cuando se va a grabar el contenido del
    // campo de texto en el fichero
    boton = new JButton( "Grabar" );
    boton.addActionListener( this );

    // Botón que se presenta una vez recuperado el texto de fichero,
    // para volver a comenzar el ciclo
    botonClick = new JButton( "Reiniciar" );
    botonClick.addActionListener( this );

    // Campo de texto cuyo contenido se va a grabar a fichero
    campoTexto = new JTextField( 20 );

    // Contenedor de los componentes que se han creado
    panel = new JPanel();
    panel.setLayout( new BorderLayout() );
    panel.setBackground( Color.white );
    getContentPane().add( panel );
    // Se incorporan los componentes al panel
    panel.add( "North",texto );
    panel.add( "Center",campoTexto );
    panel.add( "South",boton );
    }


  // Este es el método en que se realizan todas las operaciones que
  // involucran a los ficheros
  public void actionPerformed( ActionEvent evt ) {
    // Obtenemos el origen del evento, ya que sólo tratamos los que
    // provengan del click del botón
    Object source = evt.getSource();
    // Directorio raiz del usuario
    String dir = System.getProperty( "user.home" );

    // Si se trata del botón Inicial
    if( source == boton ) {
      JLabel etiqueta = new JLabel();

    try {
      // Porción de código encargada de grabar en fichero el contenido
      // que se introduzca en el campo de texto
      // Primero se recupera el texto del campo de texto y se convierte
      // a un array de bytes
      String texto = campoTexto.getText();
      byte b[] = texto.getBytes();

      // Se crea un objeto File correspondiente al fichero donde se va a
      // grabar el texto
      File fichSalida = new File( dir +File.separatorChar+ "textoe.txt" );
      // Se crea el canal de de salida conectado a ese fichero
      FileOutputStream canalSalida = new FileOutputStream( fichSalida );
      // Se escribe el contenido del array de bytes en el fichero
      canalSalida.write( b );
      // Se cierra el canal
      canalSalida.close();

      // Porción de código encargada de recuperar del fichero el contenido
      // que se presenta en la zona correspondiente al campo de texto,
      // una vez que se ha grabado en fichero el array de bytes
      // Se crea un objeto File para referenciar al fichero del que se va
      // a recuperar el contenido
      File fichEntrada = new File( dir +File.separatorChar+ "textol.txt" );
      // Se crea el canal de entrada para leer el texto
      FileInputStream canalEntrada = new FileInputStream( fichEntrada );
      // Creamos un array de bytes para almacenar el contenido del fichero
      byte bt[] = new byte[(int)fichEntrada.length()];
      // Se lee el fichero
      int numBytes = canalEntrada.read( bt );
      // Se convierte el array de bytes a cadena que se presenta en la
      // ventana
      String cadena = new String( bt );
      etiqueta.setText( cadena );
      // Se cierra el canal de comunicación con el fichero
      canalEntrada.close();
    } catch( IOException e ) {
      e.printStackTrace();
      }

    // Se presenta el texto recuperado del fichero en la ventana y se
    // cambia el botón para poder realizar otro ciclo del proceso
    panel.removeAll();
    panel.add( "North",pulsado );
    panel.add( "Center",etiqueta );
    panel.add( "South",botonClick );
    panel.validate();
    panel.repaint();
    }

    // Si se trata del botón de reinicio, volvemos a presentar en la
    // ventana los componentes iniciales
    if( source == botonClick ) {
      panel.removeAll();
      panel.add( "North",texto );
      // Texto en blanco
      campoTexto.setText( "" );
      panel.add( "Center",campoTexto );
      panel.add( "South",boton );
      panel.validate();
      panel.repaint();
      }
    }

  public static void main( String[] args ) {
    java802 frame = new java802();
    frame.setTitle( "Tutorial de Java, Ficheros" );

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

//------------------------------------------- Final del fichero java802.java