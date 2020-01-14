//
//  java1905.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 23-Dec-1998  16:13:12
//     Revision: 09-Feb-2002  19:41:09
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un servlet que permite acceder a una base de datos a trav�s de
 * ODBC.
 * Puede ser la base de un sistema de noticias. Tambi�n puede ser la base
 * para un sistema de autorizaci�n de acceso a otras zonas del sitio Web,
 * o cualquier otra aplicaci�n que el lector quiera proporcionar.
 * Se trata de un sistema de gesti�n de art�culos y de usuarios, de forma
 * que hay usuarios con niveles de autorizaci�n diferentes, unos de acceso
 * a leer los art�culos y otros autorizados tambi�n a enviarlos.
 * Cuando un usuario solicita enviar un art�culo, el servlet le proporciona
 * la p�gina en la que debe introducir la informaci�n.
 */
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java1905 extends HttpServlet {
  String DBurl = "jdbc:odbc:Tutorial";
  String usuarioGet = "";
  String usuarioPost = "";
  String claveGet = "";
  String clavePost = "";
  Connection con;
  DatabaseMetaData metaData;

  // Este m�todo es el que se encarga de establecer e inicializar
  // la conexi�n con la base de datos
  public void init( ServletConfig conf ) throws ServletException {
    SQLWarning w;

    super.init( conf );
    try {
      Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
      con = DriverManager.getConnection( DBurl,usuarioPost,clavePost );
      if( (w = con.getWarnings()) != null ) {
        while( w != null ) {
          log( "SQLWarning: "+w.getSQLState()+'\t'+
               w.getMessage()+'\t'+w.getErrorCode()+'\t' );
          w = w.getNextWarning();
          }
        }
    } catch( ClassNotFoundException e ) {
      throw new ServletException( "init" );
    } catch( SQLException e ) {
      try {
        con = DriverManager.getConnection( DBurl,usuarioGet,claveGet );
      } catch( SQLException ee ) {
        ee.printStackTrace();
        while( e != null ) {
          log( "SQLException: "+e.getSQLState()+'\t'+
               e.getMessage()+'\t'+e.getErrorCode() );
          e = e.getNextException();
          }
        throw new ServletException( "init" );
        }
      }
    }


  public void service( HttpServletRequest req,HttpServletResponse res )
  throws ServletException,IOException {
    String usuario = req.getParameter( "usuario" );
    String autorizado;
    String accion = req.getParameter( "accion" );

    try {
      autorizado = autorizacion( req );
      if( accion.equals( "Leer Articulos" )
           && !autorizado.equals( "ACCESO DENEGADO" ) ) {
        leerArticulo( req,res );
        }
      else if( accion.equals( "Enviar" )
        && autorizado.equals( "POST" ) ) {
        enviarArticulo( req,res );
        }
      else if ( accion.equals("Envio de Articulos" ) ) {
        if ( usuario == null )
          usuario = " ";
        PrintWriter out = new PrintWriter( res.getOutputStream() );
        out.println( "<HTML>" );
        out.println( "<HEAD><TITLE>Env�o de Art�culos</TITLE></HEAD>" );
        out.println( "<BODY>" );
        out.println( "<CENTER><H1>Env�o de Art�culos</H1></CENTER>" );
        out.println( "<FORM  ACTION=http://localhost:8080/noticias METHOD=POST>" );
        out.println( "<H2>Env�o de Art�culos</H2>" );
        out.println( "<P>Por este nombre te reconoce el Sistema: <BR>" );
        out.println( "<INPUT NAME=usuario TYPE=text VALUE='"+usuario+"' SIZE=16 MAXLENGTH=16><BR>" );
        out.println( "Introduce tu contrase�a: <BR>" );
        out.println( "<INPUT NAME=clave TYPE=password SIZE=8 MAXLENGTH=8></P>" );
        out.println( "<H3>T�tulo del Art�culo a enviar:</H3>" );
        out.println( "<P><INPUT NAME=titulo TYPE=text SIZE=25 MAXLENGTH=70></P>" );
        out.println( "<H3>Cuerpo del Art�culo</H3>" );
        out.println( "<P><TEXTAREA NAME=cuerpo ROWS=10 COLS=70></TEXTAREA></P>" );
        out.println( "<P><INPUT NAME=accion TYPE=submit VALUE='Enviar'></FORM>" );
        out.println( "</BODY></HTML>" );
        out.flush();
        }
      else {
        PrintWriter out = new PrintWriter( res.getOutputStream() );
        out.println( "<html>" );
        out.println( "<head><title>Acceso Denegado</title></head>" );
        out.println( "<body>" );
        out.println( "Se ha producido un error de acceso:<br>" );
        out.println( "El usuario o clave que has introducido no son v�lidos o<br>" );
        out.println( "no tienes acceso a esta funcionalidad." );
        out.println( "</body></html>" );
        out.flush();
        }
    } catch( SQLException e ) {
      while( e != null ) {
        log( "SQLException: "+e.getSQLState()+'\t'+
             e.getMessage()+'\t'+e.getErrorCode()+'\t' );
        e = e.getNextException();
        }
      // Aqu� habr�a que insertar el c�digo necesario para restablecer la
      // conexi�n llamando a init() de nuevo y volviendo a realizar la
      // llamada al m�todo service(req,res)
      }
    }


  // Se cierra la conexi�n con la base de datos
  public void destroy() {
    try {
      con.close();
    } catch( SQLException e ) {
      while( e != null ) {
        log( "SQLException: "+e.getSQLState()+'\t'+
             e.getMessage()+'\t'+e.getErrorCode()+'\t' );
        e = e.getNextException();
        }
    } catch( Exception e ) {
      e.printStackTrace();
      }
    }


  // Este m�todo ejecuta la consulata a la base de datos y devuelve el
  // resultado, para formatear la salida y presentar el resultado de
  // la consulta de art�culos al usuario
  public void leerArticulo( HttpServletRequest req,HttpServletResponse res )
  throws IOException,SQLException {
    Statement stmt = con.createStatement();
    String consulta;
    ResultSet rs;

    res.setStatus( res.SC_OK );
    res.setContentType( "text/html" );
    consulta = "SELECT articulos.cuerpo,articulos.titulo,articulos.usuario,usuarios.nombre,usuarios.empresa ";
    consulta += "FROM articulos,usuarios WHERE articulos.usuario=usuarios.usuario";
    rs = stmt.executeQuery( consulta );

    PrintWriter out = new PrintWriter( res.getOutputStream() );
    out.println( "<HTML>" );
    out.println( "<HEAD><TITLE>Art�culos Enviados</TITLE></HEAD>" );
    out.println( "<BODY>" );

    while( rs.next() ) {
      out.println( "<H2>" );
      out.println( rs.getString(1) );
      out.println( "</H2><p>" );
      out.println( "<I>Enviado desde: "+rs.getString(5)+"</I><BR> " );
      out.println( "<B>"+rs.getString(2)+"</B>, por "+rs.getString(4) );
      out.println( "<HR>" );
      }
    out.println( "</BODY></HTML>" );
    out.flush();
    rs.close();
    stmt.close();
    }


  public void enviarArticulo( HttpServletRequest req,HttpServletResponse res )
  throws IOException,SQLException {
    Statement stmt = con.createStatement();
    String consulta = "";
    String usuario = req.getParameter( "usuario" );

    PrintWriter out = new PrintWriter( res.getOutputStream() );
    res.setStatus( res.SC_OK );
    res.setContentType( "text/html" );
    out.println( "<HTML>" );
    out.println( "<HEAD><TITLE>Env�o Realizado</TITLE></HEAD>" );
    out.println( "<BODY>" );

    consulta = "INSERT INTO articulos VALUES( '";
    consulta += req.getParameter( "titulo" )+"','"+usuario+"','";
    consulta += req.getParameter("cuerpo")+"')";
    int result = stmt.executeUpdate( consulta );

    if( result != 0 ) {
      out.println( "Tu art�culo ha sido aceptado e insertado correctamente." );
      }
    else {
      out.println( "Se ha producido un error en la aceptaci�n de tu art�culo.<BR>" );
      out.println( "Contacta con el Administrador de la base de datos, o consulta<BR>" );
      out.println( "el fichero <I>log</I> del servlet." );
      }
    out.println( "</BODY></HTML>" );
    out.flush();
    stmt.close();
    }


  // Devuelve la informaci�n del Servlet
  public String getServletInfo() {
    return "Servlet JDBC (Tutorial de Java), 1998";
    }


  public String autorizacion( HttpServletRequest req ) throws SQLException {
    Statement stmt = con.createStatement();
    String consulta;
    ResultSet rs;
    String valido = "";
    String usuario = req.getParameter( "usuario" );
    String clave = req.getParameter( "clave" );
    String permiso="";

    consulta = "SELECT admitirEnvio FROM usuarios WHERE usuario = '" + usuario;
    consulta += "' AND clave = '"+clave+"'";
    rs = stmt.executeQuery( consulta );

    while( rs.next() ) {
      valido = rs.getString(1);
      }
    rs.close();
    stmt.close();

    if( valido.equals( "" ) ) {
      permiso = "ACCESO DENEGADO";
      }
    else {
      // Permiso s�lo para lectura de art�culos
      if ( valido.equals( "N" ) ) {
        permiso = "GET";
        // Permiso para lectura y env�o de art�culos
        }
      else if ( valido.equals( "S" ) ) {
        permiso = "POST";
        }
      }
    return permiso;
    }
  }

//------------------------------------------ Final del fichero java1905.java