//
//  java1408.java
//  Copyright (c) 1996-98,2002 Agustin Froufe
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
//     Creacion: 21-Sep-1998  17:27:32
//     Revision: 07-Feb-2002  05:40:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo crea una lista con un controlador propio, de forma que los
 * elementos seleccionables que se presentan, aparecen con el aspecto que
 * le proporciona el controlador que se implementa en la clase anidada
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class java1408 extends JPanel {
  private JList lista;

  public java1408() {
    // Se crea un panel para poder contener todos los Componentes
    setLayout( new BorderLayout() );

    // Se crean algunos elementos para poder seleccionar de la lista
    String datosLista[] = {
      "Capitulo 4\nIntroduccion al Lenguaje Java",
      "Capitulo 5\nConceptos Basicos de Java",
      "Capitulo 6\nProgramas Basicos en Java",
      "Capitulo 7\nEl Depurador de Java",
      "Capitulo 8\nClases Java",
      "Capitulo 9\nExcepciones en Java"
      };
    // Creamos una lista con un controlador propio, que va a permitir
    // la personalización en la presentación en pantalla de cada uno
    // de los items que se pueden seleccionar de esa lista
    lista = new JList( datosLista );
    lista.setCellRenderer( new MiRendererDeLista() );
    add( lista, BorderLayout.CENTER );
    }


  // Clase anidada que implementa el visualizador especial para las celdas
  // que componen la lista
  class MiRendererDeLista extends JTextArea implements ListCellRenderer {

    public Component getListCellRendererComponent(
      JList lista,Object valor,int indice,
      boolean seleccionado, boolean conFoco ) {

      setBorder( new BevelBorder( BevelBorder.RAISED ) );
      // Presenta el text correspondiente al item
      setText( valor.toString() );
      // Pinta en los colores indicados y con la fuente seleccionada...
      if ( seleccionado ) {
        // .. en el caso de un item marcado (rojo/blanco)
        setBackground( Color.red );
        setForeground( Color.white );
        }
      else {
        // .. en el caso de un item no marcado ( gris/negro)
        setBackground( Color.lightGray );
        setForeground( Color.black );
        }
      return( this );
      }
    }


  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( new java1408(),BorderLayout.CENTER );
    ventana.setSize( 300,180 );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1408.java