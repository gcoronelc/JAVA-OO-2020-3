//
//  java1517.java
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
//     Creacion: 08-May-1999  08:34:14
//     Revision: 08-Feb-2002  05:56:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de fuentes del Sistema. Como puede
 * comprobar el lector, Java ya no se limita a las fuentes que proporciona
 * el JDK, sino que es capaz de reconocer todas las fuentes instaladas
 * en el Sistema.
 * En el ejemplo, los n�meros que se utilizan para acceder al array y
 * visualizar las fuentes correspondientes son aleatorios, no tienen
 * ninguna otra intenci�n.
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1517 extends Frame {

  // Contructor de la clase
  public java1517() {
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setSize( 400,300 );
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
      } );
    }

  // M�todo de control del programa
  public static void main( String[] args ) {
    // Se instancia un objeto de esta clase
    new java1517();
    }


  // Se sobrecarga el m�todo paint()
  public void paint( Graphics g ) {
    // Trasladamos el origen de coordenadas que se sit�a en la
    // esquina superior izquierda, para evitar el problema que se
    // produce con insets.
    g.translate( this.getInsets().left,this.getInsets().top );

    // Obtenemos la lista completa de fuentes del sistema
    String[] fuentes =
      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    // Fijamos una fuente cualquiera, la quinta de la lista por ejemplo,
    // le cambiamos el estilo a Negrita y su tama�o a 14 puntos
    g.setFont( new Font( fuentes[4],Font.PLAIN,14 ) );
    // Obtenemos la altura de la fuente seleccionada
    int alto = g.getFontMetrics().getHeight();
    // Fijamos la coordena Y utilizando la altura de la fuente como valor
    int y = alto;

    // Presentamos un r�tulo general para la presentaci�n
    String rotulo = "Algunas de las fuentes disponibles son:";
    g.drawString( rotulo,5,y );

    // Cambiamos a color rojo
    g.setColor( Color.red );

    // Presentamos una peque�a lista de todas las fuentes del sistema
    // indicando el nombre que tiene almacenado el sistema para ellas,
    // le cambiamos el estino a Negrita y Oblicua
    for( int i=12; i < 18; i++ ) {
      g.setFont( new Font( fuentes[i],Font.BOLD | Font.ITALIC,18) );
      alto = g.getFontMetrics().getHeight();
      g.drawString( fuentes[i],
        160-(g.getFontMetrics().stringWidth(fuentes[i]))/2,y+=alto );
      }

    // Cambiamos a color azul
    g.setColor( Color.blue );
    // Volvemos a coger la fuente del r�tulo y le cambiamos el estilo a
    // fuente Normal de 15 puntos
    g.setFont( new Font( fuentes[4],Font.PLAIN,15 ) );
    alto = g.getFontMetrics().getHeight();
    // Presentamos otro r�tulo
    y += alto;
    g.drawString( "Informaci�n sobre la fuente de este texto: ",5,y );
    // Presentamos ifnormaci�n sobre la fuente actual
    y += alto;
    g.drawString( "" + g.getFont(),5,y );

    // Presentamos otro r�tulo
    y += 2*alto;
    g.drawString( "Aqu� est� parte de este Tutorial: ",5,y );

    // Creamos un array de letras para presentarlo
    char aTexto[] =
      { 'T','u','t','o','r','i','a','l',' ','d','e',' ','J','a','v','a' };
    y += alto;
    // Presentamos en pantalla el array de letras, salt�ndonos el
    // primero y no presentado el �ltimo
    g.drawChars( aTexto,1,13,5,y );
    }
  }

//------------------------------------------ Final del fichero java1517.java