//
//  java904.java
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
//     Creacion: 30-Sep-1997  13:24:34
//     Revision: 03-Feb-2002  11:30:54
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra como crear, lanzar, recoger y procesar un objeto
 * Exception propio, que contenga información de diagnóstico de la
 * aplicación
 * Además, se demuestra que el código en le bloque finally se ejecuta
 * independientemente de que el controlador de excepciones intente
 * concluir la ejecución del programa ejecutando una sentencia return
 */

// Esta clase es la que utiliza para construir el objeto Exception propio
// La variable de instancia en el objeto se utiliza para simular el paso
// de la información de diagnóstico desde el punto donde se lanza la
// excepción hacia el controlador de excepciones
class MiExcepcion extends Exception {
  int datoInformacion;

  // Constructor
  MiExcepcion( int datos ) {
    // Guarda la información de diagnóstico en el objeto
    datoInformacion = datos;
    }

  // Sobreescribe el método de Throwable
  public String getMessage() {
    return( "La maxima es: Compra Barato, Vende Caro\n"
      + "El valor de datoInformacion es: "
      + datoInformacion );
    }
  }


class java904 {
  public static void main( String args[] ) {
    try {
      for( int cnt=0; cnt < 5; cnt++ ) {
        // Lanza la excepción propia y pasa la información
        // si "cnt" es 3
        if( cnt == 3 )
          throw new MiExcepcion( 3 );
        // Transfiere el control antes de que "cnt" sea 3
        System.out.println( "Procesando datos para cnt = :"
          + cnt );
        }

      System.out.println( "Esta linea no debe ejecutarse nunca." );
    } catch( MiExcepcion e ) {
      System.out.println(
        "Controlador de Excepciones, recoge el mensaje\n"
        + e.getMessage() );
      System.out.println(
        "Controlador de Excepciones, intentando finalizar la ejecucion.\n"
        + "Ejecutando la sentencia return." );
      return;
    } finally {
      System.out.println(
        "Bloque finally, para probar que se entra en el independientemente\n"
        + "de la sentencia return del Controlador de Excepciones." );
      }

    System.out.println(
      "Esta sentencia nunca se ejecutara debido a la sentencia return"
      + "del controlador de excepciones." );
    }
  }

//------------------------------------------- Final del fichero java904.java