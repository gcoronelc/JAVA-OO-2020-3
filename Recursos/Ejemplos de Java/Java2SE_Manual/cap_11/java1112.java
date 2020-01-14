//
//  java1112.java
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
//     Creacion: 02-Oct-1997  10:59:47
//     Revision: 03-Feb-2002  12:21:39
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo mas consistente que ilustra la forma en que se
 * generan eventos desde Programa.
 * Se generan dos objetos no visibles que son capaces de enviar eventos
 * de tipo Action
 */
import java.awt.*;
import java.awt.event.*;

public class java1112 {
  public static void main( String args[] ) {
    new java1112();
    }

  public java1112() {
    System.out.println( "Tutorial de Java, Eventos" );
    System.out.println( "Instancia dos objetos NoVisualizable que son " +
      "capaces de generar eventos de tipo Action." );
    NoVisualizable aNoVisualizable = new NoVisualizable( "ObjNoVisualizable A" );
    aNoVisualizable.setName( "ObjNoVisualizableA" );
    NoVisualizable bNoVisualizable = new NoVisualizable( "ObjNoVisualizable B" );
    bNoVisualizable.setName( "ObjNoVisualizableB" );

    System.out.println( "Nombre del primer objeto NoVisualizable:  " +
      aNoVisualizable.getName() );
    System.out.println( "Nombre del segundo objeto NoVisualizable: " +
      bNoVisualizable.getName() );

    // Se registran objetos ActionListener para que recojan los
    // eventos que se generen sobre los objetos NoVisualizable. Uno de
    // los objetos NoVisualizable es registrado solamente sobre un
    // objeto ActionListener, mientras que el otro se registra
    // sobre dos objetos ActionListener, uno de los cuales es
    // el mismo que se registro para el otro objeto NoVisualizable
    System.out.println(
      "Registra objetos ActionListener sobre los objetos NoVisualizable" );
    aNoVisualizable.addActionListener( new PrimerReceptorAction() );
    bNoVisualizable.addActionListener( new PrimerReceptorAction() );
    bNoVisualizable.addActionListener( new SegundoReceptorAction() );

    // Se hace que cada uno de los dos objetos NoVisualizable generen
    // un evento Action
    System.out.println( "Metodo generaEventoAction() invocado " +
      " sobre el objeto " + aNoVisualizable.getName() );
    System.out.println(
      " que solo tiene un objeto ActionListener registrado." );
    aNoVisualizable.generaEventoAction();
    System.out.println();
    System.out.println( "Metodo generaEventoAction() invocado " +
      " sobre el objeto " + bNoVisualizable.getName() );
    System.out.println(
      " que tiene dos objetos ActionListener registrados." );
    bNoVisualizable.generaEventoAction();
    }
  }


// Las dos clases siguientes son clases ActionListener estandar. Los
// objetos instanciados de estas clases simplemente capturan los
// eventos de tipo Action y presentan informacion en pantalla sobre
// ellos
// Primera clase para responder a los eventos action
class PrimerReceptorAction implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    System.out.println(
      "En el metodo actionPerformed() del objeto " +
      "PrimerReceptorAction" );
    System.out.println(
      "Metodo actionPerformed() invocado sobre " +
      evt.getActionCommand() );
    }
  }


// Segunda clase para responder a los eventos action
class SegundoReceptorAction implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    System.out.println(
      "En el metodo actionPerformed() del objeto " +
      "SegundoReceptorAction" );
    System.out.println(
      "Metodo actionPerformed() invocado sobre " +
      evt.getActionCommand() );
    }
  }


// Clase para crear un objeto que sea capaz de generar eventos de
// accion (Action)
class NoVisualizable extends Component {
  // El estado de un objeto NoVisualizable en cualquier momento se
  // puede conocer a traves de sus variables de instancia
  // La identificacion del objeto
  String ID;
  // Lista de los objetos receptor registrados
  ActionListener receptorAction;
  // estos objetos seran notificados cuando se produzca
  // un evento Action (ver la descripcion de addActionListener)

  // Constructor para el objeto No Visual
  public NoVisualizable( String ID ) {
    this.ID = ID;
    }

  //--------------------------------------------------------------
  // El entorno de un objeto NoVisualizable esta definido por los
  // siguientes metodos de instancia
  //--------------------------------------------------------------
  /**
   * El siguiente metodo incorpora los objetos ActionListener pasados
   *  como parametro a la lista de objetos ActionListener designados
   *  como receptores que deben ser notificados cuando se produzca
   *  un evento sobre un objeto NoVisualizable
   * La notificacion tiene lugar en un metodo diferente invocando
   *  al metodo actionPerformed() de cada uno de los objetos
   *  ActionListener de la lista
   * Se incorporan nuevos objetos a la lista llamando al metodo
   *  estatico add() de la clase java.awt.AWTEventMulticaster y
   *  pasandole la variable de instancia que referencia a la lista
   *  a la que se quiere incorporar el nuevo receptor
   * Para el primer objeto receptor de eventos incorporado a la
   *  lista, se devuelve una referencia a si mismo. Luego, en
   *  este caso, la lista seria simplemente una referencia al
   *  objeto Receptor
   * Cuando se incorporan objetos receptores adicionales, el
   *  metodo add() de la clase AWTEventMulticaster devuelve una
   *  referencia a un objeto de tipo java.awt.AWTEventMulticaster
   * Segun la documentacion del JDK sobre esta clase, se dice:
   *  "Esta clase manejara la estructura de una cadena de
   *  receptores de eventos y despachara eventos a esos
   *  receptores"
   * Cuando el metodo actionPerformed() es invocado posteriormente
   *  sobre la referencia a la lista, oel metodo es invocado sobre
   *  un objeto simple, o el objeto AWTEventMulticaster asume la
   *  responsabilidad de invocar a todos los metodos actionPerformed()
   *  de todos los objetos Listener que se mantienen en su lista de
   *  objetos receptores de esos eventos
   */
  public void addActionListener( ActionListener receptor ) {
    System.out.println();
    System.out.println( "Invocado el metodo addActionListener()" );
    System.out.println( ID + ":   El Receptor incorporado es: " +
      receptor );

    System.out.println(
      "Invocado AWTEventMulticaster.add() para recuperar " +
      "la referencia a ActionListener");
    receptorAction = AWTEventMulticaster.add( receptorAction,
      receptor);
    System.out.println( ID + ":   La Ref a ActionListener es: " +
      receptorAction );
    System.out.println();
    }

  // El proposito de este metodo es invocar al metodo actionPerformed()
  // sobre todos los objetos Receptor que estan contenidos en la lista
  // de objetos Receptor que estan registrados para recibir eventos
  // Action que estan siendo generados por este objeto NoVisualizable.
  // Esto se consigue invocando el metodo actionPerformed() sobre la
  // referencia a la lista. Cuando esto esta hecho, un objeto ActionEvent
  // es instanciado y pasado como parametro
  public void generaEventoAction() {
    // Confirma que hay algun receptor registrado
    if( receptorAction != null ) {
      System.out.println(
        "En el metodo generaEventoAction(), lanzando " +
        "un evento ACTION_PERFORMED a " );
      System.out.println( receptorAction + " para " + ID );
        receptorAction.actionPerformed( new ActionEvent(
        this,ActionEvent.ACTION_PERFORMED,ID ) );
      }
    }
  }

//------------------------------------------ Final del fichero java1112.java