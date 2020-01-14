//
//  Latido.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 07-Dic-2001  18:47:52
//     Revision: 09-Feb-2002  13:54:01
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la clase que se encarga de invocar al método
 * latido() del servidor en busca de conexiones inactivas.
 * No tiene encomendada ninguna otra acción y se ejecuta
 * como una tarea independiente.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Latido extends Thread {
  // Periodo entre cada comprobación
  public int periodo;
  protected LatidoIf latidoCtl;

  public Latido( int periodo,LatidoIf li ) {
    this.periodo = periodo;
    this.latidoCtl = li;
    this.start();
    }

  // Este es el método que contiene el bucle infinito, que
  // permite que durante la vida de la tarea se compruebe
  // continuamente si hay conexiones de clientes inactivas
  public void run() {
    for( ;; ) {
      if( latidoCtl != null ) {
        latidoCtl.latido();
        }
      try {
        sleep( periodo );
      } catch( InterruptedException e ) {
        System.out.println( "Tarea Latido interrumpida: "+e );
        }
      }
    }
  }

//-------------------------------------------- Final del fichero Latido.java
