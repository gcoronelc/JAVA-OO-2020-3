//
//  java1505.java
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
//     Creacion: 05-May-1999  13:41:08
//     Revision: 08-Feb-2002  05:48:20
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * La intención de este ejemplo es demostrar el mecanismo básico de pintado
 * utilizado por los componentes Swing
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Clase de control del ejemplo
public class java1505 {
  public static void main( String[] args ) {
    JFrame f = new JFrame( "Tutorial de Java, Gráficos" );

    // Clase anidada para controlar el cierre de la ventana y
    // concluir la aplicación
    f.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      });

    // Se crea la diana como contenedor
    Container panel = new Diana();
    // Se le añade un texto
    panel.add( new JLabel( "Diana Central",SwingConstants.CENTER ),
      BorderLayout.CENTER );
    // Incorporamos la diana completa a la ventana
    f.getContentPane().add( panel,BorderLayout.CENTER );
    f.pack();
    // Se hace todo visible en pantalla
    f.show();
    }
  }

// Esta clase crea una diana de fondo, en donde la zona que se encuentra
// fuera de su campo es transparente
class Diana extends JPanel {
  // Constructor de la Diana
  public Diana() {
    super();
    // No queremos que se pinte todo
    setOpaque( false );
    setLayout( new BorderLayout() );
    // Le ponemos un bordecito
    setBorder( BorderFactory.createLineBorder(Color.black) );
    }

  public Dimension getPreferredSize() {
    // Aquí vemos cuales son las dimensiones del layout y luego le
    // añadimos 200 a la más larga de esas dimensiones para asegurarnos de
    // que la diana va a ser redonda
    Dimension tamLayout = super.getPreferredSize();
    int max = Math.max( tamLayout.width,tamLayout.height );
    return( new Dimension( max+200,max+200 ) );
    }

  // Sobrecargamos el método que pinta los componentes Swing, para que
  // se encargue de pintar la diana
  protected void paintComponent( Graphics g ) {
    Dimension tam = getSize();
    int x = 0;
    int y = 0;
    int i = 0;

    // Este es el bucle que va pintando las bandas concéntricas de la
    // diana
    while( x < tam.width && y < tam.height ) {
      // Fijamos el color de la diana, alternando entre blanco y rojo
      g.setColor( i%2==0 ? Color.red : Color.white );
      // Pintamos la banda que corresponde
      g.fillOval( x,y,tam.width-(2*x),tam.height-(2*y) );
      // Incrementamos las coordenadas
      x += 15;
      y += 15;
      // Y nos vamos a otra banda
      i++;
      }
    }
  }

//------------------------------------------ Final del fichero java1505.java