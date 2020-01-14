//
//  FavoritosDB.java
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
//     Creacion: 12-Ene-2002  11:15:48
//     Revision: 09-Feb-2002  14:13:25
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase es la encargada demantener el control sobre los usuarios y
 * los enlaces favoritos, leyendo y almacenando la información de cada
 * uno de ellos en el archivo favoritos.txt.
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class FavoritosDB{
  public String usuario;
  protected String password;
  public Hashtable favoritos;

  public FavoritosDB( String _usuario,String _pass,String _cadena ) {
    this.usuario = _usuario;
    this.password = _pass;
    this.favoritos = new Hashtable();
    if( _cadena != null ) {
      this.leeDBString( _cadena );
      }
    }

  // Añade un favorito a la cadena qque corresponde al usuario que
  // se indica
  public void nuevoFavorito( String _usuario,String _favorito ) {
    favoritos.put( _usuario,_favorito );
    }

  // Elimina un favorito de la cadena que corresponde al usuario
  // que se indica
  public void eliminaFavorito( String _usuario ) {
    favoritos.remove( _usuario );
    }

  // Este método es el encargado de convertir la información del
  // usuario al formato utilizado para el almacenamiento en
  // el archivo
  public String getDBString() {
    String cadena = "";
    boolean primero = true;
    cadena += URLEncoder.encode( this.usuario ) + " "+
      URLEncoder.encode( this.password ) + " ";


    for( Enumeration enum = favoritos.keys();
      enum.hasMoreElements(); ) {
      String favId = (String)enum.nextElement();
      String favUrl = (String)favoritos.get(favId);
      if( primero ) {
        cadena += URLEncoder.encode( favId ) + "!"+
          URLEncoder.encode( favUrl );
        primero = false;
        }
      else {
        cadena += "|"+URLEncoder.encode( favId )+ "!"+
          URLEncoder.encode( favUrl );
        }
      }
    return( cadena );
    }

  // Este es el método encargado de obtener la información que
  // corresponde a un usaurio, a partir de la cadena formateada
  // que se obtiene del archivo en donde se guarda la información
  // ca los usuarios
  public void leeDBString( String cadena ) {
    StringTokenizer token = new StringTokenizer( cadena );
    this.usuario = URLDecoder.decode( token.nextToken() );
    this.password = URLDecoder.decode( token.nextToken() );

    try {
      StringTokenizer ftoken = new StringTokenizer( token.nextToken(),"|" );
      while( ftoken.hasMoreTokens() ) {
        StringTokenizer htoken =
          new StringTokenizer( ftoken.nextToken(),"!" );
        favoritos.put( URLDecoder.decode(htoken.nextToken()),
          URLDecoder.decode(htoken.nextToken()) );
        }
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }
  }

//--------------------------------------- Final del fichero FavoritosDB.java
