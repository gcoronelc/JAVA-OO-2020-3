//
//  java1307.java
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
//     Creacion: 07-Mar-1998  12:26:34
//     Revision: 05-Feb-2002  05:41:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase Label.
 * Coloca un objeto Label sobre un Frame, apareciendo en él el texto
 * "Texto inicial". La etiqueta tiene las propiedades de sólo-lectura y
 * no genera eventos
 */
import java.awt.*;
import java.awt.event.*;

public class java1307 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM(){
    // Instancia un objeto Label con una cadena para inicializarlo y
    // que aparezca como contenido en el momento de su creación
    Label miEtiqueta = new Label( "Texto inicial" );

    // Coloca la eqtiqueta sobre el objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( miEtiqueta );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia y registra un objeto receptor de eventos de ventana
    // para concluir la ejecucion del programa cuando el Frame se
    // cierres por accion del usuario sobre el
    miFrame.addWindowListener( new Conclusion() );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye el programa cuando se cierra la ventana
    System.exit(0);
    }
  }

//------------------------------------------ Final del fichero java1307.java