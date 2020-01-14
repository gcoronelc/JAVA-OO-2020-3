//
//  java1524.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 07-Ene-2002  09:35:49
//     Revision: 08-Feb-2002  05:57:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo presenta una ventana con la información correspondiente
 * a la fuente de caracteres con que aparece el texto principal en la
 * ventana.
 * El lector puede alterar la fuente de caracteres y recompilar el
 * ejemplo para comprobar la información que Java proporciona sobre
 * cuanquier fuente de caracteres soportada por la plataforma en la
 * que se ejecute esta aplicación.
 */

import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1524 extends Frame {

  // Contructor de la clase
  public java1524() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 400,250 );
    this.setVisible( true );

    // Clase anidada que permite terminar la ejecución de la animación
    this.addWindowListener(
      // Definición de la clase anónima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      } );
    }

  // Método de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    new java1524();
    }

  // Se sobrecarga el método paint()
  public void paint( Graphics g ) {
    // Texto que se presentará en la ventana
    String texto = "Java, fuentes, gráficos";
    // Fuente de caracteres de la que obtenemos infromación
    Font fuente = new Font( "SansSerif",Font.PLAIN,32 );
    // Fuente de caracteres para presentar la información de la anterior
    Font fuenteInfo = new Font( "Monospaced",Font.PLAIN,14 );
    // Posición del texto
    int x = 20;
    int y = 60;
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING,
      RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

    // Obtenemos la información de las dos fuentes. De la primera para
    // presentarla y de la segunda, para utilizarla a la hora de
    // generar las líneas de información
    FontMetrics fm = g2.getFontMetrics( fuente );
    FontMetrics fm2 = g2.getFontMetrics( fuenteInfo );

    // Fijamos la fuente del texto y lo pintamos en la ventana
    g2.setFont( fuente );
    g2.drawString( texto,x,y );
    // Fijamos la fuente del texto de información y vamos pintando en
    // la ventana en líneas sucesivas la información correspondiente
    // a la fuente de caracteres con que se ha pintado en texto anterior
    g2.setFont( fuenteInfo );
    g2.drawString( "Tamaño: " +fm.getHeight(),
      x,y+fm.getHeight() );
    g2.drawString( "Leading: " +fm.getLeading(),
      x,y+fm.getHeight()+fm2.getHeight() );
    g2.drawString( "Ascendente: " +fm.getAscent(),
      x,y+fm.getHeight()+(fm2.getHeight()*2) );
    g2.drawString( "Descendente: " +fm.getDescent(),
      x,y+fm.getHeight()+(fm2.getHeight()*3) );
    g2.drawString( "Ascendente Máximo: " +fm.getMaxAscent(),
      x,y+fm.getHeight()+(fm2.getHeight()*4) );
    g2.drawString( "Avance Máximo: " +fm.getMaxAdvance(),
      x,y+fm.getHeight()+(fm2.getHeight()*5) );
    g2.drawString( "Longitud Texto: " +fm.stringWidth(texto),
      x,y+fm.getHeight()+(fm2.getHeight()*6) );
    }
  }

//------------------------------------------ Final del fichero java1524.java
