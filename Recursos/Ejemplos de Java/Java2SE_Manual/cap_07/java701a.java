//
//  java701a.java
//  Copyright (c) 2000,2002 Agustin Froufe
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
//     Creacion: 12-Mar-2000  15:54:09
//     Revision: 03-Feb-2002  06:13:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.math.*;

class java701a {
  // Este método devuelve el valor BigDecimal que se le pasa con
  // el número de decimales que se le pasa en "precision".
  // Si es necesario coloca ceros a la derecha para ajustar esa
  // precisión
  static BigDecimal formateo( BigDecimal numero,int precision ) {
    return( numero.setScale( precision,BigDecimal.ROUND_HALF_UP ) );
    }

  public static void main( String args[] ) {
    BigDecimal diez = new BigDecimal( 10 );
    BigDecimal d = new BigDecimal( 9 );
    float f = 9;

    for( int i=0; i < 8; i++ ) {
      // Realizamos la división utilizando aritmética decimal
      d = d.divide( diez,i+1,BigDecimal.ROUND_HALF_UP );
      // Realizamos la división utilizando aritmética binaria
      f = f / 10;

      // Formateamos una cadena para presentar los resultados de
      // las dos divisiones
      String s = d.toString();
      s = s.concat(
        new String( new char[15-s.length()]) ).replace('\000',' ' );
      System.out.println( s+" "+String.valueOf(f) );
     }

   // Ejemplo de uso del formateo de números BigDecimal
   System.out.println( formateo( new BigDecimal( "123.456789" ),3 ) );
   System.out.println( formateo( new BigDecimal( "123" ),3 ) );
   }
  }

//----------------------------------------- Final del fichero java701a.java