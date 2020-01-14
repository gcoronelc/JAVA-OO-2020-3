//
//  java514.java
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
//     Creacion: 02-Oct-1997  03:16:46
//     Revision: 02-Feb-2002  21:15:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo es para ilustrar el uso de "interface". Se utilizan los
 * interfaces definidos en los ficheros MiInterfaz.java y Constantes.java
 * Se implementas versiones diferentes de los metodos put() y get()
 * declarados en el interfaz, en las clases ClaseA y ClaseB
 */

class ClaseA implements Constantes,MiInterfaz {
  double dDato;

  // Define las versiones de put() y get() que utiliza la ClaseA
  public void put( int dato ) {
    // Se usa "pi" del interfaz Constantes
    dDato = (double)dato * pi;
    System.out.println(
      "En put() de ClaseA, usando pi del interfaz Constantes: "
      + dDato );
    }

  public int get() {
    return( (int)dDato );
    }

  // Se define un metodo show() para la ClaseA que no esta declarado
  // en el interfaz MiInterfaz
  void show() {
    System.out.println(
      "En show() de ClaseA, dDato = " + dDato );
    }
  }


class ClaseB implements Constantes,MiInterfaz {
  int dDato;

  // Define las versiones de put() y get() que utiliza la ClaseB
  public void put( int dato ) {
    // Se usa "constanteInt" del interfaz Constantes
    dDato = dato * constanteInt;
    System.out.println(
      "En put() de ClaseB, usando constanteInt del interfaz Constantes: "
      + dDato );
    }

  public int get() {
    return( dDato );
    }
  }


class java514 {
  public static void main( String args[] ) {
    System.out.println(
      "Instancia objA de tipo ClaseA, mete datos y los muestra." );
    ClaseA objA = new ClaseA();
    objA.put( 2 );
    objA.show();

    System.out.println(
      "\nAsigna objA a la variable de referencia de tipo " +
      "MiInterfaz llamada objAA." );
    MiInterfaz objAA = objA;
    System.out.println(
      "Invoca el metodo put() sobre objAA para modificar los datos.");
    objAA.put( 4 );
    System.out.println(
      "Invoca el metodo get() sobre objAA para ver los datos modificados.");
    System.out.println("dato objA = " + objAA.get() );

    System.out.println(
      "\nInstancia un objeto de tipo ClaseB llamado objB.\n" +
      "Asigna inmediatamente la refrencia a una variable de referecia " +
      " de tipo MiInterfaz\n en lugar de a una variable de tipo ClaseB" );
    MiInterfaz objB = new ClaseB();
    System.out.println(
      "Invoca a su metodo put() para guardar datos." );
    objB.put(11);
    System.out.println(
      "Invoca su metodo get() para visualizar los datos." );
    System.out.println( "dato objB = " + objB.get() );

    System.out.println(
      "\nAsigna correctamente objA a objB y muestra objB" );
    objB = objA;
    System.out.println( "dato objB = " + objB.get() );

    System.out.println(
      "\nInvoca el metodo put() sobre objAA para modificar sus datos.");
    objAA.put( 16 );
    System.out.println(
      "Asigna correctamente objAA a objB y muestra objB" );
    objB = objAA;
    System.out.println( "dato objB = " + objB.get() );

    System.out.println(
      "\nRestaura a objB su tipo y valor iniciales." );
    // objB ya esta definido como tipo MiInterfaz
    objB = new ClaseB();
    objB.put( 11 );
    System.out.println(
      "Asigna correctamente objB a objAA y muestra objAA" );
    objAA = objB;
    System.out.println( "dato objAA = " + objAA.get() );

    System.out.println(
      "\nIntenta asignar objB a objA y falla porque se necesita " +
      "un \n\"Moldeado especifico que convierta MiInterfaz a ClaseA\"." );
    // objA = objB;   // Comentado para poder ejecutar

    System.out.println(
      "\nIntenta invocar el metodo show() de objAA y falla porque" +
      "\n\"El metodo show() no esta declarado en el " +
      "interfaz MiInterfaz\"." );
    // objAA.show();  // Comentado para poder ejecutar

    System.out.println("Fin del programa.");
    }
  }

//------------------------------------------- Final del fichero java514.java