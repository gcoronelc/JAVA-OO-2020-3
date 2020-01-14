//
//  java1115.java
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
//     Creacion: 06-Jan-1998  19:24:56
//     Revision: 03-Feb-2002  12:25:38
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;

public class java1115 extends Frame {
  public static void main( String args[] ) {
    java1115 displayWindow = new java1115();
    }

  public java1115() {
    setTitle( "Tutorial de Java, Eventos" );
    setLayout( new FlowLayout() );

    Button miBoton = new Button( "Pulsar" );
    Label miColorLabel = new Label( "  Muestra  " );
    Label miClickLabel = new Label( "Pulsar" );

    // Se añaden los componentes al Frame
    add( miBoton );
    add( miColorLabel );
    add( miClickLabel );

    // Se fija el tamaño del Frame y se presenta
    setSize( 250,100 );
    setVisible( true );

    // Se registran los objetos receptores de eventos
    miClickLabel.addMouseListener( new MiMouseListener( miBoton ) );
    miBoton.addActionListener( new MiActionListener( miColorLabel ) );
    // Concluye la aplicacion cuando se cierra la ventana
    this.addWindowListener( new Conclusion() );
    }
  }


// Esta clase MouseListener se utiliza para monitorizar los clicks del
// raton sobre un objeto de tipo Label. Cada vez que el usuario pulsa
// sobre la etiqueta, el codigo del objeto de esta clase crea un objeto
// ActionEvent y lo coloca en la cola de eventos del sistema. El origen
// del evento se especifica para que sea un objeto Button, que se pasa
// cuando se instancia un objeto de esta clase.
// De este modo, el objeto Label simula ser un Boton y genera eventos
// de tipo ActionEvent, que son interpretados por el sistema runtime
// como si estuviesen originados en un verdadero boton. El tipo de
// ActionEvents generados son eventos ACTION_PERFORMED, que son
// enviados automaticamente por el metodo actionPerformed() de un
// objeto ActionListener registrado sobre el boton
class MiMouseListener extends MouseAdapter {
  Button miBoton;

  MiMouseListener( Button boton ) {
    miBoton = boton;
    }

  // Sobreescribe el metodo mouseClicked()
  public void mouseClicked( MouseEvent evt ) {
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
      new ActionEvent( miBoton,
      ActionEvent.ACTION_PERFORMED,"evento" ) );
    }
  }


// Esta clase ActionListener se utiliza para instanciar un objeto
// Listener para el objeto Button. Cadavez que el boton sea pulsado,
// se envia un evento de tipo ActionEvent con el Boton como origen.
// El codigo de un objeto de esta clase va a cambiar el color de
// fondo de un objeto Label, intercambiandolo entre amarillo y
// azul
class MiActionListener implements ActionListener {
  int cambio = 0;
  Label miLabel;

  MiActionListener (Label label ) {
    miLabel = label;
    }

  public void actionPerformed( ActionEvent evt ) {
    if ( cambio == 0 ) {
      cambio = 1;
      miLabel.setBackground( Color.yellow );
      }
    else {
      cambio = 0;
      miLabel.setBackground( Color.blue );
      }
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Termina la ejecucion del programa cuando se cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1115.java