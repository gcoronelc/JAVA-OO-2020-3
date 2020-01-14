//
//  java1502.java
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
//     Creacion: 24-Mar-1999  06:31:09
//     Revision: 08-Feb-2002  05:46:17
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo muy simple que lo único que pretende es mostrar el
 * mecanismo báscio de "callback" que utiliza el AWT a la hora de
 * realizar el repintado de la pantalla
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
public class java1502 {
  public static void main( String[] args ) {
    Frame f = new Frame( "Tutorial de Java, Gráficos" );

    // Clase anidada para controlar el cierre de la ventana y
    // concluir la aplicación
    f.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      });

    // Creamos una instancia de la carita y la incorporamos al Frame
    f.add( new CaraSonriente( Color.yellow ),BorderLayout.CENTER );
    f.pack();
    // Lo hacemos todo visible en pantalla
    f.show();
    }
  }

// Esta clase pinta una carita sonriente en la pantalla. Tenga en
// cuenta el lector, que esta clase extiende a un Canvas, que es
// un objeto "heavyweight", lo cual hace que la clase CaraSonriente
// también sea un objeto de tipo "heavyweight". Para convertirla
// en un objeto "lightweight" hay que extenter un Component, de
// forma que sería suficiente cambiar "extends Canvas" por
// "extends Component"
class CaraSonriente extends Canvas {
  // Constructor, que solamente fija el color de la carita
  public CaraSonriente( Color _colorCara ) {
    setForeground( _colorCara );
    }

  public Dimension getPreferredSize() {
    return new Dimension( 250,250 );
    }

  // Este el el método de verdad que pinta, y lo hace cada vez que
  // el AWT le indica que lo haga
  public void paint( Graphics g ) {
    // Calculamos dinámicamente todos los tamaños, de forma que
    // el canvas se puede cambiar externamente
    // La circunferencia que forma la carita, siempre utiliza
    // como dimensión para el diámetro la dimensión más pequeña
    // de la ventana en que se encuentra
    Dimension tamano = getSize();
    // Calculamos el diámetro total de la carita
    int diam = Math.min( tamano.width,tamano.height );
    // Fijamos el diámetro de los ojos a un décimo del de la cara
    int diamOjo = diam/10;
    // Calculamos el centro
    int x = (tamano.width-diam) / 2;
    int y = (tamano.height-diam) / 2;

    // Pintamos la cara, a la cual ya le ha fijado el color el
    // constructor de la clase
    g.fillOval( x,y,diam,diam );
    g.setColor( Color.black );
    g.drawOval( x,y,diam,diam );

    // Pintamos los dos ojos de la carita
    g.fillOval( x+diam/3-(diamOjo/2),y+diam/3-(diamOjo/2),
      diamOjo,diamOjo );
    g.fillOval( x+(2*(diam/3))-(diamOjo/2),y+diam/3-(diamOjo/2),
      diamOjo,diamOjo );

    // Pintamos la boca de la carita
    g.drawArc( x+diam/4,y+2*(diam/5),diam/2,diam/3,0,-180 );
    }
  }

//------------------------------------------ Final del fichero java1502.java