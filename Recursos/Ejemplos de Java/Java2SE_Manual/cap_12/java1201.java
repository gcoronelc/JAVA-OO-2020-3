//
//  java1201.java
//  Copyright (c) 1997,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 02-Oct-1997  08:03:49
//     Revision: 03-Feb-2002  12:39:06
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa ilustra el uso de clases anidadas junto con el control
 * de eventos bajo en modelo de Delegacion de Eventos instaurado por el
 * JDK 1.1
 * Cuando se ejecuta el programa, aparece un objeto Frame que contiene dos
 * botones. Cuando se pulsa sobre el boton etiquetado como "Cantar", el
 * controlador de eventos registrado para recibir los eventos Action
 * de ese objeto inva al metodo cantar() y aparece la pantalla el
 * mensaje:
 * "Cantando: Ese toro enamorado de la luna..."
 * Si se pulsa sobre el boton con la etiqueta "Silbar", el controlador
 * de eventos recibira un evento de accion desde ese boton e invocara
 * al metodo silbar(), haciendo que aparezca en pantalla el mensaje:
 * "Silbando: Fiuuu, Fiiuuu, Fiiiuuu..."
 * Cuando el usuario utiliza el raton para cerrar el objeto Frame, el
 * contorlador de eventos registrado para recibir los eventos de tipo
 * windowClosing() desde el Frame, terminara la ejecucion del programa
 *
 * En este ejemplo se utiliza la notacion larga para la definicion e
 * instanciacion de los objetos Receptores. En el ejemplo java1112 se
 * utiliza la notacion abreviada, que resulta bastante criptica
 */
import java.awt.*;
import java.awt.event.*;

public class java1201 {
  void cantar() {
    System.out.println( "Cantando: Ese toro enamorado de la luna..." );
    }

  void silbar() {
    System.out.println( "Silbando: Fiuuu, Fiiuuu, Fiiiuuu..." );
    }

  static public void main( String args[] ) {
    java1201 app = new java1201();
    IHM ihm = app.new IHM(); // Observese la sintaxis aqui
    }

  // Esta clase IHM esta definida dentro de la clase java1201
  class IHM extends Frame {

    // Esta clase CantarActionListener esta definida dentro de la clase IHM
    class CantarActionListener implements ActionListener {
      public void actionPerformed( ActionEvent evt ) {
        cantar();
        }
      }

    // Esta clase SilbarActionListener esta definida dentro de la clase IHM
    class SilbarActionListener implements ActionListener {
      public void actionPerformed( ActionEvent evt ) {
        silbar();
        }
      }

    // Esta clase Conclusion esta definida dentro de la clase IHM
    class Conclusion extends WindowAdapter {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      }

    // Constructor de la clase
    public IHM() {
      setLayout( new FlowLayout() );
      setTitle( "Tutorial de Java, Clases Anidadas" );

      Button botonCantar;
      add( botonCantar = new Button( "Cantar" ) );
      botonCantar.addActionListener( new CantarActionListener() );

      Button botonSilbar;
      add( botonSilbar = new Button( "Silbar" ) );
      botonSilbar.addActionListener( new SilbarActionListener() );

      // Registra un receptor para los eventos del Frame de IHM
      addWindowListener( new Conclusion() );
      setSize( 300,75 );
      setVisible( true );
      }
    }
  }

//------------------------------------------ Final del fichero java1201.java