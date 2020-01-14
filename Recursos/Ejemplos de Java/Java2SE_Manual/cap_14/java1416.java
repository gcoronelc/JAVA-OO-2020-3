//
//  java1416.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 04-Ago-1998  14:53:05
//     Revision: 07-Feb-2002  06:07:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Demostración del uso y presencia de una JTable Swing.
 * Se puede observar que resulta muy sencillo el manejo y
 * la implementación de hojas de cálculo o de tablas en el
 * sentido literal de la palabra
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
class ModeloDatos extends AbstractTableModel {
  Object datos[][] = {
    {"uno","dos","tres","cuatro"},
    {"cinco","seis","siete","ocho"},
    {"nueve","diez","once","doce"},
    };

  // Esta clase imprime los datos en la consola cada vez
  // que se produce un cambio en cualquiera de las
  // casillas de la tabla
  class TablaListener implements TableModelListener {
    public void tableChanged( TableModelEvent evt ) {
      for ( int i=0; i < datos.length; i++ ) {
        for ( int j=0; j < datos[0].length; j++ )
          System.out.print( datos[i][j] + " " );
        System.out.println();
        }
      }
    }

  // Constructor
  ModeloDatos() {
    addTableModelListener( new TablaListener() );
    }

  // Devuelve el número de columnas de la tabla
  public int getColumnCount() {
    return( datos[0].length );
    }

  // Devuelve el número de filas de la tabla
  public int getRowCount() {
    return( datos.length );
    }

  // Devuelve el valor de una determinada casilla de la tabla
  // identificada mediante fila y columna
  public Object getValueAt( int fila,int col ) {
    return( datos[fila][col] );
    }

  // Cambia el valor que contiene una determinada casilla de
  // la tabla
  public void setValueAt( Object valor,int fila,int col ) {
    datos[fila][col] = valor;
    // Indica que se ha cambiado
    fireTableDataChanged();
    }

  // Indica si la casilla identificada por fila y columna es
  // editable
  public boolean isCellEditable( int fila,int col ) {
    return( true );
    }
  }


public class java1416 extends JPanel {
  public java1416() {
    setLayout( new BorderLayout() );
    JTable tabla = new JTable( new ModeloDatos() );
    // La tabla se añade a un ScrollPane para que sea éste el
    // que controle automáticamente en tamaño de la tabla,
    // presentando una barra de desplazamiento cuando sea
    // necesario
    JScrollPane panel = new JScrollPane( tabla );
    add( panel,BorderLayout.CENTER );
    }

  public static void main(String args[]) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );

    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );
    frame.getContentPane().add( new java1416(),BorderLayout.CENTER );
    frame.setSize( 200,200 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1416.java