//
//  java1514.java
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
//     Creacion: 06-May-1999  05:42:53
//     Revision: 08-Feb-2002  05:53:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra el uso de los contextos gráficos, de forma
 * que lo que se realiza en una segunda referencia del contexto original
 * que se crea, no afecta en nada a las acciones que se realizan con
 * el original. En la documentación del Tutorial se explica claramente
 * como funcionan los contextos gráficos en Java
 * Para evitar el problema que se origina con "insets", ya que hay que tener
 * en cuenta la anchura de los bordes a la hora de pintar sobre un objeto de
 * tipo Frame; se traslada el origen de coordenadas que está situado en la
 * esquina superior-izquierda de la ventana, hasta el inicio del área cliente
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1514 extends Frame {

  // Función de control de la aplicación
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1514();
    }

  // Contructor de la clase
  public java1514() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 300,150 );
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
      });
    }

  // Se sobrecarga el método paint()
  public void paint( Graphics g ) {
    g.setColor( Color.red );

    // Trasladamos el origen de coordenadas que se sitúa en la
    // esquina superior izquierda, para evitar el problema que se
    // produce con insets. De este modo el origen de coordenadas sí
    // que lo dejamos situado en la zona cliente del objeto Frame
    // que es la que se utiliza para pintas
    g.translate( this.getInsets().left,this.getInsets().top );

    // Creamos una segunda referencia del contexto gráfico que se
    // utiliza en el método
    Graphics clpArea = g.create();
    // Usamos esta referencia para pintar un rectángulo rojo
    clpArea.drawRect( 0,0,250,100 );

    // Se redude el rectángulo de clipping, de forma que solamente se
    // quede sin recortar un rectángulo más pequeño
    clpArea.clipRect( 25,25,200,50 );

    // Se usa el contexto para pintar una línea diagonal verde a lo largo
    // del rectángulo original, aunque por la acción de la zona de
    // clipping que se ha fijado antes, solamente estará visible la
    // parte central
    clpArea.setColor( Color.green );
    clpArea.drawLine( 0,0,250,100 );

    // Liberamos los recursos usados por el contexto gráfico
    clpArea.dispose();
    // Y lo seleccionamos como candidato a la basura
    clpArea = null;

    // Ahora usamos el contexto gráfico original para pintar una
    // línea azul que atraviesa todo el rectángulo, y que ya no
    // se ve afectada por el rectángulo de recorte que se había fijado
    // en la segunda referencia del contexto que habíamos creado antes
    g.setColor( Color.blue );
    g.drawLine( 0,100,250,0 );
    }
  }

//------------------------------------------ Final del fichero java1514.java