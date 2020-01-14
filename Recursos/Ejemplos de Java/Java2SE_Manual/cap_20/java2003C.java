
//  java2003C.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 26-Jun-1999  14:12:23
//     Revision: 09-Feb-2002  21:03:28
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un cliente RMI en el que se muestra la diferencia que existe en
 * el paso de datos entre objetos remotos y objetos locales que no
 * implementen el interface Remote
 */
import java.rmi.*;
import java.util.*;

public class java2003C {

  public static void main( String[] args ) {
    System.out.println( "\nEjecutando el cliente..." );

    String direccion = "rmi://localhost/";
    try {
      java2003Ia refObj =
        (java2003Ia)Naming.lookup( direccion+"ObjetoRemoto" );

      System.out.println( "Pedimos la fecha al servidor" );
      Date fecha = refObj.getFecha();
      System.out.println( "La fecha del Objeto Remoto es: " +
        fecha.toString() );

      System.out.println( "Incrementamos la fecha 2 dias" );
      Calendar cal = Calendar.getInstance();
      cal.add( Calendar.DAY_OF_YEAR,2 );
      fecha = cal.getTime();
      System.out.println( "La fecha es ahora: " +
        fecha.toString() );

      System.out.println( "Pedimos la fecha al servidor" );
      fecha = refObj.getFecha();
      System.out.println( "La fecha del Objeto Remoto es ahora: " +
        fecha.toString() );

      System.out.println( "Pedimos el objeto al servidor" );
      java2003Ib obj = refObj.getObjeto();
      System.out.println( "El dato del Objeto Remoto es: " +
        obj.getDato() );

      System.out.println( "Fijamos el dato del objeto a 999" );
      obj.setDato( 999 );
      System.out.println( "El dato en el objeto es ahora: " +
        obj.getDato() );

      System.out.println( "Pedimos el objeto al servidor" );
      obj = refObj.getObjeto();
      System.out.println( "El dato del Objeto Remoto es ahora: " +
        obj.getDato() );
    }catch( Exception e ) {
      e.printStackTrace();
      }
    System.exit( 0 );
    }
  }

//----------------------------------------- Final del fichero java2003C.java