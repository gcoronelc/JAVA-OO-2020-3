//
//  java1901.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 12-Apr-1998  12:00:30
//     Revision: 09-Feb-2002  19:17:25
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo supone que exite una Base de Datos "TUTORIAL" ya registrada
 * con Access, a la cual se le añade una tabla más que contiene la
 * información que está codificada en el ejemplo.
 */
import java.sql.*;

class java1901 {
  static public void main( String[] args ) {
    Connection conexion;
    Statement sentencia;
    ResultSet resultado;

    System.out.println( "Iniciando programa." );

    // Se carga el driver JDBC-ODBC
    try {
      Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
    } catch( Exception e ) {
      System.out.println( "No se pudo cargar el puente JDBC-ODBC." );
      return;
      }

    try {
      // Se establece la conexión con la base de datos
      conexion =
        DriverManager.getConnection( "jdbc:odbc:Tutorial","","" );
      sentencia = conexion.createStatement();
      try {
        // Se elimina la tabla en caso de que ya existiese
        sentencia.execute( "DROP TABLE LIBROS" );
      } catch( SQLException e ) {
        }

      // Esto es código SQL
      sentencia.execute( "CREATE TABLE LIBROS ("+
        " TITULO VARCHAR(50) NOT NULL, "+
        " AUTOR VARCHAR(30) NOT NULL, "+
        " PRECIO FLOAT NOT NULL, "+
        " PUBLICACION DATETIME) " );
      sentencia.execute( "INSERT INTO LIBROS "+
        "VALUES('Piratas Cibernéticos',"+
        "'J. de Marcelo',18.00,'11/14/2001')" );
      sentencia.execute( "INSERT INTO LIBROS "+
        "VALUES('JavaServer Pages: Manual de Usuario y Tutorial',"+
        "'A. Froufe',18.00,'12/28/2001')" );
      sentencia.execute( "INSERT INTO LIBROS "+
        "VALUES('JAVA 2: Manual de Usuario y Tutorial',"+
        "'A. Froufe',35.75,'12/28/1999')" );
      sentencia.execute( "INSERT INTO LIBROS "+
        "VALUES('Domine Microsoft Word 2000',"+
        "'C. Pérez',35.75, NULL)" );
    } catch( Exception e ) {
      System.out.println( e );
      return;
      }
    System.out.println( "Creacion finalizada." );
    }
  }

//------------------------------------------ Final del fichero java1901.java