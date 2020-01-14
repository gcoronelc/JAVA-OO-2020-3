//
//  java411.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 16-Feb-1998  05:34:21
//     Revision: 02-Feb-2002  20:18:16
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.util.*;

class Coche {
  private int numCoche;

  Coche( int i ) {
    numCoche = i;
    }
  void print() {
    System.out.println( "Coche #"+numCoche );
    }
  }

class Barco {
  private int numBarco;

  Barco( int i ) {
    numBarco = i;
    }
  void print() {
    System.out.println( "Barco #"+numBarco );
    }
  }

public class java411 {
  public static void main( String args[] ) {
    Vector coches = new Vector();

    for ( int i=0; i < 7; i++ )
      coches.addElement( new Coche( i ) );
    // No hay ningun problema en añadir un barco a los coches
    coches.addElement( new Barco( 7 ) );
    for ( int i=0; i < coches.size(); i++ )
      (( Coche )coches.elementAt( i ) ).print();
    // El barco solamente es detectado en tiempo de ejecucion
    }
  }

//------------------------------------------- Final del fichero java411.java