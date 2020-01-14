//
//  java418.java
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
//     Creacion: 19-Feb-1998  07:36:40
//     Revision: 02-Feb-2002  20:36:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo parece correcto, pero lo cierto es que no funciona
 * del todo bien, ya que no es capaz de hacer la Prediccion para
 * todo lo que se le pregunte
 */
import java.util.*;

// Utilizamos un oso para saber si está hibernando en su temporada de
// invierno o si ya tine que despertarse porque le llega la primavera
class Oso {
  int numero;
  Oso( int n ) {
    numero = n;
    }
  }

// En función de la oscuridad, o claridad del día, pues intenta
// saber si ya ha la primavera ha asomado a nuestras puertas
class Prediccion {
  boolean oscuridad = Math.random() > 0.5;

  public String toString() {
    if ( oscuridad )
      return( "Seis semanas mas de Invierno!" );
    else
      return( "Entrando en la Primavera!" );
    }
  }

public class java418 {
  public static void main( String args[] ) {
    Hashtable ht = new Hashtable();

    for ( int i=0; i < 10; i++ )
      ht.put( new Oso( i ),new Prediccion() );
    System.out.println( "ht = "+ht+"\n" );

    System.out.println( "Comprobando la prediccion para el oso #3:" );
    Oso oso = new Oso( 3 );
    if ( ht.containsKey( oso ) )
      System.out.println( (Prediccion)ht.get( oso ) );
    }
  }

//------------------------------------------- Final del fichero java418.java