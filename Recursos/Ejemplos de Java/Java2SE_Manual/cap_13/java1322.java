//
//  java1322.java
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
//     Creacion: 05-May-1998  13:21:11
//     Revision: 05-Feb-2002  05:57:53
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utiliza un FlowLayout y se ha incorporado un
 * objeto receptor de eventos de ventana para que concluya la
 * ejecucion de la aplicacion cuando se pulse el boton de cierre
 * de la ventana
 */
import java.awt.*;
import java.awt.event.*;

public class java1322 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    // Instancia un objeto FlowLayout object alieado al Centro
    // y con una separacion de 3 pixels en horizonal y vertical
    FlowLayout miFlowLayout = new FlowLayout( FlowLayout.CENTER,3,3 );

    // Se fija este FlowLayout para que sea el controlador de
    // posicionamiento de componentes para el objeto Frame
    miFrame.setLayout( miFlowLayout );

    // Se instancian cinco objetos Button, para indicar los
    // posicionamientos del FlowLayout
    Button boton1 = new Button( "Primero" );
    Button boton2 = new Button( "Segundo" );
    Button boton3 = new Button( "Tercero" );
    Button boton4 = new Button( "Cuarto" );
    Button boton5 = new Button( "Quinto" );

    // Se añaden los cinco botones al Frame en las mismas posiciones
    // que vienen dadas por las etiquetas que se les han asignado en
    // el constructor
    miFrame.add( boton1 );
    miFrame.add( boton2 );
    miFrame.add( boton3 );
    miFrame.add( boton4 );
    miFrame.add( boton5 );

    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Instancia un objeto receptor de eventos de tipo action y
    // lo registra para los cinco botones que se han añadido al
    // objeto Frame
    MiReceptorAction miReceptorAction =
    new MiReceptorAction( miFlowLayout,miFrame );
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
  FlowLayout miObjLayout;
  Frame miObjFrame;

  MiReceptorAction( FlowLayout layout,Frame frame ) {
    miObjLayout = layout;
    miObjFrame = frame;
    }

  // Cuando sucede un evento Action, se incrementa el espacio que
  // que hay entre los componentes en el objeto FlowLayout.
  // Luego se fija el controlador de posicionamiento al nuevo
  // que se construye, y luego se valida el Frame para asegurar
  // que se actualiza en la pantalla
  public void actionPerformed( ActionEvent evt ){
    miObjLayout.setHgap( miObjLayout.getHgap()+5 );
    miObjLayout.setVgap( miObjLayout.getVgap()+5 );
    miObjFrame.setLayout( miObjLayout );
    miObjFrame.validate();
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Termina el programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1322.java