//
//  FileVolumenNodoHelper.java
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
//     Creacion: 28-Dic-2001  15:01:19
//     Revision: 07-Feb-2002  05:59:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase implementa la interfaz VolumenNodoHelper para cualquier
 * sistema de ficheros soprotado por Java
 */
import java.io.File;

public class FileVolumenNodoHelper implements VolumenNodoHelper {
  // Devuelve el nombre del fichero que se pasa, o el directorio si
  // el nombre está en blanco
  public String toString( final Object object ) {
    final File file = (File)object;
    final String name = file.getName();
    return( (name.length() > 0) ? name : file.getPath() );
    }

  // Devuelve el array de ficheros que cuelgan del que se pasa
  public Object[] getHijos( final Object object ) {
    File[] ficheros;

    if( (ficheros = ((File)object).listFiles()) == null )
      ficheros = new File[0];

    return( ficheros );
    }

  // Indica si el fichero que se pasa es un directorio
  public boolean esContenedor( final Object obj ) {
    return( ((File)obj).isDirectory() );
    }

  // Devuelve el tamaño del fichero que se pasa como parámetro
  public long getTamano( final Object obj ) {
    return( ((File)obj).length() );
    }
  }

//----------------------------- Final del fichero FileVolumenNodoHelper.java
