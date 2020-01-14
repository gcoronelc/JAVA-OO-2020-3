//
//  java1801.java
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
//     Creacion: 22-May-1999  06:19:20
//     Revision: 09-Feb-2002  14:08:19
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo muy simple que muestra la forma en que se pueden
 * escribit HTTP servlets
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class java1801 extends HttpServlet {
  // Se utiliza Properties en lugar de una base de datos para no
  // complicar el ejemplo
  private Properties diccionario = new Properties();

  // Inicializamos el servlet y se leen las definiciones
  public void init( ServletConfig config ) throws ServletException {
    super.init( config );
    ServletContext sc = config.getServletContext();

    // Lee las propiedades de un fichero o de una pequeña
    // base de datos
    String dbpath = config.getInitParameter( "dbpath" );
    if( null == dbpath )
      throw new ServletException( "Parametro 'dbpath' no inicializado" );
    try {
      FileInputStream iStream =
        new FileInputStream( sc.getRealPath(dbpath) );
      diccionario.load( iStream );
    } catch( IOException e ) {
      throw new ServletException( "No se puede leer: " + dbpath );
      }
    }

  // Control del método POST. El HttpServlet envía la petición
  // POST a través de service() a este método
  protected void doPost( HttpServletRequest req,
    HttpServletResponse res ) throws IOException {
    String clave = req.getParameter( "pregunta" );
    String definicion = getDefinicion( clave );
    creaRespuesta( clave,definicion,res );
    }

  // Control del método GET. El HttpServlet envía la petición
  // GET a través de service() a este método
  protected void doGet( HttpServletRequest req,
    HttpServletResponse res ) throws IOException {
    String clave = req.getQueryString();
    String definicion = getDefinicion( clave );
    creaRespuesta( clave,definicion,res );
    }

  // Este método es el que devuelve la palabra, en caso de que
  // se encuentre en el diccionario
  protected String getDefinicion( String clave ) {
    String definicion = diccionario.getProperty( clave.toLowerCase() );
    if( null == definicion )
      return( "Nunca he oido hablar de esa clave." );
    else
      return( definicion );
    }

  // Devuelve el resultado de la petición al navegador
  protected void creaRespuesta( String clave,String definicion,
    HttpServletResponse res ) throws IOException {
    // Primero prepara la respuesta como una página Web, usando
    // un StringBuffer para almacenarla, a fin de poder calcular
    // la longitud de la página
    StringBuffer buffer = new StringBuffer();

    buffer.append( "<HTML>\n" );
    buffer.append( "<HEAD>\n" );
    buffer.append( "<TITLE>Tutorial de Java, Servlets</TITLE>\n" );
    buffer.append( "</HEAD>\n" );
    buffer.append( "<BODY BGCOLOR=white TEXT=black LINK=blue>\n");
    buffer.append( "<H2>Servlets, Diccionario</H2>\n" );
    buffer.append( "<P>La respuesta encontrada para <EM>" );
    buffer.append( clave );
    buffer.append( "</EM> es la siguiente:\n<P><BLOCKQUOTE>" );
    buffer.append( definicion );
    buffer.append( "</BLOCKQUOTE>\n" );
    buffer.append( "<table width=70% cellspacing=0 cellpadding=0 border=0>\n");
    buffer.append( "<tr bgcolor=#0000ff><td align=center height=16>\n");
    buffer.append( "<font face=Arial,Helvetica size=1\n");
    buffer.append( "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>\n");
    buffer.append( "</tr></table>\n");
    buffer.append( "</BODY>\n" );
    buffer.append( "</HTML>" );

    // Ahora ya se pasa la respuesta al navegador
    res.setContentType( "text/html" );
    res.setContentLength( buffer.length() );
    res.getOutputStream().print( buffer.toString() );
    }

  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java1801 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1801.java