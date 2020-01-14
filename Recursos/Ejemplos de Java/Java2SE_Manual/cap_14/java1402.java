//
//  java1402.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 05-Jun-1998  11:54:32
//     Revision: 07-Feb-2002  05:27:54
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra los diferentes tipos de bordes que incorpora la
 * libreria Swing a Java
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class java1402 extends JPanel {

  static JPanel creaBorde( Border b ) {
    JPanel panel = new JPanel();
    String str = b.getClass().toString();

    str = str.substring( str.lastIndexOf('.') + 1 );
    panel.setLayout( new BorderLayout() );
    panel.add(new JLabel( str,JLabel.CENTER ),BorderLayout.CENTER );
    panel.setBorder( b );

    return( panel );
    }


  public java1402() {
    setLayout( new GridLayout( 2,4 ) );
    add( creaBorde( new TitledBorder("Titulo") ) );
    add( creaBorde( new EtchedBorder() ) );
    add( creaBorde( new LineBorder(Color.blue) ) );
    add( creaBorde( new MatteBorder(5,5,30,30,Color.green) ) );
    add( creaBorde( new BevelBorder(BevelBorder.RAISED) ) );
    add( creaBorde( new SoftBevelBorder(BevelBorder.LOWERED) ) );
    add(creaBorde(new CompoundBorder(
      new EtchedBorder(),new LineBorder(Color.red) ) ) );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1402(),BorderLayout.CENTER );
    frame.setSize( 500,300 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1402.java