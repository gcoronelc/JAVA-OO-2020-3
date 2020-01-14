//
//  VolumenNodo.java
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
//     Creacion: 28-Dic-2001  12:40:34
//     Revision: 07-Feb-2002  06:01:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta interfaz define los requerimientos para cada nodo del árbol
 * cuyo volumen se va a almacenar. Cada uno de los nodos tiene un
 * tamaño efectivo y un tamaño global que consiste en su tamaño más
 * el de todos los nodos hijos (si los tiene)
 */
import java.text.*;
import java.util.*;
import javax.swing.tree.*;

public interface VolumenNodo extends MutableTreeNode {
  // Devuelve el tamaño del nodo excluyendo a sus hijos
  public long getTamano();

  // Devuelve el tamaño total del nodo y sus descendientes
  public long getTamanoTotal();

  // Devuelve el número total del descendientes del nodo
  public int getLeafCount();

  // Devuelve el nodo raíz de la jerarquía en que se encuentra
  // englobado el nodo
  public TreeNode getRoot();

  // Devuelve true si el tamaño total ya ha sido calculado, o false
  // en caso contrario
  public boolean estaCalculado();
  }

//--------------------------------------- Final del fichero VolumenNodo.java
