//
//  java1203.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 30-Jan-1998  15:58:42
//     Revision: 03-Feb-2002  12:40:50
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Metodos comunes y generales para cualquier tipo de evento que se
 * vaya a generar, usado posteriormente en la aplicacion de control
 * Se utiliza "abstract" y no "interface" porque al basarse en la hora
 * el evento, se necesita inicializacion, que no se permite en un
 * interface
 */
abstract public class java1203 {
  private long horaEvt;

  public java1203( long horaEvento ) {
    horaEvt = horaEvento;
    }

  public boolean listo() {
    return( System.currentTimeMillis() >= horaEvt );
    }

  abstract public void accion();
  abstract public String descripcion();
  }

//------------------------------------------ Final del fichero java1203.java