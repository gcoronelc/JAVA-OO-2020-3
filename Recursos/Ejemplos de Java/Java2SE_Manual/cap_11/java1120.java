//
//  java1120.java
//  Copyright (c) 2001,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 16-Dic-2001  19:36:24
//     Revision: 03-Feb-2002  12:15:55
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1120 {
  // Creamos un array con los colores con que vamos a cambiar el fondo
  // de la ventana
  private static final Color colores[] = {
    Color.WHITE,
    Color.YELLOW,
    Color.ORANGE,
    Color.PINK,
    Color.RED,
    Color.MAGENTA,
    Color.DARK_GRAY,
    Color.GREEN,
    Color.LIGHT_GRAY,
    Color.CYAN,
    Color.GRAY,
    Color.BLUE,
    Color.BLACK
    };
  static int selColor;
  private static final int ARRIBA = 1;
  private static final int ABAJO = 2;
  // Bot�n que haremos que ocupe toda la ventana
  static JButton boton = new JButton();

  // Este m�todo redimensiona la ventana al tama�o m�ximo que
  // permita la pantalla
  private static void dimensionVentana( JFrame ventana ) {
    Toolkit tkit = Toolkit.getDefaultToolkit();
    Dimension dimVentana = tkit.getScreenSize();
    GraphicsConfiguration gconf = ventana.getGraphicsConfiguration();
    Insets insets = tkit.getScreenInsets( gconf );
    dimVentana.width -= (insets.left + insets.right );
    dimVentana.height -= (insets.top + insets.bottom );
    ventana.setSize( dimVentana );
    ventana.setLocation( insets.left,insets.top );
    }

  // Este es el m�todo encargado de cambiar el fondo de la ventana,
  // que en realidad no es m�s que cambiar el fondo del bot�n que
  // ocupa toda la ventana
  private static void cambiarFondo( JFrame ventana,int direccion ) {
    boton.setBackground( colores[selColor] );

    // Actualizamos el color en funci�n de la direcci�n elegida
    if( direccion == ARRIBA )
      selColor++;
    else
      selColor--;

    // Controlamos los l�mites del array de colores, para que parezca
    // que se trata de una lista circular
    if( selColor == colores.length )
      selColor = 0;
    else if( selColor < 0 )
      selColor = colores.length-1;
    }

  // Este es el m�todo que controla el evento de la rueda superior
  // del rat�n
  private static void eventoRuedaRaton( final JFrame ventana ) {
    MouseWheelListener rueda = new MouseWheelListener() {
      public void mouseWheelMoved( MouseWheelEvent evt ){
        int cnt = evt.getWheelRotation();
        int dir = (Math.abs(cnt) > 0) ? ARRIBA : ABAJO;
        cambiarFondo( ventana,dir );
        }
      };
    // Se a�ade el receptor de eventos al bot�n
    boton.addMouseWheelListener( rueda );
    }

  // Este es el m�todo que controla el evento que produce cada una
  // de las teclas de Shift, o may�sculas, ques e encuentran a cada
  // lado del teclado
  private static void eventoTeclado( final JFrame ventana ) {
    KeyListener tecla = new KeyAdapter() {
      public void keyPressed( KeyEvent evt ) {
        // Detectamos la tecla de may�sculas
        if( evt.getKeyCode() == KeyEvent.VK_SHIFT ) {
          // Detectamos la posici�n, si es la tecla de la izquierda o
          // la colocada a la derecha del teclado
          int pos = evt.getKeyLocation();
          int dir = (pos == KeyEvent.KEY_LOCATION_LEFT) ? ARRIBA : ABAJO;
          cambiarFondo( ventana,dir );
          }
        }
      };
    // Se a�ade el receptor de eventos al bot�n
    boton.addKeyListener( tecla );
    }

  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Eventos" );
    ventana.getContentPane().add( boton,BorderLayout.CENTER );
    ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    dimensionVentana( ventana );
    eventoRuedaRaton( ventana );
    eventoTeclado( ventana );
    cambiarFondo( ventana,ARRIBA );
    ventana.show();
    }
  }

//------------------------------------------ Final del fichero java1120.java