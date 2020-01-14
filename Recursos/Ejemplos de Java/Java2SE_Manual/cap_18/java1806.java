//
//  java1806.java
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
//     Creacion: 25-May-1999  19:08:43
//     Revision: 09-Feb-2002  14:15:40
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este servlet implementa el envío de correo electrónico desde un servidor
 * Web utilizando el protocolo SMTP
 * Hay que tener cuidado, porque los nombres de los parámetros son sensibles
 * a mayúsculas y minúsculas, y deben especificarse en el fichero HTML tal
 * como se describen en el código fuente de este ejemplo
 */
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java1806 extends HttpServlet {
  // Constantes
  private static final int PUERTO_SMTP = 25;
  private static final char ERROR_SMTP_1 = '4';
  private static final char ERROR_SMTP_2 = '5';

  // Este es el método utilizado por el servicio HTTP. Controla los
  // métodos POST, GET y HEAD del protocolo HTTP
  // Cuando no se puede establecer conexión con el servidor, se
  // produce una excepción
  public void service( HttpServletRequest req,HttpServletResponse res )
    throws ServletException,IOException {
    String host;
    String dominio;
    String remitente;
    String destinatarios;
    String asunto;
    String texto;
    String datos;
    Vector sesion = new Vector( 20 );

    // Recoge los parámetros enviados por el cliente
    host = obtieneParametro( req,"host" );
    dominio = obtieneParametro( req,"dominio" );
    remitente = obtieneParametro( req,"remitente" );
    destinatarios = obtieneParametro( req,"destinatarios" );
    asunto = obtieneParametro( req,"asunto" );
    texto = obtieneParametro( req,"texto" );

    // Intenta enviar el correo, luego la respuesta y caza cualquier
    // excepción que se produzca
    try {
      // Envía el correo electrónico
      datos =
        "Fecha: "+ (new Date()).toString() +"\r\n"+
        "De: "+ remitente +"\r\n"+
        "Para: "+ destinatarios +"\r\n" +
        "Asunto: "+ asunto +"\r\n" +
        "\r\n"+
        texto +"\r\n";
        enviaCorreo( host,dominio,remitente,destinatarios,asunto,datos,sesion );
        // Envía una página de respuesta al cliente
        enviaRespuesta( req,res,
         "Correo encolado para ser enviado.",sesion );
      } catch( IOException e ) {
        // Envía la excepción al cliente
        enviaRespuesta( req,res,e.toString(),sesion );
        }
     }

  // Este método recoge el valor del parámetro que se indique de la
  // petición HTTP
  protected String obtieneParametro( HttpServletRequest req,String parametro ) {
    String[] valores;
    String resultado = "";

    valores = req.getParameterValues( parametro );
    if( valores != null ) {
      resultado = valores[0];
      }

    return( resultado );
    }

  // Este es el método encargado del envío del correo electrónico
  protected void enviaCorreo( String host,String dominio, String remitente,
    String destinatarios,String asunto,String datos,Vector sesion )
    throws IOException {
    Socket sockCorreo;
    BufferedReader in;
    DataOutputStream out;
    StringTokenizer token;

    // Abre la conexión con el servidor SMTP, y luego coge las referencias
    // para los canales de entrada y salida
    sockCorreo = new Socket( host,PUERTO_SMTP );
    in = new BufferedReader(
      new InputStreamReader( sockCorreo.getInputStream() ) );
    out = new DataOutputStream( sockCorreo.getOutputStream() );

    // Recoge la respuesta inicial del servidor para entablar conversación
    leeRespuesta( in,sesion );
    // Envía el comando de saludo al servidor
    enviaComando( out,"HELO "+dominio,sesion );
    leeRespuesta( in,sesion );
    // Envía la dirección del remitente
    enviaComando( out,"MAIL FROM: "+remitente,sesion );
    leeRespuesta( in,sesion );
    // Envía la lista de destinatarios a los que va dirigido el correo
    token = new StringTokenizer( destinatarios,"," );
    while( token.hasMoreElements() ) {
      enviaComando( out,"RCPT TO: "+token.nextToken(),sesion );
      leeRespuesta( in,sesion );
      }
    // Marca el inicio de los datos del correo
    enviaComando( out,"DATA",sesion );
    leeRespuesta( in,sesion );
    // Envía el mensaje de correo
    enviaComando( out,datos+".",sesion );
    leeRespuesta( in,sesion );
    // Cierra la sesión
    enviaComando( out,"QUIT",sesion );
    leeRespuesta( in,sesion );
    }

  // Envía un comando SMTP al servidor. Los comandos SMTP son de la
  // forma:
  // [clave] [dato] [CR][LF]
  private void enviaComando( DataOutputStream out,String comando,
    Vector sesion ) throws IOException {

    out.writeBytes( comando+"\r\n" );
    sesion.addElement( comando );
    // System.out.println( comando );
    }

  // Lee una respuesta desde el servidor
  private void leeRespuesta( BufferedReader in,Vector sesion )
    throws IOException {
    String respuesta;
    char estado;

    respuesta = in.readLine();
    estado = respuesta.charAt( 0 );
    sesion.addElement( respuesta );
    // System.out.println( respuesta );

    if( (estado == ERROR_SMTP_1) | (estado == ERROR_SMTP_2) ) {
      throw( new IOException( "SMTP: "+respuesta ) );
      }
    }

  // Envía la respuesta al cliente que se ha generado, indicándole lo que
  // ha pasado con el envío del correo
  protected void enviaRespuesta( HttpServletRequest req,
    HttpServletResponse res,String mensaje,Vector sesion )
    throws IOException {
    // Canal de salida
    ServletOutputStream out;

    // Referencia al canal para escribir la respuesta HTML
    res.setContentType( "text/html" );
    out = res.getOutputStream();

    // Envía la cabecera
    out.println( "<HTML><HEAD><TITLE>Tutorial de Java, Servlets</TITLE></HEAD>"+
      "<BODY BGCOLOR=white TEXT=black LINK=blue VLINK=purple>"+
      "<H2>Tutorial de Java, Correo Electrónico</H2>" );
    // Envía el mensaje de estado
    out.println( "<P>"+mensaje );
    // Envía los parámetros de la petición
    out.println( "<P><B>Parámetros del mensaje de correo electrónico:</B>" );
    out.println( "<PRE>" );
    out.println( "host          = "+ obtieneParametro( req,"host" ) );
    out.println( "dominio       = "+ obtieneParametro( req,"dominio" ) );
    out.println( "remitente     = "+ obtieneParametro( req,"remitente" ) );
    out.println( "destinatarios = "+ obtieneParametro( req,"destinatarios" ) );
    out.println( "asunto        = "+ obtieneParametro( req,"asunto" ) );
    out.println( "texto         = "+ obtieneParametro( req,"texto" ) );
    out.println( "</PRE>" );
    // Envía el traceado de la sesión, para que el usuario sepa lo
    // que ha pasado con el envío
    out.println( "<P><B>Traceado de la Sesión:</B>" );
    out.println( "<PRE>" );

    Enumeration en = sesion.elements();
    while( en.hasMoreElements() ) {
      out.println( (String)en.nextElement() );
      }
    out.println("</PRE>");
    // Envía el pie de la página
    out.println( "<table width=70% cellspacing=0 cellpadding=0 border=0>"+
      "<tr bgcolor=#0000ff><td align=center height=16>"+
      "<font face=Arial,Helvetica size=1 "+
      "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>"+
      "</tr></table>"+
      "</BODY></HTML>" );
    }

  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java1806 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1806.java