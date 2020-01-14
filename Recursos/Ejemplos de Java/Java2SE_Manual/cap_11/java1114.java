//
//  java1114.java
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
//     Creacion: 06-Jan-1998  18:25:59
//     Revision: 03-Feb-2002  12:24:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo demuestra el uso del metodo postEvent() para
 * colocar eventos en la cola de eventos del sistema, EventQueue
 */
import java.awt.*;
import java.awt.event.*;

public class java1114 extends Frame {
  public static void main( String args[] ) {
    java1114 ventana = new java1114();
    }

  public java1114() {
    MiComponente miComponente = new MiComponente();
    this.add( miComponente );

    setTitle( "Tutorial de Java, Eventos" );
    setSize( 250,100 );
    setVisible( true );

    // El siguiente objeto KeyListener, cuando recibe un evento del
    // teclado lo convierte en un evento de raton
    miComponente.addKeyListener( new MiKeyListener( miComponente ) );
    // Cuando se cierre el Frame, se concluye la aplicacion
    this.addWindowListener( new Conclusion() );
    }
  }


// Esta clase recibe los eventos del teclado sobre los componentes
// que se han creado. Cuando se atrapa un evento de teclado, el
// codigo del metodo keyPressed() presenta el caracter que
// corresponde a esa tecla (siempre que se pueda, porque hay
// teclas que no tienen un caracter visible asociado).
// Tambien crea un evento de raton artificail y lo coloca en
// la cola de eventos indicando que ha sido el mismo componente
// el que lo ha generado. De este modo, los objetos Receptores
// de este tipo convierten los eventos de teclado en eventos de
// raton
class MiKeyListener extends KeyAdapter {
  // Referencia al componente creado
  MiComponente miComponente;

  // Constructor parametrizado
  MiKeyListener( MiComponente miComp ) {
    // Guarda una referencia a nuestro componente
    miComponente = miComp;
    }

  // Metodo sobreescrito de pulsacion del teclado
  public void keyPressed( KeyEvent evt ) {
    System.out.println(
      "Metodo keyPressed(), tecla pulsada -> " + evt.getKeyChar() );

    // El parametro de identificacion en la construccion del
    // evento de raton debe ser un identificador de evento de
    // raton valido. Este evento se construye con las coordenadas
    // x e y a -1, para hacer que sea mas identificable. La
    // referencia al componente que se ha creado es el primer
    // parametro del constructor.
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
      new MouseEvent( miComponente,MouseEvent.MOUSE_CLICKED,
      0,0,-1,-1,2,false ) );
    }
  }


// En esta clase se crea un nuevo componente extendiendo un Label.
// Puede responder a eventos del teclado si se registra un
// objeto KeyListener adecuado sobre el. Sobreescribe el metodo
// processMouseEvent() para poder capturar y presentar en pantalla
// objetos MouseEvent creados y enviados por el objeto KeyListener
// con una referencia aun objeto de este tipo como el primer
// parametro del constructor del MouseEvent
class MiComponente extends Label {
  MiComponente() {
    this.setText( "Mi Componente" );
    // La siguiente sentencia es necesaria para provocar que
    // el metodo processMouseEvent() sea invocado siempre que
    // se encole un evento de raton para un objeto de este tipo
    enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }

  public void processMouseEvent( MouseEvent evt ) {
    // Se indica que se ha invocado a este metodo y se presenta
    // el identificador y las coordenadas del objeto MouseEvent
    // que se haya pasado como parametro
    System.out.println(
      "Metodo processMouseEvent(), MiComponente ID = " +
      evt.getID() + " " + evt.getPoint() );

    // SIEMPRE hay que hacer esto si se sobreescribe el metodo
    super.processMouseEvent( evt );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye la ejecucion del programa cuando se cierre la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1114.java
