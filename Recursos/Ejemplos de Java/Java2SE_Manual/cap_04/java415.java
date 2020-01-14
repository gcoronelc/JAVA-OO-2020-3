//
//  java415.java
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
//     Creacion: 16-Feb-1998  08:17:34
//     Revision: 02-Feb-2002  20:33:18
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es una demostración muy sencilla de la utilización de
 * una Pila o Stack
 */
import java.util.*;

public class java415 {
  static String diasSemana[] = {
    "Lunes", "Martes", "Miercoles", "Jueves",
    "Viernes", "Sabado", "Domingo"};

  public static void main( String args[] ) {
    Stack pila = new Stack();

    for ( int i=0; i < diasSemana.length; i++ )
      pila.push( diasSemana[i]+" " );
    System.out.println( "pila = "+pila );

    // Tratando la Pila como un Vector:
    pila.addElement( "Esta es la ultima linea" );
    // Se imprime el elemento 5 (sabiendo que la cuenta empieza en 0)
    System.out.println( "Elemento 5 -> "+pila.elementAt( 5 ) );
    System.out.println( "Elementos introducidos:" );
    while ( !pila.empty() )
      System.out.println( pila.pop() );
    }
  }

//------------------------------------------- Final del fichero java415.java