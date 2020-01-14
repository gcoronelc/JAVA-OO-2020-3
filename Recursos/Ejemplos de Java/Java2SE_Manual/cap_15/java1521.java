//
//  java1521.java
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
//     Creacion: 04-May-1999  05:51:23
//     Revision: 08-Feb-2002  06:07:56
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra la diferencia que existe entre el uso y el no uso
 * del doble buffer a la hora de la realización de animaciones. Para su
 * ejecución es necesario el fichero de imagen "muneco.gif" situado en el
 * mismo directorio que las clases Java.
 * Para ejecutar el programa con el doble buffer activado, teclear:
 *   java java1521
 * y para ejecutarlo con el doble buffer desactivado, teclear:
 *   java java1521 x
 * La animación se incirá, escalando la imagen a más o menos, hasta que
 * se cierre la ventana.
 */

import java.awt.*;
import java.awt.event.*;

class java1521 extends Frame {
  Image imagenFuente;             // Imagen cargada de disco
  int iniAncho;
  int iniAlto;
  // Ancho y Alto para cada imagen de la animación
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

  // Método de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    java1521 obj = new java1521();
    // Se declaran las variables locales que indican el desplazamiento
    // y la escala de las imágenes
    double delta = 0;
    double escala = 0;

    // Se utiliza el doble buffer o no, dependiendo de los valores que se
    // hayan introducido en la línea de comandos. Por defecto, se utiliza
    // el doble buffer, y cualquier otro argumento en la línea de
    // comandos deshabilitará su uso
    if( args.length == 0 ) {
      obj.usarDobleBuffer = true;
      System.out.println( "Doble Buffer Activado" );
      }
    else {
      obj.usarDobleBuffer = false;
      System.out.println( "Doble Buffer Desactivado" );
      }

    // Bucle de control de la animación, que seguirá funcionando, hasta
    // que se cierre la ventana con el botón de cierre del marco de esa
    // ventana
    while( true ) {
      // Control de la dirección en que se realiza la animación, de
      // forma que vaya creciendo hasta su tamaño normal y luego vuelva
      // a decrecer
      if( escala >= 0.999 )
        delta = -0.005;
      if( escala <= 0.015 )
        delta = 0.005;

      // Se establece el ancho y alto para la nueva imagen que se va a
      // presentar en la animación
      obj.finAncho = (int)( escala*obj.iniAncho );
      obj.finAlto = (int)( escala*obj.iniAlto );
      // Se presenta la imagen
      obj.repaint();

      // Se actualiza la escala para la siguiente imagen
      escala += delta;

      // Se retrasa la presentación, de forma que la animación se
      // encuentre en un rango aproximado de 20 imágenes por segundo
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

    // La imagen ya está cargada, así que se establece la anchura y
    // altura de la ventana, para que sus dimensiones se adecúen a la
    // imagen
    iniAncho = imagenFuente.getWidth( this );
    iniAlto = imagenFuente.getHeight( this );
    // Se hace visible el Frame
    this.setVisible( true );

    // Se obtienen los datos de insets del objeto, para que sean
    // compensados en la presentación de las imágenes
    insetArriba = this.getInsets().top;
    insetIzqda = this.getInsets().left;

    // Se usan las dimensiones de insets y el tamaño de la imagen
    // fuente para establecer el tamaño total del Frame
    this.setSize( insetIzqda+iniAncho,insetArriba+iniAlto );
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setBackground( Color.yellow );

    // Creamos una imagen para el uso del doble buffer, que será la que
    // se utilice en el intercambio para la renderización de la imagen
    // en el buffer oculto. También se obtiene el contexto gráfico con
    // el que se va a pintar
    imgDobleBuffer = this.createImage( iniAncho,iniAlto );
    contextoDobleBuffer = imgDobleBuffer.getGraphics();

    // Clase anidada que permite terminar la ejecución de la animación
    this.addWindowListener(
      // Definición de la clase anónima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      }
    );
    }

  // Se sobreescribe el método update() para eliminar el borrado
  // innecesario de la pantalla y el parpadeo que originaría este
  // borrado. Esto requiere que el método paint() sea el que controle
  // el borrado de la pantalla
  public void update( Graphics g ) {
    paint( g );
    }

  // Método paint() sobreescrito
  public void paint( Graphics g ) {
    // Si se está utilizando el doble buffer
    if( usarDobleBuffer && imgDobleBuffer != null ) {
      // Se vuelca en pantalla la imagen que previamente se había
      // construido en el buffer oculto
      g.drawImage( imgDobleBuffer,insetIzqda,insetArriba,this );

      // Se escala y pinta la siguiente imagen en el buffer oculto
      // utilizando las variables de instancia "finAncho" y "finAlto"
      // para determinar el tamaño de esa imagen. De esta forma
      // cuando se vuelva a invocar a paint() la imagen estará lista
      // para su presentación en pantalla.
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