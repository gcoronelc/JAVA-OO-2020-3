//
//  java435.java
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
//     Creacion: 15-Dic-2001  09:54:02
//     Revision: 02-Feb-2002  20:27:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo para mostrar la implementaci�n de la interfaz
 * Iterator. Se deben implementar los m�todos que define la interfaz y que
 * en este caso se trata de buscar los elementos en una colecci�n que
 * implementen la inerfaz Number, como cuando se trata de objetos de
 * tipo Byte o Double.
 * La clase del ejemplo utiliza luego la clase FiltroNumero que implementa
 * el iterador para presentar los elementos del tipo anterior que se
 * incluyen en la lista de prueba de la aplicaci�n
 */
import java.lang.*;
import java.util.*;

// Clase que implementa la interfaz Iterador y que se encarga de devolver
// los objetos de tipo Number de la lista por la que se desplaza el
// iterador
class FiltroNumero implements Iterator {
  // Iterador para desplazarnos por la lista
  private final Iterator iterador;
  // Objeto de tipo Number actual
  private Object numero;

  // Constructor de la clase
  public FiltroNumero( Iterator _iterador ) {
    this.iterador = _iterador;
    }

  // Implementamos los tres m�todos que se declaran en la interfaz
  // Iterator para esta clase
  public boolean hasNext() {
    // Si ya tenemos un objeto Number, devolvemos true
    if( numero != null )
      return( true );

    // Recorremos la colecci�n en busca de objetos de tipo Number
    while( iterador.hasNext() ) {
      numero = iterador.next();
      if( numero instanceof Number )
        return( true );
      }

    // Si no encontramos ninguno, devolvemos false
    numero = null;
    return( false );
    }

  public Object next() {
    // Tanto si tenemos ya un Number o debemos buscar uno a trav�s
    // de hasNext()
    if( numero == null  &&  !hasNext() )  {
      throw new NoSuchElementException();
      }

    // Devuelve el objeto Number
    Object objNumero = numero;
    numero = null;
    return( objNumero );
    }

  public void remove() {
    // No permitimos que se puedan eliminar elementos de la colecci�n
    throw new UnsupportedOperationException();
    }
  }

// Clase de ejemplo para utilizar el iterador definido en la clase
// FiltroNumero, que permite imprimir los elementos de tipo Number de
// la lista que se define en la clase
public class java435 {
  public static void main( String args[] ) {
    // Creamos la lista de ejemplo
    List lista = new ArrayList();
    // La rellenamos con algunos elementos
    lista.add( null );
    lista.add( "linea 1" );
    lista.add( new Double(98.76) );
    lista.add( new Byte((byte)64) );
    lista.add( null );
    lista.add( "linea 2" );
    lista.add( new Long(3245) );
    lista.add( new Integer(69) );
    lista.add( "linea 3" );
    lista.add( new Double(54.32) );
    lista.add( null );
    lista.add( "ultima linea" );

    // Filtramos la lista utilizando el iterador y presentamos en
    // pantalla los elementos de tipo Number
    Iterator iterador = new FiltroNumero( lista.iterator() );
    while( iterador.hasNext() )
      System.out.println( iterador.next() );
    }
  }

//------------------------------------------- Final del fichero java435.java