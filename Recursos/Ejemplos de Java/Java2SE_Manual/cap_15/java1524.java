//
//  java1524.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 07-Ene-2002  09:35:49
//     Revision: 08-Feb-2002  05:57:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo presenta una ventana con la informaci�n correspondiente
 * a la fuente de caracteres con que aparece el texto principal en la
 * ventana.
 * El lector puede alterar la fuente de caracteres y recompilar el
 * ejemplo para comprobar la informaci�n que Java proporciona sobre
 * cuanquier fuente de caracteres soportada por la plataforma en la
 * que se ejecute esta aplicaci�n.
 */

import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1524 extends Frame {

  // Contructor de la clase
  public java1524() {
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setSize( 400,250 );
    this.setVisible( true );

    // Clase anidada que permite terminar la ejecuci�n de la animaci�n
    this.addWindowListener(
      // Definici�n de la clase an�nima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      } );
    }

  // M�todo de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    new java1524();
    }

  // Se sobrecarga el m�todo paint()
  public void paint( Graphics g ) {
    // Texto que se presentar� en la ventana
    String texto = "Java, fuentes, gr�ficos";
    // Fuente de caracteres de la que obtenemos infromaci�n
    Font fuente = new Font( "SansSerif",Font.PLAIN,32 );
    // Fuente de caracteres para presentar la informaci�n de la anterior
    Font fuenteInfo = new Font( "Monospaced",Font.PLAIN,14 );
    // Posici�n del texto
    int x = 20;
    int y = 60;
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING,
      RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

    // Obtenemos la informaci�n de las dos fuentes. De la primera para
    // presentarla y de la segunda, para utilizarla a la hora de
    // generar las l�neas de informaci�n
    FontMetrics fm = g2.getFontMetrics( fuente );
    FontMetrics fm2 = g2.getFontMetrics( fuenteInfo );

    // Fijamos la fuente del texto y lo pintamos en la ventana
    g2.setFont( fuente );
    g2.drawString( texto,x,y );
    // Fijamos la fuente del texto de informaci�n y vamos pintando en
    // la ventana en l�neas sucesivas la informaci�n correspondiente
    // a la fuente de caracteres con que se ha pintado en texto anterior
    g2.setFont( fuenteInfo );
    g2.drawString( "Tama�o: " +fm.getHeight(),
      x,y+fm.getHeight() );
    g2.drawString( "Leading: " +fm.getLeading(),
      x,y+fm.getHeight()+fm2.getHeight() );
    g2.drawString( "Ascendente: " +fm.getAscent(),
      x,y+fm.getHeight()+(fm2.getHeight()*2) );
    g2.drawString( "Descendente: " +fm.getDescent(),
      x,y+fm.getHeight()+(fm2.getHeight()*3) );
    g2.drawString( "Ascendente M�ximo: " +fm.getMaxAscent(),
      x,y+fm.getHeight()+(fm2.getHeight()*4) );
    g2.drawString( "Avance M�ximo: " +fm.getMaxAdvance(),
      x,y+fm.getHeight()+(fm2.getHeight()*5) );
    g2.drawString( "Longitud Texto: " +fm.stringWidth(texto),
      x,y+fm.getHeight()+(fm2.getHeight()*6) );
    }
  }

//------------------------------------------ Final del fichero java1524.java
