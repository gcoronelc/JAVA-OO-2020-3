//
//  java1118.java
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
//     Creacion: 12-Jun-1998  15:24.56
//     Revision: 03-Feb-2002  12:29:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra el uso de getContentPane() para añadir un
 * JButton a un JFrame, y el uso de AncestorListener sobre un JButton.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class java1118 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


// Clase para instanciar un objeto de tipo interfaz gráfico
class IHM {
  public IHM(){
    // Crea un JFrame y le pone título, tamaño, etc.
    JFrame ventana = new JFrame();

    ventana.setSize( 300,300 );
    ventana.setTitle( "Tutorial de Java, Swing" );

    // Se añade el receptor de eventos de la ventana para concluir la
    // ejecución del programa
    ventana.addWindowListener( new Conclusion() );

    // Se crea un objeto JButton
    JButton boton = new JButton( "Boton" );

    // Se registra un objeto AncestorListener sobre cada JButton
    boton.addAncestorListener( new MiAncestorListener() );

    // Se añade el botón al objeto JFrame
    ventana.getContentPane().add( boton );

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
    // AncestorListener
    public void ancestorAdded( AncestorEvent evt ) {
      System.out.println( "Llamada al metodo ancestorAdded" );
      System.out.println( "Origen Evento: " + evt.getSource() );
      System.out.println( "Ancestor: " + evt.getAncestor() );
      System.out.println( "Padre: " + evt.getAncestorParent() );
      System.out.println( "Componente: " + evt.getComponent() );
      System.out.println( "ID: " + evt.getID() );
      }

    public void ancestorRemoved( AncestorEvent evt ) {
      System.out.println( "Metodo ancestorRemoved" );
      }

    public void ancestorMoved( AncestorEvent evt ) {
      System.out.println( "Metodo ancestorMoved" );
      }
    }
  }

//------------------------------------------ Final del fichero java1118.java