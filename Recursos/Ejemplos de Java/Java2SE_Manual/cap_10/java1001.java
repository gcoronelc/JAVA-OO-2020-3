//
//  java1001.java
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
//     Creacion: 10-Oct-1997  06:21:58
//     Revision: 03-Feb-2002  11:49:29
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa muestra la instanciacion y ejecucion de tareas o hilos de
 * ejeccucion utilizando el interfaz Runnable en vez de extender la
 * clase Thread
 */
class java1001 {
  static public void main( String args[] ) {
    // Se instancia dos nuevos objetos Thread
    Thread hiloA = new Thread( new MiHilo(),"hiloA" );
    Thread hiloB = new Thread( new MiHilo(),"hiloB" );

    // Se arrancan las dos tareas, para que comiencen su ejecucion
    hiloA.start();
    hiloB.start();

    // Aqui se retrasa la ejecucion un segundo y se captura la
    // posible excepcion que genera el metodo, aunque no se hace
    // nada en el caso de que se produzca
    try {
      Thread.currentThread().sleep( 1000 );
    } catch( InterruptedException e ){}

    // Presenta informacion acerca del Thread o hilo principal
    // del programa
    System.out.println( Thread.currentThread() );
    }
  }


// Esta clase existe solamente para que sea heredada por la clase
// MiHilo, para evitar que esta clase sea capaz de heredar la clase
// Thread, y se pueda implementar el interfaz Runnable en su
// lugar
class NoHaceNada {}


class MiHilo extends NoHaceNada implements Runnable {
  public void run() {
    // Presenta en pantalla informacion sobre este hilo en particular
    System.out.println( Thread.currentThread() );
    }
  }

//------------------------------------------ Final del fichero java1001.java