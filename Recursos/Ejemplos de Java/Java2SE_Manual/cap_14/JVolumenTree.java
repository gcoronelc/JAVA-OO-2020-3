//
//  JVolumenTree.java
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
//     Creacion: 28-Dic-2001  14:32:12
//     Revision: 07-Feb-2002  05:59:58
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase proporciona la vista del árbol específica de la aplicación,
 * para ello extiende la clase Swing JTree y hace uso de las clases que
 * se han creado para la presentación de la información del tamaño de
 * los nodos, ya sea éste relativo al nodo raíz que se indique o al nodo
 * padre más inmediato al que se seleccione
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import com.sun.java.swing.plaf.motif.*;
import com.sun.java.swing.plaf.windows.*;
import javax.swing.plaf.metal.*;

public class JVolumenTree extends JTree {
  public static class ModoPresentacion {
    private ModoPresentacion() {}
    };

  // Cada nodo se muestra en relación al tamaño total, como proporción
  // del tamaño total de su padre inmediato
  public static final ModoPresentacion TAM_RELATIVO_PADRE =
    new ModoPresentacion();
  // Cada nodo se muestra en relación al tamaño total, como proporción
  // del tamaño total del nodo raiz
  public static final ModoPresentacion TAM_RELATIVO_RAIZ =
    new ModoPresentacion();
  // Cada nodo se muestra en relación al número de nodos total, como
  // proporción del número de nodos descendientes de su padre inmediato
  public static final ModoPresentacion NUM_RELATIVO_PADRE =
    new ModoPresentacion();
  // Cada nodo se muestra en relación al número de nodos total, como
  // proporción del número de nodos descendientes del nodo raiz
  public static final ModoPresentacion NUM_RELATIVO_RAIZ =
    new ModoPresentacion();

  // Renderer de las celdas del árbol, que permitirá presentar la
  // información textual del tamaño cuando se pase el cursor sobre
  // cada uno de los elementos del árbol
  private VolumenNodoRenderer renderCelda;

  // Este es el constructor al que se debe indicar el nodo raiz a partir
  // del cual se expandirá el árbol, el objeto "helper" que se usará en
  // la expansión del árbol y presentación de la información textual, y
  // las imágenes mediante las cuales se vidualizará la magnitud que
  // corresponde a cada uno de los nodos
  public JVolumenTree( final Object raiz,final VolumenNodoHelper helper,
    final Image[] imagenes,final Image imgVacia,
    final Image imgCalculando ) {
    // Crea el nodo raiz, crea el modelo del árbol con ese nodo y
    // contruye el objeto Swing JTree con ese modelo
    super( new DefaultTreeModel( new DefaultVolumenNodo(raiz,helper) ) );

    // Utilizamos el renderer con las imágenes para mostrar el tamaño
    // con respecto al padre
    renderCelda = new VolumenNodoRenderer( imagenes,
      imgVacia,imgCalculando );
    renderCelda.setModoPresentacion( TAM_RELATIVO_PADRE );
    setCellRenderer( renderCelda );
    // Nos aseguramos de que el árbol permita Tooltips, que es donde
    // presentaremos la información textual del tamaño de los nodos
    ToolTipManager.sharedInstance().registerComponent( this );

    // Incorporamos un receptor de eventos para controlar la expansión
    // del árbol
    addTreeExpansionListener( new TreeExpansionListener() {
      public void treeCollapsed( TreeExpansionEvent evt ) {}

      public void treeExpanded( TreeExpansionEvent evt ) {
        // Comprobamos que el nodo que está siendo expandido ya ha
        // sido explorado. Para ello lo localizamos y nos aseguramos
        // de que se ha explorado
        final DefaultVolumenNodo nodo =
          (DefaultVolumenNodo)evt.getPath().getLastPathComponent();
        nodo.explorar( (DefaultTreeModel)getModel() );
        }
      } );
    // Iniciamos la tarea en segundo plano que calcula los tamaños
    inicioCalculo();
    }

  // Este método permite especificar el nodo raiz a partir del cual
  // se visualiza el árbol, y el "helper" que se usará en la
  // expansión del árbol y presentación de la información textual
  public void setRoot( Object raiz,VolumenNodoHelper helper ) {
    // Creamos el nodo raiz y lo fijamos como el origen del actual
    // modelo del árbol
    ((DefaultTreeModel)getModel()).setRoot(
      new DefaultVolumenNodo(raiz,helper) );
    // Iniciamos la tarea en segundo plano que calcula los tamaños
    inicioCalculo();
    }

  // Permite especificar un nuevo modo de presentación
  public void setModoPresentacion( ModoPresentacion displayMode ) {
    renderCelda.setModoPresentacion( displayMode );
    repaint();
    }

  // Devuelve el modo de presentación actual
  public ModoPresentacion getModoPresentacion() {
    return( renderCelda.getModoPresentacion() );
  }

  // Inicia la tarea demonio que se encarga de calcular el tamaño de
  // los nodos hijos
  private void inicioCalculo() {
    // Ejecutamos la acción como una tarea en segundo plano
    final Thread t = new Thread() {
      public void run() {
        // Localizamos el nodo raiz e invocamos al método calcular()
        // sobre él
        final DefaultTreeModel modelo =
          (DefaultTreeModel)getModel();
        final DefaultVolumenNodo raiz =
          (DefaultVolumenNodo)modelo.getRoot();
        raiz.calcular( modelo );
        // Nos aseguramos de que el árbol actualiza la información
        repaint();
        }
      };
    // Fijamos la prioridad mínima a la tarea
    t.setPriority( Thread.MIN_PRIORITY );
    // Y la convertimos en demonio
    t.setDaemon( true );
    t.start();
    }
  }

//-------------------------------------- Final del fichero JVolumenTree.java
