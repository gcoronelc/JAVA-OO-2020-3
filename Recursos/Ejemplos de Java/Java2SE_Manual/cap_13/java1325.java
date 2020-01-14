//
//  java1325.java
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
//     Creacion: 06-Jun-1998  08:49:13
//     Revision: 05-Feb-2002  06:01:03
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa ilustra el uso del GridLayout y la construccion de
 * un interfaz de usuuario mas complejo que los que se han visto hasta
 * el momento en base a la composicion de subunidades. Y, tambien, se
 * ilustra el procedimiento de como se modifica un layout dinamicamente
 * en tiempo de ejecucion.
 * El Interfaz consiste en un Frame como base y dos Paneles colocados
 * sobre el Frame utilizando el layou por defecto, BorderLayout.
 * Uno de los paneles contiene seis botones que estan posicionados
 * utilizando un GridLayout. El uso de este layout es solamente para
 * mostrar su uso y no tiene funcionalidad (no tiene objetos registrados)
 * Los botones estan colocados inicialmente en este panel en una matriz
 * formada por dos filas y tres columnas. Esto se consigue pasando los
 * parametros adecuados al constructor del GridLayout que se utiliza para
 * controlar el posicionamiento de los componentes sobre el panel.
 * El otro panel contiene dos botones con las etiquetas 3x2 y 2x3, que
 * estan colocados usando un FlowLayout. Estos dos botones si que son
 * funcionales y actuan sobre la posicion de los botones del otro panel.
 * Asi, si se pulsa el boton 3x2, los botones del otro panel se colocaran
 * en tres filas y dos columnas; y, si se pulsa el boton 2x3, los
 * botones del otro panel recuperaran su posicionamiento inicial de
 * dos filas y tres columnas.
 * Se instancia y registra, tambien, un objeto receptor de eventos de
 * la ventana para terminar la ejecucion del programa cuando el
 * usuario cierre el Frame.
 */
import java.awt.*;
import java.awt.event.*;

public class java1325 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Se instancian los dos botones que van a proporcionar la
    // funcionalidad a la aplicacion
    Button boton7 = new Button( "3x2" );
    Button boton8 = new Button( "2x3" );

    // Se instancia un objeto layout de tipo GridLayout para ser
    // utilizado con el Panel
    GridLayout miGridLayout = new GridLayout( 2,3 );

    // Instancia el primero de los dos objetos Panel que sera
    // integrado en el objeto Frame
    Panel panel1 = new Panel();
    // Fijamos el layout que habiamos definido para el panel
    panel1.setLayout( miGridLayout );
    // Se colocan los seis botones sobre el panel con una
    // etiqueta indicando su numero
    for ( int i=0; i < 6; i++ )
      panel1.add( new Button( "Boton"+i ) );

    // Se instancia el segundo objeto Panel utilizando el FlowLayout
    // por defecto y se colocan los dos botones funcionales sobre el.
    // A estos botones se les añadira su funcionalidad a traves de
    // objetos receptores de los eventos ActionListener registrados
    // sobre ellos
    Panel panel2 = new Panel();
    panel2.add( boton7 );
    panel2.add( boton8 );

    // Se instancia el objeto Frame, que sera el padre de todo
    // el interfaz de usuario que se esta creando
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );

    // IMPORTANTE: Se añaden los dos objetos Panel que se han
    // preparado al objeto Frame para crear el interfaz definitivo
    miFrame.add( panel1,"North" );
    miFrame.add( panel2,"South" );

    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Se instancian los objetos Receptores de eventos Action y se
    // registran con los botones 7 y 8, que corresponden al
    // segundo Panel y que van a modificar el posicionamiento de
    // los otrs seis botones en el Panel contiguo
    boton7.addActionListener( new A3x2ActionListener( miGridLayout,miFrame ) );
    boton8.addActionListener( new A2x3ActionListener( miGridLayout,miFrame ) );

    // Se termina de dar funcionalidad al interfaz, instanciando y
    // registrando un objeto receptor de eventos de la ventana para
    // concluir la ejecucion de la aplicacion cuando el usuario cierre
    // el Frame
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Las siguientes dos clases son las clases de los ActionListener,
// los receptores de eventos de tipo Action. Un objeto de cada una
// de ellas se instancia y registra sobore cada uno de los dos
// botones funcionales de la aplicacion. El proposito de estos
// controladores de eventos es modificar el layout del panel
// contiguo, de forma que los botones que estan colocados sobre
// el se cambien de posicion
class A3x2ActionListener implements ActionListener {
  GridLayout miObjGridLayout;
  Frame miObjFrame;

  A3x2ActionListener( GridLayout layout,Frame frame ) {
    miObjGridLayout = layout;
    miObjFrame = frame;
    }

  // Cuando se produce un evento Action, se fijan las filas a 3 y
  // la columnas a 2 sobre el objeto GridLayout. Luego se fija el
  // controlador de posicionamiento para que sea el nuevo que
  // acabamos de modificar, y posteriormente se valida el Frame
  // para asegurarse de que el alyout es valido y tendra efecto
  // sobre la visualizacion en pantalla
  public void actionPerformed( ActionEvent evt ) {
    miObjGridLayout.setRows( 3 );
    miObjGridLayout.setColumns( 2 );
    miObjFrame.setLayout( miObjGridLayout );
    miObjFrame.validate();
    }
  }


class A2x3ActionListener implements ActionListener {
  GridLayout miObjGridLayout;
  Frame miObjFrame;

  A2x3ActionListener( GridLayout layout,Frame frame ) {
    miObjGridLayout = layout;
    miObjFrame = frame;
    }

  // Cuando se produce un evento Action, se fijan las filas a 2 y
  // la columnas a 3 sobre el objeto GridLayout. Luego se fija el
  // controlador de posicionamiento para que sea el nuevo que
  // acabamos de modificar, y posteriormente se valida el Frame
  // para asegurarse de que el alyout es valido y tendra efecto
  // sobre la visualizacion en pantalla
  public void actionPerformed( ActionEvent evt ) {
    miObjGridLayout.setRows( 2 );
    miObjGridLayout.setColumns( 3 );
    miObjFrame.setLayout( miObjGridLayout );
    miObjFrame.validate();
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Termina la ejecucion del programa cuando se cierra la
    // ventana principal de la aplicacion
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1325.java