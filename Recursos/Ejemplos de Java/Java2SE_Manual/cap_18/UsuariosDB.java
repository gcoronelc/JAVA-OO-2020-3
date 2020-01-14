//
//  UsuariosDB.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 12-Ene-2002  11:22:03
//     Revision: 09-Feb-2002  14:14:03
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.io.*;
import java.util.*;

public final class UsuariosDB {
  // Archivo que utilizamos como base de datos
  static String fichero = "/favoritos.txt";
  static Vector usuarios;

  // Utilizamos un Vector para recuperar la información que está almacenada
  // en el archivo de texto.
  // Cuando abrimos el fichero creamos el Vector, y cuando lo cerramos
  // o lo actualizamos, escribimos el contenido del Vector
  public static void abrirBD() {
    usuarios = new Vector();
    leerFavFichero( fichero );
    }

  public static void abrirBD( String _fichero ) {
    usuarios = new Vector();
    fichero = _fichero;
    leerFavFichero( _fichero );
    }

  public static void cerrarBD() {
    escribirFavFichero( fichero );
    usuarios = null;
    }

  public static void actualizaBD() {
    escribirFavFichero( fichero );
    }

  // Este es el método encargado de leer el formato en que se ha
  // guardado la información en el fichero y recuperar los datos de
  // cada uno de los usuarios y su lista de favoritos.
  // El formato con que se guarda la información es el siguiente:
  //   "usuario clave id1!url1|id2!url2|...idn!urln"
  // con cada usuario ocupando una única línea
  public static synchronized void leerFavFichero( String _fichero ) {
    try {
      BufferedReader buff = new BufferedReader( new FileReader(_fichero) );
      String linea;
      while( (linea=buff.readLine()) != null ) {

        usuarios.add( new FavoritosDB(null,null,linea.trim()) );
        }
      buff.close();
    } catch( Exception e ){
      e.printStackTrace();
      }
    }

  // Este es el método contrario al anterior. Toda la información de
  // usuarios y enlaces que esté almacenada en el Vector de usuarios,
  // se escribirá en el fichero en el formato anterior.
  // Tanto este método como el anterior están sincronizados para
  // evitar múltiples escrituras, o inconsistencias en la lectura
  // por encontrarse en proceso de escritura
  public static synchronized void escribirFavFichero( String _fichero ) {
    try {
      PrintWriter salida = new PrintWriter( new BufferedWriter(
        new FileWriter(_fichero) ) );
      for( Enumeration enum = usuarios.elements();
        enum.hasMoreElements(); ) {
        salida.println( ((FavoritosDB)enum.nextElement()).getDBString() );
        }
      salida.close();
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }

  // Este método se encarga de recuperar la información de un
  // usuario concreto para su autenticación
  public static FavoritosDB getUsuario( String _usuario,String _pass ) {
    FavoritosDB nombre = null;
    for( Enumeration enum = usuarios.elements();
      enum.hasMoreElements(); ) {
      nombre = (FavoritosDB)enum.nextElement();

      if( nombre.usuario.equals(_usuario) && nombre.password.equals(_pass) ) {
        break;
        }
      else {
        nombre = null;
        }
      }
    return( nombre );
    }

  // Este método añade un nuevo usuario al Vector, en base a su
  // nombre y contraseña
  public static void nuevoUsuario( String _usuario,String _pass ) {
    usuarios.add( new FavoritosDB(_usuario,_pass,null) );
    }
  }

//---------------------------------------- Final del fichero UsuariosDB.java
