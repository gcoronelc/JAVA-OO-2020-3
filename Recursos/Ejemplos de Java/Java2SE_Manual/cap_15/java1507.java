//
//  java1507.java
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
//     Creacion: 05-Apr-1999  06:19:39
//     Revision: 08-Feb-2002  05:50:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo crea dos botones falsos que semejan a los botones normales
 * con efecto 3D incluido, aunque ni se utilice la clase Button ni la clase
 * Graphics (m�todo draw3DRect().
 * Se simula el efecto de pulsaci�n de un bot�n normal, y el efecto 3D es
 * totalmente artificial, por la forma de pintar el rect�ngulo sobre el
 * Canvas. En las pulsaci�nes se imprimen mensajes en consola para
 * indicar que la pulsaci�n ha tenido lugar.
 * La respuesta de los falsos botones es mucho m�s r�pida que la del
 * bot�n real, o por lo mens, responden un porcentaje de veces mucho m�s
 * elevado cuando se pulsan repetidamente
 */
import java.awt.*;
import java.awt.event.*;

// Esta es la clase que crea el falso bot�n. Se sobreescribe el m�todo
// paint() para fijar el tama�o y color del objeto Canvas, para pintar
// un rect�ngulo parcialmente oculto sobre ese objeto Canvas, y para
// escribir un texto sobre el objeto Canvas.
// El hecho de que el rect�ngulo que se pinta est� parcialmente oculto
// es lo que hace que parezca que el bot�n sobresale del Canvas, o que
// d� la impresi�n de que se hunde al pulsar sobre �l.
// El offset con que se pinta el rect�ngulo es el que hace que haya dos
// l�neas que se pierdan, semejando a las sombras que provocan la
// ilusi�n del efecto 3D
class BotonFalso extends Canvas {
  boolean pulsado = false;  // Indica si el bot�n est� pulsado o no
  int ancho;                // Anchura deo bot�n en pixels
  int alto;                 // Altura del bot�n en pixels
  String rotulo;            // Texto del bot�n

  // Constructor del Falso Bot�n
  public BotonFalso( int _ancho,int _alto,String _rotulo ) {
    this.ancho = _ancho;
    this.alto = _alto;
    this.rotulo = _rotulo;
    this.setBackground( Color.green );
    this.setSize( this.ancho,this.alto );
    }

  public void paint( Graphics g ) {
    // Determinamos el desplazamiento seg�n la situaci�n en que se
    // encuentre el bot�n y se pinta el rect�ngulo sobre el objeto Canvas
    if( pulsado )
      g.drawRect( 1,1,ancho,alto );
    else
      g.drawRect( -1,-1,ancho,alto );

    // Se escribe el texto del r�tulo del bot�n centrado horizontalmente
    // y ligeramente desplazado verticalmente. Para este desplazamiento
    // vertical, se utiliza 1/4 de la altura de la fuente de caracteres
    // que se est� utilizando, para calcular la l�nea base sobre la que
    // se van a colocar los caracteres del texto
    int altoFuente = g.getFontMetrics().getHeight();
    int anchoCadena = g.getFontMetrics().stringWidth( rotulo );
    g.drawString( rotulo,
      (ancho-anchoCadena)/2,(alto/2)+(altoFuente/4) );
    }
  }

// Clase de control del ejemplo, con el m�todo main() para poder lanzarla
// como aplicaci�n individual
class java1507 extends Frame {
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1507();
    }

  // Constructor
  public java1507() {
    this.setLayout( new FlowLayout() );
    this.setTitle( "Tutorial de Java, Gr�ficos" );
    this.setSize( 300,80 );

    // Se instancian dos botones falsos de tama�os diferentes y un bot�n
    // real instanciando un objeto de la clase Button
    BotonFalso botFalsoCorto = new BotonFalso( 40,20,"Corto" );
    BotonFalso botFalsoLargo = new BotonFalso( 80,20,"Largo" );
    Button botAutentico = new Button( "Boton" );

    // Se a�aden todos los botones al objeto Frame
    this.add( botFalsoCorto );
    this.add( botAutentico );
    this.add( botFalsoLargo );
    // Se hacen visibles
    this.setVisible( true );

    // Receptor de eventos del rat�n, que se registra para que reciba los
    // eventos de rat�n que se generen sobre los dos botones falsos
    ProcesoRaton procesoRaton = new ProcesoRaton();
    botFalsoCorto.addMouseListener( procesoRaton );
    botFalsoLargo.addMouseListener( procesoRaton );

    // Se instancia y registra un objeto receptor de los eventos de tipo
    // Action que se generen sobre el bot�n real de tipo Button
    botAutentico.addActionListener( new ProcesoBoton() );

    // Se instancia y registra un objeto recetor de los eventos sobre la
    // ventana para que termine decentemente la ejecuci�n cuando se cierre
    // esa ventana
    ProcesoVentana procesoVentana = new ProcesoVentana();
    this.addWindowListener( procesoVentana );
    }
  }

// Esta clase es la que se utiliza para instanciar un objeto que reciba
// los eventos del rat�n que se produzcan sobre los botones falsos y hace
// que las imagen visual del bot�n falso cambie entre "pulsado" y normal
// para simular el efecto que se produce en los botones normales de
// que se hunden cuando se pulsa sobre ellos
class ProcesoRaton extends MouseAdapter {
  // Se sobreescribe el m�todo que recibe los eventos de pulsaci�n del
  // rat�n
  public void mousePressed( MouseEvent evt ) {
    // Se fija la variable de que est� el bot�n pulsado y se repinta
    ((BotonFalso)evt.getComponent()).pulsado = true;
    evt.getComponent().repaint();
    }

  // Se sobreescribe el m�todo que recibe los eventos de que el rat�n
  // se ha soltadpo
  public void mouseReleased( MouseEvent evt ) {
    // Se presenta un mensaje en consola para indicar que el bot�n se
    // ha soltado
    System.out.println( "mouseReleased" );
    // Se fija la variable de que el bot�n del rat�n se ha liberado y
    // se repinta el bot�n sobre el Canvas
    ((BotonFalso)evt.getComponent()).pulsado = false;
    evt.getComponent().repaint();
    }
  }

// Esta clase es la que se utiliza para instanciar un objeto que reciba
// los eventos de tipo Action que se producen cuando se pulsa sobre un
// bot�n normal instanciado de la clase Button.
class ProcesoBoton implements ActionListener {
  public void actionPerformed( ActionEvent evt ) {
    // Se presenta un mensaje en consola indicando el tipo de evento para
    // que se sepa que se ha pulsado el bot�n normal
    System.out.println( "actionPerformed" );
    }
  }

// Este es el receptor utilizado para concluir el programa cuando el
// usuario pulsa sobre el bot�n de cerrar la ventana situado en el
// marco de esa ventana
class ProcesoVentana extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1507.java