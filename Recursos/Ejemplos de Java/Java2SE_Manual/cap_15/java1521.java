//
//  java1521.java
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
//     Creacion: 04-May-1999  05:51:23
//     Revision: 08-Feb-2002  06:07:56
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra la diferencia que existe entre el uso y el no uso
 * del doble buffer a la hora de la realizaci�n de animaciones. Para su
 * ejecuci�n es necesario el fichero de imagen "muneco.gif" situado en el
 * mismo directorio que las clases Java.
 * Para ejecutar el programa con el doble buffer activado, teclear:
 *   java java1521
 * y para ejecutarlo con el doble buffer desactivado, teclear:
 *   java java1521 x
 * La animaci�n se incir�, escalando la imagen a m�s o menos, hasta que
 * se cierre la ventana.
 */

import java.awt.*;
import java.awt.event.*;

class java1521 extends Frame {
  Image imagenFuente;             // Imagen cargada de disco
  int iniAncho;
  int iniAlto;
  // Ancho y Alto para cada imagen de la animaci�n
  int finAncho;
  int finAlto;
  // Valores del borde para el objeto contenedor
  int insetArriba;
  int insetIzqda;
  // Referecnias a objetos utilizados en el doble buffer
  Image imgDobleBuffer;
  Graphics contextoDobleBuffer;
  // Indicador del uso o no del doble buffer
  boolean usarDobleBuffer = true;

  // M�todo de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    java1521 obj = new java1521();
    // Se declaran las variables locales que indican el desplazamiento
    // y la escala de las im�genes
    double delta = 0;
    double escala = 0;

    // Se utiliza el doble buffer o no, dependiendo de los valores que se
    // hayan introducido en la l�nea de comandos. Por defecto, se utiliza
    // el doble buffer, y cualquier otro argumento en la l�nea de
    // comandos deshabilitar� su uso
    if( args.length == 0 ) {
      obj.usarDobleBuffer = true;
      System.out.println( "Doble Buffer Activado" );
      }
    else {
      obj.usarDobleBuffer = false;
      System.out.println( "Doble Buffer Desactivado" );
      }

    // Bucle de control de la animaci�n, que seguir� funcionando, hasta
    // que se cierre la ventana con el bot�n de cierre del marco de esa
    // ventana
    while( true ) {
      // Control de la direcci�n en que se realiza la animaci�n, de
      // forma que vaya creciendo hasta su tama�o normal y luego vuelva
      // a decrecer
      if( escala >= 0.999 )
        delta = -0.005;
      if( escala <= 0.015 )
        delta = 0.005;

      // Se establece el ancho y alto para la nueva imagen que se va a
      // presentar en la animaci�n
      obj.finAncho = (int)( escala*obj.iniAncho );
      obj.finAlto = (int)( escala*obj.iniAlto );
      // Se presenta la imagen
      obj.repaint();

      // Se actualiza la escala para la siguiente imagen
      escala += delta;

      // Se retrasa la presentaci�n, de forma que la animaci�n se
      // encuentre en un rango aproximado de 20 im�genes por segundo
      try {
        Thread.currentThread().sleep( 50 );
      } catch( InterruptedException e ) {
        System.out.println( e );
        }
      }
    }

  // Contructor de la clase
  public java1521() {
    // Se carga la imagen desde el fichero que se indique, que se
    // supone situado en el directorio actual del disco duro
    imagenFuente = Toolkit.getDefaultToolkit().getImage( "muneco.gif" );

    // Se utilzia un objeto MediaTracker para bloquear la tarea hasta
    // que la imagen se haya cargado o hayan transcurrido 10 segundos
    // desde que se inicia la carga
    MediaTracker tracker = new MediaTracker( this );
    tracker.addImage( imagenFuente,1 );

    try {
      if( !tracker.waitForID( 1,10000 ) ) {
        System.out.println( "Error en la carga de la imagen" );
        System.exit( 1 );
      }
    } catch( InterruptedException e ) {
      System.out.println( e );
      }

    // La imagen ya est� cargada, as� que se establece la anchura y
    // altura de la ventana, para que sus dimensiones se adec�en a la
    // imagen
    iniAncho = imagenFuente.getWidth( this );
    iniAlto = imagenFuente.getHeight( this );
    // Se hace visible el Frame
    this.setVisible( true );

    // Se obtienen los datos de insets del objeto, para que sean
    // compensados en la presentaci�n de las im�genes
    insetArriba = this.getInsets().top;
    insetIzqda = this.getInsets().left;

    // Se usan las dimensiones de insets y el tama�o de la imagen
    // fuente para establecer el tama�o total del Frame
    this.setSize( insetIzqda+iniAncho,insetArriba+iniAlto );
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setBackground( Color.yellow );

    // Creamos una imagen para el uso del doble buffer, que ser� la que
    // se utilice en el intercambio para la renderizaci�n de la imagen
    // en el buffer oculto. Tambi�n se obtiene el contexto gr�fico con
    // el que se va a pintar
    imgDobleBuffer = this.createImage( iniAncho,iniAlto );
    contextoDobleBuffer = imgDobleBuffer.getGraphics();

    // Clase anidada que permite terminar la ejecuci�n de la animaci�n
    this.addWindowListener(
      // Definici�n de la clase an�nima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      }
    );
    }

  // Se sobreescribe el m�todo update() para eliminar el borrado
  // innecesario de la pantalla y el parpadeo que originar�a este
  // borrado. Esto requiere que el m�todo paint() sea el que controle
  // el borrado de la pantalla
  public void update( Graphics g ) {
    paint( g );
    }

  // M�todo paint() sobreescrito
  public void paint( Graphics g ) {
    // Si se est� utilizando el doble buffer
    if( usarDobleBuffer && imgDobleBuffer != null ) {
      // Se vuelca en pantalla la imagen que previamente se hab�a
      // construido en el buffer oculto
      g.drawImage( imgDobleBuffer,insetIzqda,insetArriba,this );

      // Se escala y pinta la siguiente imagen en el buffer oculto
      // utilizando las variables de instancia "finAncho" y "finAlto"
      // para determinar el tama�o de esa imagen. De esta forma
      // cuando se vuelva a invocar a paint() la imagen estar� lista
      // para su presentaci�n en pantalla.
      contextoDobleBuffer.clearRect( 0,0,iniAncho,iniAlto );
      contextoDobleBuffer.drawImage( imagenFuente,
        0,0,finAncho,finAlto,this );
      }
    else {
      g.clearRect( insetIzqda,insetArriba,iniAncho,iniAlto );
      g.drawImage( imagenFuente,
        insetIzqda,insetArriba,finAncho,finAlto,this);
      }
    }
  }

//------------------------------------------ Final del fichero java1521.java