//
//  java1518.java
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
//     Creacion: 03-May-1999  06:31:09
//     Revision: 08-Feb-2002  06:00:13
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se presenta una imagen en pantalla a la mitad de su
 * tamaño real.
 * Cuando se crea la ventana, la imagen no aparece en ese mismo instante
 * sino que hay que esperar a que se termine de realizar la operación de
 * escalado para hacer que aparezca esa imagen en la ventana.
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1518 extends Frame {
  // Referencia a la imagen
  Image imagen;

  // Constructor de la clase
  public java1518() {
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
    new java1518();
    }

  // Se sobrecarga el método para pintar la imagen
  public void paint( Graphics g ) {
    // Se traslada el origen para evitar el efecto del borde
    g.translate( this.getInsets().left,this.getInsets().top );

    // Ahora se pinta la imagen a la mitad de su tamaño
    g.drawImage( imagen,0,0,
      imagen.getWidth(this)/2,imagen.getHeight(this)/2,this );
    }
  }

//------------------------------------------ Final del fichero java1518.java