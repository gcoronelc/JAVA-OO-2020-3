//
//  java1513.java
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
//     Creacion: 06-May-1999  05:35:12
//     Revision: 08-Feb-2002  05:53:19
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplos e muestra la utilización de los métodos que permiten
 * la copia de zonas dentro de un contexto gráfico.
 * Primero se pitna un texto en pantalla, y luego se utilizan estos métodos
 * para colocarlo en diferentes sitios, y al final se borra parte de la
 * primera letra de una de la copias
 * Esto se hace cada vez que se llama a paint, con lo cual durante la
 * ejecución del programa se podrá observar un fuerte parpadeo en las dos
 * copias que se realizan de la cadena, ya que se borra el fondo y luego
 * se copia la zona, en el caso de la segunda copia, no entera, porque
 * faltará un trozo de la primera letra "T"
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1513 extends Frame {

  // Función de control de la aplicación
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1513();
    }

  // Contructor de la clase
  public java1513() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 300,100 );
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
    g.drawString( "Tutorial de Java",10,40 );
    // Se copia la zona ocupada por la cadena
    g.copyArea( 0,0,100,100,100,0 );
    // Se hace otra copia
    g.copyArea( 0,0,100,100,100,40 );
    // Se borra parte de esta segunda copia
    g.clearRect( 100,40,15,40 );
    }
  }

//------------------------------------------ Final del fichero java1513.java