//
//  java1330.java
//  Copyright (c) 1998,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 20-Jul-1998  15:22:40
//     Revision: 05-Feb-2002  06:06:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * El prop�sito de este programa es mostrar c�mo se imprimen componentes
 * desde el AWT
 */
import java.awt.*;
import java.awt.event.*;

public class java1330 {
  public static void main( String args[] ) {
    // Se instancia un objeto de la clase Interfaz Gr�fico
    IHM ihm = new IHM();
    }
  }

class IHM {
  // El contenedor miFrame y todos los componenete que contiene, ser�n
  // impresos o enviados a un fichero de impresora cuando se pulse el
  // bot�n con el r�tulo "Imprimir Frame"
  Frame miFrame = new Frame( "Tutorial de Java, AWT" );

  // El contenedor panelAImprimir y todos los componentes que contiene,
  // ser�n impresos o enviados a un fichero de impresora cuando se
  // pulse el bot�n con el r�tulo "Imprimir Panel"
  Panel panelAImprimir = null;

  // Referencias a los dos paneles seleccionables
  Panel panel0;
  Panel panel1;

  public IHM() {
    // Bot�n para imprimir el Panel
    Button botonImpPanel = new Button( "Imprimir Panel" );
    botonImpPanel.addActionListener( new PrintActionListener() );
    miFrame.add( botonImpPanel,"North" );

    Button botonImpFrame = new Button( "Imprimir Frame" );
    botonImpFrame.addActionListener( new PrintActionListener() );
    miFrame.add( botonImpFrame,"South" );

    // Los botones siguientes son los que se utilizan para seleccionar
    // entre los dos paneles el que se va a visualizar
    Button botonPanel0 = new Button( "Selecciona Panel 0" );
    botonPanel0.addActionListener( new Panel0Listener() );
    miFrame.add( botonPanel0,"West" );

    Button botonPanel1 =  new Button( "Selecciona Panel 1" );
    botonPanel1.addActionListener( new Panel1Listener() );
    miFrame.add( botonPanel1,"East" );

    // Aqu� se construyen los paneles que luego se asignar�n a la
    // referencia panelAImprimir al hacer una selecci�n. La rutina
    // de impresi�n har� que el contenedor referenciado por
    // panelAImprimir y todos sus componentes sean impresos
    panel0 = new Panel();
    Label labPanel0 = new Label( "Panel 0" );
    panel0.add( labPanel0 );

    TextField textoPanel0 = new TextField( "Texto" );
    panel0.add( textoPanel0 );
    panel0.add( new Button( "Boton" ) );
    panel0.setBackground( Color.yellow );

    panel1 = new Panel();
    Label labPanel1 = new Label( "Panel 1" );
    panel1.add( labPanel1 );
    TextField textoPanel1 = new TextField( "Texto" );
    panel1.add( textoPanel1 );
    panel1.add(new Button( "Un Boton" ) );
    panel1.add(new Button( "Otro Boton" ) );
    panel1.setBackground( Color.red );

    // Es necesaria una referencia v�lida en panelAImprimir para
    // evitar la presencia de una excepci�n por puntero nulo
    // al realizar la selecci�n e intentar eliminar la referencia
    // anterior
    panelAImprimir = panel0;

    miFrame.setSize( 340,200 );
    miFrame.setVisible( true );

    // Esta es una clase anidada an�nima que se utiliza para
    // concluir la ejecuci�n del programa cuando el usuario
    // decide cerrar el Frame
    miFrame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );
    }


  // Esta es una clase anidada utilizada para imprimir el
  // contenedor referenciado por panelAImprimir o el Frame
  // referenciado por miFrame
  class PrintActionListener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      // Coge un objeto PrintJob. Esto hace que aparezca el
      // di�logo est�ndar de impresi�n, que si se cierra sin
      // imprimir devolver� un nulo
      PrintJob miPrintJob = miFrame.getToolkit().
        getPrintJob( miFrame,"Tutorial de Java, AWT",null );
      if ( miPrintJob != null ) {
        // Coge el objeto gr�fico que va a imprimir
        Graphics graficoImpresion = miPrintJob.getGraphics();
        if ( graficoImpresion != null ) {
          // Invoca la m�todo printAll() del objeto Panel, o del
          // objeto Frame para hacer que los componentes del
          // que sea se dibujen sobre el objeto gr�fico y se
          // pinten sobbre el papel de la impresora
          if ( evt.getActionCommand().equals( "Imprimir Panel" ) )
            panelAImprimir.printAll( graficoImpresion );
          else
            miFrame.printAll( graficoImpresion );

          // Hacemos que se libere el papel de la impresora y los
          // recursos del sistema que estaba utilizando el
          // objeto gr�fico
          graficoImpresion.dispose();
        }
        else
          System.out.println( "No se puede imprimir el objeto" );
        // Se concluye la impresi�n y se realiza la limpieza
        // necesaria
        miPrintJob.end();
      }
      else
        System.out.println( "Impresion cancelada" );
      }
    }


  // Esta es una clase anidada que permite seleccionar e
  // imprimir el panel0
  class Panel0Listener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      miFrame.remove( panelAImprimir );
      panelAImprimir = panel0;
      miFrame.add( panelAImprimir,"Center" );
      miFrame.invalidate(); // Fuerza el redibujado
//      miFrame.repaint();
      // El m�todo repaint() se puede utilizar en lugar de
      // setVisible(), en el caso de que setVisible() haya
      // sido invocado previamente sobre el Frame que
      // contiene este Panel en alg�n sitio previo del
      // programa. No s� qu� es lo m�s eficiente, pero
      // en el caso de que repaint() sea m�s eficiente,
      // una posibilidad ser�a hacer un bucle a trav�s de
      // todos los pobibles panels al comienzo del
      // programa, invocando al m�todo setVisible( true )
      // para cada uno de ellos y as� poder utilizar luego
      // el m�todo repaint() en lugar de setVisible()
      miFrame.setVisible( true );
      }
    }

  // Esta es una clase anidada que permite seleccionar e
  // imprimir el panel1
  class Panel1Listener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      miFrame.remove( panelAImprimir );
      panelAImprimir = panel1;
      miFrame.add( panelAImprimir,"Center" );
      miFrame.invalidate(); // Fuerza el redibujado
//      miFrame.repaint();
      miFrame.setVisible( true );
      }
    }
  }

//------------------------------------------ Final del fichero java1330.java