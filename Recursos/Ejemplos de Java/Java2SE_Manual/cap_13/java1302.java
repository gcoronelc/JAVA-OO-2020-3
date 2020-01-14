//
//  java1302.java
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
//     Creacion: 06-Mar-1998  12:58:34
//     Revision: 05-Feb-2002  05:36:42
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicacion presenta el texto indicando el boton de seleccion que
 * se acaba de pulsar. Ademas se instancia y registra un objeto receptor
 * para recoger los eventos windowClosing que se produzcan sobre el
 * Frame para concluir la aplicacion
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class java1302 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }

// Clase del Interfaz Gráfico que instancia los objetos
class IHM {
  public IHM() {

    // Instancia un objeto Choice y coloca objetos String sobre el
    // para realizar las selecciones
    Choice miChoice = new Choice();
    miChoice.add( "Primer Choice" );
    miChoice.add( "Segundo Choice" );
    miChoice.add( "Tercer Choice" );

    // Seleccionamos la cadena correspondiente a la tercera seleccion
    // por defecto, al arrancar la aplicacion
    miChoice.select( "Tercer Choice" );

    // Instanciamos y registramos un objeto ItemListener sobre
    // el objeto Choice
    miChoice.addItemListener( new MiItemListener( miChoice ) );

    // Colocamos el objetos Choice sobre el Frame para poder verlo
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( miChoice );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instanciamos y registramos un objeto receptor de los eventos de
    // la ventana, para recoger el evento de cierre del Frame y
    // concluir la ejecucion de la aplicacion al recibirlo
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Clase para recibir los eventos ItemListener generados por el objeto
// Choice de la aplicacion
class MiItemListener implements ItemListener {
  Choice oChoice;

  MiItemListener( Choice choice ) {
    // Guardamos una referencia al objeto Choice
    oChoice = choice;
    }

  // Sobreescribimos el metodo itemStateChanged() del interfaz del
  // ItemListener
  public void itemStateChanged( ItemEvent evt ) {
    System.out.println( oChoice.getSelectedItem() );
    }
  }


// Concluye la ejecucion de la aplicacion cuando el usuario cierra la
// ventana, porque se genera un evento windowClosing
class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1302.java