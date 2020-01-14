//
//  java419.java
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
//     Creacion: 19-Feb-1998  07:45:58
//     Revision: 02-Feb-2002  20:36:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es el ejemplo java418, pero ya funcionando decentemente
 * y si es capaz ya de dar la Prediccion (correcta o no, depende del tiempo)
 * de si andamos abrigados o ya podemos actualizar el ropero
 */
import java.util.*;

// Si se crea una clase que utilice una clave en una Tabla Hash, es
// imprescindible sobreescribir los metodos hashCode() y equals()
// Utilizamos un oso para saber si está hibernando en su temporada de
// invierno o si ya tine que despertarse porque le llega la primavera
class Oso2 {
  int numero;

  Oso2( int n ) {
    numero = n;
    }

  public int hashCode() {
    return( numero );
    }

  public boolean equals( Object obj ) {
    if ( (obj != null) && (obj instanceof Oso2) )
      return( numero == ((Oso2)obj).numero );
    else
      return( false );
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


public class java419 {
  public static void main(String args[]) {
    Hashtable ht = new Hashtable();

    for ( int i=0; i < 10; i++ )
      ht.put( new Oso2( i ),new Prediccion() );
    System.out.println( "ht = "+ht+"\n" );

    System.out.println( "Comprobando la prediccion para el oso #3:");
    Oso2 oso = new Oso2( 3 );
    if ( ht.containsKey( oso ) )
      System.out.println( (Prediccion)ht.get( oso ) );
    }
  }

//------------------------------------------- Final del fichero java419.java