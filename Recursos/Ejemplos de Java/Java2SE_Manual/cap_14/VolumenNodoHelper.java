//
//  VolumenNodoHelper.java
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
//     Creacion: 28-Dic-2001  12:59:43
//     Revision: 07-Feb-2002  06:02:09
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta interfaz define los servicios de informaci�n necesarios a la
 * hora de calcular el tama�o total de un nodo, y cuando este nodo se
 * expande en la jerarqu�a de nodos hijos. Implementando esta interfaz
 * para cada tipo de informaci�n que se quiera almacenar: sistema de
 * ficheros, bases de datos de e-mails, etc., el uso de estos servicios
 * puede seguir siendo gen�rico
 */

public interface VolumenNodoHelper {
  // Devuelve la representaci�n del nodo en forma de cadena
  public String toString( Object obj );

  // Devuelve el conjunto de objetos de los nodos hijos del nodo que
  // se pasa como par�metro
  public Object[] getHijos( Object obj );

  // Indica si el nodo representado por el objeto que se pasa como
  // par�metro es un contenedor
  public boolean esContenedor( Object obj );

  // Devuelve el tamano del nodo representado por el objeto que se
  // pasa como par�metro, excluyendo a cualquiera de los hijos
  public long getTamano( Object obj );
  }

//--------------------------------- Final del fichero VolumenNodoHelper.java
