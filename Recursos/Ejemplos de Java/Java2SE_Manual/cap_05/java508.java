//
//  java508.java
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
//     Creacion: 13-Aug-1997  08:50:14
//     Revision: 02-Feb-2002  21:09:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicación muestra como se puede instanciar un objeto
 * llamando a su constructor de defecto y, también llamando
 * a un constructor parametrizado
 * Además se muestra el uso de super() en un método para llamar
 * al constructor en la superclase
 */

class SuperClase {
  int varInstancia;

  // Es necesario proporcionar el constructor por defecto,que
  // es aquel que no tiene parametros de llamada
  SuperClase(){}

  // Este es el constructor parametrizado de la superclase
  SuperClase( int pDato ) {
    System.out.println( "Dentro del constructor de la SuperClase" );
    varInstancia = pDato;
    }

  void verVarInstancia() {
    System.out.println( "El Objeto contiene " + varInstancia );
    }
 }


class SubClase extends SuperClase {
  // Este es el constructor parametrizado de la subclase
  SubClase( int bDato ) {
    // La siguiente sentencia println no compila, porque la llamada
    // a super() debe estar al principio de un metodo en caso de
    // que aparezca
    // System.out.println( "Dentro del constructor de la SubClase" );

    // Llamamos al constructor de la superclase
    super( bDato );
    System.out.println( "Dentro del constructor de la SubClase" );
    }
 }


class java508 {
  public static void main( String args[] ) {
    System.out.println( "Lanzando la aplicacion" );

    // Instanciamos un objeto de este tipo llamando al constructor
    // de defecto
    java508 obj = new java508();

    // Llamamos a la funcion pasandole un constructor de la subclase
    // parametrizado como parametro
    obj.miFuncion( new SubClase( 100 ) );
    }

  // Esta funcion recibe un objeto y llama a uno de sus metodos
  // para presentar en pantalla el dato que contiene el objeto,
  // en este caso el metodo es heredado de la SuperClase
  void miFuncion( SubClase objeto ) {
    objeto.verVarInstancia();
    }
  }

//------------------------------------------- Final del fichero java508.java