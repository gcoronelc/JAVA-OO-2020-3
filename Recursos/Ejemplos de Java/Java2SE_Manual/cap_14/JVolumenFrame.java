//
//  JVolumenFrame.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 28-Dic-2001  14:10:13
//     Revision: 07-Feb-2002  05:59:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la clase que presenta la ventana de la aplicaci�n, en la cual se
 * crea el �rbol de directorios que presenta la gr�fica de los datos de
 * los ficheros, el men� principal, que permite la selecci�n de las
 * diferentes opciones de visualizaci�n y tambi�n permite la presentaci�n
 * de mensajes al usuario.
 * Tambi�n es posible cambiar la apariencia de la ventana, entre las
 * diferentes que permite Swing.
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
// Importamos los paquetes para el cambio de apariencia de la ventana
import com.sun.java.swing.plaf.motif.*;
import com.sun.java.swing.plaf.windows.*;
import javax.swing.plaf.metal.*;

// Este es el constructor principal, que crea los men�s superiores
// para permitir el control de la aplicaci�n
public class JVolumenFrame extends JFrame {
  // Textos y Teclas de acceso r�pido de las diferentes opciones del
  // men� principal de la aplicaci�n
  private static final String cArchivos = "Archivos";
  private static final char tArchivos = 'A';
  private static final String cSelRaiz = "Seleccionar nodo raiz...";
  private static final char tSelRaiz = 'r';
  private static final String cSalir = "Salir";
  private static final char tSalir = 'S';
  private static final String cVer = "Ver";
  private static final char tVer = 'V';
  private static final String cComparar = "Comparar";
  private static final char tComparar = 'C';
  private static final String cTamNodos = "Tama�o nodos";
  private static final char tTamNodos = 'T';
  private static final String cNumNodos = "N�mero nodos";
  private static final char tNumNodos = 'N';
  private static final String sRelativo = "Relativo a";
  private static final char tRelativo = 'R';
  private static final String cRelRaiz = "Nodo ra�z";
  private static final char tRelRaiz = 'R';
  private static final String cRelPadre = "Nodo padre";
  private static final char tRelPadre = 'P';
  private static final String cLookFeel = "Look & Feel";
  private static final char tLookFeel = 'L';
  private static final String cLWindows = "Windows";
  private static final char tLWindows = 'W';
  private static final String cLMotif = "Motif";
  private static final char tLMotif = 'o';
  private static final String cLMetal = "Metal";
  private static final char tLMetal = 'M';

  // Im�genes que se usar�n en la visualizaci�n del �rbol
  private static final Image[] imgVolumen;
  private static final Image imgVacio;
  private static final Image imgCalculando;

  // Cargamos las im�genes
  static {
    imgVolumen = new Image[] {
      cargaImagen( "vol_0.gif" ),
      cargaImagen( "vol_10.gif" ),
      cargaImagen( "vol_25.gif" ),
      cargaImagen( "vol_35.gif" ),
      cargaImagen( "vol_50.gif" ),
      cargaImagen( "vol_60.gif" ),
      cargaImagen( "vol_75.gif" ),
      cargaImagen( "vol_90.gif" ),
      cargaImagen( "vol_100.gif" )
      };

    imgVacio = cargaImagen( "vol_casivacio.gif" );
    imgCalculando = cargaImagen( "vol_espera.gif" );
    }

  // T�tulo de la ventana, al que se a�adir� el nodo ra�z
  private String titulo;
  // Actual nodo ra�z
  private Object raiz;
  // Opci�n del men� para seleccionar un nuevo nodo ra�z
  private JMenuItem selRaiz;
  // L�nea de estado mostrando el modo actual de visualizaci�n
  private JLabel mensajeEstado;
  // Arbol de vol�menes utilizado por esta ventana
  private JVolumenTree volArbol;
  // Clase de ayuda que se proporciona con el constructor
  private final VolumenNodoHelper volArbolHelper;

  // Fijamos los par�metros de la interfaz gr�fica e iniciamos
  // la construcci�n del �rbol
  public JVolumenFrame( final String _titulo,final Object _raiz,
    final VolumenNodoHelper _volArbolHelper ) {
    // Fijamos el t�tulo de la ventana
    super( _titulo );
    // Guardamos los par�metros que se han pasado al constructor
    titulo = _titulo;
    raiz = _raiz;
    volArbolHelper = _volArbolHelper;

    // Inicializamos el �rbol a partir del nodo ra�z que se haya
    // indicado, con la clase de ayuda y las im�genes
    volArbol = new JVolumenTree( _raiz,_volArbolHelper,
      imgVolumen,imgVacio,imgCalculando );
    // Fijamos el modo de presentaci�n para que se comparen los
    // tama�os relativos al nodo ra�z
    volArbol.setModoPresentacion( JVolumenTree.TAM_RELATIVO_RAIZ );
    // A�adimos el �rbol a la ventana, habilitando el desplazamiento
    getContentPane().add( new JScrollPane(volArbol),
     BorderLayout.CENTER );

    // Salimos de la aplicaci�n
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    // Fija el �rea de estado para presentar la informaci�n del
    // nodo ra�z actual
    final JPanel lineaEstado = new JPanel();
    lineaEstado.setBorder( BorderFactory.createEtchedBorder() );
    lineaEstado.setLayout( new FlowLayout(FlowLayout.LEFT,0,0) );
    mensajeEstado = new JLabel( raiz+"" );
    lineaEstado.add( mensajeEstado );
    getContentPane().add( lineaEstado,BorderLayout.SOUTH );

    // Inicializamos la barra de men� y la asignamos a la ventana
    final JMenuBar menuBarra = new JMenuBar();
    setJMenuBar( menuBarra );

    // Opci�n de Archivos
    final JMenu menuArchivos = new JMenu( cArchivos );
    menuArchivos.setMnemonic( tArchivos );
    menuBarra.add( menuArchivos );

    // Opci�n para seleccionar el nodo ra�z
    selRaiz = new JMenuItem( cSelRaiz,tSelRaiz );
    menuArchivos.add( selRaiz );

    // Opci�n de salir del men�
    final JMenuItem salir = new JMenuItem( cSalir,tSalir );
    menuArchivos.add( salir );
    salir.addActionListener( new ActionListener() {
      // Cuando se selecciona Salir, se cierra la ventana
      public void actionPerformed( ActionEvent evt ) {
        dispose();
        }
      });

    // Opci�n de Ver
    final JMenu menuVer = new JMenu( cVer );
    menuVer.setMnemonic( tVer );
    menuBarra.add( menuVer );

    // Opci�n de Comparar
    final JMenu menuComparar = new JMenu( cComparar );
    menuComparar.setMnemonic( tComparar );
    menuVer.add( menuComparar );
    // Agrupamos las condiciones de comparaci�n para que sean
    // mutuamente exclusivas
    final ButtonGroup grupoComparar = new ButtonGroup();
    // Compara el tama�o de los ficheros
    final JMenuItem compararTam = createRadioButtonMenuItem(
      cTamNodos,tTamNodos,menuComparar,grupoComparar );
    // Opci�n para comparar nodos
    compararTam.setSelected( true );
    final JMenuItem compararNum = createRadioButtonMenuItem(
      cNumNodos,tNumNodos,menuComparar,grupoComparar );

    // Opci�n Relativo a
    final JMenu menuRelativo = new JMenu( sRelativo );
    menuRelativo.setMnemonic( tRelativo );
    menuVer.add( menuRelativo );
    // Agrupamos las condiciones de comparaci�n para que sean
    // mutuamente exclusivas
    final ButtonGroup grupoRelativo = new ButtonGroup();
    // Opci�n Relativo al nodo ra�z
    final JMenuItem relativoRaiz = createRadioButtonMenuItem(
      cRelRaiz,tRelRaiz,menuRelativo,grupoRelativo );
    // Marcamos la selecci�n por defecto
    relativoRaiz.setSelected( true );
    // Opci�n Relativo al nodo padre
    final JMenuItem relativoPadre = createRadioButtonMenuItem(
      cRelPadre,tRelPadre,menuRelativo,grupoRelativo );

    // Receptor de eventos para control del cambio de modo de
    // visualizaci�n del �rbol
    final ActionListener relativoActionListener = new ActionListener() {
      // Cuando se selecciona un modo nuvo, se le aplica al
      // �rbol.
      public void actionPerformed( ActionEvent evt ) {
        if( compararTam.isSelected() )
          volArbol.setModoPresentacion( relativoRaiz.isSelected() ?
            JVolumenTree.TAM_RELATIVO_RAIZ :
            JVolumenTree.TAM_RELATIVO_PADRE );
        else
          volArbol.setModoPresentacion( relativoRaiz.isSelected() ?
            JVolumenTree.NUM_RELATIVO_RAIZ :
            JVolumenTree.NUM_RELATIVO_PADRE );
        // Actualizamos el modo en la l�nea de estado inferior
        actualizaLineaEstado();
        }
      };
    // A�adimos el receptor de eventos a los items del men�
    compararTam.addActionListener( relativoActionListener );
    compararNum.addActionListener( relativoActionListener );
    relativoRaiz.addActionListener( relativoActionListener );
    relativoPadre.addActionListener( relativoActionListener );

    // Opci�n Look&Feel
    final JMenu menuLookFeel = new JMenu( cLookFeel );
    menuLookFeel.setMnemonic( tLookFeel );
    menuVer.add( menuLookFeel );
    // Receptor de eventos de esta opci�n
    final ActionListener lookFeelActionListener = new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        setLookAndFeel( evt.getActionCommand() );
        }
      };
    // Grupo de botones que hace las opciones mutuamente exclusivas
    final ButtonGroup grupoLookFeel = new ButtonGroup();

    // Recoge el look actual de la plataforma para presentar la
    // ventana con esa apariencia
    final String plataforma = UIManager.getSystemLookAndFeelClassName();
    // Si el look actual no coincide, lo cambiamos
    if( !UIManager.getLookAndFeel().getName().equals(plataforma) ) {
      try {
        UIManager.setLookAndFeel( plataforma );
        SwingUtilities.updateComponentTreeUI( this );
      } catch( Exception e ) {
        e.printStackTrace();
        }
      }
    // Recogemos el nombre del llok actual
    final String lfActual = UIManager.getLookAndFeel().getName();
    // Recuperamos las apariencias disponibles
    final UIManager.LookAndFeelInfo[] lfInstalados =
      UIManager.getInstalledLookAndFeels();
    // Para cada apariencia instalada, se crea una opci�n en el men�
    // desplegable y se utiliza el primer car�cter de ese Look como
    // tecla r�pida (aunque puede haber varios que coincidan).
    // Se marca como seleccionada la apariencia actual
    for( int i=0; i < lfInstalados.length; i++ ) {
      final String nomLF = lfInstalados[i].getName();
      final JMenuItem menuItem = createRadioButtonMenuItem(
        nomLF,nomLF.charAt(0),menuLookFeel,grupoLookFeel );
      menuItem.addActionListener( lookFeelActionListener );
      if( nomLF.equals(lfActual) )
        menuItem.setSelected( true );
        }

    // Colocamos el nodo en el t�tulo de la ventana
    setTitle( mergeTitle(_titulo,_raiz) );
    // Actualizamos la l�nea de estado inferior
    actualizaLineaEstado();
    }

  // Devuelve el nodo ra�z actual, o null si no hay ninguno
  public Object getRoot() {
    return( raiz );
    }

  // Fija el nodo ra�z
  public void setRoot( final Object _raiz ) {
    raiz = _raiz;
    volArbol.setRoot( raiz,volArbolHelper );
    setTitle( mergeTitle(titulo,_raiz) );
    }

  // A�ade un receptor para la opci�n de selecci�n de un nuevo
  // nodo ra�z
  public void addRootActionListener(
    final ActionListener raizActionListener ) {
    selRaiz.addActionListener( raizActionListener );
    }

  // Elimina el receptor para la opci�n de selecci�n de un nuevo
  // nodo ra�z
  public void removeRootActionListener(
    final ActionListener raizActionListener ) {
    selRaiz.removeActionListener( raizActionListener );
    }

  // Devuelve un JRadioButtonMenuItem en base al texto y tecla r�pida,
  // despu�s de a�adirlo al men� padre y al grupo de botones
  private JRadioButtonMenuItem createRadioButtonMenuItem(
    final String texto,final char tecla,final JMenu padre,
    final ButtonGroup grupo ) {
    final JRadioButtonMenuItem elemento = new JRadioButtonMenuItem( texto );
    elemento.setMnemonic( tecla );
    padre.add( elemento );
    grupo.add( elemento );
    return( elemento );
    }

  // Devuelve la cadena del t�tulo generada con el texto original y el
  // nodo ra�z
  private String mergeTitle( final String texto,final Object raiz ) {
    return( texto+" - "+raiz );
    }

  // Actualiza la linea de estado, fundamentalmente para indicar el
  // modo de presentaci�n actual del �rbol
  private void actualizaLineaEstado() {
    final JVolumenTree.ModoPresentacion modo = volArbol.getModoPresentacion();
    final StringBuffer buff = new StringBuffer(50);
    buff.append(cComparar);
    buff.append(" ");
    if( (modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
      (modo == JVolumenTree.TAM_RELATIVO_PADRE))
      buff.append(cTamNodos.toLowerCase() );
    else
      buff.append( cNumNodos.toLowerCase() );
    buff.append( " " );
    buff.append( sRelativo.toLowerCase() );
    buff.append( " " );
    if( (modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
      (modo == JVolumenTree.NUM_RELATIVO_RAIZ) )
      buff.append( cRelRaiz.toLowerCase() );
    else
      buff.append( cRelPadre.toLowerCase() );
    mensajeEstado.setText( buff.toString() );
    }

  // Fija la apariencia de la ventana en funci�n de la cadena que
  // se pase como par�metro
  private void setLookAndFeel( final String lf ) {
    // Recupera los Look & Feel instalados
    final UIManager.LookAndFeelInfo[] lfInstalados =
      UIManager.getInstalledLookAndFeels();

    // Localiza y establece la nueva apariencia de la ventana en
    // funci�n de la cadena utilizada en el men� de texto para
    // seleccionar un look & feel
    boolean set = false;
    for( int i=0; !set && (i < lfInstalados.length); i++ ) {
      final String nomLF = lfInstalados[i].getName();
      if( nomLF.equals(lf) ) {
        set = true;
        try {
          UIManager.setLookAndFeel(lfInstalados[i].getClassName());
          SwingUtilities.updateComponentTreeUI(this);
        } catch( Exception e ) {
          e.printStackTrace();
          }
        }
      }
    }

  // Devuelve una imagen en base al nombre que se pase. La imagen
  // se supone que reside en el mismo directorio en que se encuentre
  // esta clase
  private static Image cargaImagen( final String nomImagen ) {
    // Inicializamos la imagen a null (por si no existiese)
    Image imagen = null;
    imagen = Toolkit.getDefaultToolkit().getImage( nomImagen );

    return( imagen );
    }
  }

//------------------------------------- Final del fichero JVolumenFrame.java
