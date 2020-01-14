//
//  java1305.java
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
//     Creacion: 07-Mar-1998  11:21:03
//     Revision: 05-Feb-2002  05:39:27
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase TextField.
 * Coloca un objeto TextField sobre un Frame, apareciendo en él el texto
 * "Texto inicial". Permite la edición y selección de texto, de forma
 * que cuando se genera un evento sobre el campo de texto, pulsando la
 * tecla RETORNO, en la consola se indica cual es el texto completo
 * introducido y qué parte de él se encuentra seleccionada
 */
import java.awt.*;
import java.awt.event.*;

public class java1305 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Instancia un objeto TextField y coloca una cadena como
    // Texto para que aparezca en el momento de su creación
    TextField miCampoTexto = new TextField( "Texto inicial" );

    // Instancia y registra un receptor de eventos de tipo Action
    // sobre el campo de texto
    miCampoTexto.addActionListener(new MiActionListener( miCampoTexto ) );

    // Coloca el campo de texto sobre el objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( miCampoTexto );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia y registra un objeto receptor de eventos de ventana
    // para concluir la ejecucion del programa cuando el Frame se
    // cierres por accion del usuario sobre el
    miFrame.addWindowListener( new Conclusion() );
    }
  }

// Clase para recibir los eventos de tipo Action que se produzcan
// sobre el objeto TextField sobre el cual se encuentra registrado
class MiActionListener implements ActionListener {
  TextField oCampoTexto;

  MiActionListener( TextField iCampoTexto ) {
    // Guarda una referencia al objeto TextField
    oCampoTexto = iCampoTexto;
    }

  // Se sobrescribe el método actionPerformed() del interfaz
  // ActionListener para que indique en la consola el texto que
  // se introduce
  public void actionPerformed( ActionEvent evt ) {
    System.out.println( "Texto seleccionado: " +
      oCampoTexto.getSelectedText() );
    System.out.println( "Texto completo: " +
      oCampoTexto.getText() );
    }
  }

class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1305.java