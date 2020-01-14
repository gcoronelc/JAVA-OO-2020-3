//
//  java1318.java
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
//     Creacion: 02-May-1998  09:32:56
//     Revision: 05-Feb-2002  05:53:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa ilustra la utilizacion de menus de cajas de
 * comprobacion
 */
import java.awt.*;
import java.awt.event.*;

public class java1318 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Instancia objetos de tipo CheckboxMenuItem
    CheckboxMenuItem primerElementoMenu =
    new CheckboxMenuItem( "Primer Elemento" );
    CheckboxMenuItem segundoElementoMenu =
    new CheckboxMenuItem( "Segundo Elemento" );
    CheckboxMenuItem tercerElementoMenu =
    new CheckboxMenuItem( "Tercer Elemento" );

    // Instancia un objeto ItemListener y lo registra sobre los
    // objetos CheckboxMenuItem, elementos del menu de seleccion
    primerElementoMenu.addItemListener( new ControladorCheckBox() );
    segundoElementoMenu.addItemListener( new ControladorCheckBox() );
    tercerElementoMenu.addItemListener( new ControladorCheckBox() );

    // Instancia un objeto Menu y le añade los botones de la caja
    // de seleccion
    Menu menuA = new Menu( "Menu A" );
    menuA.add( primerElementoMenu );
    menuA.add( segundoElementoMenu );
    menuA.add( tercerElementoMenu );

    // Instancia un objeto MenuBar y le añade el objeto Menu
    MenuBar barraMenu = new MenuBar();
    barraMenu.add( menuA );

    // Se instancia un objeto Frame y se le asocia el objeto MenuBar.
    // Observese que esta no es la tipico invocacion del metodo
    // miFrame.add(), sino que es una forma especial de invocar
    // al metodo necesaria para poder asociar un objeto Barra de Menu
    // a un objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );

    // Esto no es el metodo add(), como se podria esperar
    miFrame.setMenuBar( barraMenu );
    miFrame.setSize( 250,100 );
    miFrame.setVisible( true );

    // Instancia y registra un receptor de eventos de ventana para
    // concluir la ejecucion del programa cuando se cierra el Frame
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Clase para instanciar un objeto ItemListener y registrarlo
// sobre los elementos del menu
class ControladorCheckBox implements ItemListener {
  public void itemStateChanged( ItemEvent evt ) {
    // Presenta en pantalla el elemento del menu que ha
    // generado el evento
    System.out.println( evt.getSource() );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Termina el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1318.java