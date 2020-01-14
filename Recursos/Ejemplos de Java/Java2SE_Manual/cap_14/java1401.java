//
//  java1401.java
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
//     Creacion: 05-Jun-1998  17:51:14
//     Revision: 07-Feb-2002  05:25:50
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo funciona exactamente igual que si se estuviese ejecutando
 * en el AWT estándar del JDK 1.1, pero se han añadido "J" a los objetos.
 * Además, se han incorporado a los tres objetos que se presentan en el
 * interior de la ventana, un texto indicando de qué componente se trata
 * que aparece cuando el ratón permanece sobre dicho componente un ratito
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1401 extends JPanel {
  JButton boton1 = new JButton( "JButton 1" );
  JButton boton2 = new JButton( "JButton 2" );
  JTextField texto = new JTextField( 20 );

  public java1401() {
    ActionListener al = new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        String nombre = ( (JButton)evt.getSource()).getText();
        texto.setText( nombre+" Pulsado" );
        }
      };
    boton1.addActionListener( al );
    boton1.setToolTipText( "Soy el JBoton 1" );
    add( boton1 );
    boton2.addActionListener( al );
    boton2.setToolTipText( "Soy el JBoton 2" );
    add( boton2 );
    texto.setToolTipText( "Soy el JCampoDeTexto" );
    add( texto );
    }

  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( new java1401(),BorderLayout.CENTER );
    ventana.setSize( 300,100 );

    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1401.java