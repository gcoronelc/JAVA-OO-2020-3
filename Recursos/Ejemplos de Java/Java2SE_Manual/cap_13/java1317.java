//
//  java1317.java
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
//     Creacion: 02-May-1998  17:50:03
//     Revision: 05-Feb-2002  05:52:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es para mostrar la forma de implementacion de un
 * menu y de un acelerador de teclado
 */
import java.awt.*;
import java.awt.event.*;

public class java1317 {
  public static void main(String args[]){
    IHM ihm = new IHM();
  }
}


class IHM {
  public IHM() {
    // Se instancia un objeto de tipo Acelerador de Teclado
    MenuShortcut miAcelerador =
    new MenuShortcut( KeyEvent.VK_K,true );

    // Se instancian varios objetos de tipo Elementos de Menu
    MenuItem primerElementoDeA = new MenuItem(
                                             "Primer Elemento del Menu A",miAcelerador );
    MenuItem segundoElementoDeA = new MenuItem(
                                              "Segundo Elemento del Menu A" );
    MenuItem primerElementoDeB = new MenuItem(
                                             "Primer Elemento del Menu B" );
    MenuItem segundoElementoDeB = new MenuItem(
                                              "Segundo Elemento del Menu B" );
    MenuItem tercerElementoDeB = new MenuItem(
                                             "Tercer Elemento del Menu B" );

    // Se instancia un objeto ActionListener y se registra sobre los
    // objetos MenuItem
    primerElementoDeA.addActionListener( new MiGestorDeMenu() );
    segundoElementoDeA.addActionListener( new MiGestorDeMenu() );
    primerElementoDeB.addActionListener( new MiGestorDeMenu() );
    segundoElementoDeB.addActionListener( new MiGestorDeMenu() );
    tercerElementoDeB.addActionListener( new MiGestorDeMenu() );

    // Se instancian dos objetos de tipo Menu y se les añaden los
    // objetos MenuItem
    Menu menuA = new Menu( "Menu A" );
    menuA.add( primerElementoDeA );
    menuA.add( segundoElementoDeA );

    Menu menuB = new Menu( "Menu B" );
    menuB.add( primerElementoDeB );
    menuB.add( segundoElementoDeB );
    menuB.add( tercerElementoDeB );

    // Se instancia una Barra de Menu y se le añaden los Menus
    MenuBar menuBar = new MenuBar();
    menuBar.add( menuA );
    menuBar.add( menuB );

    // Se instancia un objeto Frame y se le asocia el objeto MenuBar.
    // Observese que esta no es la tipico invocacion del metodo
    // miFrame.add(), sino que es una forma especial de invocar
    // al metodo necesaria para poder asociar un objeto Barra de Menu
    // a un objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    // Esto no es el metodo add(), como se podria esperar
    miFrame.setMenuBar( menuBar );
    miFrame.setSize( 250,100 );
    miFrame.setVisible( true );

    // Se instancia y registra un receptor de eventos de ventana para
    // concluir el programa cuando se cierre el Farme
    miFrame.addWindowListener( new Conclusion() );
  }
}


// Clase para instanciar un objeto ActionListener que se registra
// sobre los elementos del menu
class MiGestorDeMenu implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    // Presenta en pantalla el elemento que ha generado el evento
    // de tipo Action
    System.out.println( evt.getSource() );
  }
}


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
  }
}

//------------------------------------------ Final del fichero java1317.java
