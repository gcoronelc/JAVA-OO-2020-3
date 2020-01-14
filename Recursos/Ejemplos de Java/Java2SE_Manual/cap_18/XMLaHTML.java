//
//  XMLaHTML.java
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
//     Creacion: 27-Ene-2002  11:26:18
//     Revision: 09-Feb-2002  14:25:24
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta aplicación implementa un filtro que convierte el contenido de
 * un archivo XML a un formato más visible en un navegador, utilizando
 * con medio de conversión una hoja de estilo XSL, que debe indicarse
 * como parámetro en el descriptor de la aplicación.
 * Se hace uso tambiñén de la posibilidad que ofrece el API servlet
 * de ir volvando información de log sobre los archivos que genera
 * el propio servidor como resultado de las acciones que va realizando.
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XMLaHTML implements Filter {
  private ServletContext ctx;
  private String xslt;
  private TransformerFactory txf = TransformerFactory.newInstance();
  private Transformer tx;

  private static class ByteArrayServletStream extends ServletOutputStream {
    ByteArrayOutputStream baos;
    ByteArrayServletStream( ByteArrayOutputStream baos ) {
      this.baos = baos;
      }
    public void write( int param ) throws IOException {
      baos.write( param );
      }
    }

  private static class ByteArrayPrintWriter {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintWriter pw = new PrintWriter( baos );
    private ServletOutputStream sos = new ByteArrayServletStream( baos );

    public PrintWriter getWriter() {
      return( pw );
      }
    public ServletOutputStream getStream() {
      return( sos );
      }
    byte[] toByteArray() {
      return( baos.toByteArray() );
      }
    }

  // Este método se invoca cuando se lanza el contenedor de Servlets
  public void init( FilterConfig fc ) throws ServletException {
    ctx = fc.getServletContext();
    // Recoge el parámetro en donde se indica el archivo de la
    // hoja de estilo que se utilizará en la conversión del archivo
    // xml, y que se especifica en el descriptor web.xml
    xslt = fc.getInitParameter( "xslt" );
    ctx.log( fc.getFilterName()+
      " usando hoja de estilo "+ xslt );
    try {
      tx = txf.newTransformer(
        new StreamSource(ctx.getResourceAsStream(xslt)) );
    } catch( Exception e ) {
      ctx.log( "No se puede iniciar la transformacion",e );
      throw new ServletException(
        "No se puede iniciar la transformacion",e );
      }
    }

  public String httpReqLine( HttpServletRequest req ) {
    StringBuffer ret = req.getRequestURL();
    String cadena = req.getQueryString();

    if( cadena != null ) {
      ret.append("?").append(cadena);
      }
    return( ret.toString() );
    }

  public String getHeaders( HttpServletRequest req ) throws IOException {
    Enumeration en = req.getHeaderNames();
    StringBuffer sb = new StringBuffer();

    while( en.hasMoreElements() ) {
      String atributo = (String)en.nextElement();

      sb.append(atributo).append(": ").append(
        req.getHeader(atributo)).append("\n");
      }
    return( sb.toString() );
    }

  // Este es el método principal del filtro en donde se realizan las
  // acciones asignadas, en este caso la conversión del fichero XML
  public void doFilter( ServletRequest request,
    ServletResponse response,FilterChain chain )
    throws IOException,ServletException {
    HttpServletRequest hsr = (HttpServletRequest)request;
    final HttpServletResponse resp = (HttpServletResponse)response;
    ctx.log( "Accediendo al filtro para "+ httpReqLine(hsr)+ " "+
      hsr.getMethod() );

    final ByteArrayPrintWriter pw = new ByteArrayPrintWriter();
    final boolean[] txNecesario = new boolean[1];

    // Esta clase es auxiliar, y solamente se utiliza para hacer pasar
    // la respuesta a través de los métodos que implementa
    HttpServletResponse wResp =
      new HttpServletResponseWrapper( resp ) {
      // Devuelve el objeto de salida sobre el que se escribe la
      // respuesta
      public PrintWriter getWriter() {
        return( pw.getWriter() );
        }
      // Devuelve el canal de salida sobre el que se escribe la
      // respuesta
      public ServletOutputStream getOutputStream() {
        return( pw.getStream() );
        }
      // Comprueba si el contenido que se va a devolver es xml, en
      // cuyo caso fija a verdadero el flag que indica que es necesaria
      // la transformación del contenido
      public void setContentType( String tipo ) {
        if( tipo.equals("text/xml") ) {
          ctx.log( "Convirtiendo xml a html" );
          resp.setContentType( "text/html; charset=ISO-8859-1" );
          txNecesario[0] = true;
          }
        else {
          resp.setContentType( tipo );
          }
        }
      };

    chain.doFilter( request,wResp );
    byte[] bytes = pw.toByteArray();

    // Si no se ha escrito nada en la salida hacia el cliente, no hay
    // nada que transformar
    if( bytes == null || (bytes.length == 0) ) {
      ctx.log( "No hay contenido!" );
      }
    // Si el contenido en la salida es xml, es necesario transformar
    // ese contenido y formatearlo a html antes de enviarlo
    if( txNecesario[0] == true ) {
      try {
        // Si se trata de archivos muy grandes, las conversiones
        // deberían estar ya calculadas al llegar a este punto
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tx.transform( new StreamSource(new ByteArrayInputStream(bytes)),
          new StreamResult(baos) );
        byte[] xformBytes = baos.toByteArray();
        // Es importante asegurarse de que el tipo y longitud de la
        // respuesta que se envía al navegador son correctas y se
        // corresponden con lo que se está mandando
        resp.setContentLength( xformBytes.length );
        resp.getOutputStream().write( xformBytes );
        ctx.log( "XML -> HTML conversión concluida" );
      } catch( Exception e ) {
        throw new ServletException(
          "No se puede convertir el documento",e );
        }
      }
    else {
      resp.getOutputStream().write( bytes );
      }
    }

  // Este método se invoca cuando el filtro es descargado por
  // parte del servidor.
  public void destroy() {
    ctx.log( "Destruyendo el filtro..." );
    }
  }

//------------------------------------------ Final del fichero XMLaHTML.java
