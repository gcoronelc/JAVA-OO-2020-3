//
//  java601.java
//  Copyright (c) 2002 Agustin Froufe
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
//     Creacion: 02-Feb-2002  20:34:58
//     Revision: 02-Feb-2002  21:25:32
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicación muestra varios ejemplos de formateo de fechas y
 * y horas, así como el análisis de cadenas para convertirlas en
 * fechas.
 * También se hace uso de la clase Timer para presentar un reloj
 * digital actualizándose cada segundo.
 * Utilizar "^C" para terminar la ejecución de la aplicación.
 */
import java.util.*;
import java.text.*;

public class java601 {

  public static void main( String args[] ) {
    // Presenta la fecha actual utilizando el formato por defecto de la
    // configuración regional local que esté definada por el sistema
    DateFormat hoy = DateFormat.getDateInstance();
    System.out.println( hoy.format(new Date()) );

    // Presenta la hora actual utilizando el formato corto definido para
    // la configuración regional local del sistema
    DateFormat hora = DateFormat.getTimeInstance( DateFormat.SHORT );
    System.out.println( hora.format(new Date()) );

    // Presenta la fecha y hora en formato largo en los dos casos
    DateFormat fechaLarga =
      DateFormat.getDateTimeInstance( DateFormat.FULL,DateFormat.FULL );
    System.out.println( fechaLarga.format(new Date()) );

    // Utilizamos un formato personalizado para imprimir la
    // fecha actual
    DateFormat fmtIngles = new SimpleDateFormat( "yyyy.MM.dd" );
    System.out.println( fmtIngles.format(new Date()) );
    // Analizamos una cadena de texto que contiene una fecha
    try {
      DateFormat formato = new SimpleDateFormat( "dd.MM.yyyy" );
      Date fecha = formato.parse( "22.02.2002" );
      System.out.println( formato.format(fecha) );
    }catch( ParseException e ) {
      System.out.println( "Fecha errónea" );
      }

    // Necesitamos que sea "final" el formato, porque lo vamos a
    // invocar desde la tarea que presenta la hora, que corresponde
    // a una clase anidada
    final DateFormat fmtHora =
      DateFormat.getTimeInstance( DateFormat.MEDIUM );
    Timer timer = new Timer();
    System.out.println( "\nHora Actual:" );
    TimerTask horaActual = new TimerTask() {
      public void run() {
        System.out.print( "\r"+fmtHora.format(new Date()) );
        }
      };
    timer.schedule( horaActual,0,1000 );
    }
  }

//------------------------------------------- Final del fichero java601.java