//
//  java1431.java
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
//     Creacion: 30-Dic-2001  12:32:55
//     Revision: 07-Feb-2002  05:47:38
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra un árbol en el cual se personalizan los
 * mensajes de información.
 * Simplemente colocando el cursor sobre cada uno de los elementos se
 * presentará en pantalla un mensaje emergente con  el contenido de la
 * propiedad que señale el cursor.
 */
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class java1431 {
  public static void main( String args[] ) {
    JFrame miFrame = new JFrame( "Tutorial de Java, Swing" );
    miFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    // Obtenemos las propiedades del sistema
    Properties props = System.getProperties();
    // Creamos el árbol que nos permite visualizarlas
    JTree arbol = new JTree( props );
    ToolTipManager.sharedInstance().registerComponent( arbol );
    // Creamos el visualizador de los elementos del árbol en base
    // al que hemos implementado y personalizado
    TreeCellRenderer render = new ToolTipArbolRenderer( props );
    // hacemos que cada celda utilice ese visualizador
    arbol.setCellRenderer( render );
    // Presentamos el árbol en un panel con scroll automático
    JScrollPane panel = new JScrollPane( arbol );
    miFrame.getContentPane().add( panel );
    miFrame.setSize( 300,150 );
    miFrame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1431.java
