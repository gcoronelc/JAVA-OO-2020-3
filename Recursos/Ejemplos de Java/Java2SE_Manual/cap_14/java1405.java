//
//  java1405.java
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
//     Creacion: 09-Sep-1998  11:06:12
//     Revision: 07-Feb-2002  05:35:13
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra la facilidad con que se pueden incorporar
 * imágenes a los botones Swing, para representar los diferentes estados
 * en que se puede encontrar.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1405 extends JPanel {

  public java1405() {
    ImageIcon izq = new ImageIcon( "left.gif" );
    ImageIcon izqRollover = new ImageIcon( "leftRollover.gif" );
    ImageIcon izqDown = new ImageIcon( "leftDown.gif" );
    // Se le indica el texto que va a presentar y cual es la
    // imagen que debe usar en estado de reposo
    JButton boton = new JButton( "Boton",izq );

    // Ahora se asignan las otras imagenes a los diferentes estados,
    // una para cuando se pulsa o hunde el botón y otra para
    // cuando el ratón pasa por encima del botón
    boton.setPressedIcon( izqDown );
    boton.setRolloverIcon( izqRollover );
    boton.setRolloverEnabled( true );
    boton.setToolTipText( "Boton con imagenes asociadas" );
    add( boton );
    }

  public static void main( String args[] ) {
    java1405 panel = new java1405();
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( panel,BorderLayout.CENTER );
    ventana.setSize( 250,110 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1405.java