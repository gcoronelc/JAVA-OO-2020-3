//
//  java1002.java
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
//     Creacion: 29-Sep-1997  11:54:28
//     Revision: 27-May-1999  09:13:27
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra la instanciación y ejecución de tareas extendiendo
 * la clase Thread, en vez de implementar el interfaz Runnable
 */
class java1002 {
  static public void main( String args[] ) {
    // Se instancia dos nuevos objetos Thread
    Thread hiloA = new Thread( new MiHilo(),"hiloA" );
    Thread hiloB = new Thread( new MiHilo(),"hiloB" );

    // Se arrancan los dos hilos, para que comiencen su ejecución
    hiloA.start();
    hiloB.start();

    // Aqui se retrasa la ejecución un segundo y se captura la
    // posible excepción que genera el método, aunque no se hace
    // nada en el caso de que se produzca
    try {
      Thread.currentThread().sleep( 1000 );
    } catch( InterruptedException e ){}

    // Presenta información acerca del Thread o hilo principal
    // del programa
    System.out.println( Thread.currentThread() );
    }
  }


class MiHilo extends Thread {
  public void run() {
    // Presenta en pantalla información sobre este hilo en particular
    System.out.println( Thread.currentThread() );
    }
  }

//------------------------------------------ Final del fichero java1002.java