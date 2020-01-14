//
//  java1004.java
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
//     Creacion: 15-Oct-1996  06:12:33
//     Revision: 03-Feb-2002  11:58:07
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y est  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class java1004 extends Applet implements Runnable {
  Thread t;
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
  // atienda a otros applets o aplicaciones que pudiesen convivir
  public void run() {
    Thread miThread = Thread.currentThread();

    while( t == miThread ) {
      contador++;
      repaint();
      // Forzosamente tenemos que capturar esta interrupción
      try {
        miThread.sleep( 10 );
      } catch( InterruptedException e ) {
        e.printStackTrace();
        }
      }
    }


  // Actualizamos un contador en la ventana del applet y otro en
  // la consola
  public void paint( Graphics g ) {
    g.drawString( Integer.toString( contador ),10,10 );
    System.out.println( "Contador= "+contador );
    }


  // Paramos el applet, pero sin llamar al método stop(), por el
  // peligro de caer en un punto de no retorno
  public void stop() {
    t = null;
    }


  // Cuando se pulsa el ratón dentro del dominio del applet
  // se detiene la ejecución
  // Esta es una Clase Anidada
  class ProcesoRaton extends MouseAdapter {
    public void mousePressed( MouseEvent evt ) {
      stop();
      }
    }
  }

//---------------------------------------- Final del fichero java1004.java
