//
//  java1501.java
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
//     Creacion: 20-Mar-1999  07:01:14
//     Revision: 08-Feb-2002  05:44:42
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es muy sencillo, se limita a presentar un punto y su
 * coordenada cuando se pulsa el ratón en el interior de la ventana.
 * En la parte inferior de la ventana también se indica la coordenada
 * en la que se ha producido el click del ratón.
 * Este ejemplo puede ejecutarse tanto como Applet o como aplicación
 * independiente
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

// Clase de control del ejemplo
public class java1501 extends JApplet {
  JLabel etiqueta;

  // Este método se llama solamente una vez cuando se ejecuta el
  // ejemplo como applet
  public void init() {
    creaZona( getContentPane() );
    }

  // Este es el método que crea la zona del Interfaz sobre la
  // que se van a recibir los clicks de ratón
  void creaZona( Container contenedor ) {
    // Fijamos el controlador de posicionamiento de componentes
    contenedor.setLayout( new BoxLayout( contenedor,BoxLayout.Y_AXIS) );
    // Creamos la zona de coordenadas, donde se va a
    ZonaCoordenadas zonaCoord = new ZonaCoordenadas( this );
    contenedor.add( zonaCoord );
    // Etiqueta de la parte inferior de la ventana
    etiqueta = new JLabel( "Pulse con el ratón en la zona enmarcada." );
    contenedor.add( etiqueta );
    // Alineamos los componentes a la izquierda
    zonaCoord.setAlignmentX( LEFT_ALIGNMENT );
    etiqueta.setAlignmentX( LEFT_ALIGNMENT );
    }

  // Método para actualizar el mensaje de coordenadas en la
  // parte inferior de la ventana
  public void presentaCoor( Point _punto ) {
    etiqueta.setText( "Click en la coordenada >>> ("
      +_punto.x+", "+_punto.y+ ")" );
    }

  // Función de control, para cuando el ejemplo se ejecuta como
  // aplicación individual
  public static void main( String[] args ) {
    JFrame f = new JFrame( "Tutorial de Java, Gráficos" );

    // Clase anidada para controlar el cierre de la ventana y
    // concluir la aplicación
    f.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );

    // Creamos una instancia de la clase del Ejemplo
    java1501 ejemplo = new java1501();
    // Creamos la zona de coordenadas
    ejemplo.creaZona( f.getContentPane() );
    f.pack();
    // Lo hacemos todo visible en pantalla
    f.setVisible( true );
    }
  }

// Esta es la clase que contruye la zona de la ventana en la
// que se van a recibir la coordenadas del ratón. Es un JPanel
// sobre el que se recogen los clicks de ratón y se presentan las
// coordenadas del punto en que se ha producido ese click
class ZonaCoordenadas extends JPanel {
  Point punto = null;
  java1501 ejemplo;
  Dimension tamano = new Dimension( 350,200 );

  public ZonaCoordenadas( java1501 _ejemplo ) {
    this.ejemplo = _ejemplo;

    // Fijamos el tipo de bordes que se va a presentar, para que
    // se pueda comprobar el efecto cuando se pica sobre ellos
    Border bordeRais = BorderFactory.createRaisedBevelBorder();
    Border bordeLowe = BorderFactory.createLoweredBevelBorder();
    Border bordeComp = BorderFactory.createCompoundBorder(
      bordeRais,bordeLowe );
    setBorder( bordeComp );

    // Este es el receptor de los eventos de pulsación de
    // botones del ratón
    addMouseListener( new MouseAdapter() {
      public void mousePressed( MouseEvent evt ) {
        int x = evt.getX();
        int y = evt.getY();

        // Si no tenemos punto, lo creamos, y sino lo que hacemos
        // es actualizar las coordenadas del existente
        if( punto == null ) {
          punto = new Point( x,y );
          }
        else {
          punto.x = x;
          punto.y = y;
          }
        // Actualizamos la información del punto en que se ha
        // picado con el ratón
        repaint();
        }
      } );
    }


  public Dimension getPreferredSize() {
    return tamano;
    }


  public void paintComponent( Graphics g ) {
    // Se repinta el fondo, para eliminar el mensaje que hubiese
    // de la presentación de información del pique anterior
    super.paintComponent( g );

    // Cuando se pica en una zona correcta, tenemos un punto
    // sobre el cual presentamos un rectangulito para identificarlo
    // mejor, y al lado se indican sus coordenadas
    // Antes se ha actualizado el mensaje de texto de la parte
    // inferior de la ventana donde también se indica el punto en
    // que se ha pulsado el ratón
    if( punto != null ) {
      ejemplo.presentaCoor( punto );
      g.fillRect( punto.x-1,punto.y-1,2,2 );
      g.drawString( "("+punto.x+","+punto.y+")",punto.x,punto.y );
      }
    }
  }

//------------------------------------------ Final del fichero java1501.java