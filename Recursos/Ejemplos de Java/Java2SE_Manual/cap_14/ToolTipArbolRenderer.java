//
//  ToolTipArbolRenderer.java
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
//     Revision: 07-Feb-2002  05:47:15
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
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class ToolTipArbolRenderer implements TreeCellRenderer {
  DefaultTreeCellRenderer render =
    new DefaultTreeCellRenderer();
  Dictionary tablaTip;

  public ToolTipArbolRenderer( Dictionary tablaTip ) {
    this.tablaTip = tablaTip;
    }

  // Sobrescribimos el método para que presente el mensaje que
  // se le pasa al constructor de la clase en el diccionario que
  // correpsonde a la tabla de elementos
  public Component getTreeCellRendererComponent(
    JTree tree,Object value,boolean selected,boolean expanded,
    boolean leaf,int row,boolean hasFocus ) {

    render.getTreeCellRendererComponent( tree,value,selected,
      expanded,leaf,row,hasFocus );

    if( value != null ) {
      // Obtenemos la clave que corresponde al elemento de la
      // tabla sobre el que se encuentra el cursor
      Object claveTip;
      if( value instanceof DefaultMutableTreeNode ) {
        claveTip = ((DefaultMutableTreeNode)value).getUserObject();
        }
      else {
        claveTip = tree.convertValueToText( value,selected,
          expanded,leaf,row,hasFocus );
        }

      // Obtenemos el mensaje correspondiente a la tabla y lo
      // convertimos en el mensaje de tooltip
      Object mensaje = tablaTip.get( claveTip );
      if( mensaje != null )
        render.setToolTipText( mensaje.toString() );
      else
        render.setToolTipText( null );
      }
    return( render );
    }
  }

//------------------------------ Final del fichero ToolTipArbolRenderer.java
