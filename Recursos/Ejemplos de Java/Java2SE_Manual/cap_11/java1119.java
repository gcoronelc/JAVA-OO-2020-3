//
//  java1119.java
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
//     Creacion: 12-Jun-1998  15:25:54
//     Revision: 03-Feb-2002  12:34:20
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra el uso de AncestorListener sobre un JButton.
 * El programa apila tres objetos Button y los coloca sobre un JFrame, con
 * lo cual también permite observar como los objetos JButton pueden ser
 * contenedores de otros objetos de su mismo tipo.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class java1119 {
  public static void main( String args[] ){
    IHM ihm = new IHM();
    }
  }

// Clase para instanciar un objeto de tipo interfaz gráfico
class IHM {
  public IHM() {
    // Crea un JFrame y le pone título, tamaño, etc.
    JFrame ventana = new JFrame();

    ventana.setSize( 300,100 );
    ventana.setTitle( "Tutorial de Java, Swing" );

    // Obsérvese la utilización de getContentPane() en la siguiente
    // sentencia
    ventana.getContentPane().setLayout( new FlowLayout() );

    // Se añade el receptor de eventos de la ventana para concluir la
    // ejecución del programa
    ventana.addWindowListener( new Conclusion() );

    // Se crean los tres objetos JButton
    JButton primerBoton = new JButton( "Primer Boton" );
    JButton segundoBoton = new JButton( "Segundo Boton" );
    JButton tercerBoton = new JButton( "Tercer Boton" );

    // Se apilan los botones uno sobre otro
    primerBoton.add( segundoBoton );
    segundoBoton.add( tercerBoton );

    // Se registra un objeto AncestorListener sobre cada JButton
    primerBoton.addAncestorListener( new MiAncestorListener() );
    segundoBoton.addAncestorListener( new MiAncestorListener() );
    tercerBoton.addAncestorListener( new MiAncestorListener() );

    // Se registra un objeto ActionListener sobre cada JButton
    primerBoton.addActionListener( new MiActionListener() );
    segundoBoton.addActionListener( new MiActionListener() );
    tercerBoton.addActionListener( new MiActionListener() );

    // Se añade el primer botón, que contiene a los demás, al
    // objeto JFrame
    ventana.getContentPane().add( primerBoton );

    System.out.println( "Se hace visible el JFrame" );
    ventana.setVisible( true );
    }


  // Esta clase se utiliza para concluir el programa cuando el
  // usuario decide cerrar la ventana
  class Conclusion extends WindowAdapter {
    public void windowClosing( WindowEvent evt ) {
      System.exit( 0 );
      }
    }


  // Definicion de la clase AncestorListener
  class MiAncestorListener implements AncestorListener {
    // Se definen los tres métodos declarados en el interfaz
    // AncestorListener, incorporando el moldeo necesario para
    // que nadie se queje
    public void ancestorAdded( AncestorEvent evt ) {
      System.out.println( "Metodo ancestorAdded" );
      System.out.println( " Origen del evento: " +
        ( (JButton)evt.getSource() ).getActionCommand() );
      }

    public void ancestorRemoved( AncestorEvent evt ) {
      System.out.println( "Metodo ancestorRemoved" );
      System.out.println( " Origen del evento: " +
        ( (JButton)evt.getSource() ).getActionCommand() );
      }

    public void ancestorMoved( AncestorEvent evt ) {
      System.out.println( "Metodo ancestorMoved" );
      System.out.println( " Origen del evento: " +
        ( (JButton)evt.getSource() ).getActionCommand() );
      }
    }


  // Definicion de la clase ActionListener
  class MiActionListener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      System.out.println( "Metodo actionPerformed" );
      System.out.println( " Origen del evento: " +
        ( (JButton)evt.getSource() ).getActionCommand() );
      }
    }
  }

//------------------------------------------ Final del fichero java1119.java