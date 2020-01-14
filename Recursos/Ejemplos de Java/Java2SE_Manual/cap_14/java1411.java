//
//  java1411.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 29-Jul-1998  13:44:40
//     Revision: 07-Feb-2002  05:49:47
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejempolo presenta un completo sistema de menús, partiendo de los
 * items que están situados en la barra superior y deplegándose las
 * opciones que se pueden seleccionar. También se muestra cómo se
 * colocan iconos sobre etiquetas y la inserción de items a la
 * barra de menú
 * Se utilizan botones del tipo creados en el ejemplo java1410, los
 * cuales indican con imágenes su estado
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1411 extends JPanel {
  static final Boolean bT = new Boolean( true );
  static final Boolean bF = new Boolean( false );
  static ButtonGroup grupoBotones;

  // Clase que se utiliza para crear los distintos tipos de menús que se
  // va a presentar en la ventana
  static class TipoMenu {
    TipoMenu( int i ) {}
    };

  static final TipoMenu mi = new TipoMenu( 1 ); // Menú con elementos normales
  static final TipoMenu cb = new TipoMenu( 2 ); // Menú con cajas de selección
  static final TipoMenu rb = new TipoMenu( 3 ); // Menú con botones de radio
  JTextField txt = new JTextField( 10 );
  JLabel lbl = new JLabel( "Icono Seleccionado",java1410.imgs[0],
    JLabel.CENTER );
  ActionListener al1 = new ActionListener() {
    public void actionPerformed( ActionEvent evt ) {
      txt.setText( ((JMenuItem)evt.getSource() ).getText() );
      }
    };
  ActionListener al2 = new ActionListener() {
    public void actionPerformed( ActionEvent evt ) {
      JMenuItem mi = (JMenuItem)evt.getSource();
      lbl.setText( mi.getText() );
      lbl.setIcon( mi.getIcon() );
      }
    };
  // En estas estrcuturas se almacenas los datos de los menús como si se
  // tratara de los típicos recursos de X
  public Object menuArchivo[][] = {
    // Nombre del menú y tecla rápida asociada
    { "Archivo",new Character('A')},
    // Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
    // para cada uno de los elementos del menú
    { "Nuevo",mi,new Character('N'),al1,bT},
    { "Abrir",mi,new Character('b'),al1,bT},
    { "Guardar",mi,new Character('G'),al1,bF},
    { "Guardar como...",mi,new Character('c'),al1,bF},
    { null}, // Separador
    { "Salir",mi,new Character('S'),al1,bT},
    };
  public Object menuEdicion[][] = {
    // Nombre del menú y tecla rápida asociada
    { "Edicion",new Character('E')},
    // Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
    { "Cortar",mi,new Character('t'),al1,bT},
    { "Copiar",mi,new Character('C'),al1,bT},
    { "Pegar",mi,new Character('P'),al1,bT},
    { null}, // Separator
    { "Seleccionar Todo",mi,new Character('S'),al1,bT},
    };
  public Object menuIconos[][] = {
    // Nombre del menú y tecla rápida asociada
    { "Iconos",new Character('I')},
    // Se le añade un último elemento opcional que corresponde al
    // icono que se presenta en medio de la ventana
    { "Icono 0",rb,new Character('0'),al2,bT,java1410.imgs[0]},
    { "Icono 1",rb,new Character('1'),al2,bT,java1410.imgs[1]},
    { "Icono 2",rb,new Character('2'),al2,bT,java1410.imgs[2]},
    { "Icono 3",rb,new Character('3'),al2,bT,java1410.imgs[3]},
    { "Icono 4",rb,new Character('4'),al2,bT,java1410.imgs[4]},
    };
  public Object menuOpciones[][] = {
    // Nombre del menú y tecla rápida asociada
    { "Opciones",new Character('O')},
    // Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
    { "Opcion 1",cb,new Character('1'),al1,bT},
    { "Opcion 2",cb,new Character('2'),al1,bT},
    };
  public Object menuAyuda[][] = {
    // Nombre del menú y tecla rápida asociada
    { "Ayuda",new Character('y')},
    // Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
    { "Indice",mi,new Character('I'),al1,bT},
    { "Contenido",mi,new Character('C'),al1,bT},
    { null}, // Separator
    { "Acerca de...",mi,new Character('A'),al1,bT},
    };
  public Object barraMenu[] = {
    menuArchivo,menuEdicion,menuIconos,menuOpciones,menuAyuda,
    };


  static public JMenuBar creaMenuBarra( Object barraMenuDato[] ) {
    JMenuBar barraMenu = new JMenuBar();

    for ( int i=0; i < barraMenuDato.length; i++ )
      barraMenu.add( creaMenu((Object[][])barraMenuDato[i]) );
    return( barraMenu );
    }


  static public JMenu creaMenu( Object[][] menuDato ) {
    JMenu menu = new JMenu();

    menu.setText( (String)menuDato[0][0] );
    menu.setMnemonic( ((Character)menuDato[0][1]).charValue() );
    grupoBotones = new ButtonGroup();
    for ( int i=1; i < menuDato.length; i++ ) {
      if ( menuDato[i][0] == null )
        menu.add( new JSeparator() );
      else
        menu.add( creaMenuItem( menuDato[i] ) );
      }
    return( menu );
    }


  static public JMenuItem creaMenuItem( Object[] dato ) {
    JMenuItem m = null;
    TipoMenu tipo = (TipoMenu)dato[1];

    if ( tipo == mi )
      m = new JMenuItem();
    else if ( tipo == cb )
      m = new JCheckBoxMenuItem();
    else if ( tipo == rb ) {
      m = new JRadioButtonMenuItem();
      grupoBotones.add( m );
      }
    m.setText( (String)dato[0] );
    m.setMnemonic( ((Character)dato[2]).charValue() );
    m.addActionListener( (ActionListener)dato[3] );
    m.setEnabled( ((Boolean)dato[4]).booleanValue() );
    // Y ahora el caso opcional de los iconos
    if ( dato.length == 6 )
      m.setIcon( (Icon)dato[5] );
    return( m );
    }


  java1411() {
    setLayout( new BorderLayout() );
    add( creaMenuBarra( barraMenu ),BorderLayout.NORTH );
    JPanel p = new JPanel();
    p.setLayout( new BorderLayout() );
    p.add( txt,BorderLayout.NORTH );
    p.add( lbl,BorderLayout.CENTER );
    add( p,BorderLayout.CENTER );
    }


  public static void main(String args[]) {
    java1411 panel = new java1411();
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( panel,BorderLayout.CENTER );
    ventana.setSize( 320,200 );
    ventana.setTitle( "Tutorial de Java, Swing" );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1411.java