//
//  java803.java
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
//     Creacion: 02-Jun-1999  06:43:02
//     Revision: 03-Feb-2002  07:13:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la utilización de canales de entrada y salida
 * formados por arrays de bytes hacia fichero, a través del uso de applets.
 * Presenta un campo de entrada de texto cuyo contenido es grabado en un
 * fichero cuando se pulsa el botón.
 * Una vez pulsado el botón se presenta el contenido de otro fichero
 * y vuelta a empezar.
 * Si el lector no utiliza la firma de applets para firmar éste. Debe
 * permitir que los applets accedan a disco. Para ello es necesario
 * introducir en el archivo .java.policy la cláusula que lo permita.
 * Este archivo se coloca en el directorio raiz de la configuración del
 * usuario. En Unix es el directorio de entrada, y en Windows2000, por
 * ejemplo, es el directorio: c:\Documents and Settings\<usuario>.
 * También se puede utilizar mediante el parámetro
 * "-Djava.security.policy" caundo se invoca al applet.
 * El contenido de la clave de permisos a introducir sería:
 * grant {
 *   permission java.util.PropertyPermission "user.home","read";
 *   permission java.io.FilePermission "${user.home}/textoe.txt","write";
 *   permission java.io.FilePermission "${user.home}/textol.txt","read";
 *   };
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.io.*;

public class java803 extends Applet implements ActionListener {
  JLabel texto;
  JLabel pulsado;
  JButton boton;
  JButton botonClick;
  JPanel panel;
  JTextField campoTexto;

  public void init() {
    setLayout( new BorderLayout(1,2) );
    setFont( new Font( "Helvetica",Font.PLAIN,14 ) );
    setBackground( Color.white );
    System.out.println( "Applet arrancado..." );
    }

  public void start() {
    // Etiquetas informativas a colocar en la parte superior, indicando
    // Cuando se va a salvar a fichero y cuando se ha leido de fichero
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

    // Se incorporan los componentes al applet
    add( "North",texto );
    add( "Center",campoTexto );
    add( "South",boton );
    }

  public void stop() {
    System.out.println( "Applet detenido..." );
    }

  public void destroy() {
    System.out.println( "Llamada a destroy()...");
    }


  // Este es el método en que se realizan todas las operaciones que
  // involucran a los ficheros
  public void actionPerformed( ActionEvent evt ) {
    // Obtenemos el origen del evento, ya que sólo tratamos los que
    // provengan del click del botón
    Object source = evt.getSource();
    // Directorio raiz del usuario, al que se garantiza el acceso
    // mediante el archivo de pólizas
    String dir = System.getProperty( "user.home" );

    // Si se trata del botón Inicial
    if( source == boton ) {
      JLabel etiqueta = new JLabel();

      try {
        // Porción de código encargada de grabar en fichero el contenido
        // que se introduzca en el campo de texto
        // Primero se recupera el texto del campo de texto
        String cadena = campoTexto.getText();

        // Se crea un objeto File correspondiente al fichero donde se va a
        // grabar el texto

        File fichSalida = new File( dir +File.separatorChar+ "textoe.txt" );
        // Se crea el canal de de salida conectado a ese fichero
        FileWriter canalSalida = new FileWriter( fichSalida );
        // Se escribe la cadena de texto en el fichero
        canalSalida.write( cadena );
        // Se cierra el canal
        canalSalida.close();

        // Porción de código encargada de recuperar del fichero el contenido
        // que se presenta en la zona correspondiente al campo de texto,
        // una vez que se ha grabado en fichero el array de caracteres
        // Se crea un objeto File para referenciar al fichero del que se va
        // a recuperar el contenido
        File fichEntrada = new File( dir +File.separatorChar+ "textol.txt" );
        // Se crea el canal de entrada para leer el texto
        FileReader canalEntrada = new FileReader( fichEntrada );
        // Creamos un array de caracteres para almacenar el contenido
        // del fichero
        char c[] = new char[(char)fichEntrada.length()];
        // Se lee el fichero
        int numChars = canalEntrada.read( c );
        // Se convierte el array de caracteres a cadena que se presenta
        // en la ventana
        String cad = new String( c );
        etiqueta.setText( cad );
        // Se cierra el canal de comunicación con el fichero
        canalEntrada.close();
      } catch( IOException e ) {
        e.printStackTrace();
        }

      // Se presenta el texto recuperado del fichero en la ventana y se
      // cambia el botón para poder realizar otro ciclo del proceso
      removeAll();
      add( "North",pulsado );
      add( "Center",etiqueta );
      add( "South",botonClick );
      validate();
      repaint();
      }

    // Si se trata del botón de reinicio, volvemos a presentar en la
    // ventana los componentes iniciales
    if( source == botonClick ) {
      removeAll();
      add( "North",texto );
      // Texto en blanco
      campoTexto.setText( "" );
      add( "Center",campoTexto );
      add( "South",boton );
      validate();
      repaint();
      }
    }
  }

//------------------------------------------- Final del fichero java803.java