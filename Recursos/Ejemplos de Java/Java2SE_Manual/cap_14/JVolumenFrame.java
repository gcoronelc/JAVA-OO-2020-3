//
//  JVolumenFrame.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 28-Dic-2001  14:10:13
//     Revision: 07-Feb-2002  05:59:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la clase que presenta la ventana de la aplicación, en la cual se
 * crea el árbol de directorios que presenta la gráfica de los datos de
 * los ficheros, el menú principal, que permite la selección de las
 * diferentes opciones de visualización y también permite la presentación
 * de mensajes al usuario.
 * También es posible cambiar la apariencia de la ventana, entre las
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

// Este es el constructor principal, que crea los menús superiores
// para permitir el control de la aplicación
public class JVolumenFrame extends JFrame {
  // Textos y Teclas de acceso rápido de las diferentes opciones del
  // menú principal de la aplicación
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
  private static final String cTamNodos = "Tamaño nodos";
  private static final char tTamNodos = 'T';
  private static final String cNumNodos = "Número nodos";
  private static final char tNumNodos = 'N';
  private static final String sRelativo = "Relativo a";
  private static final char tRelativo = 'R';
  private static final String cRelRaiz = "Nodo raíz";
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

  // Imágenes que se usarán en la visualización del árbol
  private static final Image[] imgVolumen;
  private static final Image imgVacio;
  private static final Image imgCalculando;

  // Cargamos las imágenes
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

  // Título de la ventana, al que se añadirá el nodo raíz
  private String titulo;
  // Actual nodo raíz
  private Object raiz;
  // Opción del menú para seleccionar un nuevo nodo raíz
  private JMenuItem selRaiz;
  // Línea de estado mostrando el modo actual de visualización
  private JLabel mensajeEstado;
  // Arbol de volúmenes utilizado por esta ventana
  private JVolumenTree volArbol;
  // Clase de ayuda que se proporciona con el constructor
  private final VolumenNodoHelper volArbolHelper;

  // Fijamos los parámetros de la interfaz gráfica e iniciamos
  // la construcción del árbol
  public JVolumenFrame( final String _titulo,final Object _raiz,
    final VolumenNodoHelper _volArbolHelper ) {
    // Fijamos el título de la ventana
    super( _titulo );
    // Guardamos los parámetros que se han pasado al constructor
    titulo = _titulo;
    raiz = _raiz;
    volArbolHelper = _volArbolHelper;

    // Inicializamos el árbol a partir del nodo raíz que se haya
    // indicado, con la clase de ayuda y las imágenes
    volArbol = new JVolumenTree( _raiz,_volArbolHelper,
      imgVolumen,imgVacio,imgCalculando );
    // Fijamos el modo de presentación para que se comparen los
    // tamaños relativos al nodo raíz
    volArbol.setModoPresentacion( JVolumenTree.TAM_RELATIVO_RAIZ );
    // Añadimos el árbol a la ventana, habilitando el desplazamiento
    getContentPane().add( new JScrollPane(volArbol),
     BorderLayout.CENTER );

    // Salimos de la aplicación
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    // Fija el área de estado para presentar la información del
    // nodo raíz actual
    final JPanel lineaEstado = new JPanel();
    lineaEstado.setBorder( BorderFactory.createEtchedBorder() );
    lineaEstado.setLayout( new FlowLayout(FlowLayout.LEFT,0,0) );
    mensajeEstado = new JLabel( raiz+"" );
    lineaEstado.add( mensajeEstado );
    getContentPane().add( lineaEstado,BorderLayout.SOUTH );

    // Inicializamos la barra de menú y la asignamos a la ventana
    final JMenuBar menuBarra = new JMenuBar();
    setJMenuBar( menuBarra );

    // Opción de Archivos
    final JMenu menuArchivos = new JMenu( cArchivos );
    menuArchivos.setMnemonic( tArchivos );
    menuBarra.add( menuArchivos );

    // Opción para seleccionar el nodo raíz
    selRaiz = new JMenuItem( cSelRaiz,tSelRaiz );
    menuArchivos.add( selRaiz );

    // Opción de salir del menú
    final JMenuItem salir = new JMenuItem( cSalir,tSalir );
    menuArchivos.add( salir );
    salir.addActionListener( new ActionListener() {
      // Cuando se selecciona Salir, se cierra la ventana
      public void actionPerformed( ActionEvent evt ) {
        dispose();
        }
      });

    // Opción de Ver
    final JMenu menuVer = new JMenu( cVer );
    menuVer.setMnemonic( tVer );
    menuBarra.add( menuVer );

    // Opción de Comparar
    final JMenu menuComparar = new JMenu( cComparar );
    menuComparar.setMnemonic( tComparar );
    menuVer.add( menuComparar );
    // Agrupamos las condiciones de comparación para que sean
    // mutuamente exclusivas
    final ButtonGroup grupoComparar = new ButtonGroup();
    // Compara el tamaño de los ficheros
    final JMenuItem compararTam = createRadioButtonMenuItem(
      cTamNodos,tTamNodos,menuComparar,grupoComparar );
    // Opción para comparar nodos
    compararTam.setSelected( true );
    final JMenuItem compararNum = createRadioButtonMenuItem(
      cNumNodos,tNumNodos,menuComparar,grupoComparar );

    // Opción Relativo a
    final JMenu menuRelativo = new JMenu( sRelativo );
    menuRelativo.setMnemonic( tRelativo );
    menuVer.add( menuRelativo );
    // Agrupamos las condiciones de comparación para que sean
    // mutuamente exclusivas
    final ButtonGroup grupoRelativo = new ButtonGroup();
    // Opción Relativo al nodo raíz
    final JMenuItem relativoRaiz = createRadioButtonMenuItem(
      cRelRaiz,tRelRaiz,menuRelativo,grupoRelativo );
    // Marcamos la selección por defecto
    relativoRaiz.setSelected( true );
    // Opción Relativo al nodo padre
    final JMenuItem relativoPadre = createRadioButtonMenuItem(
      cRelPadre,tRelPadre,menuRelativo,grupoRelativo );

    // Receptor de eventos para control del cambio de modo de
    // visualización del árbol
    final ActionListener relativoActionListener = new ActionListener() {
      // Cuando se selecciona un modo nuvo, se le aplica al
      // árbol.
      public void actionPerformed( ActionEvent evt ) {
        if( compararTam.isSelected() )
          volArbol.setModoPresentacion( relativoRaiz.isSelected() ?
            JVolumenTree.TAM_RELATIVO_RAIZ :
            JVolumenTree.TAM_RELATIVO_PADRE );
        else
          volArbol.setModoPresentacion( relativoRaiz.isSelected() ?
            JVolumenTree.NUM_RELATIVO_RAIZ :
            JVolumenTree.NUM_RELATIVO_PADRE );
        // Actualizamos el modo en la línea de estado inferior
        actualizaLineaEstado();
        }
      };
    // Añadimos el receptor de eventos a los items del menú
    compararTam.addActionListener( relativoActionListener );
    compararNum.addActionListener( relativoActionListener );
    relativoRaiz.addActionListener( relativoActionListener );
    relativoPadre.addActionListener( relativoActionListener );

    // Opción Look&Feel
    final JMenu menuLookFeel = new JMenu( cLookFeel );
    menuLookFeel.setMnemonic( tLookFeel );
    menuVer.add( menuLookFeel );
    // Receptor de eventos de esta opción
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
    // Para cada apariencia instalada, se crea una opción en el menú
    // desplegable y se utiliza el primer carácter de ese Look como
    // tecla rápida (aunque puede haber varios que coincidan).
    // Se marca como seleccionada la apariencia actual
    for( int i=0; i < lfInstalados.length; i++ ) {
      final String nomLF = lfInstalados[i].getName();
      final JMenuItem menuItem = createRadioButtonMenuItem(
        nomLF,nomLF.charAt(0),menuLookFeel,grupoLookFeel );
      menuItem.addActionListener( lookFeelActionListener );
      if( nomLF.equals(lfActual) )
        menuItem.setSelected( true );
        }

    // Colocamos el nodo en el título de la ventana
    setTitle( mergeTitle(_titulo,_raiz) );
    // Actualizamos la línea de estado inferior
    actualizaLineaEstado();
    }

  // Devuelve el nodo raíz actual, o null si no hay ninguno
  public Object getRoot() {
    return( raiz );
    }

  // Fija el nodo raíz
  public void setRoot( final Object _raiz ) {
    raiz = _raiz;
    volArbol.setRoot( raiz,volArbolHelper );
    setTitle( mergeTitle(titulo,_raiz) );
    }

  // Añade un receptor para la opción de selección de un nuevo
  // nodo raíz
  public void addRootActionListener(
    final ActionListener raizActionListener ) {
    selRaiz.addActionListener( raizActionListener );
    }

  // Elimina el receptor para la opción de selección de un nuevo
  // nodo raíz
  public void removeRootActionListener(
    final ActionListener raizActionListener ) {
    selRaiz.removeActionListener( raizActionListener );
    }

  // Devuelve un JRadioButtonMenuItem en base al texto y tecla rápida,
  // después de añadirlo al menú padre y al grupo de botones
  private JRadioButtonMenuItem createRadioButtonMenuItem(
    final String texto,final char tecla,final JMenu padre,
    final ButtonGroup grupo ) {
    final JRadioButtonMenuItem elemento = new JRadioButtonMenuItem( texto );
    elemento.setMnemonic( tecla );
    padre.add( elemento );
    grupo.add( elemento );
    return( elemento );
    }

  // Devuelve la cadena del título generada con el texto original y el
  // nodo raíz
  private String mergeTitle( final String texto,final Object raiz ) {
    return( texto+" - "+raiz );
    }

  // Actualiza la linea de estado, fundamentalmente para indicar el
  // modo de presentación actual del árbol
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

  // Fija la apariencia de la ventana en función de la cadena que
  // se pase como parámetro
  private void setLookAndFeel( final String lf ) {
    // Recupera los Look & Feel instalados
    final UIManager.LookAndFeelInfo[] lfInstalados =
      UIManager.getInstalledLookAndFeels();

    // Localiza y establece la nueva apariencia de la ventana en
    // función de la cadena utilizada en el menú de texto para
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
