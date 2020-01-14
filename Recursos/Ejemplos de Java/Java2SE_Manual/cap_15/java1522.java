//
//  java1522.java
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
//     Creacion: 05-May-1999  05:54:54
//     Revision: 08-Feb-2002  06:10:35
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la manipulaci�n de im�genes. Se elimina el
 * componente rojo de todos los pixels de la imagen y se hace que sea
 * parcialmente transparente. Para su ejecuci�n es necesario el fichero de
 * imagen "muneco.gif" situado en el mismo directorio que las clases Java.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

class java1522 extends Frame {
  Image imagenFuente;                 // Imagen cargada del disco
  int iniAncho;
  int iniAlto;
  // Imagen modificada
  Image imagenNueva;
  // Valores del borde para el objeto contenedor
  int insetArriba;
  int insetIzqda;

  // M�todo de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    java1522 obj = new java1522();
    // Se llama directamente al m�todo que va a presentar la imagen
    // normal y la alterada
    obj.repaint();
    }

  // Constructor de la clase
  public java1522() {
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

    // Se obtienes los valores de insets para evitarlos luego
    insetArriba = this.getInsets().top;
    insetIzqda = this.getInsets().left;

    // Se usan las dimensiones de insets y el tama�o de la imagen
    // fuente para establecer el tama�o total del Frame. La altura se
    // hace doble, para que se puedan presentar la imagen original y
    // debajo de ella, la imagen modificada. Exactamente no es doble,
    // para permitir que la imagen modificada se superponga un poco
    // sobre la original.
    this.setSize( insetIzqda+iniAncho,insetArriba+(2*iniAlto-50) );
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setBackground( Color.yellow );

    // Se declara un array para guardar la representaci�n de la imagen
    // en pixels individuales
    int[] pix = new int[iniAncho * iniAlto];
    // Se convierte la "imagenFuente" a representaci�n num�rica que
    // corresponde a sus pixels, de forma que se puedan manipular
    // Esto hay que colocarlo en un bloque try-catch, porque tenemos
    // que estar prevenidos par recoger las excepciones de tipo
    // "InterrruptedException" que puede lanzar el m�todo grabPixels()
    try {
      // Se instancia un objeto de tipo PixelGrabber, pas�ndole como
      // par�metro el array de pixels en donde queremos guardar la
      // representaci�n num�rica de la imagen que vamos manipular
      PixelGrabber pgObj = new PixelGrabber( imagenFuente,
        0,0,iniAncho,iniAlto,pix,0,iniAncho );

      // Se invoca ahora el m�todo grabPixels() sobre el objeto de tipo
      // PixelGrabber que se acaba de instanciar, para la imagen se
      // convierta en un array de pixels. tambi�n se comprueba que el
      // proceso se realiza satisfactoriamente
      if( pgObj.grabPixels() &&
        ( (pgObj.getStatus() & ImageObserver.ALLBITS ) != 0 ) ) {
        // Ahora manipulamos la imagen, enmascaramos el byte ROJO de cada
        // uno de los pixels de la imagen, haciendo un AND con 0; y
        // tambi�n hacemos que la imagen se vuelva un poco transparente
        // haciendo un AND del byte del canal alfa con C0
        for( int i=0; i < (iniAncho*iniAlto); i++ ) {
          pix[i] = pix[i] & 0xC000FFFF;
          }
        }
      else {
        System.out.println( "Problemas al descomponer la imagen" );
        }
    } catch( InterruptedException e ) {
      System.out.println( e );
      }

    // Ahora se utiliza el m�todo createImage() para obtener una nueva
    // imagen a partir del array de pixels que hemos alterado
    imagenNueva = this.createImage( new MemoryImageSource(
      iniAncho,iniAlto,pix,0,iniAncho ) );

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

  // Sobrecargamos el m�todo paint() para presentar las dos im�genes
  // del ejemplo, la original leida del fichero, y la modificada que se
  // ha creado tras la manipulaci�n de los pixels de la original
  public void paint( Graphics g ) {
    if( imagenNueva != null ) {
      g.drawImage( imagenFuente,insetIzqda,insetArriba,this );
      // Colocamos la imagen nueva debajo de la original, superponi�ndola
      // ligeramente para poder comporbar el efecto de la modificaci�n
      // del canal alfa de esta nueva imagen, que dejar� ver la parte
      // de la imagen original sobre la que se encuentre
      g.drawImage( imagenNueva,insetIzqda,insetArriba+iniAlto-50,this );
      }
    }
  }

//------------------------------------------ Final del fichero java1522.java