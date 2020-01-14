//
//  java501.java
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
//     Creacion: 29-Sep-1997  16:27:23
//     Revision: 02-Feb-2002  21:02:18
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo ilustra la instanciacion de variables primitivas.
 * Tambien muestra la forma de instanciar objetos dinamicos y arrays
 * dinamicos de objetos.
 *
 * La salida que se obtiene tras la ejecucion del programa se
 * reproduce en las lineas siguientes:
 * miVariableInt contiene 6
 * miPunteroObj contiene java501@208741
 * miPunteroObj apunta a 12
 * miArrayPunterosObj contiene java501@20875f java501@208760 java501@208761
 * miArrayPunterosObj apunta a 13 14 15
 */

class java501 {
  // Variable de instancia de la clase
  private int datoObj;

  // Constructor parametrizado
  public java501( int dato ) {
    datoObj = dato;
    }

  // Metodo para recuperar datos
  public int getData() {
    return datoObj;
    }

  public static void main( String args[] ) {
    // Instancia e inicializa una variable primitiva estatica
    int miVariableInt = 6;
    System.out.println( "miVariableInt contiene "+miVariableInt );

    // Instancia e inicializa un objeto dinamico
    java501 miPunteroObj = new java501( 12 );
    System.out.println( "miPunteroObj contiene "+miPunteroObj );
    System.out.println( "miPunteroObj apunta a "+miPunteroObj.getData() );

    // Instancia un array dinamico de objetos
    java501 miArrayPunterosObj[] = new java501[3];

    // Coloca algunos datos en el array dinamico de objetos
    for ( int i=0; i < 3; i++ )
      miArrayPunterosObj[i] = new java501( i+13 );

    // Presenta las direcciones en el array de punteros a objetos
    System.out.print( "miArrayPunterosObj contiene " );
    for ( int i=0; i < 3; i++ )
      System.out.print( miArrayPunterosObj[i] + " " );
    System.out.println();

    // Presenta los datos "apuntador por" el array de punteros
    System.out.print( "miArrayPunterosObj apunta a " );
    for ( int i=0; i < 3; i++ )
      System.out.print( miArrayPunterosObj[i].getData()+" " );
    System.out.println();
    // Hace que el contenido del buffer de salida aparezca en pantalla
    System.out.println();
    }
  }

//------------------------------------------- Final del fichero java501.java