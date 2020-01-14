//
//  java1324.java
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
//     Creacion: 06-May-1998  12:41:23
//     Revision: 05-Feb-2002  06:00:45
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utiliza un BorderLayout y se ha incorporado un
 * objeto receptor de eventos de ventana para que concluya la
 * ejecucion de la aplicacion cuando se pulse el boton de cierre
 * de la ventana
 */
import java.awt.*;
import java.awt.event.*;

public class java1324 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    // Se instancia un objeto BorderLayout con una holgura en vertical y
    // horizontal de 3 pixels
    BorderLayout miBorderLayout = new BorderLayout( 3,3 );
    // Se fija este BorderLayout para que sea el controlador de
    // posicionamiento de componentes para el objeto Frame
    miFrame.setLayout( miBorderLayout );

    // Se instancian cinco objetos Button, para indicar los
    // posicionamientos del BorderLayout
    Button boton1 = new Button( "Sur" );
    Button boton2 = new Button( "Oeste" );
    Button boton3 = new Button( "Norte" );
    Button boton4 = new Button( "Este" );
    Button boton5 = new Button( "Centro" );

    // Se añaden los cinco botones al Frame en las mismas posiciones
    // que vienen dadas por las etiquetas que se les han asignado en
    // el constructor
    miFrame.add( boton1,"South" );
    miFrame.add( boton2,"West" );
    miFrame.add( boton3,"North" );
    miFrame.add( boton4,"East" );
    miFrame.add( boton5,"Center" );

    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia un objeto receptor de eventos de tipo action y
    // lo registra para los cinco botones que se han añadido al
    // objeto Frame
    MiReceptorAction miReceptorAction =
    new MiReceptorAction( miBorderLayout,miFrame );
    boton1.addActionListener( miReceptorAction );
    boton2.addActionListener( miReceptorAction );
    boton3.addActionListener( miReceptorAction );
    boton4.addActionListener( miReceptorAction );
    boton5.addActionListener( miReceptorAction );

    // Se instancia y registra un receptor de eventos de ventana
    // para terminar la ejecucion del programa cuando se cierre
    // el Frame
    miFrame.addWindowListener( new Conclusion() );
    }
  }


class MiReceptorAction implements ActionListener {
  BorderLayout miObjBorderLayout;
  Frame miObjFrame;

  MiReceptorAction( BorderLayout layout,Frame frame ) {
    miObjBorderLayout = layout;
    miObjFrame = frame;
    }

  // Cuando sucede un evento Action, se incrementa el espacio que
  // que hay entre los componentes en el objeto BorderLayout.
  // Luego se fija el controlador de posicionamiento al nuevo
  // que se construye, y luego se valida el Frame para asegurar
  // que se actualiza en la pantalla
  public void actionPerformed( ActionEvent evt ) {
    miObjBorderLayout.setHgap( miObjBorderLayout.getHgap()+5 );
    miObjBorderLayout.setVgap( miObjBorderLayout.getVgap()+5 );
    miObjFrame.setLayout( miObjBorderLayout );
    miObjFrame.validate();
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Termina el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1324.java