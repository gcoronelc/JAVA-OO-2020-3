//
//  java1410.java
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
//     Creacion: 29-Jul-1998  13:27:54
//     Revision: 07-Feb-2002  05:48:51
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra cómo se pueden crear botones con imágenes, de
 * tal forma que se hace mucho más visual la forma en que se interactúa
 * con el usuario. Al botón gráfico del ejemplo también se le ha añadido
 * un ToolTip, de forma que si el ratón permanece suficiente tiempo sobre él
 * aparece el mensaje
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1410 extends JPanel {
  static Icon imgs[] = {
    new ImageIcon( "star0.gif" ),
    new ImageIcon( "star1.gif" ),
    new ImageIcon( "star2.gif" ),
    new ImageIcon( "star3.gif" ),
    new ImageIcon( "star4.gif" ),
    };
  JButton boton = new JButton( "JButton",imgs[3] );
  JButton boton2 = new JButton( "Deshabilita" );
  boolean mad = false;

  public java1410() {
    boton.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ){
        if( mad ) {
          boton.setIcon( imgs[3] );
          mad = false;
          }
        else {
          boton.setIcon( imgs[0] );
          mad = true;
          }
        boton.setVerticalAlignment( JButton.TOP );
        boton.setHorizontalAlignment( JButton.LEFT );
        }
      } );
    boton.setRolloverEnabled( true );
    boton.setRolloverIcon( imgs[1] );
    boton.setPressedIcon( imgs[2] );
    boton.setDisabledIcon( imgs[4] );
    boton.setToolTipText( "AleHop!" );
    add( boton );

    boton2.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ){
        if( boton.isEnabled() ) {
          boton.setEnabled( false );
          boton2.setText( "Habilita" );
          }
        else {
          boton.setEnabled( true );
          boton2.setText( "Deshabilita" );
          }
        }
      } );
    add( boton2 );
    }

  public static void main( String args[] ) {
    java1410 boton = new java1410();
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( boton,BorderLayout.CENTER );
    ventana.setSize( 300,200 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1410.java