//
//  java507.java
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
//     Creacion: 13-Aug-1997  08:13:20
//     Revision: 02-Feb-2002  21:08:58
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicacion muestra como se puede instanciar un objeto
 * llamando a su constructor de defecto y, tambien llamando
 * a un constructor parametrizado
 */

class MiClase {
  int varInstancia;

  // Este es el constructor parametrizado
  MiClase( int dato ) {
    // rellenamos la variable de instancia con los datos
    // que se pasan al constructor
    varInstancia = dato;
    }

  void verVarInstancia() {
    System.out.println( "El Objeto contiene " + varInstancia );
    }
  }


class java507 {
  public static void main( String args[] ) {
    System.out.println( "Lanzando la aplicacion" );

    // Instanciamos un objeto de este tipo llamando al constructor
    // de defecto
    java507 obj = new java507();

    // Llamamos a la funcion pasandole un constructor parametrizado
    // como parametro
    obj.miFuncion( new MiClase( 100 ) );
    }

  // Esta funcion recibe un objeto y llama a uno de sus metodos
  // para presentar en pantalla el dato que contiene el objeto
  void miFuncion( MiClase objeto){
    objeto.verVarInstancia();
    }
  }

//------------------------------------------- Final del fichero java507.java