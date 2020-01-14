//
//  java704.java
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
//     Creacion: 30-Sep-1997  10:48:28
//     Revision: 03-Feb-2002  06:16:19
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicacion demuestra el hecho de que una cadena no puede ser
 * modificada, y sin embargo una variable de referencia puede
 * modificarse para que apunte a una nueva cadena para que
 * parezca que se la modificado la cadena original
 */
class java704 {
  String cadena1 = "ESTA CADENA SE LLAMA cadena1";
  String cadena2 = "Esta cadena se llama cadena2";

  public static void main( String args[] ) {
    java704 obj = new java704();

    System.out.println( "Los valores originales de las cadenas son:" );
    System.out.println( obj.cadena1 );
    System.out.println( obj.cadena2 );
    System.out.println( "Reemplaza cadena1 con otra cadena" );
    obj.cadena1 = obj.cadena1 + " " + obj.cadena2;
    System.out.println( "Presenta el nuevo valor de cadena1:" );
    System.out.println( obj.cadena1 );
    System.out.println( "Finaliza el programa" );
    }
  }

//------------------------------------------- Final del fichero java704.java