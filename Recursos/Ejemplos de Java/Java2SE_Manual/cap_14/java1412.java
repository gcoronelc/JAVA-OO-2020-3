//
//  java1412.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 08-Sep-1998  15:20:58
//     Revision: 07-Feb-2002  05:50:58
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la forma de crear un menú popup, al pulsar el
 * boton derecho del ratón, normalmente, que es el asignado a estas
 * tareas
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1412 extends JPanel {
  JPopupMenu popup = new JPopupMenu();
  JTextField txt = new JTextField( 10 );

  public java1412() {
    add( txt );

    ActionListener al = new ActionListener() {
      public void actionPerformed( ActionEvent evt ){
        txt.setText( ((JMenuItem)evt.getSource()).getText() );
        }
      };

    JMenuItem elemento = new JMenuItem( "Carpanta" );
    elemento.addActionListener( al );
    popup.add( elemento );
    elemento = new JMenuItem( "Rompetechos" );
    elemento.addActionListener( al );
    popup.add( elemento );
    elemento = new JMenuItem( "Otilio" );
    elemento.addActionListener( al );
    popup.add( elemento );
    popup.addSeparator();
    elemento = new JMenuItem( "Mortadelo" );
    elemento.addActionListener( al );
    popup.add( elemento );
    enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }


  protected void processMouseEvent( MouseEvent evt ){
    if ( evt.isPopupTrigger() )
      popup.show( evt.getComponent(),evt.getX(),evt.getY() );
    else
      super.processMouseEvent( evt );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1412(),BorderLayout.CENTER );
    frame.setSize( 200,150 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1412.java