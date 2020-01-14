//
//  DefaultVolumenNodo.java
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
//     Creacion: 28-Dic-2001  12:45:13
//     Revision: 07-Feb-2002  06:00:43
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase proporciona una implementaci�n por defecto de la
 * interfaz VolumenNodo que almacena el tama�o del nodo y tambi�n
 * soporta la expansi�n autom�tica de la jerarqu�a de nodos
 */
import java.text.*;
import java.util.*;
import javax.swing.tree.*;

public class DefaultVolumenNodo extends DefaultMutableTreeNode
  implements VolumenNodo {
  // La instancia del helper para este nodo
  private final VolumenNodoHelper helper;
  // Tama�o del nodo escluyendo los hijos
  private final long tamano;
  // Tama�o total del nodo m�s el de todos sus descendientes
  private long totalTamano;
  // Almacena el n�mero total de hijos que son descendientes de este
  // nodo. Debe mantenerse en un cach�, para proporcionar mejor
  // rendimiento a la implementaci�n del m�todo getLeafCount() de
  // la implementaci�n por defecto DefaultMutableTreeNode
  private int totalNodos;
  // Indica si se ha concluido el c�lculo de todos los hijos del nodo
  private boolean calculado;
  // Indica si el �rbol de descendientes del nodo se ha consctruido
  private boolean explorado = false;

  // Contruye un nodo a partir del objeto que se proporciona como
  // par�metro y la clase de ayuda que tambi�n debe indicarse
  public DefaultVolumenNodo( final Object obj,
    final VolumenNodoHelper _helper ) {
    // Guarda los par�metros de la invocaci�n
    super( obj );
    helper = _helper;
    // Obtiene el tama�o del objeto a trav�s de la clase auxiliar
    tamano = helper.getTamano( obj );
    // Inicializa el tama�o total del nodo al tama�o del nodo,
    // que posteriormente ser� aumentado en el tama�o de los
    // nodos hijos
    totalTamano = tamano;
    // Inicializa el n�mero de nodos a 0, o a 1 si estamos ante
    // una hoja, para ques e tenga en cuenta a la hora de calcular
    // el tama�o completo del nodo padre
    totalNodos = helper.esContenedor(obj) ? 0 : 1;
    // Si el nodo no permite tener hijos, se da por concluido el
    // c�lculo del tama�o de ese nodo
    calculado = !getAllowsChildren();
    }

  // Indica si el nodo es una hoja. Devuelve True si el nodo no es
  // un contenedor y false en caso contrario. Este m�todo sobreescribe
  // al de la clase DefaultMutableTreeNode, que devuelve true si el
  // nodo no tiene hijos, pero es incapaz de indicar si se trata de
  // un contenedor o no
  public boolean isLeaf() {
    return( !getAllowsChildren() );
    }

  // Indica si el nodo puede contener a otros nodos
  public boolean getAllowsChildren() {
    return( helper.esContenedor(getUserObject()) );
    }

  // Devuelve el tama�o del nodo
  public long getTamano() {
    return( tamano );
    }

  // Devuelve el tama�o total del noso y todos sus descendientes
  public long getTamanoTotal() {
    return( totalTamano );
    }

  // Devuelve el n�mero de nodos descendientes que contiene este nodo
  public int getLeafCount() {
    return( totalNodos );
    }

  // Devuelve la representaci�n de cadena de este nodo
  public String toString() {
    return( helper.toString(getUserObject()) );
    }

  // Indica si los hijos del nodo has sido ya creados
  public boolean estaExplorado() {
    return( explorado );
    }

  // Explora el nodo actual, constuyendo los nodos hijos y a�adi�ndolos
  // al modelo del �rbol. Este m�todo debe ser invocado antes de que el
  // nodo se expanda en la vista de �rbol y antes de que se relicen las
  // b�suqedas recursivas
  public synchronized void explorar( final DefaultTreeModel modelo ) {
    // Controlamos que no se haya explorado ya
    if( !explorado ) {
      // Recuperamos el conjundo de hijos, si los hay
      final Object[] hijos = helper.getHijos( getUserObject() );

      for( int hijo=0; hijo < hijos.length; hijo++ ) {
        // Para cada uno de ellos se contruye un nuevo nodo,
        // utilizando la misma clase de ayuda y a�adi�ndolo
        add( new DefaultVolumenNodo(hijos[hijo],helper) );
       }

      // Indicamos que esta rama ya est� explorada
      explorado = true;
      // La llamada se engloba en un try-catch para evitar la
      // excepci�n de Swing de finalizaci�n de la exploraci�n
      try {
        // Notificamos al modelo que la estructura ha cambiado
        modelo.nodeStructureChanged( this );
      } catch( NullPointerException e ) {
        e.printStackTrace();
        }
      }
    }

  // Calculamos el tama�o total del nodo y el n�mero de hijos
  // que dependen de �l
  public void calcular( final DefaultTreeModel modelo ) {
    // Nos aseguramos de que hemos construido el �rbol completo
    // del nodo
    explorar( modelo );

    // Para cada uno de los nodos hijos, hacemos lo siguiente...
    final Enumeration hijos = children();
    while( hijos.hasMoreElements() ) {
      final DefaultVolumenNodo node =
        (DefaultVolumenNodo)hijos.nextElement();

      // Si el hijo es un contenedor, seguimos recursivamente a trav�s
      // de sus hijos y almacenamos los resultados
      if( node.getAllowsChildren() ) {
        node.calcular( modelo );
        totalTamano += node.getTamanoTotal();
        totalNodos += node.getLeafCount();
        }
      // Si el hijo es una hoja, acumulamos los valores
      else {
        totalTamano += node.getTamano();
        totalNodos++;
        }
      }
    // Indicamos que se ha acabado el c�lculo del tama�o total
    calculado = true;

    // La llamada se engloba en un try-catch para evitar la excepci�n
    // de Swing de finalizaci�n de la exploraci�n
    try {
      // Indicamos al modelo que hemos cambiado el nodo
      modelo.nodeChanged( this );
    } catch( NullPointerException e ) {
      e.printStackTrace();
      }
    }

  // Devuelve true si ya se ha calculado el tama�o total del nodo
  public boolean estaCalculado() {
    return( calculado );
    }
  }

//-------------------------------- Final del fichero DefaultVolumenNodo.java
