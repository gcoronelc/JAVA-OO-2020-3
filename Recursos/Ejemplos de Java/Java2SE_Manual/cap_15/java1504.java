//
//  java1504.java
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
//     Creacion: 30-Mar-1999  05:33:45
//     Revision: 08-Feb-2002  06:15:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se trata de mostrar el mecanismo que se utiliza en el
 * AWT a la hora del pintado de componentes ligeros, "lightweight", en el
 * AWT
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
public class java1504 {
  public static void main( String[] args ) {
    Frame f = new Frame( "Tutorial de Java, Gr�ficos" );
    f.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      });
    // Creamos el panel que va a contener la carita, que al extender
    // la clase Component, ser� un componente libero
    Container panel = new PanelMulticolor();
    // Creamos una instancia de la carita y la incorporamos al Frame
    // La carita en este caso es una cara colorada
    panel.add( new CaraSonriente( Color.red ) );
    f.add( panel,BorderLayout.CENTER );
    f.pack();
    // Lo hacemos todo visible en pantalla
    f.show();
    }
  }


// Clase que permite instanciar un contendedor cuyo fondo est�
// formado por barras multicolores
class PanelMulticolor extends Container {
  // Fijamos el array de colores que se va a utilizar para pintar los
  // vectores y que el fondo sea cada vez m�s multicolor
  protected Color colores[] = {
    Color.red, Color.yellow, Color.blue,
    Color.green, Color.pink, Color.orange,
    Color.white, Color.magenta, Color.cyan
    };

  // Constructor del panel
  public PanelMulticolor() {
    // El Layout por defecto del panel es null, as� que le asignamos
    // un flowlayout
    setLayout( new FlowLayout() );
    }

  // Este el el m�todo de verdad que pinta, y lo hace cada vez que
  // el AWT le indica que lo haga. Aunque en este caso, no es en s�
  // el m�todo que pinta, sino que al ser un componente ligero, lo
  // que hace es propagar la orden de pintado hacia su superclase, de
  // forma que el componente "heavyweight" que se encuentre m�s arriba
  // en el �rbol jer�rquico sea el que resliza el repintado de la
  // instancia del objeto
  public void paint( Graphics g ) {
    Dimension dim = getSize();
    int grosor = 5;
    int x = 0;

    // Se pintan rect�ngulos que van ocupando toda la ventana
    // del tama�o que se indica en el par�metro grosor
    for( int y=0; y+grosor <= dim.height; y+=grosor ) {
      g.setColor( colores[y % colores.length] );
      g.fillRect( 0,y,dim.width,grosor );
      }
    // Hay que llamar al m�todo paint de la superclase para que la
    // carita se pueda ver en la pantalla
    super.paint( g );
    }
  }

// Esta es la clase que permite pintar la cara en pantalla, que al
// extender a Component es un componente de tipo "ligero"
class CaraSonriente extends Component {
  protected Color colorCara;

  // Constructor de la carita, al que se le pasa como par�metro el
  // color de fondo de la cara
  public CaraSonriente( Color _colorCara ) {
    this.colorCara = _colorCara;
    }

  // Tama�o del contenedor por defecto
  public Dimension getPreferredSize() {
    return new Dimension( 250,250 );
    }

  // Este el el m�todo que pinta la carita, y lo hace cada vez que
  // el AWT le indica que lo haga
  public void paint( Graphics g ) {
    // Calculamos din�micamente todos los tama�os, de forma que
    // el canvas se puede cambiar externamente
    // La circunferencia que forma la carita, siempre utiliza
    // como dimensi�n para el di�metro la dimensi�n m�s peque�a
    // de la ventana en que se encuentra
    Dimension tamano = getSize();
    // Calculamos el di�metro total de la carita
    int diam = Math.min( tamano.width,tamano.height );
    // Fijamos el di�metro de los ojos a un d�cimo del de la cara
    int diamOjo = diam/10;
    // Calculamos el centro
    int x = (tamano.width-diam) / 2;
    int y = (tamano.height-diam) / 2;

    // Pintamos la cara, a la cual ya le ha fijado el color el
    // constructor de la clase
    g.setColor( colorCara );
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

//------------------------------------------ Final del fichero java1504.java