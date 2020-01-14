//
//  java1417.java
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
//     Creacion: 21-Sep-1998  18:11:54
//     Revision: 07-Feb-2002  06:07:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Otro ejemplo de tabla en la que se ha modificado el control de las
 * columnas y filas para que cuando se selecciona una celda, se marquen
 * la columna y la fila en que se encuentra dicha celda
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

class java1417 extends JPanel {
  private JTable tabla;
  private JScrollPane panelScroll;
  private String titColumna[];
  private String datoColumna[][];

  public java1417() {
    setLayout( new BorderLayout() );
    // Creamos las columnas y las cargamos con los datos que van a
    // aparecer en la pantalla
    CreaColumnas();
    CargaDatos();
    // Creamos una instancia del componente Swing
    tabla = new JTable( datoColumna,titColumna );
    // Aquí se configuran algunos de los parámetros que permite
    // variar la JTable
    tabla.setShowHorizontalLines( false );
    tabla.setRowSelectionAllowed( true );
    tabla.setColumnSelectionAllowed( true );
    // Cambiamos el color de la zona seleccionada (rojo/blanco)
    tabla.setSelectionForeground( Color.white );
    tabla.setSelectionBackground( Color.red );
    // Incorporamos la tabla a un panel que incorpora ya una barra
    // de desplazamiento, para que la visibilidad de la tabla sea
    // automática
    panelScroll = new JScrollPane( tabla );
    add( panelScroll, BorderLayout.CENTER );
    }


  // Creamos las etiquetas que sirven de título a cada una de
  // las columnas de la tabla
  public void CreaColumnas() {
    titColumna = new String[8];

    for ( int i=0; i < 8; i++ ) {
      titColumna[i] = "Col: "+i;
      }
    }

  // Creamos los datos para cada uno de los elementos de la tabla
  public void CargaDatos() {
    datoColumna = new String[100][8];

    for ( int iY=0; iY < 100; iY++ ) {
      for ( int iX=0; iX < 8; iX++ ) {
        datoColumna[iY][iX] = "" + iX + "," + iY;
        }
      }
    }


  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( new java1417(),BorderLayout.CENTER );
    ventana.setSize( 300,180 );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1417.java