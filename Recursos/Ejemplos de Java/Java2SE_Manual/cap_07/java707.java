//
//  java707.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 26-Ago-1996  21:20:11
//     Revision: 03-Feb-2002  06:17:14
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

class java707 {

  public static void main( String args[] ) {
    // Crea un StringBuffer inicializado a un texto por defecto
    StringBuffer str = new StringBuffer( "Hola" );

    // Le concatena otro texto, String
    str.append( " Mundo" );

    // Imprime en consola el resultado de la concatenación
    System.out.println( str );
    }
  }

//------------------------------------------- Final del fichero java707.java