//
//  java1005.java
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
//     Creacion: 15-Oct-1996  06:21:02
//     Revision: 03-Feb-2002  12:02:32
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y est  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class java1005 extends Applet implements Runnable {
  private Thread t;
  public boolean suspendido = false;
  int contador;

  // Creamos el thread y lo arrancamos
  public void init() {
    ProcesoRaton procesoRaton = new ProcesoRaton();
    addMouseListener( procesoRaton );

    contador = 0;
    t = new Thread( this );
    t.start();
    }


  // Corazón del applet, incrementa el contador, lo pinta en la
  // pantalla y tiene su tiempo de espera, tanto para incrementar
  // de nuevo el contador como para dejar tiempo a la CPU para que
  // atienda a otros applets o aplicacionesque pudiesen convivir
  public synchronized void run() {
    while( true ) {
      // Forzosamente tenemos que capturar este interrupción
      try {
        Thread.currentThread().sleep( 10 );
        // Nos paramos aquí mientras la tarea esté suspendida
        while( suspendido )
          wait( 1000 );
      } catch( InterruptedException e ) {}
      contador++;
      repaint();
      }
    }


  // Actualizamos un contador en la ventana del applet y otro en
  // la consola
  public void paint( Graphics g ) {
    g.drawString( Integer.toString( contador ),10,10 );
    System.out.println( "Contador= "+contador );
    }


  // Paramos el applet
  public void stop() {
    t = null;
    }


  // Cuando se pulsa el ratón dentro del dominio del applet
  // se detiene la ejecución y se reanuda cuando se vuelve a pulsar
  // Esta es una Clase Anidada
  class ProcesoRaton extends MouseAdapter {
    // El método debe ser sincronizado para que la tarea tenga el
    // mismo propietario siempre
    public synchronized void mousePressed( MouseEvent evt ) {
      evt.consume();
      // Aquí cambiamos el valor del flag que indica si la tarea está
      // corriendo o suspendida
      suspendido = !suspendido;
      if( !suspendido )
        notify();
      }
    }
  }

//---------------------------------------- Final del fichero java1005.java
