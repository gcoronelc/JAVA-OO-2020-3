//
//  java701.java
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
//     Creacion: 12-Mar-2000  13:15:08
//     Revision: 03-Feb-2002  06:11:11
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo implementa la descomposición en factores primos de un
 * número entero cualquiera.
 */
import java.math.*;
import java.util.*;

class java701 {
  static final BigInteger cero = new BigInteger( "0" );
  static final BigInteger uno = new BigInteger( "1" );
  static final BigInteger dos = new BigInteger( "2" );
  static final BigInteger tres = new BigInteger( "3" );

  public static void main(String argv[]) {
    for( int i=0; i < argv.length; i++ ) {
      try {
        // Convertimos la cadena que se pasa en la llamada a número
        BigInteger numero = new BigInteger( argv[i] );
        if( numero.compareTo(cero) <= 0 )
          continue;
        // Presentamos el número a descomoner
        System.out.print( numero+" = " );

        // Los factores primos que se van obteniendo, se guardan en un
        // vector, presentando luego en pantalla sus elementos
        Vector factores = descomponer( numero );
        for( int j=0; j < factores.size(); j++ ) {
          if( j != 0 )
            System.out.print( " * " );
          System.out.print( (BigInteger)factores.elementAt(j) );
          }
        System.out.println();
        // Realizamos la comprobación de la descomposición
        System.out.println( compruebaFactores( numero,factores ) );
      } catch( NumberFormatException e ) {
        e.printStackTrace();
        }
      }
    }

  // Este es el método que descompone el número que se pasa en factores
  // primos. El número de entrada debe ser positivo
  static Vector descomponer( BigInteger numero ) {
    Vector factores = new Vector();

    while( numero.mod(dos).compareTo(cero) == 0 ) {
      // Obtenemos un nuevo factor
      numero = numero.divide( dos );
      factores.addElement( dos );
      }

    BigInteger limite = bigRaiz(numero).add(uno);
factor:
    for( BigInteger i=tres; i.compareTo(limite) <= 0; i=i.add(dos) ) {
      while( numero.mod(i).compareTo(cero) == 0 ) {
        // Obtenemos un nuevo factor
        numero = numero.divide( i );
        factores.addElement( i );
        if( numero.compareTo(uno) == 0 )
          break factor;
        limite = bigRaiz( numero ).add(uno);
        }
      }

    if (numero.compareTo(uno) != 0 || factores.size() == 0)
      factores.addElement(numero);

    return( factores );
    }

  // Método para realizar la raíz cuadrada con precisión arbitraria
  // de un número de tipo BigInteger
  static BigInteger bigRaiz( BigInteger numero ) {
    BigInteger antRaiz;
    BigInteger raiz;
    BigInteger cero = new BigInteger( "0" );
    BigInteger dos = new BigInteger( "2" );

    BigInteger num = numero ;
    raiz = num.shiftRight( num.bitLength()/2 );
    do {
      antRaiz = raiz;
      raiz =
        antRaiz.multiply( antRaiz ).add(num).divide(antRaiz).divide(dos);
    } while( raiz.subtract( antRaiz ).abs().compareTo(dos) > 0 );

    return( raiz );
    }

  // Este método comprueba los factores que se han obtenido
  static String compruebaFactores( BigInteger numero,Vector factores ) {
    String chequeo = "" ;
    BigInteger check = uno;  // Inicializamos el producto de los factores

    for( int i=0; i < factores.size() ; i++ ) {
      check = check.multiply( (BigInteger)factores.elementAt(i) );
      if( check.compareTo(numero) == 0 )
        chequeo = "Verificacion de Factores Correcta" ;
      else
        chequeo = "Problemas en la verificacion de factores";
      }

    return( chequeo );
    }
  }

//------------------------------------------ Final del fichero java701.java