//
//  JVolumenTree.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 28-Dic-2001  14:32:12
//     Revision: 07-Feb-2002  05:59:58
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase proporciona la vista del �rbol espec�fica de la aplicaci�n,
 * para ello extiende la clase Swing JTree y hace uso de las clases que
 * se han creado para la presentaci�n de la informaci�n del tama�o de
 * los nodos, ya sea �ste relativo al nodo ra�z que se indique o al nodo
 * padre m�s inmediato al que se seleccione
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

  // Cada nodo se muestra en relaci�n al tama�o total, como proporci�n
  // del tama�o total de su padre inmediato
  public static final ModoPresentacion TAM_RELATIVO_PADRE =
    new ModoPresentacion();
  // Cada nodo se muestra en relaci�n al tama�o total, como proporci�n
  // del tama�o total del nodo raiz
  public static final ModoPresentacion TAM_RELATIVO_RAIZ =
    new ModoPresentacion();
  // Cada nodo se muestra en relaci�n al n�mero de nodos total, como
  // proporci�n del n�mero de nodos descendientes de su padre inmediato
  public static final ModoPresentacion NUM_RELATIVO_PADRE =
    new ModoPresentacion();
  // Cada nodo se muestra en relaci�n al n�mero de nodos total, como
  // proporci�n del n�mero de nodos descendientes del nodo raiz
  public static final ModoPresentacion NUM_RELATIVO_RAIZ =
    new ModoPresentacion();

  // Renderer de las celdas del �rbol, que permitir� presentar la
  // informaci�n textual del tama�o cuando se pase el cursor sobre
  // cada uno de los elementos del �rbol
  private VolumenNodoRenderer renderCelda;

  // Este es el constructor al que se debe indicar el nodo raiz a partir
  // del cual se expandir� el �rbol, el objeto "helper" que se usar� en
  // la expansi�n del �rbol y presentaci�n de la informaci�n textual, y
  // las im�genes mediante las cuales se vidualizar� la magnitud que
  // corresponde a cada uno de los nodos
  public JVolumenTree( final Object raiz,final VolumenNodoHelper helper,
    final Image[] imagenes,final Image imgVacia,
    final Image imgCalculando ) {
    // Crea el nodo raiz, crea el modelo del �rbol con ese nodo y
    // contruye el objeto Swing JTree con ese modelo
    super( new DefaultTreeModel( new DefaultVolumenNodo(raiz,helper) ) );

    // Utilizamos el renderer con las im�genes para mostrar el tama�o
    // con respecto al padre
    renderCelda = new VolumenNodoRenderer( imagenes,
      imgVacia,imgCalculando );
    renderCelda.setModoPresentacion( TAM_RELATIVO_PADRE );
    setCellRenderer( renderCelda );
    // Nos aseguramos de que el �rbol permita Tooltips, que es donde
    // presentaremos la informaci�n textual del tama�o de los nodos
    ToolTipManager.sharedInstance().registerComponent( this );

    // Incorporamos un receptor de eventos para controlar la expansi�n
    // del �rbol
    addTreeExpansionListener( new TreeExpansionListener() {
      public void treeCollapsed( TreeExpansionEvent evt ) {}

      public void treeExpanded( TreeExpansionEvent evt ) {
        // Comprobamos que el nodo que est� siendo expandido ya ha
        // sido explorado. Para ello lo localizamos y nos aseguramos
        // de que se ha explorado
        final DefaultVolumenNodo nodo =
          (DefaultVolumenNodo)evt.getPath().getLastPathComponent();
        nodo.explorar( (DefaultTreeModel)getModel() );
        }
      } );
    // Iniciamos la tarea en segundo plano que calcula los tama�os
    inicioCalculo();
    }

  // Este m�todo permite especificar el nodo raiz a partir del cual
  // se visualiza el �rbol, y el "helper" que se usar� en la
  // expansi�n del �rbol y presentaci�n de la informaci�n textual
  public void setRoot( Object raiz,VolumenNodoHelper helper ) {
    // Creamos el nodo raiz y lo fijamos como el origen del actual
    // modelo del �rbol
    ((DefaultTreeModel)getModel()).setRoot(
      new DefaultVolumenNodo(raiz,helper) );
    // Iniciamos la tarea en segundo plano que calcula los tama�os
    inicioCalculo();
    }

  // Permite especificar un nuevo modo de presentaci�n
  public void setModoPresentacion( ModoPresentacion displayMode ) {
    renderCelda.setModoPresentacion( displayMode );
    repaint();
    }

  // Devuelve el modo de presentaci�n actual
  public ModoPresentacion getModoPresentacion() {
    return( renderCelda.getModoPresentacion() );
  }

  // Inicia la tarea demonio que se encarga de calcular el tama�o de
  // los nodos hijos
  private void inicioCalculo() {
    // Ejecutamos la acci�n como una tarea en segundo plano
    final Thread t = new Thread() {
      public void run() {
        // Localizamos el nodo raiz e invocamos al m�todo calcular()
        // sobre �l
        final DefaultTreeModel modelo =
          (DefaultTreeModel)getModel();
        final DefaultVolumenNodo raiz =
          (DefaultVolumenNodo)modelo.getRoot();
        raiz.calcular( modelo );
        // Nos aseguramos de que el �rbol actualiza la informaci�n
        repaint();
        }
      };
    // Fijamos la prioridad m�nima a la tarea
    t.setPriority( Thread.MIN_PRIORITY );
    // Y la convertimos en demonio
    t.setDaemon( true );
    t.start();
    }
  }

//-------------------------------------- Final del fichero JVolumenTree.java
