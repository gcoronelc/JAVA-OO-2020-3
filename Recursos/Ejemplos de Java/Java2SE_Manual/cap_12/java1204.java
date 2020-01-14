//
//  java1204.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 30-Jan-1998  16:04:22
//     Revision: 03-Feb-2002  12:41:26
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el control general de todos los eventos para todos los
 * sistemas de control
 */

// Esta es una forma de almacenar los eventos
class ConjuntoEventos {
  private java1203 eventos[] = new java1203[100];
  private int indice = 0;
  private int siguiente = 0;

  public void anade( java1203 evt ) {
    if ( indice >= eventos.length )
      return; // Si no fuese un ejemplo, habria que lanzar una excepción
    eventos[indice++] = evt;
    }

  public java1203 getSiguiente() {
    boolean embuclado = false;
    int inicio = siguiente;

    do {
      siguiente = (siguiente + 1) % eventos.length;
      if ( inicio == siguiente )
        embuclado = true;
      if ( (siguiente == (inicio+1) % eventos.length ) && embuclado )
        return( null );
    }while ( eventos[siguiente] == null );
    return( eventos[siguiente] );
    }

  public void borraActual() {
    eventos[siguiente] = null;
    }
  }


public class java1204 {
  private ConjuntoEventos ce = new ConjuntoEventos();

  public void anadeEvento( java1203 c ) {
    ce.anade( c );
    }

  public void ejecutar() {
    java1203 evt;

    while ( (evt = ce.getSiguiente()) != null ) {
      if ( evt.listo() ) {
        evt.accion();
        System.out.println( evt.descripcion() );
        ce.borraActual();
        }
      }
    }
  }

//------------------------------------------ Final del fichero java1204.java