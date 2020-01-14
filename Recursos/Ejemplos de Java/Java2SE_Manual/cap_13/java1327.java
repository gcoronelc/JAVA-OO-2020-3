//
//  java1327.java
//  Copyright (c) 1998,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 06-Jun-1998  09:09:34
//     Revision: 05-Feb-2002  06:02:52
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra un ejemplo de uso del controlador de
 * posicionamiento de componenentes, CardLayout
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class java1327 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Se construye una ficha (card) con componentes del interfaz
    // para ser añadido al conjunto de fichas que utiliza el
    // layout CardLayout
    Label labelFecha = new Label("                                          ");
    Button botonFecha = new Button( "Fecha y Hora" );
    Panel panelFecha = new Panel();
    panelFecha.add( botonFecha );
    panelFecha.add( labelFecha );

    // Se instancia y registra un objeto ActionListener sobre el
    // boton que va a presentar la fecha y la hora
    botonFecha.addActionListener(
      new ActionListernerFecha( labelFecha ) );

    // Se contruye una zona de visualizacion para las fichas
    // Se instancia un objeto alyout par ser usado con el Panel
    CardLayout miCardLayout = new CardLayout();

    // Se instancia el Panel de visualizacion que sera incorporado
    // al objeto Frame
    Panel panelPresentacion = new Panel();
    // Se fija el Cardlayout para el objeto panel
    panelPresentacion.setLayout( miCardLayout );
    panelPresentacion.setBackground( Color.yellow );

    // Se añaden objetos al panel, utilizanzo el Controlador CardLayout
    // que se ha instanciado antes para posicionar los componentes
    // sobre el
    panelPresentacion.add(
      new Button( "La Primera ficha es un Boton" ),"primero" );
    panelPresentacion.add(
      new Label( "La Segunda ficha es un Label" ),"segundo" );
    panelPresentacion.add(
      new Label( "Tercera ficha, tambien un Label" ),"tercero" );
    panelPresentacion.add(
      new Label( "Cuarta ficha, de nuevo un label" ),"cuarto" );
    panelPresentacion.add( panelFecha,"panel fecha" );
    panelPresentacion.add(
      new TextField( "La Ultima ficha es un campo de texto" ),"sexto" );

    // Se construye el Panel de control
    // Instanciamos los objetos Boton que se utilizaran para
    // interaccionar con las fichas del panel
    Button botonSiguiente = new Button( "Siguiente" );
    Button botonAnterior= new Button( "Anterior" );
    Button botonPrimero= new Button( "Primero" );
    Button botonUltimo= new Button( "Ultimo" );
    Button botonMostrar= new Button( "Panel Fecha" );

    // Instanciamos un objeto receptor de acciones y lo registramos
    // sobre los botones
    botonPrimero.addActionListener(
      new ListenerPrimero( miCardLayout,panelPresentacion ) );
    botonSiguiente.addActionListener(
      new ListenerSiguiente( miCardLayout,panelPresentacion ) );
    botonAnterior.addActionListener(
      new ListenerAnterior( miCardLayout,panelPresentacion ) );
    botonUltimo.addActionListener(
      new ListenerUltimo( miCardLayout,panelPresentacion ) );
    botonMostrar.addActionListener(
      new ListenerMostrar( miCardLayout,panelPresentacion ) );

    // Instanciamos un Panel de control utilizando el FlowLayout por
    // defecto y colocamos los objetos Boton sobre el. Estos botones son
    // totalmente funcionales porque tienen a objetos ActionListener
    // registrados con ellos para que los notifiquen y tomen las
    // oportunas acciones
    Panel panelControl = new Panel();
    panelControl.add( botonPrimero );
    panelControl.add( botonSiguiente );
    panelControl.add( botonAnterior );
    panelControl.add( botonUltimo );
    panelControl.add( botonMostrar );

    // Se instancia el objeto Frame que es el padre principal de todo
    // el arbol de jerarquia de objetos que nos estamos montando para
    // implementar este ejemplo de interfaz de usuario
    // Instanciamos el objeto Frame
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    // Le incorporamos el panel de visualizacion y el panel de control
    // para crear un objeto mas complejo
    miFrame.add( panelPresentacion,"North" );
    miFrame.add( panelControl,"South" );
    // Fijamos el tamaño del Frame
    miFrame.setSize( 500,150 );
    // Y lo hacemos visible
    miFrame.setVisible( true );

    // Finalmente, instanciamos y registramos un objeto receptor de
    // eventos de ventana para concluir la ejecucion del ejemplo cuando
    // se cierre el Frame padre de todo el interfaz
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Esta es la clase ActionListener que instancia un objeto
// sobre el boton del panel del Tiempo que es una de las fichas
// del layout. Cuando se produce un evento, este controlador
// hace que se presenten la fecha y la hora en la pantalla
class ActionListernerFecha implements ActionListener {
  Label miObjLabel;

  ActionListernerFecha( Label label ) {
    miObjLabel = label;
  }

  public void actionPerformed( ActionEvent evt ) {
    miObjLabel.setText( new Date().toString() );
  }
}


// Los objetos de las siguientes cinco clases se registran sobre
// los botones del panel de control. Cuando se produce un evento,
// el controlador hace que se presente una ficha diferente en el
// panel. Las cinco clases son semejantes, diferenciandose
// solamente en una sentencia en el metodo sobreescrito
// actionPerformed() que especifica la accion que debe realizarse
class ListenerPrimero implements ActionListener {
  Panel miObjPanel;
  CardLayout miObjCardLayout;

  ListenerPrimero( CardLayout cardLayout,Panel panel ) {
    miObjCardLayout = cardLayout;
    miObjPanel = panel;
    }

  public void actionPerformed(ActionEvent e){
    miObjCardLayout.first(miObjPanel);
    }
  }


// Ver los comentarios que ya se han hecho a la hora de explicar
// la clase ListenerPrimero
class ListenerSiguiente implements ActionListener {
  Panel miObjPanel;
  CardLayout miObjCardLayout;

  ListenerSiguiente( CardLayout cardLayout,Panel panel ) {
    miObjCardLayout = cardLayout;
    miObjPanel = panel;
    }

  public void actionPerformed( ActionEvent evt ) {
    miObjCardLayout.next( miObjPanel );
    }
  }


// Ver los comentarios que ya se han hecho a la hora de explicar
// la clase ListenerPrimero
class ListenerAnterior implements ActionListener {
  Panel miObjPanel;
  CardLayout miObjCardLayout;

  ListenerAnterior( CardLayout cardLayout,Panel panel ) {
    miObjCardLayout = cardLayout;
    miObjPanel = panel;
    }

  public void actionPerformed( ActionEvent evt ) {
    miObjCardLayout.previous( miObjPanel );
    }
  }


// Ver los comentarios que ya se han hecho a la hora de explicar
// la clase ListenerPrimero
class ListenerUltimo implements ActionListener {
  Panel miObjPanel;
  CardLayout miObjCardLayout;

  ListenerUltimo( CardLayout cardLayout,Panel panel ) {
    miObjCardLayout = cardLayout;
    miObjPanel = panel;
    }

  public void actionPerformed( ActionEvent evt ) {
    miObjCardLayout.last( miObjPanel );
    }
  }


// Ver los comentarios que ya se han hecho a la hora de explicar
// la clase ListenerPrimero
class ListenerMostrar implements ActionListener {
  Panel miObjPanel;
  CardLayout miObjCardLayout;

  ListenerMostrar( CardLayout cardLayout,Panel panel ) {
    miObjCardLayout = cardLayout;
    miObjPanel = panel;
    }

  public void actionPerformed( ActionEvent evt ) {
    // La invocacion del siguiente metodo hara que se visualice en
    // pantalla la ficha que corresponde al segundo parametro de
    // la llamda
    miObjCardLayout.show( miObjPanel,"panel fecha" );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1327.java