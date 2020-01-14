//
//  java1510.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 06-May-1999  04:28:19
//     Revision: 08-Feb-2002  05:51:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el ejemplo más sencillo, que muestra cómo se sobrecarga el
 * método paint(), en este caso solamente para pintar una cadena en la
 * ventana que aparece en la pantalla
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1510 extends Frame {

  // Función de control de la aplicación
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1510();
    }

  // Contructor de la clase
  public java1510() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 300,50 );
    this.setVisible( true );

    // Clase anidada que permite terminar la ejecución de la animación
    this.addWindowListener(
      // Definición de la clase anónima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      } );
    }

  // Se sobrecarga el método paint(), para presentar una cadena de
  // texto en la ventana que se abre en pantalla, sobre el contexto
  // gráfico del objeto Frame
  public void paint( Graphics g ) {
    g.drawString( "Tutorial de Java",100,40 );
    }
  }

//------------------------------------------ Final del fichero java1510.java