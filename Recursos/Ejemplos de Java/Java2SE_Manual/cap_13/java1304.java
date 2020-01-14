//
//  java1304.java
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
//     Creacion: 06-Mar-1998  17:16:18
//     Revision: 05-Feb-2002  05:38:28
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de Lista, proporcionando
 * al usuario la posibilidad de seleccionar items de esa lista
 */
import java.awt.*;
import java.awt.event.*;

public class java1304 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM(){
    // Instancia un objeto List y coloca algunas cadenas sobre el,
    // para poder realizar selecciones
    List miLista = new List();

    for ( int i=0; i < 15; i++ )
      miLista.add( "Elemento "+i );
    // Activa la seleccion multiple
    miLista.setMultipleMode( true );
    // Presenta el elemento 1 al inicio
    miLista.select( 1 );

    // Instancia y registra un objeto ActionListener sobre el objeto
    // List. Se produce un evento de tipo Action cuando el usuario
    // pulsa dos veces sobre un elemento
    miLista.addActionListener( new MiListaActionListener( miLista ) );

    // Instancia un objeto Button para servicio de la seleccion
    // multiple. Tambien instancia y registra un objeto ActionListener
    // sobre el boton
    Button miBoton = new Button( "Selecciona Multiples Items" );
    miBoton.addActionListener( new miBotonActionListener( miLista ) );

    // Coloca el objeto List y el objeto Button el el objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( miLista );
    miFrame.add( miBoton );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia y registra un objeto receptor de eventos de ventana
    // para concluir la ejecucion del programa cuando el Frame se
    // cierres por accion del usuario sobre el
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Clase para recibir eventos de tipo ActionListener sobre el
// objeto List. Presenta en elemento seleccionado cuando el usuario
// pulsa dos veces sobre un item de lista cuando la seleccion es
// individual. Si el usuario pica dos veces sobre una seleccion
// multiple, se produce un evento pero el metodo getSelectedItem()
// de la clase List devuelve null y no se presenta nada en pantalla
class MiListaActionListener implements ActionListener {
  List oLista;

  MiListaActionListener( List lista ) {
    // Salva una referencia al objeto List
    oLista = lista;
    }

  // Sobreescribe el metodo actionPerformed() del interfaz
  // ActionListener
  public void actionPerformed( ActionEvent evt ) {
    if ( oLista.getSelectedItem() != null ) {
      System.out.println( "Seleccion Simple de Elementos" );
      System.out.println( "  "+oLista.getSelectedItem() );
      }
    }
  }


// Clase para recoger los eventos Action que se produzcan sobre el
// objeto Button. Presenta los elementos que haya seleccionados
// cuando el usuario lo pulsa, incluso aunque solamente haya uno
// marcado. Si no hubiese ninguno, so se presentaria nada en
// la pantalla
class miBotonActionListener implements ActionListener {
  List oLista;

  miBotonActionListener( List lista ) {
    // Salva una referencia al objeto List
    oLista = lista;
    }

  // Sobreescribe el metodo actionPerformed() del interfaz
  // ActionListener
  public void actionPerformed( ActionEvent evt ) {
    String cadena[] = oLista.getSelectedItems();

    if ( cadena.length != 0 ) {
      System.out.println( "Seleccion Multiple de Elementos" );
      for ( int i=0; i < cadena.length; i++ )
        System.out.println( "  "+cadena[i] );
      }
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye el programa cuando se cierra la ventana
    System.exit(0);
    }
  }

//------------------------------------------ Final del fichero java1304.java