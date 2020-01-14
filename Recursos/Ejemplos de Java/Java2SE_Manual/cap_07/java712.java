//
//  java712.java
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
//     Creacion: 27-Jan-1998  05:52:10
//     Revision: 03-Feb-2002  06:18:07
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa compara el uso de String y StringBuffer, a la hora de
 * realizar operaciones en que interviene la manipulación de cadenas.
 */
import java.util.StringTokenizer;

class java712 {
  static String cadena = "titulo=Tutorial de Java:" +
    "idioma=castellano:" +
    "editor=RA-MA:" +
    "autor=Agustin Froufe:" +
    "e-mail=froufe@arrakis.es";

  public static void main( String args[] ) {
    StringTokenizer st = new StringTokenizer( cadena,"=:" );

    while( st.hasMoreTokens() ) {
      String clave = st.nextToken();
      String valor = st.nextToken();

      System.out.println( clave + "\t" + valor );
        }
      }
    }

//------------------------------------------- Final del fichero java711.java