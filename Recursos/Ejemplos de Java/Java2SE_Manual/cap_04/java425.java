//
//  java425.java
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
//     Creacion: 19-Feb-1998  08:47:51
//     Revision: 02-Feb-2002  20:42:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra algunas de las operaciones que se pueden realizar
 * sobre una estructura de datos en forma de Mapa
 */
import java.util.*;

public class java425 {
  // EL Mapa contiene parejas de valores que están formadas por una
  // clave y el valor en sí que se asocia a dicha clave
  // Por ejemplo, se puede construir datos de prueba en los que figura
  // una abreviatura como 'clave' y su descripción como 'valor'
  public final static String datosPrueba1[][] = {
    { "Km", "Kilometro"},
    { "m", "Metro"},
    { "Ha", "Hectarea"},
    { "MN", "Milla Nautica"},
    { "Kg", "Kilogramo"},
    { "dB", "Decibelio"},
    { "Mb", "Megabyte"},
    };
  public final static String datosPrueba2[][] = {
    { "Eu", "Euro"},
    { "Lb", "Libra Esterlina"},
    { "DA", "Dolar Americano"}
    };

  public static Map fill( Map m,Object obj[][] ) {
    for ( int i=0; i < obj.length; i++ )
      m.put( obj[i][0],obj[i][1] );
    return( m );
    }

  // Generando un Set de claves
  public static void printClaves( Map m ) {
    System.out.print( "Tamano = "+m.size()+", " );
    System.out.print( "Claves: " );
    java421.print( m.keySet() );
    }

  // Generando una Collection de valores
  public static void printValores( Map m ) {
    System.out.print( "Valores: " );
    java421.print( m.values() );
    }

  // Moviéndose a través de los objetos Map.Entry
  public static void print( Map m ) {
    Iterator it = m.entrySet().iterator();

    while ( it.hasNext() ) {
      Map.Entry e = (Map.Entry)it.next();
      System.out.println( "Clave = "+e.getKey()+
        ", Valor = "+e.getValue() );
      }
    }

  public static void test( Map m ) {
    fill( m,datosPrueba1 );
    // Map para las 'claves' funciona como un Set
    fill( m,datosPrueba1 );
    printClaves( m );
    printValores( m );
    print( m );

    String clave = datosPrueba1[4][0];
    String valor = datosPrueba1[4][1];
    System.out.println( "m.containsKey(\""+clave+
      "\"): "+m.containsKey(clave) );
    System.out.println("m.get(\""+clave+ "\"): "
      +m.get(clave) );
    System.out.println("m.containsValue(\""
      +valor+"\" ): "+m.containsValue(valor) );

    Map m2 = fill( new HashMap(),datosPrueba2 );
    m.putAll( m2 );
    printClaves( m );
    m.remove( datosPrueba2[0][0] );
    printClaves( m );
    m.clear();
    System.out.println( "m.isEmpty(): "+m.isEmpty() );
    fill( m,datosPrueba1 );

    // Operaciones para hacer cambios sobre el Set
    m.keySet().removeAll( m.keySet() );
    System.out.println("m.isEmpty(): "+m.isEmpty() );
    }


  public static void main( String args[] ) {
    System.out.println("Probando HashMap");
    test( new HashMap() );
    System.out.println("Probando TreeMap");
    test( new TreeMap() );
    }
  }

//------------------------------------------- Final del fichero java425.java