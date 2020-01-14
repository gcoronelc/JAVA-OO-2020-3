//
//  java1111.java
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
//     Creacion: 02-Oct-1997  09:22:43
//     Revision: 03-Feb-2002  12:20:35
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo muy sencillo que muestra como se pueden
 * generar eventos desde programa en el modelo de Delegación
 * de Eventos
 */
import java.awt.*;
import java.awt.event.*;

public class java1111 {
  public static void main( String args[] ) {
    // Se instancia un objeto de este tipo
    new java1111();
    }

  // Constructor
  public java1111() {
    System.out.println( "Tutorial de Java, Eventos" );

    NoVisualizable objNoVisual = new NoVisualizable( "ObjetoNoVisual" );
    objNoVisual.generaListaReceptores( new ClaseActionListener() );
    objNoVisual.generaEventoAction();
    }
  }


// Clase para responder a los eventos de accion
class ClaseActionListener implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    System.out.println(
      "metodo actionPerformed() invocado sobre " +
      evt.getActionCommand() );
    }
  }


// Clase para crear un objeto que sea capaz de generar eventos de
// accion (Action)
class NoVisualizable extends Component {
  // La identificacion del objeto
  String ID;
  // Lista de los objetos receptor registrados
  ActionListener receptorAction;

  // Constructor para el objeto No Visual
  public NoVisualizable( String ID ) {
    this.ID = ID;
    }

  public void generaListaReceptores( ActionListener listener ) {
    receptorAction = AWTEventMulticaster.add(
      receptorAction,listener );
    }

  public void generaEventoAction() {
    receptorAction.actionPerformed(
      new ActionEvent( this,ActionEvent.ACTION_PERFORMED,ID ) );
    }
  }

//------------------------------------------ Final del fichero java1111.java