//
//  java1331.java
//  Copyright (c) 1998,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 23-Jul-1998  10:07:10
//     Revision: 05-Feb-2002  06:07:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * El programa trata de mostrar la forma en que se pueden imprimir los
 * contenidos de un contendor AWT de forma selectiva, aunque se encuentre
 * embebido en otro contenedor.
 * Al indicar "de forma selectiva" se quiere expresar la diferencia que
 * existe con el ejemplo java1330.java, en donde se utilizaba el método
 * PrintAll() para imprimir todos los componentes que estaban contenidos
 * en el contendor. Sin mebargo, en este caso se pueden seleccionar los
 * componentes que se quieren imprimir, al igual que se puede seleccionar
 * la información que contienen esos componentes a la hora de imprimir.
 */
import java.awt.*;
import java.awt.event.*;

public class java1331 {
  public static void main( String args[] ) {
    // Se instancia un objeto de la clase Interfaz Gráfico
    IHM ihm = new IHM();
    }
  }

class IHM {
  Frame miFrame = new Frame( "Tutorial de Java, AWT" );

  // El contenedor panelAImprimir y todos los componentes que contiene,
  // serán impresos o enviados a un fichero de impresora
  Panel areaAImprimir = null;

  // Se colocan dos paneles sobre el Frame de forma que se pueda
  // seleccionar cualquiera de ellos. Son paneles propios, es decir,
  // que son creados exprofeso, ya que su apariencia es diferente y
  // la forma de imprimirse es distinta
  MiPanel0 panel0;
  MiPanel1 panel1;

  public IHM() {
    // Este botón hace que el contenedor que esté actualmente referenciado
    // por areaAImprimir se imprima a sí mismo
    Button botonImprimir = new Button( "Imprimir" );
    botonImprimir.addActionListener( new PrintActionListener() );
    miFrame.add( botonImprimir,"North" );

    // Los siguientes botones son los que se utilizan para seleccionar
    // cual de los dos paneles se presentará en pantalla y será el que
    // se imprima
    Button botonPanel0 =  new Button( "Selecciona Panel 0" );
    botonPanel0.addActionListener( new Panel0Listener() );
    miFrame.add( botonPanel0,"West" );

    Button botonPanel1 =  new Button( "Selecciona Panel 1" );
    botonPanel1.addActionListener( new Panel1Listener() );
    miFrame.add( botonPanel1,"East" );

    // Aquí se construyen los paneles que luego se asignarán a la
    // referencia areaAImprimir al hacer una selección. La rutina
    // de impresión hará que el contenedor referenciado por
    // areaAImprimir y todos sus componentes sean impresos
    panel0 = new MiPanel0();
    panel1 = new MiPanel1();

    // Es necesaria una referencia válida en areaAImprimir para
    // evitar la presencia de una excepción por puntero nulo
    // al realizar la selección e intentar eliminar la referencia
    // anterior
    areaAImprimir = panel0;

    miFrame.setSize( 340,200 );
    miFrame.setVisible( true );

    // Esta es una clase anidada anónima que se utiliza para
    // concluir la ejecución del programa cuando el usuario
    // decide cerrar el Frame
    miFrame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );
    }


  // Esta es una clase anidada utilizada para imprimir el
  // contenedor referenciado por areaAImprimir. Esto se consigue
  // aceptando un contexto de impresora y pasándoselo al
  // método paint() del contenedor referenciado por areaAImprimir.
  class PrintActionListener implements ActionListener {

    public void actionPerformed( ActionEvent evt ) {
      // Coge un objeto PrintJob. Esto hace que aparezca el
      // diálogo estándar de impresión, que si se cierra sin
      // imprimir devolverá un nulo
      PrintJob miPrintJob = miFrame.getToolkit().
        getPrintJob( miFrame,"Tutorial de Java, AWT",null );
      if ( miPrintJob != null ) {
        // Coge el objeto gráfico que va a imprimir
        Graphics graficoImpresion = miPrintJob.getGraphics();
        if ( graficoImpresion != null ) {
          // Invoca la método paint() del objeto Panel que se
          // ha creado para hacer que los componentes del
          // que sea se dibujen sobre el objeto gráfico y se
          // pinten sobbre el papel de la impresora
          areaAImprimir.paint( graficoImpresion );
          // Hacemos que se libere el papel de la impresora y los
          // recursos del sistema que estaba utilizando el
          // objeto gráfico
          graficoImpresion.dispose();
        }
        else
          System.out.println( "No se puede imprimir el objeto" );

        // Se concluye la impresión y se realiza la limpieza
        // necesaria
        miPrintJob.end();
      }
      else
        System.out.println( "Impresion cancelada" );
      }
    }


  // Esta es una de las clases propias que extienden a la clase
  // Panel, para conseguir el panel que se desea. Los objetos de
  // esta clase saben cómo imprimirse a sí mismos cuando se
  // invoca a su método paint() pasándole como parámetro un
  // objeto de tipo PrintGraphics. En el método sobrescrito
  // paint() es donde se indica la forma en que se imprime.
  class MiPanel0 extends Panel {
    // Estos son los componentes que contiens los datos que se
    // van a imprimir
    Label labPanel0;
    TextField textoPanel0;
    Button botonPanel0;

    // Este es el constructor para los objetos de la clase
    // Panel que se ha creado
    MiPanel0() {
      labPanel0 = new Label( "Panel 0" );
      this.add( labPanel0 );
      textoPanel0 = new TextField( "Texto" );
      this.add( textoPanel0 );
      botonPanel0 = new Button( "Boton Panel 0" );
      this.add( botonPanel0 );
      this.setBackground( Color.yellow );
      }

    // Este es el método sobrescrito paint(), que ni tan
    // siquiera hace el intento de manipular el tamaño de
    // la fuente de caracteres, porque con la versión del
    // JDK que estoy utilizando, hay un crash bestial
    public void paint( Graphics g ) {
      // Hay que separar el pintado sobre la pantalla de la
      // impresión sobre papel, de tal forma que esto último
      // solamente se ejecute en caso de que se pase como
      // parámetro un objeto de tipo PrintGraphics. En
      // cualquier otro caso, la información y componentes
      // aparecerá sobre la pantalla
      if ( g instanceof PrintGraphics ) {
        // Esta versión de paint() se limita a imprimir una
        // línea de cabecera y a extraer datos de los componentes
        // del Panel, imprimiéndolos en líneas sucesivas
        int margenIzqdo = 10; // Posición X de cada línea
        int margenSup = 20;   // Posición Y de la primera línea
        int pasoLinea = 13;   // Incremento o salto entre líneas

        // El cotnexto de impresión no tiene una fuente de
        // caracteres por defecto, así que hay que proporcionársela
        g.setFont( new Font( "Serif",Font.BOLD,18 ) );
        g.drawString( "Hola desde el Panel 0 del TUTORIAL",
          margenIzqdo,margenSup += pasoLinea );
        g.setFont( new Font( "Serif",Font.PLAIN,10 ) );
        g.drawString( "Texto de la Etiqueta: "+labPanel0.getText(),
          margenIzqdo,margenSup += pasoLinea );
        g.drawString( "Texto del Campo: "+textoPanel0.getText(),
          margenIzqdo,margenSup += pasoLinea );
        g.drawString( "Rotulo del Boton: "+botonPanel0.getLabel(),
          margenIzqdo,margenSup += pasoLinea );
        }
      // En el caso de que g no sea un objeto de tipo
      // PrintGraphics
      else
        // Se invoca el método paint() de la clase Panel
        super.paint( g );
      }
    }

  // Esta es la otra de las clases propias que extienden a la clase
  // Panel, para conseguir el panel que se desea. Los objetos de
  // esta clase saben cómo imprimirse a sí mismos cuando se
  // invoca a su método paint() pasándole como parámetro un
  // objeto de tipo PrintGraphics. En el método sobrescrito
  // paint() es donde se indica la forma en que se imprime.
  class MiPanel1 extends Panel {
    // Estos son los componentes que contiens los datos que se
    // van a imprimir
    Label labPanel1;
    TextField textoPanel1;
    Button botonPanel_0;
    Button botonPanel_1;

    MiPanel1(){
      labPanel1 = new Label("Panel 1");
      this.add(labPanel1);
      textoPanel1 = new TextField("Texto");
      this.add(textoPanel1);
      botonPanel_0 = new Button("Un Boton");
      this.add(botonPanel_0);
      botonPanel_1 = new Button("Otro Boton");
      this.add(botonPanel_1);
      this.setBackground(Color.red);
      }

    // Este es el método sobrescrito paint(), que ni tan
    // siquiera hace el intento de manipular el tamaño de
    // la fuente de caracteres, porque con la versión del
    // JDK que estoy utilizando, hay un crash bestial
    public void paint( Graphics g ) {
      // Hay que separar el pintado sobre la pantalla de la
      // impresión sobre papel, de tal forma que esto último
      // solamente se ejecute en caso de que se pase como
      // parámetro un objeto de tipo PrintGraphics. En
      // cualquier otro caso, la información y componentes
      // aparecerá sobre la pantalla
      if ( g instanceof PrintGraphics ) {
        // Esta versión de paint() se limita a imprimir una
        // línea de cabecera y a extraer datos de los componentes
        // del Panel, imprimiéndolos en líneas sucesivas
        int margenIzqdo = 10; // Posición X de cada línea
        int margenSup = 20;   // Posición Y de la primera línea
        int pasoLinea = 13;   // Incremento o salto entre líneas

        // El contexto de impresión no tiene una fuente de
        // caracteres por defecto, así que hay que proporcionársela
        g.setFont(new Font("Serif", Font.BOLD, 18));
        g.drawString( "Hola desde el Panel 1 del TUTORIAL",
          margenIzqdo,margenSup += pasoLinea );
        g.setFont( new Font( "Serif",Font.PLAIN,10 ) );
        g.drawString( "Texto de la Etiqueta: "+labPanel1.getText(),
          margenIzqdo,margenSup += pasoLinea );
        g.drawString( "Texto del Campo: "+textoPanel1.getText(),
          margenIzqdo,margenSup += pasoLinea );
        g.drawString( "Rotulo del Boton: "+botonPanel_0.getLabel(),
          margenIzqdo,margenSup += pasoLinea );
        g.drawString( "Rotulo del Boton: "+botonPanel_1.getLabel(),
          margenIzqdo,margenSup += pasoLinea );
        }
      // Esto en el caso de que g no se un objeto de tipo
      // PrintGraphics
      else
        super.paint( g );
      }
    }

  // Esta es una clase anidada que permite seleccionar e
  // imprimir el panel0. Evidentemente, esta clase y la quue
  // sigue, se pueden combinar en una sola que utilizaría
  // el origen del evento para determinar el panel que debe
  // enviar a la impresora
  class Panel0Listener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      miFrame.remove( areaAImprimir );
      areaAImprimir = panel0;
      miFrame.add( areaAImprimir,"Center" );
      miFrame.invalidate();   // Fuerza el repintado
      miFrame.setVisible( true );
      }
    }

  // Esta es una clase anidada que permite seleccionar e
  // imprimir el panel1
  class Panel1Listener implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      miFrame.remove( areaAImprimir );
      areaAImprimir = panel1;
      miFrame.add( areaAImprimir,"Center" );
      miFrame.invalidate();   // Fuerza el repintado
      miFrame.setVisible( true );
      }
    }
  }

//------------------------------------------ Final del fichero java1331.java