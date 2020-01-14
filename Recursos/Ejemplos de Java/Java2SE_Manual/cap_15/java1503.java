//
//  java1503.java
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
//     Creacion: 26-Mar-1999  06:24:34
//     Revision: 08-Feb-2002  05:47:05
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra la diferencia entre repintar una zona
 * completamente dentro de paint(), y el repintado incremental.
 * El repintado incremental solamente es �til en el caso de tener un fondo
 * muy complejo, porque sino, apenas ofrece ventaja alguna
 * Aparecer�n dos ventanas en pantalla, en la de la izquierda se observar�
 * la aparici�n de l�neas negras que van uniendo los puntos en que se va
 * pulsando el rat�n, observ�ndose parpadeo en cada una de las actualiza-
 * ciones que se realiza tras la incorporaci�n de un nuevo vector.
 * La diferencia con la ventana de la derecha es palpable, ya que en ella
 * no se nota absolutamente ning�n cambio en el fondo de la pantalla a la
 * hora de introducir nuevos vectores
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

// Clase de control del ejemplo
public class java1503 {
  public static void main(String[] args) {
    WindowListener conclusion = new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      };

    // Se crea el Frame en el que se utiliza paint() directamente
    Frame f1 = new Frame( "Parpadeo - Pulsa el rat�n para pintar l�neas" );
    f1.addWindowListener( conclusion );
    f1.add( new MiCanvas( new Vector()),BorderLayout.CENTER );
    f1.pack();
    f1.show();

    // Se crea el Frame en el que se realiza el repintado incremental a
    // trav�s de update()
    Frame f2 = new Frame( "Suavidad - Pulsa el rat�n para pintar l�neas" );
    f2.addWindowListener( conclusion );
    f2.add(new MiCanvasSuave( new Vector()),BorderLayout.CENTER );
    f2.pack();
    // Recogemos los l�mites del Frame anterior, para colocar el nuevo
    // a continuaci�n
    Rectangle limF1 = f1.getBounds();
    f2.setLocation( limF1.x+limF1.width,limF1.y );
    f2.show();
    }
  }

// Esta clase es un Canvas que grnera un fondo semicomplejo con vectores
// que se van creando seg�n se va haciendo click en diferentes posiciones
// de ese canvas.
// Pinta el �rea completa dentro de paint()
class MiCanvas extends Canvas {
  // Fijamos el array de colores que se va a utilizar para pintar los
  // vectores y que el fondo sea cada vez m�s multicolor
  protected Color colores[] = {
    Color.red, Color.yellow, Color.blue,
    Color.green, Color.pink, Color.orange,
    Color.white, Color.magenta, Color.cyan
    };
  protected Vector puntos;
  protected int vectoresPintados;

  public MiCanvas( Vector _puntos ) {
    this.puntos = _puntos;

    // Clase anidada para recoger los eventos de pulsaci�n del bot�n
    // delr at�n
    addMouseListener( new MouseAdapter() {
      public void mousePressed( MouseEvent evt ) {
        MiCanvas c = (MiCanvas)evt.getSource();
        // Se incorpora un nuevo vector
        c.puntos.addElement( evt.getPoint() );
        c.repaint();
        }
      });
    }

  public Dimension getPreferredSize() {
    // Tama�o del Canvas
    return new Dimension( 400,400 );
    }

/*
  El lector puede descomentar este m�todo para comprobar que si
  se sobreescribe el m�todo update() sin BORRAR el fondo, se ve
  afectado el parpadeo. No obstante, aunque ese parpadeo es menos
  dram�tico, todav�a se nota que se produce

  public void update( Graphics g ) {
    paint( g );
    }
*/

  // Se sobrecarga el m�todo paint()
  public void paint( Graphics g ) {
    // Se repinta todo, primero el fondo...
    pintaFondo(g);
    // ...y a continuaci�n, todos los vectores
    vectoresPintados = 0;
    for( int i=0 ; i < puntos.size()-1; i++ ) {
      pintaVector( g,i );
      }
    }

  // M�todo empleado para pintar el fondo
  protected void pintaFondo( Graphics g ) {
    Dimension dim = getSize();
    int grosor = 5;
    int x = 0;

    // Se pintan rect�ngulos que van ocupando toda la ventana
    // del tama�o que se indica en el par�metro grosor
    for( int y=0; y+grosor <= dim.height; y+=grosor ) {
      g.setColor( colores[y % colores.length] );
      g.fillRect( 0,y,dim.width,grosor );
      }
    }

  // Este m�todo pinta el vector que se encuentra en la posici�n
  // que se indica en el par�metro de llamada al m�todo
  protected void pintaVector( Graphics g,int _pos ) {
    Point p1 = (Point)puntos.elementAt( _pos );
    Point p2 = (Point)puntos.elementAt( _pos+1 );

    g.setColor( Color.black );
    g.drawLine( p1.x,p1.y,p2.x,p2.y );
    vectoresPintados++;
    }
  }

// Esta es una clase extendida de la anterior en la cual se utiliza el
// m�todo update para hacer que el repintado sea incremental, de forma
// que solamente se repintan los vectores que se hayan creado desde
// la �ltima vez que se realiz� una actualizaci�n del canvas
class MiCanvasSuave extends MiCanvas {
  // Constructor de la clase
  public MiCanvasSuave( Vector _puntos ) {
    super( _puntos );
    }

  // Sobrecargamos el m�todo para pintar incrementalmente
  public void update( Graphics g ) {
    // Solamente se repintan los vectores que se han incorporado desde
    // la �ltima llamada que se ha realizado al m�todo
    for( int i=vectoresPintados; i < puntos.size()-1; i++ ) {
      pintaVector( g,i );
      }
    }
  }

//------------------------------------------ Final del fichero java1503.java