//
//  java1512.java
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
//     Creacion: 06-May-1999  05:18:34
//     Revision: 08-Feb-2002  05:52:14
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo tiene la misma funcionalidad que el ejemplo java1511, aunque
 * se ha eliminado la necesidad de tener en cuenta los "insets" del objeto
 * Frame sobre el que se está pintando.
 * Esto se consigue añadiendo un objeto Canvas al objeto Frame, y dibujando
 * sobre ese objeto Canvas, en lugar de hacerlo directamente sobre el objeto
 * Frame; y como el objeto Canvas no tiene bordes, no hay ningún problema
 * con los "insets"
 */
import java.awt.*;
import java.awt.event.*;

// Clase para crear la zona de dibujo, que extiende a la clase Canvas
// para que sea posible sobrecargar el método paint()
class MiCanvas extends Canvas {
  // Se sobrecarga el método paint()
  public void paint( Graphics g ) {
    // Fijamos el color rojo para pintar
    g.setColor( Color.red );

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


// Clase de control del ejemplo
class java1512 extends Frame {

  // Función de control de la aplicación
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1512();
    }

  // Contructor de la clase
  public java1512() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 300,150 );

    // Se crea una superficie amarilla y se utiliza para cubrir el
    // área cliente del objeto Frame
    MiCanvas zonaDibujo = new MiCanvas();
    zonaDibujo.setBackground( Color.yellow );
    this.add( zonaDibujo );
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
  }

//------------------------------------------ Final del fichero java1512.java