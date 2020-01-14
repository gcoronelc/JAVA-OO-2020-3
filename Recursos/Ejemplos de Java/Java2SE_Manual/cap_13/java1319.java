//
//  java1319.java
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
//     Creacion: 02-May-1998  15:47:11
//     Revision: 05-Feb-2002  05:54:54
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utilizan elementos de menus de seleccion para
 * contruir objetos de menu de tipo PopUp
 */
import java.awt.*;
import java.awt.event.*;

public class java1319 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Instancia objetos CheckboxMenuItem
    CheckboxMenuItem primerElementoMenu =
    new CheckboxMenuItem( "Primer Elemento" );
    CheckboxMenuItem segundoElementoMenu =
    new CheckboxMenuItem( "Segundo Elemento" );
    CheckboxMenuItem tercerElementoMenu =
    new CheckboxMenuItem( "Tercer Elemento" );

    // Se instancia un objeto ItemListener y se registra sobre los
    // elementos de menu ya instanciados
    primerElementoMenu.addItemListener( new ControladorCheckBox() );
    segundoElementoMenu.addItemListener( new ControladorCheckBox() );
    tercerElementoMenu.addItemListener( new ControladorCheckBox() );

    // Instancia un objeto Menu de tipo PopUp y le añade los objetos
    // CheckboxMenuItem
    PopupMenu miMenuPopup = new PopupMenu( "Menu Popup" );
    miMenuPopup.add( primerElementoMenu );
    miMenuPopup.add( segundoElementoMenu );
    miMenuPopup.add( tercerElementoMenu );

    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.addMouseListener( new ControladorRaton( miFrame,miMenuPopup ) );
    // Aqui esta la diferencia con los Menus de Barra
    miFrame.add( miMenuPopup );
    miFrame.setSize( 250,100 );
    miFrame.setVisible( true );

    // Instancia y registra un receptor de eventos de ventana para
    // terminar el programa cuando se cierra el Frame
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Clase para atrapar los eventos de pulsacion del raton y presentar
// en la pantalla el objeto menu Popup, en la posicion en que se
// encontraba el cursor
class ControladorRaton extends MouseAdapter {
  Frame aFrame;
  PopupMenu aMenuPopup;

  // Constructor parametrizado
  ControladorRaton( Frame frame,PopupMenu menuPopup ) {
    aFrame = frame;
    aMenuPopup = menuPopup;
    }

  public void mousePressed( MouseEvent evt ) {
    // Presenta el menu PopUp sobre el Frame que se especifique
    // y en las coordenadas determinadas por el click del raton,
    // cuidando de que las coordenadas no se encuentren situadas
    // sobre la barra de titulo, porque las coordenadas Y en
    // esta zona son negativas
    if ( evt.getY() > 0 )
      aMenuPopup.show( aFrame,evt.getX(),evt.getY() );
    }
  }


// Clase para instanciar un objeto receptor de eventos de los
// elementos del menu que sera registrado sobre estos elementos
class ControladorCheckBox implements ItemListener {
  public void itemStateChanged( ItemEvent evt ) {
    // Presenta en pantalla el elemento que ha generado el
    // evento
    System.out.println( evt.getSource() );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // termina el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1319.java