//
//  java1403.java
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
//     Creacion: 09-Sep-1998  17:48:46
//     Revision: 07-Feb-2002  05:28:45
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra distintos tipos de etiquetas, en las que se cambia
 * el tipo de fuente de caracteres o se incorporan gráficos, haciendo uso
 * de las nuevas características que Swing añade a este tipo de Componentes
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class java1403 extends JPanel {

  public java1403() {
    setLayout( new GridLayout( 2,2 ) );

    JLabel etiq1 = new JLabel();
    etiq1.setText( "Etiqueta1" );
    add( etiq1 );

    JLabel etiq2 = new JLabel( "Etiqueta2" );
    etiq2.setFont( new Font( "Helvetica", Font.BOLD, 18 ) );
    add( etiq2 );

    Icon imagen = new ImageIcon( "star0.gif" );
    JLabel etiq3 = new JLabel( "Etiqueta3", imagen,SwingConstants.CENTER );
    etiq3.setVerticalTextPosition( SwingConstants.TOP );
    add( etiq3 );

    JLabel etiq4 = new JLabel( "Etiqueta4",SwingConstants.RIGHT );
    add( etiq4 );
    }

  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( new java1403(),BorderLayout.CENTER );
    ventana.setSize( 300,150 );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1403.java