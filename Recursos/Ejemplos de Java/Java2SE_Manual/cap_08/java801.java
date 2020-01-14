//  java801.java
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
//     Creacion: 02-Jun-1999  05:10:12
//     Revision: 03-Feb-2002  06:57:03
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Ejemplo que presenta información sobre el fichero que se pasa como
 * parámetro en el momento de la ejecución
 */
import java.io.*;

class java801 {
  public static void main( String args[] ) throws IOException {
    // Se comprueba que nos han indicado algún fichero
    if( args.length > 0 ) {
      // Vamos comprobando cada uno de los ficheros que se hayan pasado
      // en la línea de comandos
      for( int i=0; i < args.length; i++ ) {
        // Se crea un objeto File para tener una referencia al fichero
        // físico del disco
        File f = new File( args[i] );

        // Se presenta el nombre y directorio donde se encuentra
        System.out.println( "Nombre: "+f.getName() );
        System.out.println( "Camino: "+f.getPath() );
        // Si el fichero existe se presentan los permisos de lectura y
        // escritura y su longitud en bytes
        if( f.exists() ) {
          System.out.print( "Fichero existente" );
          System.out.print( (f.canRead() ? " y se puede Leer" : "" ) );
          System.out.print( (f.canWrite() ? " y se puese Escribir" : "" ) );
          System.out.println( "." );
          System.out.println( "La longitud del fichero es de "+
            f.length()+" bytes" );
          }
        else {
          System.out.println( "El fichero no existe." );
          }
        }
      }
    else {
      System.out.println( "Debe indicar un fichero." );
      }
    }
  }

//------------------------------------------- Final del fichero java801.java