//
//  AplicacionEvt.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 28-Ene-2002  05:13:47
//     Revision: 09-Feb-2002  14:05:47
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo implementa la interfaz ServletContextListener para
 * recibir los eventos de inicialización y destrucción de la aplicación.
 * Cuando se recibe el evento de inicialización, se almacena el instante
 * en que se produce, de forma que luego pueda ser proporcionada esa
 * información a quien la solicite.
 */

import javax.servlet.*;

public class AplicacionEvt implements ServletContextListener {
  private static long inicio = 0L;

  // Evento de lanzamiento de la aplicación
  public void contextInitialized( ServletContextEvent evt ) {
    inicio = System.currentTimeMillis();
    }

  // Evento de cierre de la aplicación
  public void contextDestroyed( ServletContextEvent evt ) {
    }

  // Devuelve el instante en que se arrancó la aplicación
  public long getInicializacion() {
    return( inicio );
    }
  }

//------------------------------------- Final del fichero AplicacionEvt.java
