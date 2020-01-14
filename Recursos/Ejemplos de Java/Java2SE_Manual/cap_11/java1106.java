//
//  java1106.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 01-Oct-1997  18:33:54
//     Revision: 03-Feb-2002  12:19:29
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra el manejo de los eventos de bajo-nivel y eventos
 * semanticos.
 * En los comentarios que preceden a cada una de las clases se explica
 * lo que hace cada una de ellas y como manejan los eventos
 */
import java.awt.*;
import java.awt.event.*;

public class java1106 {
  public static void main( String args[] ) {
    // Aqui se instancia un objeto de tipo Interfaz Hombre-Maquina
    IHM ihm = new IHM();
    }
  }


// Esta clase se utiliza para instaciar un objeto de tipo interfaz de
// usuario
class IHM {
  public IHM() {
    // Crea un objeto visual Campo de Texto (TextField)
    TextField miTexto = new TextField( "Cadena Inicial" );
    miTexto.setName( "CampoTexto" );

    // Crea un objeto visual Boton (Button)
    Button miBoton = new Button( "Púlsame" );
    miBoton.setName( "Boton" );

    // Crea un objeto visual Frame
    Frame miFrame = new Frame();
    miFrame.setSize( 300,200 );
    miFrame.setTitle( "Tutorial de Java, Eventos" );
    miFrame.setName( "Frame" );

    // Incorpora el Campo de Texto y el Boton al objeto de tipo Frame
    miFrame.add( "North",miBoton );
    miFrame.add( "South",miTexto );
    miFrame.setVisible( true );

    // Se instancia y registra un objeto ActionListener que
    // monitorizara todos los eventos de acciones que tengan
    // su origen en el Campo de Texto y en el Boton
    ProcesoAccion procesoAccion = new ProcesoAccion();
    miTexto.addActionListener( procesoAccion );
    miBoton.addActionListener( procesoAccion );

    // Se instancia y registra un objeto FocusListener que
    // monitorizara todos los eventos producidos por cambios
    // en el foco que tengan su origen en el Campo de Texto y
    // en el Boton
    ProcesoFoco procesoFoco = new ProcesoFoco();
    miTexto.addFocusListener( procesoFoco );
    miBoton.addFocusListener( procesoFoco );

    // Se instancia y registra un objeto MouserListener que procesara
    // todos los eventos del raton que se produzcan o sean generados
    // por los objetos Frame, Button y TextField
    ProcesoRaton procesoRaton = new ProcesoRaton();
    miFrame.addMouseListener( procesoRaton );
    miTexto.addMouseListener( procesoRaton );
    miBoton.addMouseListener( procesoRaton );

    // Se instancia y registra un objeto receptor de eventos
    // para terminar la ejecucion del programa cuando el
    // usuario decida cerrar la ventana
    Proceso1 procesoVentana1 = new Proceso1();
    miFrame.addWindowListener( procesoVentana1 );
    }
  }


// Receptor de eventos Semanticos.
// Esta clase ActionListener se utiliza para instanciar un objeto
// Receptor que monitorice todos los eventos Action que se generen en
// los objetos TextField y Button. Cuando se produce un evento de
// tipo actionPreformed(), se presenta en pantalla el ActionCommand
// y la identificacion del componente que ha generado el evento.
// El objeto receptor distingue entre los componentes que de envian
// eventos sobre la base del nombre que se ha asignado a cada uno
// de los objetos y que se encuentra embebido en el objeto que es
// pasado como parametro cuando se produce el evento
class ProcesoAccion implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    System.out.println( "evt.getActionCommand() = " +
      evt.getActionCommand() );

    if( evt.toString().indexOf("on CampoTexto") != -1 ) {
      System.out.println(
        "Capturado actionPerformed sobre el objeto CampoTexto" );
      }
    if( evt.toString().indexOf("on Boton") != -1 ) {
      System.out.println(
        "Capturado actionPerformed sobre el objeto Boton" );
      }
    }
  }


// Receptor de eventos de Bajo-Nivel.
// Esta clase FocusListener se utiliza para instanciar un objeto
// Receptor que monitorice los eventos relacionados con el foco
// que se generen en los objetos TextField y Button. Cuando se
// produce un evento de tipo focusLost() o focusGained(), se
// presenta en pantalla la identificacion del componente que ha
// generado el evento. El objeto receptor distingue entre los
// componentes que de envian eventos sobre la base del nombre que
// se ha asignado a cada uno de los objetos y que se encuentra
// embebido en el objeto que es pasado como parametro cuando se
// produce el evento
class ProcesoFoco implements FocusListener{
  public void focusGained( FocusEvent evt ) {
    if( evt.toString().indexOf("on CampoTexto") != -1 ) {
      System.out.println(
        "Capturado focusGained sobre el objeto CampoTexto" );
      }
    if( evt.toString().indexOf("on Boton") != -1 ) {
      System.out.println(
        "Capturado focusGained sobre el objeto Boton" );
      }
    }

  public void focusLost( FocusEvent evt ) {
    if( evt.toString().indexOf("on CampoTexto") != -1 ) {
      System.out.println(
        "Capturado focusLost sobre el objeto CampoTexto" );
      }
    if( evt.toString().indexOf("on Boton") != -1 ) {
      System.out.println(
        "Capturado focusLost sobre el objeto Boton" );
      }
    }
  }


// Receptor de eventos de Bajo-Nivel.
// Esta clase receptor se utiliza para monitorizor los eventos
// de pulsacion de los botones del raton sobre el objeto Frame,
// el objeto Button y el objeto TextField. El mensaje indentifica
// el componente que ha generado el evento.El receptor distingue
// entre los componentes que de envian eventos sobre la base del
// nombre que se ha asignado a cada uno de los objetos y que se
// encuentra embebido en el objeto que es pasado como parametro
// cuando se produce el evento
class ProcesoRaton extends MouseAdapter {
  public void mousePressed( MouseEvent evt ) {
    if( evt.toString().indexOf("on Frame") != -1 ) {
      System.out.println(
        "Capturado mousePressed sobre el objeto Frame" );
      }
    if( evt.toString().indexOf("on CampoTexto") != -1 ) {
      System.out.println(
        "Capturado mousePressed sobre el objeto CampoTexto" );
      }
    if( evt.toString().indexOf("on Boton") != -1 ) {
      System.out.println(
        "Capturado mousePressed sobre el objeto Boton" );
      }
    }
  }


// Receptor de eventos de Bajo-Nivel.
// Este repector de eventos de la ventana se utiliza para concluir
// la ejecucion del programa cuando el usuario pulsa sobre el boton
// de cierre del Frame
class Proceso1 extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1106.java