//
//  java506.java
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
//     Creacion: 29-Sep-1997  17:19:12
//     Revision: 02-Feb-2002  21:07:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra el retorno de valores tanto por referencia
 * como por valor
 */

// Un objeto de esta clase sera devuelto por referencia
class miClase {
  int varInstancia = 10;
  }


class java506 {

  // Metodo que devuelve por Valor
  int retornoPorValor() {
    // Devuelve un tipo primitivo por valor
    return( 5 );
    }

  // Metodo que devuelve por Referencia
  miClase retornoPorReferencia() {
    // Devuelve un objeto por referencia
    return( new miClase() );
    }

  public static void main( String args[] ) {
    // Instancia un objeto
    java506 obj = new java506();
    System.out.println( "El Valor devuelto es "+obj.retornoPorValor() );

    System.out.println(
      "El Valor de la variable de instancia en el objeto devuelto es "+
      obj.retornoPorReferencia().varInstancia ); // Atencion a los dos puntos
    }
  }

//------------------------------------------- Final del fichero java506.java