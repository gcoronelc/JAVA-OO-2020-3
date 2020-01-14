//
//  java1423.java
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
//     Creacion: 10-Sep-1998  16:00:29
//     Revision: 07-Feb-2002  06:18:38
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo permite el control del teclado. En la pantalla aparecen
 * nueve botones sobre los cuales se puede colocar una "X" a través del
 * uso de los cursores del teclado
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class java1423 extends JFrame implements ActionListener {
  protected JButton botones[] = new JButton[9];

  public java1423() {
    super( "Tutorial de Java, Swing" );
    Container pane = getContentPane();
    pane.setLayout( new GridLayout( 3,3 ) );

    Border borde = BorderFactory.createLineBorder( Color.black );
    KeyStroke arriba = KeyStroke.getKeyStroke( KeyEvent.VK_UP,0 );
    KeyStroke abajo = KeyStroke.getKeyStroke( KeyEvent.VK_DOWN,0 );
    KeyStroke izqda = KeyStroke.getKeyStroke( KeyEvent.VK_LEFT,0 );
    KeyStroke drcha = KeyStroke.getKeyStroke( KeyEvent.VK_RIGHT,0 );

    JRootPane rootPane = getRootPane();
    rootPane.registerKeyboardAction( this,"arriba",arriba,
      JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
    rootPane.registerKeyboardAction( this,"abajo",abajo,
      JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
    rootPane.registerKeyboardAction( this,"drcha",drcha,
      JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
    rootPane.registerKeyboardAction( this,"izqda",izqda,
      JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );

    for ( int i=0; i < 9; i++ ) {
      JButton boton;
      boton = new JButton();
      boton.setBorder( borde );
      boton.setName( new Integer(i).toString() );
      pane.add( boton );

      botones[i] = boton;
      }
    setSize( 200,200 );
    }


  public void actionPerformed( ActionEvent evt ) {
    Component foco = getFocusOwner();
    String nombre = foco.getName();
    int indice = Integer.parseInt( nombre );
    botones[indice].setText( "" );
    String accion = evt.getActionCommand();

    if ( accion.equals( "arriba" ) ) {
      indice = (indice < 3) ? indice + 6 : indice - 3;
      }
    else if ( accion.equals( "abajo" ) ) {
      indice = (indice > 5) ? indice - 6 : indice + 3;
      }
    else if ( accion.equals( "izqda" ) ) {
      indice = (indice == 0) ? indice = 8 : indice - 1;
      }
    else { // asume drcha
      indice = (indice == 8) ? indice = 0 : indice + 1;
      }

    botones[indice].setText( "X" );
    botones[indice].requestFocus();
    }


  static public void main( String argv[] ) {
    new java1423().show();
    }
  }

//------------------------------------------ Final del fichero java1423.java