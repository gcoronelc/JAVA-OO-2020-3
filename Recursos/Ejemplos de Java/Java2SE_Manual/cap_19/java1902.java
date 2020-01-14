//
//  java1902.java
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
//     Creacion: 05-Dec-1998  14:20:36
//     Revision: 09-Feb-2002  19:15:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la parte del servidor que funciona vía socket. Esta es una
 * aplicación Java que necesita correr en la misma máquina eue el cliente
 * que se le conecte. Escucha siempre en el puerto 6700 (aunque se puede
 * especificar cualquier otro cambiando la constante PUERTO).
 * Cuando se produce una conexión, el servidor acepta una cadena SQL,
 * la ejecuta y devuelve el resultado al cliente, que presenta esta
 * información en una ventana.
 * Esta aplicación no intenta ser robusta ni tiene la seguridad en
 * consideración, sino que se ha hecho solamente como modelo de muestra,
 * por lo que no hay mecanismos para seguir la pista a los clientes,
 * para que se puedan borrar las tablas, etc. Por ello, Yo no recomiendo
 * que se utilice este servidor sin estar pendientes de lo que se hace
 * con él.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class java1902 extends Thread {
  public static final int PUERTO = 6700;
  ServerSocket socketEscucha;

  public java1902() {
    try {
      socketEscucha = new ServerSocket( PUERTO );
    } catch( IOException e ) {
      System.err.println( e );
      }
    this.start();
    }


  public void run() {
    try {
      while( true ) {
        Socket socketCliente = socketEscucha.accept();
        SQLConexion c = new SQLConexion( socketCliente );
        }
    } catch( IOException e ) {
      System.err.println( e );
      }
    }


  public static void main( String[] argv ) {
    new java1902();
    }
  }


class SQLConexion extends Thread {
  protected Socket cliente;
  protected BufferedReader in;
  protected PrintStream out;
  protected String consulta;

  public SQLConexion( Socket socketCliente ) {
    cliente = socketCliente;
    try {
      in = new BufferedReader( new InputStreamReader( cliente.getInputStream() ) );
      out = new PrintStream( cliente.getOutputStream() );
    } catch ( IOException e ) {
      System.err.println( e );
      try {
        cliente.close();
      } catch( IOException e2 ) {
        }
      return;
      }
    this.start();
    }


  public void run() {
    try {
      consulta = in.readLine();
      System.out.println( "Lee la consulta <" + consulta + ">" );
      ejecutaSQL();
    } catch( IOException e ) {
    }finally {
      try {
        cliente.close();
      } catch( IOException e ) {
        }
      }
    }


  public void ejecutaSQL() {
    Connection conexion; // Objeto de conexión a la base de datos
    Statement sentencia; // Objeto con la sentencia SQL
    ResultSet resultado;   // Objeto con el resultado de la consulta SQL
    ResultSetMetaData resultadoMeta;
    boolean mas;   // Indicador de si hay más filas
    String driver = "jdbc:odbc:Tutorial";
    String usuario = "";
    String clave = "";
    String registro;
    int numCols, i;

    try {
      Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
      conexion = DriverManager.getConnection( driver,usuario,clave );

      sentencia = conexion.createStatement();
      resultado = sentencia.executeQuery( consulta );

      mas = resultado.next();
      if( !mas ) {
        out.println( "No hay mas filas." );
        return;
        }

      resultadoMeta = resultado.getMetaData();
      numCols = resultadoMeta.getColumnCount();
      System.out.println( numCols + " columnas en el resultado.");
      while( mas ) {
        // Se construye la cadena de respuesta
        registro = "";
        for( i=1; i <= numCols; i++ ) {
          registro = registro.concat( resultado.getString(i)+"  " );
          }
        out.println( registro );
        System.out.println( registro );
        mas = resultado.next();
        }

      resultado.close();
      sentencia.close();
      conexion.commit();
      conexion.close();
    } catch( Exception e ) {
      System.out.println( e.toString() );
      }
    }
  }

//------------------------------------------ Final del fichero java1902.java