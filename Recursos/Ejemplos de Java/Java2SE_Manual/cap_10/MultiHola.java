//
//  MultiHola.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 18-Sep-1996  05:51:27
//     Revision: 03-Feb-2002  11:47:59
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y est  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Definimos unas sencillas tareass. Se detendrán un rato
 * antes de imprimir sus nombres y retardos
 */
class TestTh extends Thread {
  private String nombre;
  private int retardo;

  // Constructor para almacenar nuestro nombre
  // y el retardo
  public TestTh( String s,int d ) {
    nombre = s;
    retardo = d;
    }

  // El metodo run() es similar al main(), pero para
  // threads. Cuando run() termina el thread muere
  public void run() {
    // Retasamos la ejecución el tiempo especificado
    try {
      sleep( retardo );
    } catch( InterruptedException e ) {
      ;
      }

    // Ahora imprimimos el nombre
    System.out.println( "Hola Mundo! "+nombre+" "+retardo );
    }
  }


public class MultiHola {
  public static void main( String args[] ) {
    TestTh t1,t2,t3;

    // Creamos los threads
    t1 = new TestTh( "Thread 1",(int)(Math.random()*2000) );
    t2 = new TestTh( "Thread 2",(int)(Math.random()*2000) );
    t3 = new TestTh( "Thread 3",(int)(Math.random()*2000) );

    // Arrancamos los threads
    t1.start();
    t2.start();
    t3.start();
    }
  }

//----------------------------------------- Final del fichero MultiHola.java