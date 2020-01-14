//
//  java1525.java
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
//     Creacion: 07-Ene-2002  11:53:54
//     Revision: 08-Feb-2002  05:58:14
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de caracteres Unicode en la presentación
 * de textos en pantalla.
 * Si el lector no tiene isntaladas fuentes Unicode, puede que algunos
 * caracteres se muestren como cuadros vacíos.
 * Además, es conveniente asegurarse de que el archivo de configuración
 * de fuentes "font.properties" contiene el mapeo correcto de las fuentes
 * que correpsonden al sistema operativo en que se ejecute el programa.
 * El texto que se muestra es ilustrativo y no corresponde a significado
 * alguno, es simplemente una sucesión de letras para que el lector
 * pueda comprobar el uso de las diferentes características de las
 * fuentes y el uso de distintos alfabetos dentro de un mismo patrón
 * de caracteres Unicode.
 */

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

class java1525 extends JPanel {
  protected JLabel lab1,lab2,lab3,lab4,lab5;
  protected JTextField txt1,txt2,txt3,txt4,txt5;

  java1525() {
    setLayout( new BoxLayout(this,BoxLayout.Y_AXIS) );
    crearAlfabeto();
    }

  public void crearAlfabeto() {
    lab1 = new JLabel( "Texto en Español:" );
    txt1 = new JTextField( 10 );
    txt1.setFont( new Font("Arial Unicode MS",Font.PLAIN,24) );
    txt1.setForeground( Color.blue );
    txt1.setText( "\u0061\u0062\u0063\u0064\u0065\u0066\u0067\u0068"+
      "\u0069\u006A\u006B\u006C\u006D\u006E\u006F" );
    add( lab1 );
    add( txt1 );

    lab2 = new JLabel( "Texto en Árabe:" );
    txt2 = new JTextField( 10 );
    txt2.setFont(new Font( "Arial Unicode MS",Font.PLAIN,24) );
    txt2.setHorizontalAlignment( JTextField.RIGHT );
    txt2.setForeground( Color.blue );
    txt2.getDocument().putProperty( TextAttribute.RUN_DIRECTION,
      TextAttribute.RUN_DIRECTION_RTL );
    txt2.setText( "\u0624\u0626\u0628\u0629\u0634\u0635\u0637\u0639"+
      "\u0642\u0643\u0645\u0646\u0647\u0648\u0649" );
    add( lab2 );
    add( txt2 );


    lab3 = new JLabel( "Texto en Hebreo:" );
    txt3 = new JTextField( 10 );
    txt3.setFont(new Font( "Lucida Sans Regular",Font.PLAIN,24) );
    txt3.setHorizontalAlignment( JTextField.RIGHT );
    txt3.setForeground( Color.blue );
    txt3.getDocument().putProperty( TextAttribute.RUN_DIRECTION,
      TextAttribute.RUN_DIRECTION_RTL );
    txt3.setText(
       "\u05D0\u05D1\u05D2\u05D3\u05D4\u05D5\u05D6\u05D7\u05D8"+
       "\u05D9\u05DA\u05DB\u05DC\u05DE\u05DF" );
    add( lab3 );
    add( txt3 );

    lab4 = new JLabel( "Texto en Cirílico:" );
    txt4 = new JTextField( 10 );
    txt4.setFont( new Font("Lucida Sans Regular",Font.PLAIN,24) );
    txt4.setForeground( Color.blue );
    txt4.setText( "\u0430\u0431\u0432\u0433\u0434\u0435\u0436"+
      "\u0437\u0438\u0439\u043A\u043B\u043C\u043D\u043E" );
    add( lab4 );
    add( txt4 );

    lab5 = new JLabel( "Texto en Griego:" );
    txt5 = new JTextField( 10 );
    txt5.setFont( new Font("Lucida Sans Regular",Font.PLAIN,24) );
    txt5.setForeground( Color.blue );
    txt5.setText( "\u03B1\u03B2\u03B3\u03B4\u03B5\u03B6\u03B7"+
      "\u03B8\u03B9\u03BA\u03BB\u03BC\u03BD\u03BE\u03BF" );
    add( lab5 );
    add( txt5 );
    }

  public static void main( String[] args ) {
    JFrame f = new JFrame( "Tutorial de Java, Gráficos" );
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    f.getContentPane().setLayout( new BorderLayout() );
    f.getContentPane().add( new java1525(),BorderLayout.CENTER );
    f.pack();
    f.setSize( 300,300 );
    f.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1525.java
