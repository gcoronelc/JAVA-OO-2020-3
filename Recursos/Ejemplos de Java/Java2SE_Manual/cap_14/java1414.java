//
//  java1414.java
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
//     Creacion: 04-Ago-1998  14:04:08
//     Revision: 07-Feb-2002  05:54:03
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo muy sencillo de manejo de �rboles con Swing.
 * Si se pulsa el bot�n de prueba, se a�aden ramas al �rbol, que ya se
 * pueden expandir o contraer pulsando en el r�tulo correspondiente a
 * cada una de las ramas.
 * Los �rboles se pueden complicar much�simo m�s de lo que se muestra
 * en el ejemplo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

// Esta clase coge un array de Strings, haciendo que el primer elemento
// del array sea un nodo y el resto sean ramas de ese nodo
// Con ello se consiguen las ramas del �rbol general cuando se pulsa
// el bot�n de test
class Rama {
  DefaultMutableTreeNode r;
  public Rama( String datos[] ) {
    r = new DefaultMutableTreeNode( datos[0] );
    for ( int i=1; i < datos.length; i++ )
      r.add( new DefaultMutableTreeNode( datos[i] ) );
    }

  public DefaultMutableTreeNode node() {
    return( r );
    }
  }


public class java1414 extends JPanel {
  String datos[][] = {
    { "Colores","Rojo","Verde","Azul"},
    { "Sabores","Salado","Dulce","Amargo"},
    { "Longitud","Corta","Media","Larga"},
    { "Intensidad","Alta","Media","Baja"},
    { "Temperatura","Alta","Media","Baja"},
    { "Volumen","Alto","Medio","Bajo"},
    };
  static int i=0;
  DefaultMutableTreeNode raiz,rama,seleccion;
  JTree arbol;
  DefaultTreeModel modelo;

  public java1414() {
    setLayout( new BorderLayout() );
    raiz = new DefaultMutableTreeNode( "raiz" );
    arbol = new JTree( raiz );
    // Se a�ade el �rbol y se hace sobre un ScrollPane, para
    // que se controle autom�ticamente la longitud del �rbol
    // cuando est� desplegado, de forma que aparecer� una
    // barra de desplazamiento para poder visualizarlo en su
    // totalidad
    add( new JScrollPane( arbol ),BorderLayout.CENTER );
    // Se obtiene el modelo del �rbol
    modelo =(DefaultTreeModel)arbol.getModel();
    // Y se a�ade el bot�n que va a ir incorporando ramas
    // cada vez que se pulse
    JButton botonPrueba = new JButton( "Pulsame" );
    botonPrueba.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        if( i < datos.length ) {
          rama = new Rama( datos[i++] ).node();
          // Control de la �tlima selecci�n realizada
          seleccion = (DefaultMutableTreeNode)
          arbol.getLastSelectedPathComponent();
          if( seleccion == null )
            seleccion = raiz;
          // El modelo crear� el evento adecuado, y en respuesta
          // a �l, el �rbol se actualizar� autom�ticamente
          modelo.insertNodeInto( rama,seleccion,0 );
          }
        }
      } );

    // Cambio del color del bot�n
    botonPrueba.setBackground( Color.blue );
    botonPrueba.setForeground( Color.white );
    // Se crea un panel para contener al bot�n
    JPanel panel = new JPanel();
    panel.add( botonPrueba );
    add( panel,BorderLayout.SOUTH );
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1414(),BorderLayout.CENTER );
    frame.setSize( 200,500 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1414.java