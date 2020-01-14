//
//  java720.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 15-Dic-2001  19:23:23
//     Revision: 03-Feb-2002  06:20:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa comprueba la validez de una direcci�n de correo
 * electr�nico utilizando expresiones regulares.
 * Comprueba que la direcci�n no empieza por un punto, ni por www, que
 * contiene el car�cter @, que contiene una palabra a cada lado de la @
 * y elimina cuanquier car�cter ilegal
 *
 * Si el lector modifica "cadena" podr� comprobar la utilidad del
 * ejemplo. O tambi�n puede introducir una cadena de texto en la
 * invocaci�n de la aplicaci�n, comprob�ndose en este caso la validez
 * de esa cadena como direcci�n de correo electr�nico.
 */
import java.util.regex.*;

class java720 {
  public static void main( String args[] ) throws Exception {
    // Texto que utilizamos como prueba en la comparaci�n
    String cadena = "pepito#grillo@universo.es";

    // Si se ha intorducido una cadena de texto en la invocaci�n de
    // la aplicaci�n, la utilizamos para realizar la comprobaci�n
    // de la existencia de los patrones de b�squeda
    if( args.length > 0 )
      cadena = args[0];

    // Convertimos la cadena de b�squeda de @ y . al principio de
    // la l�nea en un patr�n
    Pattern p = Pattern.compile( "^\\.|^\\@" );
    Matcher m = p.matcher( cadena );
    // Si la encontramos es que hay fallo
    if( m.find() )
      System.out.println(
        "El E-mail no puede empezar con puntos o signo @" );

    // Convertimos la cadena de b�squeda de www al principio de
    // la l�nea en un patr�n
    p = Pattern.compile( "^www\\." );
    m = p.matcher( cadena );
    // Si la encontramos es que hay fallo
    if( m.find() )
      System.out.println(
        "S�lo las p�ginas web pueden empezar por \"www.\" " );

    // Convertimos la cadena de b�squeda de carateres ilegales en
    // un patr�n
    p = Pattern.compile( "[^A-Za-z0-9\\.\\@_\\-~#]+" );
    m = p.matcher( cadena );
    StringBuffer sb = new StringBuffer();
    // Comprobamos si hay caracteres ilegales
    boolean resultado = m.find();
    boolean caracteresIlegales = false;

    // Si hay caracteres ilegales, los eliminamos y lo indicaremos
    // al salir
    while( resultado ) {
      caracteresIlegales = true;
      m.appendReplacement( sb,"" );
      resultado = m.find();
      }
    m.appendTail( sb );
    cadena = sb.toString();
    // Si hay carateres ilegales, lo indicamos al usuario
    if( caracteresIlegales )
      System.out.println( "La direcci�n conten�a caracteres ilegales" );
    }
  }

//------------------------------------------- Final del fichero java720.java