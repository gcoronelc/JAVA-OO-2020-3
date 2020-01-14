//
//  java1430.java
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
//     Creacion: 30-Dic-2001  12:12:13
//     Revision: 07-Feb-2002  05:45:27
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra una lista en la cual se personalizan los
 * mensajes de información.
 * Simplemente colocando el cursor sobre cada uno de los elementos se
 * presentará en pantalla un mensaje emergente con  el contenido de la
 * propiedad que señale el cursor.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class java1430 extends JList {
  // Modelo de control de la lista
  DefaultListModel modelo;
  // Lista de propiedades del sistema
  Properties propiedadesTip;

  public java1430( Properties propiedades ) {
    modelo = new DefaultListModel();
    setModel( modelo );
    ToolTipManager.sharedInstance().registerComponent( this );
    propiedadesTip = propiedades;
    // Añadimos las propiedades a la lista
    Enumeration enum = propiedades.propertyNames();
    while( enum.hasMoreElements() )
      modelo.addElement( enum.nextElement() );
    }

  // Devuelve el texto que se presentará en el mensaje tooltip
  public String getToolTipText( MouseEvent evt ) {
    // Posición de la pantalla en donde se encuentra en ratón
    Point p = evt.getPoint();
    int posicion = locationToIndex( p );
    // Obtenemos la propiedad sobre la que está situado el cursor
    String clave = (String)modelo.getElementAt( posicion );
    // Obtenemos el valor de esa propiedad, que devolvemos como
    // texto a presentar en el mensaje tooltip
    String mensajeTip = propiedadesTip.getProperty( clave );

    return( mensajeTip );
    }

  public static void main( String args[] ) {
    JFrame miFrame = new JFrame( "Tutorial de Java, Swing" );
    miFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    // Obtenemos las propiedades del sistema
    Properties props = System.getProperties();
    // Creamos la lista personalizada que visualiza las propiedades
    // en base al modelo propio que hemos creado
    java1430 lista = new java1430( props );
    // Panel sobre el que se presenta la lista, con barras de scroll
    // automáticas
    JScrollPane panel = new JScrollPane( lista );
    miFrame.getContentPane().add( panel );
    miFrame.setSize( 300,300 );
    miFrame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1430.java
