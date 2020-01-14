//
//  java515.java
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
//     Creacion: 17-Oct-1997  08:26:30
//     Revision: 02-Feb-2002  21:08:13
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra cómo se pasan parámetros a métodos, variables
 * de tipo primitivo por valor y objetos por referencia
 */

// Esta clase se usa para instanciar un objeto referencia
class MiClase {
  int varInstancia = 100;
  }

// Clase principal
class java515 {

  // Función para ilustrar el paso de parámetros
  void pasoVariables( int varPrim,MiClase varRef ) {
    System.out.println( "--> Entrada en la funcion pasoVariables" );
    System.out.println( "Valor de la variable primitiva: "+varPrim );
    System.out.println( "Valor contenido en el objeto: "+
      varRef.varInstancia );

    System.out.println( "-> Modificamos los valores" );
    varRef.varInstancia = 101;
    varPrim = 201;

    System.out.println( "--> Todavia en la funcion pasoVariables" );
    System.out.println( "Valor de la variable primitiva: "+varPrim );
    System.out.println( "Valor contenido en el objeto: "+
      varRef.varInstancia );
    }

  public static void main( String args[] ) {
    // Instanciamos un objeto para acceder a sus métodos
    java515 aObj = new java515();

    // Instanciamos un objeto normal
    MiClase obj = new MiClase();
    // Instanciamos una variable de tipo primitivo
    int varPrim = 200;

    System.out.println( "> Estamos en main()" );
    System.out.println( "Valor de la variable primitiva: "+varPrim );
    System.out.println( "Valor contenido en el objeto: "+
      obj.varInstancia );

    // Llamamos al método del objeto
    aObj.pasoVariables( varPrim,obj );

    System.out.println( "> Volvemos a main()" );
    System.out.println( "Valor de la variable primitiva, todavia : "+
      varPrim );
    System.out.println( "Valor contenido ahora en el objeto: "+
      obj.varInstancia );
    }
  }

//------------------------------------------- Final del fichero java515.java