//
//  java1514.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 06-May-1999  05:42:53
//     Revision: 08-Feb-2002  05:53:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra el uso de los contextos gr�ficos, de forma
 * que lo que se realiza en una segunda referencia del contexto original
 * que se crea, no afecta en nada a las acciones que se realizan con
 * el original. En la documentaci�n del Tutorial se explica claramente
 * como funcionan los contextos gr�ficos en Java
 * Para evitar el problema que se origina con "insets", ya que hay que tener
 * en cuenta la anchura de los bordes a la hora de pintar sobre un objeto de
 * tipo Frame; se traslada el origen de coordenadas que est� situado en la
 * esquina superior-izquierda de la ventana, hasta el inicio del �rea cliente
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1514 extends Frame {

  // Funci�n de control de la aplicaci�n
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1514();
    }

  // Contructor de la clase
  public java1514() {
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setSize( 300,150 );
    this.setVisible( true );

    // Clase anidada que permite terminar la ejecuci�n de la animaci�n
    this.addWindowListener(
      // Definici�n de la clase an�nima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      });
    }

  // Se sobrecarga el m�todo paint()
  public void paint( Graphics g ) {
    g.setColor( Color.red );

    // Trasladamos el origen de coordenadas que se sit�a en la
    // esquina superior izquierda, para evitar el problema que se
    // produce con insets. De este modo el origen de coordenadas s�
    // que lo dejamos situado en la zona cliente del objeto Frame
    // que es la que se utiliza para pintas
    g.translate( this.getInsets().left,this.getInsets().top );

    // Creamos una segunda referencia del contexto gr�fico que se
    // utiliza en el m�todo
    Graphics clpArea = g.create();
    // Usamos esta referencia para pintar un rect�ngulo rojo
    clpArea.drawRect( 0,0,250,100 );

    // Se redude el rect�ngulo de clipping, de forma que solamente se
    // quede sin recortar un rect�ngulo m�s peque�o
    clpArea.clipRect( 25,25,200,50 );

    // Se usa el contexto para pintar una l�nea diagonal verde a lo largo
    // del rect�ngulo original, aunque por la acci�n de la zona de
    // clipping que se ha fijado antes, solamente estar� visible la
    // parte central
    clpArea.setColor( Color.green );
    clpArea.drawLine( 0,0,250,100 );

    // Liberamos los recursos usados por el contexto gr�fico
    clpArea.dispose();
    // Y lo seleccionamos como candidato a la basura
    clpArea = null;

    // Ahora usamos el contexto gr�fico original para pintar una
    // l�nea azul que atraviesa todo el rect�ngulo, y que ya no
    // se ve afectada por el rect�ngulo de recorte que se hab�a fijado
    // en la segunda referencia del contexto que hab�amos creado antes
    g.setColor( Color.blue );
    g.drawLine( 0,100,250,0 );
    }
  }

//------------------------------------------ Final del fichero java1514.java