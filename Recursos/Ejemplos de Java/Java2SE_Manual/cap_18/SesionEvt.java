//
//  SesionEvt.java
//  Copyright (c) 2002, Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 28-Ene-2002  05:01:57
//     Revision: 09-Feb-2002  14:06:55
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo implementa la interfaz HttpSessionListener, para recoger
 * los eventos de creaci�n e invalidaci�n de las sesiones que se
 * establecen con el servidor.
 * Se mantiene el control sobre el n�mero de sesiones abiertas con el
 * servidor a trav�s de la variable miembro de la clase que es
 * actualizada en funci�n de los eventos que recibe la propia clase.
 */

import javax.servlet.http.*;

public class SesionEvt implements HttpSessionListener {
  private static int sesionesActivas = 0;

  // Evento de creaci�n de sesiones
  public void sessionCreated( HttpSessionEvent evt ) {
    sesionesActivas++;
    }

  // Evento de invalidaci�n de sesiones
  public void sessionDestroyed( HttpSessionEvent evt ) {
    if( sesionesActivas > 0 )
      sesionesActivas--;
    }

  // Devuelve el n�mero de sesiones activas
  public int getSesionesActivas() {
    return( sesionesActivas );
    }
  }

//----------------------------------------- Final del fichero SesionEvt.java
