//
//  java721.java
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
//     Creacion: 15-Dic-2001  20:15:48
//     Revision: 03-Feb-2002  06:23:00
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo utiliza expresiones regulares para obtener los comentarios
 * que se incluyen en el fichero que se pasa por parámetro en la llamada,
 * de este propio fichero en caso contrario.
 * Utiliza la clase "nio" para crear un canal de lectura con el
 * fichero que se indique
 */
import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class java721 {
  public static void main( String args[] ) throws Exception {
    // Nombre del fichero de que queremos extraer los comentarios
    String nombreFichero = "java721.java";
    // Creamos el patrón con los identificadores de comentarios de
    // línea única, es decir "//"
    Pattern p = Pattern.compile( "//.*$",Pattern.MULTILINE );

    // Comprobamos si se ha indicado el nombre de un fichero en la
    // llamada a la aplicación
    if( args.length > 0 )
      nombreFichero = args[0];

    // Creamos un canal para el fichero de entrada
    File f = new File( nombreFichero );
    FileInputStream fis = new FileInputStream( f );
    FileChannel fc = fis.getChannel();

    // Obtenemos un buffer a partir del fichero de entrada
    ByteBuffer bb = fc.map( FileChannel.MapMode.READ_ONLY,0,
      (int)fc.size() );
    Charset cs = Charset.forName( "8859_1" );
    CharsetDecoder cd = cs.newDecoder();
    CharBuffer cb = cd.decode( bb );

    // Cabecera de información
    System.out.println( "Comentarios del fichero: "+nombreFichero );

    // Buscamos las coincidencias
    Matcher m = p.matcher( cb );
    while( m.find() ) {
      // Imprimimos todos los comentarios en la pantalla
      System.out.print( m.group() );
      }
    }
  }

//------------------------------------------- Final del fichero java721.java