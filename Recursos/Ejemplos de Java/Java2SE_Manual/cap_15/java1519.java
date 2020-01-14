//
//  java1519.java
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
//     Creacion: 03-May-1999  09:51:42
//     Revision: 08-Feb-2002  06:02:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo sencillo que muestra cómo se carga y presenta en
 * pantalla una imagen, a través de un objeto MediaTracker que controla y
 * monitoriza la carga de la imagen, bloqueando la tarea hasta que esa
 * imagen se ha cargado completamente.
 * Cada segundo se vuelve a cargar la imagen, de forma que se presenta en
 * ese momento en pantalla, originándose un molesto parpadeo, que se
 * puede eliminar con el uso del doble buffer,
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1519 extends Frame {
  // Referencia a la imagen
  Image imagen;

  public java1519() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 275,250 );

    // Recogemos en la variable "imagen" el fichero de imagen que
    // se indica, y que se supone situado en el mismo directorio y
    // disco que la clase del ejemplo
    imagen = Toolkit.getDefaultToolkit().getImage( "muneco.gif" );

    // Se hace visible el Frame, que en la pantalla da origen a
    // la ventana, aunque la primera imagen no es visible en el
    // mismo momento en que aparece la ventana en pantalla, porque
    // hasta que se invoque por primera vez el método paint(), no
    // se colocará una imagen en el contendor
    this.setVisible( true );

    // Clase anónima anidada que permite terminar la ejecución del
    // programa, controlando el botón de cierre del Frame
    this.addWindowListener(
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se sale al sistema
          System.exit( 0 );
        }
      } );
    }

  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1519();
    }

  // Se sobrecarga el método para pintar la imagen
  public void paint( Graphics g ) {
    // Se traslada el origen para evitar el efecto del borde
    g.translate( this.getInsets().left,this.getInsets().top );

    // Se utiliza un objeto MediaTracker para bloquear la tarea hasta
    // que la imagen esté cargada completamente, antes de intentar
    // pintarla en pantalla. Si la carga de la imagen falla en el
    // intervalo de un segundo, el programa se termina. Sin el uso de
    // un objeto de tipo MediaTracker, si la imagen es pequeña, apenas
    // se notará el uso de MediaTracker, pero sí que es importante a la
    // hora de cargar imágenes muy grandes o gran cantidad de ellas
    MediaTracker tracker = new MediaTracker( this );
    // Se añade la imagen a la lista del tracker
    tracker.addImage( imagen,1 );
    try {
      // Se bloquea la tarea durante un segundo, mientras se
      // intenta la carga de la imagen. En caso de que la imagen
      // sea mayor que la que se utiliza en este ejemplo, puede
      // ser necesario aumentar este tiempo
      if( !tracker.waitForID(1,1000) ) {
        System.out.println( "Fallo en la carga de la imagen" );
        System.exit( 0 );
        }
    } catch( InterruptedException e ) {
      System.out.println( e );
      }

    // Ahora se pinta la imagen a la mitad de su tamaño normal
    g.drawImage( imagen,0,0,
      imagen.getWidth(this)/2,imagen.getHeight(this)/2,this );
    // Y se presenta en pantalla
    this.repaint();
    }
  }

//------------------------------------------ Final del fichero java1519.java