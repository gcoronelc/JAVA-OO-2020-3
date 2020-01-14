//
//  java1306.java
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
//     Creacion: 07-Mar-1998  11:33:12
//     Revision: 05-Feb-2002  05:40:23
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase TextArea.
 * Coloca un objeto TextArea sobre un Frame, inicializándolo con varias
 * líneas de texto. Cuando se genera un evento sobre el área de texto,
 * pulsando la tecla RETORNO, en la consola se indica cuál es el texto
 * que contine el objeto
 */
import java.awt.*;
import java.awt.event.*;

public class java1306 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Instancia un objeto TextArea, con una barra de desplazamiento
    // vertical y lo inicializa con diez líneas de texto
    TextArea miAreaTexto = new TextArea( "",5,20,
      TextArea.SCROLLBARS_VERTICAL_ONLY );
    for ( int i=0; i < 10; i++ )
      miAreaTexto.append( "linea "+i+"\n" );

    // Instancia y registra un receptor de eventos de tipo Text
    // sobre el area de texto
    miAreaTexto.addTextListener(new MiTextListener( miAreaTexto ) );

    // Coloca el área de texto sobre el objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( miAreaTexto );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia y registra un objeto receptor de eventos de ventana
    // para concluir la ejecucion del programa cuando el Frame se
    // cierres por accion del usuario sobre el
    miFrame.addWindowListener( new Conclusion() );
    }
  }

// Clase para recibir los eventos de tipo Text que se produzcan
// sobre el objeto TextArea sobre el cual se encuentra registrado
class MiTextListener implements TextListener {
  TextArea oAreaTexto;

  MiTextListener( TextArea iAreaTexto ) {
    // Guarda una referencia al objeto TextArea
    oAreaTexto = iAreaTexto;
    }

  // Se sobrescribe el método textValueChanged() del interfaz
  // TextListener para que indique en la consola el texto que
  // ocupa el área de texto cuando se cambie
  public void textValueChanged( TextEvent evt ) {
    System.out.println( oAreaTexto.getText() );
    }
  }

class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1306.java