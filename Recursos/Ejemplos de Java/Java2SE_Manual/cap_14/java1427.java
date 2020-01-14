//
//  java1427.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 14-Sep-1998  13:21:31
//     Revision: 07-Feb-2002  06:09:47
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo de aplicaci�n �til utilizando Java y Swing.
 * Es un conversor de grados Cent�grados <--> Fahrenheit
 * Se utilizan muchos de los componentes que incorpora Swing, como son
 * JFrame, JCheckBoxMenuItem, JRadioButtonMenuItem, JLabel, JMenuBar,
 * JMenuItem, JPanel, JSlider y TitledBorder
 * Adem�s se muestra la forma en que se puede cambiar la apariencia de
 * ejecuci�n de la aplicaci�n, seleccionando uno u otro Look&Feel
 * entre los que se encuentren soportados por la plataforma sobre la
 * cual se est� ejecutando la aplicaci�n.
 * Tambi�n se muestra la forma de cambiar el cursor por defecto para
 * indicar acciones al usuario. En el ejemplo se utiliza el cursor de
 * de espera para indicar que se est� llevando a cabo el cambio de
 * Look de todos los Componentes que componen el interfaz de la
 * aplicaci�n, cuandos e cambia de Look&Feel
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.* ;
import javax.swing.border.*;

// La clase se base en un JFrame e implementa los interfaces que se
// indican
public class java1427 extends JFrame implements ActionListener,
SwingConstants, ChangeListener {
  // Modos de funcionamiento, o de conversi�n
  private static final int MODO_F2C = 1;
  private static final int MODO_C2F = 2;

  // Valores m�ximos y m�nimos de las barras de desplazamiento
  private static final int FMIN =   0;
  private static final int FMAX = 140;
  private static final int CMIN = -20;
  private static final int CMAX =  60;

  private static final Dimension vpad10 = new Dimension( 1,10 );

  // Modo inicial
  int modo = MODO_F2C;

  static java1427 MiFrame;
  private Container cliente;
  private JSlider fahrenheit;
  private JSlider centigrade;
  private JLabel valorF;
  private JLabel valorC;
  private JMenuBar barraMenu;
  private JMenu menuModo;
  private JCheckBoxMenuItem opcionF2C;
  private JCheckBoxMenuItem opcionC2F;
  private JMenu menuLaf;
  private ButtonGroup grupoLaf;
  private JRadioButtonMenuItem opcionLaf;
  private UIManager.LookAndFeelInfo laf[];

  // Este es el contrsuctor de la clase, que crea la barra del
  // men� superior, las barras de desplazamiento que van a ir
  // mostrando las conversiones de temperatura y registra los
  // receptores de eventos
  public java1427() {
    super( "Tutorial de Java, Swing" );

    // Los dos paneles correspondientes a cada sistema de grados
    JPanel panelFH;
    JPanel panelCG;

    barraMenu  = new JMenuBar();
    menuModo = new JMenu( "Modo" );
    menuLaf = new JMenu( "LookAndFeel" );
    grupoLaf = new ButtonGroup();

    // Se crean las opciones correspondientes a los modos de conversi�n
    // se a�aden a la barra superior
    opcionF2C = new JCheckBoxMenuItem(
      "Conversion Fahrenheit a Centigrados",true  );
    opcionC2F = new JCheckBoxMenuItem(
      "Conversion Centigrados a Fahrenheit",false );
    opcionF2C.addActionListener( this );
    opcionC2F.addActionListener( this );
    menuModo.add( opcionF2C );
    menuModo.add( opcionC2F );
    barraMenu.add( menuModo );
    // Se crean las opciones correspondientes a los look&feels
    // disponibles se a�aden a la barra superior
    UIManager.LookAndFeelInfo laf[] =
    UIManager.getInstalledLookAndFeels();
    for ( int i=0; i < laf.length; i++ ) {
      opcionLaf = new JRadioButtonMenuItem( laf[i].getName() );
      opcionLaf.addActionListener( this );
      if ( i == 0 )
        opcionLaf.setSelected( true );
      grupoLaf.add( opcionLaf );
      menuLaf.add( opcionLaf );
      }
    barraMenu.add( menuLaf );
    // Se a�ade la barra de men� al Frame
    this.setJMenuBar( barraMenu );
    // Se habilitan los eventos
    enableEvents( AWTEvent.WINDOW_EVENT_MASK );
    UIManager.installLookAndFeel( laf[0] );

    // El �rea cliente del frame, correspondiente a la parte
    // que queda por debajo de la barra de men�, se divide en
    // dos columnas
    cliente = this.getContentPane();
    //cliente.setLayout( new BoxLayout( cliente,BoxLayout.X_AXIS ) );
    cliente.setLayout( new GridLayout( 1,2 ) );

    // Se crea el panel sobre el que se va a colocar la barra
    // correspondiente a los grados Fahrenheit, situando en la parte
    // inferior una etiqueta con el valor num�rico correspondiente
    // a la conversi�n o al valor convertido
    panelFH = new JPanel();
    panelFH.setBorder( new TitledBorder( "Fahrenheit" ) );
    panelFH.setLayout( new BoxLayout( panelFH, BoxLayout.Y_AXIS ) );

    fahrenheit = new JSlider( VERTICAL,FMIN,FMAX,0 );
    fahrenheit.setMajorTickSpacing( 20 );
    fahrenheit.setMinorTickSpacing( 5 );
    fahrenheit.setPaintTicks( true );
    fahrenheit.setPaintLabels( true );
    fahrenheit.addChangeListener( this );

    panelFH.add( fahrenheit );
    panelFH.add( Box.createRigidArea( vpad10 ) );
    valorF = new JLabel( "00000",CENTER );
    panelFH.add( valorF );

    // Se crea el panel sobre el que se va a colocar la barra
    // correspondiente a los grados Cent�grados, situando en la parte
    // inferior una etiqueta con el valor num�rico correspondiente
    // a la conversi�n o al valor convertido
    panelCG = new JPanel() ;
    panelCG.setBorder( new TitledBorder( "Centigrados" ) );
    panelCG.setLayout( new BoxLayout( panelCG, BoxLayout.Y_AXIS ) );

    centigrade = new JSlider( VERTICAL,CMIN,CMAX,0 );
    centigrade.setMajorTickSpacing( 20 );
    centigrade.setMinorTickSpacing( 5 );
    centigrade.setPaintTicks( true );
    centigrade.setPaintLabels( true );
    centigrade.addChangeListener( this );
    centigrade.setEnabled( false );

    panelCG.add( centigrade );
    panelCG.add( Box.createRigidArea( vpad10 ) );
    valorC = new JLabel( "00000",CENTER );
    panelCG.add( valorC );

    // Se incorporan los paneles al frame
    cliente.add( panelFH );
    cliente.add( panelCG );

    // Se fuerzan las barras de desplazamiento a que se
    // inicialicen a valores predefinidos
    //fahrenheit.setValue( FMAX ) ;
    }

  // Control del cambio de valor en las barras de
  // desplazamiento. Se detecta el cambio en una y, por
  // simpat�a se muestra el valor convertido en la otra
  public void stateChanged( ChangeEvent ce ) {
    JSlider js = (JSlider)ce.getSource();

    int valor = js.getValue();

    //System.out.println( "Cambio estado -> "+valor );

    if ( js.equals(fahrenheit) && modo == MODO_F2C ) {
      valorF.setText( Integer.toString(valor) );
      actcualizaBarraCG( valor );
      }
    else if ( js.equals(centigrade) && modo == MODO_C2F ) {
      valorC.setText( Integer.toString(valor) );
      actualizaBarraFH( valor );
      }
    }


  // Actualiza la barra que muestra la temperatura en
  // grados Fahrenheit
  private void actualizaBarraFH( int c ) {
    int f;

    f = (int)( (c * 9) / 5 + 32 + 0.5 );
    valorF.setText( Integer.toString(f) );
    fahrenheit.setValue( f );
    }


  // Actualiza la barra que muestra la temperatura en
  // grados Cent�grados
  private void actcualizaBarraCG( int f ) {
    int c;

    c = (int)( (f-32) * 5 / 9 + 0.5 );
    valorC.setText( Integer.toString( c ) );
    centigrade.setValue( c );
    }


  // Proceso de los eventos de alto nivel. En realidad est�
  // control�ndose solamente cuando el usuario cierra la
  // ventana, para concluir ejecuci�n la aplicaci�n, el resto
  // se pasan hacia arriba en el �rbol jer�rquico
  public void processEvent( AWTEvent evt ) {
    if ( evt.getID() == WindowEvent.WINDOW_CLOSING ) {
      System.exit( 0 );
      }
    else {
      super.processEvent( evt );
      }
    }


  // Este m�todo es el que controla la acci�n por la cual
  // se ha demostrado inter�s, que en este caso concreto es
  // la opci�n que se selecciona entre las dos que presenta
  // la barra de men� y que permite que la conversi�n de
  // temperaturas sea en una direcci�n u otra
  // Y tambi�n el caso en que se quiera cambiar de apariencia
  // la ventana, para tener un look-and-feel diferente al que
  // se instala por defecto
  public void actionPerformed( ActionEvent evt ) {
    // Aqui controlamos el cambio de conversi�n
    if ( evt.getSource() instanceof JCheckBoxMenuItem ) {
      JCheckBoxMenuItem jc = (JCheckBoxMenuItem)evt.getSource();

      if ( jc.equals( opcionF2C ) ) {
        opcionC2F.setState( false );
        this.modo = MODO_F2C;
        fahrenheit.setEnabled( true );
        centigrade.setEnabled( false );
        }
      else {
        opcionF2C.setState( false );
        this.modo = MODO_C2F;
        centigrade.setEnabled( true );
        fahrenheit.setEnabled( false );
        }
      }
    // Y aqui el cambio de apariencia de la ventana en general
    if ( evt.getSource() instanceof JRadioButtonMenuItem ) {
      JRadioButtonMenuItem rb = (JRadioButtonMenuItem)evt.getSource();
      Component root = SwingUtilities.getRoot( MiFrame );
      // Aqu� se cambia la forma del cursor, para que aparezca el cursor
      // de espera predefinido por la plataforma sobre la que se est�
      // ejecutando el programa
      root.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
      try {
        if ( rb.isSelected() && rb.getText().equals("Windows") ) {
          UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
          SwingUtilities.updateComponentTreeUI(
            SwingUtilities.getRoot( MiFrame ) );
          }
        else if ( rb.isSelected() && rb.getText().equals("CDE/Motif") ) {
          UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel" );
          SwingUtilities.updateComponentTreeUI(
            SwingUtilities.getRoot( MiFrame ) );
          }
        else if ( rb.isSelected() && rb.getText().equals("Metal") ) {
          UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.metal.MetalLookAndFeel" );
          SwingUtilities.updateComponentTreeUI(
            SwingUtilities.getRoot( MiFrame ) );
          }
      } catch( UnsupportedLookAndFeelException e ) {
        // Si no est� soportado el Look&Feel que se acaba de seleccionar,
        // se presenta un mensaje al uso, se deshabilita el bot�n
        // correspondiente ala opci�n del men�
        // Una mejora ser�a poner el Look por defecto de la plataforma.
        // Teniendo cuidado de volver a capturar las excepciones
        System.err.println( "No esta soportado el LookAndFeel: "+
          rb.getText() );
        rb.setEnabled( false );
      } catch( Exception e ) {
        System.err.println( "No puedo cargar el LookAndFeel: "+
          rb.getText() );
        }
      // Finalmente, se recupera el cursor por defecto del sistema
      root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
      }
    }


  static public void main( String args[] ) {
    MiFrame = new java1427();
    MiFrame.setSize( 300,300 );
    MiFrame.setBackground( Color.lightGray );
    MiFrame.show();
    }
  }

//------------------------------------------ Final del fichero java1427.java