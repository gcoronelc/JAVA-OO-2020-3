//
//  java1116.java
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
//     Creacion: 08-Jun-1998  13:18.22
//     Revision: 03-Feb-2002  12:27:40
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de fuentes de eventos, receptores de eventos
 * y adaptadores en el modelo de Delegacion de Eventos introducido por
 * Sun en el JDK 1.1, para comprobar que no se diferencia, aparentemente,
 * en nada respecto a los Componentes que introduce Swing.
 * La conversión a partir del ejemplo java1101.java, no involucra más que
 * alguna cuestión de maquillaje como es sustituir "Frame" por "JFrame", y
 * algún añadido, como es la sentencia "import" para que se puedan
 * localizar las clases en la librería de Swing en tiempo de compilación
 * y en tiempo de ejecución.
 * La aplicacion instacia un objeto que crea un interfaz de usuario que
 * consta de un componente Swing que es un JFrame. Este objeto es la Fuente
 * de Eventos que notifica a dos Receptores diferentes los eventos que se
 * producen en la ventana.
 * Uno de los receptores de eventos implementa el interfaz WindowListener
 * y define todos los metodos declarados en ese interfaz.
 * El otro objeto receptor extiende la clase adaptadora WindowAdapter, y
 * solamente sobreescribe dos de los metodos, ya que la clase Adapter
 * sobreescribe los restantes con metodos vacios.
 * La aplicacion no termina por si sola, hay que forzarla a concluir.
 */
import java.awt.*;
import java.awt.event.*;      // Este es el paquete de eventos del JDK 1.1
import javax.swing.*;         // Este es el paquete de Swing

public class java1116 {
  public static void main( String args[] ) {
    // Aqui se instancia un objeto de tipo Interfaz Hombre-Maquina
    IHM ihm = new IHM();
    }
  }


// Esta clase se utiliza para instaciar un objeto de tipo interfaz de
// usuario, para que permita instanciar a su vez dos objetos Listener
// y registrarlos para que reciban notificacion cuando se producen
// eventos en una Ventana
class IHM {
  // Constructor de la clase
  public IHM() {
    // Se crea un objeto JFrame
    JFrame ventana = new JFrame();

    // El metodo setSize() reemplaza al metodo resize() del JDK 1.0
    ventana.setSize( 300,200 );
    ventana.setTitle( "Tutorial de Java, Eventos" );
    // El metodo setVisible() reemplaza al metodo show() del JDK 1.0
    ventana.setVisible( true );

    // Se instancian dos objetos receptores que procesaran los
    // eventos de la ventana
    Proceso1 ventanaProceso1 = new Proceso1( ventana );
    Proceso2 ventanaProceso2 = new Proceso2();

    // Se registran los dos objetos receptores para que sean
    // notificados de los evetnos que genere la ventana, que es el
    // objeto origen de los eventos
    ventana.addWindowListener( ventanaProceso1 );
    ventana.addWindowListener( ventanaProceso2 );
    }
  }


// Las dos clases siguientes se pueden utilizar para instanciar los
// objetos receptor. Esta clase implementa el interfaz WindowListener,
// lo cual requiere que todos los metodos que estan declarados en el
// interfaz sean definidos en la clase.
// La clase define todos esos metodos y presenta un mensaje
// descriptivo cada vez que se invoca a uno de ellos.
class Proceso1 implements WindowListener {
  // Variable utilizada para guardar una referencia al objeto JFrame
  JFrame ventanaRef;

  // Constructor que guarda la referencia al objeto JFrame
  Proceso1( JFrame vent ){
    this.ventanaRef = vent;
    }

  public void windowClosed( WindowEvent evt ) {
    System.out.println( "Metodo windowClosed de Proceso1" );
    }

  public void windowIconified( WindowEvent evt ) {
    System.out.println( "Metodo windowIconified de Proceso1" );
    }

  public void windowOpened( WindowEvent evt ) {
    System.out.println( "Metodo windowOpened de Proceso1" );
    }

  public void windowClosing( WindowEvent evt ) {
    System.out.println( "Metodo windowClosing de Proceso1" );
    // Se oculta la ventana
    ventanaRef.setVisible( false );
    }

  public void windowDeiconified( WindowEvent evt ) {
    System.out.println( "Metodo windowDeiconified Proceso1" );
    }

  public void windowActivated( WindowEvent evt ) {
    System.out.println( "Metodo windowActivated de Proceso1" );
    }

  public void windowDeactivated( WindowEvent evt ) {
    System.out.println( "Metodo windowDeactivated de Proceso1" );
    }
  }


// Esta clase y la anterior se pueden utilizar para instanciar
// objetos Listener. En esta clase, se extiende la clase Adapter
// obvienado el requerimiento de tener que definir todos los
// metodos del receptor de eventos WindowListener. El objeto
// Adapter, WindowAdapter extiende a WindowListener y define
// todos los metodos con codigo vacio, que pueden ser sobreescritos
// siempre que se desee. En este clase concreta, solamente se
// sobreescriben dos de los metodos declarados en el interfaz, y
// presenta un mensaje cada vez que se invoca a uno de ellos
class Proceso2 extends WindowAdapter {
  public void windowIconified( WindowEvent evt ) {
    System.out.println( "--- Metodo windowIconified de Proceso2" );
    }

  public void windowDeiconified( WindowEvent evt ) {
    System.out.println( "---Metodo windowDeiconified de Proceso2" );
    }
  }

//------------------------------------------ Final del fichero java1116.java